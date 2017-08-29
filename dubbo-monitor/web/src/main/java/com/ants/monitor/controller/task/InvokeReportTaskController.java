package com.ants.monitor.controller.task;

import com.alibaba.dubbo.common.Constants;
import com.ants.monitor.bean.MonitorConstants;
import com.ants.monitor.bean.bizBean.HostBO;
import com.ants.monitor.bean.entity.InvokeDO;
import com.ants.monitor.biz.bussiness.InvokeBiz;
import com.ants.monitor.biz.support.service.ApplicationService;
import com.ants.monitor.biz.support.service.HostService;
import com.ants.monitor.common.tools.TimeUtil;
import com.ants.monitor.dao.redisManager.InvokeRedisManager;
import com.ants.monitor.dao.redisManager.InvokeReportManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * Created by zxg on 16/1/28.
 * 13:49
 * 报表数据，每小时统计一次开始统计
 */
@RestController
@RequestMapping("/monitor/invokeReportTask")
@Slf4j
public class InvokeReportTaskController {


    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @Autowired
    private InvokeReportManager invokeReportManager;
    @Autowired
    private InvokeRedisManager invokeRedisManager;

    @Autowired
    private InvokeBiz invokeBiz;
    @Autowired
    private ApplicationService applicationService;
    @Autowired
    private HostService hostService;

    //每天每个小时小时 :01
    @Scheduled(cron = "0 1 * * * ?")
    public void everyHourDo(){
        //应用间调用数量
        AppSumOnHourProcess appSumOnHourProcess = new AppSumOnHourProcess();
        taskExecutor.execute(appSumOnHourProcess);

        //应用作为提供者 每小时被消费的数量
        AppConsumerOnHourProcess appConsumerOnHourProcess = new AppConsumerOnHourProcess();
        taskExecutor.execute(appConsumerOnHourProcess);

    }

    //每天凌晨 00：01分执行
    @Scheduled(cron = "0 1 0 * * ?")
    public void everyDayDo(){

        //应用作为提供者 每天被消费的数量
        AppConsumerOnDayProcess appConsumerOnDayProcess = new AppConsumerOnDayProcess();
        taskExecutor.execute(appConsumerOnDayProcess);
        //应用方法排行榜
        taskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    appMethodRankOnDay();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    //应用之间调用总数
    private class AppSumOnHourProcess implements Runnable {
        @Override
        public void run() {
            try {
                appSumOnHour();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    //应用提供每小时 成功、失败数
    private class AppConsumerOnHourProcess implements Runnable {
        @Override
        public void run() {
            try {
                appConsumerHourInHour();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    //应用提供每天 成功、失败数
    private class AppConsumerOnDayProcess implements Runnable {
        @Override
        public void run() {
            try {
                appConsumerOnHourToDay();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    //每天每个小时小时 :01 统计每个应用每个小时相互调用情况
    private void appSumOnHour() {
        Date now = new Date();
        Date lastHourDate = TimeUtil.getBeforHourByNumber(now, -1);
        String lastHourDay = TimeUtil.getDateString(now);
        String lastHour = TimeUtil.getHourString(lastHourDate);

        List<String> allApplication = applicationService.getAllApplicationsCache();


        List<InvokeDO> invokeDOList = invokeRedisManager.getInvokeByHour(lastHour);

        for (String applicationName : allApplication) {
            Map<String,Map<String,Integer>> appDayMap = invokeReportManager.getAppRelationByAppOnDay(applicationName, lastHourDay);
            Map<String, Integer> providerMap = appDayMap.get(Constants.PROVIDER);
            Map<String, Integer> consumerMap = appDayMap.get(Constants.CONSUMER);

            if(providerMap == null){
                providerMap = new HashMap<>();
                appDayMap.put(Constants.PROVIDER,providerMap);
            }
            if(consumerMap == null){
                consumerMap = new HashMap<>();
                appDayMap.put(Constants.CONSUMER,consumerMap);
            }

            Boolean has_pro = false;
            Boolean has_consu = false;
            for (InvokeDO invokeDO : invokeDOList) {
                String invokeType = invokeDO.getAppType();
                if (invokeType.equals(Constants.PROVIDER)) {
                    // 做为提供者，提供服务--找不到消费者
                    continue;
                }
                String providerHost = invokeDO.getProviderHost();
                String providerPort = invokeDO.getProviderPort();
                Set<String> nameSet = hostService.getAppNameByHost(new HostBO(providerHost, providerPort));
                if (nameSet.isEmpty() || nameSet.size() > 1) {
                    // 有且只有一个
                    continue;
                }
                String appName = invokeDO.getApplication();
                String providerName = nameSet.iterator().next();
                if (applicationName.equals(appName)) {
                    Integer success = invokeDO.getSuccess();
                    // app 作为消费者，被提供
                    Integer providerSum = providerMap.get(providerName) == null ? Integer.valueOf(0) : providerMap.get(providerName);
                    providerSum += success;
                    providerMap.put(providerName, providerSum);
                    has_pro = true;
                }
                if (applicationName.equals(providerName)) {
                    // app 作为提供者，被消费
                    Integer success = invokeDO.getSuccess();
                    Integer consumerSum = consumerMap.get(appName) == null ? Integer.valueOf(0) : consumerMap.get(appName);
                    consumerSum += success;
                    consumerMap.put(appName, consumerSum);
                    has_consu = true;
                }
            }
            if (!has_pro) {
                appDayMap.remove(Constants.PROVIDER);
            }
            if (!has_consu) {
                appDayMap.remove(Constants.CONSUMER);
            }

            if (has_consu || has_pro) {
                invokeReportManager.saveAppRelationByAppOnDay(applicationName, lastHourDay, appDayMap);
            }
        }
    }

    // 每小时的数据调用
    private void appConsumerHourInHour() {
        Date now = new Date();
        Date lastHourDate = TimeUtil.getBeforHourByNumber(now, -1);
        String lastHourDay = TimeUtil.getDateString(now);
        String lastHour = TimeUtil.getHourString(lastHourDate);

        List<String> allApplication = applicationService.getAllApplicationsCache();

        List<InvokeDO> invokeDOList = invokeRedisManager.getInvokeByHour(lastHour);

        for (String applicationName : allApplication) {
            Map<String, Map<String,?>> saveMap = (Map<String, Map<String, ?>>) invokeReportManager.getConsumerByAppOnHour(applicationName,lastHourDay);

            Boolean is_ok = false;

            for(InvokeDO invokeDO : invokeDOList){
                String invokeType = invokeDO.getAppType();
                if(invokeType.equals(Constants.PROVIDER)){
                    // 做为提供者，提供服务--找不到消费者
                    continue;
                }
                String providerHost = invokeDO.getProviderHost();
                String providerPort = invokeDO.getProviderPort();
                Set<String> nameSet = hostService.getAppNameByHost(new HostBO(providerHost, providerPort));
                if(nameSet.isEmpty() || nameSet.size() > 1){
                    // 有且只有一个
                    continue;
                }
                String appName = invokeDO.getApplication();
                String providerName = nameSet.iterator().next();
                if(applicationName.equals(providerName)) {
                    is_ok = true;
                    // app 作为提供者，被消费
                    Integer success = invokeDO.getSuccess();
                    Integer fail = invokeDO.getFailure();
                    // 存储
                    Map<String, Object> hourSumMap = (Map<String, Object>) saveMap.get(appName);
                    if(null == hourSumMap){
                        hourSumMap = new HashMap<>();
                        saveMap.put(appName, hourSumMap);
                    }
                    Map<String,Integer> sumMap = (Map<String, Integer>) hourSumMap.get(lastHour);
                    if(sumMap == null){
                        sumMap = new HashMap<>();
                        sumMap.put(MonitorConstants.SUCCESS,success);
                        sumMap.put(MonitorConstants.FAIL,fail);
                        hourSumMap.put(lastHour,sumMap);
                    }else{
                        success += sumMap.get(MonitorConstants.SUCCESS);
                        fail += sumMap.get(MonitorConstants.FAIL);
                        sumMap.put(MonitorConstants.SUCCESS,success);
                        sumMap.put(MonitorConstants.FAIL,fail);
                    }
                }
            }

            if(is_ok) {
                invokeReportManager.saveConsumerByAppOnHour(applicationName, lastHourDay, saveMap);
            }

        }




    }

    //每天凌晨 00:01 统计每个应用昨天的每小时消费者消费情况，汇总为一天
    private void appConsumerOnHourToDay() {
        String yesterday = TimeUtil.getBeforDateByNumber(new Date(), -1);
        List<String> allApplication = applicationService.getAllApplicationsCache();

        for (String applicationName : allApplication) {
            Map<String, Map<String,?>> saveMap = (Map<String, Map<String, ?>>) invokeReportManager.getConsumerByAppOnHour(applicationName,yesterday);

            Map<String,Map<String,Integer>> dayMap = new HashMap<>();

            for(Map.Entry<String, Map<String,?>> mapEntry:saveMap.entrySet()){
                String consumerApp = mapEntry.getKey();
                Map<String, ?> hourSumMap = mapEntry.getValue();

                Boolean is_ok = false;
                Integer success = 0;
                Integer fail = 0;
                for(Map.Entry<String,?> hourEntry : hourSumMap.entrySet()){
                    Map<String,Integer> sumMap = (Map<String, Integer>) hourEntry.getValue();

                    success += sumMap.get(MonitorConstants.SUCCESS);
                    fail += sumMap.get(MonitorConstants.FAIL);
                    is_ok = true;
                }

                if(is_ok) {
                    // 存当日 sourceAPP 被 consumerApp 消费的成功数
                    Map<String, Integer> numberMap = new HashMap<>();
                    numberMap.put(MonitorConstants.SUCCESS, success);
                    numberMap.put(MonitorConstants.FAIL, fail);
                    dayMap.put(consumerApp,numberMap);
                }

            }

            if(!dayMap.isEmpty()){
                invokeReportManager.saveConsumerByAppOnDay(applicationName, yesterday, dayMap);
            }

        }
    }



    //每天凌晨统计之前一天的每个应用排行榜
    private void appMethodRankOnDay(){
        List<String> allApplication = applicationService.getAllApplicationsCache();
        for (String applicationName : allApplication) {
            invokeBiz.getMethodRankByAppName(applicationName);
        }
    }
}

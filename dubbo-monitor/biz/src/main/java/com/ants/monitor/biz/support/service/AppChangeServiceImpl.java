package com.ants.monitor.biz.support.service;

import com.alibaba.dubbo.common.utils.ConcurrentHashSet;
import com.ants.monitor.bean.bizBean.ApplicationChangeBO;
import com.ants.monitor.biz.support.processor.NotifyAppChangeProcessor;
import com.ants.monitor.common.tools.BdUtil;
import com.ants.monitor.common.tools.TimeUtil;
import com.ants.monitor.dao.redisManager.AppChangeRedisManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zxg on 16/1/5.
 * 15:04
 */
@Slf4j
@Service
public class AppChangeServiceImpl implements AppChangeService {

    @Autowired
    private AppChangeRedisManager appChangeRedisManager;
    @Autowired
    private NotifyAppChangeProcessor notifyAppChangeProcessor;

    @Override
    public List<ApplicationChangeBO> getRecentInsertList() {
        return appChangeRedisManager.getRecentInsertList();
    }

    @Override
    public List<ApplicationChangeBO> getRecentDeleteList() {
        return appChangeRedisManager.getRecentDeleteList();
    }

    @Override
    public List<ApplicationChangeBO> getChangeListByDay(String day, Integer pageIndex, Integer limit) {
        return appChangeRedisManager.getChangeListByDay(day, pageIndex, limit);
    }

    @Override
    public Integer getListSum(String day) {
        return appChangeRedisManager.getListSum(day);
    }

    @Override
    public Set<String> getDaySet(String month) {
        return appChangeRedisManager.getDaySet(month);
    }

    @Override
    public void afterChangeDeleteDo(ApplicationChangeBO applicationChangeBO) {
        log.info("remove:" + applicationChangeBO.toString());

        Date now = new Date();
        String thisTime = TimeUtil.getTimeString(now);

        ApplicationChangeBO insertBO = new ApplicationChangeBO(applicationChangeBO.getHost(),applicationChangeBO.getPort(),applicationChangeBO.getAppName(),applicationChangeBO.getCategory(),applicationChangeBO.getOrganization());
        insertBO.setTime(thisTime);
        insertBO.setDoType("delete");


        appChangeRedisManager.addDeleteRecentRecord(insertBO);
        //应用停止服务后的处理
        notifyAppChangeProcessor.stopApp(insertBO);
    }

    @Override
    public void afterChangeInsertDo(ApplicationChangeBO applicationChangeBO) {
        log.info("add:" + applicationChangeBO.toString());

        Date now = new Date();
        String thisTime = TimeUtil.getTimeString(now);

        ApplicationChangeBO insertBO = new ApplicationChangeBO(applicationChangeBO.getHost(),applicationChangeBO.getPort(),applicationChangeBO.getAppName(),applicationChangeBO.getCategory(),applicationChangeBO.getOrganization());
        insertBO.setTime(thisTime);
        insertBO.setDoType("insert");


        appChangeRedisManager.addInsertRecentRecord(insertBO);
        //应用启动服务后的处理
        notifyAppChangeProcessor.startApp(insertBO);
    }

    @Override
    public Map<String, Set<ApplicationChangeBO>> getChangeAppCache() {
        Map<String,List<Map<String,String>>> map = appChangeRedisManager.getChangeAppCache();

        //将map里面的对象转为ApplicationChangeBO
        Map<String,Set<ApplicationChangeBO>> resultMap = new ConcurrentHashMap<>();
        if(null == map){
            return null;
        }
        for(Map.Entry<String,List<Map<String,String>>> entry : map.entrySet()){
            String entryKey = entry.getKey();
            List<Map<String,String>> entryList = entry.getValue();

            Set<ApplicationChangeBO> appSet = new ConcurrentHashSet<>();
            for(Map<String,String> applicationChangeMap : entryList){
                ApplicationChangeBO applicationChangeBO = new ApplicationChangeBO();
                BdUtil.transMap2Bean2(applicationChangeMap, applicationChangeBO);
                appSet.add(applicationChangeBO);
            }
            resultMap.put(entryKey,appSet);
        }
        return resultMap;
    }

    @Override
    public void saveChangeAppCache(Map<String, Set<ApplicationChangeBO>> map) {
        appChangeRedisManager.saveChangeAppCache(map);
    }
}

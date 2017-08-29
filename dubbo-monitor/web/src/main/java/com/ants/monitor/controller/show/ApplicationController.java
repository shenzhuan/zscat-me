package com.ants.monitor.controller.show;

import com.alibaba.dubbo.common.Constants;
import com.ants.monitor.bean.MonitorConstants;
import com.ants.monitor.bean.ResultVO;
import com.ants.monitor.bean.bizBean.ApplicationBO;
import com.ants.monitor.bean.bizBean.MethodRankBO;
import com.ants.monitor.bean.bizBean.ServiceBO;
import com.ants.monitor.biz.bussiness.InvokeBiz;
import com.ants.monitor.biz.support.service.ApplicationService;
import com.ants.monitor.common.tools.TimeUtil;
import com.ants.monitor.dao.redisManager.InvokeReportManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * Created by zxg on 15/11/7.
 * 16:39
 */
@Controller
@RequestMapping("/monitor/application")
public class ApplicationController {
    @Autowired
    private ApplicationService applicationService;
    @Autowired
    private InvokeBiz invokeBiz;

    @Autowired
    private InvokeReportManager invokeReportManager;

    //主页
    @RequestMapping(value = "main")
    public ModelAndView main() {
        return new ModelAndView("monitorView/application/appIndex");
    }


    //所有服务和其相关信息
    @RequestMapping(value = "/getAllAPPAndRelation", method = RequestMethod.GET)
    public
    @ResponseBody
    ResultVO getAllAPPAndRelation() {
        try {
            Map<String, Object> resultMap = new HashMap<>();

            Map<String, ApplicationBO> allApplicationsMap = applicationService.getApplicationsBOMap();
            List<ApplicationBO> appList = new ArrayList<>();

            Set<String> groupSet = new HashSet<>();
            Integer appSum = 0;
            for (Map.Entry<String, ApplicationBO> applicationBOEntry : allApplicationsMap.entrySet()) {
                appSum += 1;
                ApplicationBO applicationBO = applicationBOEntry.getValue();
                String organization = applicationBO.getOrganization();
                if (!organization.equals("")) {
                    groupSet.add(organization);
                }

                Set<String> serviceSet = new HashSet<>();
                Map<String, Set<ServiceBO>> serviceMap = applicationBO.getServiceMap();
                if (serviceMap != null) {
                    for (Map.Entry<String, Set<ServiceBO>> entry : serviceMap.entrySet()) {
                        Set<ServiceBO> serviceBOSet = entry.getValue();
                        for (ServiceBO serviceBO : serviceBOSet) {
                            serviceSet.add(serviceBO.getServiceName());
                        }
                    }
                }

                Set<String> providersSet = applicationBO.getProvidersSet();
                Set<String> consumersSet = applicationBO.getConsumersSet();
                if (!serviceSet.isEmpty()) applicationBO.setServiceSum(serviceSet.size());
                if (null != providersSet) applicationBO.setProviderSum(providersSet.size());
                if (null != consumersSet) applicationBO.setConsumerSum(consumersSet.size());
                appList.add(applicationBO);
            }

            //对appList 排序，按首字母
            Collections.sort(appList, new Comparator<ApplicationBO>() {
                @Override
                public int compare(ApplicationBO o1, ApplicationBO o2) {
                    Integer o1First = o1.getApplicationName().codePointAt(0);
                    Integer o2First = o2.getApplicationName().codePointAt(0);
                    return o1First.compareTo(o2First);
                }
            });

            resultMap.put("appSum", appSum);
            resultMap.put("groupSum", groupSet.size());
            resultMap.put("appList", appList);
            resultMap.put("allApp", allApplicationsMap);
            return ResultVO.wrapSuccessfulResult(resultMap);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVO.wrapErrorResult(e.getMessage());
        }

    }

    //获得target消耗source在时间内频次
    @RequestMapping(value = "/getSuccessByConsumer", method = RequestMethod.GET)
    public
    @ResponseBody
    ResultVO getSuccessByConsumer(String type, String source) {
        Map<String, Object> resultMap = new HashMap<>();

        List<String> recentDateList = getRecentDay(type);
        ApplicationBO applicationBO = applicationService.getApplicationsBOMap().get(source);
        Set<String> providerSet = applicationBO.getProvidersSet();
        Set<String> consumerSet = applicationBO.getConsumersSet();

        if (providerSet == null) providerSet = new HashSet<>();
        if (consumerSet == null) consumerSet = new HashSet<>();

        Map<String, Integer> providerMap = new HashMap<>();
        Map<String, Integer> consumerMap = new HashMap<>();
        // 即是消费者 又是提供者
        Map<String, Map<String, Integer>> providerConsumerMap = new HashMap<>();
        resultMap.put(Constants.PROVIDER, providerMap);
        resultMap.put(Constants.CONSUMER, consumerMap);
        resultMap.put("providerConsumer", providerConsumerMap);

        // 已appStyle 为 consumer 为主 统计
        for (String date : recentDateList) {
            Map<String, Map<String, Integer>> appDayMap = invokeReportManager.getAppRelationByAppOnDay(source, date);
            if (null != appDayMap && !appDayMap.isEmpty()) {

                for (Map.Entry<String, Map<String, Integer>> appDayEntry : appDayMap.entrySet()) {
                    String appType = appDayEntry.getKey();
                    Map<String, Integer> dayMap = appDayEntry.getValue();

                    Map<String, Integer> resultTypeMap = (Map<String, Integer>) resultMap.get(appType);
                    for (Map.Entry<String, Integer> dayEntry : dayMap.entrySet()) {
                        String appName = dayEntry.getKey();
                        Integer sum = dayEntry.getValue();

                        Integer oldSum = resultTypeMap.get(appName);
                        if (oldSum == null) {
                            oldSum = 0;
                        }
                        sum += oldSum;
                        resultTypeMap.put(appName, sum);
                    }
                }

            }
        }

        // 生成二者共有的，并补充无记录数的数据
        Set<String> consumerKeySet = consumerMap.keySet();
        Set<String> providerKeySet = providerMap.keySet();
        Integer sum = 0;

        for (String providerName : providerSet) {
            if (consumerSet.contains(providerName)) {
                Integer providerSum = providerMap.get(providerName) == null ? 0 : providerMap.get(providerName);
//                Integer consumerSum = consumerMap.get(providerName);
                Map<String, Integer> map = new HashMap<>();
                map.put(Constants.PROVIDER, providerSum);
                providerConsumerMap.put(providerName, map);
                providerMap.remove(providerName);
            } else {
                if (!providerKeySet.contains(providerName)) {
                    providerMap.put(providerName, sum);
                }
            }
        }

        for (String consumerName : consumerSet) {
            if (providerSet.contains(consumerName)) {
                Integer consumerSum = consumerMap.get(consumerName) == null ? 0 : consumerMap.get(consumerName);
                providerConsumerMap.get(consumerName).put(Constants.CONSUMER, consumerSum);
                consumerMap.remove(consumerName);
            } else {
                if (!consumerKeySet.contains(consumerName)) {
                    consumerMap.put(consumerName, sum);
                }
            }
        }
        return ResultVO.wrapSuccessfulResult(resultMap);
    }

    // 按时间
    @RequestMapping(value = "/getSuccessByConsumerOnHour", method = RequestMethod.GET)
    public
    @ResponseBody
    ResultVO getSuccessByConsumerOnHour(String type, String source) {
        //{consumers : time : success/fail}
        Map<String, Object> resultMap = new HashMap<>();


        ApplicationBO applicationBO = applicationService.getApplicationsBOMap().get(source);
        Set<String> consumerSet = applicationBO.getConsumersSet();

        if (consumerSet == null) consumerSet = new HashSet<>();

        List<String> recentDateList = getRecentDay(type);

        for (String date : recentDateList) {
            Map<String, Object> map = (Map<String, Object>) invokeReportManager.getConsumerByAppOnHour(source, date);
            if (null != map && !map.isEmpty()) {
                for (Map.Entry<String, Object> consumerEntry : map.entrySet()) {
                    String consumerName = consumerEntry.getKey();
                    Map<String, Object> timeMap = (Map<String, Object>) consumerEntry.getValue();

                    Map<String, Object> resultTimeMap = (Map<String, Object>) resultMap.get(consumerName);
                    if (resultTimeMap == null) {
                        resultTimeMap = new HashMap<>();
                        resultMap.put(consumerName, resultTimeMap);
                    }
                    for (Map.Entry<String, Object> timeEntry : timeMap.entrySet()) {
                        String time = timeEntry.getKey();
                        Map<String, Integer> sumMap = (Map<String, Integer>) timeEntry.getValue();

                        Map<String, Integer> resultSumMap = (Map<String, Integer>) resultTimeMap.get(time);
                        if (resultSumMap == null) {
                            resultSumMap = new HashMap<>();
                            resultTimeMap.put(time, resultSumMap);
                        }
                        Integer resultSuccessNum = resultSumMap.get(MonitorConstants.SUCCESS) == null ? 0 : resultSumMap.get(MonitorConstants.SUCCESS);
                        Integer resultFailNum = resultSumMap.get(MonitorConstants.FAIL) == null ? 0 : resultSumMap.get(MonitorConstants.FAIL);

                        resultSuccessNum += sumMap.get(MonitorConstants.SUCCESS);
                        resultFailNum += sumMap.get(MonitorConstants.FAIL);
                        resultSumMap.put(MonitorConstants.SUCCESS, resultSuccessNum);
                        resultSumMap.put(MonitorConstants.FAIL, resultFailNum);
                    }
                }
            }
        }

        //补充为0的数据
        for (String consumerName : consumerSet) {
            Map<String, Object> hourSumMap = (Map<String, Object>) resultMap.get(consumerName);
            if (null == hourSumMap) {
                hourSumMap = new HashMap<>();
                resultMap.put(consumerName, hourSumMap);
            }
        }

        return ResultVO.wrapSuccessfulResult(resultMap);

    }

    // 按日期:15天
    @RequestMapping(value = "/getSuccessByConsumerOnDay", method = RequestMethod.GET)
    public
    @ResponseBody
    ResultVO getSuccessByConsumerOnDay(String type, String source) {
        //{consumers：day: success/fail}
        //recentDateList
        Map<String, Object> finalMap = new HashMap<>();
        Map<String, Object> resultDataMap = new HashMap<>();

        finalMap.put("dataMap",resultDataMap);



        ApplicationBO applicationBO = applicationService.getApplicationsBOMap().get(source);
        Set<String> consumerSet = applicationBO.getConsumersSet();

        if (consumerSet == null) consumerSet = new HashSet<>();

        List<String> recentDateList = getRecentDay(type);

        finalMap.put("dateList",recentDateList);
        for (String date : recentDateList) {
            Map<String, Map<String, Integer>> map = invokeReportManager.getConsumerByAppOnDay(source, date);
            for (Map.Entry<String, Map<String, Integer>> consumerEntry : map.entrySet()) {
                String consumerName = consumerEntry.getKey();
                Map<String, Integer> sumMap = consumerEntry.getValue();

                Map<String, Object> resultTimeMap = (Map<String, Object>) resultDataMap.get(consumerName);
                if (resultTimeMap == null) {
                    resultTimeMap = new HashMap<>();
                    resultDataMap.put(consumerName, resultTimeMap);
                }
                resultTimeMap.put(date,sumMap);
            }
        }

        //补充为0的数据
        for (String consumerName : consumerSet) {
            Map<String, Object> resultTimeMap = (Map<String, Object>) resultDataMap.get(consumerName);
            if (null == resultTimeMap) {
                resultTimeMap = new HashMap<>();
                resultDataMap.put(consumerName, resultTimeMap);
            }
        }

        return ResultVO.wrapSuccessfulResult(finalMap);

    }


    // 获得
    @RequestMapping(value = "/getMethodRanking", method = RequestMethod.GET)
    public
    @ResponseBody
    ResultVO getMethodRanking(String appName) {
        List<MethodRankBO> list = invokeBiz.getMethodRankByAppName(appName);

        return ResultVO.wrapSuccessfulResult(list);
    }

    //=======private======
    private List<String> getRecentDay(String type) {
        Integer limit = 0;
        Date date = new Date(System.currentTimeMillis());

        List<String> recentDateList = new ArrayList<>();
        if (type.equals("Today")) {
            String nowDate = TimeUtil.getDateString(date);
            recentDateList.add(nowDate);
        }  else {
            switch (type) {
                case "Seven_DAY":
                    limit = 7;
                    break;
                case "Fifteen_DAT":
                    limit = 15;
                    break;
                case "Yesterday":
                    limit = 1;
                    break;
            }
            for (Integer amount = -limit; amount < 0; amount++) {
                recentDateList.add(TimeUtil.getBeforDateByNumber(date, amount));
            }
        }
        return recentDateList;
    }
}

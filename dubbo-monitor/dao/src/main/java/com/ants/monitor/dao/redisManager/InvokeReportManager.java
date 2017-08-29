package com.ants.monitor.dao.redisManager;

import java.util.Map;

/**
 * Created by zxg on 16/1/28.
 * 13:19
 *  报表数据redis
 */
public interface InvokeReportManager {

    /**
     * 保存15天
     * 根据来源app名称记录app的每日跟其他app的调用情况，最小粒度：日
     * @param sourceApp 来源app
     * @param dayTime 日期
     * @param reportMap {provider:{appName:sumNumber}}
     */
    void saveAppRelationByAppOnDay(String sourceApp, String dayTime, Map<String, Map<String, Integer>> reportMap);

    Map<String,Map<String,Integer>> getAppRelationByAppOnDay(String sourceApp, String dayTime);


    /**
     * 保存2天
     * 消费来源app的每日调用情况，最小粒度：小时
     * @param sourceApp 来源app
     * @param dayTime 日期
     * @param reportMap {appName:{time:{success：sumNumber}}}
     */
    void saveConsumerByAppOnHour(String sourceApp, String dayTime, Map<String, Map<String, ?>> reportMap);

    Map<String, ?> getConsumerByAppOnHour(String sourceApp, String dayTime);

    /**
     * 保存15天
     * 消费来源app的每日调用情况，最小粒度：天
     * @param sourceApp 来源app
     * @param dayTime 日期
     * @param reportMap {appName:{success：sumNumber}}
     */
    void saveConsumerByAppOnDay(String sourceApp, String dayTime, Map<String, Map<String, Integer>> reportMap);

    Map<String,Map<String,Integer>> getConsumerByAppOnDay(String sourceApp, String dayTime);

}

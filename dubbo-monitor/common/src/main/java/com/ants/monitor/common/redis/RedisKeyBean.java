package com.ants.monitor.common.redis;

/**
 * Created by zxg on 15/9/10.
 * redis key 的管理
 */

public interface RedisKeyBean {
    /*===========变量自动为 final static*/
    //redis变量key的系统前缀
    String SYSTEM_PREFIX = "ants_monitor_";

    String NULL_OBJECT = "None";
    /*=====================自定义的各种key=========================================================================*/

    /*===固定大小的key=======*/
    /**list key**/
    String recentInsertKey = SYSTEM_PREFIX+"recent_app_change_insert_list";
    String recentDeleteKey = SYSTEM_PREFIX+"recent_app_change_delete_list";
    /**app 的主要变更 纪录 用作对比比较**/
    String appChangeCacheKey = SYSTEM_PREFIX+"app_change_key";

    //app停止后的记录的map{host-app:time+次数}
    String appStopMapKey = SYSTEM_PREFIX+"app_stop_map";

    /**APP:PHONE**/
    String appPhoneMapKey = SYSTEM_PREFIX+"app_phone_map";

    /*========有效期的key==============*/
    // 该日期的invoke对象，2小时
    String invokeListHour = SYSTEM_PREFIX+"invoke_list_hour_%s";

    //查到method日期的数据 service_method_day，1个小时
    String invokeMethodDayKey = SYSTEM_PREFIX+"invoke_method_%s_%s_%s";

    //该应用下的方法排行榜数据 appName,1h
    String invokeMethodRankKey = SYSTEM_PREFIX+"app_method_rank_%s";

    //按日期存储详细变化,一周
    String dayChangeKey = SYSTEM_PREFIX+"month_app_change_list_%s";
    //存这月多少日期有变化，一周
    String monthDayKey = SYSTEM_PREFIX+"month_day_app_change_%s";


    //按日统计app的交互数,15天
    String appInvokeSumOnDayKEY = SYSTEM_PREFIX+"%s_app_sum_on_day_%s";
    //按小时统计app消费者的每小时消费情况，2天
    String appConsumerSumOnHourKEY = SYSTEM_PREFIX+"%s_app_consumer_on_hour_%s";
    //按日统计app消费者的每日情况，15天
    String appConsumerSumOnDayKEY = SYSTEM_PREFIX+"%s_app_consumer_on_day_%s";


    //application 名单,一小时
    String APP_LIST_KEY = SYSTEM_PREFIX+"app_list";
    /*===not used=====*/
    /**每日用户的ip：name**/
    String userIpNameKey = SYSTEM_PREFIX+"user_ip_name_map_%s";
    String userIpNameFieldKey = SYSTEM_PREFIX+"field_%s";

    /*=============失效时间=======================================================================*/
    /**
     * 缓存时效 1分钟
     */
    int RREDIS_EXP_MINUTE = 60;

    /**
     * 缓存时效 10分钟
     */
    int RREDIS_EXP_MINUTES = 60 * 10;

    /**
     * 缓存时效 60分钟
     */
    int RREDIS_EXP_HOURS = 60 * 60;

    /**
     * 缓存时效 半天
     */
    int RREDIS_EXP_HALF_DAY = 3600 * 12;
    /**
     * 缓存时效 1天
     */
    int RREDIS_EXP_DAY = 3600 * 24;

    /**
     * 缓存时效 1周
     */
    int RREDIS_EXP_WEEK = 3600 * 24 * 7;

    /**
     * 缓存时效 1月
     */
    int RREDIS_EXP_MONTH = 3600 * 24 * 30 * 7;
}

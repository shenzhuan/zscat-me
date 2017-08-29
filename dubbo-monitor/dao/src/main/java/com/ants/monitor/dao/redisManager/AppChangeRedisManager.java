package com.ants.monitor.dao.redisManager;

import com.ants.monitor.bean.bizBean.ApplicationChangeBO;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by zxg on 15/12/9.
 * 应用服务发生变化后的处理
 */
public interface AppChangeRedisManager {

    /**获得最近新增30条**/
    List<ApplicationChangeBO> getRecentInsertList();

    /**获得最近减少30条**/
    List<ApplicationChangeBO> getRecentDeleteList();

    /**按日期获得数据**/
    List<ApplicationChangeBO> getChangeListByDay(String day, Integer pageIndex, Integer limit);

    Integer getListSum(String day);

    //按月份获得实际日期
    Set<String> getDaySet(String month);

    //获得redis存储的上次所有的app对象
    Map<String,List<Map<String,String>>> getChangeAppCache();


    /**================redis 存储＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝**/

    void saveChangeAppCache(Map<String, Set<ApplicationChangeBO>> map);

    /*保存最近删除的和本日删除的记录*/
    void addDeleteRecentRecord(ApplicationChangeBO applicationChangeBO);

    /*保存最近增加的和本日增加的记录*/
    void addInsertRecentRecord(ApplicationChangeBO applicationChangeBO);

}

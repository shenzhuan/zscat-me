package com.ants.monitor.biz.support.service;

import com.ants.monitor.bean.bizBean.ApplicationChangeBO;

import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * Created by zxg on 16/1/5.
 * app新增减少历史
 * 14:38
 */
public interface AppChangeService {
    /*===========================app历史的基础查询======================*/
    /*获得最近新增30条*/
    List<ApplicationChangeBO> getRecentInsertList();

    /*获得最近减少30条*/
    List<ApplicationChangeBO> getRecentDeleteList();

    /*按日期获得数据*/
    List<ApplicationChangeBO> getChangeListByDay(String day, Integer pageIndex, Integer limit);

    /*按日期获得数量*/
    Integer getListSum(String day);

    /*按月份获得实际日期*/
    Set<String> getDaySet(String month);


    /*===========================app新增或停止触发的操作======================*/
    /*服务停止后的操作*/
    void afterChangeDeleteDo(ApplicationChangeBO applicationChangeBO);

    /*服务增加后的操作*/
    void afterChangeInsertDo(ApplicationChangeBO applicationChangeBO);

    /*===========================app历史的保存和取出======================*/
    /*获得redis中保存的当前app列表*/
    Map<String,Set<ApplicationChangeBO>> getChangeAppCache();

    /*保存当前app列表*/
    void saveChangeAppCache(Map<String, Set<ApplicationChangeBO>> map);
}

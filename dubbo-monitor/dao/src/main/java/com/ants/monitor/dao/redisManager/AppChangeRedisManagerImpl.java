package com.ants.monitor.dao.redisManager;

import com.ants.monitor.bean.bizBean.ApplicationChangeBO;
import com.ants.monitor.common.redis.RedisClientTemplate;
import com.ants.monitor.common.redis.RedisKeyBean;
import com.ants.monitor.common.tools.JsonUtil;
import com.ants.monitor.common.tools.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zxg on 15/12/9.
 * 应用服务发生变化后的处理
 */
@Service
public class AppChangeRedisManagerImpl implements AppChangeRedisManager {
    private static Boolean recentDeleteNumOk = false;
    private static Boolean recentInsertNumOk = false;
    //保存过的日期
    private static List<String> haveDayList = new ArrayList<>();

    //最近常用保持30条记录
    private final static Integer recentNum = 10;

    @Autowired
    private RedisClientTemplate redisClientTemplate;

    /**获得最近新增30条**/
    public List<ApplicationChangeBO> getRecentInsertList(){
        List<ApplicationChangeBO> resultList = new ArrayList<>();
        List<String> list = redisClientTemplate.getList( RedisKeyBean.recentInsertKey,0,-1);
        for(String recentString : list){
            ApplicationChangeBO applicationChangeBO = JsonUtil.jsonStrToObject(recentString, ApplicationChangeBO.class);
            resultList.add(applicationChangeBO);
        }
        return resultList;
    }

    /**获得最近减少30条**/
    public List<ApplicationChangeBO> getRecentDeleteList(){
        List<ApplicationChangeBO> resultList = new ArrayList<>();
        List<String> list = redisClientTemplate.getList( RedisKeyBean.recentDeleteKey,0,-1);
        for(String recentString : list){
            ApplicationChangeBO applicationChangeBO = JsonUtil.jsonStrToObject(recentString, ApplicationChangeBO.class);
            resultList.add(applicationChangeBO);
        }
        return resultList;
    }


    /**按日期获得数据**/
    public List<ApplicationChangeBO> getChangeListByDay(String day,Integer pageIndex,Integer limit){
        List<ApplicationChangeBO> resultList = new ArrayList<>();
        String thisDayKey = String.format(RedisKeyBean.dayChangeKey, day);

        Integer start = (pageIndex-1)*limit;
        Integer end = start + limit -1;
        List<String> list = redisClientTemplate.getList(thisDayKey, start, end);
        for(String recentString : list){
            ApplicationChangeBO applicationChangeBO = JsonUtil.jsonStrToObject(recentString, ApplicationChangeBO.class);
            resultList.add(applicationChangeBO);
        }
        return resultList;
    }

    public Integer getListSum(String day){
        String thisDayKey = String.format(RedisKeyBean.dayChangeKey, day);

        return redisClientTemplate.listSize(thisDayKey);
    }
    //按月份获得实际日期
    public Set<String> getDaySet(String month){
        String monthKey = String.format(RedisKeyBean.monthDayKey, month);
        return redisClientTemplate.getSet(monthKey);
    }

    public Map<String,List<Map<String,String>>> getChangeAppCache(){
        String key = RedisKeyBean.appChangeCacheKey;

        return redisClientTemplate.lazyGet(key, ConcurrentHashMap.class);
    }


    /**================redis 存储＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝**/
    public void saveChangeAppCache(Map<String, Set<ApplicationChangeBO>> map){
        String key = RedisKeyBean.appChangeCacheKey;
        redisClientTemplate.lazySet(key,map,null);
    }

    /*保存最近删除的和本日删除的记录*/
    public void addDeleteRecentRecord(ApplicationChangeBO applicationChangeBO){
        Date now = new Date();
        String thisDay = TimeUtil.getDateString(now);
        String deleteString = JsonUtil.objectToJsonStr(applicationChangeBO);

        //最近常用删除位
        String recentDeleteKey = RedisKeyBean.recentDeleteKey;
        redisClientTemplate.lPushList(recentDeleteKey, deleteString);


        Integer deleteSize = redisClientTemplate.listSize(recentDeleteKey);
        Integer diff = 0;
        if(deleteSize >= recentNum){
            this.recentDeleteNumOk = true;
            diff = deleteSize - recentNum;
        }
        if (recentDeleteNumOk){
            for(int i = 0; i<diff;i++) {
                redisClientTemplate.rPopList(recentDeleteKey);
            }
        }
        //本日删除位记录
        String thisDayKey = String.format(RedisKeyBean.dayChangeKey,thisDay);
        redisClientTemplate.lPushList(thisDayKey, deleteString, RedisKeyBean.RREDIS_EXP_WEEK);

        if(!haveDayList.contains(thisDay)){
            String thisMonth = TimeUtil.getYearMonthString(now);
            String monthKey = String.format(RedisKeyBean.monthDayKey,thisMonth);
            redisClientTemplate.addSet(monthKey,thisDay, RedisKeyBean.RREDIS_EXP_WEEK);
            haveDayList.add(thisDay);
        }
    }

    /*保存最近增加的和本日增加的记录*/
    public void addInsertRecentRecord(ApplicationChangeBO applicationChangeBO){
        Date now = new Date();
        String thisDay = TimeUtil.getDateString(now);
        String insertString = JsonUtil.objectToJsonStr(applicationChangeBO);

        //最近常用insert位
        String recentInsertKey = RedisKeyBean.recentInsertKey;
        redisClientTemplate.lPushList(recentInsertKey, insertString);


        Integer insertSize = redisClientTemplate.listSize(recentInsertKey);
        Integer diff = 0;
        if(insertSize >= recentNum){
            this.recentInsertNumOk = true;
            diff = insertSize - recentNum;
        }
        if (recentInsertNumOk){
            for(int i = 0; i<diff;i++) {
                redisClientTemplate.rPopList(recentInsertKey);
            }
        }


        //本月insert位记录
        String thisDayKey = String.format(RedisKeyBean.dayChangeKey,thisDay);
        redisClientTemplate.lPushList(thisDayKey, insertString, RedisKeyBean.RREDIS_EXP_WEEK);
        if(!haveDayList.contains(thisDay)){
            String thisMonth = TimeUtil.getYearMonthString(now);
            String monthKey = String.format(RedisKeyBean.monthDayKey,thisMonth);
            redisClientTemplate.addSet(monthKey,thisDay, RedisKeyBean.RREDIS_EXP_WEEK);
            haveDayList.add(thisDay);
        }
    }


}

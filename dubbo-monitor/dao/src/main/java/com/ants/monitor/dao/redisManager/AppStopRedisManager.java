package com.ants.monitor.dao.redisManager;

import com.ants.monitor.bean.bizBean.ApplicationChangeBO;

import java.util.Map;

/**
 * Created by zxg on 16/1/8.
 * 18:05
 * 内存中的记录 服务停止
 */
public interface AppStopRedisManager {
    //获得所有的数据
    Map<ApplicationChangeBO,String> getAllStopApp();

    //保存停止服务（删除）的当前记录于当前内存中
    void saveStopApp(ApplicationChangeBO applicationChangeBO, Integer number);

    //移除已经启动的服务于停止列表
    void removeStopApp(ApplicationChangeBO applicationChangeBO);

}

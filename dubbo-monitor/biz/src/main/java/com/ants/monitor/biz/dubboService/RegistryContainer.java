package com.ants.monitor.biz.dubboService;

import com.alibaba.dubbo.common.URL;

import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * RegistryContainer
 * 
 * @author zxg 2015-11-03
 */
public interface RegistryContainer {

    Map<String, Map<String, Set<URL>>> getRegistryCache();

    // 获得当前更新时间
    Date getFinalUpdateTime();

    // 初始化启动函数
    void start() ;

    // 重启函数()
    void restart();

    void stop();


    //初始化changeApp--redis取出，比较
    void initRedisChangeAppCaChe();
}
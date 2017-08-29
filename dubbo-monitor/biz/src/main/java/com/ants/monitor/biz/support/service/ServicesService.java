package com.ants.monitor.biz.support.service;

import com.ants.monitor.bean.bizBean.ServiceBO;

import java.util.Map;
import java.util.Set;

/**
 * Created by zxg on 15/11/11.
 * 11:15
 */
public interface ServicesService {

    // 所有service
    Set<String> getAllServicesString();

    // service对应的所有provider--app
    Map<String, Set<String>> getServiceProviders();

    //service对应的所有消费者 --app
    Map<String, Set<String>> getServiceConsumers();


    // service名称，service对象
    Map<String,ServiceBO> getServiceBOMap();
}

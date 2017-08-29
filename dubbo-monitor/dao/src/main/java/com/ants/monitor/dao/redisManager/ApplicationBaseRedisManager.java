package com.ants.monitor.dao.redisManager;

import com.ants.monitor.common.redis.RedisClientTemplate;
import com.ants.monitor.common.redis.RedisKeyBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by zxg on 16/1/8.
 * 19:23
 * 存应用对应的负责人
 */
@Component
public class ApplicationBaseRedisManager {


    @Autowired
    private RedisClientTemplate redisClientTemplate;

    public void saveApplicationPhone(String appName,String phone){
        String key = RedisKeyBean.appPhoneMapKey;
        String field = appName;
        redisClientTemplate.setMapKey(key,field,phone);
    }

    public String getPhoneByAppName(String appName){
        String key = RedisKeyBean.appPhoneMapKey;
        return redisClientTemplate.getMapKey(key,appName);
    }

    public Map<String,String> getAllAppPhone(){
        return redisClientTemplate.getAllHash(RedisKeyBean.appPhoneMapKey);
    }
}

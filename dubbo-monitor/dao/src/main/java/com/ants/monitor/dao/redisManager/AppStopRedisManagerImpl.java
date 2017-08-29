package com.ants.monitor.dao.redisManager;

import com.ants.monitor.bean.bizBean.ApplicationChangeBO;
import com.ants.monitor.common.redis.RedisClientTemplate;
import com.ants.monitor.common.redis.RedisKeyBean;
import com.ants.monitor.common.tools.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zxg on 16/1/11.
 * 20:09
 */
@Component
public class AppStopRedisManagerImpl implements AppStopRedisManager {
    @Autowired
    private RedisClientTemplate redisClientTemplate;


    @Override
    public Map<ApplicationChangeBO, String> getAllStopApp() {
        Map<ApplicationChangeBO, String> resultMap = new HashMap<>();
        for(Map.Entry<String,String> entry : redisClientTemplate.getAllHash(RedisKeyBean.appStopMapKey).entrySet()){
            String key = entry.getKey();
            ApplicationChangeBO applicationChangeBO = JsonUtil.jsonStrToObject(key, ApplicationChangeBO.class);
            resultMap.put(applicationChangeBO,entry.getValue());
        }
        return resultMap;
    }

    @Override
    public void saveStopApp(ApplicationChangeBO applicationChangeBO,Integer number) {
        if(null == number){
            number = 0;
        }
        ApplicationChangeBO filedBO = new ApplicationChangeBO();
        filedBO.setAppName(applicationChangeBO.getAppName());
        filedBO.setHost(applicationChangeBO.getHost());
        filedBO.setPort(applicationChangeBO.getPort());


        String filed = JsonUtil.objectToJsonStr(filedBO);
        String value = applicationChangeBO.getTime()+","+number;
        redisClientTemplate.setMapKey(RedisKeyBean.appStopMapKey, filed,value);
    }

    @Override
    public void removeStopApp(ApplicationChangeBO applicationChangeBO) {
        ApplicationChangeBO filedBO = new ApplicationChangeBO();
        filedBO.setAppName(applicationChangeBO.getAppName());
        filedBO.setHost(applicationChangeBO.getHost());
        filedBO.setPort(applicationChangeBO.getPort());

        String filed = JsonUtil.objectToJsonStr(filedBO);
        redisClientTemplate.delMapKey(RedisKeyBean.appStopMapKey,filed);
    }
}

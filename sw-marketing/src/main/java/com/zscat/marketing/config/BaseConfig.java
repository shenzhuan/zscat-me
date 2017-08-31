package com.zscat.marketing.config;


import com.zsCat.common.base.RedisLink;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author : zscat
 * @version : 1.0
 * @created on  : 2017/6/28  下午6:00
 */
@Configuration
public class BaseConfig {
    @Resource
    RedisConfigProperties redisConfigProperties;

    @Resource
    WxConfigProperties wxConfigProperties;

    /**
     * redis库
     *
     * @return
     */
    @Bean
    public RedisLink redisLink() {
        RedisLink redisLink = new RedisLink(redisConfigProperties.getHost(), redisConfigProperties.getPort(),
                redisConfigProperties.getPasswd());
        return redisLink;
    }


    @Bean
    public WxMpConfigStorage wxMpConfigStorage() {
        WxMpInMemoryConfigStorage configStorage = new WxMpInMemoryConfigStorage();
        configStorage.setAppId(wxConfigProperties.getAppid());
        configStorage.setSecret(wxConfigProperties.getAppsecret());
        configStorage.setToken(wxConfigProperties.getToken());
        configStorage.setAesKey(wxConfigProperties.getAeskey());
//        configStorage.setTmpDirFile(new File(wxConfigProperties.getApp().getProperty("tempPath")));
        return configStorage;
    }

    @Bean
    public WxMpService wxMpService() {
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxMpConfigStorage());
        return wxMpService;
    }
}

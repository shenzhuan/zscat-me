package com.mallplus.order.pay.config;


import com.mallplus.core.kit.HttpKit;
import com.mallplus.order.pay.custom.OkHttpKit;
import com.mallplus.unionpay.SDKConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;

@Order(1)
public class StartupRunner implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(StartupRunner.class);

    @Override
    public void run(String... args) throws Exception {
        logger.info("startup runner");
        // 银联加载配置
        SDKConfig.getConfig().loadPropertiesFromSrc();// 从classpath加载acp_sdk.properties文件
        // 自定义 Http 客户端
        HttpKit.setDelegate(new OkHttpKit());
    }

}

package com.mallplus.jdpay;

import cn.hutool.core.util.StrUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>IJPay 让支付触手可及，封装了微信支付、支付宝支付、京东支付、银联支付常用的支付方式以及各种常用的接口。</p>
 *
 * <p>不依赖任何第三方 mvc 框架，仅仅作为工具使用简单快速完成支付模块的开发，可轻松嵌入到任何系统里。 </p>
 *
 * <p>IJPay 交流群: 723992875</p>
 *
 * <p>Node.js 版: https://gitee.com/javen205/TNW</p>
 *
 * <p>京东支付常用配置 Kit</p>
 *
 * @author Javen
 */
public class JdPayApiConfigKit {

    private static final ThreadLocal<String> TL = new ThreadLocal<String>();

    private static final Map<String, JdPayApiConfig> CFG_MAP = new ConcurrentHashMap<String, JdPayApiConfig>();
    private static final String DEFAULT_CFG_KEY = "_default_key_";

    /**
     * 添加微信支付配置，每个appId只需添加一次，相同appId将被覆盖
     *
     * @param jdPayApiConfig 微信支付配置
     * @return {WxPayApiConfig} 微信支付配置
     */
    public static JdPayApiConfig putApiConfig(JdPayApiConfig jdPayApiConfig) {
        if (CFG_MAP.size() == 0) {
            CFG_MAP.put(DEFAULT_CFG_KEY, jdPayApiConfig);
        }
        return CFG_MAP.put(jdPayApiConfig.getAppId(), jdPayApiConfig);
    }

    public static JdPayApiConfig setThreadLocalJdPayApiConfig(JdPayApiConfig jdPayApiConfig) {
        if (StrUtil.isNotEmpty(jdPayApiConfig.getAppId())) {
            setThreadLocalAppId(jdPayApiConfig.getAppId());
        }
        return putApiConfig(jdPayApiConfig);
    }

    public static JdPayApiConfig removeApiConfig(JdPayApiConfig jdPayApiConfig) {
        return removeApiConfig(jdPayApiConfig.getAppId());
    }

    public static JdPayApiConfig removeApiConfig(String appId) {
        return CFG_MAP.remove(appId);
    }

    public static void setThreadLocalAppId(String appId) {
        if (StrUtil.isEmpty(appId)) {
            appId = CFG_MAP.get(DEFAULT_CFG_KEY).getAppId();
        }
        TL.set(appId);
    }

    public static void removeThreadLocalAppId() {
        TL.remove();
    }

    public static String getAppId() {
        String appId = TL.get();
        if (StrUtil.isEmpty(appId)) {
            appId = CFG_MAP.get(DEFAULT_CFG_KEY).getAppId();
        }
        return appId;
    }

    public static JdPayApiConfig getJdPayApiConfig() {
        String appId = getAppId();
        return getApiConfig(appId);
    }

    public static JdPayApiConfig getApiConfig(String appId) {
        JdPayApiConfig cfg = CFG_MAP.get(appId);
        if (cfg == null) {
            throw new IllegalStateException("需事先调用 JdPayApiConfigKit.putApiConfig(jdPayApiConfig) 将 appId 对应的 jdPayApiConfig 对象存入，才可以使用 JdPayApiConfigKit.getJdPayApiConfig() 的系列方法");
        }
        return cfg;
    }
}

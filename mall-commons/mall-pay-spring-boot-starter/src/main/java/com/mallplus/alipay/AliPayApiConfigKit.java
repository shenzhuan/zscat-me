package com.mallplus.alipay;

import cn.hutool.core.util.StrUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>IJPay 让支付触手可及，封装了微信支付、支付宝支付、银联支付常用的支付方式以及各种常用的接口。</p>
 *
 * <p>不依赖任何第三方 mvc 框架，仅仅作为工具使用简单快速完成支付模块的开发，可轻松嵌入到任何系统里。 </p>
 *
 * <p>IJPay 交流群: 723992875</p>
 *
 * <p>Node.js 版: https://gitee.com/javen205/TNW</p>
 *
 * @author Javen
 */
public class AliPayApiConfigKit {
    private static final ThreadLocal<String> TL = new ThreadLocal<String>();

    private static final Map<String, AliPayApiConfig> CFG_MAP = new ConcurrentHashMap<String, AliPayApiConfig>();
    private static final String DEFAULT_CFG_KEY = "_default_key_";

    /**
     * <p>向缓存中设置 AliPayApiConfig </p>
     * <p>每个 appId 只需添加一次，相同 appId 将被覆盖</p>
     *
     * @param aliPayApiConfig 支付宝支付配置
     * @return {@link AliPayApiConfig}
     */
    public static AliPayApiConfig putApiConfig(AliPayApiConfig aliPayApiConfig) {
        if (CFG_MAP.size() == 0) {
            CFG_MAP.put(DEFAULT_CFG_KEY, aliPayApiConfig);
        }
        return CFG_MAP.put(aliPayApiConfig.getAppId(), aliPayApiConfig);
    }

    /**
     * 向当前线程中设置 {@link AliPayApiConfig}
     *
     * @param aliPayApiConfig {@link AliPayApiConfig} 支付宝配置对象
     * @return {@link AliPayApiConfig}
     */
    public static AliPayApiConfig setThreadLocalAliPayApiConfig(AliPayApiConfig aliPayApiConfig) {
        if (StrUtil.isNotEmpty(aliPayApiConfig.getAppId())) {
            setThreadLocalAppId(aliPayApiConfig.getAppId());
        }
        return putApiConfig(aliPayApiConfig);
    }

    /**
     * 通过 AliPayApiConfig 移除支付配置
     *
     * @param aliPayApiConfig {@link AliPayApiConfig} 支付宝配置对象
     * @return {@link AliPayApiConfig}
     */
    public static AliPayApiConfig removeApiConfig(AliPayApiConfig aliPayApiConfig) {
        return removeApiConfig(aliPayApiConfig.getAppId());
    }

    /**
     * 通过 appId 移除支付配置
     *
     * @param appId 支付宝应用编号
     * @return {@link AliPayApiConfig}
     */
    public static AliPayApiConfig removeApiConfig(String appId) {
        return CFG_MAP.remove(appId);
    }

    /**
     * 向当前线程中设置 appId
     *
     * @param appId 支付宝应用编号
     */
    public static void setThreadLocalAppId(String appId) {
        if (StrUtil.isEmpty(appId)) {
            appId = CFG_MAP.get(DEFAULT_CFG_KEY).getAppId();
        }
        TL.set(appId);
    }

    /**
     * 移除当前线程中的 appId
     */
    public static void removeThreadLocalAppId() {
        TL.remove();
    }

    /**
     * 获取当前线程中的  appId
     *
     * @return 支付宝应用编号 appId
     */
    public static String getAppId() {
        String appId = TL.get();
        if (StrUtil.isEmpty(appId)) {
            appId = CFG_MAP.get(DEFAULT_CFG_KEY).getAppId();
        }
        return appId;
    }

    /**
     * 获取当前线程中的 AliPayApiConfig
     *
     * @return {@link AliPayApiConfig}
     */
    public static AliPayApiConfig getAliPayApiConfig() {
        // AliPayApiConfig aliPayApiConfig = null;
        AliPayBean aliPayBean = new AliPayBean();
        aliPayBean.setAppId("2017010804923732");
        aliPayBean.setDomain("http://www.yjlive.cn/api");
        aliPayBean.setServerUrl("https://openapi.alipay.com/gateway.do");
        aliPayBean.setPublicKey("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAzQsSWsgXJO7HTWLd/8Y9de6kPBlGqvnBCdL6N8pbg9TZ5LDQOpPxef940nY/dHQiBw61bVZQULSps2mhOs7xjebhEJfhXiGV+aZBjacxr+qJ4EpM/pjH3MrfA8IB5MpB9OFEeOTGos3FMzeQHPiqeeDAIDEFs4fO112/3OWfCD6rLI88H0FoDqZ4oSsAkniFZAW1IjwW9Whgicgo4v3IjcWV+k4eFCSCORpnNKjLbsco0qJic1FaHqbkccnpW8/40j/Vo/ZZG/qCDyZ/Lt7+OKDgO8dzelFfG/IoAuEMGsgb26tCAZMVyjMxXUgLrqnTESAx6121pqy1fiwyMC6cRwIDAQAB\n");
        aliPayBean.setPrivateKey("MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDNCxJayBck7sdNYt3/xj117qQ8GUaq+cEJ0vo3yluD1NnksNA6k/F5/3jSdj90dCIHDrVtVlBQtKmzaaE6zvGN5uEQl+FeIZX5pkGNpzGv6ongSkz+mMfcyt8DwgHkykH04UR45MaizcUzN5Ac+Kp54MAgMQWzh87XXb/c5Z8IPqssjzwfQWgOpnihKwCSeIVkBbUiPBb1aGCJyCji/ciNxZX6Th4UJII5Gmc0qMtuxyjSomJzUVoepuRxyelbz/jSP9Wj9lkb+oIPJn8u3v44oOA7x3N6UV8b8igC4QwayBvbq0IBkxXKMzFdSAuuqdMRIDHrXbWmrLV+LDIwLpxHAgMBAAECggEACYIM7NbAc/76kPUXtEeeC/zv8rV9WGpScEEvRy0EB130aK1mSoEXvn+BO8kt8hl8hnVBJnvNJ6DpCZ/JUS/NdbYSE7HnSnUmPjhea9In9K9ci2EGpvuwsOVbaBI0Akb6vf9ALJb3Ow9tqI1YCm/hf9tTLWr4h7Wxer0nK3geYsRn6O92AKFYjxvImR/qj9sr2DNHg83lX/2YkdDuxhLWF8oTZzunhqvEWo17mEcCrFpx/vY3bME+ZMG/IAtp89PFXXsNHii4nI0buR8mx4z6CgIetgL0qFJUeMUir1ZKn+uAyy9Jv1V9Bu7R07LXsjlZlA/xnew5ie/42iyGcusYiQKBgQDvRiPWCX0eEe4rVcPHAZJ1d/jsOGwvhzmE4TUQdnjVU0bKdgY4hHS9BrvjCTAWmzEE0siS85inzQj26DGGNr5U+D0HYfTEymNQNBMLin/ApLvNPL7xzFdpA19sVvatVV+c5Vl9JaVGuBraK69Q7Cz/6OQwXU1NGQWohZhOyMX6MwKBgQDbYF2BM7npaXFQVbigGmXgdvLHeWdZag7M4dB0lHsqGWAdtQzIIn05q9rBWNMusEfal/eZKuvmoXaDquh/g0VDCmmxxIE4OS9j37g64/4QbWJxwM8rDzA6Z58peng7CUse9Pb3Q/F8JQ/EvniEa6JT1qXWGWhQcpGsACCZEYYpnQKBgGQPAsFo6md+vAhnLx2zbJmu9+tglO0zMTx+KQCfalxbHMlhnaxYx7Ccdkm09+UcNN19f97j+zyAo3UNGFi139YMkQjbT85TjEBn5mb3HgFjYh2rf3YCK7OAc5EMtM87WmZ0Cn4pFfqC1sfRaNkASrkhnPsUqVTKV/FnHJAlqZS9AoGBAJgAKCmajolEzwerrXX5dHdX05YU72AL1V9uY0IzkzczR96tkMKm6v9nrPXktsaVy+ORAjS1gahWXciTRe78JKRz9ZH/ps0vCj/4Ri0/xczaDajlwGWEa5U8MRLLUb0ODmfPscLX591tzIQ0uUp/TYUrp9I13opHJ9n2aJ/GfaAdAoGARUzil6jIO3mASNaH7MPR4MqvxMO0LuBwaVxR1mvM7GtDDDYWU3fTJ6lFpyr340cYgEmHAVnLezbLmP75Jqo42j7H5V3BplPITSSik9ti3WOHFlPRYsZBewL7cJb4VX5oRrbfOX8to9wfw2TvofHE82NDtQn9fQUoFpqKlkIraL4=\n");
        AliPayApiConfig aliPayApiConfig = AliPayApiConfig.builder()
                .setAppId(aliPayBean.getAppId())
                .setAliPayPublicKey(aliPayBean.getPublicKey())
                .setCharset("UTF-8")
                .setPrivateKey(aliPayBean.getPrivateKey())
                .setServiceUrl(aliPayBean.getServerUrl())
                .setSignType("RSA2")
                .build();
        return aliPayApiConfig;
      /*  String appId = getAppId();
        return getApiConfig(appId);*/
    }

    /**
     * 通过 appId 获取 AliPayApiConfig
     *
     * @param appId 支付宝应用编号
     * @return {@link AliPayApiConfig}
     */
    public static AliPayApiConfig getApiConfig(String appId) {
        AliPayApiConfig cfg = CFG_MAP.get(appId);
        if (cfg == null) {
            throw new IllegalStateException("需事先调用 AliPayApiConfigKit.putApiConfig(aliPayApiConfig) 将 appId对应的 aliPayApiConfig 对象存入，才可以使用 AliPayApiConfigKit.getAliPayApiConfig() 的系列方法");
        }
        return cfg;
    }
}

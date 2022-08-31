package com.mallplus.order.pay.controller.alipay;


import com.mallplus.alipay.AliPayApiConfig;

/**
 * @author Javen
 */
public abstract class AbstractAliPayApiController {
    /**
     * 获取支付宝配置
     *
     * @return {@link AliPayApiConfig} 支付宝配置
     */
    public abstract AliPayApiConfig getApiConfig();
}

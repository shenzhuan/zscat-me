package com.mallplus.wxpay.enums;

/**
 * <p>IJPay 让支付触手可及，封装了微信支付、支付宝支付、银联支付常用的支付方式以及各种常用的接口。</p>
 *
 * <p>不依赖任何第三方 mvc 框架，仅仅作为工具使用简单快速完成支付模块的开发，可轻松嵌入到任何系统里。 </p>
 *
 * <p>IJPay 交流群: 723992875</p>
 *
 * <p>Node.js 版: https://gitee.com/javen205/TNW</p>
 *
 * <p>分账接收方类型</p>
 *
 * @author Javen
 */

public enum ReceiverType {
    /**
     * 商户ID
     */
    MERCHANT("MERCHANT_ID"),
    /**
     * 个人微信号
     */
    WECHATID("PERSONAL_WECHATID"),
    /**
     * 个人 openId（由父商户 appId 转换得到）
     */
    OPENID("PERSONAL_OPENID"),
    /**
     * 个人 sub_openid（由子商户 appId 转换得到）
     */
    SUB_OPENID("PERSONAL_SUB_OPENID");


    /**
     * 类型
     */
    private final String type;

    ReceiverType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}

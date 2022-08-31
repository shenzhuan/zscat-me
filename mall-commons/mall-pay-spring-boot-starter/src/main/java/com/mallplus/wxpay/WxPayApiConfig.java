package com.mallplus.wxpay;

import lombok.*;

import java.io.Serializable;

/**
 * <p>IJPay 让支付触手可及，封装了微信支付、支付宝支付、银联支付常用的支付方式以及各种常用的接口。</p>
 *
 * <p>不依赖任何第三方 mvc 框架，仅仅作为工具使用简单快速完成支付模块的开发，可轻松嵌入到任何系统里。 </p>
 *
 * <p>IJPay 交流群: 723992875</p>
 *
 * <p>Node.js 版: https://gitee.com/javen205/TNW</p>
 *
 * <p>微信支付常用配置</p>
 *
 * @author Javen
 */

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class WxPayApiConfig implements Serializable {
    private static final long serialVersionUID = -9044503427692786302L;

    private String appId;
    private String mchId;
    private String slAppId;
    private String slMchId;
    private String partnerKey;
    private String domain;
    private String certPath;
    private Object exParams;
}

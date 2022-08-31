package com.mallplus.order.pay.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * <p>IJPay 让支付触手可及，封装了微信支付、支付宝支付、银联支付常用的支付方式以及各种常用的接口。</p>
 *
 * <p>不依赖任何第三方 mvc 框架，仅仅作为工具使用简单快速完成支付模块的开发，可轻松嵌入到任何系统里。 </p>
 *
 * <p>IJPay 交流群: 723992875</p>
 *
 * <p>Node.js 版: https://gitee.com/javen205/TNW</p>
 *
 * <p>JD配置 Bean</p>
 *
 * @author Javen
 */
@Component
@PropertySource("classpath:/production/jdpay.properties")
@ConfigurationProperties(prefix = "jdpay")
public class JdPayBean {
    private String mchId;
    private String rsaPrivateKey;
    private String desKey;
    private String rsaPublicKey;
    private String certPath;

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getRsaPrivateKey() {
        return rsaPrivateKey;
    }

    public void setRsaPrivateKey(String rsaPrivateKey) {
        this.rsaPrivateKey = rsaPrivateKey;
    }

    public String getDesKey() {
        return desKey;
    }

    public void setDesKey(String desKey) {
        this.desKey = desKey;
    }

    public String getRsaPublicKey() {
        return rsaPublicKey;
    }

    public void setRsaPublicKey(String rsaPublicKey) {
        this.rsaPublicKey = rsaPublicKey;
    }

    public String getCertPath() {
        return certPath;
    }

    public void setCertPath(String certPath) {
        this.certPath = certPath;
    }

    @Override
    public String toString() {
        return "JdPayBean{" +
                "mchId='" + mchId + '\'' +
                ", rsaPrivateKey='" + rsaPrivateKey + '\'' +
                ", desKey='" + desKey + '\'' +
                ", rsaPublicKey='" + rsaPublicKey + '\'' +
                ", certPath='" + certPath + '\'' +
                '}';
    }
}

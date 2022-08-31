package com.mallplus.order.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Data
@ToString
@Component
@ConfigurationProperties(prefix = "wx")
public class WxAppletProperties {

    private String secret;
    private String appId;
    private String mchId;
    private String paySignKey;
    private String certName;
    private String getCode;
    private String notifyUrl;
    private String orderquery;
    private String refundUrl;
    private String refundqueryUrl;
    private String tradeType;
    private String uniformorder;
    private String userMessage;
    private String webAccessTokenhttps;
    private  String templateId ;


}

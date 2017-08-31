package com.zscat.marketing.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author : zscat
 * @version : 1.0
 * @created on  : 2017/6/28  下午4:18
 */
@Configuration
@ConfigurationProperties(prefix = "wx")
public class WxConfigProperties {

    private String appid;
    private String appsecret;
    private String token;
    private String aeskey;
    private String partenerId;
    private String partenerKey;
    private String pageDomain;

    public String getAppid() {
        return appid;
    }

    public WxConfigProperties setAppid(String appid) {
        this.appid = appid;
        return this;
    }

    public String getAppsecret() {
        return appsecret;
    }

    public WxConfigProperties setAppsecret(String appsecret) {
        this.appsecret = appsecret;
        return this;
    }

    public String getToken() {
        return token;
    }

    public WxConfigProperties setToken(String token) {
        this.token = token;
        return this;
    }

    public String getAeskey() {
        return aeskey;
    }

    public WxConfigProperties setAeskey(String aeskey) {
        this.aeskey = aeskey;
        return this;
    }

    public String getPartenerId() {
        return partenerId;
    }

    public WxConfigProperties setPartenerId(String partenerId) {
        this.partenerId = partenerId;
        return this;
    }

    public String getPartenerKey() {
        return partenerKey;
    }

    public WxConfigProperties setPartenerKey(String partenerKey) {
        this.partenerKey = partenerKey;
        return this;
    }

    public String getPageDomain() {
        return pageDomain;
    }

    public WxConfigProperties setPageDomain(String pageDomain) {
        this.pageDomain = pageDomain;
        return this;
    }
}

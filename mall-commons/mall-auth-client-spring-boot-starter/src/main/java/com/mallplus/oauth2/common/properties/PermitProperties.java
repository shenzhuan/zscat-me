package com.mallplus.oauth2.common.properties;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 配置需要放权的url白名单
 *
 * @author mall
 */
@Setter
@Getter
public class PermitProperties {
    /**
     * 监控中心和swagger需要访问的url
     */
    private static final String[] ENDPOINTS = {
            "/sys/logout/**" ,
            "/oauth/**",
            "/actuator/**",
            "/*/v2/api-docs",
            "/swagger/api-docs",
            "/swagger-ui.html",
            "/swagger-resources/**",
            "/webjars/**",
            "/api-ums/api/applet/**",
            "/api/applet/**",
            "/druid/**"
    };

    private static final String[] marking = {
            "/api-sms/notAuth/**"
    };
    private static final String[] cms = {
            "/api-cms/notAuth/**"
    };
    private static final String[] order = {
            "/api-order/notAuth/**"
    };
    private static final String[] goods = {
            "/api-goods/notAuth/**"
    };
    private static final String[] member = {
            "/api-member/notAuth/**"
    };
    /**
     * 设置不用认证的url
     */
    private String[] httpUrls = {};

    /**
     * 设置认证后不需要判断具体权限的url，所有登录的人都能访问
     */
    private String[] menusPaths = {};

    public String[] getUrls() {
        if (httpUrls == null || httpUrls.length == 0) {
            return ENDPOINTS;
        }
        List<String> list = new ArrayList<>();
        for (String url : ENDPOINTS) {
            list.add(url);
        }
        for (String url : httpUrls) {
            list.add(url);
        }
        for (String url : marking) {
            list.add(url);
        }
        for (String url : cms) {
            list.add(url);
        }
        for (String url : order) {
            list.add(url);
        }
        for (String url : goods) {
            list.add(url);
        }
        for (String url : member) {
            list.add(url);
        }
        list.add("/oauth/user/reg/token");
        return list.toArray(new String[list.size()]);
    }
}

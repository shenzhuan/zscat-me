/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zscat.marketing.utils;

import com.zscat.marketing.constant.Constant;

import javax.servlet.http.Cookie;

/**
 *
 * cookie 工具类
 *
 * @fileName : CookieUtil.java
 * @encoding : UTF-8
 * @author : zscat
 * @version : 1.0
 * @created on  : 2017/6/28  下午4:18
 */
public class CookieUtil {

    /**
     * <pre>
     *  根据cookie key获取cookie对象
     * </pre>
     *
     * @param key String Cooke name
     * @return Cookie
     */
    public static Cookie getCookie(String key) {
        Cookie cookie = null;
        Cookie[] cookieArr = RequestUtil.getRequest().getCookies();
        if (cookieArr != null) {
            for (Cookie cookieObj : cookieArr) {
                if (cookieObj.getName().equals(key)) {
                    cookie = cookieObj;
                    break;
                }
            }
        }
        return cookie;
    }

    /**
     * <pre>
     *  根据cookie key获取Cooke值
     * </pre>
     *
     * @param key String Cookie name
     * @return String
     */
    public static String getCookieValue(String key) {
        String value = "";
        Cookie cookie = getCookie(key);
        if (cookie != null) {
            value = cookie.getValue();
        }
        return value;
    }

    /**
     * 根据key判断当
     *
     * @param key String
     * @return boolean
     */
    public static boolean contains(String key) {
        boolean result = false;

        Cookie cookie = getCookie(key);
        if (cookie != null) {
            result = true;
        }
        return result;
    }



    /**
     * 从cookie取 token
     *
     * @return String
     */
    public static String getToken() {
        String leId = null;
        Cookie cookie = getCookie(Constant.SESSION_USER_KEY);
        if (cookie != null) {
            leId = cookie.getValue();
        }
        return leId;

    }
}

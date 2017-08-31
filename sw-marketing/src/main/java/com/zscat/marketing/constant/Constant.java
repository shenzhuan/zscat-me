package com.zscat.marketing.constant;

/**
 * @author : zscat
 * @version : 1.0
 * @created on  : 2017/6/28  下午3:47
 */
public class Constant {

    /**
     * 微信中H5页面 前缀
     */
    public static final String WX_H5_URI = "/wx/h5/";
    public static final String WX_SERVER_URI = "/wx/server/";

    /**
     * 微信回调页面前缀
     */
    public static final String WX_CALLBACK_URI = "/wx/callback/";

    /**
     * 7天有效期
     */
    public static int SESSION_TIME = 60 * 60 * 24 * 7;

    /**
     * iF推广员 登录后session key
     */
    public static  String SESSION_USER_KEY = "ifu";


    /**
     * key: ifu:{token}
     */
    public static  String SESSION_REDIS_KEY = "ifu:%s";
    public static  String WX_STATE_REDIS_KEY = "wxstate:%s";

}

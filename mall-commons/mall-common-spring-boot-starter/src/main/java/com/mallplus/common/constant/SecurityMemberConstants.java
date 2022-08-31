package com.mallplus.common.constant;

/**
 * Security 权限常量
 *
 * @author mall
 */
public interface SecurityMemberConstants {
    /**
     * 用户信息分隔符
     */
    String USER_SPLIT = ":";

    /**
     * 用户信息头
     */
    String USER_HEADER = "x-user-header";

    /**
     * 角色信息头
     */
    String ROLE_HEADER = "x-role-header";

    /**
     * 基础角色
     */
    String BASE_ROLE = "ROLE_USER";

    /**
     * 授权码模式
     */
    String AUTHORIZATION_CODE = "authorization_code";

    /**
     * 密码模式
     */
    String PASSWORD = "password";

    /**
     * 刷新token
     */
    String REFRESH_TOKEN = "refresh_token";

    /**
     * oauth token
     */
    String OAUTH_TOKEN_URL = "/oauth/token";

    /**
     * 默认的处理验证码的url前缀
     */
    String DEFAULT_VALIDATE_CODE_URL_PREFIX = "/validata/ums/code";

    /**
     * 手机号的处理验证码的url前缀
     */
    String MOBILE_VALIDATE_CODE_URL_PREFIX = "/validata/ums/smsCode";

    /**
     * 默认生成图形验证码宽度
     */
    String DEFAULT_IMAGE_WIDTH = "100";

    /**
     * 默认生成图像验证码高度
     */
    String DEFAULT_IMAGE_HEIGHT = "35";

    /**
     * 默认生成图形验证码长度
     */
    String DEFAULT_IMAGE_LENGTH = "4";

    /**
     * 默认生成图形验证码过期时间
     */
    int DEFAULT_IMAGE_EXPIRE = 60*5;

    /**
     * 边框颜色，合法值： r,g,b (and optional alpha) 或者 white,black,blue.
     */
    String DEFAULT_COLOR_FONT = "blue";

    /**
     * 图片边框
     */
    String DEFAULT_IMAGE_BORDER = "no";

    /**
     * 默认图片间隔
     */
    String DEFAULT_CHAR_SPACE = "5";

    /**
     * 默认保存code的前缀
     */
    String DEFAULT_CODE_KEY = "DEFAULT_CODE_KEY";

    /**
     * 验证码文字大小
     */
    String DEFAULT_IMAGE_FONT_SIZE = "30";
    /**
     * mall公共前缀
     */
    String ZLT_PREFIX = "mall:";
    /**
     * 缓存client的redis key，这里是hash结构存储
     */
    String CACHE_CLIENT_KEY = "oauth_client_details";
    /**
     * OAUTH模式登录处理地址
     */
    String OAUTH_LOGIN_PRO_URL = "/user/ums/login";
    /**
     * PASSWORD模式登录处理地址
     */
    String PASSWORD_LOGIN_PRO_URL = "/oauth/user/ums/token";

    /**
     * 电话 密码注册
     */
    String PASSWORD_RRG_PRO_URL = "/oauth/user/reg/token";
    /**
     * 验证码模式登录处理地址
     */
    String CODE_LOGIN_PRO_URL = "/oauth/user/code/token";
    /**
     * 获取授权码地址
     */
    String AUTH_CODE_URL = "/oauth/authorize";
    /**
     * 登录页面
     */
    String LOGIN_PAGE = "/login.html";
    /**
     * 默认的OPENID登录请求处理url
     */
    String OPENID_TOKEN_URL = "/oauth/openId/ums/token";

    /**
     * 默认的OPENID登录请求处理url
     */
    String WEIXIN_TOKEN_URL = "/oauth/weixin/ums/token";
    /**
     * 手机登录URL
     */
    String MOBILE_TOKEN_URL = "/oauth/mobile/ums/token";
    /**
     * 登出URL
     */
    String LOGOUT_URL = "/oauth/remove/ums/token";
}

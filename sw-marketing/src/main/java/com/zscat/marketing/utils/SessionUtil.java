package com.zscat.marketing.utils;


import com.zsCat.common.base.RedisLink;
import com.zsCat.common.utils.JSONSerializerUtil;
import com.zscat.marketing.constant.Constant;
import com.zscat.marketing.model.PromotionUser;
import com.zscat.marketing.service.IPromotionUserService;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * session util
 *
 * @author : zscat
 * @version : 1.0
 * @created on  : 2017/6/28  下午4:18
 */
@Component
public class SessionUtil {
    @Resource
    RedisLink redisLink;
    @Resource
    private IPromotionUserService ipromotionUserService;
    public PromotionUser getCurrentUser() {
        String token = CookieUtil.getToken();
        if (token==null){
            return  null;
        }
        String redisKey = String.format(Constant.SESSION_REDIS_KEY, token);
        byte[] userBytes = redisLink.get(redisKey);
        PromotionUser user = null;
        if (null != userBytes) {
            user = JSONSerializerUtil.unserialize(userBytes, PromotionUser.class);
        }
        return user;
    }


    public PromotionUser getCurrentUser(String token) {
        String redisKey = String.format(Constant.SESSION_REDIS_KEY, token);
        byte[] userBytes = redisLink.get(redisKey);
        PromotionUser user = null;
        if (null != userBytes) {
            user = JSONSerializerUtil.unserialize(userBytes, PromotionUser.class);
        }
        return user;
    }

    public void setCurrentUser(HttpServletResponse response, PromotionUser suyUser) {
        String cookieToken = CookieUtil.getToken();
        String token = cookieToken == null ? suyUser.getToken() : cookieToken;
        String redisKey = String.format(Constant.SESSION_REDIS_KEY, token);
        redisLink.setString(redisKey, JSONSerializerUtil.serialize(suyUser));

        setTokenToCookie(token, response);
    }

    public String getString(String key) {
        String au = null;
        Object obj = RequestUtil.getRequest().getSession().getAttribute(key);
        if (null != obj) {
            au = obj.toString();
        }
        return au;
    }


    public Object getObject(String key) {
        Object obj = RequestUtil.getRequest().getSession().getAttribute(key);
        return obj;
    }

    public void setValue(String key, Object obj) {
        RequestUtil.getRequest().getSession().setAttribute(key, obj);
    }

    public String getSessionId() {
        return RequestUtil.getRequest().getSession().getId();
    }

    public void setTokenToCookie(String token, HttpServletResponse response) {
        setTokenToCookie(token, Constant.SESSION_TIME, response);
    }

    /**
     * 生成token
     *
     * @return
     */
    public String generateToken() {
        String token = UUID.randomUUID().toString();
        return token;
    }

    /**
     * 设置用户token存入cookie
     *
     * @param token
     * @param cookieAge
     * @param response
     */
    public void setTokenToCookie(String token, int cookieAge, HttpServletResponse response) {

        Cookie cookie_com = new Cookie(Constant.SESSION_USER_KEY, token);
        cookie_com.setPath("/");
        if (cookieAge != -1) {
            cookie_com.setMaxAge(cookieAge);
        }
        response.addCookie(cookie_com);
    }

    /**
     * 返回新的  微信state
     *
     * @return
     */
    public String getNewWxState() {
        String newState = RandomStringUtils.randomNumeric(8);
        String redisKey = String.format(Constant.WX_STATE_REDIS_KEY, newState);
        redisLink.setString(redisKey, newState);
        return newState;
    }

    /**
     * 判断微信 state是否存储
     *
     * @param state
     * @return
     */
    public boolean isExistState(String state) {
        String redisKey = String.format(Constant.WX_STATE_REDIS_KEY, state);
        return redisLink.exist(redisKey);
    }


    public void delState(String state) {
        String redisKey = String.format(Constant.WX_STATE_REDIS_KEY, state);
        redisLink.del(redisKey);
    }


}

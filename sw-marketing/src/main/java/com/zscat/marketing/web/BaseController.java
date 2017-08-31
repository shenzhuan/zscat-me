package com.zscat.marketing.web;

import com.zscat.marketing.constant.Constant;
import com.zscat.marketing.model.PromotionUser;
import com.zscat.marketing.service.IPromotionUserService;
import com.zscat.marketing.utils.CookieUtil;
import com.zscat.marketing.utils.DateUtils;
import com.zscat.marketing.utils.RequestUtil;
import com.zscat.marketing.utils.SessionUtil;
import com.zscat.marketing.web.converter.MultiDateParseEditor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author : zscat
 * @version : 1.0
 * @created on  : 2016/11/13  上午10:00
 */
public class BaseController {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    //page
    public static final String PAGE_REGISTER = "/user/signup"; //注册推广员
    public static final String PAGE_USERINFO = "/user/info"; //推广员信息
    public static final String PAGE_INCOMES = "/income/below-income"; //收入明细
    public static final String PAGE_BELOW_TGUSERS = "/below-user"; //下线推广员
    public static final String PAGE_ERROR = "/error"; //错误页面

    @Resource
    SessionUtil sessionUtil;

    @Resource
    IPromotionUserService promotionUserService;

    /**
     * 重定向
     *
     * @param viewName
     * @return
     */
    public String redirect(String viewName) {
        return "redirect:" + viewName;
    }


    /**
     * 从cookie取 token
     *
     * @return String
     */
    public String getToken() {
        String leId = null;
        Cookie cookie = CookieUtil.getCookie(Constant.SESSION_USER_KEY);
        if (cookie != null) {
            leId = cookie.getValue();
        }
        return leId;

    }


    /**
     * 取request
     *
     * @return HttpServletRequest
     */
    public HttpServletRequest getRequest() {
        return RequestUtil.getRequest();
    }

    /**
     * 判断一个请求是不是ajax请求
     *
     * @return Boolean
     */
    public Boolean isAjaxRequest() {
        return RequestUtil.isAjaxRequest();
    }

    /**
     * 获取用户浏览器信息
     *
     * @return String
     */
    public String getUserAgent() {
        return RequestUtil.getUserAgent();
    }

    /**
     * 获取客户端ip
     *
     * @return String ip
     */
    public String getClientIP() {
        return RequestUtil.getClientIP();
    }

    /**
     * 判断一个字符串,是不是ip字符串
     *
     * @param ipStr String
     * @return boolean
     */
    public boolean isIpString(String ipStr) {
        return RequestUtil.isIpString(ipStr);
    }

    public PromotionUser getCurrentUser() {
        return sessionUtil.getCurrentUser();
    }

    public PromotionUser getCurrentUser(HttpServletResponse response) {
        PromotionUser myPromotion = sessionUtil.getCurrentUser(); //获取当前推广员
        if (myPromotion != null && myPromotion.getUid() == null && org.apache.commons.lang.StringUtils.isNotEmpty(myPromotion.getWxOpenId())) { //session过期
            PromotionUser query = new PromotionUser();
            query.setWxOpenId(myPromotion.getWxOpenId());
            PromotionUser oldPromotion = promotionUserService.selectOne(query); //如果为空,新用户
            if (oldPromotion != null && oldPromotion.getUid() != null) {
                setSession(response, myPromotion);
                return oldPromotion;
            }
        }
        return myPromotion;
    }

    public void setSession(HttpServletResponse response, PromotionUser user) {
        sessionUtil.setCurrentUser(response, user);
    }

    /**
     * @param @param binder    设定文件
     * @return void    返回类型
     * @Title: initBinder
     * @Description: 表单数据格式转换
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        //自动进行日期格式的转换
        List<DateFormat> dateFormats = new LinkedList<DateFormat>();
        dateFormats.add(new SimpleDateFormat(DateUtils.DEFAULT_DATE_FORMAT));
        dateFormats.add(new SimpleDateFormat(DateUtils.DEFAULT_DATE_TIME_FORMAT));
        binder.registerCustomEditor(Date.class, new MultiDateParseEditor(dateFormats, true));
        //防止XSS攻击
        //binder.registerCustomEditor(String.class, new StringEscapeEditor(true, true,true));
    }


}

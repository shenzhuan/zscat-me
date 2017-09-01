package com.zscat.marketing.filter;

import com.zscat.marketing.config.WxConfigProperties;
import com.zscat.marketing.constant.Constant;
import com.zscat.marketing.model.PromotionUser;
import com.zscat.marketing.service.IPromotionUserService;
import com.zscat.marketing.utils.SessionUtil;
import com.zscat.marketing.web.UserController;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : zscat
 * @version : 1.0
 * @created on  : 2017/6/28  下午4:18
 */
@Component
public class WxAuthFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(WxAuthFilter.class);

    @Autowired
    WxMpService wxMpService;

    @Autowired
    IPromotionUserService promotionUserService;

    @Autowired
    SessionUtil sessionUtil;

    @Autowired
    WxConfigProperties wxConfigProperties;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }


    private Map<String, String> getHeadersInfo(HttpServletRequest request) {
        Map<String, String> map = new HashMap<String, String>();
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
    }



    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {


        MDC.put("logId", RandomStringUtils.randomNumeric(8));

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String uri = httpServletRequest.getRequestURI();
        logger.info("request url={}?{}", uri, httpServletRequest.getQueryString());
        logger.info("doFilter getHeadersInfo:{}",getHeadersInfo(httpServletRequest));

        String wxOauth2Code = request.getParameter("code");
        String state = request.getParameter("state");
        PromotionUser user = sessionUtil.getCurrentUser();
        logger.info("user:{}", user);

        if (uri.startsWith(Constant.WX_H5_URI) && null == user
                && (StringUtils.isEmpty(state) || !sessionUtil.isExistState(state))) {
            logger.info("current user is null");

            String param = httpServletRequest.getQueryString();
            String redirectUrl = wxConfigProperties.getPageDomain() + uri;
            if (StringUtils.isNotBlank(param)) {
                redirectUrl += "?" + param;
            }
            String scope = "snsapi_userinfo";
            String wxOauth2Url = wxMpService.oauth2buildAuthorizationUrl(redirectUrl, scope
                    , sessionUtil.getNewWxState());

            logger.info(":{},{},{},{}", wxOauth2Url, scope, state, redirectUrl);
            httpServletResponse.sendRedirect(wxOauth2Url);
            //跳转到 微信登录页面
            return;

        } else if (uri.startsWith(Constant.WX_H5_URI) && null == user
                && (StringUtils.isNotEmpty(wxOauth2Code) && StringUtils.isNotEmpty(state))
                && sessionUtil.isExistState(state)) {

            //处理用户
            try {
                user = promotionUserService.getByWxOauthCode(wxOauth2Code);
                user.setToken(sessionUtil.generateToken());
                sessionUtil.setCurrentUser(httpServletResponse, user);
                Long uid = user.getUid();
                if (null == uid || uid <= 0) {
                    logger.info("----go to signup page------");
                    String toUrl = wxConfigProperties.getPageDomain() + UserController.VIEW_SIGNUP + "?token=" + user.getToken();
                    httpServletResponse.sendRedirect(toUrl);
                    return;
                }
            } catch (WxErrorException e) {
                e.printStackTrace();
            }

        }
        chain.doFilter(httpServletRequest, response);
    }

    @Override
    public void destroy() {

    }

}

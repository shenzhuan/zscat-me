package com.zsCat.common.filter;

import com.alibaba.fastjson.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * Check if an user is authorized
 */
//@Component
//@Order(2)
public class AuthFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(AuthFilter.class);

    private static String NOAUTHMETHOD = "client_msg,sendVideoMsg,sms_code,sms_codev1,sms_codev2,login,loginv1,loginv2,loginyx,upgrade,checklogincode,voiceCode,voiceCodev1,uploadLog,uploadLogv1,getStat,hadSendRedpacket";



    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {


        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String content = "";
        String decode_res = "";
        JSONObject jsonObject;
        String uid = "";
        String uri = httpServletRequest.getRequestURI();

        MDC.put("logId", UUID.randomUUID().toString());



        if (uri.startsWith("/web") || uri.startsWith("/user/callback")|| uri.startsWith("/file/callback") || uri.startsWith("/longvideo/callback")
                || uri.startsWith("/app/resources")
                || uri.startsWith("/videoPlay/callback")
                || uri.startsWith("/open")) {
            logger.info("request url={}?{}", uri, httpServletRequest.getQueryString());
            chain.doFilter(httpServletRequest,httpServletResponse);
            return;
        }


        chain.doFilter(httpServletRequest, response);
    }

    @Override
    public void destroy() {
    }

}

package com.zscat.filter;

import com.alibaba.fastjson.JSONObject;
import com.zscat.shop.model.Member;
import com.zscat.shop.util.SysUserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * Check if an user is authorized
 */

public class ShopAuthFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(ShopAuthFilter.class);

    private static String NOAUTHMETHOD = "client_msg,sendVideoMsg,sms_code,sms_codev1,sms_codev2,login,loginv1,loginv2,loginyx,upgrade,checklogincode,voiceCode,voiceCodev1,uploadLog,uploadLogv1,getStat,hadSendRedpacket";



    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {


        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String uri = httpServletRequest.getRequestURI(); //请求路径
        String ctx = httpServletRequest.getContextPath();
        String path = uri.replace(ctx,"");

        if(path.startsWith("/person")){
            Member member= SysUserUtils.getSessionLoginUser();
            if (member == null || member == null) { // 转到登陆页面
                httpServletResponse.sendRedirect(ctx + "/front/login");

            } else{

            }
        }
        if(path.startsWith("/wap/person")){
            Member member=SysUserUtils.getSessionLoginUser();
            if (member == null || member == null) { // 转到登陆页面
                httpServletResponse.sendRedirect(ctx + "/wap/login");

            } else{

            }
        }
        if(path.startsWith("/youhong/person")){
            Member member=SysUserUtils.getSessionLoginUser();
            if (member == null || member == null) { // 转到登陆页面
                httpServletResponse.sendRedirect(ctx + "/web1/login");

            } else{

            }
        }


        chain.doFilter(httpServletRequest, response);
    }

    @Override
    public void destroy() {
    }

}

package com.zscat.filter;

import com.zscat.shop.model.Member;
import com.zscat.shop.util.SysUserUtils;
import com.zscat.util.MemberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Check if an user is authorized
 */

public class H5AuthFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(H5AuthFilter.class);

    private static String NOAUTHMETHOD = "client_msg,sendVideoMsg,sms_code,sms_codev1,sms_codev2,login,loginv1,loginv2,loginyx,upgrade,checklogincode,voiceCode,voiceCodev1,uploadLog,uploadLogv1,getStat,hadSendRedpacket";


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
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {


        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String uri = httpServletRequest.getRequestURI(); //请求路径
        String ctx = httpServletRequest.getContextPath();
        String path = uri.replace(ctx,"");
        logger.info("doFilter getHeadersInfo:{}",getHeadersInfo(httpServletRequest));
        if(path.startsWith("/wap/person")){
            Member member= SysUserUtils.getSessionLoginUser(httpServletRequest);
            if (member == null ) { // 转到登陆页面
                String u="http://localhost:8080/wap/login";
                httpServletResponse.sendRedirect(u);
              //  httpServletResponse.sendRedirect(ctx + "/wap/login");
                return;

            }
        }

        chain.doFilter(httpServletRequest, httpServletResponse);
    }

    @Override
    public void destroy() {
    }

}

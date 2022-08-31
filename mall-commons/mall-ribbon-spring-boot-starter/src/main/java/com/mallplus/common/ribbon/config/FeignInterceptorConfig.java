package com.mallplus.common.ribbon.config;

import cn.hutool.core.util.StrUtil;
import com.mallplus.common.constant.CommonConstant;
import com.mallplus.common.constant.SecurityConstants;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * feign拦截器
 *
 * @author mall
 */
public class FeignInterceptorConfig {

    /**
     * 使用feign client访问别的微服务时，将access_token放入参数或者header ，Authorization:Bearer xxx
     * 或者url?access_token=xxx
     */
    @Bean
    public RequestInterceptor requestInterceptor() {
        RequestInterceptor requestInterceptor = template -> {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null) {
                if (authentication instanceof OAuth2Authentication) {
                    OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
                    String access_token = details.getTokenValue();
                    template.header("Authorization", OAuth2AccessToken.BEARER_TYPE + " " + access_token);
                }
            }
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                    .getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            //传递userid
            String userid = request.getHeader(SecurityConstants.USER_ID_HEADER);
            if (StrUtil.isNotEmpty(userid)) {
                template.header(SecurityConstants.USER_ID_HEADER, userid);
            }

            //传递username
            String username = request.getHeader(SecurityConstants.USER_HEADER);
            if (StrUtil.isNotEmpty(username)) {
                template.header(SecurityConstants.USER_HEADER, username);
            }

            //传递roles
            String roles = request.getHeader(SecurityConstants.ROLE_HEADER);
            if (StrUtil.isNotEmpty(roles)) {
                template.header(SecurityConstants.ROLE_HEADER, roles);
            }

            //传递client
           /* String tenant = TenantContextHolder.getTenant();
            if (StrUtil.isNotEmpty(tenant)) {
                template.header(SecurityConstants.TENANT_HEADER, tenant);
            }*/
        };
        return requestInterceptor;
    }

}

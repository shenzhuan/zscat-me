package com.mallplus.oauth.dto;

import org.springframework.security.oauth2.common.OAuth2AccessToken;

/**
 * @Auther: shenzhuan
 * @Date: 2019/5/4 14:38
 * @Description:
 */
public interface OAuth2AccessTokenVo  extends OAuth2AccessToken{
    public Long userId = null;
}

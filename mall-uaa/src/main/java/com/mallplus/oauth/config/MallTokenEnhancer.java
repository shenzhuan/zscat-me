/*
 *    Copyright (c) 2018-2025, edynamic All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: edynamic
 */

package com.mallplus.oauth.config;


import com.mallplus.common.constant.SecurityConstants;
import com.mallplus.common.model.SysUser;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mallcloud
 * @date 2019-09-17
 * <p>
 * token增强，客户端模式不增强。
 */
public class MallTokenEnhancer implements TokenEnhancer {
    /**
     * Provides an opportunity for customization of an access token (e.g. through its additional information map) during
     * the process of creating a new token for use by a client.
     *
     * @param accessToken    the current access token with its expiration and refresh token
     * @param authentication the current authentication including client and user details
     * @return a new token enhanced with additional information
     */
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        if (SecurityConstants.CLIENT_CREDENTIALS
                .equals(authentication.getOAuth2Request().getGrantType())) {
            return accessToken;
        }

        final Map<String, Object> additionalInfo = new HashMap<>(8);
        SysUser sysUser = (SysUser) authentication.getUserAuthentication().getPrincipal();
        if (sysUser != null) {
            additionalInfo.put(SecurityConstants.DETAILS_USER_ID, sysUser.getId());
            additionalInfo.put(SecurityConstants.DETAILS_USERNAME, sysUser.getUsername());
        }
		/*additionalInfo.put(SecurityConstants.DETAILS_DEPT_ID, txUser.getDeptId());
		additionalInfo.put(SecurityConstants.DETAILS_TENANT_ID, txUser.get());*/
        additionalInfo.put(SecurityConstants.DETAILS_LICENSE, SecurityConstants.MALL_LICENSE);
        additionalInfo.put(SecurityConstants.ACTIVE, Boolean.TRUE);
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
        return accessToken;
    }
}

package com.mallplus.oauth.openid.member;

import com.mallplus.oauth.service.ZltUserDetailsService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author mall
 */
public class OpenIdMemberAuthenticationProvider implements AuthenticationProvider {

    private ZltUserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) {
        OpenIdMemberAuthenticationToken authenticationToken = (OpenIdMemberAuthenticationToken) authentication;
        String openId = (String) authenticationToken.getPrincipal();
        UserDetails user = userDetailsService.loadMemberByOpenId(openId);
        if (user == null) {
            throw new InternalAuthenticationServiceException("openId错误");
        }
        OpenIdMemberAuthenticationToken authenticationResult = new OpenIdMemberAuthenticationToken(user, user.getAuthorities());
        authenticationResult.setDetails(authenticationToken.getDetails());
        return authenticationResult;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return OpenIdMemberAuthenticationToken.class.isAssignableFrom(authentication);
    }

    public ZltUserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(ZltUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}

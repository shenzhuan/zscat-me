package com.mallplus.oauth.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mallplus.common.constant.SecurityConstants;
import com.mallplus.common.feign.UserService;
import com.mallplus.common.model.Result;
import com.mallplus.common.model.SysRole;
import com.mallplus.common.model.SysUser;
import com.mallplus.common.utils.CommonResult;
import com.mallplus.common.utils.SpringUtil;
import com.mallplus.oauth.mobile.MobileAuthenticationToken;
import com.mallplus.oauth.openid.OpenIdAuthenticationToken;
import com.mallplus.oauth.service.impl.RedisClientDetailsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.refresh.RefreshTokenGranter;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.security.Principal;
import java.util.*;

/**
 * OAuth2相关操作
 *
 * @author mall
 */
@Api(tags = "OAuth2相关操作")
@Slf4j
@RestController
public class OAuth2Controller {

    @Resource
    private ObjectMapper objectMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Resource
    private AuthorizationServerTokenServices authorizationServerTokenServices;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Resource
    private UserService userService;

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private UserDetailsService userDetailsService;



    @ApiOperation(value = "用户名密码获取token")
    @PostMapping(SecurityConstants.PASSWORD_LOGIN_PRO_URL)
    public void getUserTokenInfo(@RequestBody SysUser param,
            HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (param.getUsername() == null || "".equals(param.getUsername())) {
            throw new UnapprovedClientAuthenticationException("用户名为空");
        }
        if ( param.getPassword() == null || "".equals( param.getPassword())) {
            throw new UnapprovedClientAuthenticationException("密码为空");
        }


       if (true){
           UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(param.getUsername(),  param.getPassword());
           writerToken(request, response, token, "用户名或密码错误",1L);
       }else {
           exceptionHandler(response, "用户名或密码错误");
       }

    }

    @ApiOperation(value = "openId获取token")
    @PostMapping(SecurityConstants.OPENID_TOKEN_URL)
    public void getTokenByOpenId(
            @ApiParam(required = true, name = "openId", value = "openId") String openId,
            HttpServletRequest request, HttpServletResponse response) throws IOException {
        OpenIdAuthenticationToken token = new OpenIdAuthenticationToken(openId);
        SysUser member = userService.findByOpenId(openId);
        if (member!=null){
            writerToken(request, response, token, "openId错误",member.getId());
        }else {
            exceptionHandler(response, "openId错误");
        }

    }




    @ApiOperation(value = "mobile获取token")
    @PostMapping(SecurityConstants.MOBILE_TOKEN_URL)
    public void getTokenByMobile(
            @ApiParam(required = true, name = "mobile", value = "mobile") String mobile,
            @ApiParam(required = true, name = "password", value = "密码") String password,
            HttpServletRequest request, HttpServletResponse response) throws IOException {
        MobileAuthenticationToken token = new MobileAuthenticationToken(mobile, password);
        writerToken(request, response, token, "手机号或密码错误",1L);
    }


    /**
     * 当前登陆用户信息
     * security获取当前登录用户的方法是SecurityContextHolder.getContext().getAuthentication()
     * 这里的实现类是org.springframework.security.oauth2.provider.OAuth2Authentication
     *
     * @return
     */
    @ApiOperation(value = "当前登陆用户信息")
    @RequestMapping(value = "/oauth/userinfo", method = RequestMethod.GET)
    public Object getCurrentUserDetail(Principal principal) {
        Map<String, Object> data = new HashMap<>();
        if (principal!=null){
            String username = principal.getName();
            SysUser umsAdmin = userService.selectByUsername(username);
            data.put("username", umsAdmin.getUsername());
            data.put("icon", umsAdmin.getIcon());
        }
        data.put("roles", new String[]{"TEST"});
        return new CommonResult().success(data);

    }

    private void writerToken(HttpServletRequest request, HttpServletResponse response, AbstractAuthenticationToken token
            , String badCredenbtialsMsg,Long userId) throws IOException {
        try {
            String clientId = request.getHeader("client_id");
            String clientSecret = request.getHeader("client_secret");
            if (clientId == null || "".equals(clientId)) {
                throw new UnapprovedClientAuthenticationException("请求头中无client_id信息");
            }

            if (clientSecret == null || "".equals(clientSecret)) {
                throw new UnapprovedClientAuthenticationException("请求头中无client_secret信息");
            }

            ClientDetails clientDetails = getClient(clientId, clientSecret, null);
            TokenRequest tokenRequest = new TokenRequest(MapUtils.EMPTY_MAP, clientId, clientDetails.getScope(), "customer");
            OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);
            Authentication authentication = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, authentication);
            OAuth2AccessToken oAuth2AccessToken =  authorizationServerTokenServices.createAccessToken(oAuth2Authentication);
            oAuth2Authentication.setAuthenticated(true);

            writerObj(response, oAuth2AccessToken);
        } catch (BadCredentialsException | InternalAuthenticationServiceException e) {
            exceptionHandler(response, badCredenbtialsMsg);
            e.printStackTrace();
        } catch (Exception e) {
            exceptionHandler(response, e);
        }
    }

    @ApiOperation(value = "登出功能")
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public Object logout() {
        return new CommonResult().success(null);
    }

    @ApiOperation(value = "access_token刷新token")
    @PostMapping(value = "/oauth/refresh/token", params = "access_token")
    public void refreshTokenInfo(String access_token, HttpServletRequest request, HttpServletResponse response) {

        // 拿到当前用户信息
        try {
            Authentication user = SecurityContextHolder.getContext().getAuthentication();

            if (user != null) {
                if (user instanceof OAuth2Authentication) {
                    Authentication athentication = (Authentication) user;
                    OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) athentication.getDetails();
                }

            }
            OAuth2AccessToken accessToken = tokenStore.readAccessToken(access_token);
            OAuth2Authentication auth = (OAuth2Authentication) user;
            RedisClientDetailsService clientDetailsService = SpringUtil.getBean(RedisClientDetailsService.class);

            ClientDetails clientDetails = clientDetailsService
                    .loadClientByClientId(auth.getOAuth2Request().getClientId());

            AuthorizationServerTokenServices authorizationServerTokenServices = SpringUtil
                    .getBean("defaultAuthorizationServerTokenServices", AuthorizationServerTokenServices.class);
            OAuth2RequestFactory requestFactory = new DefaultOAuth2RequestFactory(clientDetailsService);

            RefreshTokenGranter refreshTokenGranter = new RefreshTokenGranter(authorizationServerTokenServices,
                    clientDetailsService, requestFactory);

            Map<String, String> map = new HashMap<>();
            map.put("grant_type", "refresh_token");
            map.put("refresh_token", accessToken.getRefreshToken().getValue());
            TokenRequest tokenRequest = new TokenRequest(map, auth.getOAuth2Request().getClientId(),
                    auth.getOAuth2Request().getScope(), "refresh_token");

            OAuth2AccessToken oAuth2AccessToken = refreshTokenGranter.grant("refresh_token", tokenRequest);

            tokenStore.removeAccessToken(accessToken);

            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(oAuth2AccessToken));
            response.getWriter().flush();
            response.getWriter().close();
        } catch (Exception e) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json;charset=UTF-8");
            Map<String, String> rsp = new HashMap<>();
            rsp.put("resp_code", HttpStatus.UNAUTHORIZED.value() + "");
            rsp.put("rsp_msg", e.getMessage());

            try {
                response.getWriter().write(objectMapper.writeValueAsString(rsp));
                response.getWriter().flush();
                response.getWriter().close();
            } catch (JsonProcessingException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }

    }

    /**
     * 移除access_token和refresh_token
     *
     * @param access_token
     */
    @ApiOperation(value = "移除token")
    @PostMapping(value = "/oauth/remove/token", params = "access_token")
    public void removeToken(String access_token) {

        // 拿到当前用户信息
        Authentication user = SecurityContextHolder.getContext().getAuthentication();

        if (user != null) {
            if (user instanceof OAuth2Authentication) {
                Authentication athentication = (Authentication) user;
                OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) athentication.getDetails();
            }

        }
        OAuth2AccessToken accessToken = tokenStore.readAccessToken(access_token);
        if (accessToken != null) {
            // 移除access_token
            tokenStore.removeAccessToken(accessToken);

            // 移除refresh_token
            if (accessToken.getRefreshToken() != null) {
                tokenStore.removeRefreshToken(accessToken.getRefreshToken());
            }

        }
    }
    private void exceptionHandler(HttpServletResponse response, Exception e) throws IOException {
        log.error("exceptionHandler-error:", e);
        exceptionHandler(response, e.getMessage());
    }

    private void exceptionHandler(HttpServletResponse response, String msg) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        writerObj(response, Result.failed(msg));
    }

    private void writerObj(HttpServletResponse response, Object obj) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        try (
                Writer writer = response.getWriter()
        ) {
            writer.write(objectMapper.writeValueAsString(obj));
            writer.flush();
        }
    }

    private ClientDetails getClient(String clientId, String clientSecret, RedisClientDetailsService clientDetailsService) {
        if (clientDetailsService == null) {
            clientDetailsService = SpringUtil.getBean(RedisClientDetailsService.class);
        }
        ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);

        if (clientDetails == null) {
            throw new UnapprovedClientAuthenticationException("clientId对应的信息不存在");
        } else if (!passwordEncoder.matches(clientSecret, clientDetails.getClientSecret())) {
            throw new UnapprovedClientAuthenticationException("clientSecret不匹配");
        }
        return clientDetails;
    }
}

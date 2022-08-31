package com.mallplus.oauth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mallplus.common.annotation.IgnoreAuth;
import com.mallplus.common.constant.SecurityMemberConstants;
import com.mallplus.common.entity.ums.UmsMember;
import com.mallplus.common.exception.ApiMallPlusException;
import com.mallplus.common.feign.MemberFeignClient;
import com.mallplus.common.feign.UserService;
import com.mallplus.common.model.Result;
import com.mallplus.common.model.SysUser;
import com.mallplus.common.redis.template.RedisRepository;
import com.mallplus.common.utils.CommonResult;
import com.mallplus.common.utils.SpringUtil;
import com.mallplus.oauth.mobile.member.MobileCodeMemberAuthenticationToken;
import com.mallplus.oauth.mobile.member.MobileMemberAuthenticationToken;
import com.mallplus.oauth.openid.member.OpenIdMemberAuthenticationToken;
import com.mallplus.oauth.service.impl.RedisClientDetailsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.Date;
import java.util.Map;

/**
 * OAuth2相关操作
 *
 * @author mall
 */
@Api(tags = "OAuth2相关操作")
@Slf4j
@RestController
public class OAuth2MemberController {
    @Resource
    private MemberFeignClient memberFeignClient;
    @Resource
    private ObjectMapper objectMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Resource
    private AuthorizationServerTokenServices authorizationServerTokenServices;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisRepository redisRepository;
    @Resource
    private UserService userService;


    @IgnoreAuth
    @ApiOperation("注册")
    @PostMapping(SecurityMemberConstants.PASSWORD_RRG_PRO_URL)
    public Object simpleReg(@RequestBody UmsMember ums) {
         String phone = ums.getPhone();
         String password = ums.getPassword();
         String confimpassword = ums.getConfimpassword();
        if (phone == null || "".equals(phone)) {
            return new CommonResult().validateFailed("手机号或密码错误");
        }
        if (password == null || "".equals(password)) {
            return new CommonResult().validateFailed("手机号或密码错误");
        }
        if (confimpassword == null || "".equals(confimpassword)) {
            return new CommonResult().validateFailed("手机号或密码错误");
        }
        //没有该用户进行添加操作
        UmsMember user = new UmsMember();
        user.setUsername(phone);
        user.setPhone(phone);
        user.setPassword(password);
        user.setConfimpassword(confimpassword);


        if (!user.getPassword().equals(user.getConfimpassword())) {
            return new CommonResult().failed("密码不一致");
        }
        //查询是否已有该用户

        UmsMember queryM = new UmsMember();
        queryM.setUsername(user.getUsername());

        UmsMember umsMembers = memberFeignClient.findByMobile(phone);
        if (umsMembers != null) {
            return new CommonResult().failed("该手机号已经存在");
        }
        //没有该用户进行添加操作

        UmsMember umsMember = new UmsMember();
        umsMember.setMemberLevelId(4L);
        umsMember.setUsername(user.getUsername());
        umsMember.setPhone(user.getPhone());
        umsMember.setPassword(passwordEncoder.encode(user.getPassword()));
        umsMember.setCreateTime(new Date());
        umsMember.setStatus(1);
        memberFeignClient.saveUmsMember(umsMember);
        return new CommonResult().success("注册成功", "注册成功");

    }
    @ApiOperation(value = "用户名密码获取token")
    @PostMapping(SecurityMemberConstants.PASSWORD_LOGIN_PRO_URL)
    public void getUserTokenInfo(@RequestBody SysUser umsAdminLoginParam,
            HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (umsAdminLoginParam.getUsername() == null || "".equals(umsAdminLoginParam.getUsername())) {
            throw new UnapprovedClientAuthenticationException("用户名为空");
        }
        if (umsAdminLoginParam.getPassword() == null || "".equals(umsAdminLoginParam.getPassword())) {
            throw new UnapprovedClientAuthenticationException("密码为空");
        }
        MobileMemberAuthenticationToken token = new MobileMemberAuthenticationToken(umsAdminLoginParam.getUsername(), umsAdminLoginParam.getPassword());
        UmsMember user = memberFeignClient.findByUsername(umsAdminLoginParam.getUsername());
       if (user!=null){
           writerToken(request, response, token, "用户名或密码错误",user.getId());
       }else {
           exceptionHandler(response, "用户名或密码错误");
       }

    }
    @ApiOperation(value = "手机号和验证码获取token")
    @PostMapping(SecurityMemberConstants.CODE_LOGIN_PRO_URL)
    public void getUserTokenByCode(@RequestBody UmsMember umsAdminLoginParam,
                                 HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (umsAdminLoginParam.getUsername() == null || "".equals(umsAdminLoginParam.getUsername())) {
            throw new UnapprovedClientAuthenticationException("手机号为空");
        }
        if (umsAdminLoginParam.getCode() == null || "".equals(umsAdminLoginParam.getCode())) {
            throw new UnapprovedClientAuthenticationException("验证码为空");
        }
        MobileCodeMemberAuthenticationToken token = new MobileCodeMemberAuthenticationToken(umsAdminLoginParam.getPhone());
        UmsMember member = memberFeignClient.findByMobile(umsAdminLoginParam.getPhone());
        if (member!=null){
            //验证验证码
            if (!verifyAuthCode(member.getCode(), member.getPhone())) {
                throw new ApiMallPlusException("验证码错误");
            }
            writerToken(request, response, token, "手机号错误",member.getId());
        }else {
            exceptionHandler(response, "手机号错误");
        }

    }
    @ApiOperation(value = "openId获取token")
    @PostMapping(SecurityMemberConstants.OPENID_TOKEN_URL)
    public void getTokenByOpenId(
            @ApiParam(required = true, name = "openId", value = "openId") String openId,
            HttpServletRequest request, HttpServletResponse response) throws IOException {
        OpenIdMemberAuthenticationToken token = new OpenIdMemberAuthenticationToken(openId);
        UmsMember member = memberFeignClient.findByOpenId(openId);
        if (member!=null){
            writerToken(request, response, token, "openId错误",member.getId());
        }else {
            exceptionHandler(response, "openId错误");
        }

    }


    @ApiOperation(value = "mobile获取token")
    @PostMapping(SecurityMemberConstants.MOBILE_TOKEN_URL)
    public void getTokenByMobile(@RequestBody UmsMember umsAdminLoginParam,
            HttpServletRequest request, HttpServletResponse response) throws IOException {
        MobileMemberAuthenticationToken token = new MobileMemberAuthenticationToken(umsAdminLoginParam.getPhone(), umsAdminLoginParam.getPassword());
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
    @RequestMapping(value = { "/oauth/member/userinfo" }, produces = "application/json") // 获取用户信息。/auth/user
    public Object getCurrentUserDetail() {
        return new CommonResult().success(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
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
            Map<String, String> requestParameters = new HashedMap();
            requestParameters.put("memberId",userId+"");
            ClientDetails clientDetails = getClient(clientId, clientSecret, null);
            TokenRequest tokenRequest = new TokenRequest(requestParameters, clientId, clientDetails.getScope(), "customer");
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
    //对输入的验证码进行校验
    private boolean verifyAuthCode(String authCode, String telephone) {
        if (StringUtils.isEmpty(authCode)) {
            return false;
        }
        String realAuthCode = redisRepository.get("member:code:" + telephone).toString();
        return authCode.equals(realAuthCode);
    }

}

package com.mallplus.oauth.config;

import com.mallplus.common.config.DefaultPasswordConfig;
import com.mallplus.common.constant.SecurityConstants;
import com.mallplus.oauth.mobile.MobileAuthenticationSecurityConfig;
import com.mallplus.oauth.mobile.member.MobileCodeMemberAuthenticationSecurityConfig;
import com.mallplus.oauth.mobile.member.MobileMemberAuthenticationSecurityConfig;
import com.mallplus.oauth.openid.OpenIdAuthenticationSecurityConfig;
import com.mallplus.oauth.openid.member.OpenIdMemberAuthenticationSecurityConfig;
import com.mallplus.oauth2.common.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.annotation.Resource;

/**
 * spring security配置
 * 在WebSecurityConfigurerAdapter不拦截oauth要开放的资源
 * 
 * @author mall
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
@Import(DefaultPasswordConfig.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthenticationSuccessHandler authenticationSuccessHandler;
	@Autowired
	private AuthenticationFailureHandler authenticationFailureHandler;

	@Autowired(required = false)
	private AuthenticationEntryPoint authenticationEntryPoint;

	@Resource
	private UserDetailsService userDetailsService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Resource
	private LogoutHandler oauthLogoutHandler;

	@Autowired
	private SecurityProperties securityProperties;

	@Autowired
	private ValidateCodeSecurityConfig validateCodeSecurityConfig;

	@Autowired
	private OpenIdAuthenticationSecurityConfig openIdAuthenticationSecurityConfig;
	@Autowired
	private OpenIdMemberAuthenticationSecurityConfig openIdMemberAuthenticationSecurityConfig;
	@Autowired
	private MobileMemberAuthenticationSecurityConfig mobileMemberAuthenticationSecurityConfig;
	@Autowired
	private MobileAuthenticationSecurityConfig mobileAuthenticationSecurityConfig;
	@Autowired
	private MobileCodeMemberAuthenticationSecurityConfig mobileCodeMemberAuthenticationSecurityConfig;
	@Autowired
	private RestfulAccessDeniedHandler restfulAccessDeniedHandler;
	@Autowired
	private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
	/**
	 * 这一步的配置是必不可少的，否则SpringBoot会自动配置一个AuthenticationManager,覆盖掉内存中的用户
	 * @return 认证管理对象
	 */
	@Bean
    @Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		log.info("configure");
		httpSecurity.headers().cacheControl();

		/*httpSecurity.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);

		httpSecurity.exceptionHandling()
				.accessDeniedHandler(restfulAccessDeniedHandler)
				.authenticationEntryPoint(restAuthenticationEntryPoint);*/

		httpSecurity.authorizeRequests()
				.antMatchers( securityProperties.getIgnore().getUrls())
				.permitAll()
				.anyRequest().authenticated()
				.and()
				/*.formLogin()
				.loginPage(SecurityConstants.LOGIN_PAGE)
				.loginProcessingUrl(SecurityConstants.OAUTH_LOGIN_PRO_URL)
				.successHandler(authenticationSuccessHandler)
				.failureHandler(authenticationFailureHandler)
				.and()*/
				.logout()
				.logoutUrl(SecurityConstants.LOGOUT_URL)
				.logoutSuccessUrl(SecurityConstants.LOGIN_PAGE)
				.logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler())
				.addLogoutHandler(oauthLogoutHandler)
				.clearAuthentication(true)
				.and()
				.apply(validateCodeSecurityConfig)
				.and()
				.apply(openIdAuthenticationSecurityConfig)
				.and()
				.apply(openIdMemberAuthenticationSecurityConfig)
				.and()
				.apply(mobileCodeMemberAuthenticationSecurityConfig)
				.and()
				.apply(mobileAuthenticationSecurityConfig)
				.and()
				.apply(mobileMemberAuthenticationSecurityConfig)
				.and()
				.csrf().disable()
				// 解决不允许显示在iframe的问题
				.headers().frameOptions().disable().cacheControl();

		// 基于密码 等模式可以无session,不支持授权码模式
		if (authenticationEntryPoint != null) {
			httpSecurity.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);
			httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		} else {
			// 授权码模式单独处理，需要session的支持，此模式可以支持所有oauth2的认证
			httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
		}


	/*	httpSecurity.csrf()
				.disable()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.apply(openIdAuthenticationSecurityConfig)
				.and()
				.apply(openIdMemberAuthenticationSecurityConfig)
				.and()
				.apply(mobileAuthenticationSecurityConfig)
				.and()
				.apply(mobileMemberAuthenticationSecurityConfig)
				.and()
				.authorizeRequests()
				.antMatchers( securityProperties.getIgnore().getUrls())
				.permitAll()

				.permitAll()
				.antMatchers("/admin/login", "/admin/register","/api-ums/api/applet/login_by_weixin","/api/applet/login_by_weixin")// 对登录注册要允许匿名访问
				.permitAll()
				.antMatchers(HttpMethod.OPTIONS)
				.permitAll()
				.antMatchers("/**")
				.permitAll()
				.anyRequest()
				.authenticated();

		httpSecurity.headers().cacheControl();

		httpSecurity.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);

		httpSecurity.exceptionHandling()
				.accessDeniedHandler(restfulAccessDeniedHandler)
				.authenticationEntryPoint(restAuthenticationEntryPoint);*/
	}
/*
	@Bean
	public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
		return new JwtAuthenticationTokenFilter();
	}
*/

		/**
         * 全局用户信息
         */
	@Autowired
	public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}
}

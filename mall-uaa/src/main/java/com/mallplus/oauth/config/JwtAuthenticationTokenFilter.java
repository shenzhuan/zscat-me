package com.mallplus.oauth.config;


import com.mallplus.oauth.util.IpAddressUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT登录授权过滤器
 * https://github.com/shenzhuan/mallplus on 2018/4/26.
 */
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);
    @Resource
    private UserDetailsService userDetailsService;
    @Resource
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        long startTime, endTime;
        Map<String,String[]> params = new HashMap<String,String[]>(request.getParameterMap());

        StringBuffer sbParams = new StringBuffer();
        sbParams.append("?");

        for (String key : params.keySet()) {
            if(null == key || null == params.get(key) || null == params.get(key)[0]){  continue;}
            sbParams.append(key).append("=").append(params.get(key)[0]).append("&");
        }

        if(sbParams.length() > 1) {sbParams = sbParams.delete(sbParams.length() - 1, sbParams.length());}

        String fullUrl = ((HttpServletRequest)request).getRequestURL().toString();

        String authHeader = request.getHeader(this.tokenHeader);
        if (authHeader != null && authHeader.startsWith(this.tokenHead)) {


            String authToken = authHeader.substring(this.tokenHead.length());// The part after "Bearer "
            String username = jwtTokenUtil.getUserNameFromToken(authToken);
            LOGGER.info("checking username:{}", username);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    LOGGER.info("authenticated user:{}", username);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        startTime = System.currentTimeMillis();
        chain.doFilter(request, response);
        endTime = System.currentTimeMillis();
        String requestType = ((HttpServletRequest)request).getMethod();
if (fullUrl.contains(".css") || fullUrl.contains(".js")||fullUrl.contains(".png")||
        fullUrl.contains(".jpeg")||fullUrl.contains(".jpg")){

}else {
    logger.info(formMapKey(11, fullUrl, requestType,
            IpAddressUtil.getIpAddr((HttpServletRequest) request), sbParams.toString(), authHeader)
            + ",\"cost\":\"" + (endTime - startTime) + "ms\"");
}

    }
    private String formMapKey(Object userId, String mothedName, String requestType,
                              String ip, String params, String token) {
        return "\"time\"" + ":\"" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").format(new Date())
                + "\",\"name\"" + ":\"" + mothedName + "\",\"uid\":\"" + userId
                + "\",\"type\":\"" + requestType + "\",\"ip\":\"" + ip
                + "\",\"token\":\"" + token + "\",\"params\":\"" + params + "\"";
    }
}

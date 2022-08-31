package com.mallplus.oauth.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author mall
 * @date 2018/12/28
 */
public interface ZltUserDetailsService extends UserDetailsService {
    /**
     * 根据电话号码查询用户
     *
     * @param mobile
     * @return
     */
    UserDetails loadUserByMobile(String mobile);



    // 获取前台用户信息
    public UserDetails loadMemberByUsername(String username);

    public UserDetails loadMemberByOpenId(String openId);

    public UserDetails loadMemberByMobile(String mobile);




}

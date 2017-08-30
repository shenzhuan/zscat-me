package com.zscat.beetl.function;

import com.zscat.shop.util.SysUserUtils;
import org.springframework.stereotype.Service;

import com.zscat.conf.JbaseFunctionPackage;
import com.zscat.shop.model.Member;

@Service("auth")
public class AuthUserFunctions implements JbaseFunctionPackage{

	
	
	/**
	 * 登录用户
	* @return
	 */
	public Member getLoginUser(){
		return SysUserUtils.getSessionLoginUser();
	}
	
	
	
	/**
	 * 是否为超级管理员
	* @return
	 */
	public boolean isLogin(){
		return SysUserUtils.getSessionLoginUser()!=null;
	}
}

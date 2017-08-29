package com.zscat.blog.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.zscat.blog.api.model.Blogger;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;




public class SysUserUtils {


	public static final String SESSION_LOGIN_USER = "BLOG_USER";
	/**
	 * session中的用户
	 */
	public static Blogger getSessionLoginUser(){
		return (Blogger) getSession().getAttribute(SysUserUtils.SESSION_LOGIN_USER);
	}
	
	/**
	 * 得到当前session
	 */
	public static HttpSession getSession() {
		HttpSession session = getCurRequest().getSession();
		return session;
	}
	/**
	 * @Title: getCurRequest
	 * @Description:(获得当前的request) 
	 * @param:@return 
	 * @return:HttpServletRequest
	 */
	public static HttpServletRequest getCurRequest(){
		RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
		if(requestAttributes != null && requestAttributes instanceof ServletRequestAttributes){
			ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)requestAttributes;
			return servletRequestAttributes.getRequest();
		}
		return null;
	}
}

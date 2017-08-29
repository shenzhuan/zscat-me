package com.zscat.util;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.zscat.shop.model.Member;



/**
 * @ClassName:SysUserUtils
 * @date:2015年2月4日 下午8:12:41
 * @author  ?
 */
public class SysUserUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(SysUserUtils.class);
	public static final String SESSION_LOGIN_USER = "MEMBER_LOGIN";
	public static final Integer ORDER_TWO = 2;
	public static final Integer ORDER_NiNe = 9;
	public static final Integer ORDER_ONE = 1;

	/**
	 * 得到当前session
	 */
	public static HttpSession getSession() {
		HttpSession session = getCurRequest().getSession();
		return session;
	}
	
	/**
	 * session中的用户
	 */
	public static Member getSessionLoginUser(){
		return (Member) getSession().getAttribute(SysUserUtils.SESSION_LOGIN_USER);
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

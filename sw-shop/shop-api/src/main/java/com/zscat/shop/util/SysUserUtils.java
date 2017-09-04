package com.zscat.shop.util;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.zsCat.common.utils.JSONSerializerUtil;
import com.zsCat.common.utils.RedisUtils;
import com.zscat.shop.model.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


/**
 * @ClassName:SysUserUtils
 * @date:2015年2月4日 下午8:12:41
 * @author  ?
 */
public class SysUserUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(SysUserUtils.class);
	public static String SESSION_LOGIN_USER ="loginMember";
	public static Integer ORDER_ONE=1; // 微未支付
	public static Integer ORDER_TWO=2; // 已支付
	public static Integer ORDER_NiNe=9;// 已取消
	static RedisUtils redisUtils = new RedisUtils();

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
	 * session中的用户
	 */
	public static Member getSessionLoginUser(HttpServletRequest request){
		System.out.println("getSessionLoginUser--sessionId:"+request.getSession().getId());
		byte[] userBytes = redisUtils.getByte(request.getSession().getId());
		Member user = null;
		if (null != userBytes) {
			user = JSONSerializerUtil.unserialize(userBytes, Member.class);
		}
		System.out.println("getSessionLoginUser--user:"+user);
		return user;
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

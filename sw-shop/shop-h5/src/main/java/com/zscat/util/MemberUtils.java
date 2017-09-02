package com.zscat.util;

import com.zsCat.common.utils.JSONSerializerUtil;
import com.zscat.shop.model.Member;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.zscat.shop.util.SysUserUtils;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2017/9/2 0002.
 */
public class MemberUtils
{
    /**
     * session中的用户
     */
    public static Member getSessionLoginUser(){
        return SysUserUtils.getSessionLoginUser(HttpKit.getRequest());
    }
}

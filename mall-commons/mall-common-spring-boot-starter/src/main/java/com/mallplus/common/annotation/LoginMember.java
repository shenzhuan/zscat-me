package com.mallplus.common.annotation;

import java.lang.annotation.*;

/**
 * 请求的方法参数SysUser上添加该注解，则注入当前登录人信息
 * 例1：public void test(@LoginUser SysUser ums) //只有username 和 roles
 * 例2：public void test(@LoginUser(isFull = true) SysUser ums) //能获取SysUser对象的所有信息
 *
 * @author mall
 * @date 2018/7/24 16:44
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoginMember {
    /**
     * 是否查询SysUser对象所有信息，true则通过rpc接口查询
     */
    boolean isFull() default false;
}

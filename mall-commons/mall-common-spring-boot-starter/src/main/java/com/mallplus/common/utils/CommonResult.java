package com.mallplus.common.utils;

import org.springframework.validation.BindingResult;

/**
 * 通用返回对象
 * Created by zscat on 2018/4/26.
 */
public class CommonResult {
    //操作成功
    public static final int SUCCESS = 200;
    //操作失败
    public static final int FAILED = 500;
    //参数校验失败
    public static final int VALIDATE_FAILED = 404;
    //未认证
    public static final int UNAUTHORIZED = 401;
    //未授权
    public static final int FORBIDDEN = 403;
    private int code;
    private String msg;
    private Object data;

    /**
     * 普通成功返回
     *
     * @param data 获取的数据
     */
    public CommonResult success(Object data) {
        this.code = SUCCESS;
        this.msg = "操作成功";
        this.data = data;
        return this;
    }
    /**
     * 普通成功返回
     *
     */
    public CommonResult success() {
        this.code = SUCCESS;
        this.msg = "操作成功";
        this.data =  "操作成功";
        return this;
    }
    /**
     * 普通成功返回
     */
    public CommonResult success(String msg, Object data) {
        this.code = SUCCESS;
        this.msg = msg;
        this.data = data;
        return this;
    }


    /**
     * 普通失败提示信息
     */
    public CommonResult failed() {
        this.code = FAILED;
        this.msg = "操作失败";
        return this;
    }

    public CommonResult failed(String msg) {
        this.code = FAILED;
        this.msg = msg;
        return this;
    }

    /**
     * 参数验证失败使用
     *
     * @param msg 错误信息
     */
    public CommonResult validateFailed(String msg) {
        this.code = VALIDATE_FAILED;
        this.msg = msg;
        return this;
    }

    /**
     * 未登录时使用
     *
     * @param msg 错误信息
     */
    public CommonResult unauthorized(String msg) {
        this.code = UNAUTHORIZED;
        this.msg = "暂未登录或token已经过期";
        this.data = msg;
        return this;
    }

    /**
     * 未授权时使用
     *
     * @param msg 错误信息
     */
    public CommonResult forbidden(String msg) {
        this.code = FORBIDDEN;
        this.msg = "没有相关权限";
        this.data = msg;
        return this;
    }

    /**
     * 参数验证失败使用
     *
     * @param result 错误信息
     */
    public CommonResult validateFailed(BindingResult result) {
        validateFailed(result.getFieldError().getDefaultMessage());
        return this;
    }
    /**
     * 普通失败提示信息
     */
    public CommonResult paramFailed() {
        this.code = FAILED;
        this.msg = "参数失败";
        return this;
    }

    public CommonResult paramFailed(String msg) {
        this.code = FAILED;
        this.msg = msg;
        return this;
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getmsg() {
        return msg;
    }

    public void setmsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


}

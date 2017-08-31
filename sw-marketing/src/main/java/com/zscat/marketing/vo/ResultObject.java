/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zscat.marketing.vo;

import java.io.Serializable;

/**
 * 返回结果类
 * <p>
 * <p>
 * 统一的json返回结果</p>
 * <p>
 * 主要用于:</p>
 * <ol>
 * <li>api调用</li>
 * <li>ajax调用</li>
 * </ol>
 *
 * @author :  Huzi.Wang [huzi.wh@gmail.com]
 * @version :  1.0
 * @created on  : 2017/6/27  上午12:43
 * @copyright :  Copyright(c) 2013 http://imhuzi.net
 */

public class ResultObject implements Serializable {

    private static final long serialVersionUID = 1L;
    // success [true|false] default true
    private boolean success = true;
    // 提示信息
    private String msg;
    // 代码, 后面需要对没一个错误进行编码
    int code;
    // 返回时携带的数据
    private Object data;

    public ResultObject() {
    }

    public ResultObject(String msg) {
        this.msg = msg;
    }

    public ResultObject(Object data) {
        this.data = data;
    }

    public ResultObject(boolean result, String msg) {
        this.success = result;
        this.msg = msg;
    }

    public ResultObject(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultObj{" + "result=" + success + ", msg=" + msg + ", code=" + code + ", data=" + data + '}';
    }


}

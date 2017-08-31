package com.zscat.marketing.wx.handler;

import com.google.gson.Gson;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 消息处理Handler的父类
 *
 * @author : zscat
 * @version : 1.0
 * @created on  : 2016/11/24  下午11:28
 */
public abstract class AbstractHandler implements WxMpMessageHandler {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    protected final Gson gson = new Gson();
}

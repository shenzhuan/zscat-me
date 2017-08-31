package com.zscat.marketing.wx.service.impl;

import com.zscat.marketing.wx.handler.LogHandler;
import com.zscat.marketing.wx.handler.MsgHandler;
import com.zscat.marketing.wx.handler.SubscribeHandler;
import com.zscat.marketing.wx.handler.UnSubscribeHandler;
import com.zscat.marketing.wx.service.IWxServerService;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 微信核心业务实现
 *
 * @author : zscat
 * @version : 1.0
 * @created on  : 2016/11/24  下午10:01
 */
@Service
public class WxServerService implements IWxServerService {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    protected WxMpService wxMpService;

    @Resource
    protected LogHandler logHandler;

    @Resource
    protected SubscribeHandler subscribeHandler;

    @Resource
    protected UnSubscribeHandler unSubscribeHandler;

    @Resource
    protected MsgHandler msgHandler;

    private WxMpMessageRouter router;


    @PostConstruct
    public void init() {
        this.refreshRouter();
    }

    @Override
    public void requestGet(String urlWithParams) throws IOException {
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpGet httpget = new HttpGet(urlWithParams);
        httpget.addHeader("Content-Type", "text/html;charset=UTF-8");

        //配置请求的超时设置
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(50)
                .setConnectTimeout(50)
                .setSocketTimeout(50).build();
        httpget.setConfig(requestConfig);

        CloseableHttpResponse response = httpclient.execute(httpget);

        logger.info("requestGet->StatusCode:{}",response.getStatusLine().getStatusCode());

        HttpEntity entity = response.getEntity();
        String jsonStr = EntityUtils.toString(entity);
        logger.info("requestGet->jsonStr:{}");

        httpget.releaseConnection();
    }

    @Override
    public void requestPost(String url, List<NameValuePair> params) throws ClientProtocolException, IOException {
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();

        HttpPost httppost = new HttpPost(url);
        httppost.setEntity(new UrlEncodedFormEntity(params, StandardCharsets.UTF_8));

        CloseableHttpResponse response = httpclient.execute(httppost);
        logger.info("requestPost->response:{}",response);

        HttpEntity entity = response.getEntity();
        String jsonStr = EntityUtils.toString(entity, "utf-8");
        logger.info("requestPost->jsonStr:{}",jsonStr);

        httppost.releaseConnection();
    }

    @Override
    public void refreshRouter() {
        final WxMpMessageRouter newRouter = new WxMpMessageRouter(
                this.wxMpService);

        // 记录所有事件的日志
        newRouter.rule().handler(this.logHandler).next();

        // 关注事件
        newRouter.rule().async(false)
                .msgType(WxConsts.XML_MSG_EVENT)
                .event(WxConsts.EVT_SUBSCRIBE).handler(this.subscribeHandler).end();

        //取消关注
        newRouter.rule().async(false)
                .msgType(WxConsts.XML_MSG_EVENT)
                .event(WxConsts.EVT_UNSUBSCRIBE).handler(this.unSubscribeHandler).end();

        // 默认,转发消息给客服人员
        newRouter.rule().async(false).handler(this.msgHandler).end();

        this.router = newRouter;
    }

    @Override
    public WxMpXmlOutMessage route(WxMpXmlMessage inMessage) {
        try {
            return this.router.route(inMessage);
        } catch (Exception e) {
            this.logger.error(e.getMessage(), e);
        }

        return null;
    }

    @Override
    public WxMpUser getUserInfo(String openid, String lang) {
        WxMpUser wxMpUser = null;
        try {
            wxMpUser = this.wxMpService.getUserService().userInfo(openid, lang);
        } catch (WxErrorException e) {
            this.logger.error(e.getError().toString());
        }
        return wxMpUser;
    }

    /**
     * 初始化微信公众号菜单
     */
    @Override
    public void initMenu() {
    }

}

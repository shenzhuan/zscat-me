package com.zscat.marketing.wx.handler;

import com.zscat.marketing.wx.service.IWxServerService;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 微信用户取消关注公众号时需要标记
 *
 * @author : zscat
 * @version : 1.0
 * @created on  : 2016/11/25  上午12:09
 */
@Component
public class UnSubscribeHandler extends AbstractHandler {

    @Resource
    protected IWxServerService wxCoreService;


    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> map, WxMpService wxMpService,
                                    WxSessionManager wxSessionManager) throws WxErrorException {

        WxMpUser wxMpUser = wxCoreService.getUserInfo(wxMessage.getFromUser(), "zh_CN");
        logger.info("user:{}", wxMpUser);

        return null;
    }
}

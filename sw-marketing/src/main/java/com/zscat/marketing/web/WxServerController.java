package com.zscat.marketing.web;

import com.zscat.marketing.config.WxConfigProperties;
import com.zscat.marketing.constant.Constant;
import com.zscat.marketing.wx.service.impl.WxServerService;
import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.bean.menu.WxMenuButton;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 * 微信服务器接入接口
 *
 * @author : zscat
 * @version : 1.0
 * @created on  : 2016/11/24  下午10:00
 */
@Controller
@RequestMapping(Constant.WX_SERVER_URI)
public class WxServerController extends BaseController {

    @Resource
    protected WxMpConfigStorage configStorage;

    @Resource
    protected WxMpService wxMpService;

    @Resource
    protected WxConfigProperties wxConfigProperties;

    @Resource
    protected WxServerService wxServerService;


    /**
     * 微信公众号webservice主服务接口，提供与微信服务器的信息交互
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = {"index"})
    public void wechatCore(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

        String signature = request.getParameter("signature");
        String nonce = request.getParameter("nonce");
        String timestamp = request.getParameter("timestamp");

        if (!this.wxMpService.checkSignature(timestamp, nonce, signature)) {
            // 消息签名不正确，说明不是公众平台发过来的消息
            response.getWriter().println("非法请求");
            return;
        }

        String echoStr = request.getParameter("echostr");
        if (StringUtils.isNotBlank(echoStr)) {
            // 说明是一个仅仅用来验证的请求，回显echostr
            String echoStrOut = String.copyValueOf(echoStr.toCharArray());
            logger.info("微信验证成功！回显echostr=" + echoStrOut);
            response.getWriter().println(echoStrOut);
            return;
        }

        String encryptType = StringUtils.isBlank(request.getParameter("encrypt_type"))
                ? "raw"
                : request.getParameter("encrypt_type");

        if ("raw".equals(encryptType)) {
            // 明文传输的消息
            WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(request.getInputStream());
            WxMpXmlOutMessage outMessage = this.wxServerService.route(inMessage);
            if (outMessage != null) {
                response.getWriter().write(outMessage.toXml());
            }
            return;
        }

        if ("aes".equals(encryptType)) {
            // 是aes加密的消息
            String msgSignature = request.getParameter("msg_signature");
            WxMpXmlMessage inMessage = WxMpXmlMessage.fromEncryptedXml(
                    request.getInputStream(), this.configStorage, timestamp, nonce,
                    msgSignature);
            this.logger.debug("\n消息解密后内容为：\n{} ", inMessage.toString());
            WxMpXmlOutMessage outMessage = this.wxServerService.route(inMessage);
            this.logger.info(response.toString());
            response.getWriter()
                    .write(outMessage.toEncryptedXml(this.configStorage));
            return;
        }

        response.getWriter().println("不可识别的加密类型");
        return;
    }

    @RequestMapping(value = {"/createAppMenu"})
    @ResponseBody
    public String createAppMenu() {
        try {
            wxMpService.getMenuService().menuDelete();
            String appDomain = wxConfigProperties.getPageDomain();

            WxMenu menu = new WxMenu();
            WxMenuButton button2 = new WxMenuButton();
            button2.setType("click");
            button2.setName("营销后台");
            button2.setKey("to_kefu");
            button2.setUrl(appDomain + Constant.WX_H5_URI + "user/tginfo");
            WxMenuButton button3 = new WxMenuButton();
            button3.setName("推广计划");


            ArrayList<WxMenuButton> promotionplanbtns = new ArrayList<>();

            WxMenuButton button4 = new WxMenuButton();
            button4.setType("view");
            button4.setName("推广后台");
            button4.setKey("to_manager");
            button4.setUrl(appDomain + Constant.WX_H5_URI + "user/tginfo");


            WxMenuButton button5 = new WxMenuButton();
            button5.setType("view");
            button5.setName("推广计划");
            button5.setKey("to_promotionplan");
            button5.setUrl("http://www.if-chat.com");

            promotionplanbtns.add(button4);
            promotionplanbtns.add(button5);
            button3.setSubButtons(promotionplanbtns);


            WxMenuButton button6 = new WxMenuButton();
            button6.setType("view");
            button6.setName("下载app");
            button6.setKey("to_downapp");
            button6.setUrl("http://www.if-chat.com");


            ArrayList<WxMenuButton> btns = new ArrayList<>();
            btns.add(button2);
            btns.add(button3);
            btns.add(button6);
            menu.setButtons(btns);
            wxMpService.getMenuService().menuCreate(menu);
            logger.info("----create menu");
            return "ok";
        } catch (WxErrorException e) {
            this.logger.error("公众号创建菜单失败", e);
            return "fail";
        }
    }

}

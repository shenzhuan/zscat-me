package com.mallplus.order.pay.controller.wxpay;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import com.mallplus.common.constant.OrderStatus;
import com.mallplus.common.entity.oms.OmsOrder;
import com.mallplus.common.entity.oms.OmsPayments;
import com.mallplus.common.entity.ums.SysAppletSet;
import com.mallplus.common.entity.ums.UmsMember;
import com.mallplus.common.exception.ApiMallPlusException;
import com.mallplus.common.feign.MemberFeignClient;
import com.mallplus.common.utils.CommonResult;
import com.mallplus.common.utils.JsonUtil;
import com.mallplus.common.utils.ValidatorUtils;
import com.mallplus.core.enums.SignType;
import com.mallplus.core.enums.TradeType;
import com.mallplus.core.kit.HttpKit;
import com.mallplus.core.kit.QrCodeKit;
import com.mallplus.core.kit.RsaKit;
import com.mallplus.core.kit.WxPayKit;
import com.mallplus.order.config.WxAppletProperties;
import com.mallplus.order.pay.entity.*;
import com.mallplus.order.pay.entity.WxPayBean;
import com.mallplus.order.pay.kit.IpKit;
import com.mallplus.order.pay.vo.AjaxResult;
import com.mallplus.order.service.IOmsOrderItemService;
import com.mallplus.order.service.IOmsOrderService;
import com.mallplus.order.service.IOmsPaymentsService;
import com.mallplus.wxpay.WxPayApi;
import com.mallplus.wxpay.WxPayApiConfig;
import com.mallplus.wxpay.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>IJPay 让支付触手可及，封装了微信支付、支付宝支付、银联支付常用的支付方式以及各种常用的接口。</p>
 *
 * <p>不依赖任何第三方 mvc 框架，仅仅作为工具使用简单快速完成支付模块的开发，可轻松嵌入到任何系统里。 </p>
 *
 * <p>IJPay 交流群: 723992875</p>
 *
 * <p>Node.js 版: https://gitee.com/javen205/TNW</p>
 *
 * <p>微信支付 Demo</p>
 *
 * @author Javen
 */
@RestController
@RequestMapping("api/wxPay")
public class WxPayController extends AbstractWxPayApiController {
    private static final String USER_PAYING = "USERPAYING";
    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Resource
    private IOmsOrderService orderService;

    @Resource
    private IOmsOrderItemService orderItemService;
    @Autowired
    private IOmsPaymentsService paymentsService;
    @Resource
    private MemberFeignClient memberFeignClient;
    @Autowired
    private WxAppletProperties wxAppletProperties;

    private String notifyUrl = "http://java.chengguo.link:8081/api";
    private String refundNotifyUrl;

    @Override
    public WxPayApiConfig getApiConfig() {
        WxPayApiConfig apiConfig = null;

        try {
            OmsPayments payments = paymentsService.getById(1);
            if (payments != null) {
                WxPayBean wxPayBean = JsonUtil.jsonToPojo(payments.getParams(), WxPayBean.class);
                apiConfig = WxPayApiConfig.builder()
                        .appId(wxPayBean.getAppId())
                        .mchId(wxPayBean.getMchId())
                        .partnerKey(wxPayBean.getPartnerKey())
                        .certPath(wxPayBean.getCertPath())
                        .domain(wxPayBean.getDomain())
                        .build();
            }
            notifyUrl = apiConfig.getDomain().concat("/wxPay/payNotify");
            refundNotifyUrl = apiConfig.getDomain().concat("/wxPay/refundNotify");
        } catch (Exception e) {
            WxPayBean wxPayBean = new WxPayBean();
            wxPayBean.setAppId("wx8321531c6046c924");
            wxPayBean.setMchId("1533901051");
            wxPayBean.setDomain("http://www.yjlive.cn/api/api");
            wxPayBean.setPartnerKey("shen9136shen9136shen9136shen9136");
            apiConfig = WxPayApiConfig.builder()
                    .appId(wxPayBean.getAppId())
                    .mchId(wxPayBean.getMchId())
                    .partnerKey(wxPayBean.getPartnerKey())
                    .certPath(wxPayBean.getCertPath())
                    .domain(wxPayBean.getDomain())
                    .build();

            notifyUrl = apiConfig.getDomain().concat("/wxPay/payNotify");
            refundNotifyUrl = apiConfig.getDomain().concat("/wxPay/refundNotify");
        }

        return apiConfig;
    }

    public WxPayApiConfig getApiConfig1() {
        WxPayApiConfig apiConfig = null;

        try {
            /*SysAppletSet appletSet = appletSetMapper.selectOne(new QueryWrapper<>());
            if (null == appletSet) {
                throw new ApiMallPlusException("没有设置支付配置");
            }
            if (appletSet != null) {
                apiConfig = WxPayApiConfig.builder()
                        .appId(appletSet.getAppid())
                        .mchId(appletSet.getMchid())
                        .partnerKey(appletSet.getPaySignKey())
                        .certPath(appletSet.getCertname())
                        .domain(appletSet.getNotifyurl())
                        .build();
            }*/
            notifyUrl = apiConfig.getDomain().concat("/wxPay/payNotify");
            refundNotifyUrl = apiConfig.getDomain().concat("/wxPay/refundNotify");
        } catch (Exception e) {

        }

        return apiConfig;
    }

    @GetMapping("/getKey")
    @ResponseBody
    public void getKey() {

        // return new CommonResult().success(WxPayApi.getSignKey(wxPayBean.getMchId(), wxPayBean.getPartnerKey(), SignType.MD5));
    }

    /**
     * 微信H5 支付
     * 注意：必须再web页面中发起支付且域名已添加到开发配置中
     */
    @RequestMapping(value = "/wapPay", method = {RequestMethod.POST, RequestMethod.GET})
    public Object wapPay(HttpServletRequest request, @RequestParam(value = "orderId", required = false) Long orderId) throws IOException {
        try {
            String ip = IpKit.getRealIp(request);
            if (ValidatorUtils.empty(ip)) {
                ip = "127.0.0.1";
            }

            OmsOrder orderInfo = orderService.getById(orderId);
            if (null == orderInfo) {
                return new CommonResult().failed("订单已取消");
            }
            if (orderInfo.getStatus() == OrderStatus.CLOSED.getValue()) {
                return new CommonResult().failed("订单已已关闭，请不要重复操作");
            }
            if (orderInfo.getStatus() != OrderStatus.INIT.getValue()) {
                return new CommonResult().failed("订单已支付，请不要重复操作");
            }

            H5SceneInfo sceneInfo = new H5SceneInfo();
            WxPayApiConfig wxPayApiConfig = this.getApiConfig1();

            H5SceneInfo.H5 h5_info = new H5SceneInfo.H5();
            h5_info.setType("Wap");
            //此域名必须在商户平台--"产品中心"--"开发配置"中添加
            h5_info.setWap_url(wxPayApiConfig.getDomain());
            h5_info.setWap_name("IJPay VIP 充值");
            sceneInfo.setH5Info(h5_info);


            Map<String, String> params = UnifiedOrderModel
                    .builder()
                    .appid(wxPayApiConfig.getAppId())
                    .mch_id(wxPayApiConfig.getMchId())
                    .nonce_str(WxPayKit.generateStr())
                    .body("mallplus  微信H5 支付")
                    .attach(orderInfo.getOrderSn())
                    .out_trade_no(orderInfo.getOrderSn())
                    .total_fee(orderInfo.getPayAmount().multiply(new BigDecimal(100)).intValue() + "")
                    .spbill_create_ip(ip)
                    .notify_url(notifyUrl)
                    .trade_type(TradeType.MWEB.getTradeType())
                    .scene_info(JSON.toJSONString(sceneInfo))
                    .build()
                    .createSign(wxPayApiConfig.getPartnerKey(), SignType.HMACSHA256);
            log.info("params:" + params.toString());
            String xmlResult = WxPayApi.pushOrder(false, params);
            log.info("xmlResult:" + xmlResult);

            Map<String, String> result = WxPayKit.xmlToMap(xmlResult);

            String return_code = result.get("return_code");
            String return_msg = result.get("return_msg");
            if (!WxPayKit.codeIsOk(return_code)) {
                throw new RuntimeException(return_msg);
            }
            String result_code = result.get("result_code");
            if (!WxPayKit.codeIsOk(result_code)) {
                throw new RuntimeException(return_msg);
            }
            // 以下字段在return_code 和result_code都为SUCCESS的时候有返回

            String prepayId = result.get("prepay_id");
            String webUrl = result.get("mweb_url");

            log.info("prepay_id:" + prepayId + " mweb_url:" + webUrl);
            //   response.sendRedirect(webUrl);
            return new CommonResult().success(webUrl);
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResult().failed(e.getMessage());
        }

    }

    /**
     * openId，采用 网页授权获取 access_token API：SnsAccessTokenApi获取
     */
    @RequestMapping(value = "/authCodeToOpenid", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Object authCodeToOpenid(@RequestParam(value = "wxH5Appid", required = true) String wxH5Appid,
                                   @RequestParam(value = "wxH5Secret", required = true) String wxH5Secret,
                                   @RequestParam(value = "code", required = true) String code) {
        return new CommonResult().success();
    }

    /**
     * 公众号支付
     */
    @RequestMapping(value = "/webPay", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Object webPay(HttpServletRequest request, @RequestParam(value = "orderId", required = true) Long orderId
            , @RequestParam(value = "memberId", required = true) Long memberId) {
        try {
            String totalFee = "0.01";
            // openId，采用 网页授权获取 access_token API：SnsAccessTokenApi获取
            UmsMember member = memberFeignClient.findById(memberId);
            String openId = member.getWeixinOpenid();
            if (StrUtil.isEmpty(openId)) {
                return new CommonResult().failed("openId is null");
            }
            if (StrUtil.isEmpty(totalFee)) {
                return new CommonResult().failed("请输入数字金额");
            }
            String ip = IpKit.getRealIp(request);
            if (StrUtil.isEmpty(ip)) {
                ip = "127.0.0.1";
            }

            OmsOrder orderInfo = orderService.getById(orderId);
            if (null == orderInfo) {
                return new CommonResult().failed("订单已取消");
            }
            if (orderInfo.getStatus() == OrderStatus.CLOSED.getValue()) {
                return new CommonResult().failed("订单已已关闭，请不要重复操作");
            }
            if (orderInfo.getStatus() != OrderStatus.INIT.getValue()) {
                return new CommonResult().failed("订单已支付，请不要重复操作");
            }
            System.out.println(orderInfo.getPayAmount().multiply(new BigDecimal(100)).toPlainString());

            WxPayApiConfig wxPayApiConfig = this.getApiConfig();
            Map<String, String> params = UnifiedOrderModel
                    .builder()
                    .appid(wxPayApiConfig.getAppId())
                    .mch_id(wxPayApiConfig.getMchId())
                    .nonce_str(WxPayKit.generateStr())
                    .body("mallplus 公众号支付") // IJPay 让支付触手可及-公众号支付
                    .attach(orderInfo.getOrderSn())
                    .out_trade_no(orderInfo.getOrderSn())
                    .total_fee(orderInfo.getPayAmount().multiply(new BigDecimal(100)).intValue() + "")
                    .spbill_create_ip(ip)
                    .notify_url(notifyUrl)
                    .trade_type(TradeType.JSAPI.getTradeType())
                    .openid(openId)
                    .build()
                    .createSign(wxPayApiConfig.getPartnerKey(), SignType.HMACSHA256);

            String xmlResult = WxPayApi.pushOrder(false, params);
            log.info("xmlResult:" + xmlResult);

            Map<String, String> resultMap = WxPayKit.xmlToMap(xmlResult);
            String returnCode = resultMap.get("return_code");
            String returnMsg = resultMap.get("return_msg");
            if (!WxPayKit.codeIsOk(returnCode)) {
                return new CommonResult().failed(returnMsg);
            }
            String resultCode = resultMap.get("result_code");
            if (!WxPayKit.codeIsOk(resultCode)) {
                return new CommonResult().failed(returnMsg);
            }

            // 以下字段在 return_code 和 result_code 都为 SUCCESS 的时候有返回

            String prepayId = resultMap.get("prepay_id");

            Map<String, String> packageParams = WxPayKit.prepayIdCreateSign(prepayId, wxPayApiConfig.getAppId(),
                    wxPayApiConfig.getPartnerKey(), SignType.HMACSHA256);

            String jsonStr = JSON.toJSONString(packageParams);
            return new CommonResult().success(jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResult().failed(e.getMessage());
        }

    }

    /**
     * 扫码模式一
     */
    @RequestMapping(value = "/scanCode1", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Object scanCode1(HttpServletRequest request, HttpServletResponse response,
                            @RequestParam("productId") String productId) {
        try {
            if (ValidatorUtils.empty(productId)) {
                return new CommonResult().failed("productId is null");
            }
            WxPayApiConfig config = this.getApiConfig();
            //获取扫码支付（模式一）url
            String qrCodeUrl = WxPayKit.bizPayUrl(config.getPartnerKey(), config.getAppId(), config.getMchId(), productId);
            log.info(qrCodeUrl);
            //生成二维码保存的路径
            String name = "payQRCode1.png";
            log.info(ResourceUtils.getURL("classpath:").getPath());
            Boolean encode = QrCodeKit.encode(qrCodeUrl, BarcodeFormat.QR_CODE, 3, ErrorCorrectionLevel.H,
                    "png", 200, 200,
                    ResourceUtils.getURL("classpath:").getPath().concat("static").concat(File.separator).concat(name));
            if (encode) {
                //在页面上显示
                return new AjaxResult().success(name);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResult().failed("系统异常：" + e.getMessage());
        }
        return null;
    }

    /**
     * 扫码支付模式一回调
     */
    @RequestMapping(value = "/scanCodeNotify", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String scanCodeNotify(HttpServletRequest request, HttpServletResponse response) {
        try {
            String result = HttpKit.readData(request);
            log.info("scanCodeNotify>>>" + result);
            /**
             * 获取返回的信息内容中各个参数的值
             */
            Map<String, String> map = WxPayKit.xmlToMap(result);
            for (String key : map.keySet()) {
                log.info("key= " + key + " and value= " + map.get(key));
            }

            String appId = map.get("appid");
            String openId = map.get("openid");
            String mchId = map.get("mch_id");
            String isSubscribe = map.get("is_subscribe");
            String nonceStr = map.get("nonce_str");
            String productId = map.get("product_id");
            String sign = map.get("sign");

            Map<String, String> packageParams = new HashMap<String, String>(6);
            packageParams.put("appid", appId);
            packageParams.put("openid", openId);
            packageParams.put("mch_id", mchId);
            packageParams.put("is_subscribe", isSubscribe);
            packageParams.put("nonce_str", nonceStr);
            packageParams.put("product_id", productId);

            WxPayApiConfig wxPayApiConfig = this.getApiConfig();

            String packageSign = WxPayKit.createSign(packageParams, wxPayApiConfig.getPartnerKey(), SignType.MD5);

            String ip = IpKit.getRealIp(request);
            if (ValidatorUtils.empty(ip)) {
                ip = "127.0.0.1";
            }
            Map<String, String> params = UnifiedOrderModel
                    .builder()
                    .appid(wxPayApiConfig.getAppId())
                    .mch_id(wxPayApiConfig.getMchId())
                    .nonce_str(WxPayKit.generateStr())
                    .body("IJPay 让支付触手可及-扫码支付模式一")
                    .attach("Node.js 版:https://gitee.com/javen205/TNW")
                    .out_trade_no(WxPayKit.generateStr())
                    .total_fee("1")
                    .spbill_create_ip(ip)
                    .notify_url(notifyUrl)
                    .trade_type(TradeType.NATIVE.getTradeType())
                    .openid(openId)
                    .build()
                    .createSign(wxPayApiConfig.getPartnerKey(), SignType.HMACSHA256);
            String xmlResult = WxPayApi.pushOrder(false, params);
            log.info("统一下单:" + xmlResult);
            /**
             * 发送信息给微信服务器
             */
            Map<String, String> payResult = WxPayKit.xmlToMap(xmlResult);
            String returnCode = payResult.get("return_code");
            String resultCode = payResult.get("result_code");
            if (WxPayKit.codeIsOk(returnCode) && WxPayKit.codeIsOk(resultCode)) {
                // 以下字段在 return_code 和 result_code 都为 SUCCESS 的时候有返回
                String prepayId = payResult.get("prepay_id");

                Map<String, String> prepayParams = new HashMap<String, String>(10);
                prepayParams.put("return_code", "SUCCESS");
                prepayParams.put("appid", appId);
                prepayParams.put("mch_id", mchId);
                prepayParams.put("nonceStr", System.currentTimeMillis() + "");
                prepayParams.put("prepay_id", prepayId);
                String prepaySign = null;
                if (sign.equals(packageSign)) {
                    prepayParams.put("result_code", "SUCCESS");
                } else {
                    prepayParams.put("result_code", "FAIL");
                    //result_code为FAIL时，添加该键值对，value值是微信告诉客户的信息
                    prepayParams.put("err_code_des", "订单失效");
                }
                prepaySign = WxPayKit.createSign(prepayParams, wxPayApiConfig.getPartnerKey(), SignType.HMACSHA256);
                prepayParams.put("sign", prepaySign);
                String xml = WxPayKit.toXml(prepayParams);
                log.error(xml);
                return xml;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 扫码支付模式二
     */
    @RequestMapping(value = "/scanCode2", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Object scanCode2(HttpServletRequest request, HttpServletResponse response,
                            @RequestParam("total_fee") String totalFee) {

        if (ValidatorUtils.empty(totalFee)) {
            return new CommonResult().failed("支付金额不能为空");
        }

        String ip = IpKit.getRealIp(request);
        if (ValidatorUtils.empty(ip)) {
            ip = "127.0.0.1";
        }
        WxPayApiConfig wxPayApiConfig = this.getApiConfig();

        Map<String, String> params = UnifiedOrderModel
                .builder()
                .appid(wxPayApiConfig.getAppId())
                .mch_id(wxPayApiConfig.getMchId())
                .nonce_str(WxPayKit.generateStr())
                .body("IJPay 让支付触手可及-扫码支付模式二")
                .attach("Node.js 版:https://gitee.com/javen205/TNW")
                .out_trade_no(WxPayKit.generateStr())
                .total_fee("1")
                .spbill_create_ip(ip)
                .notify_url(notifyUrl)
                .trade_type(TradeType.NATIVE.getTradeType())
                .build()
                .createSign(wxPayApiConfig.getPartnerKey(), SignType.HMACSHA256);

        String xmlResult = WxPayApi.pushOrder(false, params);
        log.info("统一下单:" + xmlResult);

        Map<String, String> result = WxPayKit.xmlToMap(xmlResult);

        String returnCode = result.get("return_code");
        String returnMsg = result.get("return_msg");
        System.out.println(returnMsg);
        if (!WxPayKit.codeIsOk(returnCode)) {
            return new CommonResult().failed("error:" + returnMsg);
        }
        String resultCode = result.get("result_code");
        if (!WxPayKit.codeIsOk(resultCode)) {
            return new CommonResult().failed("error:" + returnMsg);
        }
        //生成预付订单success

        String qrCodeUrl = result.get("code_url");
        String name = "payQRCode2.png";

        Boolean encode = QrCodeKit.encode(qrCodeUrl, BarcodeFormat.QR_CODE, 3, ErrorCorrectionLevel.H, "png", 200, 200,
                request.getSession().getServletContext().getRealPath("/") + File.separator + name);
        if (encode) {
            //在页面上显示
            return new AjaxResult().success(name);
        }
        return null;
    }

    /**
     * 刷卡支付
     */
    @RequestMapping(value = "/micropay", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Object microPay(HttpServletRequest request, HttpServletResponse response) {
        String authCode = request.getParameter("auth_code");
        String totalFee = request.getParameter("total_fee");
        if (ValidatorUtils.empty(totalFee)) {
            return new CommonResult().failed("支付金额不能为空");
        }
        if (ValidatorUtils.empty(authCode)) {
            return new CommonResult().failed("auth_code参数错误");
        }
        String ip = IpKit.getRealIp(request);
        if (ValidatorUtils.empty(ip)) {
            ip = "127.0.0.1";
        }
        WxPayApiConfig wxPayApiConfig = this.getApiConfig();

        Map<String, String> params = MicroPayModel.builder()
                .appid(wxPayApiConfig.getAppId())
                .mch_id(wxPayApiConfig.getMchId())
                .nonce_str(WxPayKit.generateStr())
                .body("IJPay 让支付触手可及-刷卡支付")
                .attach("Node.js 版:https://gitee.com/javen205/TNW")
                .out_trade_no(WxPayKit.generateStr())
                .total_fee("1")
                .spbill_create_ip(ip)
                .auth_code(authCode)
                .build()
                .createSign(wxPayApiConfig.getPartnerKey(), SignType.HMACSHA256);

        String xmlResult = WxPayApi.microPay(false, params);
        //同步返回结果
        log.info("xmlResult:" + xmlResult);
        Map<String, String> result = WxPayKit.xmlToMap(xmlResult);
        String returnCode = result.get("return_code");
        String returnMsg = result.get("return_msg");
        if (!WxPayKit.codeIsOk(returnCode)) {
            //通讯失败
            String errCode = result.get("err_code");
            if (ValidatorUtils.notEmpty(errCode)) {
                //用户支付中，需要输入密码
                if (USER_PAYING.equals(errCode)) {
                    //等待5秒后调用【查询订单API】
                }
            }
            log.info("提交刷卡支付失败>>" + xmlResult);
            return new CommonResult().failed(returnMsg);
        }

        String resultCode = result.get("result_code");
        if (!WxPayKit.codeIsOk(resultCode)) {
            log.info("支付失败>>" + xmlResult);
            String errCodeDes = result.get("err_code_des");
            return new CommonResult().failed(errCodeDes);
        }
        //支付成功
        return new AjaxResult().success(xmlResult);
    }

    /**
     * 微信APP支付
     */
    @RequestMapping(value = "/appPay", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Object appPay(HttpServletRequest request, @RequestParam(value = "orderId", required = false) Long orderId) {

        try {
            String ip = IpKit.getRealIp(request);
            if (ValidatorUtils.empty(ip)) {
                ip = "127.0.0.1";
            }
            OmsOrder orderInfo = orderService.getById(orderId);
            if (null == orderInfo) {
                return new CommonResult().failed("订单已取消");
            }
            if (orderInfo.getStatus() == OrderStatus.CLOSED.getValue()) {
                return new CommonResult().failed("订单已已关闭，请不要重复操作");
            }
            if (orderInfo.getStatus() != OrderStatus.INIT.getValue()) {
                return new CommonResult().failed("订单已支付，请不要重复操作");
            }
            WxPayApiConfig wxPayApiConfig = this.getApiConfig();

            Map<String, String> params = UnifiedOrderModel
                    .builder()
                    .appid(wxPayApiConfig.getAppId())
                    .mch_id(wxPayApiConfig.getMchId())
                    .nonce_str(WxPayKit.generateStr())
                    .body("微信APP支付")
                    .attach(orderInfo.getOrderSn())
                    .out_trade_no(orderInfo.getOrderSn())
                    .total_fee(orderInfo.getPayAmount().multiply(new BigDecimal(100)).intValue() + "")
                    .spbill_create_ip(ip)
                    .notify_url(notifyUrl)
                    .trade_type(TradeType.APP.getTradeType())
                    .build()
                    .createSign(wxPayApiConfig.getPartnerKey(), SignType.HMACSHA256);

            String xmlResult = WxPayApi.pushOrder(false, params);

            log.info(xmlResult);
            Map<String, String> result = WxPayKit.xmlToMap(xmlResult);

            String returnCode = result.get("return_code");
            String returnMsg = result.get("return_msg");
            if (!WxPayKit.codeIsOk(returnCode)) {
                return new CommonResult().failed(returnMsg);
            }
            String resultCode = result.get("result_code");
            if (!WxPayKit.codeIsOk(resultCode)) {
                return new CommonResult().failed(returnMsg);
            }
            // 以下字段在 return_code 和 result_code 都为 SUCCESS 的时候有返回
            String prepayId = result.get("prepay_id");

            Map<String, String> packageParams = WxPayKit.appPrepayIdCreateSign(wxPayApiConfig.getAppId(), wxPayApiConfig.getMchId(), prepayId,
                    wxPayApiConfig.getPartnerKey(), SignType.HMACSHA256);

            //  WxAppPayDto dto = JsonUtil.map2pojo(packageParams, WxAppPayDto.class);
            log.info("返回apk的参数:" + JsonUtil.objectToJson(packageParams));
            return new CommonResult().success(JsonUtil.objectToJson(packageParams));
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResult().failed(e.getMessage());
        }

    }

    /**
     * 微信小程序支付
     */
    @RequestMapping(value = "/miniAppPay", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Object miniAppPay(HttpServletRequest request, @RequestParam(value = "orderId", required = false) Long orderId
            , @RequestParam(value = "memberId", required = true) Long memberId) {
        try {
            //需要通过授权来获取openId
            UmsMember user = memberFeignClient.findById(memberId);

            String ip = IpKit.getRealIp(request);
            if (ValidatorUtils.empty(ip)) {
                ip = "127.0.0.1";
            }

            SysAppletSet appletSet = null;
            //appletSetMapper.selectOne(new QueryWrapper<>());
            if (null == appletSet) {
                throw new ApiMallPlusException("没有设置支付配置");
            }
            OmsOrder orderInfo = orderService.getById(orderId);
            if (null == orderInfo) {
                return new CommonResult().failed("订单已取消");
            }
            if (orderInfo.getStatus() == OrderStatus.CLOSED.getValue()) {
                return new CommonResult().failed("订单已已关闭，请不要重复操作");
            }
            if (orderInfo.getStatus() != OrderStatus.INIT.getValue()) {
                return new CommonResult().failed("订单已支付，请不要重复操作");
            }
            WxPayBean wxPayApiConfig = new WxPayBean();

            wxPayApiConfig.setAppId(appletSet.getAppid());
            wxPayApiConfig.setMchId(appletSet.getMchid());
            wxPayApiConfig.setDomain(appletSet.getNotifyurl());
            wxPayApiConfig.setPartnerKey(appletSet.getPaySignKey());

            Map<String, String> params = UnifiedOrderModel
                    .builder()
                    .appid(wxPayApiConfig.getAppId())
                    .mch_id(wxPayApiConfig.getMchId())
                    .nonce_str(WxPayKit.generateStr())
                    .body("mallplus-小程序支付")
                    .attach("Node.js 版:https://gitee.com/javen205/TNW")
                    .out_trade_no(orderInfo.getOrderSn())
                    .total_fee(orderInfo.getPayAmount().multiply(new BigDecimal(100)).intValue() + "")
                    .spbill_create_ip(ip)
                    .notify_url(notifyUrl)
                    .trade_type(TradeType.JSAPI.getTradeType())
                    .openid(user.getWeixinOpenid())
                    .build()
                    .createSign(wxPayApiConfig.getPartnerKey(), SignType.HMACSHA256);

            String xmlResult = WxPayApi.pushOrder(false, params);

            log.info(xmlResult);
            Map<String, String> result = WxPayKit.xmlToMap(xmlResult);

            String returnCode = result.get("return_code");
            String returnMsg = result.get("return_msg");
            if (!WxPayKit.codeIsOk(returnCode)) {
                return new CommonResult().failed(returnMsg);
            }
            String resultCode = result.get("result_code");
            if (!WxPayKit.codeIsOk(resultCode)) {
                return new CommonResult().failed(returnMsg);
            }
            // 以下字段在 return_code 和 result_code 都为 SUCCESS 的时候有返回
            String prepayId = result.get("prepay_id");
            Map<String, String> packageParams = WxPayKit.miniAppPrepayIdCreateSign(wxPayApiConfig.getAppId(), prepayId,
                    wxPayApiConfig.getPartnerKey(), SignType.HMACSHA256);
            String jsonStr = JSON.toJSONString(packageParams);
            log.info("小程序支付的参数:" + jsonStr);
            return new CommonResult().success(packageParams);
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResult().failed(e.getMessage());
        }

    }

    /**
     * 企业付款到零钱
     */
    @RequestMapping(value = "/transfer", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String transfer(HttpServletRequest request, @RequestParam("openId") String openId) {

        String ip = IpKit.getRealIp(request);
        if (ValidatorUtils.empty(ip)) {
            ip = "127.0.0.1";
        }

        WxPayApiConfig wxPayApiConfig = this.getApiConfig();

        Map<String, String> params = TransferModel.builder()
                .mch_appid(wxPayApiConfig.getAppId())
                .mchid(wxPayApiConfig.getMchId())
                .nonce_str(WxPayKit.generateStr())
                .partner_trade_no(WxPayKit.generateStr())
                .openid(openId)
                .check_name("NO_CHECK")
                .amount("100")
                .desc("IJPay 让支付触手可及-企业付款")
                .spbill_create_ip(ip)
                .build()
                .createSign(wxPayApiConfig.getPartnerKey(), SignType.HMACSHA256, false);

        // 提现
        String transfers = WxPayApi.transfers(params, wxPayApiConfig.getCertPath(), wxPayApiConfig.getMchId());
        log.info("提现结果:" + transfers);
        Map<String, String> map = WxPayKit.xmlToMap(transfers);
        String returnCode = map.get("return_code");
        String resultCode = map.get("result_code");
        if (WxPayKit.codeIsOk(returnCode) && WxPayKit.codeIsOk(resultCode)) {
            // 提现成功
        } else {
            // 提现失败
        }
        return transfers;
    }

    /**
     * 查询企业付款到零钱
     */
    @RequestMapping(value = "/transferInfo", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String transferInfo(@RequestParam("partner_trade_no") String partnerTradeNo) {
        try {
            WxPayApiConfig wxPayApiConfig = this.getApiConfig();

            Map<String, String> params = GetTransferInfoModel.builder()
                    .nonce_str(WxPayKit.generateStr())
                    .partner_trade_no(partnerTradeNo)
                    .mch_id(wxPayApiConfig.getMchId())
                    .appid(wxPayApiConfig.getAppId())
                    .build()
                    .createSign(wxPayApiConfig.getPartnerKey(), SignType.HMACSHA256, false);

            return WxPayApi.getTransferInfo(params, wxPayApiConfig.getCertPath(), wxPayApiConfig.getMchId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取RSA加密公钥
     */
    @RequestMapping(value = "/getPublicKey", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String getPublicKey() {
        try {
            WxPayApiConfig wxPayApiConfig = this.getApiConfig();

            Map<String, String> params = new HashMap<String, String>(4);
            params.put("mch_id", wxPayApiConfig.getMchId());
            params.put("nonce_str", String.valueOf(System.currentTimeMillis()));
            params.put("sign_type", "MD5");
            String createSign = WxPayKit.createSign(params, wxPayApiConfig.getPartnerKey(), SignType.MD5);
            params.put("sign", createSign);
            return WxPayApi.getPublicKey(params, wxPayApiConfig.getCertPath(), wxPayApiConfig.getMchId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 企业付款到银行卡
     */
    @RequestMapping(value = "/payBank", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String payBank() {
        try {
            WxPayApiConfig wxPayApiConfig = this.getApiConfig();

            //通过WxPayApi.getPublicKey接口获取RSA加密公钥
            //假设获取到的RSA加密公钥为PUBLIC_KEY(PKCS#8格式)
            final String PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA6Bl76IwSvBTiibZ+CNRUA6BfahMshZ0WJpHD1GpmvcQjeN6Yrv6c9eIl6gB4nU3isN7bn+LmoVTpH1gHViaV2YyG/zXj4z4h7r+V+ezesMqqorEg38BCNUHNmhnw4/C0I4gBAQ4x0SJOGnfKGZKR9yzvbkJtvEn732JcEZCbdTZmaxkwlenXvM+mStcJaxBCB/h5xJ5VOF5nDbTPzLphIpzddr3zx/Jxjna9QB1v/YSKYXn+iuwruNUXGCvvxBWaBGKrjOdRTRy9adWOgNmtuYDQJ2YOfG8PtPe06ELKjmr2CfaAGrKKUroyaGvy3qxAV0PlT+UQ4ADSXWt/zl0o5wIDAQAB";

            Map<String, String> params = new HashMap<String, String>(10);
            params.put("mch_id", wxPayApiConfig.getMchId());
            params.put("partner_trade_no", System.currentTimeMillis() + "");
            params.put("nonce_str", System.currentTimeMillis() + "");
            //收款方银行卡号
            params.put("enc_bank_no", RsaKit.encryptByPublicKeyByWx("银行卡号", PUBLIC_KEY));
            //收款方用户名
            params.put("enc_true_name", RsaKit.encryptByPublicKeyByWx("银行卡持有人姓名", PUBLIC_KEY));
            //收款方开户行
            params.put("bank_code", "1001");
            params.put("amount", "1");
            params.put("desc", "IJPay 让支付触手可及-付款到银行卡");
            params.put("sign", WxPayKit.createSign(params, wxPayApiConfig.getPartnerKey(), SignType.HMACSHA256));
            return WxPayApi.payBank(params, wxPayApiConfig.getCertPath(), wxPayApiConfig.getMchId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询企业付款到银行
     */
    @RequestMapping(value = "/queryBank", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String queryBank(@RequestParam("partner_trade_no") String partnerTradeNo) {
        try {
            WxPayApiConfig wxPayApiConfig = this.getApiConfig();

            Map<String, String> params = new HashMap<String, String>(4);
            params.put("mch_id", wxPayApiConfig.getMchId());
            params.put("partner_trade_no", partnerTradeNo);
            params.put("nonce_str", System.currentTimeMillis() + "");
            params.put("sign", WxPayKit.createSign(params, wxPayApiConfig.getPartnerKey(), SignType.MD5));
            return WxPayApi.queryBank(params, wxPayApiConfig.getCertPath(), wxPayApiConfig.getMchId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 微信退款
     */
    @RequestMapping(value = "/refund", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String refund(@RequestParam("transactionId") String transactionId,
                         @RequestParam("out_trade_no") String outTradeNo) {

        if (ValidatorUtils.empty(outTradeNo) && ValidatorUtils.empty(transactionId)) {
            return "transactionId、out_trade_no二选一";
        }
        WxPayApiConfig wxPayApiConfig = this.getApiConfig();

        Map<String, String> params = RefundModel.builder()
                .appid(wxPayApiConfig.getAppId())
                .mch_id(wxPayApiConfig.getMchId())
                .nonce_str(WxPayKit.generateStr())
                .transaction_id(transactionId)
                .out_trade_no(outTradeNo)
                .out_refund_no(WxPayKit.generateStr())
                .total_fee("1")
                .refund_fee("1")
                .notify_url(refundNotifyUrl)
                .build()
                .createSign(wxPayApiConfig.getPartnerKey(), SignType.MD5);

        return WxPayApi.orderRefund(false, params, wxPayApiConfig.getCertPath(), wxPayApiConfig.getMchId());
    }

    /**
     * 微信退款查询
     */
    @RequestMapping(value = "/refundQuery", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String refundQuery(@RequestParam("transactionId") String transactionId,
                              @RequestParam("out_trade_no") String outTradeNo,
                              @RequestParam("out_refund_no") String outRefundNo,
                              @RequestParam("refund_id") String refundId) {

        WxPayApiConfig wxPayApiConfig = this.getApiConfig();

        Map<String, String> params = RefundQueryModel.builder()
                .appid(wxPayApiConfig.getAppId())
                .mch_id(wxPayApiConfig.getMchId())
                .nonce_str(WxPayKit.generateStr())
                .transaction_id(transactionId)
                .out_trade_no(outTradeNo)
                .out_refund_no(outRefundNo)
                .refund_id(refundId)
                .build()
                .createSign(wxPayApiConfig.getPartnerKey(), SignType.MD5);

        return WxPayApi.orderRefundQuery(false, params);
    }

    /**
     * 退款通知
     */
    @RequestMapping(value = "/refundNotify", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String refundNotify(HttpServletRequest request) {
        String xmlMsg = HttpKit.readData(request);
        log.info("微信退款通知=" + xmlMsg);
        Map<String, String> params = WxPayKit.xmlToMap(xmlMsg);

        String returnCode = params.get("return_code");
        // 注意重复通知的情况，同一订单号可能收到多次通知，请注意一定先判断订单状态
        if (WxPayKit.codeIsOk(returnCode)) {
            String reqInfo = params.get("req_info");
            String decryptData = WxPayKit.decryptData(reqInfo, this.getApiConfig().getPartnerKey());
            log.info("退款通知解密后的数据=" + decryptData);
            // 更新订单信息
            // 发送通知等
            Map<String, String> xml = new HashMap<String, String>(2);
            xml.put("return_code", "SUCCESS");
            xml.put("return_msg", "OK");
            return WxPayKit.toXml(xml);
        }
        return null;
    }

    /**
     * 异步通知
     */
    @RequestMapping(value = "/payNotify", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String payNotify(HttpServletRequest request) {
        String xmlMsg = HttpKit.readData(request);

        Map<String, String> params = WxPayKit.xmlToMap(xmlMsg);
        log.info("微信支付通知=" + params);
        String returnCode = params.get("return_code");
        //订单编号
        String out_trade_no = params.get("out_trade_no");
        OmsOrder param = new OmsOrder();
        param.setOrderSn(out_trade_no);
        List<OmsOrder> list = orderService.list(new QueryWrapper<>(param));
        OmsOrder orderInfo = list.get(0);
        orderInfo.setStatus(OrderStatus.TO_DELIVER.getValue());
        orderInfo.setPaymentTime(new Date());
        // 注意重复通知的情况，同一订单号可能收到多次通知，请注意一定先判断订单状态
        // 注意此处签名方式需与统一下单的签名类型一致
        if (WxPayKit.verifyNotify(params, this.getApiConfig().getPartnerKey(), SignType.HMACSHA256)) {
            if (WxPayKit.codeIsOk(returnCode)) {
                // 更新订单信息
                orderService.updateById(orderInfo);
                // 发送通知等
                Map<String, String> xml = new HashMap<String, String>(2);
                xml.put("return_code", "SUCCESS");
                xml.put("return_msg", "OK");
                return WxPayKit.toXml(xml);
            } else {
                log.error("订单" + out_trade_no + "支付失败");
                orderService.releaseStock(orderInfo);
            }
        }
        return null;
    }
}

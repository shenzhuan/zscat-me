package com.mallplus.order.controller;


import cn.hutool.core.util.XmlUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mallplus.common.annotation.SysLog;
import com.mallplus.common.constant.OrderStatus;
import com.mallplus.common.entity.oms.OmsOrder;
import com.mallplus.common.entity.oms.OmsOrderItem;
import com.mallplus.common.entity.oms.OmsPayments;
import com.mallplus.common.entity.sms.SmsGroup;
import com.mallplus.common.entity.ums.UmsMember;
import com.mallplus.common.feign.MarkingFeignClinent;
import com.mallplus.common.feign.MemberFeignClient;
import com.mallplus.common.feign.PmsFeignClinent;
import com.mallplus.common.utils.CommonResult;
import com.mallplus.common.utils.ValidatorUtils;
import com.mallplus.common.vo.OrderParam;
import com.mallplus.order.config.WxAppletProperties;
import com.mallplus.order.service.IOmsOrderItemService;
import com.mallplus.order.service.IOmsOrderService;
import com.mallplus.order.service.IOmsPaymentsService;
import com.mallplus.order.utils.IpAddressUtil;
import com.mallplus.order.utils.applet.WechatRefundApiResult;
import com.mallplus.order.utils.applet.WechatUtil;
import com.mallplus.order.vo.BalancePayParam;
import com.mallplus.util.CharUtil;
import com.mallplus.util.DateUtils;
import com.mallplus.util.MapUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 作者: @author mallplus <br>
 * 时间: 2017-08-11 08:32<br>
 * 描述: ApiIndexController <br>
 */
@Api(tags = "商户支付")
@Slf4j
@RestController
@RequestMapping("/api/pay")
public class PayController {
    private static Logger LOGGER = LoggerFactory.getLogger(PayController.class);

    @Autowired
    private IOmsOrderService orderService;
    @Resource
    private MemberFeignClient memberFeignClient;
    @Autowired
    private WxAppletProperties wxAppletProperties;

    @Autowired
    private IOmsOrderItemService orderItemService;
    @Autowired
    private IOmsPaymentsService paymentsService;

    @Autowired
    private MarkingFeignClinent markingFeignClinent;


    @ApiOperation("显示所有支付方式")
    @RequestMapping(value = "/paymentlist", method = RequestMethod.GET)
    public Object getPayments() {
        List<OmsPayments> paymentss = paymentsService.list(new QueryWrapper<OmsPayments>().eq("status", 1));
        return new CommonResult().success(paymentss);
    }

    /**
     * 余额支付
     */
    @SysLog(MODULE = "pay", REMARK = "余额支付")
    @ApiOperation(value = "余额支付")
    @PostMapping("balancePay")
    public Object balancePay(BalancePayParam payParam){

            return new CommonResult().success(orderService.blancePay(orderService.getById(payParam.getOrderId())));
    }

    /**
     * 余额支付
     */
    @SysLog(MODULE = "pay", REMARK = "积分兑换")
    @ApiOperation(value = "积分兑换")
    @PostMapping("jifenPay")
    public Object jifenPay(OrderParam payParam){
        return orderService.jifenPay(payParam);
    }

    /**
     * 订单退款请求
     */
    /*
    @SysLog(MODULE = "pay", REMARK = "订单退款请求")
    @ApiOperation(value = "订单退款请求")
    @PostMapping("refund")
    public Object refund(Integer orderId) {
        //
        OrderVo orderInfo = orderService.queryObject(orderId);

        if (null == orderInfo) {
            return toResponsObject(400, "订单已取消", "");
        }

        if (orderInfo.getOrder_status() == 401 || orderInfo.getOrder_status() == 402) {
            return toResponsObject(400, "订单已退款", "");
        }

        WechatRefundApiResult result = WechatUtil.wxRefund(orderInfo.getId().toString(),
                10.01, 10.01);
        if (result.getResult_code().equals("SUCCESS")) {
            if (orderInfo.getOrder_status() == 201) {
                orderInfo.setOrder_status(401);
            } else if (orderInfo.getOrder_status() == 300) {
                orderInfo.setOrder_status(402);
            }
            orderService.updateAll(orderInfo);
            return toResponsObject(400, "成功退款", "");
        } else {
            return toResponsObject(400, "退款失败", "");
        }
    }*/


    //返回微信服务
    public static String setXml(String return_code, String return_msg) {
        return "<xml><return_code><![CDATA[" + return_code + "]]></return_code><return_msg><![CDATA[" + return_msg + "]]></return_msg></xml>";
    }

    //模拟微信回调接口
    public static String callbakcXml(String orderNum) {
        return "<xml><appid><![CDATA[wx2421b1c4370ec43b]]></appid><attach><![CDATA[支付测试]]></attach><bank_type><![CDATA[CFT]]></bank_type><fee_type><![CDATA[CNY]]></fee_type> <is_subscribe><![CDATA[Y]]></is_subscribe><mch_id><![CDATA[10000100]]></mch_id><nonce_str><![CDATA[5d2b6c2a8db53831f7eda20af46e531c]]></nonce_str><openid><![CDATA[oUpF8uMEb4qRXf22hE3X68TekukE]]></openid> <out_trade_no><![CDATA[" + orderNum + "]]></out_trade_no>  <result_code><![CDATA[SUCCESS]]></result_code> <return_code><![CDATA[SUCCESS]]></return_code><sign><![CDATA[B552ED6B279343CB493C5DD0D78AB241]]></sign><sub_mch_id><![CDATA[10000100]]></sub_mch_id> <time_end><![CDATA[20140903131540]]></time_end><total_fee>1</total_fee><trade_type><![CDATA[JSAPI]]></trade_type><transaction_id><![CDATA[1004400740201409030005092168]]></transaction_id></xml>";
    }



    /**
     * 获取支付的请求参数
     */
    @SysLog(MODULE = "pay", REMARK = "获取支付的请求参数")
    @ApiOperation(value = "获取支付的请求参数")
    @GetMapping("prepay")
    public Object payPrepay(@RequestParam(value = "id", required = false) Long id,
                            @RequestParam(value = "memberId", required = false) Long memberId,
                            HttpServletRequest req) {
        UmsMember user = memberFeignClient.findById(memberId);
        //
        OmsOrder orderInfo = orderService.getById(id);

        if (null == orderInfo) {
            return  new CommonResult().failed("订单已取消");
        }

        if (orderInfo.getStatus() != 0) {
            return new CommonResult().failed("订单已支付，请不要重复操作");
        }

        String nonceStr = CharUtil.getRandomString(32);

        //https://pay.weixin.qq.com/wiki/doc/api/wxa/wxa_api.php?chapter=7_7&index=3
        Map<Object, Object> resultObj = new TreeMap();

        try {
            Map<Object, Object> parame = new TreeMap<Object, Object>();
            parame.put("appid", wxAppletProperties.getAppId());
            // 商家账号。
            parame.put("mch_id", wxAppletProperties.getMchId());
            String randomStr = CharUtil.getRandomNum(18).toUpperCase();
            // 随机字符串
            parame.put("nonce_str", randomStr);
            // 商户订单编号
            parame.put("out_trade_no", orderInfo.getOrderSn());

            // 商品描述
            parame.put("body", "超市-支付");
            //订单的商品
            List<OmsOrderItem> orderGoods = orderItemService.list(new QueryWrapper<>(new OmsOrderItem()).eq("orderId", id));
            if (null != orderGoods) {
                String body = "超市-";
                for (OmsOrderItem goodsVo : orderGoods) {
                    body = body + goodsVo.getProductName() + "、";
                }
                if (body.length() > 0) {
                    body = body.substring(0, body.length() - 1);
                }
                // 商品描述
                parame.put("body", body);
            }
            //支付金额
            parame.put("total_fee", orderInfo.getPayAmount().multiply(new BigDecimal(100)).intValue());
            // 回调地址
            parame.put("notify_url", wxAppletProperties.getNotifyUrl());
            // 交易类型APP
            parame.put("trade_type", wxAppletProperties.getTradeType());
            parame.put("spbill_create_ip", IpAddressUtil.getIpAddr(req));
            parame.put("openid", user.getWeixinOpenid());
            String sign = WechatUtil.arraySign(parame, wxAppletProperties.getPaySignKey());
            // 数字签证
            parame.put("sign", sign);

            String xml = MapUtils.convertMap2Xml(parame);
            log.info("xml:" + xml);
            Map<String, Object> resultUn = XmlUtil.xmlToMap(WechatUtil.requestOnce(wxAppletProperties.getUniformorder(), xml));
            // 响应报文
            String return_code = MapUtils.getString("return_code", resultUn);
            String return_msg = MapUtils.getString("return_msg", resultUn);
            //
            if (return_code.equalsIgnoreCase("FAIL")) {
                return new CommonResult().failed("支付失败," + return_msg);
            } else if (return_code.equalsIgnoreCase("SUCCESS")) {
                // 返回数据
                String result_code = MapUtils.getString("result_code", resultUn);
                String err_code_des = MapUtils.getString("err_code_des", resultUn);
                if (result_code.equalsIgnoreCase("FAIL")) {
                    return new CommonResult().failed("支付失败," + err_code_des);
                } else if (result_code.equalsIgnoreCase("SUCCESS")) {
                    String prepay_id = MapUtils.getString("prepay_id", resultUn);
                    // 先生成paySign 参考https://pay.weixin.qq.com/wiki/doc/api/wxa/wxa_api.php?chapter=7_7&index=5
                    resultObj.put("appId", wxAppletProperties.getAppId());
                    resultObj.put("timeStamp", DateUtils.timeToStr(System.currentTimeMillis() / 1000, DateUtils.DATE_TIME_PATTERN));
                    resultObj.put("nonceStr", nonceStr);
                    resultObj.put("package", "prepay_id=" + prepay_id);
                    resultObj.put("signType", "MD5");
                    String paySign = WechatUtil.arraySign(resultObj, wxAppletProperties.getPaySignKey());
                    resultObj.put("paySign", paySign);
                    // 业务处理
                    orderInfo.setPrepayId(prepay_id);
                    // 付款中
                    orderInfo.setStatus(1);
                    orderService.updateById(orderInfo);
                    return new CommonResult().success("微信统一订单下单成功");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResult().failed("\"下单失败,error=\" + e.getMessage()");
        }
        return new CommonResult().failed();
    }

    /**
     * 微信查询订单状态
     */
    @SysLog(MODULE = "pay", REMARK = "查询订单状态")
    @ApiOperation(value = "查询订单状态")
    @GetMapping("query")
    public Object orderQuery(@RequestParam(value = "id", required = false) Long id) {

        OmsOrder orderDetail = orderService.getById(id);
        if (id == null) {
            return new CommonResult().failed("订单不存在");
        }
        Map<Object, Object> parame = new TreeMap<Object, Object>();
        parame.put("appid", wxAppletProperties.getAppId());
        // 商家账号。
        parame.put("mch_id", wxAppletProperties.getMchId());
        String randomStr = CharUtil.getRandomNum(18).toUpperCase();
        // 随机字符串
        parame.put("nonce_str", randomStr);
        // 商户订单编号
        parame.put("out_trade_no", orderDetail.getOrderSn());

        String sign = WechatUtil.arraySign(parame, wxAppletProperties.getPaySignKey());
        // 数字签证
        parame.put("sign", sign);

        String xml = MapUtils.convertMap2Xml(parame);
        log.info("xml:" + xml);
        Map<String, Object> resultUn = null;
        try {
            resultUn = XmlUtil.xmlToMap(WechatUtil.requestOnce(wxAppletProperties.getOrderquery(), xml));
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResult().failed("查询失败,error=" + e.getMessage());
        }
        // 响应报文
        String return_code = MapUtils.getString("return_code", resultUn);
        String return_msg = MapUtils.getString("return_msg", resultUn);

        if (!"SUCCESS".equals(return_code)) {
            return new CommonResult().failed("查询失败,error=" + return_msg);
        }

        String trade_state = MapUtils.getString("trade_state", resultUn);
        if ("SUCCESS".equals(trade_state)) {
            // 更改订单状态
            // 业务处理
            OmsOrder orderInfo = new OmsOrder();
            orderInfo.setId(id);
            orderInfo.setStatus(2);
            orderInfo.setConfirmStatus(1);
            orderInfo.setPaymentTime(new Date());
            orderService.updateById(orderInfo);
            return new CommonResult().success("支付成功");
        } else if ("USERPAYING".equals(trade_state)) {
            // 重新查询 正在支付中
           /* Integer num = (Integer) J2CacheUtils.get(J2CacheUtils.SHOP_CACHE_NAME, "queryRepeatNum" + id + "");
            if (num == null) {
                J2CacheUtils.put(J2CacheUtils.SHOP_CACHE_NAME, "queryRepeatNum" + id + "", 1);
                this.orderQuery(id);
            } else if (num <= 3) {
                J2CacheUtils.remove(J2CacheUtils.SHOP_CACHE_NAME, "queryRepeatNum" + id);
                this.orderQuery(id);
            } else {
                return toResponsFail("查询失败,error=" + trade_state);
            }*/

        } else {
            // 失败
            return new CommonResult().failed("查询失败,error=" + trade_state);
        }

        return new CommonResult().failed("查询失败，未知错误");
    }

    /**
     * 微信订单回调接口
     *
     * @return
     */
    @SysLog(MODULE = "pay", REMARK = "微信订单回调接口")
    @ApiOperation(value = "微信订单回调接口")
    @RequestMapping(value = "/notify", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public void notify(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            response.setHeader("Access-Control-Allow-Origin", "*");
            InputStream in = request.getInputStream();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            out.close();
            in.close();
            //xml数据
            String reponseXml = new String(out.toByteArray(), "utf-8");

            WechatRefundApiResult result = (WechatRefundApiResult) XmlUtil.readObjectFromXml(reponseXml);
            String result_code = result.getResult_code();
            if (result_code.equalsIgnoreCase("FAIL")) {
                //订单编号
                String out_trade_no = result.getOut_trade_no();
                log.error("订单" + out_trade_no + "支付失败");
                response.getWriter().write(setXml("SUCCESS", "OK"));
            } else if (result_code.equalsIgnoreCase("SUCCESS")) {
                //订单编号
                String out_trade_no = result.getOut_trade_no();
                log.error("订单" + out_trade_no + "支付成功");
                // 业务处理
                OmsOrder param = new OmsOrder();
                param.setOrderSn(out_trade_no);
                List<OmsOrder> list = orderService.list(new QueryWrapper<>(param));
                OmsOrder orderInfo = list.get(0);
                orderInfo.setStatus(2);
                orderInfo.setPaymentTime(new Date());
                orderService.updateById(orderInfo);
                response.getWriter().write(setXml("SUCCESS", "OK"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }
}

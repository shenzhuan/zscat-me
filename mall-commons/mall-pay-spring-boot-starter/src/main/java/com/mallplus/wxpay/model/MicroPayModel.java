/**
 * <p>IJPay 让支付触手可及，封装了微信支付、支付宝支付、银联支付常用的支付方式以及各种常用的接口。</p>
 *
 * <p>不依赖任何第三方 mvc 框架，仅仅作为工具使用简单快速完成支付模块的开发，可轻松嵌入到任何系统里。 </p>
 *
 * <p>IJPay 交流群: 723992875</p>
 *
 * <p>Node.js 版: https://gitee.com/javen205/TNW</p>
 *
 * <p>付款码支付 Model</p>
 * <p>支持: 付款支付、支付押金（人脸支付）、支付押金（付款码支付）</p>
 *
 * @author Javen
 */
package com.mallplus.wxpay.model;

import com.mallplus.core.model.BaseModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class MicroPayModel extends BaseModel {
    /**
     * 是否押金支付
     */
    private String deposit;
    private String appid;
    private String sub_appid;
    private String mch_id;
    private String sub_mch_id;
    private String device_info;
    private String nonce_str;
    private String sign;
    private String sign_type;
    private String body;
    private String detail;
    private String attach;
    private String out_trade_no;
    private String total_fee;
    private String fee_type;
    private String spbill_create_ip;
    private String goods_tag;
    private String limit_pay;
    private String time_start;
    private String time_expire;
    private String auth_code;
    private String receipt;
    private String scene_info;
    private String openid;
    /**
     * 人脸凭证，用于人脸支付
     */
    private String face_code;
}

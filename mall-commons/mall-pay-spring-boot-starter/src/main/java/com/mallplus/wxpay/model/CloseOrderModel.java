/**
 * <p>IJPay 让支付触手可及，封装了微信支付、支付宝支付、银联支付常用的支付方式以及各种常用的接口。</p>
 *
 * <p>不依赖任何第三方 mvc 框架，仅仅作为工具使用简单快速完成支付模块的开发，可轻松嵌入到任何系统里。 </p>
 *
 * <p>IJPay 交流群: 723992875</p>
 *
 * <p>Node.js 版: https://gitee.com/javen205/TNW</p>
 *
 * <p>关闭订单 Model</p>
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
public class CloseOrderModel extends BaseModel {
    private String appid;
    private String sub_appid;
    private String mch_id;
    private String sub_mch_id;
    private String out_trade_no;
    private String nonce_str;
    private String sign;
    private String sign_type;
}

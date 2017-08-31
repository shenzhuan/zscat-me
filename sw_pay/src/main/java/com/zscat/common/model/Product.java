package com.itstyle.common.model;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 产品订单信息
 * 创建者 科帮网
 * 创建时间	2017年7月27日
 */
@Data                
@NoArgsConstructor     
@AllArgsConstructor
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	private String productId;// 商品ID
	private String subject;//订单名称 
	private String body;// 商品描述
	private String totalFee;// 总金额(单位是分)
	private String outTradeNo;// 订单号(唯一)
	private String spbillCreateIp;// 发起人IP地址
	private String attach;// 附件数据主要用于商户携带订单的自定义数据
	private Short payType;// 支付类型(1:支付宝 2:微信 3:银联)
	private Short payWay;// 支付方式 (1：PC,平板 2：手机)
	private String frontUrl;// 前台回调地址  非扫码支付使用
}

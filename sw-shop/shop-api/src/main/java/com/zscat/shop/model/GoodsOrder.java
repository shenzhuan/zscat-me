/** Powered By zscat科技, Since 2016 - 2020 */

package com.zscat.shop.model;

import javax.persistence.Table;

import com.zsCat.common.base.BaseEntity;

/**
 * 
 * @author zsCat 2017-1-7 16:07:35
 * @Email: 951449465@qq.com
 * @version 1.0v 商品订单管理
 */
@SuppressWarnings({ "unused" })
@Table(name = "t_goodsorder")
public class GoodsOrder extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private Long goodsid;

	private Long orderid;

	public Long getGoodsid() {
		return goodsid;
	}

	public void setGoodsid(Long goodsid) {
		this.goodsid = goodsid;
	}

	public Long getOrderid() {
		return orderid;
	}

	public void setOrderid(Long orderid) {
		this.orderid = orderid;
	}

}

 /*
  * Powered By zsCat, Since 2014 - 2020
  */
package com.zscat.shop.service.impl;

import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.Resource;

import com.alibaba.dubbo.config.annotation.Service;

import com.zsCat.common.base.ServiceMybatis;

import com.zsCat.common.utils.RandomString;
import com.zscat.shop.mapper.*;
import com.zscat.shop.model.*;
import com.zscat.shop.service.OrderService;
import com.zscat.shop.util.SysUserUtils;


 /**
 * 
 * @author zsCat 2017-4-14 13:56:18
 * @Email: 951449465@qq.com
 * @version 4.0v
 */
@Service(version = "1.0.0",retries = 0,timeout = 60000)
public class OrderServiceImpl extends ServiceMybatis<Order> implements OrderService {

	@Resource
	private OrderMapper OrderMapper;
	
	@Resource
	private GoodsOrderMapper GoodsOrderMapper;
	 @Resource
	 private ProductMapper productMapper;
	 @Resource
	private  CartMapper CartMapper;
	@Resource
	private OrderLogMapper  OrderLogMapper;
	@Override
	public Order insertOrder(String[] cartIds,Long addressid, Long paymentid, String usercontent,Long uid,String uname) {
		Order order=new Order();

		if(cartIds!=null && cartIds.length>0){
			int count=0;
			BigDecimal total=BigDecimal.ZERO;
			for(String cartId:cartIds){
				Cart cart=CartMapper.selectByPrimaryKey(Long.parseLong(cartId));
				if(cart==null){
					return null;
				}
				count+=cart.getCount();
				 total =total.add(BigDecimal.valueOf(Double.valueOf(cart.getPrice())).multiply(BigDecimal.valueOf(cart.getCount())));
				GoodsOrder go=new GoodsOrder();
				go.setGoodsid(cart.getGoodsid());
				go.setOrderid(order.getId());
				GoodsOrderMapper.insertSelective(go);
				CartMapper.deleteByPrimaryKey(cart);
			}
			order.setOrdersn(RandomString.generateRandomString(8));
			order.setCreatedate(new Date());
			order.setStatus(1);
			order.setUserid(uid);
			order.setUsername(uname);
			order.setPaymentid(paymentid);
			order.setUsercontent(usercontent);
			order.setAddressid(addressid);
			//order.setOrderTotalPrice();
			OrderMapper.insertSelective(order);
			
			OrderLog log=new OrderLog();
			log.setOrderId(order.getId());
			log.setOrderState("1");
			log.setStateInfo("提交订单");
			log.setCreateTime(new Date().getTime());
			log.setOperator(uname);
			OrderLogMapper.insertSelective(log);
			
			order.setTotalcount(count);
			order.setTotalprice(total);
			OrderMapper.updateByPrimaryKey(order);
		}
		return order;
		
	}

	 @Override
	 public Order insertWapOrder(Long productId, Long addressid, Long paymentid, String usercontent, Long uid, String uname) {
		 Order order=new Order();
		 Product p =productMapper.selectByPrimaryKey(productId);
		  order.setOrdersn(RandomString.generateRandomString(8));
		 order.setCreatedate(new Date());
		 order.setStatus(SysUserUtils.ORDER_ONE);
		 order.setUserid(uid);
		 order.setUsername(uname);
		 order.setPaymentid(paymentid);
		 order.setUsercontent(usercontent);
		 order.setAddressid(addressid);
		 order.setTotalcount(1);
		 order.setTotalprice(BigDecimal.valueOf(Double.valueOf(p.getPrices())));
		 OrderMapper.insertSelective(order);

		 OrderLog log=new OrderLog();
		 log.setOrderId(order.getId());
		 log.setOrderState("1");
		 log.setStateInfo("提交订单");
		 log.setCreateTime(new Date().getTime());
		 log.setOperator(uname);
		 OrderLogMapper.insertSelective(log);

		 return order;
	 }


 }

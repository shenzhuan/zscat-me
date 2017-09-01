 /*
  * Powered By zsCat, Since 2014 - 2020
  */
package com.zscat.shop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.zsCat.common.base.ServiceMybatis;
import com.alibaba.dubbo.config.annotation.Service;


import com.zscat.shop.mapper.CartMapper;
import com.zscat.shop.model.Cart;
import com.zscat.shop.service.CartService;


 /**
 * 
 * @author zsCat 2017-4-14 13:56:18
 * @Email: 951449465@qq.com
 * @version 4.0v
 */
@Service(version = "1.0.0",retries = 0,timeout = 60000)
public class CartServiceImpl extends ServiceMybatis<Cart> implements CartService {
	@Resource
	private CartMapper CartMapper;

	 @Override
		public List<Cart> selectOwnCart(Long uid) {
				Cart cart=new Cart();
				cart.setUserid(uid);
				return CartMapper.select(cart);
		}
		@Override
		public int selectOwnCartCount(Long uid) {
				Cart cart=new Cart();
				cart.setUserid(uid);
				return CartMapper.selectCount(cart);

			
		}

  
    
}

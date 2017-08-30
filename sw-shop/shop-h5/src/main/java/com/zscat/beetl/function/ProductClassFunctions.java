package com.zscat.beetl.function;

import java.util.List;

import javax.annotation.Resource;


import com.alibaba.dubbo.config.annotation.Reference;
import com.zscat.shop.model.Product;
import com.zscat.shop.model.ProductClass;
import com.zscat.shop.service.ProductClassService;
import com.zscat.shop.service.ProductService;
import com.zscat.shop.service.CartService;
import org.springframework.stereotype.Service;

@Service("productClass")
public class ProductClassFunctions {
	
	@Reference(version = "1.0.0")
	private ProductClassService goodsClassService;

	@Reference(version = "1.0.0")
	private ProductService productService;
	@Reference(version = "1.0.0")
	private CartService cartService;
	public List<ProductClass> getProductClassListByPid(Long pid){
		ProductClass gc=new ProductClass();
		gc.setParentId(pid);
		return goodsClassService.select(gc);
	}
//	public List<ShopType> getShopTypeListByPid(Long pid){
//		ShopType gc=new ShopType();
//		gc.setParentId(pid);
//		return ShopTypeService.select(gc);
//	}
	/**
	 * 根据商品类型查询商品
	 * @param tid
	 * @return
	 */
	public List<Product> getProductListByTypeid(Long tid){
		Product goods=new Product();
		goods.setType(tid);
		return productService.selectPage(1, 6, goods).getList();
	}
	/**
	 * 查询楼层商品
	 * @param tid
	 * @return
	 */
	public List<Product> getProductByFloorid(Long tid){
		return productService.selectProductByFloor(tid);
	}
	/**
	 * 根据订单查询商品
	 * @param tid
	 * @return
	 */
//	public List<Product> getProductListByOrderid(Long tid){
//		Product goods=new Product();
//		
//		return ProductService.selectPage(1, 6, goods).getList();
//	}
	/**
	 * 得到购物车数量
	 * @return
	 */
	public int selectOwnCartCount(){
		return cartService.selectOwnCartCount();
	}
}

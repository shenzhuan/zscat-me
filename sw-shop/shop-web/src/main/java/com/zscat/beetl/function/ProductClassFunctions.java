package com.zscat.beetl.function;

import java.util.List;

import javax.annotation.Resource;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zscat.shop.util.SysUserUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.zscat.conf.JbaseFunctionPackage;
import com.zscat.shop.model.Product;
import com.zscat.shop.model.ProductClass;
import com.zscat.shop.service.CartService;
import com.zscat.shop.service.ProductClassService;
import com.zscat.shop.service.ProductService;


	@Service("productClass")
	public class ProductClassFunctions implements JbaseFunctionPackage {
		@Reference(version = "1.0.0")
	private ProductClassService goodsClassService;
//	@Reference(version = "1.0.0")
//	private ShopTypeService ShopTypeService;
@Reference(version = "1.0.0")
	private ProductService ProductService;
		@Reference(version = "1.0.0")
	private CartService CartService;
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
		return ProductService.selectPage(1, 6, goods).getList();
	}
	/**
	 * 查询楼层商品
	 * @param tid
	 * @return
	 */
	public List<Product> getProductByFloorid(Long tid){
		return ProductService.selectProductByFloor(tid);
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
		if(SysUserUtils.getSessionLoginUser()!=null){
			return CartService.selectOwnCartCount(SysUserUtils.getSessionLoginUser().getId());
		}
		return 0;
	}
}

package com.zscat.beetl.function;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.zsCat.common.utils.AddressUtils;
import com.zscat.conf.JbaseFunctionPackage;
import com.zscat.shop.model.*;
import com.zscat.shop.service.*;
import com.zscat.shop.util.SysUserUtils;
import com.zscat.util.MemberUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("goods")
public class GoodsFunction implements JbaseFunctionPackage {


	@Reference(version = "1.0.0")
	private OrderService orderService;
	@Reference(version = "1.0.0")
	private ProductClassService ProductClassService;
	@Reference(version = "1.0.0")
	private CartService CartService;	
	@Reference(version = "1.0.0")
	private ProductService productService;
	@Reference(version = "1.0.0")
	private ArticleService ArticleService;
	public   String getCityByIp() throws Exception {
		return AddressUtils.getCityByIp();
	}
	public PageInfo<Product> getLatestGoods(int pageSize){
		return productService.selectPage(1, pageSize, new Product()," create_date desc");
	}
	public PageInfo<Product> getHitGoods(int pageSize){
		return productService.selectPage(1, pageSize, new Product()," clickHit desc");
	}
	public PageInfo<Product> getSellGoods(int pageSize){
		return productService.selectPage(1, pageSize, new Product()," sellhit desc");
	}
	
	/**
	 * 拿到推荐
	 * @param pageSize
	 * @return
	 */
	public PageInfo<Product> getCommendGoods(int pageSize){
		Product p=new Product();
		p.setIscom(1);
		return productService.selectPage(1, pageSize,p);
	}
	public PageInfo<Product> getTypeGoods(int pageSize,Long typeid){
		Product p=new Product();
		p.setTypeid(typeid);
		return productService.selectPage(1, pageSize,p);
	}
	public PageInfo<Product> getMenuTypeGoods(int pageSize,Long typeid){
		Product p=new Product();
		p.setType(typeid);
		return productService.selectPage(1, pageSize,p);
	}
	//活动最新订单
	public PageInfo<Order> getLatestOrder(int pageSize){
		return orderService.selectPage(1, pageSize, new Order());
	}
	//得到菜单类别
	public PageInfo<ProductClass> getProductClass(int pageSize,Long parentId){
		 ProductClass gc=new ProductClass();
         gc.setParentId(parentId);
		return ProductClassService.selectPage(1, pageSize, gc);
	}
	public List<ProductClass> getAllProductClass(){
		 ProductClass gc=new ProductClass();
		return ProductClassService.select(gc);
	}
	
	//得到购物车
	public List<Cart> getCartList() {
		if(MemberUtils.getSessionLoginUser()!=null){
			return CartService.selectOwnCart(MemberUtils.getSessionLoginUser().getId());
		}
		return new ArrayList<>();
	 }

	public PageInfo<Article> getAdvArticle(int count){
		return  ArticleService.selectPage(1, count, new Article());
	}
	
}

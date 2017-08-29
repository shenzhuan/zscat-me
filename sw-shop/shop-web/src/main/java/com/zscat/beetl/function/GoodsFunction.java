package com.zscat.beetl.function;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.zscat.conf.JbaseFunctionPackage;
import com.zscat.shop.model.Cart;
import com.zscat.shop.model.Order;
import com.zscat.shop.model.Product;
import com.zscat.shop.model.ProductClass;
import com.zscat.shop.service.CartService;
import com.zscat.shop.service.OrderService;
import com.zscat.shop.service.ProductClassService;
import com.zscat.shop.service.ProductService;

@Service("goods")
public class GoodsFunction implements JbaseFunctionPackage {

	
	@Resource
	private OrderService orderService;
	@Resource
	private ProductClassService ProductClassService;
	@Resource
	private CartService CartService;	
	@Resource
	private ProductService productService;
	
	
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
		 return CartService.selectOwnCart();
	 }
	
	
}

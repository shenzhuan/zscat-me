 /*
  * Powered By zsCat, Since 2014 - 2020
  */
package com.zscat.shop.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.zsCat.common.base.BaseService;
import com.zscat.shop.model.Product;

/**
 * 
 * @author zsCat 2017-4-14 13:56:18
 * @Email: 951449465@qq.com
 * @version 4.0v
 */
public interface ProductService extends BaseService<Product>  {

	public List<Product> selectProductByFloor(Long id);
	public List<Product> getProductByFloorid(Long tid);
	public PageInfo<Product> selectgoodsListByType(int i, int j, Product g);
	public List<Product> selectRepoer();



}

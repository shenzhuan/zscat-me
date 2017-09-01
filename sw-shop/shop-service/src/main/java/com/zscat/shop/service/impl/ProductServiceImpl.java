 /*
  * Powered By zsCat, Since 2014 - 2020
  */
package com.zscat.shop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.alibaba.dubbo.config.annotation.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zsCat.common.base.ServiceMybatis;
import com.zscat.shop.mapper.ProductMapper;
import com.zscat.shop.model.Product;
import com.zscat.shop.service.ProductService;

 /**
 * 
 * @author zsCat 2017-4-14 13:56:18
 * @Email: 951449465@qq.com
 * @version 4.0v
 */
@Service(version = "1.0.0",retries = 0,timeout = 60000)
public class ProductServiceImpl extends ServiceMybatis<Product> implements ProductService {

	@Resource
	private ProductMapper ProductMapper;

	
	

@Override
public List<Product> selectProductByFloor(Long id) {
	return ProductMapper.selectProductByFloor(id);
}

@Override
public List<Product> getProductByFloorid(Long tid) {
	// TODO Auto-generated method stub
	return ProductMapper.getProductByFloorid(tid);
}

@Override
public PageInfo<Product> selectgoodsListByType(int i, int j, Product g) {
	PageHelper.startPage(i, j);
	List<Product> list = ProductMapper.selectgoodsListByType(g);
	return new PageInfo<Product>(list);
}

@Override
public List<Product> selectRepoer() {
	// TODO Auto-generated method stub
	return ProductMapper.selectRepoer();
}

   
}

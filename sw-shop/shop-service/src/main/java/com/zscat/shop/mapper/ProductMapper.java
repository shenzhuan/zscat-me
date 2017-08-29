package com.zscat.shop.mapper;

import java.util.List;
import java.util.Map;

import com.zsCat.common.base.MyMapper;
import com.zscat.shop.model.Product;

public interface ProductMapper extends MyMapper<Product> {


	public List<Product> selectProductByFloor(Long id);

	public List<Product> getProductByFloorid(Long tid);

	public List<Product> selectgoodsListByType(Product g);

	public List<Product> selectRepoer();
}
package com.zscat.beetl.function;

import java.util.List;

import javax.annotation.Resource;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zscat.shop.model.ProductType;
import org.springframework.stereotype.Component;
import com.zscat.shop.service.ProductTypeService;

import org.springframework.stereotype.Service;

@Service("select")
public class SelectFunctions {

	@Reference(version = "1.0.0")
	protected ProductTypeService productTypeService;
	
	/**
	 * 得到所有产品类型
	 */
	public List<ProductType> getProductType(){
		return productTypeService.select(null);
	}
	
	
	
}

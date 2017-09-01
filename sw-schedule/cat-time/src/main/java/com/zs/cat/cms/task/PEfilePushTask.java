package com.zs.cat.cms.task;


import com.zscat.shop.model.Product;
import com.zscat.shop.service.ProductService;
import org.quartz.JobExecutionContext;
import org.quartz.StatefulJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zs.cat.schedule.frame.AbstractTask;

import javax.annotation.Resource;
import java.util.List;


public class PEfilePushTask extends AbstractTask implements StatefulJob{
	
	private static final Logger logger = LoggerFactory.getLogger(PEfilePushTask.class);
	@Resource
	ProductService ProductClassService;

	
	@Override
	public String doTask(JobExecutionContext context)throws Exception {
		String result = "任务执行成功!";


		List<Product> s =ProductClassService.select(new Product());

		for (Product ss :s){
			logger.info("【SysDict模块】PEstart..."+ss.getTitle());
		}

		return result;
		
		
	}
	
}

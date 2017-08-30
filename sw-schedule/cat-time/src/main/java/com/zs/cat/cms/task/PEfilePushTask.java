package com.zs.cat.cms.task;

import com.zs.pig.base.api.model.SysDict;
import com.zs.pig.base.api.service.SysDictService;
import com.zs.pig.log.api.service.PlogService;
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
	PlogService plogService;
	@Resource
	SysDictService sysDictService;
	
	@Override
	public String doTask(JobExecutionContext context)throws Exception {
		String result = "任务执行成功!";


		List<SysDict> s =sysDictService.select(new SysDict());
		logger.info("【LOG模块】PEstart..."+plogService.zikpin1Log());
		for (SysDict ss :s){
			logger.info("【SysDict模块】PEstart..."+ss.getValue());
		}

		return result;
		
		
	}
	
}

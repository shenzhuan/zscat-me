package com.zs.cat.schedule.config.service.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.zs.cat.schedule.config.bean.TaskParamBean;
import com.zs.cat.schedule.config.dao.TaskParamBeanMapper;
import com.zs.cat.schedule.config.service.ITaskParamService;

@Component("taskParamService")
public class TaskParamService implements ITaskParamService {
	
	private static final Logger logger = LoggerFactory.getLogger(TaskParamService.class);
	
	@Resource
	TaskParamBeanMapper taskParamDAO;
	
	@Override
	public Map<String,String> queryParamsByTaskId(int taskId)
			throws Exception {
		List<TaskParamBean> beans = new ArrayList<TaskParamBean>();
		Map<String,String> map = new HashMap<String,String>();
		
		try {
			beans = taskParamDAO.selectByTaskId(taskId);
			for(TaskParamBean bean : beans){
				map.put(bean.getParamName(), bean.getParamValue());
			}
		} catch (Exception e) {
			logger.error("查询任务["+taskId+"]的参数配置信息出现异常!",e);
		}		
		return map;
	}

	@Override
	public List<TaskParamBean> queryTaskParamBeanPage(TaskParamBean paramBean) {
		
		return taskParamDAO.queryTaskParamBeanPage(paramBean);
	}

	@Override
	public int insert(TaskParamBean bean) {
		
		return taskParamDAO.insert(bean);
	}

	@Override
	public int updateTaskParamBeanByParamId(TaskParamBean bean) {
		
		return taskParamDAO.updateByPrimaryKey(bean);
	}

	@Override
	public int deleteTaskParamBeanBatch(String[] idItem) {
		
		return taskParamDAO.deleteTaskParamBeanBatch(idItem);
	}

	@Override
	public TaskParamBean queryTaskParamByParamId(int paramId) {
		
		return taskParamDAO.selectByPrimaryKey(paramId);
	}

}

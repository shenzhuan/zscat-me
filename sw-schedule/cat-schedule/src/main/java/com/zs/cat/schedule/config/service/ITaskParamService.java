package com.zs.cat.schedule.config.service;

import java.util.List;
import java.util.Map;

import com.zs.cat.schedule.config.bean.TaskParamBean;

/**
 * 任务参数服务类
 * @author 
 *
 * 2014-9-12下午4:25:47
 */
public interface ITaskParamService {
	
	public Map<String ,String> queryParamsByTaskId(int taskId) throws Exception;
	
	public List<TaskParamBean> queryTaskParamBeanPage(TaskParamBean paramBean);
	
	public TaskParamBean queryTaskParamByParamId(int paramId);
	
	int insert(TaskParamBean bean);
	
	int updateTaskParamBeanByParamId(TaskParamBean bean);
	
	int deleteTaskParamBeanBatch(String[] idItem);
}

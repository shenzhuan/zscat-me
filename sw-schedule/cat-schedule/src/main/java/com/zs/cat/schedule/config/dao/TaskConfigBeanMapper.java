package com.zs.cat.schedule.config.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.zs.cat.schedule.config.bean.TaskConfigBean;

@Component("taskConfigDAO")
public interface TaskConfigBeanMapper {

	// 新增任务配置表
	public void insertTaskConfig(TaskConfigBean taskConfig);

	// 修改任务配置表
	public void updateTaskConfig(TaskConfigBean taskConfig);

	// 删除定时任务配置（无提交）
	public void deleteTaskConfig(TaskConfigBean taskConfig);
	
	// 根据主键删除
	public void deleteTaskByKey(int configId);

	// 查找需要执行的定时任务
	public List<TaskConfigBean> getTaskConfigByType(String taskGroupFlag, String moduleId);

	// 根据主键查询
	public TaskConfigBean getTaskByKey(int configId);

	// 根据条件查询
	public List<TaskConfigBean> getTasks(TaskConfigBean taskConfig);

	//查询任务配置
	public List<TaskConfigBean> queryTaskConigPage(TaskConfigBean taskConfig);
	//批量删除
	public int deleteTaskConfigBatch(String[] idItem);
	
	public int insertSelective(TaskConfigBean taskConfig);
	
	public int updateByPrimaryKeySelective(TaskConfigBean taskConfig);
}
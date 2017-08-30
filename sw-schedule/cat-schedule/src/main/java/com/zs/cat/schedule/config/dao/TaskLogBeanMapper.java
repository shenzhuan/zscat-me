package com.zs.cat.schedule.config.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zs.cat.schedule.config.bean.TaskLogBean;

@Repository("taskLogDAO")
public interface TaskLogBeanMapper {

	//根据主键查询
	public TaskLogBean getTaskLogByKey(int taskLogId);
	
	//新增日志
	public int insertTaskLog(TaskLogBean taskLog) throws Exception;
	
	//修改日志
	public int updateTaskLog(TaskLogBean taskLog) throws Exception;
	
	// 删除日志，根据configId（无提交）
	public void deleteTaskLog(TaskLogBean taskLog) throws Exception;

	// 根据configId查找日志
	public List<TaskLogBean> getTaskLogs(TaskLogBean taskLog) throws Exception;

	//查询日志（分页）
	public List<TaskLogBean> queryTaskLogPage(TaskLogBean taskLog);

}
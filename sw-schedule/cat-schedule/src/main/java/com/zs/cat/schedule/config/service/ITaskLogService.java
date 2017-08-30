package com.zs.cat.schedule.config.service;

import java.util.List;

import com.zs.cat.schedule.config.bean.TaskLogBean;

public interface ITaskLogService {
	/**
	 * 保存配置
	 * @param taskLog
	 * @return
	 */
	public void saveOrUpdate(TaskLogBean taskLog)throws Exception;
	
	/**
	 * 删除配置
	 * @param taskLog
	 * @return
	 */
	public int deleteTaskLog(TaskLogBean taskLog)throws Exception;
	
	/**
	 * 查询配置
	 * @param taskLog
	 * @return
	 */
	public TaskLogBean getTaskLogByKey(int taskLogId)throws Exception;
	
	
	/**
	 * 查询日志（分页）
	 * @param taskLog
	 * @return
	 * @throws Exception
	 */
	public List<TaskLogBean> queryTaskLogPage(TaskLogBean taskLog) throws Exception;
	
}

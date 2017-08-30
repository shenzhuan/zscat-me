package com.zs.cat.schedule.config.service;

import java.util.List;

import com.zs.cat.schedule.config.bean.TaskConfigBean;

public interface ITaskConfigService {
	
	
	/**
	 * 查询任务配置
	 * 
	 * @param taskConfig
	 * @return
	 */
	public List<TaskConfigBean> queryTaskConigPage(TaskConfigBean taskConfig)throws Exception;
	
	/**
	 * 保存配置
	 * 
	 * @param taskConfig
	 * @return
	 */
	public void saveOrUpdate(TaskConfigBean taskConfig)throws Exception;
	
	
	/**
	 * 删除配置
	 * @param taskConfig
	 * @return
	 */
	public void deleteTaskConfig(TaskConfigBean taskConfig)throws Exception;
	
	/**
	 * 查询配置
	 * @param taskConfig
	 * @return
	 */
	public TaskConfigBean getTaskByKey(int configId)throws Exception;
	
	/**
	 * 查询配置
	 * @param taskGroupFlag 任务分组标识
	 * @param moduleId 模块标识ID
	 * @return
	 */
	public List<TaskConfigBean> getTaskConfigByType(String taskGroupFlag, String moduleId) throws Exception;

	
	public boolean delTaskConfigs(String configIdStr);
	/**
	 * 批量删除
	 * @version : 1.00
	 * @history  : 2015年8月19日 下午8:27:30 [created]
	 * @author  : guohao 郭毫
	 * @param idItem
	 * @see
	 */
	public int deleteTaskConfigBatch(String[] idItem);
	/**
	 * 新增
	 * @version : 1.00
	 * @history  : 2015年8月20日 上午10:59:47 [created]
	 * @author  : guohao 郭毫
	 * @param taskConfig
	 * @see
	 */
	public int insertSelective(TaskConfigBean taskConfig);
	/**
	 * 更新
	 * @version : 1.00
	 * @history  : 2015年8月20日 上午11:00:48 [created]
	 * @author  : guohao 郭毫
	 * @param taskConfig
	 * @see
	 */
	public int updateByPrimaryKeySelective(TaskConfigBean taskConfig);
}

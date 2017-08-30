package com.zs.cat.schedule.config.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.zs.cat.commons.util.Charset;
import com.zs.cat.schedule.config.bean.TaskConfigBean;
import com.zs.cat.schedule.config.dao.TaskConfigBeanMapper;
import com.zs.cat.schedule.config.service.ITaskConfigService;

@Service("taskConfigService")
public class TaskConfigService implements ITaskConfigService {

	private static final Logger logger = LoggerFactory.getLogger(TaskConfigService.class);
	
	@Resource
	TaskConfigBeanMapper taskConfigDAO;
	
	//查询任务配置
	@Override
	public List<TaskConfigBean> queryTaskConigPage(TaskConfigBean taskConfig) throws Exception {
		try {
			return taskConfigDAO.queryTaskConigPage(taskConfig);
		} catch (Exception e) {
			logger.error("查询任务配置异常",e);
		}
		return null;
	}
	
	// 根据任务类型和服务器号查找配置（用于后台自动运行查找时使用）
	public List<TaskConfigBean> getTaskConfigByType(String taskGroupFlag, String moduleId) throws Exception {
		try {
			return taskConfigDAO.getTaskConfigByType(taskGroupFlag, moduleId);
		} catch (Exception e) {
			logger.error("查询任务配置异常",e);
		}
		return null;
	}

	@Override
	public TaskConfigBean getTaskByKey(int configId) throws Exception {
		try {
			return taskConfigDAO.getTaskByKey(configId);
		} catch (Exception e) {
			logger.error("查询任务配置异常",e);
		}
		return null;
	}

	@Override
	public void saveOrUpdate(TaskConfigBean taskConfig) throws Exception {
		try {
			if (taskConfig != null && taskConfig.getConfigId() > 0) {
				taskConfigDAO.updateTaskConfig(taskConfig);
			} else {
				taskConfigDAO.insertTaskConfig(taskConfig);
			}
		} catch (Exception e) {
			logger.error("新增或修改任务配置异常",e);
		}
	}
	
	@Override
	public boolean delTaskConfigs(String configIdStr) {
		try {
			if (!Charset.isEmpty(configIdStr, true)) {
				String[] configIds = configIdStr.split(",");
				for (String configId : configIds) {
					taskConfigDAO.deleteTaskByKey(Integer.valueOf(configId));
				}
				return true;
			}
		} catch (Exception e) {
			logger.error("删除任务配置异常",e);
		}
		return false;
	}
	
	@Override
	public void deleteTaskConfig(TaskConfigBean taskConfig) throws Exception {
		
	}

	@Override
	public int deleteTaskConfigBatch(String[] idItem) {
		try {
			return taskConfigDAO.deleteTaskConfigBatch(idItem);
		} catch (Exception e) {
			logger.error("删除任务配置异常 configId=["+idItem+"]",e);
		}
		return 0;
		
	}

	@Override
	public int insertSelective(TaskConfigBean taskConfig) {
		try {
			return taskConfigDAO.insertSelective(taskConfig);
		} catch (Exception e) {
			logger.error("新增任务配置异常",e);
		}
		return 0;
	}

	@Override
	public int updateByPrimaryKeySelective(TaskConfigBean taskConfig) {
		
		try {
			return taskConfigDAO.updateByPrimaryKeySelective(taskConfig);
		} catch (Exception e) {
			logger.error("更新任务配置异常",e);
		}
		return 0;
	}
}
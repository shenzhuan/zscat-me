package com.zs.cat.schedule.config.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.zs.cat.schedule.config.bean.TaskLogBean;
import com.zs.cat.schedule.config.dao.TaskLogBeanMapper;
import com.zs.cat.schedule.config.service.ITaskLogService;

@Service("taskLogService")
public class TaskLogService implements ITaskLogService {

	private static final Logger logger = LoggerFactory.getLogger(TaskLogService.class);
	
	@Resource
	TaskLogBeanMapper taskLogDAO;
	
	@Override
	public void saveOrUpdate(TaskLogBean taskLog) throws Exception {
		try {
			if (taskLog != null && taskLog.getLogId() > 0) {
				taskLogDAO.updateTaskLog(taskLog);
			} else {
				taskLogDAO.insertTaskLog(taskLog);
			}
		} catch (Exception e) {
			logger.error("新增或修改任务日志异常",e);
		}
	}
	
	@Override
	public TaskLogBean getTaskLogByKey(int taskLogId) throws Exception {
		try {
			return taskLogDAO.getTaskLogByKey(taskLogId);
		} catch (Exception e) {
			logger.error("查询任务日志异常",e);
		}
		return null;
	}
	
	@Override
	public int deleteTaskLog(TaskLogBean taskLog) throws Exception {
		return 0;
	}

	
	@Override
	public List<TaskLogBean> queryTaskLogPage(TaskLogBean taskLog)
			throws Exception {
		try {
			return taskLogDAO.queryTaskLogPage(taskLog);
		} catch (Exception e) {
			logger.error("查询任务日志异常",e);
		}
		return null;
	}
}

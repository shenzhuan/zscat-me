package com.zs.cat.schedule.config.service.imp;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.zs.cat.schedule.config.bean.TaskScheduler;
import com.zs.cat.schedule.config.dao.TaskSchedulerMapper;
import com.zs.cat.schedule.config.service.ITaskSchedulerService;

@Component("taskSchedulerService")
public class TaskSchedulerService implements ITaskSchedulerService {

	private static final Logger logger = LoggerFactory.getLogger(TaskSchedulerService.class);

	@Resource
	TaskSchedulerMapper taskSchedulerDAO;

	public int deleteByPrimaryKey(int schedulerId) {
		return taskSchedulerDAO.deleteByPrimaryKey(schedulerId);
	}

	public int insert(TaskScheduler record) {
		return taskSchedulerDAO.insert(record);
	}

	public int insertSelective(TaskScheduler record) {
		return taskSchedulerDAO.insertSelective(record);
	}

	public TaskScheduler selectByPrimaryKey(int schedulerId) {
		return taskSchedulerDAO.selectByPrimaryKey(schedulerId);
	}

	public int updateByPrimaryKeySelective(TaskScheduler record) {
		return taskSchedulerDAO.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(TaskScheduler record) {
		return taskSchedulerDAO.updateByPrimaryKey(record);
	}

	public List<TaskScheduler> getTaskGroupFlag(Map<String, Object> paramsMap) {
		return taskSchedulerDAO.getTaskGroupFlag(paramsMap);
	}

}

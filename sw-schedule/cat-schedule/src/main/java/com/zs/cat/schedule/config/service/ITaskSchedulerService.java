package com.zs.cat.schedule.config.service;

import java.util.List;
import java.util.Map;

import com.zs.cat.schedule.config.bean.TaskScheduler;

public interface ITaskSchedulerService {
	
    int deleteByPrimaryKey(int schedulerId);

    int insert(TaskScheduler record);

    int insertSelective(TaskScheduler record);

    TaskScheduler selectByPrimaryKey(int schedulerId);

    int updateByPrimaryKeySelective(TaskScheduler record);

	int updateByPrimaryKey(TaskScheduler record);

	public List<TaskScheduler> getTaskGroupFlag(Map<String, Object> paramsMap);

}
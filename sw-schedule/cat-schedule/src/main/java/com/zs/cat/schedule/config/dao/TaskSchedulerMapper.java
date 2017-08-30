package com.zs.cat.schedule.config.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zs.cat.schedule.config.bean.TaskScheduler;

@Repository("taskSchedulerDAO")
public interface TaskSchedulerMapper {
    int deleteByPrimaryKey(int schedulerId);

    int insert(TaskScheduler record);

    int insertSelective(TaskScheduler record);

    TaskScheduler selectByPrimaryKey(int schedulerId);

    int updateByPrimaryKeySelective(TaskScheduler record);

	int updateByPrimaryKey(TaskScheduler record);

	public List<TaskScheduler> getTaskGroupFlag(Map<String, Object> paramsMap);

}
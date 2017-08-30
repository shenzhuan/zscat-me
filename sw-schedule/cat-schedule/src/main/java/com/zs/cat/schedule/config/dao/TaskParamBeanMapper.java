package com.zs.cat.schedule.config.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zs.cat.schedule.config.bean.TaskParamBean;
@Repository("taskParamDAO")
public interface TaskParamBeanMapper {
    int deleteByPrimaryKey(int paramId);

    int insert(TaskParamBean record);

    int insertSelective(TaskParamBean record);

    TaskParamBean selectByPrimaryKey(int paramId);

    int updateByPrimaryKeySelective(TaskParamBean record);

    int updateByPrimaryKey(TaskParamBean record);
    
    List<TaskParamBean> selectByTaskId(int taskId);
    
    int deleteTaskParamBeanBatch(String[] idItem);
    
    List<TaskParamBean> queryTaskParamBeanPage(TaskParamBean record);
}
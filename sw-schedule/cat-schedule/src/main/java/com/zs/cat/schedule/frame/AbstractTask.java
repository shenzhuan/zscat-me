package com.zs.cat.schedule.frame;


import java.util.Date;

import javax.annotation.Resource;

import org.quartz.InterruptableJob;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.UnableToInterruptJobException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.zs.cat.commons.util.Charset;
import com.zs.cat.schedule.config.bean.TaskConfigBean;
import com.zs.cat.schedule.config.bean.TaskLogBean;
import com.zs.cat.schedule.config.service.ITaskConfigService;
import com.zs.cat.schedule.config.service.ITaskLogService;

/**
 * 工作任务的模块
 * 实现InterruptableJob类
 * @author 
 * @date 2014-3-20 下午8:52:00
 */
public abstract class AbstractTask implements InterruptableJob{

	private Logger log = LoggerFactory.getLogger(AbstractTask.class);
	
	private boolean isInterrupt;//是否要中断
	@Resource
	ITaskConfigService taskConfigService;
	@Resource
	ITaskLogService taskLogService;
	
	protected ApplicationContext getApplicationContext(JobExecutionContext context) throws Exception {
		ApplicationContext appCtx = (ApplicationContext) context.getScheduler().getContext().get(TaskConstantUtils.APPLICATION_CONTEXT_KEY);
		if (appCtx == null) {
			log.error("无可用的spring上下文!");
			throw new JobExecutionException("无可用的spring上下文!");
		}
		return appCtx;
	}
		
	/**
	 * 1.更新任务状态
	 * 2.记录任务工作日志
	 * @return taskLogId 日志ID
	 */
	public int preTask(JobExecutionContext context) throws JobExecutionException{
		int taskLogId = -1;
		try {
			//更新任务状态为运行中
			String taskName = context.getJobDetail().getName();
			if(Charset.isEmpty(taskName)){//任务名称不能为空
				log.error("任务名称不能为空!");
				throw new JobExecutionException("任务名称不能为空!");
			}
			 TaskConfigBean configBean = taskConfigService.getTaskByKey(Integer.parseInt(taskName));
	         configBean.setRunState(TaskConstantUtils.TASK_RUNNING);//设置运行状态为运行中
	         configBean.setLastRunDate(new Date());//设置最近执行时间为当前时间
			 taskConfigService.saveOrUpdate(configBean);
			//记录任务运行日志
			taskLogId = this.saveStartTaskLog(context,configBean);
		} catch (Exception e) {
			log.error("任务执行准备工作执行异常!",e);
			throw new JobExecutionException("任务执行准备工作执行异常!");
		}
		return taskLogId;
	}
	
	@Override
	public void execute(JobExecutionContext context)throws JobExecutionException {
		if(!isInterrupt){
			int taskLogId = -1;
			String result = null;
			String taskName = context.getJobDetail().getName();
			try{
			    if(!"0".equals(taskName)){
		            taskLogId = this.preTask(context);
	                result = this.doTask(context);
	                this.afterSuccessTask(context,taskLogId,result);
			    }else{
			        result = this.doTask(context);
			    }

			}catch(Exception e){
				e.printStackTrace();
				if(!"0".equals(taskName)){
				    this.afterFailTask(context,taskLogId,result);
				}
				log.error(e.getMessage());
			}
		}else{
			log.debug("任务已经被中断运行...");
		}
			
	}
	/**
	 * 任务实现方法
	 * 具体由子类实现
	 */
	public abstract String doTask(JobExecutionContext context) throws Exception;
	
	/**
	 * 1.更新任务状态
	 * 2.记录任务工作日志
	 * @return taskLogId 日志ID
	 */
	public void afterSuccessTask(JobExecutionContext context,int taskLogId,String result) throws JobExecutionException{			
		try {
			//更新任务状态为运行成功
			String taskName = context.getJobDetail().getName();
			if(Charset.isEmpty(taskName)){//任务名称不能为空
				log.error("任务名称不能为空!");
				throw new JobExecutionException("任务名称不能为空!");
			}
             TaskConfigBean configBean = taskConfigService.getTaskByKey(Integer.parseInt(taskName));
             configBean.setRunState(TaskConstantUtils.TASK_COMPLETED);//设置运行状态为运行完成
             configBean.setLastRunDate(new Date());//设置最近执行时间为当前时间
             taskConfigService.saveOrUpdate(configBean);
			//记录任务运行日志
			this.updateSuccessTaskLog(context,taskLogId, result);		
		} catch (Exception e) {
			log.error("任务执行准备工作执行异常!",e);
			throw new JobExecutionException("任务执行准备工作执行异常!");
		}
	}
	
	/**
	 * 1.更新任务状态
	 * 2.记录任务工作日志
	 * @return taskLogId 日志ID
	 */
	public void afterFailTask(JobExecutionContext context,int taskLogId,String result) throws JobExecutionException{				
		try {
			//更新任务状态为运行失败
			String taskName = context.getJobDetail().getName();
			if(Charset.isEmpty(taskName)){//任务名称不能为空
				log.error("任务名称不能为空!");
				throw new JobExecutionException("任务名称不能为空!");
			}
            TaskConfigBean configBean = taskConfigService.getTaskByKey(Integer.parseInt(taskName));
            configBean.setRunState(TaskConstantUtils.TASK_COMPLETED);//设置运行状态为运行完成
            configBean.setLastRunDate(new Date());//设置最近执行时间为当前时间
            taskConfigService.saveOrUpdate(configBean);
			//记录任务运行日志
			this.updateFailTaskLog(context,taskLogId, result);		
		} catch (Exception e) {
			log.error("任务执行准备工作执行异常!",e);
			throw new JobExecutionException("任务执行准备工作执行异常!");
		}	
	}
	
	/**
	 * 保存任务开始时日志
	 * @param logBean
	 * @return
	 */
	private int saveStartTaskLog(JobExecutionContext context,TaskConfigBean configBean) throws Exception{
		TaskLogBean logBean = new TaskLogBean();
		logBean.setConfigId(configBean.getConfigId());
		logBean.setState(TaskConstantUtils.TASK_RUNNING);
		logBean.setBeginDate(configBean.getLastRunDate());
		taskLogService.saveOrUpdate(logBean);
		return logBean.getLogId();
	}
	
	/**
	 * 任务运行成功时更新日志
	 */
	private void updateSuccessTaskLog(JobExecutionContext context,int taskLogId,String result) throws Exception{
		TaskLogBean logBean =taskLogService.getTaskLogByKey(taskLogId) ;
		logBean.setState(TaskConstantUtils.TASK_COMPLETED);
		logBean.setEndDate(new Date());
		logBean.setResults(result);
	
		taskLogService.saveOrUpdate(logBean);
	}
	
	/**
	 * 任务失败时更新日志 
	 * @param configBean
	 * @return
	 */
	private void updateFailTaskLog(JobExecutionContext context,int taskLogId,String result) throws Exception{
		TaskLogBean logBean =taskLogService.getTaskLogByKey(taskLogId);
		logBean.setState(TaskConstantUtils.TASK_EXCEPTION);
        logBean.setEndDate(new Date());
        logBean.setResults(result);
		taskLogService.saveOrUpdate(logBean);
	}
	
	@Override
	public void interrupt() throws UnableToInterruptJobException {
		this.setInterrupt(true);
	}

	public boolean isInterrupt() {
		return isInterrupt;
	}

	public void setInterrupt(boolean isInterrupt) {
		this.isInterrupt = isInterrupt;
	}
}

package com.zs.cat.schedule.frame;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.impl.StdScheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zs.cat.schedule.config.bean.TaskConfigBean;


public class TaskFactory{
	
	private static String DEFAULT_GROUP = "BPPF_CSS";
	private static Logger log = LoggerFactory.getLogger(TaskFactory.class);

	/**
	 * 加入一个调度对象
	* @param taskConfigId 任务调度id
	 * @param cron 执行时间
	 * @param classPath  执行类路径
	 * @throws Exception 
	 */
	public static boolean addTask(JobExecutionContext context,TaskConfigBean taskConfig) throws Exception{
		boolean flag = false;
		
		int taskConfigId  = taskConfig.getConfigId();
		String cron = taskConfig.getTaskCron();
		String classPath = taskConfig.getTaskClass();
		Integer priority=taskConfig.getPriority();
		
		String jobName = String.valueOf(taskConfigId);
		try{
			@SuppressWarnings("rawtypes")
			Class cName = Class.forName(classPath);
			if(cName == null){
				return false;
			}			
			JobDetail jobDetail = new JobDetail(jobName,DEFAULT_GROUP,cName);
			jobDetail.getJobDataMap().put("JOB_NAME",jobName);
			
			CronTrigger trigger = new CronTrigger(jobName, DEFAULT_GROUP, cron);
			if (priority != null && priority > 0) {
				trigger.setPriority(priority);
				log.debug("调度任务使的优先级：priority=" + priority);
			} else {
				log.debug("调度任务使用默认的优先级：priority=" + CronTrigger.DEFAULT_PRIORITY);
			}
			
			context.getScheduler().scheduleJob(jobDetail,trigger);
			if(log.isDebugEnabled()){
				log.debug("加入调度对象成功：taskConfigId="+jobName+",cron="+cron);
			}
			flag = true;
			
		}catch (Exception e) {
			String errorMsg="加入调度对象异常：taskConfigId="+jobName+",cron="+cron;
			if(log.isErrorEnabled()){
				log.error(errorMsg,e);
			}
			throw new Exception(errorMsg+e.getMessage());
		}
		return flag;
	}
	
	
	
	/**
	 * 删除一个调度对象
	 * @param jobName 调度名称
	 * @throws Exception 
	 */
	public static boolean removeTask(StdScheduler scheduler,TaskConfigBean taskConfig) throws Exception{
		boolean flag = false;
		int taskConfigId = taskConfig.getConfigId();
		String jobName = String.valueOf(taskConfigId);
		try {
		    scheduler.interrupt(jobName, DEFAULT_GROUP);
			flag =scheduler.deleteJob(jobName,DEFAULT_GROUP);
			if(log.isDebugEnabled())
			log.debug("删除调度对象成功：taskConfigId="+jobName);
		} catch (Exception e) {
			String errorMsg="删除调度对象异常：taskConfigId="+jobName;
			if(log.isErrorEnabled())
			log.error(errorMsg,e);
			throw new Exception(errorMsg+e.getMessage());
		}
		return flag;
	}
	
	
	/**
	 * 是否存在一个调度对象
	 * @param taskConfigId 任务调度id
	 * @throws Exception 
	 */
	public static boolean isExsitsTask(JobExecutionContext context,int taskConfigId) throws Exception{
		boolean flag = false;
		String jobName = String.valueOf(taskConfigId);
		try {			
			JobDetail job = context.getScheduler().getJobDetail(jobName,DEFAULT_GROUP);
			if(job != null){
				flag = true;
			}else{
				flag = false;
			}
		} catch (Exception e) {
			String errorMsg="判断任务对象是否已经存在异常：taskConfigId="+jobName;
			if(log.isErrorEnabled())
			log.error(errorMsg,e);
			throw new Exception(errorMsg+e.getMessage());
		}
		return flag;
	}
}

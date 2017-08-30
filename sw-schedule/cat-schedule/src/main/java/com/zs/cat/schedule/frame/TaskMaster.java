package com.zs.cat.schedule.frame;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.zs.cat.schedule.config.bean.TaskConfigBean;
import com.zs.cat.schedule.config.bean.TaskScheduler;
import com.zs.cat.schedule.config.service.ITaskConfigService;
import com.zs.cat.schedule.config.service.ITaskSchedulerService;

@Service("taskMaster")
public class TaskMaster extends AbstractTask {

	private static Logger log = LoggerFactory.getLogger(TaskMaster.class);
	boolean is = true;

	@Resource
	ITaskConfigService taskConfigService;
	
	@Resource(name = "taskSchedulerService")
	ITaskSchedulerService taskSchedulerService;
	
	@Override
	public String doTask(JobExecutionContext context) throws JobExecutionException {
		String result = "任务主程序开始执行...";
		log.debug(result);
		int sCount = 0;// 加载成功数
		int fCount = 0;// 加载失败数
		JobDataMap params = context.getJobDetail().getJobDataMap();
		int schedulerId = params.getInt("SCHEDULER_ID");
		// 更新心跳时间
		TaskScheduler scheduler = taskSchedulerService.selectByPrimaryKey(schedulerId);
		scheduler.setModifyTime(new Date());
		taskSchedulerService.updateByPrimaryKeySelective(scheduler);
		// 取出真正要执行的JOB
		List<TaskConfigBean> taskConfigs = new ArrayList<TaskConfigBean>();
		try {
			taskConfigs = taskConfigService.getTaskConfigByType(scheduler.getTaskGroupFlag(), scheduler.getModuleId());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("查询任务配置列表出错!");
		}
		if (taskConfigs == null || taskConfigs.size() == 0) {
			result = "无需要执行的任务";
			log.debug(result);
			return result;
		}
		for (TaskConfigBean taskConfig : taskConfigs) {
			try {
				int taskConfigId = taskConfig.getConfigId();
				if (!TaskFactory.isExsitsTask(context, taskConfigId)) {
					String taskCron = taskConfig.getTaskCron();
					String taskClass = taskConfig.getTaskClass();
					if (StringUtils.isBlank(taskCron)) {
						log.error("任务ID:" + taskConfig.getConfigId()+ ",配置的周期执行时间为空!");
						continue;
					}
					if (StringUtils.isBlank(taskClass)) {
						log.error("任务ID:" + taskConfig.getConfigId()+ ",配置的业务执行类为空!");
						continue;
					}
					boolean isSucc = TaskFactory.addTask(context, taskConfig);
					if (isSucc) {
						sCount++;
					} else {
						fCount++;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("初始化加载任务配置出错！" + e.getMessage());
				continue;
			}
		}
		if (log.isDebugEnabled()) {
			log.debug("任务加载器已加载" + sCount + "个调度任务对象,加载失败" + fCount
					+ "个调度任务....");
		}

		return result;
	}
}
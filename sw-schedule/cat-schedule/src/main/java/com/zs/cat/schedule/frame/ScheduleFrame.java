package com.zs.cat.schedule.frame;


import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Assert;

import com.google.common.collect.Maps;
import com.zs.cat.commons.util.IpGetter;
import com.zs.cat.schedule.config.bean.TaskScheduler;
import com.zs.cat.schedule.config.service.ITaskSchedulerService;

@Slf4j
public class ScheduleFrame {
	
	private final int HEART_BEAT_TIMEOUT = 5;// 多少分钟为心跳超时
	private int schedulerId=0;// 任务分组ID
	public Scheduler scheduler;
	@Resource(name = "taskSchedulerService")
	ITaskSchedulerService taskSchedulerService;
	@Value("${JOB_GROUP}")
	String jobGroup;
	@Value("${MODULE_ID}")
	String moduleId;// 用于取出【指定模块】的任务去调度
	@Value("${JOB_CLASS}")
	String jobClass;
	@Value("${JOB_CRON}")
	String jobCron;
	@Value("${JOB_NAME}")
	String jobName;
	@Value("${zooKeeper.schedule.URL}")
	String zooKeeperURL;
	@Value("${zooKeeper.schedule.groupPath}")
	String groupPath;
	@Value("${zooKeeper.schedule.subPath}")
	String subPath;

	/**
	 * 初始化定时调度框架
	 * @throws Exception
	 */
	public void initScheduleFrame() throws Exception {
		Assert.hasText(jobName, "'JOB_NAME' must not be empty");
		String localIP = IpGetter.getLocalIP();
		// 取定时任务分组标识
		while (this.schedulerId == 0) {
			this.schedulerId = this.getSchedulerId();
			if (this.schedulerId == 0) {
				log.debug("IP={}的机器，没有取到定时任务分组标识！！！", localIP);
				Thread.sleep(60 * 1000);
			} else {
				break;
			}
		}
		log.info("IP={}的机器，取到定时任务分组id是:{}", localIP, this.schedulerId);
		try {
			JobDetail jobDetail = new JobDetail(jobName, jobGroup, Class.forName(jobClass));
			jobDetail.getJobDataMap().put("JOB_NAME", jobName);
			jobDetail.getJobDataMap().put("SCHEDULER_ID", this.schedulerId);
			CronTrigger trigger = new CronTrigger(jobName, jobGroup, jobCron);
			scheduler.scheduleJob(jobDetail, trigger);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 定义获得锁后要做的事 */
	private int getSchedulerId() throws Exception {
		DistributedLock.Task lockTask = new DistributedLock.Task() {
			public String run() {
				Date modifyTime = DateUtils.addMinutes(new Date(), -HEART_BEAT_TIMEOUT);
				Map<String, Object> paramsMap = Maps.newHashMap();
				paramsMap.put("moduleId", moduleId);
				paramsMap.put("modifyTime", modifyTime);
				List<TaskScheduler> list = taskSchedulerService.getTaskGroupFlag(paramsMap);
				String result = null;
				if (list != null && !list.isEmpty()) {
					TaskScheduler taskScheduler = list.get(0);
					taskScheduler.setHostIp(IpGetter.getLocalIP());
					taskScheduler.setModifyTime(new Date());
					result = taskScheduler.getSchedulerId()+"";
					taskSchedulerService.updateByPrimaryKeySelective(taskScheduler);
				}
				return result;
			}
        };
        DistributedLock lock = new DistributedLock(zooKeeperURL, groupPath, subPath, lockTask);
        String nodeData = "schedulerNodeInfo ===> moduleId=" + moduleId + ", createdIP=" + IpGetter.getLocalIP();
        String schedulerIdStr = lock.getLockDoTask(nodeData, true);
        return NumberUtils.toInt(schedulerIdStr);
    }
	
	public Scheduler getScheduler() {
		return scheduler;
	}

	public void setScheduler(Scheduler scheduler) {
		this.scheduler = scheduler;
	}
}

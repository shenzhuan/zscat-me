package com.zs.cat.schedule.config.bean;

import java.util.Date;

public class TaskScheduler {
    private int schedulerId;

    private String moduleId;

    private String taskGroupFlag;

    private String hostIp;

    private Date modifyTime;

    /**
     * Y/N 判断该任务可否在多台机器同时运行
     */
    private String isShare;

    public int getSchedulerId() {
        return schedulerId;
    }

    public void setSchedulerId(int schedulerId) {
        this.schedulerId = schedulerId;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId == null ? null : moduleId.trim();
    }

    public String getTaskGroupFlag() {
        return taskGroupFlag;
    }

    public void setTaskGroupFlag(String taskGroupFlag) {
        this.taskGroupFlag = taskGroupFlag == null ? null : taskGroupFlag.trim();
    }

    public String getHostIp() {
        return hostIp;
    }

    public void setHostIp(String hostIp) {
        this.hostIp = hostIp == null ? null : hostIp.trim();
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getIsShare() {
        return isShare;
    }

    public void setIsShare(String isShare) {
        this.isShare = isShare;
    }
}
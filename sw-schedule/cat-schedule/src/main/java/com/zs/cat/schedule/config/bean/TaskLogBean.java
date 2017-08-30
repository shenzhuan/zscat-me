package com.zs.cat.schedule.config.bean;

import java.io.Serializable;
import java.util.Date;

public class TaskLogBean implements Serializable{

	
	// 表字段映射
	private int logId;
	private int configId;
	private int state;
	private String results;
	private int sucessNum;
	private int failNum;
	private Date beginDate;
	private Date endDate;
	private String remarks;
	
	public int getLogId() {
		return logId;
	}

	public void setLogId(int logId) {
		this.logId = logId;
	}

	public int getConfigId() {
		return configId;
	}

	public void setConfigId(int configId) {
		this.configId = configId;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getResults() {
		return results;
	}

	public void setResults(String results) {
		this.results = results;
	}

	public int getSucessNum() {
		return sucessNum;
	}

	public void setSucessNum(int sucessNum) {
		this.sucessNum = sucessNum;
	}

	public int getFailNum() {
		return failNum;
	}

	public void setFailNum(int failNum) {
		this.failNum = failNum;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}

/**
 * 
 */
package com.mallplus.common.entity.sms;

import com.mallplus.common.entity.BaseEntity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 营销模块 - 市场推送实体类定义
 * @author yang.liu
 */
public class UserFormId extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 4565156634543900140L;
	
	private Long id;
	private Long userId;//用户ID
	private String formId;//小程序推送formId
	private Integer source;//来源 1，步数兑换海贝按钮；2，首页邀请按钮；3，步数拦截弹窗邀请按钮；4，兑换商品按钮；5，海贝不够邀请按钮；6，引导关注蒙层按钮；7，健康体验领取按钮
	private Integer status;//使用状态 1 未使用[默认] 2 已使用
	private Timestamp createTime;//创建时间
	private Timestamp updateTime;//修改时间
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getFormId() {
		return formId;
	}
	public void setFormId(String formId) {
		this.formId = formId;
	}
	public Integer getSource() {
		return source;
	}
	public void setSource(Integer source) {
		this.source = source;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	
	
}

package com.mallplus.common.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 后台用户权限表
 * </p>
 *
 * @author zscat
 * @since 2019-04-14
 */
@TableName("sys_permission")
public class SysPermission  implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.AUTO)
	private Long id;

	/**
	 * 父级权限id
	 */
	private Long pid;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 权限值
	 */
	private String value;

	/**
	 * 图标
	 */
	private String icon;

	/**
	 * 权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）
	 */
	private Integer type;

	/**
	 * 前端资源路径
	 */
	private String uri;

	/**
	 * 启用状态；0->禁用；1->启用
	 */
	private Integer status;

	/**
	 * 创建时间
	 */
	@TableField("create_time")
	private Date createTime;

	/**
	 * 排序
	 */
	private Integer sort;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@TableField(exist = false)
	private String label;

	public String getLabel() {
		return name;
	}

	public void setLabel(String label) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "SysPermission{" +
				", id=" + id +
				", pid=" + pid +
				", name=" + name +
				", value=" + value +
				", icon=" + icon +
				", type=" + type +
				", uri=" + uri +
				", status=" + status +
				", createTime=" + createTime +
				", sort=" + sort +
				"}";
	}
}

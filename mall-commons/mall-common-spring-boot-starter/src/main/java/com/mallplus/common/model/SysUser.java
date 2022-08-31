package com.mallplus.common.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * @author mall
 * 用户实体
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_user")
public class SysUser extends SuperEntity {
	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.AUTO)
	private Long id;

	private String username;

	private String password;

	/**
	 * 头像
	 */
	private String icon;

	/**
	 * 邮箱
	 */
	private String email;

	/**
	 * 昵称
	 */
	@TableField("nick_name")
	private String nickName;

	/**
	 * 备注信息
	 */
	private String note;

	/**
	 * 创建时间
	 */
	@TableField("create_time")
	private Date createTime;

	/**
	 * 最后登录时间
	 */
	@TableField("login_time")
	private Date loginTime;

	/**
	 * 帐号启用状态：0->禁用；1->启用
	 */
	private Integer status;

	/**
	 * 供应商
	 */
	@TableField("supply_id")
	private Long supplyId;


	//角色
	@TableField(exist = false)
	private String roleIds;


	@TableField(exist = false)
	private String confimpassword;

	@TableField(exist = false)
	private String oldPassword;
	@TableField(exist = false)
	private String newPassword;

	@TableField(exist = false)
	private String code;
	@TableField(exist = false)
	List<SysRole> roles;

	private Boolean enabled;

	@TableField(exist = false)
	private String roleId;

}


package com.mallplus.common.entity.fenxiao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author mallplus
 * @date 2019-12-17
 * 分销审核
 */
@Data
@TableName("fenxiao_checks")
public class FenxiaoChecks implements Serializable {


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;


    @TableField("member_id")
    private Integer memberId;


    @TableField("phone")
    private String phone;


    @TableField("status")
    private String status;


    @TableField("create_time")
    private Date createTime;


    @TableField("update_time")
    private Date updateTime;


}

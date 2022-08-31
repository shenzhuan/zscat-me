package com.mallplus.common.entity.toupiao.entity;

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
 * 投票项目
 */
@Data
@TableName("toupiao_project")
public class ToupiaoProject implements Serializable {


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;


    @TableField("type")
    private Integer type;


    @TableField("status")
    private Integer status;


    @TableField("name")
    private String name;


    @TableField("remark")
    private String remark;


    @TableField("start_time")
    private Date startTime;


    @TableField("end_time")
    private Date endTime;


    @TableField("create_time")
    private Date createTime;


}

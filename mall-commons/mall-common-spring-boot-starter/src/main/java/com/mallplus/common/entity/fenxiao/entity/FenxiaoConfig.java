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
 * 分销配置
 */
@Data
@TableName("fenxiao_config")
public class FenxiaoConfig implements Serializable {


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;


    @TableField("status")
    private Integer status;


    @TableField("type")
    private Integer type;


    @TableField("fan_percent")
    private Integer fanPercent;


    @TableField("fan_type")
    private Integer fanType;


    @TableField("withdraw_type")
    private Integer withdrawType;


    @TableField("one_percent")
    private Integer onePercent;


    @TableField("two_percent")
    private Integer twoPercent;


    @TableField("three_percent")
    private Integer threePercent;


    @TableField("create_time")
    private Date createTime;


}

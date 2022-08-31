package com.mallplus.common.entity.jifen.entity;

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
 * 积分券
 */
@Data
@TableName("jifen_coupon")
public class JifenCoupon implements Serializable {


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;


    @TableField("valid_day")
    private Integer validDay;


    @TableField("min_integration")
    private Integer minIntegration;


    @TableField("max_integration")
    private Integer maxIntegration;

    private Integer count;

    @TableField("remark")
    private String remark;


    @TableField("create_time")
    private Date createTime;


}

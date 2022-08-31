package com.mallplus.common.entity.jifen.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author mallplus
 * @date 2019-12-17
 * 积分赠送规则
 */
@Data
@TableName("jifen_donate_rule")
public class JifenDonateRule implements Serializable {


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;


    @TableField("donate_type")
    private String donateType;


    @TableField("donate_condtion")
    private BigDecimal donateCondtion;


    @TableField("donate_integration")
    private Integer donateIntegration;


}

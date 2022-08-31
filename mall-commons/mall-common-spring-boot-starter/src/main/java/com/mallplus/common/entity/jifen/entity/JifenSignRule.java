package com.mallplus.common.entity.jifen.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author mallplus
 * @date 2019-12-17
 * 积分签到规则
 */
@Data
@TableName("jifen_sign_rule")
public class JifenSignRule implements Serializable {


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;


    @TableField("contineu_count")
    private Integer contineuCount;


    @TableField("donate_integrtion")
    private Integer donateIntegrtion;


}

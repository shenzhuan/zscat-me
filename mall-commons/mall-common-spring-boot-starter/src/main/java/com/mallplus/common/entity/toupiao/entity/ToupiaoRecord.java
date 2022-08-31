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
 * 投票记录
 */
@Data
@TableName("toupiao_record")
public class ToupiaoRecord implements Serializable {


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;


    @TableField("phone")
    private String phone;


    @TableField("toupiao_id")
    private Long toupiaoId;


    @TableField("create_time")
    private Date createTime;


}

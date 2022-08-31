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
 * 分销记录
 */
@Data
@TableName("fenxiao_records")
public class FenxiaoRecords implements Serializable {


    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;


    @TableField("order_id")
    private Integer orderId;


    @TableField("member_id")
    private Integer memberId;


    @TableField("invite_id")
    private Integer inviteId;


    @TableField("money")
    private String money;


    @TableField("level")
    private String level;


    @TableField("status")
    private String status;


    @TableField("create_time")
    private Date createTime;


}

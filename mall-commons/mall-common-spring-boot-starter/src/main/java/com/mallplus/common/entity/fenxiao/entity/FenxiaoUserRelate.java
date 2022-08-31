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
 * 分销关系
 */
@Data
@TableName("fenxiao_user_relate")
public class FenxiaoUserRelate implements Serializable {


    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;


    @TableField("member_id")
    private Integer memberId;


    @TableField("invite_id")
    private Integer inviteId;


    @TableField("level")
    private Integer level;


    @TableField("create_time")
    private Date createTime;


}

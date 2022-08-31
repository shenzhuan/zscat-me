package com.mallplus.common.entity.ums;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author zscat
 * @since 2019-07-02
 */
@Data
@TableName("ums_reward_log")
public class UmsRewardLog implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Long objid;
    @TableField("send_member_id")
    private Long sendMemberId;
    /**
     * 用户名
     */
    @TableField("member_nick_name")
    private String memberNickName;

    /**
     * 用户图标
     */
    @TableField("member_icon")
    private String memberIcon;


    @TableField("rec_member_id")
    private Long recMemberId;

    private Integer coin;

    @TableField("create_time")
    private Date createTime;


}

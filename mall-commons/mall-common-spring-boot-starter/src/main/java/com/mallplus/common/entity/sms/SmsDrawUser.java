package com.mallplus.common.entity.sms;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 抽奖与用户关联表
 * </p>
 *
 * @author zscat
 * @since 2019-10-17
 */
@Data
@TableName("sms_draw_user")
public class SmsDrawUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long id;

    /**
     * 拼团ID
     */
    @TableField("draw_id")
    private Long drawId;

    /**
     * 用户ID
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 用户参团时间
     */
    private Date
            time;

    /**
     * 用户角色（默认 0：团长  userid:该用户分享进来的用户）
     */
    private String role;

    /**
     * 抽奖状态（0.参团中 1.待抽奖 2.参团失败 3.抽奖失败 4.抽奖成功）
     */
    @TableField("lottery_status")
    private Integer lotteryStatus;


}

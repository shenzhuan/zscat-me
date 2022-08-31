package com.mallplus.common.entity.sms;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mallplus.common.entity.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 红包
 * </p>
 *
 * @author zscat
 * @since 2019-04-19
 */
@Data
@TableName("sms_red_packet")
public class SmsRedPacket extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 红包编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 发红包的用户id
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 红包金额
     */
    private BigDecimal amount;

    /**
     * 发红包日期
     */
    @TableField("send_date")
    private Date sendDate;

    /**
     * 红包总数
     */
    private Integer total;

    /**
     * 单个红包的金额
     */
    @TableField("unit_amount")
    private BigDecimal unitAmount;

    /**
     * 红包剩余个数
     */
    private Integer stock;

    /**
     * 红包类型
     */
    private Integer type;

    /**
     * 备注
     */
    private String note;

    @TableField(exist = false)
    private Integer status; // 1 已领取 2 未领取
    @TableField(exist = false)
    private BigDecimal reciveAmount;

}

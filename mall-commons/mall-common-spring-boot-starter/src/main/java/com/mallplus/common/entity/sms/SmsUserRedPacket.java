package com.mallplus.common.entity.sms;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mallplus.common.entity.BaseEntity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 用户红包
 * </p>
 *
 * @author zscat
 * @since 2019-04-19
 */
@TableName("sms_user_red_packet")
public class SmsUserRedPacket extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户红包编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 红包编号
     */
    @TableField("red_packet_id")
    private Integer redPacketId;

    /**
     * 发红包用户的编号
     */
    @TableField("admin_id")
    private Long adminId;

    /**
     * 抢红包用户的编号
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 抢到的红包金额
     */
    private BigDecimal amount;

    /**
     * 抢红包时间
     */
    @TableField("grab_time")
    private Date grabTime;

    /**
     * 备注
     */
    private String note;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRedPacketId() {
        return redPacketId;
    }

    public void setRedPacketId(Integer redPacketId) {
        this.redPacketId = redPacketId;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getGrabTime() {
        return grabTime;
    }

    public void setGrabTime(Date grabTime) {
        this.grabTime = grabTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "SmsUserRedPacket{" +
        ", id=" + id +
        ", redPacketId=" + redPacketId +
        ", adminId=" + adminId +
        ", userId=" + userId +
        ", amount=" + amount +
        ", grabTime=" + grabTime +
        ", note=" + note +
        "}";
    }
}

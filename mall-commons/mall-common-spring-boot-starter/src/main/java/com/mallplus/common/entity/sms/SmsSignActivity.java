package com.mallplus.common.entity.sms;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 签到活动
 * </p>
 *
 * @author zscat
 * @since 2019-10-17
 */
@TableName("sms_sign_activity")
public class SmsSignActivity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 图片
     */
    private String image;

    /**
     * 签到活动开始时间
     */
    private String starttime;

    /**
     * 签到活动结束时间
     */
    private String endtime;

    /**
     * 签到活动详情
     */
    private String detail;

    /**
     * 修改时间
     */
    @TableField("add_time")
    private LocalDateTime addTime;

    /**
     * 状态 0：未启用 1：启用 2：已结束
     */
    private Integer status;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public LocalDateTime getAddTime() {
        return addTime;
    }

    public void setAddTime(LocalDateTime addTime) {
        this.addTime = addTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SmsSignActivity{" +
                ", id=" + id +
                ", image=" + image +
                ", starttime=" + starttime +
                ", endtime=" + endtime +
                ", detail=" + detail +
                ", addTime=" + addTime +
                ", status=" + status +
                "}";
    }
}

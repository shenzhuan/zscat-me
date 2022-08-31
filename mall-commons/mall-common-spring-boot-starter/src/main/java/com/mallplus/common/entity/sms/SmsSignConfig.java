package com.mallplus.common.entity.sms;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 签到配置表
 * </p>
 *
 * @author zscat
 * @since 2019-10-17
 */
@TableName("sms_sign_config")
public class SmsSignConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 插件id
     */
    @TableField("plug_ins_id")
    private Long plugInsId;

    /**
     * 图片
     */
    private String imgurl;

    /**
     * 领取的最少积分
     */
    @TableField("min_score")
    private Integer minScore;

    /**
     * 领取的最大积分
     */
    @TableField("max_score")
    private Integer maxScore;

    /**
     * 连续签到7天
     */
    @TableField("continuity_three")
    private Integer continuityThree;

    /**
     * 连续签到20天
     */
    @TableField("continuity_twenty")
    private Integer continuityTwenty;

    /**
     * 连续签到30天
     */
    @TableField("continuity_thirty")
    private Integer continuityThirty;

    /**
     * 活动过期删除时间
     */
    @TableField("activity_overdue")
    private Integer activityOverdue;

    /**
     * 修改时间
     */
    @TableField("modify_date")
    private LocalDateTime modifyDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPlugInsId() {
        return plugInsId;
    }

    public void setPlugInsId(Long plugInsId) {
        this.plugInsId = plugInsId;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public Integer getMinScore() {
        return minScore;
    }

    public void setMinScore(Integer minScore) {
        this.minScore = minScore;
    }

    public Integer getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(Integer maxScore) {
        this.maxScore = maxScore;
    }

    public Integer getContinuityThree() {
        return continuityThree;
    }

    public void setContinuityThree(Integer continuityThree) {
        this.continuityThree = continuityThree;
    }

    public Integer getContinuityTwenty() {
        return continuityTwenty;
    }

    public void setContinuityTwenty(Integer continuityTwenty) {
        this.continuityTwenty = continuityTwenty;
    }

    public Integer getContinuityThirty() {
        return continuityThirty;
    }

    public void setContinuityThirty(Integer continuityThirty) {
        this.continuityThirty = continuityThirty;
    }

    public Integer getActivityOverdue() {
        return activityOverdue;
    }

    public void setActivityOverdue(Integer activityOverdue) {
        this.activityOverdue = activityOverdue;
    }

    public LocalDateTime getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(LocalDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    @Override
    public String toString() {
        return "SmsSignConfig{" +
                ", id=" + id +
                ", plugInsId=" + plugInsId +
                ", imgurl=" + imgurl +
                ", minScore=" + minScore +
                ", maxScore=" + maxScore +
                ", continuityThree=" + continuityThree +
                ", continuityTwenty=" + continuityTwenty +
                ", continuityThirty=" + continuityThirty +
                ", activityOverdue=" + activityOverdue +
                ", modifyDate=" + modifyDate +
                "}";
    }
}

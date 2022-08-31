package com.mallplus.common.entity.sms;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 分享列表
 * </p>
 *
 * @author zscat
 * @since 2019-10-17
 */
@TableName("sms_share")
public class SmsShare implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 用户id
     */
    @TableField("user_id")
    private String userId;

    /**
     * 微信id
     */
    @TableField("wx_id")
    private String wxId;

    /**
     * 微信昵称
     */
    @TableField("wx_name")
    private String wxName;

    /**
     * 性别 0:未知 1:男 2:女
     */
    private Integer sex;

    /**
     * 类别 0：新闻 1：文章
     */
    private Integer type;

    /**
     * 新闻id
     */
    @TableField("Article_id")
    private Long articleId;

    /**
     * 分享时间
     */
    @TableField("share_add")
    private LocalDateTime shareAdd;

    /**
     * 礼券
     */
    private BigDecimal coupon;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getWxId() {
        return wxId;
    }

    public void setWxId(String wxId) {
        this.wxId = wxId;
    }

    public String getWxName() {
        return wxName;
    }

    public void setWxName(String wxName) {
        this.wxName = wxName;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public LocalDateTime getShareAdd() {
        return shareAdd;
    }

    public void setShareAdd(LocalDateTime shareAdd) {
        this.shareAdd = shareAdd;
    }

    public BigDecimal getCoupon() {
        return coupon;
    }

    public void setCoupon(BigDecimal coupon) {
        this.coupon = coupon;
    }

    @Override
    public String toString() {
        return "SmsShare{" +
                ", id=" + id +
                ", userId=" + userId +
                ", wxId=" + wxId +
                ", wxName=" + wxName +
                ", sex=" + sex +
                ", type=" + type +
                ", articleId=" + articleId +
                ", shareAdd=" + shareAdd +
                ", coupon=" + coupon +
                "}";
    }
}

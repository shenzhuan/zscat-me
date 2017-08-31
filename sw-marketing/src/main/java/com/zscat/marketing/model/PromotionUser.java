package com.zscat.marketing.model;

import com.zscat.base.BaseEntity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tg_promotion_user")
public class PromotionUser  extends BaseEntity{


    public PromotionUser() {
    }


    public PromotionUser(Long uid) {
        this.uid = uid;
    }

    public PromotionUser(String phone) {
        this.phone = phone;
    }

    @Transient
    private String token;
    /**
     * 升级进度
     */
    @Transient
    private String countlevel;

    @Id
    private Long uid;
    private Integer status;
    private String ifcode;
    @Column(name = "up_uid")
    private Long upUid;
    @Column(name = "up_uids")
    private String upUids;
    @Column(name = "up_ifcode")
    private String upIfcode;
    private Integer level;
    @Column(name = "total_income")
    private Long totalIncome;
    private String phone;
    @Column(name = "wx_open_id")
    private String wxOpenId;
    @Column(name = "wx_unionid")
    private String wxUnionid;
    @Column(name = "wx_nickname")
    private String wxNickname;
    @Column(name = "wx_avatar")
    private String wxAvatar;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "update_time")
    private Date updateTime;



    /**
     *
     *
     * @return uid
     */
    public Long getUid() {
        return uid;
    }


    public void setUid(Long uid) {
        this.uid = uid;
    }

    /**
     状态：1正常，2：主动退出   3：查封
     */
    public Integer getStatus() {
        return status;
    }

    /**
     状态：1正常，2：主动退出   3：查封
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     推广员的if号
     */
    public String getIfcode() {
        return ifcode;
    }

    /**
     推广员的if号
     */
    public void setIfcode(String ifcode) {
        this.ifcode = ifcode;
    }

    /**
     上线UID
     */
    public Long getUpUid() {
        return upUid;
    }

    /**
     上线UID
     */
    public void setUpUid(Long upUid) {
        this.upUid = upUid;
    }

    /**
     所有上级推广员id  逗号分隔
     */
    public String getUpUids() {
        return upUids;
    }

    /**
     所有上级推广员id  逗号分隔
     */
    public void setUpUids(String upUids) {
        this.upUids = upUids;
    }

    /**
     上线if号
     */
    public String getUpIfcode() {
        return upIfcode;
    }

    /**
     上线if号
     */
    public void setUpIfcode(String upIfcode) {
        this.upIfcode = upIfcode;
    }

    /**
     推广等级1-6
     */
    public Integer getLevel() {
        return level;
    }

    /**
     推广等级1-6
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     总收入
     */
    public Long getTotalIncome() {
        return totalIncome;
    }

    /**
     总收入
     */
    public void setTotalIncome(Long totalIncome) {
        this.totalIncome = totalIncome;
    }

    /**
     手机号
     */
    public String getPhone() {
        return phone;
    }

    /**
     手机号
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     推广员微信openid
     */
    public String getWxOpenId() {
        return wxOpenId;
    }

    /**
     推广员微信openid
     */
    public void setWxOpenId(String wxOpenId) {
        this.wxOpenId = wxOpenId;
    }

    /**
     * @return wx_unionid
     */
    public String getWxUnionid() {
        return wxUnionid;
    }

    /**
     * @param wxUnionid
     */
    public void setWxUnionid(String wxUnionid) {
        this.wxUnionid = wxUnionid;
    }

    /**
     * @return wx_nickname
     */
    public String getWxNickname() {
        return wxNickname;
    }

    /**
     * @param wxNickname
     */
    public void setWxNickname(String wxNickname) {
        this.wxNickname = wxNickname;
    }

    /**
     * @return wx_avatar
     */
    public String getWxAvatar() {
        return wxAvatar;
    }

    /**
     * @param wxAvatar
     */
    public void setWxAvatar(String wxAvatar) {
        this.wxAvatar = wxAvatar;
    }


    public Date getCreateTime() {
        return createTime;
    }


    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public Date getUpdateTime() {
        return updateTime;
    }


    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getToken() {
        return token;
    }

    public PromotionUser setToken(String token) {
        this.token = token;
        return this;
    }
    public String getCountlevel() {
        return countlevel;
    }

    public void setCountlevel(String countlevel) {
        this.countlevel = countlevel;
    }
    @Override
    public String toString() {
        return "PromotionUser{" +
                "token='" + token + '\'' +
                ", uid=" + uid +
                ", status=" + status +
                ", ifcode='" + ifcode + '\'' +
                ", upUid=" + upUid +
                ", upUids='" + upUids + '\'' +
                ", upIfcode='" + upIfcode + '\'' +
                ", level=" + level +
                ", totalIncome=" + totalIncome +
                ", phone='" + phone + '\'' +
                ", wxOpenId='" + wxOpenId + '\'' +
                ", wxUnionid='" + wxUnionid + '\'' +
                ", wxNickname='" + wxNickname + '\'' +
                ", wxAvatar='" + wxAvatar + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
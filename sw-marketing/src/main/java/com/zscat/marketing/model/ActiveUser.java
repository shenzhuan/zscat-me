package com.zscat.marketing.model;

import com.zscat.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "tg_active_user")
public class ActiveUser extends BaseEntity {
    /**
     * 用户UID
     */
    @Id
    private Long uid;

    /**
     * 推广员UID
     */
    @Column(name = "pro_uid")
    private Long proUid;

    /**
     * 推广人ifcode
     */
    @Column(name = "pro_ifcode")
    private String proIfcode;

    /**
     * 注册时间
     */
    @Column(name = "register_time")
    private Date registerTime;

    /**
     * 新增领取 0 未领取  1 领取
     */
    @Column(name = "new_reward")
    private Byte newReward;

    /**
     * 活跃天数
     */
    @Column(name = "active_days")
    private Byte activeDays;

    /**
     * 上次活跃时间
     */
    @Column(name = "last_active_date")
    private Date lastActiveDate;

    /**
     * 获取用户UID
     *
     * @return uid - 用户UID
     */
    public Long getUid() {
        return uid;
    }

    /**
     * 设置用户UID
     *
     * @param uid 用户UID
     */
    public void setUid(Long uid) {
        this.uid = uid;
    }

    /**
     * 获取推广员UID
     *
     * @return pro_uid - 推广员UID
     */
    public Long getProUid() {
        return proUid;
    }

    /**
     * 设置推广员UID
     *
     * @param proUid 推广员UID
     */
    public void setProUid(Long proUid) {
        this.proUid = proUid;
    }

    /**
     * 获取推广人ifcode
     *
     * @return pro_ifcode - 推广人ifcode
     */
    public String getProIfcode() {
        return proIfcode;
    }

    /**
     * 设置推广人ifcode
     *
     * @param proIfcode 推广人ifcode
     */
    public void setProIfcode(String proIfcode) {
        this.proIfcode = proIfcode;
    }

    /**
     * 获取注册时间
     *
     * @return register_time - 注册时间
     */
    public Date getRegisterTime() {
        return registerTime;
    }

    /**
     * 设置注册时间
     *
     * @param registerTime 注册时间
     */
    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    /**
     * 获取新增领取 0 未领取  1 领取
     *
     * @return new_reward - 新增领取 0 未领取  1 领取
     */
    public Byte getNewReward() {
        return newReward;
    }

    /**
     * 设置新增领取 0 未领取  1 领取
     *
     * @param newReward 新增领取 0 未领取  1 领取
     */
    public void setNewReward(Byte newReward) {
        this.newReward = newReward;
    }

    /**
     * 获取活跃天数
     *
     * @return active_days - 活跃天数
     */
    public Byte getActiveDays() {
        return activeDays;
    }

    /**
     * 设置活跃天数
     *
     * @param activeDays 活跃天数
     */
    public void setActiveDays(Byte activeDays) {
        this.activeDays = activeDays;
    }

    /**
     * 获取上次活跃时间
     *
     * @return last_active_date - 上次活跃时间
     */
    public Date getLastActiveDate() {
        return lastActiveDate;
    }

    /**
     * 设置上次活跃时间
     *
     * @param lastActiveDate 上次活跃时间
     */
    public void setLastActiveDate(Date lastActiveDate) {
        this.lastActiveDate = lastActiveDate;
    }
}
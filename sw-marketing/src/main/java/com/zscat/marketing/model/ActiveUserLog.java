package com.zscat.marketing.model;

import com.zscat.base.BaseEntity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tg_active_user_log")
public class ActiveUserLog extends BaseEntity{
    @Id
    private Long uid;

    @Id
    @Column(name = "active_date")
    private Date activeDate;

    /**
     * @return uid
     */
    public Long getUid() {
        return uid;
    }

    /**
     * @param uid
     */
    public void setUid(Long uid) {
        this.uid = uid;
    }

    /**
     * @return active_date
     */
    public Date getActiveDate() {
        return activeDate;
    }

    /**
     * @param activeDate
     */
    public void setActiveDate(Date activeDate) {
        this.activeDate = activeDate;
    }
}
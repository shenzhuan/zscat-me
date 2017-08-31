package com.zscat.marketing.model;

import com.zscat.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "tg_income")
public class Income extends BaseEntity {

    public Income () {}

    public Income (Long uid) {
        this.uid = uid;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Long uid;


    private Long income;


    private String type;


    @Column(name = "income_date")
    private Date incomeDate;


    @Column(name = "from_uid")
    private Long fromUid;





    private String remark;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }


    public Long getUid() {
        return uid;
    }


    public void setUid(Long uid) {
        this.uid = uid;
    }

    /**
     收入 单位分
     */
    public Long getIncome() {
        return income;
    }

    /**
     收入 单位分
     */
    public void setIncome(Long income) {
        this.income = income;
    }

    /**
     收入类型 1:新增  2:活跃 3:新增分成 4:活跃分成
     */
    public String getType() {
        return type;
    }

    /**
     收入类型 1:新增  2:活跃 3:新增分成 4:活跃分成
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     收入时间
     */
    public Date getIncomeDate() {
        return incomeDate;
    }

    /**
     收入时间
     */
    public void setIncomeDate(Date incomeDate) {
        this.incomeDate = incomeDate;
    }

    /**
     下线UID
     */
    public Long getFromUid() {
        return fromUid;
    }

    /**
     下线UID
     */
    public void setFromUid(Long fromUid) {
        this.fromUid = fromUid;
    }

    /**
     备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
}
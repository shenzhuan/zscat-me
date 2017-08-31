package com.zscat.marketing.model;

import com.zscat.base.BaseEntity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tg_withdraw")
public class Withdraw  extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long uid;

    /**
     提现金额(分)
     */
    private Long money;

    /**
     * 提现支付类型: wxpay, alipay
     */
    @Column(name = "pay_type")
    private String payType;

    /**
     0,等待处理,1:操作成功,2:处理失败, -1: 无效数据
     */
    private Integer status;

    /**
     支付平台成功回执编号
     */
    @Column(name = "receipt_no")
    private String receiptNo;

    /**
     平台返回的错误信息
     */
    @Column(name = "err_msg")
    private String errMsg;

    /**
     状态码
     */
    @Column(name = "return_code")
    private String returnCode;

    /**
     操作时间
     */
    @Column(name = "operationTime")
    private Date operationtime;


    @Column(name = "update_time")
    private Date updateTime;


    @Column(name = "create_time")
    private Date createTime;

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


    public Long getMoney() {
        return money;
    }


    public void setMoney(Long money) {
        this.money = money;
    }


    public String getPayType() {
        return payType;
    }


    public void setPayType(String payType) {
        this.payType = payType;
    }


    public Integer getStatus() {
        return status;
    }


    public void setStatus(Integer status) {
        this.status = status;
    }


    public String getReceiptNo() {
        return receiptNo;
    }


    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }


    public String getErrMsg() {
        return errMsg;
    }


    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }


    public String getReturnCode() {
        return returnCode;
    }


    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }


    public Date getOperationtime() {
        return operationtime;
    }


    public void setOperationtime(Date operationtime) {
        this.operationtime = operationtime;
    }


    public Date getUpdateTime() {
        return updateTime;
    }


    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


    public Date getCreateTime() {
        return createTime;
    }


    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
package com.zscat.shop.model;

import javax.persistence.*;

import com.zsCat.common.base.BaseEntity;

@Table(name = "t_payment")
public class Payment extends BaseEntity{
   
    /**
     * 支付代码名称
     */
    @Column(name = "payment_code")
    private String paymentCode;

    /**
     * 支付名称
     */
    @Column(name = "payment_name")
    private String paymentName;

    /**
     * 接口状态0禁用1启用
     */
    @Column(name = "payment_state")
    private String paymentState;

    /**
     * 是否删除0:未删除;1:已删除
     */
    @Column(name = "is_del")
    private Integer isDel;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Long createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Long updateTime;

    /**
     * 支付方式logo
     */
    @Column(name = "payment_logo")
    private String paymentLogo;

    /**
     * 支付接口配置信息
     */
    @Column(name = "payment_config")
    private String paymentConfig;


    /**
     * 获取支付代码名称
     *
     * @return payment_code - 支付代码名称
     */
    public String getPaymentCode() {
        return paymentCode;
    }

    /**
     * 设置支付代码名称
     *
     * @param paymentCode 支付代码名称
     */
    public void setPaymentCode(String paymentCode) {
        this.paymentCode = paymentCode;
    }

    /**
     * 获取支付名称
     *
     * @return payment_name - 支付名称
     */
    public String getPaymentName() {
        return paymentName;
    }

    /**
     * 设置支付名称
     *
     * @param paymentName 支付名称
     */
    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName;
    }

    /**
     * 获取接口状态0禁用1启用
     *
     * @return payment_state - 接口状态0禁用1启用
     */
    public String getPaymentState() {
        return paymentState;
    }

    /**
     * 设置接口状态0禁用1启用
     *
     * @param paymentState 接口状态0禁用1启用
     */
    public void setPaymentState(String paymentState) {
        this.paymentState = paymentState;
    }

    /**
     * 获取是否删除0:未删除;1:已删除
     *
     * @return is_del - 是否删除0:未删除;1:已删除
     */
    public Integer getIsDel() {
        return isDel;
    }

    /**
     * 设置是否删除0:未删除;1:已删除
     *
     * @param isDel 是否删除0:未删除;1:已删除
     */
    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Long getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取修改时间
     *
     * @return update_time - 修改时间
     */
    public Long getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置修改时间
     *
     * @param updateTime 修改时间
     */
    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取支付方式logo
     *
     * @return payment_logo - 支付方式logo
     */
    public String getPaymentLogo() {
        return paymentLogo;
    }

    /**
     * 设置支付方式logo
     *
     * @param paymentLogo 支付方式logo
     */
    public void setPaymentLogo(String paymentLogo) {
        this.paymentLogo = paymentLogo;
    }

    /**
     * 获取支付接口配置信息
     *
     * @return payment_config - 支付接口配置信息
     */
    public String getPaymentConfig() {
        return paymentConfig;
    }

    /**
     * 设置支付接口配置信息
     *
     * @param paymentConfig 支付接口配置信息
     */
    public void setPaymentConfig(String paymentConfig) {
        this.paymentConfig = paymentConfig;
    }
}
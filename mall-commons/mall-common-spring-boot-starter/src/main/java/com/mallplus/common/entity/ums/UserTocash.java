package com.mallplus.common.entity.ums;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 *
 * </p>
 *
 * @author zscat
 * @since 2019-09-16
 */
@TableName("user_tocash")
public class UserTocash implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户ID
     */
    @TableField("user_id")
    private Integer userId;

    /**
     * 提现金额
     */
    private BigDecimal money;

    /**
     * 银行名称
     */
    @TableField("bank_name")
    private String bankName;

    /**
     * 银行缩写
     */
    @TableField("bank_code")
    private String bankCode;

    /**
     * 账号地区ID
     */
    @TableField("bank_area_id")
    private Integer bankAreaId;

    /**
     * 开户行
     */
    @TableField("account_bank")
    private String accountBank;

    /**
     * 账户名
     */
    @TableField("account_name")
    private String accountName;

    /**
     * 卡号
     */
    @TableField("card_number")
    private String cardNumber;

    /**
     * 提现服务费
     */
    private BigDecimal withdrawals;

    /**
     * 1默认，2提现成功，3提现失败
     */
    private Integer type;

    /**
     * 创建时间
     */
    private Long ctime;

    /**
     * 更新时间
     */
    private Long utime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public Integer getBankAreaId() {
        return bankAreaId;
    }

    public void setBankAreaId(Integer bankAreaId) {
        this.bankAreaId = bankAreaId;
    }

    public String getAccountBank() {
        return accountBank;
    }

    public void setAccountBank(String accountBank) {
        this.accountBank = accountBank;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public BigDecimal getWithdrawals() {
        return withdrawals;
    }

    public void setWithdrawals(BigDecimal withdrawals) {
        this.withdrawals = withdrawals;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getCtime() {
        return ctime;
    }

    public void setCtime(Long ctime) {
        this.ctime = ctime;
    }

    public Long getUtime() {
        return utime;
    }

    public void setUtime(Long utime) {
        this.utime = utime;
    }

    @Override
    public String toString() {
        return "UserTocash{" +
                ", id=" + id +
                ", userId=" + userId +
                ", money=" + money +
                ", bankName=" + bankName +
                ", bankCode=" + bankCode +
                ", bankAreaId=" + bankAreaId +
                ", accountBank=" + accountBank +
                ", accountName=" + accountName +
                ", cardNumber=" + cardNumber +
                ", withdrawals=" + withdrawals +
                ", type=" + type +
                ", ctime=" + ctime +
                ", utime=" + utime +
                "}";
    }
}

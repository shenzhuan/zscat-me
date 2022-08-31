package com.mallplus.common.entity.sms;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 分销佣金明细表
 * </p>
 *
 * @author zscat
 * @since 2019-10-17
 */
@TableName("sms_detailed_commission")
public class SmsDetailedCommission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long userid;

    /**
     * 订单号
     */
    private String sNo;

    /**
     * 应发佣金
     */
    private Float money;

    /**
     * 实发佣金
     */
    @TableField("s_money")
    private Float sMoney;

    /**
     * 1.未发放，2.已发放
     */
    private Integer status;

    /**
     * 添加时间
     */
    private LocalDateTime addtime;

    /**
     * 类型
     */
    private Integer type;

    /**
     * 上级
     */
    private String Referee;

    /**
     * 0 不回收  1.回收
     */
    private Integer recycle;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getsNo() {
        return sNo;
    }

    public void setsNo(String sNo) {
        this.sNo = sNo;
    }

    public Float getMoney() {
        return money;
    }

    public void setMoney(Float money) {
        this.money = money;
    }

    public Float getsMoney() {
        return sMoney;
    }

    public void setsMoney(Float sMoney) {
        this.sMoney = sMoney;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getAddtime() {
        return addtime;
    }

    public void setAddtime(LocalDateTime addtime) {
        this.addtime = addtime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getReferee() {
        return Referee;
    }

    public void setReferee(String Referee) {
        this.Referee = Referee;
    }

    public Integer getRecycle() {
        return recycle;
    }

    public void setRecycle(Integer recycle) {
        this.recycle = recycle;
    }

    @Override
    public String toString() {
        return "SmsDetailedCommission{" +
                ", id=" + id +
                ", userid=" + userid +
                ", sNo=" + sNo +
                ", money=" + money +
                ", sMoney=" + sMoney +
                ", status=" + status +
                ", addtime=" + addtime +
                ", type=" + type +
                ", Referee=" + Referee +
                ", recycle=" + recycle +
                "}";
    }
}

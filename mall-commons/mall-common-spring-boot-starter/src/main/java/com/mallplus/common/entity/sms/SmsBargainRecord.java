package com.mallplus.common.entity.sms;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 砍价免单记录表
 * </p>
 *
 * @author zscat
 * @since 2019-10-17
 */
@TableName("sms_bargain_record")
public class SmsBargainRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 属性id
     */
    @TableField("s_id")
    private Long sId;

    /**
     * 用户ID
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 金额
     */
    private BigDecimal money;

    /**
     * 添加时间
     */
    @TableField("add_time")
    private LocalDateTime addTime;

    /**
     * 事件
     */
    private String event;

    /**
     * 收货人
     */
    private String name;

    /**
     * 联系方式
     */
    private String tel;

    /**
     * 省
     */
    private Integer sheng;

    /**
     * 市
     */
    private Integer city;

    /**
     * 县
     */
    private Integer quyu;

    /**
     * 收货地址（不加省市区）
     */
    private String address;

    /**
     * 状态 0:砍价中 1:砍价成功 2:逾期失效 3:生成订单
     */
    private Integer status;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getsId() {
        return sId;
    }

    public void setsId(Long sId) {
        this.sId = sId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public LocalDateTime getAddTime() {
        return addTime;
    }

    public void setAddTime(LocalDateTime addTime) {
        this.addTime = addTime;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getSheng() {
        return sheng;
    }

    public void setSheng(Integer sheng) {
        this.sheng = sheng;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public Integer getQuyu() {
        return quyu;
    }

    public void setQuyu(Integer quyu) {
        this.quyu = quyu;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SmsBargainRecord{" +
                ", id=" + id +
                ", sId=" + sId +
                ", userId=" + userId +
                ", money=" + money +
                ", addTime=" + addTime +
                ", event=" + event +
                ", name=" + name +
                ", tel=" + tel +
                ", sheng=" + sheng +
                ", city=" + city +
                ", quyu=" + quyu +
                ", address=" + address +
                ", status=" + status +
                "}";
    }
}

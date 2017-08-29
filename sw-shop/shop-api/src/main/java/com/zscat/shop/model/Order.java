package com.zscat.shop.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Table;

import com.zsCat.common.base.BaseEntity;

@Table(name = "t_order")
public class Order extends BaseEntity{
   

    /**
     * 总价格
     */
    private BigDecimal totalprice;

    /**
     * 总个数
     */
    private Integer totalcount;

    private String ordersn;

    /**
     * 1
     */
    private Integer status;

    private Long userid;

    private Date createdate;

    private Long paymentid;

    private Long addressid;

    private String username;

    private String usercontent;

  

    /**
     * 获取总价格
     *
     * @return totalprice - 总价格
     */
    public BigDecimal getTotalprice() {
        return totalprice;
    }

    /**
     * 设置总价格
     *
     * @param totalprice 总价格
     */
    public void setTotalprice(BigDecimal totalprice) {
        this.totalprice = totalprice;
    }

    /**
     * 获取总个数
     *
     * @return totalcount - 总个数
     */
    public Integer getTotalcount() {
        return totalcount;
    }

    /**
     * 设置总个数
     *
     * @param totalcount 总个数
     */
    public void setTotalcount(Integer totalcount) {
        this.totalcount = totalcount;
    }

    /**
     * @return ordersn
     */
    public String getOrdersn() {
        return ordersn;
    }

    /**
     * @param ordersn
     */
    public void setOrdersn(String ordersn) {
        this.ordersn = ordersn;
    }

    /**
     * 获取1
     *
     * @return status - 1
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置1
     *
     * @param status 1
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return userid
     */
    public Long getUserid() {
        return userid;
    }

    /**
     * @param userid
     */
    public void setUserid(Long userid) {
        this.userid = userid;
    }

    /**
     * @return createdate
     */
    public Date getCreatedate() {
        return createdate;
    }

    /**
     * @param createdate
     */
    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    /**
     * @return paymentid
     */
    public Long getPaymentid() {
        return paymentid;
    }

    /**
     * @param paymentid
     */
    public void setPaymentid(Long paymentid) {
        this.paymentid = paymentid;
    }

    /**
     * @return addressid
     */
    public Long getAddressid() {
        return addressid;
    }

    /**
     * @param addressid
     */
    public void setAddressid(Long addressid) {
        this.addressid = addressid;
    }

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return usercontent
     */
    public String getUsercontent() {
        return usercontent;
    }

    /**
     * @param usercontent
     */
    public void setUsercontent(String usercontent) {
        this.usercontent = usercontent;
    }
}
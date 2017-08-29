package com.zscat.shop.model;

import java.util.Date;

import javax.persistence.*;

import com.zsCat.common.base.BaseEntity;

@Table(name = "t_reply")
public class Reply extends BaseEntity{
   

    private Long goodsid;

    private String content;

    private Date createdate;

    private Integer status;

    private Long userid;

    private String username;

   

    /**
     * @return goodsid
     */
    public Long getGoodsid() {
        return goodsid;
    }

    /**
     * @param goodsid
     */
    public void setGoodsid(Long goodsid) {
        this.goodsid = goodsid;
    }

    /**
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
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
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status
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
}
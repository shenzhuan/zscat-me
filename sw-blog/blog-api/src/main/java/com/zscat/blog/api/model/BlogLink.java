package com.zscat.blog.api.model;

import com.zsCat.common.base.BaseEntity;

import javax.persistence.*;



@Table(name = "t_link")
public class BlogLink extends BaseEntity {
   

    @Column(name = "linkName")
    private String linkname;

    @Column(name = "linkUrl")
    private String linkurl;

    @Column(name = "orderNo")
    private Integer orderno;

   

    /**
     * @return linkName
     */
    public String getLinkname() {
        return linkname;
    }

    /**
     * @param linkname
     */
    public void setLinkname(String linkname) {
        this.linkname = linkname;
    }

    /**
     * @return linkUrl
     */
    public String getLinkurl() {
        return linkurl;
    }

    /**
     * @param linkurl
     */
    public void setLinkurl(String linkurl) {
        this.linkurl = linkurl;
    }

    /**
     * @return orderNo
     */
    public Integer getOrderno() {
        return orderno;
    }

    /**
     * @param orderno
     */
    public void setOrderno(Integer orderno) {
        this.orderno = orderno;
    }
}
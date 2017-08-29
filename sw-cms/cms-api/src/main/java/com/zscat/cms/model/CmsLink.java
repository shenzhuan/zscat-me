package com.zscat.cms.model;

import java.util.Date;

import javax.persistence.*;

import com.zsCat.common.base.BaseEntity;

@Table(name = "cms_link")
public class CmsLink extends BaseEntity{
   

    /**
     * 颜色
     */
    private String color;

    /**
     * 链接
     */
    private String href;

    /**
     * 图片
     */
    private String image;

    /**
     * 标题
     */
    private String title;

    /**
     * 权重
     */
    private Integer weight;

    @Column(name = "weightDate")
    private Date weightdate;

    @Column(name = "CATEGORY_ID")
    private Long categoryId;

   

    /**
     * 获取颜色
     *
     * @return color - 颜色
     */
    public String getColor() {
        return color;
    }

    /**
     * 设置颜色
     *
     * @param color 颜色
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * 获取链接
     *
     * @return href - 链接
     */
    public String getHref() {
        return href;
    }

    /**
     * 设置链接
     *
     * @param href 链接
     */
    public void setHref(String href) {
        this.href = href;
    }

    /**
     * 获取图片
     *
     * @return image - 图片
     */
    public String getImage() {
        return image;
    }

    /**
     * 设置图片
     *
     * @param image 图片
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * 获取标题
     *
     * @return title - 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置标题
     *
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取权重
     *
     * @return weight - 权重
     */
    public Integer getWeight() {
        return weight;
    }

    /**
     * 设置权重
     *
     * @param weight 权重
     */
    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    /**
     * @return weightDate
     */
    public Date getWeightdate() {
        return weightdate;
    }

    /**
     * @param weightdate
     */
    public void setWeightdate(Date weightdate) {
        this.weightdate = weightdate;
    }

    /**
     * @return CATEGORY_ID
     */
    public Long getCategoryId() {
        return categoryId;
    }

    /**
     * @param categoryId
     */
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
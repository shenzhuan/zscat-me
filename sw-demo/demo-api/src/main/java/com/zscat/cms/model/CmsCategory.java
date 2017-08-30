package com.zscat.cms.model;

import javax.persistence.*;

import com.zsCat.common.base.BaseEntity;

@Table(name = "cms_category")
public class CmsCategory extends BaseEntity{
  
    /**
     * 描述
     */
    private String description;

    /**
     * 图片
     */
    private String image;

    /**
     * 关键字
     */
    private String keywords;

    /**
     * 模块
     */
    private String module;

    /**
     * 名称
     */
    private String name;

    @Column(name = "parent_id")
    private Long parentId;

    /**
     * 级别
     */
    private Integer grade;

    /**
     * 是否展示
     */
    @Column(name = "isShow")
    private Integer isshow;

    @Column(name = "orderNo")
    private Integer orderno;

    /**
     * 网址
     */
    @Column(name = "site_id")
    private Long siteId;

    /**
     * 链接
     */
    private String url;

    @Column(name = "parent_ids")
    private String parentIds;

   

    /**
     * 获取描述
     *
     * @return description - 描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置描述
     *
     * @param description 描述
     */
    public void setDescription(String description) {
        this.description = description;
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
     * 获取关键字
     *
     * @return keywords - 关键字
     */
    public String getKeywords() {
        return keywords;
    }

    /**
     * 设置关键字
     *
     * @param keywords 关键字
     */
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    /**
     * 获取模块
     *
     * @return module - 模块
     */
    public String getModule() {
        return module;
    }

    /**
     * 设置模块
     *
     * @param module 模块
     */
    public void setModule(String module) {
        this.module = module;
    }

    /**
     * 获取名称
     *
     * @return name - 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     *
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return parent_id
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * @param parentId
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取级别
     *
     * @return grade - 级别
     */
    public Integer getGrade() {
        return grade;
    }

    /**
     * 设置级别
     *
     * @param grade 级别
     */
    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    /**
     * 获取是否展示
     *
     * @return isShow - 是否展示
     */
    public Integer getIsshow() {
        return isshow;
    }

    /**
     * 设置是否展示
     *
     * @param isshow 是否展示
     */
    public void setIsshow(Integer isshow) {
        this.isshow = isshow;
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

    /**
     * 获取网址
     *
     * @return site_id - 网址
     */
    public Long getSiteId() {
        return siteId;
    }

    /**
     * 设置网址
     *
     * @param siteId 网址
     */
    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    /**
     * 获取链接
     *
     * @return url - 链接
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置链接
     *
     * @param url 链接
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return parent_ids
     */
    public String getParentIds() {
        return parentIds;
    }

    /**
     * @param parentIds
     */
    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }
}
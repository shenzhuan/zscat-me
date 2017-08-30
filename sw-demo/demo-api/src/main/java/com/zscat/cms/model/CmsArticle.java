package com.zscat.cms.model;

import com.zsCat.common.base.BaseEntity;

import java.util.Date;

import javax.persistence.*;



@Table(name = "cms_article")
public class CmsArticle extends BaseEntity {
 
    /**
     * 颜色 文章  视频
     */
    private String color;

    @Column(name = "createDate")
    private Date createdate;

    /**
     * 描述
     */
    
    private String description;

    /**
     * 点击量
     */
    private Integer hits;

    /**
     * htmlId
     */
    private String htmlid;

    /**
     * 图片
     */
    private String image;

    /**
     * isWord
     */
    @Column(name = "isWord")
    private Integer isword;

    private String keywords;

    /**
     * 标题
     */
    private String title;

    /**
     * 权重
     */
    private Integer weight;

    @Column(name = "CATEGORY_ID")
    private Long categoryId;

    @Column(name = "delFlag")
    private Integer delflag;

    @Column(name = "updateDate")
    private Date updatedate;

    @Column(name = "createby_id")
    private Long createbyId;

    @Column(name = "updateby_id")
    private Long updatebyId;

    private String moreimage;

    @Column(name = "CATEGORYNAME")
    private String categoryname;

    private Long siteid;

    /**
     * 内容
     */
    private String content;

   

    /**
     * 获取颜色 文章  视频
     *
     * @return color - 颜色 文章  视频
     */
    public String getColor() {
        return color;
    }

    /**
     * 设置颜色 文章  视频
     *
     * @param color 颜色 文章  视频
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @return createDate
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
     * 获取点击量
     *
     * @return hits - 点击量
     */
    public Integer getHits() {
        return hits;
    }

    /**
     * 设置点击量
     *
     * @param hits 点击量
     */
    public void setHits(Integer hits) {
        this.hits = hits;
    }

    /**
     * 获取htmlId
     *
     * @return htmlid - htmlId
     */
    public String getHtmlid() {
        return htmlid;
    }

    /**
     * 设置htmlId
     *
     * @param htmlid htmlId
     */
    public void setHtmlid(String htmlid) {
        this.htmlid = htmlid;
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
     * 获取isWord
     *
     * @return isWord - isWord
     */
    public Integer getIsword() {
        return isword;
    }

    /**
     * 设置isWord
     *
     * @param isword isWord
     */
    public void setIsword(Integer isword) {
        this.isword = isword;
    }

    /**
     * @return keywords
     */
    public String getKeywords() {
        return keywords;
    }

    /**
     * @param keywords
     */
    public void setKeywords(String keywords) {
        this.keywords = keywords;
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

    /**
     * @return delFlag
     */
    public Integer getDelflag() {
        return delflag;
    }

    /**
     * @param delflag
     */
    public void setDelflag(Integer delflag) {
        this.delflag = delflag;
    }

    /**
     * @return updateDate
     */
    public Date getUpdatedate() {
        return updatedate;
    }

    /**
     * @param updatedate
     */
    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

    /**
     * @return createby_id
     */
    public Long getCreatebyId() {
        return createbyId;
    }

    /**
     * @param createbyId
     */
    public void setCreatebyId(Long createbyId) {
        this.createbyId = createbyId;
    }

    /**
     * @return updateby_id
     */
    public Long getUpdatebyId() {
        return updatebyId;
    }

    /**
     * @param updatebyId
     */
    public void setUpdatebyId(Long updatebyId) {
        this.updatebyId = updatebyId;
    }

    /**
     * @return moreimage
     */
    public String getMoreimage() {
        return moreimage;
    }

    /**
     * @param moreimage
     */
    public void setMoreimage(String moreimage) {
        this.moreimage = moreimage;
    }

    /**
     * @return CATEGORYNAME
     */
    public String getCategoryname() {
        return categoryname;
    }

    /**
     * @param categoryname
     */
    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    /**
     * @return siteid
     */
    public Long getSiteid() {
        return siteid;
    }

    /**
     * @param siteid
     */
    public void setSiteid(Long siteid) {
        this.siteid = siteid;
    }

    /**
     * 获取内容
     *
     * @return content - 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置内容
     *
     * @param content 内容
     */
    public void setContent(String content) {
        this.content = content;
    }
}
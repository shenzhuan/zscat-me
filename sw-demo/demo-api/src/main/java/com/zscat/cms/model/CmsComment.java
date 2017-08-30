package com.zscat.cms.model;

import java.util.Date;

import javax.persistence.*;

import com.zsCat.common.base.BaseEntity;

@Table(name = "cms_comment")
public class CmsComment extends BaseEntity{
   
    @Column(name = "auditDate")
    private Date auditdate;

    private String content;

    @Column(name = "createDate")
    private Date createdate;

    @Column(name = "delFlag")
    private Integer delflag;

    private String email;

    private String ip;

    private String name;

    private String url;

    @Column(name = "article_id")
    private Long articleId;

    @Column(name = "auditer_id")
    private Long auditerId;

    @Column(name = "headPath")
    private String headpath;

   

    /**
     * @return auditDate
     */
    public Date getAuditdate() {
        return auditdate;
    }

    /**
     * @param auditdate
     */
    public void setAuditdate(Date auditdate) {
        this.auditdate = auditdate;
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
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return article_id
     */
    public Long getArticleId() {
        return articleId;
    }

    /**
     * @param articleId
     */
    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    /**
     * @return auditer_id
     */
    public Long getAuditerId() {
        return auditerId;
    }

    /**
     * @param auditerId
     */
    public void setAuditerId(Long auditerId) {
        this.auditerId = auditerId;
    }

    /**
     * @return headPath
     */
    public String getHeadpath() {
        return headpath;
    }

    /**
     * @param headpath
     */
    public void setHeadpath(String headpath) {
        this.headpath = headpath;
    }
}
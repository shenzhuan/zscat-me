package com.zscat.blog.api.model;

import com.zsCat.common.base.BaseEntity;

import java.util.Date;

import javax.persistence.*;


@Table(name = "t_comment")
public class BlogComment extends BaseEntity {
  

    @Column(name = "userIp")
    private String userip;

    @Column(name = "blogId")
    private Long blogid;

    private String content;

    @Column(name = "commentDate")
    private Date commentdate;

    private Integer state;

    @Column(name = "blogger_id")
    private Long bloggerId;

    private String title;

    private String blogger;

   

    /**
     * @return userIp
     */
    public String getUserip() {
        return userip;
    }

    /**
     * @param userip
     */
    public void setUserip(String userip) {
        this.userip = userip;
    }

    /**
     * @return blogId
     */
    public Long getBlogid() {
        return blogid;
    }

    /**
     * @param blogid
     */
    public void setBlogid(Long blogid) {
        this.blogid = blogid;
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
     * @return commentDate
     */
    public Date getCommentdate() {
        return commentdate;
    }

    /**
     * @param commentdate
     */
    public void setCommentdate(Date commentdate) {
        this.commentdate = commentdate;
    }

    /**
     * @return state
     */
    public Integer getState() {
        return state;
    }

    /**
     * @param state
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * @return blogger_id
     */
    public Long getBloggerId() {
        return bloggerId;
    }

    /**
     * @param bloggerId
     */
    public void setBloggerId(Long bloggerId) {
        this.bloggerId = bloggerId;
    }

    /**
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return blogger
     */
    public String getBlogger() {
        return blogger;
    }

    /**
     * @param blogger
     */
    public void setBlogger(String blogger) {
        this.blogger = blogger;
    }
}
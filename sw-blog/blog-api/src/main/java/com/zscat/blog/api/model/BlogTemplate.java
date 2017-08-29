package com.zscat.blog.api.model;

import com.zsCat.common.base.BaseEntity;

import java.util.Date;

import javax.persistence.*;


@Table(name = "t_blog_template")
public class BlogTemplate extends BaseEntity {
    @Transient
	private String img;

	public String getImg() {
		return this.img;
	}

	public void setImg(String img) {
		 this.img = img;
	}
    /**
     * 标题
     */
    private String title;

    /**
     * 发布时间
     */
    @Column(name = "releaseDate")
    private Date releasedate;

    @Column(name = "clickHit")
    private Integer clickhit;

    @Column(name = "replyHit")
    private Integer replyhit;

    @Column(name = "keyWord")
    private String keyword;

    /**
     * 摘要
     */
    private String summary;

    private String content;

   

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
     * 获取发布时间
     *
     * @return releaseDate - 发布时间
     */
    public Date getReleasedate() {
        return releasedate;
    }

    /**
     * 设置发布时间
     *
     * @param releasedate 发布时间
     */
    public void setReleasedate(Date releasedate) {
        this.releasedate = releasedate;
    }

    /**
     * @return clickHit
     */
    public Integer getClickhit() {
        return clickhit;
    }

    /**
     * @param clickhit
     */
    public void setClickhit(Integer clickhit) {
        this.clickhit = clickhit;
    }

    /**
     * @return replyHit
     */
    public Integer getReplyhit() {
        return replyhit;
    }

    /**
     * @param replyhit
     */
    public void setReplyhit(Integer replyhit) {
        this.replyhit = replyhit;
    }

    /**
     * @return keyWord
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * @param keyword
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    /**
     * 获取摘要
     *
     * @return summary - 摘要
     */
    public String getSummary() {
        return summary;
    }

    /**
     * 设置摘要
     *
     * @param summary 摘要
     */
    public void setSummary(String summary) {
        this.summary = summary;
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
}
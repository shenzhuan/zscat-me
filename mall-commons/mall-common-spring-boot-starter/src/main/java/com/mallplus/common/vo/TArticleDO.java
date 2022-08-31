package com.mallplus.common.vo;

import com.mallplus.common.entity.BaseEntity;

import java.io.Serializable;
import java.util.Date;


/**
 * @author zscat
 * @email 951449465@qq.com
 * @date 2017-10-15 15:07:36
 */
public class TArticleDO  implements Serializable {
    private static final long serialVersionUID = 1L;

    public TArticleDO() {

    }

    public TArticleDO(String title) {
        this.title = title;
    }

    public TArticleDO(String title, String summary) {
        this.title = title;
        this.summary = summary;
    }

    public TArticleDO(String title, String summary, String content, String img) {
        this.title = title;
        this.summary = summary;
        this.content = content;
        this.img = img;
    }

    //
    private Long id;
    //标题
    private String title;
    //摘要
    private String summary;
    //发布时间
    private Date releasedate;
    //点击量
    private Integer clickhit;
    //回复量
    private Integer replyhit;
    //内容
    private String content;
    //关键字
    private String keyword;
    //状态
    private Integer state;
    //图片
    private String img;
    //所属者
    private Long villageid;
    //用户名
    private String username;

    /**
     * 设置：
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取：
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置：标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取：标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置：摘要
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * 获取：摘要
     */
    public String getSummary() {
        return summary;
    }

    /**
     * 设置：发布时间
     */
    public void setReleasedate(Date releasedate) {
        this.releasedate = releasedate;
    }

    /**
     * 获取：发布时间
     */
    public Date getReleasedate() {
        return releasedate;
    }

    /**
     * 设置：点击量
     */
    public void setClickhit(Integer clickhit) {
        this.clickhit = clickhit;
    }

    /**
     * 获取：点击量
     */
    public Integer getClickhit() {
        return clickhit;
    }

    /**
     * 设置：回复量
     */
    public void setReplyhit(Integer replyhit) {
        this.replyhit = replyhit;
    }

    /**
     * 获取：回复量
     */
    public Integer getReplyhit() {
        return replyhit;
    }

    /**
     * 设置：内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取：内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置：关键字
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    /**
     * 获取：关键字
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * 设置：状态
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * 获取：状态
     */
    public Integer getState() {
        return state;
    }

    /**
     * 设置：图片
     */
    public void setImg(String img) {
        this.img = img;
    }

    /**
     * 获取：图片
     */
    public String getImg() {
        return img;
    }

    /**
     * 设置：所属者
     */
    public void setVillageid(Long villageid) {
        this.villageid = villageid;
    }

    /**
     * 获取：所属者
     */
    public Long getVillageid() {
        return villageid;
    }

    /**
     * 设置：用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取：用户名
     */
    public String getUsername() {
        return username;
    }
}

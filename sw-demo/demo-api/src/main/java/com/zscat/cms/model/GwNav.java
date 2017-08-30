package com.zscat.cms.model;

import com.zsCat.common.base.BaseEntity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "gw_nav")
public class GwNav extends BaseEntity {

    @Column(name = "addTime")
    private Date addtime;

    private Boolean display;

    private Integer location;

    @Column(name = "new_win")
    private Integer newWin;

    private Integer sequence;

    private String title;

    private String type;

    private String url;

    @Column(name = "original_url")
    private String originalUrl;


    /**
     * @return addTime
     */
    public Date getAddtime() {
        return addtime;
    }

    /**
     * @param addtime
     */
    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    /**
     * @return display
     */
    public Boolean getDisplay() {
        return display;
    }

    /**
     * @param display
     */
    public void setDisplay(Boolean display) {
        this.display = display;
    }

    /**
     * @return location
     */
    public Integer getLocation() {
        return location;
    }

    /**
     * @param location
     */
    public void setLocation(Integer location) {
        this.location = location;
    }

    /**
     * @return new_win
     */
    public Integer getNewWin() {
        return newWin;
    }

    /**
     * @param newWin
     */
    public void setNewWin(Integer newWin) {
        this.newWin = newWin;
    }

    /**
     * @return sequence
     */
    public Integer getSequence() {
        return sequence;
    }

    /**
     * @param sequence
     */
    public void setSequence(Integer sequence) {
        this.sequence = sequence;
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
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(String type) {
        this.type = type;
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
     * @return original_url
     */
    public String getOriginalUrl() {
        return originalUrl;
    }

    /**
     * @param originalUrl
     */
    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }
}
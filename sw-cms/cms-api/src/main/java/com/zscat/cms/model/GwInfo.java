package com.zscat.cms.model;

import com.zsCat.common.base.BaseEntity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "gw_info")
public class GwInfo extends BaseEntity {


    private String name;

    private Boolean display;

    private String content;

    @Column(name = "addTime")
    private Date addtime;



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
}
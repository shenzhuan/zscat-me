package com.zscat.cms.model;

import javax.persistence.*;

import com.zsCat.common.base.BaseEntity;

@Table(name = "cms_site")
public class CmsSite extends BaseEntity{
    

    /**
     * 版权
     */
    private String copyright;

    /**
     * 描述
     */
    private String description;

    /**
     * 关键字
     */
    private String keywords;

    /**
     * logo
     */
    private String logo;

    /**
     * 名称
     */
    private String name;

    /**
     * 主题
     */
    private String theme;

    /**
     * 标题
     */
    private String title;

    

    /**
     * 获取版权
     *
     * @return copyright - 版权
     */
    public String getCopyright() {
        return copyright;
    }

    /**
     * 设置版权
     *
     * @param copyright 版权
     */
    public void setCopyright(String copyright) {
        this.copyright = copyright;
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
     * 获取logo
     *
     * @return logo - logo
     */
    public String getLogo() {
        return logo;
    }

    /**
     * 设置logo
     *
     * @param logo logo
     */
    public void setLogo(String logo) {
        this.logo = logo;
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
     * 获取主题
     *
     * @return theme - 主题
     */
    public String getTheme() {
        return theme;
    }

    /**
     * 设置主题
     *
     * @param theme 主题
     */
    public void setTheme(String theme) {
        this.theme = theme;
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
}
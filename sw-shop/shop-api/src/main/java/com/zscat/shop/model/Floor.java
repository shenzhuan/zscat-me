package com.zscat.shop.model;

import javax.persistence.Column;
import javax.persistence.Table;

import com.zsCat.common.base.BaseEntity;

@Table(name = "t_floor")
public class Floor extends BaseEntity{
   

    /**
     * 类型名称
     */
    private String name;

    /**
     * 商品类型排序
     */
    private Integer typesort;

    /**
     * 父id
     */
    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "parent_ids")
    private String parentIds;

    private String title;

    @Column(name = "del_flag")
    private String delFlag;

    private String advimg;

    private String advurl;

   

    /**
     * 获取类型名称
     *
     * @return name - 类型名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置类型名称
     *
     * @param name 类型名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取商品类型排序
     *
     * @return typesort - 商品类型排序
     */
    public Integer getTypesort() {
        return typesort;
    }

    /**
     * 设置商品类型排序
     *
     * @param typesort 商品类型排序
     */
    public void setTypesort(Integer typesort) {
        this.typesort = typesort;
    }

    /**
     * 获取父id
     *
     * @return parent_id - 父id
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 设置父id
     *
     * @param parentId 父id
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
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
     * @return del_flag
     */
    public String getDelFlag() {
        return delFlag;
    }

    /**
     * @param delFlag
     */
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * @return advimg
     */
    public String getAdvimg() {
        return advimg;
    }

    /**
     * @param advimg
     */
    public void setAdvimg(String advimg) {
        this.advimg = advimg;
    }

    /**
     * @return advurl
     */
    public String getAdvurl() {
        return advurl;
    }

    /**
     * @param advurl
     */
    public void setAdvurl(String advurl) {
        this.advurl = advurl;
    }
}
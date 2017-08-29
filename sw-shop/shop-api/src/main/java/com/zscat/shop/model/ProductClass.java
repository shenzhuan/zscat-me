package com.zscat.shop.model;

import javax.persistence.Column;
import javax.persistence.Table;

import com.zsCat.common.base.BaseEntity;

@Table(name = "t_product_class")
public class ProductClass extends BaseEntity{
   

    /**
     * 分类名称
     */
    private String name;

    /**
     * 分类图片
     */
    private String pic;

    /**
     * 前台显示，0为否，1为是，默认为1
     */
    @Column(name = "del_flag")
    private String delFlag;

    /**
     * 名称
     */
    private String title;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "parent_ids")
    private String parentIds;

    

    /**
     * 获取分类名称
     *
     * @return name - 分类名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置分类名称
     *
     * @param name 分类名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取分类图片
     *
     * @return pic - 分类图片
     */
    public String getPic() {
        return pic;
    }

    /**
     * 设置分类图片
     *
     * @param pic 分类图片
     */
    public void setPic(String pic) {
        this.pic = pic;
    }

    /**
     * 获取前台显示，0为否，1为是，默认为1
     *
     * @return del_flag - 前台显示，0为否，1为是，默认为1
     */
    public String getDelFlag() {
        return delFlag;
    }

    /**
     * 设置前台显示，0为否，1为是，默认为1
     *
     * @param delFlag 前台显示，0为否，1为是，默认为1
     */
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * 获取名称
     *
     * @return title - 名称
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置名称
     *
     * @param title 名称
     */
    public void setTitle(String title) {
        this.title = title;
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
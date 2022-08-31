package com.mallplus.common.entity.cms;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mallplus.common.entity.BaseEntity;

import java.io.Serializable;

/**
 * <p>
 * 优选专区
 * </p>
 *
 * @author zscat
 * @since 2019-04-17
 */
@TableName("cms_prefrence_area")
public class CmsPrefrenceArea extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 标题
     */
    private String name;

    /**
     * 子标题
     */
    @TableField("sub_title")
    private String subTitle;

    /**
     * 展示图片
     */
    private byte[] pic;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 状态
     */
    @TableField("show_status")
    private Integer showStatus;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public byte[] getPic() {
        return pic;
    }

    public void setPic(byte[] pic) {
        this.pic = pic;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getShowStatus() {
        return showStatus;
    }

    public void setShowStatus(Integer showStatus) {
        this.showStatus = showStatus;
    }

    @Override
    public String toString() {
        return "CmsPrefrenceArea{" +
        ", id=" + id +
        ", name=" + name +
        ", subTitle=" + subTitle +
        ", pic=" + pic +
        ", sort=" + sort +
        ", showStatus=" + showStatus +
        "}";
    }
}

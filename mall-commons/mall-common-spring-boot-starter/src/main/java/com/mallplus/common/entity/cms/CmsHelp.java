package com.mallplus.common.entity.cms;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mallplus.common.entity.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 帮助表
 * </p>
 *
 * @author zscat
 * @since 2019-04-17
 */
@TableName("cms_help")
public class CmsHelp extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 类别
     */
    @TableField("category_id")
    private Long categoryId;

    /**
     * 图片
     */
    private String icon;

    /**
     * 标题
     */
    private String title;

    /**
     * 状态
     */
    @TableField("show_status")
    private Integer showStatus;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 点击量
     */
    @TableField("read_count")
    private Integer readCount;

    /**
     * 内容
     */
    private String content;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getShowStatus() {
        return showStatus;
    }

    public void setShowStatus(Integer showStatus) {
        this.showStatus = showStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getReadCount() {
        return readCount;
    }

    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "CmsHelp{" +
        ", id=" + id +
        ", categoryId=" + categoryId +
        ", icon=" + icon +
        ", title=" + title +
        ", showStatus=" + showStatus +
        ", createTime=" + createTime +
        ", readCount=" + readCount +
        ", content=" + content +
        "}";
    }
}

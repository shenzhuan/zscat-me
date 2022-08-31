package com.mallplus.common.entity.cms;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mallplus.common.entity.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户举报表
 * </p>
 *
 * @author zscat
 * @since 2019-04-17
 */
@TableName("cms_member_report")
public class CmsMemberReport extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 举报类型：0->商品评价；1->话题内容；2->用户评论
     */
    @TableField("report_type")
    private Integer reportType;

    /**
     * 举报人
     */
    @TableField("report_member_name")
    private String reportMemberName;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 举报对象
     */
    @TableField("report_object")
    private String reportObject;

    /**
     * 举报状态：0->未处理；1->已处理
     */
    @TableField("report_status")
    private Integer reportStatus;

    /**
     * 处理结果：0->无效；1->有效；2->恶意
     */
    @TableField("handle_status")
    private Integer handleStatus;

    /**
     * 内容
     */
    private String note;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getReportType() {
        return reportType;
    }

    public void setReportType(Integer reportType) {
        this.reportType = reportType;
    }

    public String getReportMemberName() {
        return reportMemberName;
    }

    public void setReportMemberName(String reportMemberName) {
        this.reportMemberName = reportMemberName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getReportObject() {
        return reportObject;
    }

    public void setReportObject(String reportObject) {
        this.reportObject = reportObject;
    }

    public Integer getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(Integer reportStatus) {
        this.reportStatus = reportStatus;
    }

    public Integer getHandleStatus() {
        return handleStatus;
    }

    public void setHandleStatus(Integer handleStatus) {
        this.handleStatus = handleStatus;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "CmsMemberReport{" +
        ", id=" + id +
        ", reportType=" + reportType +
        ", reportMemberName=" + reportMemberName +
        ", createTime=" + createTime +
        ", reportObject=" + reportObject +
        ", reportStatus=" + reportStatus +
        ", handleStatus=" + handleStatus +
        ", note=" + note +
        "}";
    }
}

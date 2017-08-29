package com.zscat.blog.api.model;

import com.zsCat.common.base.BaseEntity;

import java.util.Date;

import javax.persistence.*;


@Table(name = "t_blogtype")
public class BlogType extends BaseEntity {
   

    @Column(name = "typeName")
    private String typename;

    @Column(name = "orderNo")
    private Integer orderno;

    /**
     * 创建者
     */
    @Column(name = "create_by")
    private String createBy;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 更新者
     */
    @Column(name = "update_by")
    private String updateBy;

    /**
     * 更新时间
     */
    @Column(name = "update_date")
    private Date updateDate;

    /**
     * 备注信息
     */
    private String remarks;

    /**
     * 删除标记(0活null 正常 1,删除)
     */
    @Column(name = "del_flag")
    private String delFlag;

   

    /**
     * @return typeName
     */
    public String getTypename() {
        return typename;
    }

    /**
     * @param typename
     */
    public void setTypename(String typename) {
        this.typename = typename;
    }

    /**
     * @return orderNo
     */
    public Integer getOrderno() {
        return orderno;
    }

    /**
     * @param orderno
     */
    public void setOrderno(Integer orderno) {
        this.orderno = orderno;
    }

    /**
     * 获取创建者
     *
     * @return create_by - 创建者
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * 设置创建者
     *
     * @param createBy 创建者
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    /**
     * 获取创建时间
     *
     * @return create_date - 创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置创建时间
     *
     * @param createDate 创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取更新者
     *
     * @return update_by - 更新者
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /**
     * 设置更新者
     *
     * @param updateBy 更新者
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    /**
     * 获取更新时间
     *
     * @return update_date - 更新时间
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 设置更新时间
     *
     * @param updateDate 更新时间
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * 获取备注信息
     *
     * @return remarks - 备注信息
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 设置备注信息
     *
     * @param remarks 备注信息
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * 获取删除标记(0活null 正常 1,删除)
     *
     * @return del_flag - 删除标记(0活null 正常 1,删除)
     */
    public String getDelFlag() {
        return delFlag;
    }

    /**
     * 设置删除标记(0活null 正常 1,删除)
     *
     * @param delFlag 删除标记(0活null 正常 1,删除)
     */
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}
package com.zscat.shop.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;

import com.zsCat.common.base.BaseEntity;

@Table(name = "t_producttype")
public class ProductType extends BaseEntity{
   

    private String name;

    /**
     * 创建者
     */
    @Column(name = "create_by")
    private Long createBy;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 删除标记(0活null 正常 1,删除)
     */
    @Column(name = "del_flag")
    private String delFlag;

    

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
     * 获取创建者
     *
     * @return create_by - 创建者
     */
    public Long getCreateBy() {
        return createBy;
    }

    /**
     * 设置创建者
     *
     * @param createBy 创建者
     */
    public void setCreateBy(Long createBy) {
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
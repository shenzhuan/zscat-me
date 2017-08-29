package com.zscat.shop.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;

import com.zsCat.common.base.BaseEntity;

@Table(name = "t_product")
public class Product extends BaseEntity{
   

    private String title;

    private String tag;

    /**
     * 0
     */
    @Column(name = "clickHit")
    private Integer clickhit;

    private Long typeid;

    private String img;

    private String typename;

    private Long type;

    private String orderby;

    private String prices;

    private String imgmore;

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
     * 评价量
     */
    private Integer replyhit;

    /**
     * 销售量
     */
    private Integer sellhit;

    /**
     * 1推荐，2不推荐
     */
    private Integer iscom;

    private String remark;

    private String summary;

    

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
     * @return tag
     */
    public String getTag() {
        return tag;
    }

    /**
     * @param tag
     */
    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     * 获取0
     *
     * @return clickHit - 0
     */
    public Integer getClickhit() {
        return clickhit;
    }

    /**
     * 设置0
     *
     * @param clickhit 0
     */
    public void setClickhit(Integer clickhit) {
        this.clickhit = clickhit;
    }

    /**
     * @return typeid
     */
    public Long getTypeid() {
        return typeid;
    }

    /**
     * @param typeid
     */
    public void setTypeid(Long typeid) {
        this.typeid = typeid;
    }

    /**
     * @return img
     */
    public String getImg() {
        return img;
    }

    /**
     * @param img
     */
    public void setImg(String img) {
        this.img = img;
    }

    /**
     * @return typename
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
     * @return type
     */
    public Long getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(Long type) {
        this.type = type;
    }

    /**
     * @return orderby
     */
    public String getOrderby() {
        return orderby;
    }

    /**
     * @param orderby
     */
    public void setOrderby(String orderby) {
        this.orderby = orderby;
    }

    /**
     * @return prices
     */
    public String getPrices() {
        return prices;
    }

    /**
     * @param prices
     */
    public void setPrices(String prices) {
        this.prices = prices;
    }

    /**
     * @return imgmore
     */
    public String getImgmore() {
        return imgmore;
    }

    /**
     * @param imgmore
     */
    public void setImgmore(String imgmore) {
        this.imgmore = imgmore;
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

    /**
     * 获取评价量
     *
     * @return replyhit - 评价量
     */
    public Integer getReplyhit() {
        return replyhit;
    }

    /**
     * 设置评价量
     *
     * @param replyhit 评价量
     */
    public void setReplyhit(Integer replyhit) {
        this.replyhit = replyhit;
    }

    /**
     * 获取销售量
     *
     * @return sellhit - 销售量
     */
    public Integer getSellhit() {
        return sellhit;
    }

    /**
     * 设置销售量
     *
     * @param sellhit 销售量
     */
    public void setSellhit(Integer sellhit) {
        this.sellhit = sellhit;
    }

    /**
     * 获取1推荐，2不推荐
     *
     * @return iscom - 1推荐，2不推荐
     */
    public Integer getIscom() {
        return iscom;
    }

    /**
     * 设置1推荐，2不推荐
     *
     * @param iscom 1推荐，2不推荐
     */
    public void setIscom(Integer iscom) {
        this.iscom = iscom;
    }

    /**
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return summary
     */
    public String getSummary() {
        return summary;
    }

    /**
     * @param summary
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }
}
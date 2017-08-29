package com.zscat.shop.model;

import javax.persistence.*;

import com.zsCat.common.base.BaseEntity;

@Table(name = "t_consult")
public class Consult extends BaseEntity {
    

    /**
     * 商品编号
     */
    @Column(name = "goods_id")
    private Long goodsId;

    /**
     * 商品名称
     */
    @Column(name = "cgoods_name")
    private String cgoodsName;

    /**
     * 咨询发布者会员编号(0：游客)
     */
    @Column(name = "member_id")
    private Long memberId;

    /**
     * 会员名称
     */
    @Column(name = "cmember_name")
    private String cmemberName;

    /**
     * 店铺编号
     */
    @Column(name = "store_id")
    private Long storeId;

    /**
     * 咨询发布者邮箱
     */
    private String email;

    /**
     * 咨询内容
     */
    @Column(name = "consult_content")
    private String consultContent;

    /**
     * 咨询添加时间
     */
    @Column(name = "consult_addtime")
    private Long consultAddtime;

    /**
     * 咨询回复内容
     */
    @Column(name = "consult_reply")
    private String consultReply;

    /**
     * 咨询回复时间
     */
    @Column(name = "consult_reply_time")
    private Long consultReplyTime;

    /**
     * 0表示不匿名 1表示匿名
     */
    private Boolean isanonymous;

    @Column(name = "is_del")
    private Boolean isDel;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Long createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Long updateTime;

   

    /**
     * 获取商品编号
     *
     * @return goods_id - 商品编号
     */
    public Long getGoodsId() {
        return goodsId;
    }

    /**
     * 设置商品编号
     *
     * @param goodsId 商品编号
     */
    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 获取商品名称
     *
     * @return cgoods_name - 商品名称
     */
    public String getCgoodsName() {
        return cgoodsName;
    }

    /**
     * 设置商品名称
     *
     * @param cgoodsName 商品名称
     */
    public void setCgoodsName(String cgoodsName) {
        this.cgoodsName = cgoodsName;
    }

    /**
     * 获取咨询发布者会员编号(0：游客)
     *
     * @return member_id - 咨询发布者会员编号(0：游客)
     */
    public Long getMemberId() {
        return memberId;
    }

    /**
     * 设置咨询发布者会员编号(0：游客)
     *
     * @param memberId 咨询发布者会员编号(0：游客)
     */
    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    /**
     * 获取会员名称
     *
     * @return cmember_name - 会员名称
     */
    public String getCmemberName() {
        return cmemberName;
    }

    /**
     * 设置会员名称
     *
     * @param cmemberName 会员名称
     */
    public void setCmemberName(String cmemberName) {
        this.cmemberName = cmemberName;
    }

    /**
     * 获取店铺编号
     *
     * @return store_id - 店铺编号
     */
    public Long getStoreId() {
        return storeId;
    }

    /**
     * 设置店铺编号
     *
     * @param storeId 店铺编号
     */
    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    /**
     * 获取咨询发布者邮箱
     *
     * @return email - 咨询发布者邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置咨询发布者邮箱
     *
     * @param email 咨询发布者邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取咨询内容
     *
     * @return consult_content - 咨询内容
     */
    public String getConsultContent() {
        return consultContent;
    }

    /**
     * 设置咨询内容
     *
     * @param consultContent 咨询内容
     */
    public void setConsultContent(String consultContent) {
        this.consultContent = consultContent;
    }

    /**
     * 获取咨询添加时间
     *
     * @return consult_addtime - 咨询添加时间
     */
    public Long getConsultAddtime() {
        return consultAddtime;
    }

    /**
     * 设置咨询添加时间
     *
     * @param consultAddtime 咨询添加时间
     */
    public void setConsultAddtime(Long consultAddtime) {
        this.consultAddtime = consultAddtime;
    }

    /**
     * 获取咨询回复内容
     *
     * @return consult_reply - 咨询回复内容
     */
    public String getConsultReply() {
        return consultReply;
    }

    /**
     * 设置咨询回复内容
     *
     * @param consultReply 咨询回复内容
     */
    public void setConsultReply(String consultReply) {
        this.consultReply = consultReply;
    }

    /**
     * 获取咨询回复时间
     *
     * @return consult_reply_time - 咨询回复时间
     */
    public Long getConsultReplyTime() {
        return consultReplyTime;
    }

    /**
     * 设置咨询回复时间
     *
     * @param consultReplyTime 咨询回复时间
     */
    public void setConsultReplyTime(Long consultReplyTime) {
        this.consultReplyTime = consultReplyTime;
    }

    /**
     * 获取0表示不匿名 1表示匿名
     *
     * @return isanonymous - 0表示不匿名 1表示匿名
     */
    public Boolean getIsanonymous() {
        return isanonymous;
    }

    /**
     * 设置0表示不匿名 1表示匿名
     *
     * @param isanonymous 0表示不匿名 1表示匿名
     */
    public void setIsanonymous(Boolean isanonymous) {
        this.isanonymous = isanonymous;
    }

    /**
     * @return is_del
     */
    public Boolean getIsDel() {
        return isDel;
    }

    /**
     * @param isDel
     */
    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Long getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取修改时间
     *
     * @return update_time - 修改时间
     */
    public Long getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置修改时间
     *
     * @param updateTime 修改时间
     */
    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }
}
package com.zscat.shop.model;

import com.zsCat.common.base.BaseEntity;

import javax.persistence.*;



@Table(name = "t_address")
public class Address extends BaseEntity {
   

    /**
     * 会员ID
     */
    @Column(name = "member_id")
    private Long memberId;

    /**
     * 会员姓名
     */
    @Column(name = "true_name")
    private String trueName;

    /**
     * 地区ID
     */
    @Column(name = "area_id")
    private Long areaId;

    /**
     * 市级ID
     */
    @Column(name = "city_id")
    private Long cityId;

    /**
     * 地区内容
     */
    @Column(name = "area_info")
    private String areaInfo;

    /**
     * 地址
     */
    private String address;

    /**
     * 座机电话
     */
    @Column(name = "tel_phone")
    private String telPhone;

    /**
     * 手机电话
     */
    @Column(name = "mob_phone")
    private String mobPhone;

    /**
     * 1默认收货地址
     */
    @Column(name = "is_default")
    private String isDefault;

    /**
     * 省级id
     */
    @Column(name = "province_id")
    private Integer provinceId;

    /**
     * 邮编
     */
    @Column(name = "zip_code")
    private Integer zipCode;

   

    /**
     * 获取会员ID
     *
     * @return member_id - 会员ID
     */
    public Long getMemberId() {
        return memberId;
    }

    /**
     * 设置会员ID
     *
     * @param memberId 会员ID
     */
    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    /**
     * 获取会员姓名
     *
     * @return true_name - 会员姓名
     */
    public String getTrueName() {
        return trueName;
    }

    /**
     * 设置会员姓名
     *
     * @param trueName 会员姓名
     */
    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    /**
     * 获取地区ID
     *
     * @return area_id - 地区ID
     */
    public Long getAreaId() {
        return areaId;
    }

    /**
     * 设置地区ID
     *
     * @param areaId 地区ID
     */
    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    /**
     * 获取市级ID
     *
     * @return city_id - 市级ID
     */
    public Long getCityId() {
        return cityId;
    }

    /**
     * 设置市级ID
     *
     * @param cityId 市级ID
     */
    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    /**
     * 获取地区内容
     *
     * @return area_info - 地区内容
     */
    public String getAreaInfo() {
        return areaInfo;
    }

    /**
     * 设置地区内容
     *
     * @param areaInfo 地区内容
     */
    public void setAreaInfo(String areaInfo) {
        this.areaInfo = areaInfo;
    }

    /**
     * 获取地址
     *
     * @return address - 地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置地址
     *
     * @param address 地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取座机电话
     *
     * @return tel_phone - 座机电话
     */
    public String getTelPhone() {
        return telPhone;
    }

    /**
     * 设置座机电话
     *
     * @param telPhone 座机电话
     */
    public void setTelPhone(String telPhone) {
        this.telPhone = telPhone;
    }

    /**
     * 获取手机电话
     *
     * @return mob_phone - 手机电话
     */
    public String getMobPhone() {
        return mobPhone;
    }

    /**
     * 设置手机电话
     *
     * @param mobPhone 手机电话
     */
    public void setMobPhone(String mobPhone) {
        this.mobPhone = mobPhone;
    }

    /**
     * 获取1默认收货地址
     *
     * @return is_default - 1默认收货地址
     */
    public String getIsDefault() {
        return isDefault;
    }

    /**
     * 设置1默认收货地址
     *
     * @param isDefault 1默认收货地址
     */
    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    /**
     * 获取省级id
     *
     * @return province_id - 省级id
     */
    public Integer getProvinceId() {
        return provinceId;
    }

    /**
     * 设置省级id
     *
     * @param provinceId 省级id
     */
    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    /**
     * 获取邮编
     *
     * @return zip_code - 邮编
     */
    public Integer getZipCode() {
        return zipCode;
    }

    /**
     * 设置邮编
     *
     * @param zipCode 邮编
     */
    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }
}
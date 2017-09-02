package com.zscat.shop.model;

import java.util.Date;

import javax.persistence.*;

import com.zsCat.common.base.BaseEntity;

@Table(name = "t_member")
public class Member extends BaseEntity{
   

    /**
     * 姓名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    private Date addtime;

    private String phone;

    private String qq;

    private String email;

    @Column(name = "trueName")
    private String truename;

    private Integer gold;

    private Integer status;

    private String address;

   

    /**
     * 获取姓名
     *
     * @return username - 姓名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置姓名
     *
     * @param username 姓名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return addtime
     */
    public Date getAddtime() {
        return addtime;
    }

    /**
     * @param addtime
     */
    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    /**
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return qq
     */
    public String getQq() {
        return qq;
    }

    /**
     * @param qq
     */
    public void setQq(String qq) {
        this.qq = qq;
    }

    /**
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return trueName
     */
    public String getTruename() {
        return truename;
    }

    /**
     * @param truename
     */
    public void setTruename(String truename) {
        this.truename = truename;
    }

    /**
     * @return gold
     */
    public Integer getGold() {
        return gold;
    }

    /**
     * @param gold
     */
    public void setGold(Integer gold) {
        this.gold = gold;
    }

    /**
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Member{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", addtime=" + addtime +
                ", phone='" + phone + '\'' +
                ", qq='" + qq + '\'' +
                ", email='" + email + '\'' +
                ", truename='" + truename + '\'' +
                '}';
    }
}
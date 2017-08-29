package com.zscat.blog.api.model;

import com.zsCat.common.base.BaseEntity;

import javax.persistence.*;



@Table(name = "t_blogger")
public class Blogger extends BaseEntity {
   

    @Column(name = "userName")
    private String username;

    private String password;

    @Column(name = "nickName")
    private String nickname;

    private String sign;

    @Column(name = "imageName")
    private String imagename;

    private String profile;

   

    /**
     * @return userName
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return nickName
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @param nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * @return sign
     */
    public String getSign() {
        return sign;
    }

    /**
     * @param sign
     */
    public void setSign(String sign) {
        this.sign = sign;
    }

    /**
     * @return imageName
     */
    public String getImagename() {
        return imagename;
    }

    /**
     * @param imagename
     */
    public void setImagename(String imagename) {
        this.imagename = imagename;
    }

    /**
     * @return profile
     */
    public String getProfile() {
        return profile;
    }

    /**
     * @param profile
     */
    public void setProfile(String profile) {
        this.profile = profile;
    }
}
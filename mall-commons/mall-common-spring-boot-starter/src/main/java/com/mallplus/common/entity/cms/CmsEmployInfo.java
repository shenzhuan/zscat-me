package com.mallplus.common.entity.cms;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.mallplus.common.entity.BaseEntity;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author zscat
 * @since 2019-04-28
 */
@TableName("cms_employ_info")
public class CmsEmployInfo extends BaseEntity  implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 年纪
     */
    private Integer age;

    /**
     * 性别
     */
    private String gender;

    /**
     * 爱好
     */
    private String hobby;

    /**
     * 人生物语
     */
    private String peoplenote;

    /**
     * 电话
     */
    private String phone;

    /**
     * 地址
     */
    private String address;

    /**
     * 建议
     */
    private String suggestion;

    /**
     * 主管
     */
    private String guide;

    /**
     * 备管1
     */
    private String guide1;

    /**
     * 备管2
     */
    private String guide2;

    private LocalDateTime createtime;

    /**
     * 1区域主管2备管
     */
    private Integer type;


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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getPeoplenote() {
        return peoplenote;
    }

    public void setPeoplenote(String peoplenote) {
        this.peoplenote = peoplenote;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public String getGuide() {
        return guide;
    }

    public void setGuide(String guide) {
        this.guide = guide;
    }

    public String getGuide1() {
        return guide1;
    }

    public void setGuide1(String guide1) {
        this.guide1 = guide1;
    }

    public String getGuide2() {
        return guide2;
    }

    public void setGuide2(String guide2) {
        this.guide2 = guide2;
    }

    public LocalDateTime getCreatetime() {
        return createtime;
    }

    public void setCreatetime(LocalDateTime createtime) {
        this.createtime = createtime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "CmsEmployInfo{" +
        ", id=" + id +
        ", name=" + name +
        ", age=" + age +
        ", gender=" + gender +
        ", hobby=" + hobby +
        ", peoplenote=" + peoplenote +
        ", phone=" + phone +
        ", address=" + address +
        ", suggestion=" + suggestion +
        ", guide=" + guide +
        ", guide1=" + guide1 +
        ", guide2=" + guide2 +
        ", createtime=" + createtime +
        ", type=" + type +
        "}";
    }
}

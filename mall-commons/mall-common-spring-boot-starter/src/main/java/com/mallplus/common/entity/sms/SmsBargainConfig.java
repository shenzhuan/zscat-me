package com.mallplus.common.entity.sms;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 砍价免单设置表
 * </p>
 *
 * @author zscat
 * @since 2019-10-17
 */
@TableName("sms_bargain_config")
public class SmsBargainConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 插件id
     */
    @TableField("plug_ins_id")
    private Long plugInsId;

    /**
     * 能砍的次数
     */
    @TableField("can_num")
    private Integer canNum;

    /**
     * 每天最多帮别人砍的次数
     */
    @TableField("help_num")
    private Integer helpNum;

    /**
     * 每次砍价的参数
     */
    private String parameter;

    /**
     * 逾期失效时间
     */
    @TableField("invalid_time")
    private Integer invalidTime;

    /**
     * 修改时间
     */
    @TableField("add_time")
    private LocalDateTime addTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPlugInsId() {
        return plugInsId;
    }

    public void setPlugInsId(Long plugInsId) {
        this.plugInsId = plugInsId;
    }

    public Integer getCanNum() {
        return canNum;
    }

    public void setCanNum(Integer canNum) {
        this.canNum = canNum;
    }

    public Integer getHelpNum() {
        return helpNum;
    }

    public void setHelpNum(Integer helpNum) {
        this.helpNum = helpNum;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public Integer getInvalidTime() {
        return invalidTime;
    }

    public void setInvalidTime(Integer invalidTime) {
        this.invalidTime = invalidTime;
    }

    public LocalDateTime getAddTime() {
        return addTime;
    }

    public void setAddTime(LocalDateTime addTime) {
        this.addTime = addTime;
    }

    @Override
    public String toString() {
        return "SmsBargainConfig{" +
                ", id=" + id +
                ", plugInsId=" + plugInsId +
                ", canNum=" + canNum +
                ", helpNum=" + helpNum +
                ", parameter=" + parameter +
                ", invalidTime=" + invalidTime +
                ", addTime=" + addTime +
                "}";
    }
}

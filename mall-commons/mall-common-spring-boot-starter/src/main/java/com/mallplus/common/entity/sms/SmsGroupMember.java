package com.mallplus.common.entity.sms;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mallplus.common.entity.BaseEntity;
import com.mallplus.common.utils.ValidatorUtils;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author zscat
 * @since 2019-04-19
 */
@TableName("sms_group_member")
public class SmsGroupMember extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("group_id")
    private Long groupId;

    @TableField("member_id")
    private String memberId;

    @TableField("create_time")
    private Date createTime;
    @TableField("exipre_time")
    private Long exipreTime;
    @TableField("main_id")
    private Long mainId;

    private String name;

    @TableField("goods_id")
    private Long goodsId;

    /**
     * 状态
     */
    private Integer status;

    @TableField("order_id")
    private String orderId;
    @TableField(exist = false)
    private List pics;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getExipreTime() {
        return exipreTime;
    }

    public void setExipreTime(Long exipreTime) {
        this.exipreTime = exipreTime;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getMainId() {
        return mainId;
    }

    public void setMainId(Long mainId) {
        this.mainId = mainId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List getPics() {
        if (ValidatorUtils.notEmpty(name)){
            this.pics = Arrays.asList(name.split(","));
        }
        return pics;
    }

    public void setPics(List pics) {
        if (ValidatorUtils.notEmpty(name)){
            this.pics = Arrays.asList(name.split(","));
        }
    }
}

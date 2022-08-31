package com.mallplus.common.entity.ums;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mallplus.common.entity.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author zscat
 * @since 2019-04-19
 */
@TableName("ums_collect")
public class UmsCollect extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 会员编号
     */
    @TableField("member_id")
    private Long memberId;

    /**
     * 商品编号
     */
    @TableField("goods_id")
    private Long goodsId;

    /**
     * 商品
     */
    @TableField("goods_name")
    private String goodsName;

    /**
     * 图片
     */
    @TableField("goods_pic")
    private String goodsPic;

    /**
     * 价格
     */
    @TableField("goods_price")
    private String goodsPrice;

    /**
     * 1 商品 2 文章
     */
    private Integer type;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsPic() {
        return goodsPic;
    }

    public void setGoodsPic(String goodsPic) {
        this.goodsPic = goodsPic;
    }

    public String getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "UmsCollect{" +
        ", id=" + id +
        ", memberId=" + memberId +
        ", goodsId=" + goodsId +
        ", goodsName=" + goodsName +
        ", goodsPic=" + goodsPic +
        ", goodsPrice=" + goodsPrice +
        ", type=" + type +
        ", createTime=" + createTime +
        "}";
    }
}

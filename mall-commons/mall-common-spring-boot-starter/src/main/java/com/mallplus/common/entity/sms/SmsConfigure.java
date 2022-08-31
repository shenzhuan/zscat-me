package com.mallplus.common.entity.sms;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 商品配置表
 * </p>
 *
 * @author zscat
 * @since 2019-10-18
 */
@TableName("sms_configure")
public class SmsConfigure implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 配置id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 配置名称
     */
    private String name;

    /**
     * 颜色
     */
    private String color;

    /**
     * 尺码
     */
    private String size;

    /**
     * 成本价
     */
    private BigDecimal costprice;

    /**
     * 出售价格
     */
    private BigDecimal price;

    /**
     * 原价格
     */
    private BigDecimal yprice;

    /**
     * 图片
     */
    private String img;

    /**
     * 商品id
     */
    private Integer pid;

    /**
     * 库存
     */
    private Integer num;

    /**
     * 单位
     */
    private String unit;

    /**
     * 砍价开始价格
     */
    @TableField("bargain_price")
    private BigDecimal bargainPrice;

    /**
     * 状态 0:未开启砍价 1:开启砍价 2 上架 3 缺货 4下架
     */
    private Integer status;

    /**
     * 属性
     */
    private String attribute;

    /**
     * 回收站 0.不回收 1.回收
     */
    private Integer recycle;

    /**
     * 总库存
     */
    @TableField("total_num")
    private Integer totalNum;


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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public BigDecimal getCostprice() {
        return costprice;
    }

    public void setCostprice(BigDecimal costprice) {
        this.costprice = costprice;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getYprice() {
        return yprice;
    }

    public void setYprice(BigDecimal yprice) {
        this.yprice = yprice;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getBargainPrice() {
        return bargainPrice;
    }

    public void setBargainPrice(BigDecimal bargainPrice) {
        this.bargainPrice = bargainPrice;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public Integer getRecycle() {
        return recycle;
    }

    public void setRecycle(Integer recycle) {
        this.recycle = recycle;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    @Override
    public String toString() {
        return "SmsConfigure{" +
                ", id=" + id +
                ", name=" + name +
                ", color=" + color +
                ", size=" + size +
                ", costprice=" + costprice +
                ", price=" + price +
                ", yprice=" + yprice +
                ", img=" + img +
                ", pid=" + pid +
                ", num=" + num +
                ", unit=" + unit +
                ", bargainPrice=" + bargainPrice +
                ", status=" + status +
                ", attribute=" + attribute +
                ", recycle=" + recycle +
                ", totalNum=" + totalNum +
                "}";
    }
}

package com.zscat.shop.model;

import javax.persistence.Table;

import com.zsCat.common.base.BaseEntity;

@Table(name = "t_cart")
public class Cart extends BaseEntity{
   
    private Long goodsid;

    private Long userid;

    private String goodsname;

    private String price;

    /**
     * 1
     */
    private Integer count;

    private String img;

    

    /**
     * @return goodsid
     */
    public Long getGoodsid() {
        return goodsid;
    }

    /**
     * @param goodsid
     */
    public void setGoodsid(Long goodsid) {
        this.goodsid = goodsid;
    }

    /**
     * @return userid
     */
    public Long getUserid() {
        return userid;
    }

    /**
     * @param userid
     */
    public void setUserid(Long userid) {
        this.userid = userid;
    }

    /**
     * @return goodsname
     */
    public String getGoodsname() {
        return goodsname;
    }

    /**
     * @param goodsname
     */
    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
    }

    /**
     * @return price
     */
    public String getPrice() {
        return price;
    }

    /**
     * @param price
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * 获取1
     *
     * @return count - 1
     */
    public Integer getCount() {
        return count;
    }

    /**
     * 设置1
     *
     * @param count 1
     */
    public void setCount(Integer count) {
        this.count = count;
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
}
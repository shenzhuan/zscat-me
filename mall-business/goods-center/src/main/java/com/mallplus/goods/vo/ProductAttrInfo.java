package com.mallplus.goods.vo;


/**
 * 商品分类对应属性信息
 * https://github.com/shenzhuan/mallplus on 2018/5/23.
 */
public class ProductAttrInfo {
    private Long attributeId;
    private Long attributeCategoryId;

    public Long getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(Long attributeId) {
        this.attributeId = attributeId;
    }

    public Long getAttributeCategoryId() {
        return attributeCategoryId;
    }

    public void setAttributeCategoryId(Long attributeCategoryId) {
        this.attributeCategoryId = attributeCategoryId;
    }
}

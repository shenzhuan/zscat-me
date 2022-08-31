package com.mallplus.goods.vo;

/**
 * 查询单个产品进行修改时返回的结果
 * https://github.com/shenzhuan/mallplus on 2018/4/26.
 */
public class PmsProductResult extends PmsProductParam {
    //商品所选分类的父id
    private Long cateParentId;

    private  int  is_favorite ;// 1 已收藏 2 未收藏

    public int getIs_favorite() {
        return is_favorite;
    }

    public void setIs_favorite(int is_favorite) {
        this.is_favorite = is_favorite;
    }

    public Long getCateParentId() {
        return cateParentId;
    }

    public void setCateParentId(Long cateParentId) {
        this.cateParentId = cateParentId;
    }
}

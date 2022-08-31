package com.mallplus.goods.vo;


import com.mallplus.common.entity.pms.PmsProductCategory;

import java.util.List;

/**
 * https://github.com/shenzhuan/mallplus on 2018/5/25.
 */
public class PmsProductCategoryWithChildrenItem extends PmsProductCategory {
    private List<PmsProductCategory> children;

    public List<PmsProductCategory> getChildren() {
        return children;
    }

    public void setChildren(List<PmsProductCategory> children) {
        this.children = children;
    }
}

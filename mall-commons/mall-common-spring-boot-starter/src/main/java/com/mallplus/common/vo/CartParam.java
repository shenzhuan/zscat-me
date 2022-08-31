package com.mallplus.common.vo;

import lombok.Data;

/**
 * @Auther: shenzhuan
 * @Date: 2019/4/21 16:21
 * @Description:
 */
@Data
public class CartParam {
    private Long cartId;
    private Long skuId;
    private Long goodsId;
    private Integer total;
    private Long memberId;
    private String ids;
    // 1 选中 2 未选中
    private Integer isSelected;
    // 1sku 2spu
    private Integer type;
}

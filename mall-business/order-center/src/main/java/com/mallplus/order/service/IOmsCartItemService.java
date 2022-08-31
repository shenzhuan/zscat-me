package com.mallplus.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mallplus.common.entity.oms.OmsCartItem;
import com.mallplus.common.vo.CartPromotionItem;

import java.util.List;

/**
 * <p>
 * 购物车表 服务类
 * </p>
 *
 * @author zscat
 * @since 2019-04-17
 */
public interface IOmsCartItemService extends IService<OmsCartItem> {

    /**
     * 根据会员编号获取购物车列表
     */
    List<OmsCartItem> list(Long memberId, List<Long> ids);

    OmsCartItem selectById(Long id);

    /**
     * 获取包含促销活动信息的购物车列表
     */
    List<CartPromotionItem> listPromotion(Long memberId, List<Long> ids);

    /**
     * 修改某个购物车商品的数量
     */
    int updateQuantity(Long id, Long memberId, Integer quantity);

    /**
     * 批量删除购物车中的商品
     */
    int delete(Long memberId, List<Long> ids);


    /**
     * 清空购物车
     */
    int clear(Long memberId);

    List<CartPromotionItem> calcCartPromotion(List<OmsCartItem> cartItemList);

}

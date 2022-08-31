package com.mallplus.marking.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mallplus.common.entity.oms.OmsCartItem;
import com.mallplus.common.entity.sms.SmsCoupon;
import com.mallplus.common.entity.sms.SmsCouponHistory;
import com.mallplus.common.utils.CommonResult;
import com.mallplus.common.vo.CartMarkingVo;
import com.mallplus.common.vo.CartPromotionItem;
import com.mallplus.common.vo.SmsCouponHistoryDetail;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 优惠卷表 服务类
 * </p>
 *
 * @author zscat
 * @since 2019-04-19
 */
public interface ISmsCouponService extends IService<SmsCoupon> {

    /**
     * 会员添加优惠券
     */
    @Transactional
    CommonResult add(Long couponId,Long memberId);

    /**
     * 获取优惠券列表
     *
     * @param useStatus 优惠券的使用状态
     */
    List<SmsCouponHistory> list(Integer useStatus,Long memberId);

    /**
     * 根据购物车信息获取可用优惠券
     */
    List<SmsCouponHistoryDetail> listCart(CartMarkingVo vo);


    List<SmsCoupon> selectNotRecive(Long memberId);

    List<SmsCoupon> selectRecive(Long memberId);

    List<SmsCoupon> selectNotRecive();

}

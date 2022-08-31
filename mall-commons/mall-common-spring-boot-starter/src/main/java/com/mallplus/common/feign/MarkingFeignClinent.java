package com.mallplus.common.feign;

import com.mallplus.common.constant.ServiceNameConstants;
import com.mallplus.common.entity.oms.OmsCartItem;
import com.mallplus.common.entity.pms.PmsProduct;
import com.mallplus.common.entity.pms.PmsSkuStock;
import com.mallplus.common.entity.sms.*;
import com.mallplus.common.feign.fallback.PmsFeignClientFallbackFactory;
import com.mallplus.common.vo.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author mall
 */
@FeignClient(name = ServiceNameConstants.MARKING_SERVICE, fallbackFactory = PmsFeignClientFallbackFactory.class, decode404 = true)
public interface MarkingFeignClinent {
    /**
     * feign rpc访问远程/goods/{id}接口
     * 查询用户实体对象PmsProduct
     *
     * @param id
     * @return
     */


    /**
     * 根据购物车信息获取可用优惠券
     */
    @PostMapping(value = "/notAuth/getPromotionProductList")
    List<SmsCouponHistoryDetail> listCart(@RequestBody CartMarkingVo vo);

    @GetMapping(value = "/notAuth/listCouponHistory")
    List<SmsCouponHistory> listCouponHistory(@RequestBody  SmsCouponHistory queryC);

    @PostMapping(value = "/notAuth/updateCouponHistoryById")
    void updateCouponHistoryById(@RequestBody  SmsCouponHistory couponHistory);

    @GetMapping(value = "/notAuth/listGroup")
    List<SmsGroup> listGroup();

    @GetMapping(value = "/notAuth/getGroupById")
    SmsGroup getGroupById(@RequestParam("id") Long id);

    @GetMapping(value = "/notAuth/selectGroupMemberList")
    Object selectGroupMemberList(@RequestParam("id") Long id);

    @GetMapping(value = "/notAuth/acceptGroup")
    Object acceptGroup(@RequestParam("orderParam") OrderParam orderParam);

    @PostMapping(value = "/notAuth/updateGroupById")
    void updateGroupById(@RequestBody  SmsGroup group);

    @PostMapping(value = "/notAuth/matchOrderBasicMarking")
    SmsBasicMarking matchOrderBasicMarking(@RequestBody CartMarkingVo vo);

    @PostMapping(value = "/notAuth/getFlashPromotionProductRelationById")
    SmsFlashPromotionProductRelation getFlashPromotionProductRelationById(Long id);

    @PostMapping(value = "/notAuth/updateFlashPromotionProductRelationById")
    void updateFlashPromotionProductRelationById(@RequestBody SmsFlashPromotionProductRelation relation);

    @PostMapping(value = "/notAuth/getSmsGroupActivityById")
    SmsGroupActivity getSmsGroupActivityById(Long groupActivityId);

    @PostMapping(value = "/notAuth/getCouponById")
    SmsCoupon getCouponById(Long couponId);

    @PostMapping(value = "/notAuth/getcouponHistoryById")
    SmsCouponHistory getcouponHistoryById(Long memberCouponId);

    @PostMapping(value = "/notAuth/matchOrderBasicGifts")
    List<SmsBasicGifts> matchOrderBasicGifts(@RequestBody CartMarkingVo vo);
}

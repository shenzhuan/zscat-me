package com.mallplus.marking.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mallplus.common.entity.oms.OmsCartItem;
import com.mallplus.common.entity.sms.SmsCoupon;
import com.mallplus.common.entity.sms.SmsCouponHistory;
import com.mallplus.common.entity.sms.SmsCouponProductCategoryRelation;
import com.mallplus.common.entity.sms.SmsCouponProductRelation;
import com.mallplus.common.utils.CommonResult;
import com.mallplus.common.vo.CartMarkingVo;
import com.mallplus.common.vo.CartPromotionItem;
import com.mallplus.common.vo.SmsCouponHistoryDetail;
import com.mallplus.marking.mapper.SmsCouponHistoryMapper;
import com.mallplus.marking.mapper.SmsCouponMapper;
import com.mallplus.marking.service.ISmsCouponService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * <p>
 * 优惠卷表 服务实现类
 * </p>
 *
 * @author zscat
 * @since 2019-04-19
 */
@Service
public class SmsCouponServiceImpl extends ServiceImpl<SmsCouponMapper, SmsCoupon> implements ISmsCouponService {

    @Resource
    private SmsCouponMapper couponMapper;
    @Resource
    private SmsCouponHistoryMapper couponHistoryMapper;

    @Override
    public List<SmsCoupon> selectNotRecive(Long memberId) {
        return couponMapper.selectNotRecive(memberId);
    }

    @Override
    public List<SmsCoupon> selectRecive(Long memberId) {
        return couponMapper.selectRecive(memberId);
    }

    @Override
    public List<SmsCoupon> selectNotRecive() {
        SmsCoupon coupon = new SmsCoupon();
        coupon.setType(0);
        return couponMapper.selectList(new QueryWrapper<>(coupon).gt("end_time", new Date()));
    }

    @Override
    public CommonResult add(Long couponId,Long memberId) {
        //获取优惠券信息，判断数量
        SmsCoupon coupon = couponMapper.selectById(couponId);
        if (coupon == null) {
            return new CommonResult().failed("优惠券不存在");
        }
        if (coupon.getCount() <= 0) {
            return new CommonResult().failed("优惠券已经领完了");
        }
        Date now = new Date();
        if (now.after(coupon.getEndTime())) {
            return new CommonResult().failed("优惠券已过期");
        }
        //判断用户领取的优惠券数量是否超过限制
        SmsCouponHistory queryH = new SmsCouponHistory();
        queryH.setMemberId(memberId);
        queryH.setCouponId(couponId);

        int count = couponHistoryMapper.selectCount(new QueryWrapper<>(queryH));
        if (count >= coupon.getPerLimit()) {
            return new CommonResult().failed("您已经领取过该优惠券");
        }
        //生成领取优惠券历史
        SmsCouponHistory couponHistory = new SmsCouponHistory();
        couponHistory.setCouponId(couponId);
        couponHistory.setCouponCode(generateCouponCode(memberId));
        couponHistory.setCreateTime(now);
        couponHistory.setMemberId(memberId);
      //  couponHistory.setMemberNickname(currentMember.getNickname());
        //主动领取
        couponHistory.setGetType(1);
        //未使用
        couponHistory.setUseStatus(0);
        couponHistory.setStartTime(coupon.getStartTime());
        couponHistory.setEndTime(coupon.getEndTime());
        couponHistory.setNote(coupon.getName() + ":满" + coupon.getMinPoint() + "减" + coupon.getAmount());
        couponHistoryMapper.insert(couponHistory);
        //修改优惠券表的数量、领取数量
        coupon.setCount(coupon.getCount() - 1);
        coupon.setReceiveCount(coupon.getReceiveCount() == null ? 1 : coupon.getReceiveCount() + 1);
        couponMapper.updateById(coupon);
        return new CommonResult().success("领取成功", null);
    }

    /**
     * 16位优惠码生成：时间戳后8位+4位随机数+用户id后4位
     */
    private String generateCouponCode(Long memberId) {
        StringBuilder sb = new StringBuilder();
        Long currentTimeMillis = System.currentTimeMillis();
        String timeMillisStr = currentTimeMillis.toString();
        sb.append(timeMillisStr.substring(timeMillisStr.length() - 8));
        for (int i = 0; i < 4; i++) {
            sb.append(new Random().nextInt(10));
        }
        String memberIdStr = memberId.toString();
        if (memberIdStr.length() <= 4) {
            sb.append(String.format("%04d", memberId));
        } else {
            sb.append(memberIdStr.substring(memberIdStr.length() - 4));
        }
        return sb.toString();
    }

    @Override
    public List<SmsCouponHistory> list(Integer useStatus,Long memberId) {
        SmsCouponHistory couponHistory = new SmsCouponHistory();
        couponHistory.setMemberId(memberId);

        if (useStatus != null) {
            couponHistory.setUseStatus(useStatus);
        }
        return couponHistoryMapper.selectList(new QueryWrapper<>(couponHistory));
    }

    @Override
    public List<SmsCouponHistoryDetail> listCart(CartMarkingVo vo) {

        Date now = new Date();
        //获取该用户所有优惠券
        List<SmsCouponHistoryDetail> allList = couponHistoryMapper.getDetailList(vo.getMemberId());
        //根据优惠券使用类型来判断优惠券是否可用
        List<SmsCouponHistoryDetail> enableList = new ArrayList<>();
        List<SmsCouponHistoryDetail> disableList = new ArrayList<>();
        for (SmsCouponHistoryDetail couponHistoryDetail : allList) {
            Integer useType = couponHistoryDetail.getCoupon().getUseType();
            BigDecimal minPoint = couponHistoryDetail.getCoupon().getMinPoint();
            Date endTime = couponHistoryDetail.getCoupon().getEndTime();
            if (useType.equals(0)) {
                //0->全场通用
                //判断是否满足优惠起点
                //计算购物车商品的总价
                BigDecimal totalAmount = calcTotalAmount(vo.getCartList());
                if (now.before(endTime) && totalAmount.subtract(minPoint).intValue() >= 0) {
                    enableList.add(couponHistoryDetail);
                } else {
                    disableList.add(couponHistoryDetail);
                }
            } else if (useType.equals(1)) {
                //1->指定分类
                //计算指定分类商品的总价
                List<Long> productCategoryIds = new ArrayList<>();
                for (SmsCouponProductCategoryRelation categoryRelation : couponHistoryDetail.getCategoryRelationList()) {
                    productCategoryIds.add(categoryRelation.getProductCategoryId());
                }
                BigDecimal totalAmount = calcTotalAmountByproductCategoryId(vo.getCartList(), productCategoryIds);
                if (now.before(endTime) && totalAmount.intValue() > 0 && totalAmount.subtract(minPoint).intValue() >= 0) {
                    enableList.add(couponHistoryDetail);
                } else {
                    disableList.add(couponHistoryDetail);
                }
            } else if (useType.equals(2)) {
                //2->指定商品
                //计算指定商品的总价
                List<Long> productIds = new ArrayList<>();
                for (SmsCouponProductRelation productRelation : couponHistoryDetail.getProductRelationList()) {
                    productIds.add(productRelation.getProductId());
                }
                BigDecimal totalAmount = calcTotalAmountByProductId(vo.getCartList(), productIds);
                if (now.before(endTime) && totalAmount.intValue() > 0 && totalAmount.subtract(minPoint).intValue() >= 0) {
                    enableList.add(couponHistoryDetail);
                } else {
                    disableList.add(couponHistoryDetail);
                }
            }
        }
        if (vo.getType() == 1) {
            return enableList;
        } else {
            return disableList;
        }
    }

    private BigDecimal calcTotalAmount(List<OmsCartItem> cartItemList) {
        BigDecimal total = new BigDecimal("0");
        for (OmsCartItem item : cartItemList) {
           // BigDecimal realPrice = item.getPrice().subtract(item.getReduceAmount());
            total = total.add(item.getPrice().multiply(new BigDecimal(item.getQuantity())));
        }
        return total;
    }

    private BigDecimal calcTotalAmountByproductCategoryId(List<OmsCartItem> cartItemList, List<Long> productCategoryIds) {
        BigDecimal total = new BigDecimal("0");
        for (OmsCartItem item : cartItemList) {
            if (productCategoryIds.contains(item.getProductCategoryId())) {
                BigDecimal realPrice = item.getPrice();
                total = total.add(realPrice.multiply(new BigDecimal(item.getQuantity())));
            }
        }
        return total;
    }

    private BigDecimal calcTotalAmountByProductId(List<OmsCartItem> cartItemList, List<Long> productIds) {
        BigDecimal total = new BigDecimal("0");
        for (OmsCartItem item : cartItemList) {
            if (productIds.contains(item.getProductId())) {
                BigDecimal realPrice = item.getPrice();
                total = total.add(realPrice.multiply(new BigDecimal(item.getQuantity())));
            }
        }
        return total;
    }
}

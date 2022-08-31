package com.mallplus.order.service.impl;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mallplus.common.constant.AllEnum;
import com.mallplus.common.constant.OrderStatus;
import com.mallplus.common.entity.oms.*;
import com.mallplus.common.entity.pms.*;
import com.mallplus.common.entity.sms.*;
import com.mallplus.common.entity.ums.UmsIntegrationConsumeSetting;
import com.mallplus.common.entity.ums.UmsMember;
import com.mallplus.common.entity.ums.UmsMemberBlanceLog;
import com.mallplus.common.exception.ApiMallPlusException;
import com.mallplus.common.exception.BusinessException;
import com.mallplus.common.feign.MarkingFeignClinent;
import com.mallplus.common.feign.MemberFeignClient;
import com.mallplus.common.feign.PmsFeignClinent;
import com.mallplus.common.redis.constant.RedisToolsConstant;
import com.mallplus.common.redis.template.RedisUtil;
import com.mallplus.common.utils.CommonResult;
import com.mallplus.common.utils.DateUtils;
import com.mallplus.common.utils.JsonUtil;
import com.mallplus.common.utils.ValidatorUtils;
import com.mallplus.common.vo.*;
import com.mallplus.order.config.WxAppletProperties;
import com.mallplus.order.mapper.OmsCartItemMapper;
import com.mallplus.order.mapper.OmsOrderMapper;
import com.mallplus.order.mapper.OmsOrderOperateHistoryMapper;
import com.mallplus.order.mapper.OmsOrderSettingMapper;
import com.mallplus.order.service.*;
import com.mallplus.order.utils.applet.TemplateData;
import com.mallplus.order.utils.applet.WX_TemplateMsgUtil;
import com.mallplus.order.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StopWatch;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author zscat
 * @since 2019-04-17
 */
@Slf4j
@Service
public class OmsOrderServiceImpl extends ServiceImpl<OmsOrderMapper, OmsOrder> implements IOmsOrderService {

    @Autowired
    private WxAppletProperties wxAppletProperties;
    @Resource
    private WechatApiService wechatApiService;

    @Resource
    private IUmsMemberReceiveAddressService addressService;
    @Resource
    private OmsOrderMapper orderMapper;
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private IOmsOrderOperateHistoryService orderOperateHistoryDao;
    @Resource
    private OmsOrderOperateHistoryMapper orderOperateHistoryMapper;
    @Resource
    private PmsFeignClinent pmsFeignClinent;
    @Resource
    private MemberFeignClient memberFeignClient;
    @Resource
    private MarkingFeignClinent markingFeignClinent;
    @Resource
    private OmsCartItemMapper cartItemMapper;

    @Resource
    private IOmsOrderService orderService;
    @Resource
    private IOmsOrderItemService orderItemService;

    @Resource
    private OmsOrderSettingMapper orderSettingMapper;
    @Resource
    private   IOmsCartItemService cartItemService;
    @Autowired
    private IOmsOrderOperateHistoryService orderOperateHistoryService;
    @Autowired
    private ApiContext apiContext;

    @Override
    public int delivery(List<OmsOrderDeliveryParam> deliveryParamList) {
        //批量发货
        int count = orderMapper.delivery(deliveryParamList);
        //添加操作记录
        List<OmsOrderOperateHistory> operateHistoryList = deliveryParamList.stream()
                .map(omsOrderDeliveryParam -> {
                    OmsOrderOperateHistory history = new OmsOrderOperateHistory();
                    history.setOrderId(omsOrderDeliveryParam.getOrderId());
                    history.setCreateTime(new Date());
                    history.setOperateMan("后台管理员");
                    history.setOrderStatus(2);
                    history.setNote("完成发货");
                    return history;
                }).collect(Collectors.toList());
        orderOperateHistoryDao.saveBatch(operateHistoryList);
        return count;
    }

    @Override
    public int close(List<Long> ids, String note) {
        OmsOrder record = new OmsOrder();
        record.setStatus(4);
        int count = orderMapper.update(record, new QueryWrapper<OmsOrder>().eq("delete_status",0).in("id",ids));
        List<OmsOrderOperateHistory> historyList = ids.stream().map(orderId -> {
            OmsOrderOperateHistory history = new OmsOrderOperateHistory();
            history.setOrderId(orderId);
            history.setCreateTime(new Date());
            history.setOperateMan("后台管理员");
            history.setOrderStatus(4);
            history.setNote("订单关闭:" + note);
            return history;
        }).collect(Collectors.toList());
        orderOperateHistoryDao.saveBatch(historyList);
        return count;
    }
    @Override
    public int updateReceiverInfo(OmsReceiverInfoParam receiverInfoParam) {
        OmsOrder order = new OmsOrder();
        order.setId(receiverInfoParam.getOrderId());
        order.setReceiverName(receiverInfoParam.getReceiverName());
        order.setReceiverPhone(receiverInfoParam.getReceiverPhone());
        order.setReceiverPostCode(receiverInfoParam.getReceiverPostCode());
        order.setReceiverDetailAddress(receiverInfoParam.getReceiverDetailAddress());
        order.setReceiverProvince(receiverInfoParam.getReceiverProvince());
        order.setReceiverCity(receiverInfoParam.getReceiverCity());
        order.setReceiverRegion(receiverInfoParam.getReceiverRegion());
        order.setModifyTime(new Date());
        int count = orderMapper.updateById(order);
        //插入操作记录
        OmsOrderOperateHistory history = new OmsOrderOperateHistory();
        history.setOrderId(receiverInfoParam.getOrderId());
        history.setCreateTime(new Date());
        history.setOperateMan("后台管理员");
        history.setOrderStatus(receiverInfoParam.getStatus());
        history.setNote("修改收货人信息");
        orderOperateHistoryMapper.insert(history);
        return count;
    }

    @Override
    public int updateMoneyInfo(OmsMoneyInfoParam moneyInfoParam) {
        OmsOrder order = new OmsOrder();
        order.setId(moneyInfoParam.getOrderId());
        order.setFreightAmount(moneyInfoParam.getFreightAmount());
        order.setDiscountAmount(moneyInfoParam.getDiscountAmount());
        order.setModifyTime(new Date());
        int count = orderMapper.updateById(order);
        //插入操作记录
        OmsOrderOperateHistory history = new OmsOrderOperateHistory();
        history.setOrderId(moneyInfoParam.getOrderId());
        history.setCreateTime(new Date());
        history.setOperateMan("后台管理员");
        history.setOrderStatus(moneyInfoParam.getStatus());
        history.setNote("修改费用信息");
        orderOperateHistoryMapper.insert(history);
        return count;
    }

    @Override
    public int updateNote(Long id, String note, Integer status) {
        OmsOrder order = new OmsOrder();
        order.setId(id);
        order.setNote(note);
        order.setModifyTime(new Date());
        int count = orderMapper.updateById(order);
        OmsOrderOperateHistory history = new OmsOrderOperateHistory();
        history.setOrderId(id);
        history.setCreateTime(new Date());
        history.setOperateMan("后台管理员");
        history.setOrderStatus(status);
        history.setNote("修改备注信息：" + note);
        orderOperateHistoryMapper.insert(history);
        return count;
    }


    /**
     * 计算购物车中商品的价格
     */
    private ConfirmOrderResult.CalcAmount calcCartAmount(List<OmsCartItem> cartPromotionItemList ,BigDecimal transFee,BigDecimal payAmount) {
        ConfirmOrderResult.CalcAmount calcAmount = new ConfirmOrderResult.CalcAmount();
        calcAmount.setFreightAmount(transFee);

        BigDecimal totalAmount = new BigDecimal("0");
        BigDecimal promotionAmount = new BigDecimal("0");
        CartMarkingVo vo = new CartMarkingVo();
        vo.setCartList(cartPromotionItemList);
        SmsBasicMarking basicMarking = markingFeignClinent.matchOrderBasicMarking(vo);
        log.info("basicMarking="+ com.alibaba.fastjson.JSONObject.toJSONString(basicMarking));
        if (basicMarking!=null){
            promotionAmount = basicMarking.getMinAmount();
        }
        if (promotionAmount==null){
            promotionAmount=BigDecimal.ZERO;
        }
        for (OmsCartItem cartPromotionItem : cartPromotionItemList) {
            totalAmount = totalAmount.add(cartPromotionItem.getPrice().multiply(new BigDecimal(cartPromotionItem.getQuantity())));
            //  promotionAmount = promotionAmount.add(cartPromotionItem.getReduceAmount().multiply(new BigDecimal(cartPromotionItem.getQuantity())));
        }
        calcAmount.setTotalAmount(totalAmount);
        calcAmount.setPromotionAmount(promotionAmount);
        if (payAmount.compareTo(BigDecimal.ZERO) > 0) {
            calcAmount.setPayAmount(payAmount.subtract(promotionAmount).add(transFee));
        } else {
            calcAmount.setPayAmount(totalAmount.subtract(promotionAmount).add(transFee));
        }
        if (calcAmount.getPayAmount().compareTo(BigDecimal.ZERO)<0){
            calcAmount.setPayAmount(new BigDecimal("0.01"));
        }
        return calcAmount;
    }

    /**
     * 将优惠券信息更改为指定状态
     *
     * @param couponId  优惠券id
     * @param memberId  会员id
     * @param useStatus 0->未使用；1->已使用
     */
    private void updateCouponStatus(Long couponId, Long memberId, Integer useStatus) {
        if (couponId == null) {
            return;
        }
        //查询第一张优惠券
        SmsCouponHistory queryC = new SmsCouponHistory();
        queryC.setCouponId(couponId);
        if (useStatus == 0) {
            queryC.setUseStatus(1);
        } else {
            queryC.setUseStatus(0);
        }
        List<SmsCouponHistory> couponHistoryList = markingFeignClinent.listCouponHistory(queryC);
        if (!CollectionUtils.isEmpty(couponHistoryList)) {
            SmsCouponHistory couponHistory = couponHistoryList.get(0);
            couponHistory.setUseTime(new Date());
            couponHistory.setUseStatus(useStatus);
            markingFeignClinent.updateCouponHistoryById(couponHistory);
        }
    }
    /**
     * 获取订单促销信息
     */
    private String getOrderPromotionInfo(List<OmsOrderItem> orderItemList) {
        StringBuilder sb = new StringBuilder();
        for (OmsOrderItem orderItem : orderItemList) {
            sb.append(orderItem.getPromotionName());
            sb.append(",");
        }
        String result = sb.toString();
        if (result.endsWith(",")) {
            result = result.substring(0, result.length() - 1);
        }
        return result;
    }
    private void handleRealAmount(List<OmsOrderItem> orderItemList) {
        for (OmsOrderItem orderItem : orderItemList) {
            //原价-促销价格-优惠券抵扣-积分抵扣
            BigDecimal realAmount = orderItem.getProductPrice()
                    .subtract(orderItem.getPromotionAmount())
                    .subtract(orderItem.getCouponAmount())
                    .subtract(orderItem.getIntegrationAmount());
            orderItem.setRealAmount(realAmount);
        }
    }
    /**
     * 计算订单应付金额
     */
    private BigDecimal calcPayAmount(OmsOrder order) {
        //总金额+运费-促销优惠-优惠券优惠-积分抵扣
        BigDecimal payAmount = order.getTotalAmount()
                .add(order.getFreightAmount())
                .subtract(order.getPromotionAmount())
                .subtract(order.getCouponAmount())
                .subtract(order.getIntegrationAmount());
        return payAmount;
    }

    /**
     * 计算订单优惠券金额
     */
    private BigDecimal calcIntegrationAmount(List<OmsOrderItem> orderItemList) {
        BigDecimal integrationAmount = new BigDecimal(0);
        for (OmsOrderItem orderItem : orderItemList) {
            if (orderItem.getIntegrationAmount() != null) {
                integrationAmount = integrationAmount.add(orderItem.getIntegrationAmount().multiply(new BigDecimal(orderItem.getProductQuantity())));
            }
        }
        return integrationAmount;
    }

    /**
     * 计算订单优惠券金额
     */
    private BigDecimal calcCouponAmount(List<OmsOrderItem> orderItemList) {
        BigDecimal couponAmount = new BigDecimal(0);
        for (OmsOrderItem orderItem : orderItemList) {
            if (orderItem.getCouponAmount() != null) {
                couponAmount = couponAmount.add(orderItem.getCouponAmount().multiply(new BigDecimal(orderItem.getProductQuantity())));
            }
        }
        return couponAmount;
    }

    /**
     * 计算订单活动优惠
     */
    private BigDecimal calcPromotionAmount(List<OmsOrderItem> orderItemList) {
        BigDecimal promotionAmount = new BigDecimal(0);
        for (OmsOrderItem orderItem : orderItemList) {
            if (orderItem.getPromotionAmount() != null) {
                promotionAmount = promotionAmount.add(orderItem.getPromotionAmount().multiply(new BigDecimal(orderItem.getProductQuantity())));
            }
        }
        return promotionAmount;
    }
    /**
     * 获取可用积分抵扣金额
     *
     * @param useIntegration 使用的积分数量
     * @param totalAmount    订单总金额
     * @param currentMember  使用的用户
     * @param hasCoupon      是否已经使用优惠券
     */
    private BigDecimal getUseIntegrationAmount(Integer useIntegration, BigDecimal totalAmount, UmsMember currentMember, boolean hasCoupon) {
        BigDecimal zeroAmount = new BigDecimal(0);
        //判断用户是否有这么多积分
        if (useIntegration.compareTo(currentMember.getIntegration()) > 0) {
            return zeroAmount;
        }
        //根据积分使用规则判断使用可用
        //是否可用于优惠券共用
        UmsIntegrationConsumeSetting integrationConsumeSetting = memberFeignClient.selectIntegrationConsumeSettingById(1L);
        if (hasCoupon && integrationConsumeSetting.getCouponStatus().equals(0)) {
            //不可与优惠券共用
            return zeroAmount;
        }
        //是否达到最低使用积分门槛
        if (useIntegration.compareTo(integrationConsumeSetting.getUseUnit()) < 0) {
            return zeroAmount;
        }
        //是否超过订单抵用最高百分比
        BigDecimal integrationAmount = new BigDecimal(useIntegration).divide(new BigDecimal(integrationConsumeSetting.getUseUnit()), 2, RoundingMode.HALF_EVEN);
        BigDecimal maxPercent = new BigDecimal(integrationConsumeSetting.getMaxPercentPerOrder()).divide(new BigDecimal(100), 2, RoundingMode.HALF_EVEN);
        if (integrationAmount.compareTo(totalAmount.multiply(maxPercent)) > 0) {
            return zeroAmount;
        }
        return integrationAmount;
    }

    /**
     * 对优惠券优惠进行处理
     *
     * @param orderItemList       order_item列表
     * @param couponHistoryDetail 可用优惠券详情
     */
    private void handleCouponAmount(List<OmsOrderItem> orderItemList, SmsCouponHistoryDetail couponHistoryDetail) {
        SmsCoupon coupon = couponHistoryDetail.getCoupon();
        if (coupon.getUseType().equals(0)) {
            //全场通用
            calcPerCouponAmount(orderItemList, coupon);
        } else if (coupon.getUseType().equals(1)) {
            //指定分类
            List<OmsOrderItem> couponOrderItemList = getCouponOrderItemByRelation(couponHistoryDetail, orderItemList, 0);
            calcPerCouponAmount(couponOrderItemList, coupon);
        } else if (coupon.getUseType().equals(2)) {
            //指定商品
            List<OmsOrderItem> couponOrderItemList = getCouponOrderItemByRelation(couponHistoryDetail, orderItemList, 1);
            calcPerCouponAmount(couponOrderItemList, coupon);
        }
    }

    /**
     * 对每个下单商品进行优惠券金额分摊的计算
     *
     * @param orderItemList 可用优惠券的下单商品商品
     */
    private void calcPerCouponAmount(List<OmsOrderItem> orderItemList, SmsCoupon coupon) {
        BigDecimal totalAmount = calcTotalAmount(orderItemList);
        for (OmsOrderItem orderItem : orderItemList) {
            //(商品价格/可用商品总价)*优惠券面额
            BigDecimal couponAmount = orderItem.getProductPrice().divide(totalAmount, 3, RoundingMode.HALF_EVEN).multiply(coupon.getAmount());
            orderItem.setCouponAmount(couponAmount);
        }
    }
    /**
     * 获取与优惠券有关系的下单商品
     *
     * @param couponHistoryDetail 优惠券详情
     * @param orderItemList       下单商品
     * @param type                使用关系类型：0->相关分类；1->指定商品
     */
    private List<OmsOrderItem> getCouponOrderItemByRelation(SmsCouponHistoryDetail couponHistoryDetail, List<OmsOrderItem> orderItemList, int type) {
        List<OmsOrderItem> result = new ArrayList<>();
        if (type == 0) {
            List<Long> categoryIdList = new ArrayList<>();
            for (SmsCouponProductCategoryRelation productCategoryRelation : couponHistoryDetail.getCategoryRelationList()) {
                categoryIdList.add(productCategoryRelation.getProductCategoryId());
            }
            for (OmsOrderItem orderItem : orderItemList) {
                if (categoryIdList.contains(orderItem.getProductCategoryId())) {
                    result.add(orderItem);
                } else {
                    orderItem.setCouponAmount(new BigDecimal(0));
                }
            }
        } else if (type == 1) {
            List<Long> productIdList = new ArrayList<>();
            for (SmsCouponProductRelation productRelation : couponHistoryDetail.getProductRelationList()) {
                productIdList.add(productRelation.getProductId());
            }
            for (OmsOrderItem orderItem : orderItemList) {
                if (productIdList.contains(orderItem.getProductId())) {
                    result.add(orderItem);
                } else {
                    orderItem.setCouponAmount(new BigDecimal(0));
                }
            }
        }
        return result;
    }
    /**
     * 计算总金额
     */
    private BigDecimal calcTotalAmount(List<OmsOrderItem> orderItemList) {
        BigDecimal totalAmount = new BigDecimal("0");
        for (OmsOrderItem item : orderItemList) {
            totalAmount = totalAmount.add(item.getProductPrice().multiply(new BigDecimal(item.getProductQuantity())));
        }
        return totalAmount;
    }

    /**
     * 锁定下单商品的所有库存
     */

    public void lockStockByOrder(List<OmsOrderItem> cartPromotionItemList,String type) {
        for (OmsOrderItem item : cartPromotionItemList) {
            if (item.getType().equals(AllEnum.OrderItemType.GOODS.code())) {
                if (type!=null && "6".equals(type)){
                    SmsFlashPromotionProductRelation relation = markingFeignClinent.getFlashPromotionProductRelationById(item.getGiftIntegration().longValue());
                    if ((relation.getFlashPromotionCount() - item.getProductQuantity()) < 0) {
                        throw new ApiMallPlusException("SmsFlashPromotionProductRelation is stock out. goodsId=" + item.getProductId() + ", relation=" + relation.getId());
                    }
                    relation.setFlashPromotionCount(relation.getFlashPromotionCount() - item.getProductQuantity());
                    markingFeignClinent.updateFlashPromotionProductRelationById(relation);
                }
                PmsProduct goods = pmsFeignClinent.selectById(item.getProductId());
                if (goods != null && goods.getId() != null) {
                    if (true) {
                        redisUtil.delete(String.format(RedisToolsConstant.GOODSDETAIL, goods.getId() + ""));
                        PmsProduct newGoods = new PmsProduct();
                        newGoods.setId(goods.getId());
                        if (!ValidatorUtils.empty(item.getProductSkuId()) && item.getProductSkuId() > 0) {

                            PmsSkuStock skuStock = pmsFeignClinent.selectSkuById(item.getProductSkuId());
                            if ((skuStock.getStock() - item.getProductQuantity()) < 0) {
                                throw new BusinessException("goods is stock out. goodsId=" + item.getProductId() + ", skuId=" + item.getProductSkuId());
                            } else {
                                skuStock.setId(item.getProductSkuId());
                                skuStock.setStock(skuStock.getStock() - item.getProductQuantity());
                                pmsFeignClinent.updateSkuById(skuStock);

                                newGoods.setSale(goods.getSale() + item.getProductQuantity());
                                newGoods.setStock(goods.getStock() - item.getProductQuantity());
                                pmsFeignClinent.updateGoodsById(newGoods);
                            }
                        } else {

                            if ((goods.getStock() - item.getProductQuantity()) < 0) {
                                throw new BusinessException("goods is stock out. goodsId=" + item.getProductId() + ", goodsId=" + item.getProductSkuId());
                            } else {
                                newGoods.setStock(goods.getStock() - item.getProductQuantity());
                                newGoods.setSale(goods.getSale() + item.getProductQuantity());
                                pmsFeignClinent.updateGoodsById(newGoods);
                            }
                        }
                    }
                }
            }else {
                PmsGifts goods = pmsFeignClinent.getGiftById(item.getProductId());
                if (goods != null && goods.getId() != null) {
                    PmsGifts newGoods = new PmsGifts();
                    newGoods.setId(goods.getId());
                    if ((goods.getStock() - item.getProductQuantity()) < 0) {
                        throw new ApiMallPlusException("goods is stock out. goodsId=" + item.getProductId() + ", goodsId=" + item.getProductSkuId());
                    }
                    newGoods.setStock(goods.getStock() - item.getProductQuantity());
                    pmsFeignClinent.updateGiftById(newGoods);
                }
            }
        }
    }

    @Override
    public void releaseStock(OmsOrder order){
        List<OmsOrderItem> itemList = orderItemService.list(new QueryWrapper<OmsOrderItem>().eq("order_id", order.getId()));
        if (itemList != null && itemList.size() > 0) {
            for (OmsOrderItem item : itemList) {
                if (item.getType().equals(AllEnum.OrderItemType.GOODS.code())) {
                    if ("6".equals(order.getOrderType())) {
                        SmsFlashPromotionProductRelation relation = markingFeignClinent.getFlashPromotionProductRelationById(item.getGiftIntegration().longValue());
                        relation.setFlashPromotionCount(relation.getFlashPromotionCount() + item.getProductQuantity());
                        markingFeignClinent.updateFlashPromotionProductRelationById(relation);
                    }
                    PmsProduct goods = pmsFeignClinent.selectById(item.getProductId());
                    if (goods != null && goods.getId() != null) {
                        redisUtil.delete(String.format(RedisToolsConstant.GOODSDETAIL, goods.getId() + ""));
                        goods.setStock(goods.getStock() + item.getProductQuantity());
                        goods.setSale(goods.getSale() - item.getProductQuantity());
                        pmsFeignClinent.updateGoodsById(goods);
                        if (!ValidatorUtils.empty(item.getProductSkuId()) && item.getProductSkuId() > 0) {
                            PmsSkuStock skuStock = new PmsSkuStock();
                            skuStock.setId(item.getProductSkuId());
                            skuStock.setStock(skuStock.getStock() + item.getProductQuantity());
                            skuStock.setSale(skuStock.getSale() - item.getProductQuantity());
                            pmsFeignClinent.updateSkuById(skuStock);
                        }
                    }
                } else {
                    PmsGifts goods = pmsFeignClinent.getGiftById(item.getProductId());
                    if (goods != null && goods.getId() != null) {
                        PmsGifts newGoods = new PmsGifts();
                        newGoods.setId(goods.getId());
                        if ((goods.getStock() + item.getProductQuantity()) < 0) {
                            throw new ApiMallPlusException("goods is stock out. goodsId=" + item.getProductId() + ", goodsId=" + item.getProductSkuId());
                        }
                        newGoods.setStock(goods.getStock() + item.getProductQuantity());
                        pmsFeignClinent.updateGiftById(newGoods);
                    }
                }
            }
        }
    }
    /**
     * 判断下单商品是否都有库存
     */
    private boolean hasStock(List<CartPromotionItem> cartPromotionItemList) {
        for (CartPromotionItem cartPromotionItem : cartPromotionItemList) {
            if (cartPromotionItem.getRealStock() <= 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 计算该订单赠送的成长值
     */
    private Integer calcGiftGrowth(List<OmsOrderItem> orderItemList) {
        Integer sum = 0;
        for (OmsOrderItem orderItem : orderItemList) {
            sum = sum + orderItem.getGiftGrowth() * orderItem.getProductQuantity();
        }
        return sum;
    }
    /**
     * 删除下单商品的购物车信息
     */
    private void deleteCartItemList(List<OmsCartItem> cartPromotionItemList, UmsMember currentMember) {
        List<Long> ids = new ArrayList<>();
        for (OmsCartItem cartPromotionItem : cartPromotionItemList) {
            ids.add(cartPromotionItem.getId());
        }
        cartItemService.delete(currentMember.getId(), ids);
    }
    /**
     * 计算该订单赠送的积分
     */
    private Integer calcGifIntegration(List<OmsOrderItem> orderItemList) {
       return 0;
    }
    @Override
    public ConfirmOrderResult addGroup(OrderParam orderParam) {
        List<OmsCartItem> list = new ArrayList<>();
        if (ValidatorUtils.empty(orderParam.getTotal())) {
            orderParam.setTotal(1);
        }
        OmsCartItem cartItem = new OmsCartItem();
        PmsProduct pmsProduct = pmsFeignClinent.selectById(orderParam.getGoodsId());
        createCartObj(orderParam, list, cartItem, pmsProduct);
        ConfirmOrderResult result = new ConfirmOrderResult();
        //获取购物车信息

        result.setCartPromotionItemList(list);
        //获取用户收货地址列表
        UmsMemberReceiveAddress queryU = new UmsMemberReceiveAddress();
        queryU.setMemberId(orderParam.getMemberId());
        List<UmsMemberReceiveAddress> memberReceiveAddressList = addressService.list(new QueryWrapper<>(queryU));
        result.setMemberReceiveAddressList(memberReceiveAddressList);
        UmsMemberReceiveAddress address = addressService.getDefaultItem(orderParam.getMemberId());

        UmsMember member = memberFeignClient.findById(orderParam.getMemberId());
        //获取用户积分
        result.setMemberIntegration(member.getIntegration());
       /* //获取用户可用优惠券列表
        List<SmsCouponHistoryDetail> couponHistoryDetailList = couponService.listCart(list, 1);
        result.setCouponHistoryDetailList(couponHistoryDetailList);
        //获取积分使用规则
        UmsIntegrationConsumeSetting integrationConsumeSetting = integrationConsumeSettingMapper.selectById(1L);
        result.setIntegrationConsumeSetting(integrationConsumeSetting);*/
        //计算总金额、活动优惠、应付金额
        ConfirmOrderResult.CalcAmount calcAmount = calcCartAmount(list, BigDecimal.ZERO, BigDecimal.ZERO);
        result.setCalcAmount(calcAmount);
        result.setAddress(address);

        return result;
    }


    @Override
    public CommonResult acceptGroup(OrderParam orderParam) {


        UmsMember currentMember = memberFeignClient.findById(orderParam.getMemberId());
        List<OmsCartItem> list = new ArrayList<>();
        if (ValidatorUtils.empty(orderParam.getTotal())) {
            orderParam.setTotal(1);
        }
        OmsCartItem cartItem = new OmsCartItem();
        PmsProduct pmsProduct = pmsFeignClinent.selectById(orderParam.getGoodsId());
        createCartObj(orderParam, list, cartItem, pmsProduct);


        List<OmsOrderItem> orderItemList = new ArrayList<>();
        //获取购物车及优惠信息
        String name = "";

        for (OmsCartItem cartPromotionItem : list) {
            PmsProduct goods = pmsFeignClinent.selectById(cartPromotionItem.getProductId());
            if (!ValidatorUtils.empty(cartPromotionItem.getProductSkuId()) && cartPromotionItem.getProductSkuId() > 0) {
                checkGoods(goods, false, cartPromotionItem.getQuantity());
                PmsSkuStock skuStock = pmsFeignClinent.selectSkuById(cartPromotionItem.getProductSkuId());
                checkSkuGoods(skuStock, cartPromotionItem.getQuantity());
            } else {
                checkGoods(goods, true, cartPromotionItem.getQuantity());
            }
            //生成下单商品信息
            OmsOrderItem orderItem = createOrderItem(cartPromotionItem);
            orderItemList.add(orderItem);
        }

        //进行库存锁定
        lockStockByOrder(orderItemList,"1");
        //根据商品合计、运费、活动优惠、优惠券、积分计算应付金额
        UmsMemberReceiveAddress address = addressService.getById(orderParam.getAddressId());

        OmsOrder order =new OmsOrder();
        createOrderObj(order,orderParam, currentMember, orderItemList, address);

        order.setMemberId(orderParam.getMemberId());

        // TODO: 2018/9/3 bill_*,delivery_*
        //插入order表和order_item表
        orderService.save(order);
        orderParam.setOrderId(order.getId());
        orderParam.setMemberIcon(currentMember.getIcon());
        markingFeignClinent.acceptGroup(orderParam);
        for (OmsOrderItem orderItem : orderItemList) {
            orderItem.setOrderId(order.getId());
            orderItem.setOrderSn(order.getOrderSn());
        }
        orderItemService.saveBatch(orderItemList);


        Map<String, Object> result = new HashMap<>();
        result.put("order", order);
        result.put("orderItemList", orderItemList);

        String platform = orderParam.getPlatform();

        return new CommonResult().success("下单成功", result);
    }

    private void createCartObj(OrderParam orderParam, List<OmsCartItem> list, OmsCartItem cartItem, PmsProduct pmsProduct) {
        if (ValidatorUtils.notEmpty(orderParam.getSkuId())) {
            PmsSkuStock pmsSkuStock = pmsFeignClinent.selectSkuById(orderParam.getSkuId());
            checkGoods(pmsProduct, false, 1);
            checkSkuGoods(pmsSkuStock, 1);
            cartItem.setProductId(pmsSkuStock.getProductId());
            cartItem.setMemberId(orderParam.getMemberId());
            cartItem.setProductSkuId(pmsSkuStock.getId());
           // cartItem.setChecked(1);
            cartItem.setPrice(pmsSkuStock.getPrice());
            cartItem.setProductSkuCode(pmsSkuStock.getSkuCode());
            cartItem.setQuantity(orderParam.getTotal());
            cartItem.setProductAttr(pmsSkuStock.getMeno());
            cartItem.setProductPic(pmsSkuStock.getPic());
            cartItem.setSp1(pmsSkuStock.getSp1());
            cartItem.setSp2(pmsSkuStock.getSp2());
            cartItem.setSp3(pmsSkuStock.getSp3());
            cartItem.setProductName(pmsSkuStock.getProductName());
            cartItem.setProductCategoryId(pmsProduct.getProductCategoryId());
            cartItem.setProductBrand(pmsProduct.getBrandName());
            cartItem.setCreateDate(new Date());

        } else {
            checkGoods(pmsProduct, true, orderParam.getTotal());
            cartItem.setProductId(orderParam.getGoodsId());
            cartItem.setMemberId(orderParam.getMemberId());
          //  cartItem.setChecked(1);
            cartItem.setPrice(pmsProduct.getPrice());
            cartItem.setProductName(pmsProduct.getName());
            cartItem.setQuantity(orderParam.getTotal());
            cartItem.setProductPic(pmsProduct.getPic());
            cartItem.setCreateDate(new Date());
            cartItem.setMemberId(orderParam.getMemberId());
            cartItem.setProductCategoryId(pmsProduct.getProductCategoryId());
            cartItem.setProductBrand(pmsProduct.getBrandName());

        }
        list.add(cartItem);
    }

    private OmsOrderItem createOrderItem(OmsCartItem cartPromotionItem) {
        OmsOrderItem orderItem = new OmsOrderItem();
        orderItem.setProductAttr(cartPromotionItem.getProductAttr());
        orderItem.setProductId(cartPromotionItem.getProductId());
        orderItem.setProductName(cartPromotionItem.getProductName());
        orderItem.setProductPic(cartPromotionItem.getProductPic());
        orderItem.setProductAttr(cartPromotionItem.getProductAttr());
        orderItem.setProductBrand(cartPromotionItem.getProductBrand());
        orderItem.setProductSn(cartPromotionItem.getProductSn());
        orderItem.setProductPrice(cartPromotionItem.getPrice());
        orderItem.setProductQuantity(cartPromotionItem.getQuantity());
        orderItem.setProductSkuId(cartPromotionItem.getProductSkuId());
        orderItem.setProductSkuCode(cartPromotionItem.getProductSkuCode());
        orderItem.setProductCategoryId(cartPromotionItem.getProductCategoryId());
           /* orderItem.setPromotionAmount(cartPromotionItem.getReduceAmount());
            orderItem.setPromotionName(cartPromotionItem.getPromotionMessage());
            orderItem.setGiftIntegration(cartPromotionItem.getIntegration());
            orderItem.setGiftGrowth(cartPromotionItem.getGrowth());*/
        return orderItem;
    }

    private OmsOrder createOrderObj(OmsOrder order, OrderParam orderParam, UmsMember currentMember, List<OmsOrderItem> orderItemList, UmsMemberReceiveAddress address) {
        order.setIsComment(1);
        order.setTaxType(1);
        order.setDiscountAmount(new BigDecimal(0));
        order.setTotalAmount(calcTotalAmount(orderItemList));

        if (ValidatorUtils.notEmpty(orderParam.getGroupId())) {
            order.setGroupId(orderParam.getGroupId());
        }
        if (orderParam.getUseIntegration() == null) {
            order.setIntegration(0);
            order.setIntegrationAmount(new BigDecimal(0));
        } else {
            order.setIntegration(orderParam.getUseIntegration());
            order.setIntegrationAmount(calcIntegrationAmount(orderItemList));
        }
        //转化为订单信息并插入数据库
        order.setCreateTime(new Date());
        order.setMemberUsername(currentMember.getUsername());
        order.setMemberId(currentMember.getId());
        //支付方式：0->未支付；1->支付宝；2->微信
        order.setPayType(orderParam.getPayType());
        //订单来源：0->PC订单；5->app订单 2 h5 3微信小程序 4 支付宝小程序
        order.setSourceType(orderParam.getSource());
        //订单状态：订单状态：1->待付款；2->待发货；3->已发货；4->已完成；5->售后订单 6->已关闭；
        order.setStatus(OrderStatus.INIT.getValue());
        //订单类型：0->正常订单；1->秒杀订单
        order.setOrderType(orderParam.getOrderType());
        //收货人信息：姓名、电话、邮编、地址
        if (address != null) {
            order.setReceiverId(address.getId());
            order.setReceiverName(address.getName());
            order.setReceiverPhone(address.getPhoneNumber());
            order.setReceiverPostCode(address.getPostCode());
            order.setReceiverProvince(address.getProvince());
            order.setReceiverCity(address.getCity());
            order.setReceiverRegion(address.getRegion());
            order.setReceiverDetailAddress(address.getDetailAddress());
        }
        //0->未确认；1->已确认
        order.setConfirmStatus(0);
        order.setDeleteStatus(0);
        order.setMemberId(currentMember.getId());
        //生成订单号
        order.setOrderSn(generateOrderSn(order));
        return order;
    }
    @Override
    @Transactional
    public boolean closeOrder(OmsOrder order){
        releaseStock(order);
        order.setStatus(OrderStatus.CLOSED.getValue());
        return orderMapper.updateById(order) > 0;
    }


    @Override
    @Transactional
    public  Object jifenPay(OrderParam orderParam){
        UmsMember member= memberFeignClient.findById(orderParam.getMemberId());
        PmsGifts gifts = pmsFeignClinent.getGiftById(orderParam.getGoodsId());
        if(gifts.getPrice().intValue()>member.getIntegration()){
            return new CommonResult().failed("积分不足！");
        }else {
            // UmsMemberReceiveAddress address = addressService.getById(orderParam.getAddressId());

            OmsOrderItem orderItem = new OmsOrderItem();
            orderItem.setProductId(orderParam.getGoodsId());
            orderItem.setProductName(gifts.getTitle());
            orderItem.setProductPic(gifts.getIcon());
            orderItem.setProductPrice(gifts.getPrice());
            orderItem.setProductQuantity(1);
            orderItem.setProductCategoryId(gifts.getCategoryId());
            List<OmsOrderItem> omsOrderItemList = new ArrayList<>();
            omsOrderItemList.add(orderItem);
            OmsOrder order = new OmsOrder();
                    createOrderObj(order,orderParam, member, omsOrderItemList, null);
            order.setOrderType(2);
            order.setStatus(OrderStatus.TO_DELIVER.getValue());
            order.setPayType(3);
            orderService.save(order);
            orderItem.setOrderId(order.getId());
            orderItemService.save(orderItem);
            member.setIntegration(member.getIntegration()-gifts.getPrice().intValue());
            memberFeignClient.updateMember(member);

        }
        return new CommonResult().success("兑换成功");
    }
    @Override
    public Object addCart(CartParam cartParam) {
        if (ValidatorUtils.empty(cartParam.getTotal())) {
            cartParam.setTotal(1);
        }
        UmsMember umsMember = memberFeignClient.findById(cartParam.getMemberId());
        OmsCartItem cartItem = new OmsCartItem();
        PmsProduct pmsProduct = pmsFeignClinent.selectById(cartParam.getGoodsId());
        if (ValidatorUtils.notEmpty(cartParam.getSkuId())){

            PmsSkuStock pmsSkuStock = pmsFeignClinent.selectSkuById(cartParam.getSkuId());
            checkGoods(pmsProduct, false, cartParam.getTotal());
            checkSkuGoods(pmsSkuStock, cartParam.getTotal());
            cartItem.setProductId(pmsSkuStock.getProductId());
            cartItem.setMemberId(cartParam.getMemberId());
            cartItem.setProductSkuId(pmsSkuStock.getId());
            OmsCartItem existCartItem   = cartItemMapper.selectOne(new QueryWrapper<>(cartItem));
            if (existCartItem == null) {
                cartItem.setPrice(pmsSkuStock.getPrice());
                cartItem.setProductSkuCode(pmsSkuStock.getSkuCode());
                cartItem.setQuantity(cartParam.getTotal());
                cartItem.setProductAttr(pmsSkuStock.getMeno1());
                cartItem.setMemberId(umsMember.getId());
                cartItem.setProductPic(pmsSkuStock.getPic());
                cartItem.setSp1(pmsSkuStock.getSp1());
                cartItem.setSp2(pmsSkuStock.getSp2());
                cartItem.setSp3(pmsSkuStock.getSp3());
                cartItem.setProductName(pmsSkuStock.getProductName());
                cartItem.setCreateDate(new Date());
                cartItemMapper.insert(cartItem);
            } else {
                cartItem.setModifyDate(new Date());
                existCartItem.setQuantity(existCartItem.getQuantity() + cartParam.getTotal());
                cartItemMapper.updateById(existCartItem);
                return new CommonResult().success(existCartItem);
            }
        }else {

            checkGoods(pmsProduct, true, cartParam.getTotal());
            cartItem.setProductId(pmsProduct.getId());
            cartItem.setMemberId(cartParam.getMemberId());
            OmsCartItem existCartItem   = cartItemMapper.selectOne(new QueryWrapper<>(cartItem));
            if (existCartItem == null) {
                cartItem.setPrice(pmsProduct.getPrice());
                cartItem.setProductName(pmsProduct.getName());
                cartItem.setQuantity(cartParam.getTotal());
                cartItem.setProductPic(pmsProduct.getPic());
                cartItem.setMemberId(umsMember.getId());
                cartItem.setCreateDate(new Date());
                cartItemMapper.insert(cartItem);
            } else {
                cartItem.setModifyDate(new Date());
                existCartItem.setQuantity(existCartItem.getQuantity() + cartParam.getTotal());
                cartItemMapper.updateById(existCartItem);
                return new CommonResult().success(existCartItem);
            }
        }


        return new CommonResult().success(cartItem);
    }
    private void checkGoods(PmsProduct goods, boolean falg, int count) {
        if (goods == null || goods.getId() == null) {
            throw new ApiMallPlusException("商品已删除");
        }
        if (falg && (goods.getStock() <= 0 || goods.getStock() < count)) {
            throw new ApiMallPlusException("库存不足!");
        }
    }

    private void checkSkuGoods(PmsSkuStock goods, int count) {
        if (goods == null || goods.getId() == null) {
            throw new ApiMallPlusException("商品已删除");
        }
        if (goods.getStock() <= 0 || goods.getStock() < count) {
            throw new ApiMallPlusException("库存不足!");
        }
    }
    /**
     * 生成18位订单编号:8位日期+2位平台号码+2位支付方式+6位以上自增id
     */
    private String generateOrderSn(OmsOrder order) {
        StringBuilder sb = new StringBuilder();
        String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
        sb.append(date);
        sb.append(String.format("%02d", order.getSourceType()));
        sb.append(String.format("%02d", order.getPayType()));
        sb.append(order.getMemberId());
        return sb.toString();
    }
    /**
     * 推送消息
     */
    public void push(GroupAndOrderVo umsMember, OmsOrder order, String page, String formId) {
        log.info("发送模版消息：userId=" + umsMember.getMemberId() + ",orderId=" + order.getId() + ",formId=" + formId);
        if (StringUtils.isEmpty(formId)) {
            log.error("发送模版消息：userId=" + umsMember.getMemberId() + ",orderId=" + order.getId() + ",formId=" + formId);
        }
        String accessToken = null;
        try {
            accessToken = wechatApiService.getAccessToken();

            String templateId = wxAppletProperties.getTemplateId();
            Map<String, TemplateData> param = new HashMap<String, TemplateData>();
            param.put("keyword1", new TemplateData(DateUtils.format(order.getCreateTime(), "yyyy-MM-dd"), "#EE0000"));

            param.put("keyword2", new TemplateData(order.getGoodsName(), "#EE0000"));
            param.put("keyword3", new TemplateData(order.getOrderSn(), "#EE0000"));
            param.put("keyword3", new TemplateData(order.getPayAmount() + "", "#EE0000"));

            JSON jsonObject = JSONUtil.parseObj(param);
            //调用发送微信消息给用户的接口    ********这里写自己在微信公众平台拿到的模板ID
            WX_TemplateMsgUtil.sendWechatMsgToUser(umsMember.getWxid(), templateId, page + "?id=" + order.getId(),
                    formId, jsonObject, accessToken);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * @return
     */
    @Override
    public ConfirmOrderResult submitPreview(OrderParam orderParam) {
        if (ValidatorUtils.empty(orderParam.getTotal())) {
            orderParam.setTotal(1);
        }
        String type = orderParam.getType();
        StopWatch stopWatch = new StopWatch("下单orderType=" + orderParam.getOrderType());
        stopWatch.start("1. 获取购物车商品");
        UmsMember currentMember = memberFeignClient.findById(orderParam.getMemberId());
        List<OmsCartItem> list = new ArrayList<>();
        if ("3".equals(type)) { // 1 商品详情 2 勾选购物车 3全部购物车的商品
            list = cartItemService.list(currentMember.getId(), null);
        }else if ("1".equals(type)) {
            String cartId = orderParam.getCartId();
            if (org.apache.commons.lang.StringUtils.isBlank(cartId)) {
                throw new ApiMallPlusException("参数为空");
            }
            OmsCartItem omsCartItem = cartItemService.selectById(Long.valueOf(cartId));
            if (omsCartItem == null) {
                return null;
            }
            list.add(omsCartItem);
        } else if ("2".equals(type)) {
            String cart_id_list1 = orderParam.getCartIds();
            if (org.apache.commons.lang.StringUtils.isBlank(cart_id_list1)) {
                throw new ApiMallPlusException("参数为空");
            }
            String[] ids1 = cart_id_list1.split(",");
            List<Long> resultList = new ArrayList<>(ids1.length);
            for (String s : ids1) {
                resultList.add(Long.valueOf(s));
            }
            list = cartItemService.list(currentMember.getId(), resultList);
        }else if ("6".equals(type)) { // 秒杀
            SmsFlashPromotionProductRelation relation = markingFeignClinent.getFlashPromotionProductRelationById(orderParam.getSkillId());
            PmsProduct product = pmsFeignClinent.selectById(relation.getProductId());
            OmsCartItem omsCartItem = new OmsCartItem();
            omsCartItem.setQuantity(orderParam.getTotal());
            if (orderParam.getTotal()>relation.getFlashPromotionLimit()){
                throw new ApiMallPlusException("超过秒杀个数！");
            }
            omsCartItem.setPrice(relation.getFlashPromotionPrice());
            omsCartItem.setProductBrand(product.getBrandId()+"");
            omsCartItem.setProductCategoryId(product.getProductCategoryId());
            omsCartItem.setProductName(product.getName());
            omsCartItem.setProductPic(product.getPic());
            omsCartItem.setProductId(product.getId());
            omsCartItem.setProductSn(product.getProductSn());

            list.add(omsCartItem);
        }
        if (list == null && list.size() < 1) {
            throw new ApiMallPlusException("订单已提交");
        }
        List<OmsCartItem> newCartList = new ArrayList<>();
        // 取商品大的运费
        BigDecimal transFee = BigDecimal.ZERO;
        for (OmsCartItem cart : list) {
            PmsProduct goods = pmsFeignClinent.selectById(cart.getProductId());
            if (goods != null && goods.getStock() > 0 && goods.getStock() >= cart.getQuantity()) {
                if (goods.getTransfee().compareTo(transFee) > 0) {
                    transFee = goods.getTransfee();
                }
            }
            newCartList.add(cart);
        }
        stopWatch.stop();
        stopWatch.start("赠品营销计算");
        ConfirmOrderResult result = new ConfirmOrderResult();
        //获取购物车信息
        CartMarkingVo vo = new CartMarkingVo();
        vo.setCartList(newCartList);
        //是否首单
        int firstOrder = orderMapper.selectCount(new QueryWrapper<OmsOrder>().eq("member_id", currentMember.getId()));
        vo.setType(1);
        if (firstOrder > 0) {
            vo.setType(2);
        }

        List<SmsBasicGifts> basicGiftsList = markingFeignClinent.matchOrderBasicGifts(vo);
        log.info(com.alibaba.fastjson.JSONObject.toJSONString(basicGiftsList));
        result.setBasicGiftsList(basicGiftsList);
        stopWatch.stop();
        stopWatch.start("其他数据计算");
        result.setCartPromotionItemList(newCartList);
        //获取用户收货地址列表
        UmsMemberReceiveAddress queryU = new UmsMemberReceiveAddress();
        queryU.setMemberId(currentMember.getId());
        List<UmsMemberReceiveAddress> memberReceiveAddressList = addressService.list(new QueryWrapper<>(queryU));
        result.setMemberReceiveAddressList(memberReceiveAddressList);
        UmsMemberReceiveAddress address = addressService.getDefaultItem(currentMember.getId());
        //获取用户可用优惠券列表
        vo.setMemberId(currentMember.getId());
        List<SmsCouponHistoryDetail> couponHistoryDetailList = markingFeignClinent.listCart(vo);
        result.setCouponHistoryDetailList(couponHistoryDetailList);
        //获取用户积分
        result.setMemberIntegration(currentMember.getIntegration());
        //获取积分使用规则
        UmsIntegrationConsumeSetting integrationConsumeSetting = memberFeignClient.selectIntegrationConsumeSettingById(1L);
        result.setIntegrationConsumeSetting(integrationConsumeSetting);
        //计算总金额、活动优惠、应付金额
        if (list != null && list.size() > 0) {
            ConfirmOrderResult.CalcAmount calcAmount = calcCartAmount(newCartList, transFee, BigDecimal.ZERO);
            result.setCalcAmount(calcAmount);
            result.setAddress(address);
            return result;
        }
        stopWatch.stop();
        log.info(stopWatch.prettyPrint());
        return null;

    }
    @Override
    public CommonResult generateOrder(OrderParam orderParam) {
        if (ValidatorUtils.empty(orderParam.getTotal())) {
            orderParam.setTotal(1);
        }
        UmsMember member= memberFeignClient.findById(orderParam.getMemberId());
        String type = orderParam.getType();
        StopWatch stopWatch = new StopWatch("下单orderType=" + orderParam.getOrderType());
        stopWatch.start("1. 获取购物车商品");
        List<OmsCartItem> cartPromotionItemList = new ArrayList<>();
        OmsOrder order = new OmsOrder();
        //团购订单
        if (orderParam.getOrderType() == 3) {
            SmsGroupActivity smsGroupActivity = markingFeignClinent.getSmsGroupActivityById(orderParam.getGroupActivityId());
            if (ValidatorUtils.notEmpty(smsGroupActivity.getGoodsIds())) {
                List<PmsProduct> productList = (List<PmsProduct>) pmsFeignClinent.listGoodsByIds(
                        Arrays.asList(smsGroupActivity.getGoodsIds().split(",")).stream().map(s -> Long.parseLong(s.trim())).collect(Collectors.toList()));
                if (productList != null && productList.size() > 0) {
                    order.setFreightAmount(smsGroupActivity.getTransfee());
                    //获取购物车信息
                    cartPromotionItemList = goodsToCartList(productList);
                }
            }
        } else {
            if ("3".equals(type)) { // 1 商品详情 2 勾选购物车 3全部购物车的商品
                cartPromotionItemList = cartItemService.list(orderParam.getMemberId(), null);
            }
            if ("1".equals(type)) {
                Long cartId = Long.valueOf(orderParam.getCartId());
                OmsCartItem omsCartItem = cartItemService.selectById(cartId);
                if (omsCartItem != null) {
                    cartPromotionItemList.add(omsCartItem);
                } else {
                    throw new ApiMallPlusException("订单已提交");
                }

            } else if ("2".equals(type)) {
                String cart_id_list1 = orderParam.getCartIds();
                String[] ids1 = cart_id_list1.split(",");
                List<Long> resultList = new ArrayList<>(ids1.length);
                for (String s : ids1) {
                    resultList.add(Long.valueOf(s));
                }
                cartPromotionItemList = cartItemService.list(orderParam.getMemberId(), resultList);
            }else if ("6".equals(type)) { // 秒杀
                SmsFlashPromotionProductRelation relation = markingFeignClinent.getFlashPromotionProductRelationById(orderParam.getSkillId());
                PmsProduct product = pmsFeignClinent.selectById(relation.getProductId());
                OmsCartItem omsCartItem = new OmsCartItem();
                omsCartItem.setQuantity(orderParam.getTotal());
                if (orderParam.getTotal()>relation.getFlashPromotionLimit()){
                    throw new ApiMallPlusException("超过秒杀个数！");
                }
                omsCartItem.setPrice(relation.getFlashPromotionPrice());
                omsCartItem.setProductBrand(product.getBrandId()+"");
                omsCartItem.setProductCategoryId(product.getProductCategoryId());
                omsCartItem.setProductName(product.getName());
                omsCartItem.setProductPic(product.getPic());
                omsCartItem.setProductId(product.getId());
                omsCartItem.setProductSn(product.getProductSn());

                cartPromotionItemList.add(omsCartItem);
            }
        }
        if (cartPromotionItemList == null || cartPromotionItemList.size() < 1) {
            return new CommonResult().failed("没有下单的商品");
        }
        stopWatch.stop();
        List<OmsOrderItem> orderItemList = new ArrayList<>();
        //获取购物车及优惠信息
        String name = "";
        BigDecimal transFee = BigDecimal.ZERO;
        List<OmsCartItem> newCartItemList = new ArrayList<>();
        Integer isFirst = 1;
        stopWatch.start("2. 校验商品库存，舍弃商品不存或没有库存 计算运费");
        //根据商品合计、运费、活动优惠、优惠券、积分计算应付金额

        for (OmsCartItem cartPromotionItem : cartPromotionItemList) {
            boolean flag = false;
            PmsProduct goods = pmsFeignClinent.selectById(cartPromotionItem.getProductId());
            if (!ValidatorUtils.empty(cartPromotionItem.getProductSkuId()) && cartPromotionItem.getProductSkuId() > 0) {
                checkGoods(goods, false, cartPromotionItem.getQuantity());
                PmsSkuStock skuStock = pmsFeignClinent.selectSkuById(cartPromotionItem.getProductSkuId());
                if (skuStock.getStock() > 0 && skuStock.getStock() >= cartPromotionItem.getQuantity()) {
                    flag = true;
                }
            } else {
                if (goods != null && goods.getId() != null && goods.getStock() > 0 && goods.getStock() >= cartPromotionItem.getQuantity()) {
                    flag = true;
                }
            }
            if (flag) {
                if (goods.getTransfee().compareTo(transFee) > 0) {
                    transFee = goods.getTransfee();
                }
                //生成下单商品信息
                OmsOrderItem orderItem = createOrderItem(cartPromotionItem);
                orderItem.setType(AllEnum.OrderItemType.GOODS.code());
                orderItemList.add(orderItem);
                if (isFirst==1){
                    name = cartPromotionItem.getProductName();
                    order.setGoodsId(cartPromotionItem.getProductId());
                    order.setGoodsName(cartPromotionItem.getProductName());
                }
                newCartItemList.add(cartPromotionItem);
            }
        }
        if (cartPromotionItemList == null || cartPromotionItemList.size() < 1) {
            return new CommonResult().failed("没有下单的商品");
        }

        //3.计算优惠券
        SmsCouponHistory couponHistory = null;
        SmsCoupon coupon = null;
        if (orderParam.getCouponId() != null) {
            couponHistory = markingFeignClinent.getcouponHistoryById(orderParam.getMemberCouponId());
            coupon = markingFeignClinent.getCouponById(orderParam.getCouponId());
        }
        //判断是否使用积分
        if (orderParam.getUseIntegration() == null) {
            //不使用积分
            for (OmsOrderItem orderItem : orderItemList) {
                orderItem.setIntegrationAmount(new BigDecimal(0));
            }
        } else {
            //使用积分
            BigDecimal totalAmount = calcTotalAmount(orderItemList);
            BigDecimal integrationAmount = getUseIntegrationAmount(orderParam.getUseIntegration(), totalAmount, member, orderParam.getCouponId() != null);
            if (integrationAmount.compareTo(new BigDecimal(0)) == 0) {
                return new CommonResult().failed("积分不可用");
            } else {
                //可用情况下分摊到可用商品中
                for (OmsOrderItem orderItem : orderItemList) {
                    BigDecimal perAmount = orderItem.getProductPrice().divide(totalAmount, 3, RoundingMode.HALF_EVEN).multiply(integrationAmount);
                    orderItem.setIntegrationAmount(perAmount);
                }
            }
        }
        //计算order_item的实付金额

        //进行库存锁定
        lockStockByOrder(orderItemList,type);
        //根据商品合计、运费、活动优惠、优惠券、积分计算应付金额
        UmsMemberReceiveAddress address = addressService.getById(orderParam.getAddressId());
        createOrderObj(order, orderParam, member, orderItemList, address);

        if (orderParam.getOrderType() != 3) {
            order.setFreightAmount(transFee);
        }
        if (orderParam.getCouponId() == null) {
            order.setCouponAmount(new BigDecimal(0));
        } else {
            order.setCouponId(orderParam.getCouponId());
            order.setCouponAmount(coupon.getAmount());
        }
        CartMarkingVo vo = new CartMarkingVo();
        vo.setCartList(newCartItemList);
        SmsBasicMarking basicMarking = markingFeignClinent.matchOrderBasicMarking(vo);
        log.info("basicMarking="+ com.alibaba.fastjson.JSONObject.toJSONString(basicMarking));
        if (basicMarking!=null){
            order.setPromotionAmount(basicMarking.getMinAmount());
        }else {
            order.setPromotionAmount(BigDecimal.ZERO);
        }
        order.setPayAmount(calcPayAmount(order));
        if (order.getPayAmount().compareTo(BigDecimal.ZERO)<0){
            order.setPayAmount(new BigDecimal("0.01"));
        }
        stopWatch.stop();

        stopWatch.start("3.计算赠品营销");
        if (ValidatorUtils.notEmpty(orderParam.getBasicGiftsVar())){
            String[] basicGiftsList = orderParam.getBasicGiftsVar().split("@");
            if (basicGiftsList != null && basicGiftsList.length > 0) {
                for (String basicGifts : basicGiftsList) {
                    if (ValidatorUtils.notEmpty(basicGifts)) {
                        String[] beanKv = basicGifts.split(":");
                        if (beanKv != null && beanKv.length > 1) {
                            String[] ids = beanKv[1].split(",");
                            if (ids != null && ids.length > 0) {
                                for (String id : ids) {
                                    PmsGifts pmsGifts = pmsFeignClinent.getGiftById(Long.parseLong(id));
                                    if (pmsGifts != null) {
                                        OmsOrderItem orderItem = new OmsOrderItem();
                                        orderItem.setOrderSn(beanKv[0]);
                                        orderItem.setProductId(pmsGifts.getId());
                                        orderItem.setProductName(pmsGifts.getTitle());
                                        orderItem.setProductPic(pmsGifts.getIcon());
                                        orderItem.setProductPrice(pmsGifts.getPrice());
                                        orderItem.setProductQuantity(1);
                                        orderItem.setType(AllEnum.OrderItemType.GIFT.code());

                                        orderItemList.add(orderItem);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        stopWatch.stop();
        stopWatch.start("4.计算优惠券 插入订单");
        //插入order表和order_item表
        orderService.save(order);
        for (OmsOrderItem orderItem : orderItemList) {
            orderItem.setOrderId(order.getId());
            orderItem.setOrderSn(order.getOrderSn());
        }
        orderItemService.saveBatch(orderItemList);
        //如使用优惠券更新优惠券使用状态
        if (orderParam.getCouponId() != null) {
            updateCouponStatus(orderParam.getCouponId(), member.getId(), 1);
        }
        //如使用积分需要扣除积分
        if (orderParam.getUseIntegration() != null) {
            order.setUseIntegration(orderParam.getUseIntegration());
         //   memberService.updateIntegration(currentMember.getId(), currentMember.getIntegration() - orderParam.getUseIntegration());
        }
        //删除购物车中的下单商品
        deleteCartItemList(cartPromotionItemList, member);
        Map<String, Object> result = new HashMap<>();
        result.put("order", order);
        result.put("orderItemList", orderItemList);

        String platform = orderParam.getPlatform();
        if ("1".equals(platform)) {
        //    push(currentMember, order, orderParam.getPage(), orderParam.getFormId(), name);
        }
        return new CommonResult().success("下单成功", result);
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Object confimDelivery(Long id) {
        OmsOrder order = this.orderMapper.selectById(id);
        if (order.getStatus() != OrderStatus.DELIVERED.getValue()) {
            return new CommonResult().paramFailed("已发货订单才能确认收货");
        }
        OmsOrderOperateHistory history = updateOrderInfo(id, order, OrderStatus.TO_COMMENT);
        history.setOrderStatus(OrderStatus.TO_COMMENT.getValue());
        history.setNote("确认收货");
        orderOperateHistoryService.save(history);

        return new CommonResult().success();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Object applyRefund(Long id){
        OmsOrder order = this.orderMapper.selectById(id);
        try {
            if (order.getStatus() >9) {
                return new CommonResult().paramFailed("已支付的订单才能申请退款");
            }
            OmsOrderOperateHistory history = updateOrderInfo(id, order, OrderStatus.REFUNDING);
            history.setOrderStatus(OrderStatus.REFUNDING.getValue());
            history.setNote("申请退款");
            orderOperateHistoryService.save(history);
        }catch (Exception e){
            e.printStackTrace();
        }

        return new CommonResult().success();
    }

    private OmsOrderOperateHistory updateOrderInfo(Long id, OmsOrder order, OrderStatus refunding) {
        String key = RedisToolsConstant.orderDetail + apiContext.getCurrentProviderId() + "orderid" + id;
        redisUtil.delete(key);
        order.setStatus(refunding.getValue());
        orderMapper.updateById(order);

        OmsOrderOperateHistory history = new OmsOrderOperateHistory();
        history.setOrderId(order.getId());
        history.setCreateTime(new Date());
        history.setOperateMan("shop");
        history.setPreStatus(order.getStatus());
        return  history;
    }

    @Override
    public Object orderComment(Long orderId, String items) {
        UmsMember member= memberFeignClient.findById(orderMapper.selectById(orderId).getMemberId());
        List<ProductConsultParam> itemss = null;
        try {
            itemss = JsonUtil.jsonToList(items, ProductConsultParam.class);
            for (ProductConsultParam param : itemss){
                PmsProductConsult productConsult = new PmsProductConsult();
                if (member!=null){
                    productConsult.setPic(member.getIcon());
                    productConsult.setMemberName(member.getNickname());
                    productConsult.setMemberId(member.getId());
                }else {
                    return new CommonResult().failed("请先登录");
                }
                productConsult.setGoodsId(param.getGoodsId());
                productConsult.setOrderId(orderId);
                productConsult.setConsultContent(param.getTextarea());
                productConsult.setStars(param.getScore());
                productConsult.setEmail(Arrays.toString(param.getImages()));
                productConsult.setConsultAddtime(new Date());
                productConsult.setType(AllEnum.ConsultType.ORDER.code());
                if (ValidatorUtils.empty(param.getTextarea()) && ValidatorUtils.empty(param.getImages())){

                }else {
                    pmsFeignClinent.saveProductConsult(productConsult);
                }

            }
            OmsOrder omsOrder = new OmsOrder();
            omsOrder.setId(orderId);

            omsOrder.setStatus(OrderStatus.TRADE_SUCCESS.getValue());
            if (orderService.updateById(omsOrder)) {
                OmsOrderOperateHistory history = new OmsOrderOperateHistory();
                history.setOrderId(omsOrder.getId());
                history.setCreateTime(new Date());
                history.setOperateMan("shop");
                history.setPreStatus(OrderStatus.TO_COMMENT.getValue());
                history.setOrderStatus(OrderStatus.TRADE_SUCCESS.getValue());
                history.setNote("订单评价");
                orderOperateHistoryService.save(history);
                return new CommonResult().success(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return  new CommonResult().failed();
    }

    @Transactional
    @Override
    public Object blancePay(OmsOrder order) {
        UmsMember userDO= memberFeignClient.findById(order.getMemberId());
        if(order.getPayAmount().compareTo(userDO.getBlance())>0){
            return new CommonResult().failed("余额不足！");
        }
        order.setStatus(OrderStatus.TO_DELIVER.getValue());
        order.setPayType(AllEnum.OrderPayType.balancePay.code());
        order.setPaymentTime(new Date());
        orderService.updateById(order);
        if (ValidatorUtils.notEmpty(order.getGroupId())) {
            SmsGroup group = new SmsGroup();
            group.setId(order.getGroupId());
            group.setPeoples(group.getPeoples() - 1);
            markingFeignClinent.updateGroupById(group);
        }
        if (order.getPayAmount().compareTo(BigDecimal.ZERO)<0){
            order.setPayAmount(new BigDecimal("0.01"));
        }
        userDO.setBlance(userDO.getBlance().subtract(order.getPayAmount()));
        memberFeignClient.updateMember(userDO);
        UmsMemberBlanceLog blog = new UmsMemberBlanceLog();
        blog.setMemberId(userDO.getId());
        blog.setCreateTime(new Date());
        blog.setNote("支付订单：" + order.getId());
        blog.setPrice(order.getPayAmount());
        blog.setType(1);
        memberFeignClient.saveBlanceLog(blog);
        OmsOrderOperateHistory history = new OmsOrderOperateHistory();
        history.setOrderId(order.getId());
        history.setCreateTime(new Date());
        history.setOperateMan("shop");
        history.setPreStatus(OrderStatus.INIT.getValue());
        history.setOrderStatus(OrderStatus.TO_DELIVER.getValue());
        history.setNote("余额支付");
        orderOperateHistoryService.save(history);

        return order;
    }
    @Override
    public Object preGroupActivityOrder(OrderParam orderParam) {
        SmsGroupActivity smsGroupActivity = markingFeignClinent.getSmsGroupActivityById(orderParam.getGroupId());
        if (ValidatorUtils.notEmpty(smsGroupActivity.getGoodsIds())) {
            List<PmsProduct> productList = (List<PmsProduct>) pmsFeignClinent.listGoodsByIds(
                    Arrays.asList(smsGroupActivity.getGoodsIds().split(",")).stream().map(s -> Long.parseLong(s.trim())).collect(Collectors.toList()));
            if (productList != null && productList.size() > 0) {
                UmsMember currentMember = memberFeignClient.findById(orderParam.getMemberId());
                ConfirmOrderResult result = new ConfirmOrderResult();
                // 邮费
                BigDecimal transFee = BigDecimal.ZERO;
                //获取购物车信息
                List<OmsCartItem> list = new ArrayList<>();
                for (PmsProduct product : productList) {
                    if (product == null && product.getId() == null) {
                        return new CommonResult().failed("库存不够");
                    }
                    if (product != null && product.getStock() < 1) {
                        return new CommonResult().failed("库存不够");
                    }
                    OmsCartItem omsCartItem = new OmsCartItem();
                    omsCartItem.setProductId(product.getId());
                    omsCartItem.setPrice(product.getPrice());
                    omsCartItem.setCreateDate(new Date());
                    omsCartItem.setProductBrand(product.getBrandName());
                    omsCartItem.setProductCategoryId(product.getProductCategoryId());
                    omsCartItem.setProductName(product.getName());
                    omsCartItem.setProductSn(product.getProductSn());
                    omsCartItem.setQuantity(1);
                    omsCartItem.setProductPic(product.getPic());
                    list.add(omsCartItem);
                }
                result.setCartPromotionItemList(list);
                //获取用户收货地址列表
                UmsMemberReceiveAddress queryU = new UmsMemberReceiveAddress();
                queryU.setMemberId(currentMember.getId());
                List<UmsMemberReceiveAddress> memberReceiveAddressList = addressService.list(new QueryWrapper<>(queryU));
                result.setMemberReceiveAddressList(memberReceiveAddressList);
                UmsMemberReceiveAddress address = addressService.getDefaultItem(currentMember.getId());
                //获取用户可用优惠券列表
                CartMarkingVo vo = new CartMarkingVo();
                vo.setCartList(list);
                vo.setType(1);
                vo.setMemberId(currentMember.getId());
                List<SmsCouponHistoryDetail> couponHistoryDetailList = markingFeignClinent.listCart(vo);
                result.setCouponHistoryDetailList(couponHistoryDetailList);
                //获取用户积分
                result.setMemberIntegration(currentMember.getIntegration());
                //获取积分使用规则
                UmsIntegrationConsumeSetting integrationConsumeSetting = memberFeignClient.selectIntegrationConsumeSettingById(1L);
                result.setIntegrationConsumeSetting(integrationConsumeSetting);
                //计算总金额、活动优惠、应付金额
                if (list != null && list.size() > 0) {
                    ConfirmOrderResult.CalcAmount calcAmount = calcCartAmount(list, smsGroupActivity.getTransfee(), smsGroupActivity.getPrice());
                    result.setCalcAmount(calcAmount);
                    result.setAddress(address);
                    smsGroupActivity.setProductList(null);
                    result.setGroupActivity(smsGroupActivity);
                    return new CommonResult().success(result);
                }
                return null;
            }
        }
        return null;
    }
    private List<OmsCartItem> goodsToCartList(List<PmsProduct> productList) {
        List<OmsCartItem> cartItems = new ArrayList<>();
        for (PmsProduct product : productList) {
            OmsCartItem omsCartItem = new OmsCartItem();
            omsCartItem.setProductId(product.getId());
            omsCartItem.setPrice(product.getPrice());
            omsCartItem.setCreateDate(new Date());
            omsCartItem.setProductBrand(product.getBrandName());
            omsCartItem.setProductCategoryId(product.getProductCategoryId());
            omsCartItem.setProductName(product.getName());
            omsCartItem.setProductSn(product.getProductSn());
            omsCartItem.setQuantity(1);
            omsCartItem.setProductPic(product.getPic());
            cartItems.add(omsCartItem);
        }
        return cartItems;
    }
}

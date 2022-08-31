package com.mallplus.marking.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.mallplus.common.entity.oms.OmsCartItem;
import com.mallplus.common.entity.pms.PmsProduct;
import com.mallplus.common.entity.sms.SmsBasicGifts;
import com.mallplus.common.entity.sms.SmsBasicMarking;
import com.mallplus.common.feign.PmsFeignClinent;
import com.mallplus.common.utils.JsonUtil;
import com.mallplus.common.vo.AmountAndCount;
import com.mallplus.common.vo.BasicRuls;
import com.mallplus.common.vo.BeanKv;
import com.mallplus.common.vo.CartMarkingVo;
import com.mallplus.marking.mapper.SmsBasicGiftsMapper;
import com.mallplus.marking.mapper.SmsBasicMarkingMapper;
import com.mallplus.marking.service.ISmsBasicGiftsService;
import com.mallplus.marking.service.ISmsBasicMarkingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zscat
 * @since 2019-07-07
 */
@Service
public class SmsBasicGiftsServiceImpl extends ServiceImpl<SmsBasicGiftsMapper, SmsBasicGifts> implements ISmsBasicGiftsService {
    @Resource
    private PmsFeignClinent pmsFeignClinent;
    @Resource
    private  SmsBasicGiftsMapper giftsMapper;
    @Override
    public int updateStatus(Long id, Integer status) {
        SmsBasicGifts gifts = new SmsBasicGifts();
        gifts.setId(id);

        gifts.setStatus(status);

        return giftsMapper.updateById(gifts);
    }
    @Override
    public List<SmsBasicGifts> matchGoodsBasicGifts(Long id) {
        PmsProduct product = pmsFeignClinent.selectById(id);
        List<SmsBasicGifts> list = giftsMapper.selectList(new QueryWrapper<SmsBasicGifts>().eq("status", 0));
        List<SmsBasicGifts> newList = new ArrayList<>();
        for (SmsBasicGifts m : list) {
            if (checkManjian(m)) {
                SmsBasicGifts newBasicGift = new SmsBasicGifts();
                newBasicGift.setName(m.getName());
                newBasicGift.setId(m.getId());
                // BeanUtils.copyProperties(m, newBasicGift);
                if (m.getBigType() == 2) { // 1 首购礼 2 满 购礼 3 单品礼赠
                    BigDecimal totalAmount = product.getPrice();//实付金额
                    int totalCount = 1;

                    if (m.getActiviGoods() == 3) { //1 按类别  2 部分商品  3 全部
                        /**
                         * 首购礼 1第一单获取 2所有订单获取 ；
                         * 满购礼1选赠礼 获取规则 2满赠礼；
                         * 单品礼赠 1 仅送一件  2 按购买件数送  3 指定件数送
                         */
                        // 规则
                        List<BasicRuls> actrule = JsonUtil.jsonToList(m.getRules(), BasicRuls.class);
                        Collections.sort(actrule, Comparator.comparing(BasicRuls::getFullPrice).reversed());

                        getList(m, newBasicGift, totalAmount, totalCount, actrule);
                    } else { //1 按类别  2 部分商品  3 全部
                        BigDecimal singAmount = getCondtionByGoodsId(product, m);
                        List<BasicRuls> actrule = JsonUtil.jsonToList(m.getRules(), BasicRuls.class);
                        getList(m, newBasicGift, singAmount, totalCount, actrule);
                    }
                } else {
                    if (m.getActiviGoods() == 3) { //1 按类别  2 部分商品  3 全部
                        /**
                         * 首购礼 1第一单获取 2所有订单获取 ；
                         * 满购礼1选赠礼 获取规则 2满赠礼；
                         * 单品礼赠 1 仅送一件  2 按购买件数送  3 指定件数送
                         */
                        get(m, newBasicGift);
                    } else if (m.getActiviGoods() == 2) { //1 按类别  2 部分商品  3 全部
                        List<BeanKv> productRelationList = JsonUtil.jsonToList(m.getGoodsIds(), BeanKv.class);
                        boolean flag = false;
                        for (BeanKv goods : productRelationList) {
                            if (product.getId().equals(goods.getId())) {
                                flag = true;
                                break;
                            }
                        }
                        if (flag) {
                            get(m, newBasicGift);
                        }
                    } else {
                        List<BeanKv> productCategoryRelationList = JsonUtil.jsonToList(m.getGoodsIds(), BeanKv.class);
                        boolean flag = false;
                        for (BeanKv goods : productCategoryRelationList) {
                            if (product.getProductCategoryId().equals(goods.getId())) {
                                flag = true;
                                break;
                            }
                        }
                        if (flag) {
                            get(m, newBasicGift);
                        }
                    }
                }
                newList.add(newBasicGift);
            }
        }
        return newList;
    }



    @Override
    public List<SmsBasicGifts> matchOrderBasicGifts(CartMarkingVo vo) {
        List<SmsBasicGifts> list = giftsMapper.selectList(new QueryWrapper<SmsBasicGifts>().eq("status", 0));
        List<SmsBasicGifts> newList = new ArrayList<>();
        for (SmsBasicGifts m : list) {
            if (checkManjian(m)) {
                SmsBasicGifts newBasicGift = new SmsBasicGifts();
                newBasicGift.setName(m.getName());
                newBasicGift.setId(m.getId());
                // BeanUtils.copyProperties(m, newBasicGift);
                if (m.getBigType() == 2) { // 1 首购礼 2 满 购礼 3 单品礼赠
                    BigDecimal totalAmount = new BigDecimal("0");//实付金额
                    int totalCount = 0;
                    for (OmsCartItem cart : vo.getCartList()) {
                        totalAmount = totalAmount.add(cart.getPrice().multiply(new BigDecimal(cart.getQuantity())));
                        totalCount = totalCount + cart.getQuantity();
                    }
                    if (m.getActiviGoods() == 3) { //1 按类别  2 部分商品  3 全部
                        /**
                         * 首购礼 1第一单获取 2所有订单获取 ；
                         * 满购礼1选赠礼 获取规则 2满赠礼；
                         * 单品礼赠 1 仅送一件  2 按购买件数送  3 指定件数送
                         */
                        // 规则
                        List<BasicRuls> actrule = JsonUtil.jsonToList(m.getRules(), BasicRuls.class);
                        Collections.sort(actrule, Comparator.comparing(BasicRuls::getFullPrice).reversed());

                        getByRule(vo, m, newBasicGift, totalAmount, totalCount, actrule);
                    } else { //1 按类别  2 部分商品  3 全部
                        AmountAndCount andCount = getCondtion(vo, m);
                        List<BasicRuls> actrule = JsonUtil.jsonToList(m.getRules(), BasicRuls.class);
                        getByRule(vo, m, newBasicGift, andCount.getSingleAmount(), andCount.getSingleCount(), actrule);
                    }
                } else {
                    getBaseGifts(vo, m, newBasicGift);
                }
                newList.add(newBasicGift);
            }
        }
        return newList;
    }

    private void get(SmsBasicGifts m, SmsBasicGifts newBasicGift) {
        if (m.getBigType() == 1) {
            if (m.getSmallType() == 2) {
                newBasicGift.setGiftsList(JsonUtil.jsonToList(m.getGiftIds(), BeanKv.class));
            }
        } else {
            if (m.getSmallType() == 1) {
                newBasicGift.setGiftsList(JsonUtil.jsonToList(m.getGiftIds(), BeanKv.class));
            }
            if (m.getSmallType() == 2) {
                newBasicGift.setGiftsList(JsonUtil.jsonToList(m.getGiftIds(), BeanKv.class));
            }
        }
    }

    private void getList(SmsBasicGifts m, SmsBasicGifts newBasicGift, BigDecimal totalAmount, int totalCount, List<BasicRuls> actrule) {
        if (m.getBigType() == 1) {
            if ( m.getSmallType() == 2) {
                for (BasicRuls rule : actrule) {
                    if (totalCount >= rule.getFullPrice().intValue()) {
                        newBasicGift.setGiftsList(JsonUtil.jsonToList(m.getGiftIds(), BeanKv.class));
                        newBasicGift.setRule(rule);
                    }
                }
            }
        } else {
            addList(m, newBasicGift, totalAmount, totalCount, actrule);
        }
    }

    private void addList(SmsBasicGifts m, SmsBasicGifts newBasicGift, BigDecimal totalAmount, int totalCount, List<BasicRuls> actrule) {
        if (m.getSmallType() == 1) {
            for (BasicRuls rule : actrule) {
                if (totalAmount.compareTo(rule.getFullPrice()) >= 0) {
                    newBasicGift.setGiftsList(JsonUtil.jsonToList(m.getGiftIds(), BeanKv.class));
                    newBasicGift.setRule(rule);
                }
            }
        } else if (m.getSmallType() == 2) {
            for (BasicRuls rule : actrule) {
                if (totalCount >= rule.getFullPrice().intValue()) {
                    newBasicGift.setGiftsList(JsonUtil.jsonToList(m.getGiftIds(), BeanKv.class));
                    newBasicGift.setRule(rule);
                }
            }
        }
    }

    /**
     * 获取规则的数据
     *
     * @param vo
     * @param m
     * @return
     */
    private AmountAndCount getCondtion(CartMarkingVo vo, SmsBasicGifts m) {
        AmountAndCount andCount = new AmountAndCount();
        BigDecimal singleAmount = new BigDecimal("0");//实付金额
        int singleCount = 0;
        List<BeanKv> productRelationList = JsonUtil.jsonToList(m.getGoodsIds(), BeanKv.class);
        for (OmsCartItem cart : vo.getCartList()) {
            for (BeanKv goods : productRelationList) {
                if (m.getActiviGoods() == 2) {
                    if (cart.getProductId().equals(goods.getId())) {
                        singleAmount = singleAmount.add(cart.getPrice().multiply(new BigDecimal(cart.getQuantity())));
                        singleCount = singleCount + cart.getQuantity();
                        break;
                    }
                } else {
                    if (cart.getProductCategoryId().equals(goods.getId())) {
                        singleAmount = singleAmount.add(cart.getPrice().multiply(new BigDecimal(cart.getQuantity())));
                        singleCount = singleCount + cart.getQuantity();
                        break;
                    }
                }
            }
        }
        andCount.setSingleAmount(singleAmount);
        andCount.setSingleCount(singleCount);
        return andCount;
    }

    /**
     * 获取规则的数据
     *
     * @param product
     * @param m
     * @return
     */
    private BigDecimal getCondtionByGoodsId(PmsProduct product, SmsBasicGifts m) {
        BigDecimal singleAmount = new BigDecimal("0");//实付金额
        List<BeanKv> productRelationList = JsonUtil.jsonToList(m.getGoodsIds(), BeanKv.class);
        for (BeanKv goods : productRelationList) {
            if (m.getActiviGoods() == 2) {
                if (product.getId().equals(goods.getId())) {
                    singleAmount = product.getPrice();
                    break;
                }
            } else {
                if (product.getProductCategoryId().equals(goods.getId())) {
                    singleAmount = product.getPrice();
                    break;
                }
            }
        }
        return singleAmount;
    }
    private void getByRule(CartMarkingVo vo, SmsBasicGifts m, SmsBasicGifts newBasicGift, BigDecimal singleAmount, int singleCount, List<BasicRuls> actrule) {
        if (m.getBigType() == 1) {
            if (vo.getType() == 1 && m.getSmallType() == 1) {
                for (BasicRuls rule : actrule) {
                    if (singleAmount.compareTo(rule.getFullPrice()) >= 0) {
                        newBasicGift.setGiftsList(JsonUtil.jsonToList(m.getGiftIds(), BeanKv.class));
                        newBasicGift.setRule(rule);
                    }
                }
            } else if (vo.getType() == 2 && m.getSmallType() == 2) {
                for (BasicRuls rule : actrule) {
                    if (singleCount >= rule.getFullPrice().intValue()) {
                        newBasicGift.setGiftsList(JsonUtil.jsonToList(m.getGiftIds(), BeanKv.class));
                        newBasicGift.setRule(rule);
                    }
                }
            }
        } else {
            addList(m, newBasicGift, singleAmount, singleCount, actrule);
        }

    }

    /**
     * 全部商品的条件
     *
     * @param vo
     * @param m
     * @param newBasicGift
     */
    private void getNoRule(CartMarkingVo vo, SmsBasicGifts m, SmsBasicGifts newBasicGift) {
        if (m.getBigType() == 1) {
            if (vo.getType() == 1 && m.getSmallType() == 1) {
                newBasicGift.setGiftsList(JsonUtil.jsonToList(m.getGiftIds(), BeanKv.class));
            } else if (vo.getType() == 2 && m.getSmallType() == 2) {
                newBasicGift.setGiftsList(JsonUtil.jsonToList(m.getGiftIds(), BeanKv.class));
            }
        } else {
            if (m.getSmallType() == 1) {
                newBasicGift.setGiftsList(JsonUtil.jsonToList(m.getGiftIds(), BeanKv.class));
            }
            if (m.getSmallType() == 2) {
                newBasicGift.setGiftsList(JsonUtil.jsonToList(m.getGiftIds(), BeanKv.class));
            }
        }
    }

    private void getBaseGifts(CartMarkingVo vo, SmsBasicGifts m, SmsBasicGifts newBasicGift) {
        if (m.getActiviGoods() == 3) { //1 按类别  2 部分商品  3 全部
            /**
             * 首购礼 1第一单获取 2所有订单获取 ；
             * 满购礼1选赠礼 获取规则 2满赠礼；
             * 单品礼赠 1 仅送一件  2 按购买件数送  3 指定件数送
             */
            getNoRule(vo, m, newBasicGift);
        } else if (m.getActiviGoods() == 2) { //1 按类别  2 部分商品  3 全部
            List<BeanKv> productRelationList = JsonUtil.jsonToList(m.getGoodsIds(), BeanKv.class);
            boolean flag = false;
            for (OmsCartItem cart : vo.getCartList()) {
                for (BeanKv goods : productRelationList) {
                    if (cart.getProductId().equals(goods.getId())) {
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    break;
                }
            }
            if (flag) {
                getNoRule(vo, m, newBasicGift);
            }
        } else {
            List<BeanKv> productCategoryRelationList = JsonUtil.jsonToList(m.getGoodsIds(), BeanKv.class);
            boolean flag = false;
            for (OmsCartItem cart : vo.getCartList()) {
                for (BeanKv goods : productCategoryRelationList) {
                    if (cart.getProductCategoryId().equals(goods.getId())) {
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    break;
                }
            }
            if (flag) {
                getNoRule(vo, m, newBasicGift);
            }
        }
    }


    /*
        判断是否在高级设置活动范围内
  */
    private boolean checkManjian(SmsBasicGifts manjian) {
        if (manjian != null) {
            Date da = new Date();
            if (manjian.getStartTime().getTime() <= da.getTime() && manjian.getEndTime().getTime() >= da.getTime()) {
                return true;
            }
        }
        return false;
    }
}

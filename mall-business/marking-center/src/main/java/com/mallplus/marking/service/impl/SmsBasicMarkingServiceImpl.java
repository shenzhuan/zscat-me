package com.mallplus.marking.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.mallplus.common.entity.oms.OmsCartItem;
import com.mallplus.common.entity.pms.PmsProduct;
import com.mallplus.common.entity.sms.SmsBasicMarking;
import com.mallplus.common.feign.PmsFeignClinent;
import com.mallplus.common.utils.JsonUtil;
import com.mallplus.common.vo.AmountAndCount;
import com.mallplus.common.vo.BasicRuls;
import com.mallplus.common.vo.BeanKv;
import com.mallplus.common.vo.CartMarkingVo;
import com.mallplus.marking.mapper.SmsBasicMarkingMapper;
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
public class SmsBasicMarkingServiceImpl extends ServiceImpl<SmsBasicMarkingMapper, SmsBasicMarking> implements ISmsBasicMarkingService {

    @Resource
    private SmsBasicMarkingMapper markingMapper;
    @Resource
    private PmsFeignClinent pmsFeignClinent;
    /**
     *   * 1 有效2 无效
     * @param id
     * @param status
     * @return
     */
    @Transactional
    @Override
    public int updateStatus(Long id, Integer status,Integer bigType) {
        SmsBasicMarking marking = new SmsBasicMarking();
        if (status==1){
            marking.setId(id);
            marking.setStatus(1);
            markingMapper.updateById(marking);
        }else {
            marking.setStatus(1);
            markingMapper.update(marking,new QueryWrapper<SmsBasicMarking>().eq("big_type",bigType));
            marking.setId(id);
            marking.setStatus(0);
            markingMapper.updateById(marking);
        }
        return 0;
    }
    /**
     * 查询单个商品的优惠
     *
     * @param id
     * @return
     */
    @Override
    public List<SmsBasicMarking> matchGoodsBasicMarking(Long id) {
        List<SmsBasicMarking> newList = new ArrayList<>();
        PmsProduct product = pmsFeignClinent.selectById(id);
        List<SmsBasicMarking> list = markingMapper.selectList(new QueryWrapper<SmsBasicMarking>().eq("status", 0));
        BigDecimal totalAmount = product.getPrice();//实付金额
        int totalCount = 1;
        for (SmsBasicMarking m : list) {
            if (checkManjian(m)) {
                SmsBasicMarking newBasicGift = new SmsBasicMarking();
                newBasicGift.setName(m.getName());
                newBasicGift.setSmallType(m.getSmallType());
                newBasicGift.setBigType(m.getBigType());
                newBasicGift.setId(m.getId());
                List<BasicRuls> actrule = JsonUtil.jsonToList(m.getRules(), BasicRuls.class);
                Collections.sort(actrule, Comparator.comparing(BasicRuls::getFullPrice).reversed());
                if (m.getBigType() == 1) { // 1 满减 2 折扣
                    getList(newList, product, totalAmount, totalCount, m, newBasicGift, actrule);
                } else if (m.getBigType() == 2) {
                    getList(newList, product, totalAmount, totalCount, m, newBasicGift, actrule);
                }
            }
        }
        return newList;
    }


    public SmsBasicMarking matchOrderBasicMarking(CartMarkingVo vo) {

        List<SmsBasicMarking> list = markingMapper.selectList(new QueryWrapper<SmsBasicMarking>().eq("status", 0));
        BigDecimal lastAmount = BigDecimal.ZERO;
        SmsBasicMarking newBasicGift = null;
        for (SmsBasicMarking m : list) {
            if (checkManjian(m)) {
                newBasicGift = new SmsBasicMarking();
                newBasicGift.setName(m.getName());
                newBasicGift.setSmallType(m.getSmallType());
                newBasicGift.setBigType(m.getBigType());
                newBasicGift.setId(m.getId());
                List<BasicRuls> actrule = JsonUtil.jsonToList(m.getRules(), BasicRuls.class);
                Collections.sort(actrule, Comparator.comparing(BasicRuls::getFullPrice).reversed());
                if (m.getBigType() == 1) { // 1 满减 2 折扣
                    if (m.getActiviGoods() == 3) { //1 按类别  2 部分商品  3 全部
                        BigDecimal totalAmount = new BigDecimal("0");//实付金额
                        int totalCount = 0;
                        for (OmsCartItem cart : vo.getCartList()) {
                            totalAmount = totalAmount.add(cart.getPrice().multiply(new BigDecimal(cart.getQuantity())));
                            totalCount = totalCount + cart.getQuantity();
                        }
                        // 1消费金额 2 购买件数
                        lastAmount = getBigDecimal(lastAmount, newBasicGift, m, actrule, totalAmount, totalCount);
                    } else {
                        AmountAndCount andCount = getCondtion(vo, m);
                        // 1消费金额 2 购买件数
                        lastAmount = getBigDecimal(lastAmount, newBasicGift, m, actrule, andCount.getSingleAmount(), andCount.getSingleCount());
                    }
                } else if (m.getBigType() == 2) {
                    if (m.getActiviGoods() == 3) {
                        BigDecimal totalAmount = new BigDecimal("0");//实付金额
                        int totalCount = 0;
                        for (OmsCartItem cart : vo.getCartList()) {
                            totalAmount = totalAmount.add(cart.getPrice().multiply(new BigDecimal(cart.getQuantity())));
                            totalCount = totalCount + cart.getQuantity();
                        }
                        // 1消费金额 2 购买件数
                        lastAmount = getDIscountBigDecimal(lastAmount, newBasicGift, m, totalAmount, totalCount, actrule);
                    } else {
                        AmountAndCount andCount = getCondtion(vo, m);
                        // 1消费金额 2 购买件数
                        lastAmount = getDIscountBigDecimal(lastAmount, newBasicGift, m, andCount.getSingleAmount(), andCount.getSingleCount(), actrule);
                    }
                }


            }
        }
        if (newBasicGift!=null){
            newBasicGift.setMinAmount(lastAmount);
        }

        return newBasicGift;
    }

    private void getList(List<SmsBasicMarking> newList, PmsProduct product, BigDecimal totalAmount, int totalCount, SmsBasicMarking m, SmsBasicMarking newBasicGift, List<BasicRuls> actrule) {
        if (m.getActiviGoods() == 3) {
            get(newList, totalCount, m, newBasicGift, actrule, totalAmount);
        } else {
            BigDecimal singAmount = getCondtionByGoodsId(product, m);
            get(newList, totalCount, m, newBasicGift, actrule, singAmount);
        }
    }

    private void get(List<SmsBasicMarking> newList, int totalCount, SmsBasicMarking m, SmsBasicMarking newBasicGift, List<BasicRuls> actrule, BigDecimal singAmount) {
        newBasicGift.setActrule(actrule);
        if (m.getSmallType() == 1) {   // 1消费金额 2 购买件数
            for (BasicRuls rule : actrule) {
                if (singAmount.compareTo(rule.getFullPrice()) >= 0) {
                    newBasicGift.setSelectRule(rule);
                    newBasicGift.setMinAmount(rule.getReducePrice());
                    newList.add(newBasicGift);
                    break;
                }
            }
        } else if (m.getSmallType() == 2) {
            for (BasicRuls rule : actrule) {
                if (totalCount >= rule.getFullPrice().intValue()) {
                    newBasicGift.setSelectRule(rule);
                    newBasicGift.setMinAmount(rule.getReducePrice());
                    newList.add(newBasicGift);
                    break;
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
    private AmountAndCount getCondtion(CartMarkingVo vo, SmsBasicMarking m) {
        AmountAndCount andCount = new AmountAndCount();
        BigDecimal singleAmount = new BigDecimal("0");//实付金额
        int singleCount = 0;
        List<BeanKv> productRelationList = JsonUtil.jsonToList(m.getGoodsDs(), BeanKv.class);
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
    private BigDecimal getCondtionByGoodsId(PmsProduct product, SmsBasicMarking m) {
        BigDecimal singleAmount = new BigDecimal("0");//实付金额
        List<BeanKv> productRelationList = JsonUtil.jsonToList(m.getGoodsDs(), BeanKv.class);
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

    /**
     * 获取满减
     *
     * @param lastAmount
     * @param newBasicGift
     * @param m
     * @param singleAmount
     * @param singleCount
     * @param actrule
     * @return
     */
    private BigDecimal getBigDecimal(BigDecimal lastAmount, SmsBasicMarking newBasicGift, SmsBasicMarking m, BigDecimal singleAmount, int singleCount, List<BasicRuls> actrule) {
        if (m.getSmallType() == 1) {
            for (BasicRuls rule : actrule) {
                if (singleAmount.compareTo(rule.getFullPrice()) >= 0) {
                    if (rule.getReducePrice().compareTo(lastAmount) > 0) {
                        lastAmount = rule.getReducePrice();
                        newBasicGift.setMinAmount(lastAmount);
                        break;
                    }
                }
            }
        } else if (m.getSmallType() == 2) {
            for (BasicRuls rule : actrule) {
                if (singleCount >= rule.getFullPrice().intValue()) {
                    if (rule.getReducePrice().compareTo(lastAmount) > 0) {
                        lastAmount = rule.getReducePrice();
                        newBasicGift.setMinAmount(lastAmount);
                        break;
                    }
                }
            }
        }
        return lastAmount;
    }

    /**
     * 获取折扣
     *
     * @param lastAmount
     * @param newBasicGift
     * @param m
     * @param totalAmount
     * @param totalCount
     * @param actrule
     * @return
     */
    private BigDecimal getDIscountBigDecimal(BigDecimal lastAmount, SmsBasicMarking newBasicGift, SmsBasicMarking m, BigDecimal totalAmount, int totalCount, List<BasicRuls> actrule) {
        if (m.getSmallType() == 1) {
            for (BasicRuls rule : actrule) {
                if (totalAmount.compareTo(rule.getFullPrice()) >= 0) {
                    if (rule.getReducePrice().compareTo(lastAmount) > 0) {
                        lastAmount = rule.getReducePrice().multiply(totalAmount).divide(new BigDecimal(10),2);
                        newBasicGift.setMinAmount(totalAmount.subtract(lastAmount));
                        break;
                    }
                }
            }
        } else if (m.getSmallType() == 2) {
            for (BasicRuls rule : actrule) {
                if (totalCount >= rule.getFullPrice().intValue()) {
                    if (rule.getReducePrice().compareTo(lastAmount) > 0) {
                        lastAmount = rule.getReducePrice().multiply(totalAmount).divide(new BigDecimal(10),2);
                        newBasicGift.setMinAmount(totalAmount.subtract(lastAmount));
                        break;
                    }
                }
            }
        }
        return newBasicGift.getMinAmount();
    }

    private BigDecimal getBigDecimal(BigDecimal lastAmount, SmsBasicMarking newBasicGift, SmsBasicMarking m, List<BasicRuls> actrule, BigDecimal totalAmount, int totalCount) {
        lastAmount = getBigDecimal(lastAmount, newBasicGift, m, totalAmount, totalCount, actrule);
        return lastAmount;
    }


    /*
         判断是否在高级设置活动范围内
   */
    private boolean checkManjian(SmsBasicMarking manjian) {
        if (manjian != null) {
            Date da = new Date();
            if (manjian.getStartTime().getTime() <= da.getTime() && manjian.getEndTime().getTime() >= da.getTime()) {
                return true;
            }
        }
        return false;
    }
}

package com.mallplus.marking.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mallplus.common.entity.sms.SmsBasicGifts;
import com.mallplus.common.vo.CartMarkingVo;

import java.util.List;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zscat
 * @since 2019-07-07
 */
public interface ISmsBasicGiftsService extends IService<SmsBasicGifts> {

    int updateStatus(Long id, Integer status);
    /**
     * 满足商品的所有赠品优惠
     * @param id
     * @return
     */
    List<SmsBasicGifts> matchGoodsBasicGifts(Long id);

    /**
     * 满足订单的所有赠品优惠
     * @param vo
     * @return
     */
    List<SmsBasicGifts> matchOrderBasicGifts(CartMarkingVo vo);
}

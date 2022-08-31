package com.mallplus.marking.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mallplus.common.entity.sms.SmsFlashPromotionProductRelation;

/**
 * <p>
 * 商品限时购与商品关系表 服务类
 * </p>
 *
 * @author zscat
 * @since 2019-04-19
 */
public interface ISmsFlashPromotionProductRelationService extends IService<SmsFlashPromotionProductRelation> {


    int getCount(Long flashPromotionId, Long id);
}

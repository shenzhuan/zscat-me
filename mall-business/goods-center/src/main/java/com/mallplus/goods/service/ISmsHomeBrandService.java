package com.mallplus.goods.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.mallplus.common.entity.pms.SmsHomeBrand;

import java.util.List;

/**
 * <p>
 * 首页推荐品牌表 服务类
 * </p>
 *
 * @author zscat
 * @since 2019-04-19
 */
public interface ISmsHomeBrandService extends IService<SmsHomeBrand> {
    /**
     * 更新推荐状态
     */
    int updateRecommendStatus(List<Long> ids, Integer recommendStatus);
    /**
     * 修改品牌推荐排序
     */
    int updateSort(Long id, Integer sort);
}

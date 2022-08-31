package com.mallplus.cms.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.mallplus.common.entity.cms.SmsHomeRecommendSubject;

import java.util.List;

/**
 * <p>
 * 首页推荐专题表 服务类
 * </p>
 *
 * @author zscat
 * @since 2019-04-19
 */
public interface ISmsHomeRecommendSubjectService extends IService<SmsHomeRecommendSubject> {
    /**
     * 更新推荐状态
     */
    int updateRecommendStatus(List<Long> ids, Integer recommendStatus);
    /**
     * 修改推荐排序
     */
    int updateSort(Long id, Integer sort);
}

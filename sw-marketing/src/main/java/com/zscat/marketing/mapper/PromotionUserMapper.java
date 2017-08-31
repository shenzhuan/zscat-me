package com.zscat.marketing.mapper;

import com.zscat.base.MyMapper;
import com.zscat.marketing.model.PromotionUser;

import java.util.List;
import java.util.Map;

public interface PromotionUserMapper extends MyMapper<PromotionUser> {
    List<PromotionUser> findAllChild(Map<String, Object> params);
    List<PromotionUser> findAllOneChild(Map<String, Object> params);
}
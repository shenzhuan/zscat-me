package com.zscat.marketing.service;

import com.github.pagehelper.PageInfo;
import com.zscat.base.BaseService;
import com.zscat.marketing.model.PromotionUser;
import me.chanjar.weixin.common.exception.WxErrorException;

import java.util.Map;

/**
 * @author : zscat
 * @version : 1.0
 * @created on  : 2017/6/27  下午5:43
 */
public interface IPromotionUserService extends BaseService<PromotionUser> {
    /**
     * 合并微信用户信息
     *
     * @param wxOauthCode
     * @return
     */
    PromotionUser mergeUserInfo(String wxOauthCode);

    /**
     * 获取微信用户
     *
     * @param wxOauthCode
     * @return
     */
    PromotionUser getByWxOauthCode(String wxOauthCode) throws WxErrorException;

    /**
     * 根据 微信openid查询
     * @param openid
     * @return
     */
    PromotionUser getByWxOpenId(String openid);

    PageInfo<PromotionUser> findAllChild(Map<String, Object> params);

    PageInfo<PromotionUser> findAllOneChild(Map<String, Object> params);

    boolean isFull(Long upUid);

    String getUpgradeSchedule();

    String selectWithDrawLevel(Integer level);
}

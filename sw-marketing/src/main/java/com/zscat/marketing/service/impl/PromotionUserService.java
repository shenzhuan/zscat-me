package com.zscat.marketing.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zsCat.common.base.RedisLink;
import com.zscat.base.ServiceMybatis;
import com.zscat.marketing.constant.RedisKey;
import com.zscat.marketing.mapper.PromotionUserMapper;
import com.zscat.marketing.model.PromotionUser;
import com.zscat.marketing.service.IPromotionUserService;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author : zscat
 * @version : 1.0
 * @created on  : 2017/6/27  下午5:43
 */
@Service
public class PromotionUserService extends ServiceMybatis<PromotionUser> implements IPromotionUserService {

    @Resource
    PromotionUserMapper promotionUserMapper;

    @Resource
    WxMpService wxMpService;

    @Resource
    private RedisLink redisLink;

    /**
     * 合并微信用户信息
     *
     * @param wxOauthCode
     * @return
     */
    @Override
    public PromotionUser mergeUserInfo(String wxOauthCode) {
        return null;
    }

    /**
     * 获取微信用户
     *
     * @param wxOauthCode
     * @return
     */
    @Override
    public PromotionUser getByWxOauthCode(String wxOauthCode) throws WxErrorException {
        WxMpOAuth2AccessToken accessToken = wxMpService.oauth2getAccessToken(wxOauthCode);
        WxMpUser wxMpUser = wxMpService.oauth2getUserInfo(accessToken, "zh_CN");

        PromotionUser user = toPromotionUser(wxMpUser);

        PromotionUser user2 = getByWxOpenId(user.getWxOpenId());

        return null == user2 ? user : user2;
    }

    private PromotionUser toPromotionUser(WxMpUser wxMpUser) {
        PromotionUser user = new PromotionUser();
        user.setWxAvatar(wxMpUser.getHeadImgUrl());
        user.setWxNickname(wxMpUser.getNickname());
        user.setWxOpenId(wxMpUser.getOpenId());
        user.setWxUnionid(wxMpUser.getUnionId());
        return user;
    }


    /**
     * 根据 微信openid查询
     *
     * @param openid
     * @return
     */
    @Override
    public PromotionUser getByWxOpenId(String openid) {
        PromotionUser promotionUser = new PromotionUser();
        promotionUser.setWxOpenId(openid);
        return selectOne(promotionUser);
    }

    /**
     * 查询所有下行
     * @param params
     * @return
     */
    @Override
    public PageInfo<PromotionUser> findAllChild(Map<String, Object> params) {
        PageHelper.startPage(params);
        List<PromotionUser> list = promotionUserMapper.findAllChild(params);
        return new PageInfo<PromotionUser>(list);
    }
    /**
     * 查询所有下行
     * @param params
     * @return
     */
    @Override
    public PageInfo<PromotionUser> findAllOneChild(Map<String, Object> params) {
        List<PromotionUser> list = promotionUserMapper.findAllOneChild(params);
        return new PageInfo<PromotionUser>(list);
    }

    @Override
    public boolean isFull(Long upUid) {
        PromotionUser pu = new PromotionUser();
        pu.setUpUid(upUid);
        int count = selectCount(pu);
        if (count >= 20) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 这里加上ecache
     * @return
     */
    @Override
    public String getUpgradeSchedule() {
        return  redisLink.getString(RedisKey.UPGRDESCHEDULE);
    }

    @Override
    public String selectWithDrawLevel(Integer level) {
      return  redisLink.hget(RedisKey.WITHDRAWLEVEL,level+"");
    }
}

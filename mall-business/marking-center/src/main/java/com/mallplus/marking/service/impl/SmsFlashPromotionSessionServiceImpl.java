package com.mallplus.marking.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mallplus.common.entity.sms.SmsFlashPromotionSession;
import com.mallplus.marking.mapper.SmsFlashPromotionSessionMapper;
import com.mallplus.marking.service.ISmsFlashPromotionProductRelationService;
import com.mallplus.marking.service.ISmsFlashPromotionSessionService;
import com.mallplus.marking.vo.SmsFlashPromotionSessionDetail;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 限时购场次表 服务实现类
 * </p>
 *
 * @author zscat
 * @since 2019-04-19
 */
@Service
public class SmsFlashPromotionSessionServiceImpl extends ServiceImpl<SmsFlashPromotionSessionMapper, SmsFlashPromotionSession> implements ISmsFlashPromotionSessionService {

    @Resource
    private SmsFlashPromotionSessionMapper promotionSessionMapper;
    @Resource
    private ISmsFlashPromotionProductRelationService relationService;
    @Override
    public List<SmsFlashPromotionSessionDetail> selectList(Long flashPromotionId) {
        List<SmsFlashPromotionSessionDetail> result = new ArrayList<>();

        List<SmsFlashPromotionSession> list = promotionSessionMapper.selectList(new QueryWrapper<>(new SmsFlashPromotionSession()).eq("status",1));
        for (SmsFlashPromotionSession promotionSession : list) {
            SmsFlashPromotionSessionDetail detail = new SmsFlashPromotionSessionDetail();
            BeanUtils.copyProperties(promotionSession, detail);
            int count = relationService.getCount(flashPromotionId, promotionSession.getId());
            detail.setProductCount(count);
            result.add(detail);
        }
        return result;
    }

    @Override
    public int updateStatus(Long id, Integer status) {
        SmsFlashPromotionSession promotionSession = new SmsFlashPromotionSession();
        promotionSession.setId(id);
        promotionSession.setStatus(status);
        return promotionSessionMapper.updateById(promotionSession);
    }
}

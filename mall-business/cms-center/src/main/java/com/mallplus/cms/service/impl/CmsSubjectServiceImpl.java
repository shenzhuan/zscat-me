package com.mallplus.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mallplus.cms.mapper.UmsRewardLogMapper;
import com.mallplus.common.entity.cms.CmsSubject;
import com.mallplus.common.entity.cms.CmsSubjectCategory;
import com.mallplus.common.entity.cms.SmsHomeRecommendSubject;
import com.mallplus.cms.mapper.CmsSubjectCategoryMapper;
import com.mallplus.cms.mapper.CmsSubjectMapper;
import com.mallplus.cms.service.ICmsSubjectService;
import com.mallplus.cms.service.ISmsHomeRecommendSubjectService;
import com.mallplus.common.entity.cms.UmsRewardLog;
import com.mallplus.common.entity.ums.UmsMember;
import com.mallplus.common.feign.MemberFeignClient;
import com.mallplus.common.utils.CommonResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 专题表 服务实现类
 * </p>
 *
 * @author zscat
 * @since 2019-04-17
 */
@Service
public class CmsSubjectServiceImpl extends ServiceImpl<CmsSubjectMapper, CmsSubject> implements ICmsSubjectService {
    @Resource
    private CmsSubjectMapper subjectMapper;
    @Resource
    private ISmsHomeRecommendSubjectService homeRecommendSubjectService;

    @Resource
    private UmsRewardLogMapper rewardLogMapper;
    @Resource
    private MemberFeignClient memberFeignClient;
    @Resource
    private CmsSubjectCategoryMapper subjectCategoryMapper;

    @Override
    @Transactional
    public boolean saves(CmsSubject entity) {
        entity.setCreateTime(new Date());
        subjectMapper.insert(entity);
        CmsSubjectCategory category = subjectCategoryMapper.selectById(entity.getCategoryId());
        category.setSubjectCount(category.getSubjectCount() + 1);
        subjectCategoryMapper.updateById(category);
        return true;
    }

    @Override
    public int updateRecommendStatus(Long ids, Integer recommendStatus) {
        CmsSubject record = new CmsSubject();
        record.setRecommendStatus(recommendStatus);
        return subjectMapper.update(record, new QueryWrapper<CmsSubject>().in("id",ids));
    }

    @Override
    public int updateShowStatus(Long ids, Integer showStatus) {
        CmsSubject record = new CmsSubject();
        record.setShowStatus(showStatus);
        return subjectMapper.update(record, new QueryWrapper<CmsSubject>().in("id",ids));
    }

    @Override
    public List<CmsSubject> getRecommendSubjectList(int pageNum, int pageSize) {
        List<SmsHomeRecommendSubject> brands = homeRecommendSubjectService.list(new QueryWrapper<>());
        List<Long> ids = brands.stream()
                .map(SmsHomeRecommendSubject::getId)
                .collect(Collectors.toList());
        return (List<CmsSubject>) subjectMapper.selectBatchIds(ids);
    }
    @Override
    public int countByToday(Long id){
        return subjectMapper.countByToday(id);
    }
    @Transactional
    @Override
    public Object reward(Long aid, int coin,Long memberId) {
        try {
            UmsMember member = memberFeignClient.findById(memberId);
            if (member!=null && member.getBlance().compareTo(new BigDecimal(coin))<0){
                return new CommonResult().failed("余额不够");
            }
            member.setBlance(member.getBlance().subtract(new BigDecimal(coin)));
            memberFeignClient.updateMember(member);
            CmsSubject subject = subjectMapper.selectById(aid);
            UmsMember remember = memberFeignClient.findById(subject.getMemberId());
            if (remember!=null){
                subject.setReward(subject.getReward()+coin);
                subjectMapper.updateById(subject);
                remember.setBlance(remember.getBlance().add(new BigDecimal(coin)));
                memberFeignClient.updateMember(remember);
                UmsRewardLog log = new UmsRewardLog();
                log.setCoin(coin);log.setSendMemberId(member.getId());
                log.setMemberIcon(member.getIcon());
                log.setMemberNickName(member.getNickname());
                log.setRecMemberId(remember.getId());log.setCreateTime(new Date());
                log.setObjid(aid);
                rewardLogMapper.insert(log);
            }
            return new CommonResult().success("打赏文章成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResult().failed("打赏文章失败");
        }
    }
}

package com.mallplus.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.mallplus.common.entity.pms.PmsFavorite;
import com.mallplus.goods.mapper.PmsFavoriteMapper;
import com.mallplus.goods.service.IPmsFavoriteService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zscat
 * @since 2019-06-15
 */
@Service
public class PmsFavoriteServiceImpl extends ServiceImpl<PmsFavoriteMapper, PmsFavorite> implements IPmsFavoriteService {

    @Resource
    private PmsFavoriteMapper productCollectionRepository;

    @Override
    public int addProduct(PmsFavorite productCollection) {
        int count = 0;
        PmsFavorite query = new PmsFavorite();
        query.setObjId(productCollection.getObjId());
        query.setMemberId(productCollection.getMemberId());
        query.setType(productCollection.getType());
        PmsFavorite findCollection = productCollectionRepository.selectOne(new QueryWrapper<>(query));
        if (findCollection == null) {
            productCollection.setAddTime(new Date());
            productCollectionRepository.insert(productCollection);
            count = 1;
        }else {
            return productCollectionRepository.delete(new QueryWrapper<>(query));
        }
        return count;
    }



    @Override
    public List<PmsFavorite> listProduct(Long memberId, int type) {
        return productCollectionRepository.selectList(new QueryWrapper<PmsFavorite>().eq("member_id",memberId).eq("type",type));
    }
    @Override
    public List<PmsFavorite> listCollect(Long memberId) {
        return productCollectionRepository.selectList(new QueryWrapper<PmsFavorite>().eq("member_id",memberId));
    }
}

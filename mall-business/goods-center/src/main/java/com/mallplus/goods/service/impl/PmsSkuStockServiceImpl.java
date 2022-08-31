package com.mallplus.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mallplus.common.entity.pms.PmsSkuStock;
import com.mallplus.goods.mapper.PmsSkuStockMapper;
import com.mallplus.goods.service.IPmsSkuStockService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * sku的库存 服务实现类
 * </p>
 *
 * @author zscat
 * @since 2019-04-19
 */
@Service
public class PmsSkuStockServiceImpl extends ServiceImpl<PmsSkuStockMapper, PmsSkuStock> implements IPmsSkuStockService {
    @Resource
    private PmsSkuStockMapper skuStockMapper;


    @Override
    public List<PmsSkuStock> getList(Long pid, String keyword) {
        QueryWrapper q = new QueryWrapper();
        q.eq("product_id",pid);

        if (!StringUtils.isEmpty(keyword)) {
            q.like("sku_code",keyword);
        }
        return skuStockMapper.selectList(q);
    }

    @Override
    public int update(Long pid, List<PmsSkuStock> skuStockList) {
        return skuStockMapper.replaceList(skuStockList);
    }

}

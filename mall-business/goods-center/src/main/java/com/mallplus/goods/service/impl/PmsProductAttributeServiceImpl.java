package com.mallplus.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mallplus.common.entity.pms.PmsProductAttribute;
import com.mallplus.common.entity.pms.PmsProductAttributeCategory;
import com.mallplus.common.exception.ApiMallPlusException;
import com.mallplus.goods.mapper.PmsProductAttributeCategoryMapper;
import com.mallplus.goods.mapper.PmsProductAttributeMapper;
import com.mallplus.goods.service.IPmsProductAttributeService;
import com.mallplus.goods.vo.ProductAttrInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 商品属性参数表 服务实现类
 * </p>
 *
 * @author zscat
 * @since 2019-04-19
 */
@Service
public class PmsProductAttributeServiceImpl extends ServiceImpl<PmsProductAttributeMapper, PmsProductAttribute> implements IPmsProductAttributeService {

    @Resource
    private  PmsProductAttributeMapper productAttributeMapper;
    @Resource
    private PmsProductAttributeCategoryMapper productAttributeCategoryMapper;

    @Override
    public List<ProductAttrInfo> getProductAttrInfo(Long productCategoryId) {
        return productAttributeMapper.getProductAttrInfo(productCategoryId);
    }

    @Transactional
    @Override
    public boolean saveAndUpdate(PmsProductAttribute entity) {
        int count = productAttributeMapper.selectCount(new QueryWrapper<PmsProductAttribute>().eq("product_attribute_category_id", entity.getProductAttributeCategoryId()).eq("type", entity.getType()));
        if (count >= 3) {
            throw new ApiMallPlusException("规格或者属性数量不能超过3个");
        }
         productAttributeMapper.insert(entity);
        //新增商品属性以后需要更新商品属性分类数量
        PmsProductAttributeCategory pmsProductAttributeCategory = productAttributeCategoryMapper.selectById(entity.getProductAttributeCategoryId());
        if (entity.getType() == 0) {
            pmsProductAttributeCategory.setAttributeCount(pmsProductAttributeCategory.getAttributeCount() + 1);
        } else if (entity.getType() == 1) {
            pmsProductAttributeCategory.setParamCount(pmsProductAttributeCategory.getParamCount() + 1);
        }
        productAttributeCategoryMapper.updateById(pmsProductAttributeCategory);
        return true;
    }
}

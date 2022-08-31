package com.mallplus.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mallplus.common.entity.pms.PmsProduct;
import com.mallplus.common.entity.pms.PmsProductCategory;
import com.mallplus.common.entity.pms.PmsProductCategoryAttributeRelation;
import com.mallplus.goods.mapper.PmsProductCategoryAttributeRelationMapper;
import com.mallplus.goods.mapper.PmsProductCategoryMapper;
import com.mallplus.goods.mapper.PmsProductMapper;
import com.mallplus.goods.service.IPmsProductCategoryAttributeRelationService;
import com.mallplus.goods.service.IPmsProductCategoryService;
import com.mallplus.goods.vo.PmsProductCategoryWithChildrenItem;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 产品分类 服务实现类
 * </p>
 *
 * @author zscat
 * @since 2019-04-19
 */
@Service
public class PmsProductCategoryServiceImpl extends ServiceImpl<PmsProductCategoryMapper, PmsProductCategory> implements IPmsProductCategoryService {

    @Resource
    private PmsProductCategoryMapper categoryMapper;
    @Resource
    private PmsProductMapper productMapper;
    @Resource
    private IPmsProductCategoryAttributeRelationService pmsProductCategoryAttributeRelationService;
    @Resource
    private PmsProductCategoryAttributeRelationMapper productCategoryAttributeRelationMapper;

    @Override
    public List<PmsProductCategoryWithChildrenItem> listWithChildren() {
        return categoryMapper.listWithChildren();
    }

    @Override
    public int updateNavStatus(List<Long> ids, Integer navStatus) {
        PmsProductCategory productCategory = new PmsProductCategory();
        productCategory.setNavStatus(navStatus);
        return categoryMapper.update(productCategory, new QueryWrapper<PmsProductCategory>().in("id",ids));
    }

    @Override
    public int updateShowStatus(List<Long> ids, Integer showStatus) {
        PmsProductCategory productCategory = new PmsProductCategory();
        productCategory.setShowStatus(showStatus);
        return categoryMapper.update(productCategory, new QueryWrapper<PmsProductCategory>().in("id",ids));
    }

    @Override
    public boolean updateAnd(PmsProductCategory entity) {
        PmsProductCategory productCategory = new PmsProductCategory();
        productCategory.setId(entity.getId());

        setCategoryLevel(productCategory);
        //更新商品分类时要更新商品中的名称
        PmsProduct product = new PmsProduct();
        product.setProductCategoryName(productCategory.getName());

        productMapper.update(product, new QueryWrapper<PmsProduct>().eq("product_category_id",entity.getId()));
        //同时更新筛选属性的信息
        if (!CollectionUtils.isEmpty(entity.getProductAttributeIdList())) {

            productCategoryAttributeRelationMapper.delete(new QueryWrapper<>(new PmsProductCategoryAttributeRelation()).eq("product_category_id",entity.getId()));
            insertRelationList(entity.getId(), entity.getProductAttributeIdList());
        } else {
            productCategoryAttributeRelationMapper.delete(new QueryWrapper<>(new PmsProductCategoryAttributeRelation()).eq("product_category_id",entity.getId()));

        }
         categoryMapper.updateById(productCategory);
        return true;
    }

    @Override
    public boolean saveAnd(PmsProductCategory entity) {
        PmsProductCategory productCategory = new PmsProductCategory();
        productCategory.setProductCount(0);
        BeanUtils.copyProperties(entity, productCategory);
        //没有父分类时为一级分类
        setCategoryLevel(productCategory);
        int count = categoryMapper.insert(productCategory);
        //创建筛选属性关联
        List<Long> productAttributeIdList = entity.getProductAttributeIdList();
        if (!CollectionUtils.isEmpty(productAttributeIdList)) {
            insertRelationList(productCategory.getId(), productAttributeIdList);
        }
        return true;
    }
    /**
     * 批量插入商品分类与筛选属性关系表
     *
     * @param productCategoryId      商品分类id
     * @param productAttributeIdList 相关商品筛选属性id集合
     */
    private void insertRelationList(Long productCategoryId, List<Long> productAttributeIdList) {
        List<PmsProductCategoryAttributeRelation> relationList = new ArrayList<>();
        for (Long productAttrId : productAttributeIdList) {
            PmsProductCategoryAttributeRelation relation = new PmsProductCategoryAttributeRelation();
            relation.setProductAttributeId(productAttrId);
            relation.setProductCategoryId(productCategoryId);
            relationList.add(relation);
        }
        pmsProductCategoryAttributeRelationService.saveBatch(relationList);
    }
    /**
     * 根据分类的parentId设置分类的level
     */
    private void setCategoryLevel(PmsProductCategory productCategory) {
        //没有父分类时为一级分类
        if (productCategory.getParentId() == 0) {
            productCategory.setLevel(0);
        } else {
            //有父分类时选择根据父分类level设置
            PmsProductCategory parentCategory = categoryMapper.selectById(productCategory.getParentId());
            if (parentCategory != null) {
                productCategory.setLevel(parentCategory.getLevel() + 1);
            } else {
                productCategory.setLevel(0);
            }
        }
    }
}

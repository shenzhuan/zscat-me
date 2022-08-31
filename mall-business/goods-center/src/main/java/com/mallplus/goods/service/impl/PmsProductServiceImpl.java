package com.mallplus.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mallplus.common.entity.pms.*;
import com.mallplus.common.entity.pms.PmsProduct;
import com.mallplus.common.entity.sms.SmsHomeAdvertise;
import com.mallplus.common.redis.constant.RedisToolsConstant;
import com.mallplus.common.redis.template.RedisRepository;
import com.mallplus.common.utils.ValidatorUtils;
import com.mallplus.common.vo.GoodsDetailResult;
import com.mallplus.common.vo.HomeContentResult;
import com.mallplus.goods.mapper.*;
import com.mallplus.goods.service.*;
import com.mallplus.goods.utils.GoodsUtils;
import com.mallplus.goods.vo.PmsProductParam;
import com.mallplus.goods.vo.PmsProductResult;
import com.mallplus.sentinel.config.ConstansValue;
import com.mallplus.util.DateUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 商品信息 服务实现类
 * </p>
 *
 * @author zscat
 * @since 2019-04-19
 */
@Slf4j
@Service
public class PmsProductServiceImpl extends ServiceImpl<PmsProductMapper, PmsProduct> implements IPmsProductService {

    @Resource
    private IPmsBrandService brandService;
    @Resource
    private ISmsHomeBrandService homeBrandService;
    @Resource
    private ISmsHomeNewProductService homeNewProductService;
    @Resource
    private ISmsHomeRecommendProductService homeRecommendProductService;
    @Resource
    private PmsProductMapper productMapper;
    @Resource
    private IPmsMemberPriceService memberPriceDao;
    @Resource
    private PmsMemberPriceMapper memberPriceMapper;
    @Resource
    private IPmsProductLadderService productLadderDao;
    @Resource
    private PmsProductLadderMapper productLadderMapper;
    @Resource
    private IPmsProductFullReductionService productFullReductionDao;
    @Resource
    private PmsProductFullReductionMapper productFullReductionMapper;
    @Resource
    private IPmsSkuStockService skuStockDao;
    @Resource
    private PmsSkuStockMapper skuStockMapper;
    @Resource
    private IPmsProductAttributeValueService productAttributeValueDao;
    @Resource
    private PmsProductAttributeValueMapper productAttributeValueMapper;
    @Resource
    private ICmsSubjectProductRelationService subjectProductRelationDao;
    @Resource
    private CmsSubjectProductRelationMapper subjectProductRelationMapper;
    @Resource
    private ICmsPrefrenceAreaProductRelationService prefrenceAreaProductRelationDao;
    @Resource
    private CmsPrefrenceAreaProductRelationMapper prefrenceAreaProductRelationMapper;

    @Resource
    private PmsProductVertifyRecordMapper productVertifyRecordDao;

    @Resource
    private PmsProductVertifyRecordMapper productVertifyRecordMapper;
    @Autowired
    private ISmsHomeAdvertiseService advertiseService;
    @Resource
    private RedisRepository redisRepository;
    @Resource
    private IPmsProductAttributeCategoryService productAttributeCategoryService;
    @Resource
    private IPmsProductService pmsProductService;
    public List<SmsHomeAdvertise> getHomeAdvertiseList() {
        SmsHomeAdvertise advertise = new SmsHomeAdvertise();
        advertise.setStatus(1);
        return advertiseService.list(new QueryWrapper<>(advertise));
    }
    @Override
    public  HomeContentResult content(){
        HomeContentResult result = new HomeContentResult();
        //获取首页广告
        result.setAdvertiseList(getHomeAdvertiseList());
        //获取推荐品牌
        result.setBrandList(this.getRecommendBrandList(0, 4));

        //获取新品推荐
        result.setNewProductList(this.getNewProductList(0, 4));
        //获取人气推荐
        result.setHotProductList(this.getHotProductList(0, 4));
        //获取推荐专题
     //   result.setSubjectList(this.getRecommendSubjectList(0, 4));
        List<PmsProductAttributeCategory> productAttributeCategoryList = getPmsProductAttributeCategories();
        result.setCat_list(productAttributeCategoryList);
        return result;
    }

    public List<PmsProductAttributeCategory> getPmsProductAttributeCategories() {
        List<PmsProductAttributeCategory> productAttributeCategoryList = productAttributeCategoryService.list(new QueryWrapper<>());

        for (PmsProductAttributeCategory gt : productAttributeCategoryList) {
            PmsProduct productQueryParam = new PmsProduct();
            productQueryParam.setProductAttributeCategoryId(gt.getId());
            productQueryParam.setPublishStatus(1);
            productQueryParam.setVerifyStatus(1);
            IPage<PmsProduct> goodsList = pmsProductService.page(new Page<PmsProduct>(0, 8), new QueryWrapper<>(productQueryParam).select(ConstansValue.sampleGoodsList));

            if (goodsList!=null&& goodsList.getRecords()!=null && goodsList.getRecords().size()>0){
                gt.setGoodsList(goodsList.getRecords());
            }else{
                gt.setGoodsList(new ArrayList<>());
            }

        }
        return productAttributeCategoryList;
    }

    @Override
    public int create(PmsProductParam productParam) {
        int count;
        //创建商品
        PmsProduct product = productParam;
        product.setCreateTime(new Date());
        product.setId(null);
        //处理sku的编码
        handleSkuStockCode(productParam.getSkuStockList(), product);
        if (ValidatorUtils.isEmpty(product.getProductSn())) {
            product.setProductSn(IdWorker.getId() + "");
        }

        if (ValidatorUtils.empty(product.getOriginalPrice())) {
            product.setOriginalPrice(product.getPrice());
        }
        productMapper.insert(product);
        //根据促销类型设置价格：、阶梯价格、满减价格
        Long productId = product.getId();

        //会员价格
        //  relateAndInsertList(memberPriceDao, productParam.getMemberPriceList(), productId);
        //阶梯价格
        // relateAndInsertList(productLadderDao, productParam.getProductLadderList(), productId);
        //满减价格
        // relateAndInsertList(productFullReductionDao, productParam.getProductFullReductionList(), productId);
        //处理sku的编码
        // handleSkuStockCode(productParam.getSkuStockList(), productId);
        //添加sku库存信息
        List<PmsSkuStock> skuStockList = productParam.getSkuStockList();
        for (PmsSkuStock stock : skuStockList) {
            stock.setProductId(productId);
            stock.setProductName(product.getName());
        }
        skuStockDao.saveBatch(skuStockList);

        List<PmsProductAttributeValue> productAttributeValueList = productParam.getProductAttributeValueList();
        for (PmsProductAttributeValue stock : productAttributeValueList) {
            stock.setProductId(productId);
        }
        productAttributeValueDao.saveBatch(productAttributeValueList);

        List<CmsSubjectProductRelation> subjectProductRelationList = productParam.getSubjectProductRelationList();
        for (CmsSubjectProductRelation stock : subjectProductRelationList) {
            stock.setProductId(productId);
        }
        subjectProductRelationDao.saveBatch(subjectProductRelationList);

        List<CmsPrefrenceAreaProductRelation> prefrenceAreaProductRelationList = productParam.getPrefrenceAreaProductRelationList();
        for (CmsPrefrenceAreaProductRelation stock : prefrenceAreaProductRelationList) {
            stock.setProductId(productId);
        }
        prefrenceAreaProductRelationDao.saveBatch(prefrenceAreaProductRelationList);
        //relateAndInsertList(skuStockDao, productParam.getSkuStockList(), productId);
        //添加商品参数,添加自定义商品规格
        // relateAndInsertList(productAttributeValueDao, productParam.getProductAttributeValueList(), productId);
        //关联专题
        //  relateAndInsertList(subjectProductRelationDao, productParam.getSubjectProductRelationList(), productId);
        //关联优选
        //  relateAndInsertList(prefrenceAreaProductRelationDao, productParam.getPrefrenceAreaProductRelationList(), productId);
        count = 1;

        redisRepository.set(String.format(RedisToolsConstant.GOODSDETAIL, product.getId()), productParam);
        return count;
    }

    private void handleSkuStockCode(List<PmsSkuStock> skuStockList, PmsProduct product) {
        if (CollectionUtils.isEmpty(skuStockList)) return;
        int stock = 0;
        for (int i = 0; i < skuStockList.size(); i++) {
            PmsSkuStock skuStock = skuStockList.get(i);
            if (StringUtils.isEmpty(skuStock.getSkuCode())) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                StringBuilder sb = new StringBuilder();
                //日期
                sb.append(sdf.format(new Date()));
                //四位商品id
                sb.append(String.format("%04d", product.getProductCategoryId()));
                //三位索引id
                sb.append(String.format("%03d", i + 1));
                skuStock.setSkuCode(sb.toString());
                stock = stock + skuStock.getStock();
            }
            product.setStock(stock);
        }
    }

    @Override
    public PmsProductResult getUpdateInfo(Long id) {
        return productMapper.getUpdateInfo(id);
    }

    @Override
    public int update(Long id, PmsProductParam productParam) {
        int count;
        //更新商品信息
        PmsProduct product = productParam;
        product.setId(id);
        handleSkuStockCode(productParam.getSkuStockList(), product);
        productMapper.updateById(product);
        //会员价格
        //  memberPriceMapper.delete(new QueryWrapper<>(new PmsMemberPrice()).eq("product_id",id));
        //  relateAndInsertList(memberPriceDao, productParam.getMemberPriceList(), id);
        //阶梯价格

        //  productLadderMapper.delete(new QueryWrapper<>(new PmsProductLadder()).eq("product_id",id));
        //  relateAndInsertList(productLadderDao, productParam.getProductLadderList(), id);
        //满减价格

        //  productFullReductionMapper.delete(new QueryWrapper<>(new PmsProductFullReduction()).eq("product_id",id));
        // relateAndInsertList(productFullReductionDao, productParam.getProductFullReductionList(), id);
        //修改sku库存信息

        skuStockMapper.delete(new QueryWrapper<>(new PmsSkuStock()).eq("product_id",id));

        //  relateAndInsertList(skuStockDao, productParam.getSkuStockList(), id);
        //修改商品参数,添加自定义商品规格

        productAttributeValueMapper.delete(new QueryWrapper<>(new PmsProductAttributeValue()).eq("product_id",id));
        //  relateAndInsertList(productAttributeValueDao, productParam.getProductAttributeValueList(), id);
        //关联专题

        subjectProductRelationMapper.delete(new QueryWrapper<>(new CmsSubjectProductRelation()).eq("product_id",id));
        //  relateAndInsertList(subjectProductRelationDao, productParam.getSubjectProductRelationList(), id);
        //关联优选

        prefrenceAreaProductRelationMapper.delete(new QueryWrapper<>(new CmsPrefrenceAreaProductRelation()).eq("product_id",id));
        //  relateAndInsertList(prefrenceAreaProductRelationDao, productParam.getPrefrenceAreaProductRelationList(), id);
        List<PmsSkuStock> skuStockList = productParam.getSkuStockList();
        for (PmsSkuStock stock : skuStockList) {
            stock.setProductId(id);
            stock.setProductName(product.getName());
        }
        skuStockDao.saveBatch(skuStockList);

        List<PmsProductAttributeValue> productAttributeValueList = productParam.getProductAttributeValueList();
        for (PmsProductAttributeValue stock : productAttributeValueList) {
            stock.setProductId(id);
        }
        productAttributeValueDao.saveBatch(productAttributeValueList);

        List<CmsSubjectProductRelation> subjectProductRelationList = productParam.getSubjectProductRelationList();
        for (CmsSubjectProductRelation stock : subjectProductRelationList) {
            stock.setProductId(id);
        }
        subjectProductRelationDao.saveBatch(subjectProductRelationList);

        List<CmsPrefrenceAreaProductRelation> prefrenceAreaProductRelationList = productParam.getPrefrenceAreaProductRelationList();
        for (CmsPrefrenceAreaProductRelation stock : prefrenceAreaProductRelationList) {
            stock.setProductId(id);
        }
        prefrenceAreaProductRelationDao.saveBatch(prefrenceAreaProductRelationList);
        count = 1;
        redisRepository.set(String.format(RedisToolsConstant.GOODSDETAIL, product.getId()), productParam);
        return count;
    }



    @Override
    public int updateVerifyStatus(Long ids, Integer verifyStatus, String detail) {
        PmsProduct product = new PmsProduct();
        product.setVerifyStatus(verifyStatus);
        int count = productMapper.update(product, new QueryWrapper<PmsProduct>().eq("id",ids) );
        //修改完审核状态后插入审核记录

        PmsProductVertifyRecord record = new PmsProductVertifyRecord();
        record.setProductId(ids);
        record.setCreateTime(new Date());
        record.setDetail(detail);
        record.setStatus(verifyStatus);
       // record.setVertifyMan(UserUtils.getCurrentMember().getUsername());
        productVertifyRecordMapper.insert(record);
        return count;
    }

    @Override
    public int updatePublishStatus(List<Long> ids, Integer publishStatus) {
        PmsProduct record = new PmsProduct();
        record.setPublishStatus(publishStatus);

        return productMapper.update(record, new QueryWrapper<PmsProduct>().in("id",ids));
    }

    @Override
    public int updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        PmsProduct record = new PmsProduct();
        record.setRecommandStatus(recommendStatus);

        return productMapper.update(record, new QueryWrapper<PmsProduct>().in("id",ids));
    }

    @Override
    public int updateNewStatus(List<Long> ids, Integer newStatus) {
        PmsProduct record = new PmsProduct();
        record.setNewStatus(newStatus);

        return productMapper.update(record, new QueryWrapper<PmsProduct>().in("id",ids));
    }

    @Override
    public int updateDeleteStatus(List<Long> ids, Integer deleteStatus) {
        PmsProduct record = new PmsProduct();
        record.setDeleteStatus(deleteStatus);

        return productMapper.update(record, new QueryWrapper<PmsProduct>().in("id",ids));
    }

    @Override
    public List<PmsProduct> list(String keyword) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("delete_status",0);

        if (!StringUtils.isEmpty(keyword)) {
            queryWrapper.like("name",keyword);

        }
        return productMapper.selectList(queryWrapper);
    }

    @Override
    public List<PmsProductVertifyRecord> getProductVertifyRecord(Long id) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("product_id",id);

        return productVertifyRecordMapper.selectList(queryWrapper);
    }


    /**
     * 建立和插入关系表操作
     *
     * @param dao       可以操作的dao
     * @param dataList  要插入的数据
     * @param productId 建立关系的id
     */
    private void relateAndInsertList(Object dao, List dataList, Long productId) {
        try {
            if (CollectionUtils.isEmpty(dataList)) return;
            for (Object item : dataList) {
                Method setId = item.getClass().getMethod("setId", Long.class);
                setId.invoke(item, (Long) null);
                Method setProductId = item.getClass().getMethod("setProductId", Long.class);
                setProductId.invoke(item, productId);
            }
            Method insertList = dao.getClass().getMethod("saveBatch", List.class);
            insertList.invoke(dao, dataList);
        } catch (Exception e) {
            e.printStackTrace();
            log.warn("创建产品出错:{}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<PmsBrand> getRecommendBrandList(int pageNum, int pageSize) {
        List<SmsHomeBrand> brands = homeBrandService.list(new QueryWrapper<>());
        if (brands!=null && brands.size()>0){
            List<Long> ids = brands.stream()
                    .map(SmsHomeBrand::getId)
                    .collect(Collectors.toList());
            return (List<PmsBrand>) brandService.listByIds(ids);
        }
      return null;

    }
    @Override
    public List<PmsProduct> getNewProductList(int pageNum, int pageSize) {
        PmsProduct query = new PmsProduct();
        query.setPublishStatus(1);
        query.setVerifyStatus(1);
        return this.page(new Page<PmsProduct>(pageNum, pageSize), new QueryWrapper<>(query).orderByDesc("create_time").select(ConstansValue.sampleGoodsList)).getRecords();

    }
    @Override
    public List<PmsProduct> getHotProductList(int pageNum, int pageSize) {
        List<SmsHomeRecommendProduct> brands = homeRecommendProductService.list(new QueryWrapper<>());
        if (brands!=null && brands.size()>0){
            List<Long> ids = brands.stream()
                    .map(SmsHomeRecommendProduct::getId)
                    .collect(Collectors.toList());
            return (List<PmsProduct>)productMapper.selectBatchIds(ids);
        }
        return null;


    }

    @Override
    public Object initGoodsRedis() {
        List<PmsProduct> list = productMapper.selectList(new QueryWrapper<>());
        for (PmsProduct goods : list) {
            GoodsDetailResult param = getPmsProductParam(goods);
        }
        return 1;
    }
    @Override
    public GoodsDetailResult getGoodsRedisById(Long id) {
        PmsProduct goods = productMapper.selectById(id);

        GoodsDetailResult param = getPmsProductParam(goods);

        return param;
    }

    private GoodsDetailResult getPmsProductParam(PmsProduct goods) {
        GoodsDetailResult param = new GoodsDetailResult();
        param.setGoods(goods);
/*

        List<PmsProductLadder> productLadderList = productLadderMapper.selectList(new QueryWrapper<PmsProductLadder>().eq("product_id", goods.getId()));

        List<PmsProductFullReduction> productFullReductionList = productFullReductionMapper.selectList(new QueryWrapper<PmsProductFullReduction>().eq("product_id", goods.getId()));

        List<PmsMemberPrice> memberPriceList = memberPriceMapper.selectList(new QueryWrapper<PmsMemberPrice>().eq("product_id", goods.getId()));
  param.setProductFullReductionList(productFullReductionList);
        param.setProductLadderList(productLadderList);
*/

        List<PmsSkuStock> skuStockList = skuStockMapper.selectList(new QueryWrapper<PmsSkuStock>().eq("product_id", goods.getId()));

        List<PmsProductAttributeValue> productAttributeValueList = productAttributeValueMapper.selectList(new QueryWrapper<PmsProductAttributeValue>().eq("product_id", goods.getId()).eq("type",1));

        List<CmsSubjectProductRelation> subjectProductRelationList = subjectProductRelationMapper.selectList(new QueryWrapper<CmsSubjectProductRelation>().eq("product_id", goods.getId()));

        List<CmsPrefrenceAreaProductRelation> prefrenceAreaProductRelationList = prefrenceAreaProductRelationMapper.selectList(new QueryWrapper<CmsPrefrenceAreaProductRelation>().eq("product_id", goods.getId()));
        List<PmsProductAttributeValue> productCanShuValueList = productAttributeValueMapper.selectList(new QueryWrapper<PmsProductAttributeValue>().eq("product_id", goods.getId()).eq("type",2));
        param.setProductCanShuValueList(productCanShuValueList);

        param.setPrefrenceAreaProductRelationList(prefrenceAreaProductRelationList);
        param.setProductAttributeValueList(productAttributeValueList);

        param.setSkuStockList(skuStockList);
        param.setSubjectProductRelationList(subjectProductRelationList);
        List<PmsProduct> typeGoodsList = productMapper.selectList(new QueryWrapper<PmsProduct>().eq("product_attribute_category_id", goods.getProductAttributeCategoryId()).select(ConstansValue.sampleGoodsList));
        param.setTypeGoodsList(typeGoodsList);
        redisRepository.set(String.format(RedisToolsConstant.GOODSDETAIL, goods.getId()), param);
        return param;
    }
}



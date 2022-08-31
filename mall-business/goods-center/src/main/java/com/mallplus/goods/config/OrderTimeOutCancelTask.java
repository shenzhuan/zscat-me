package com.mallplus.goods.config;


import com.mallplus.common.entity.pms.PmsProduct;
import com.mallplus.common.redis.constant.RedisToolsConstant;
import com.mallplus.common.redis.template.RedisUtil;
import com.mallplus.common.utils.CommonResult;
import com.mallplus.goods.mapper.PmsProductMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * https://github.com/shenzhuan/mallplus on 2018/8/24.
 * 订单超时取消并解锁库存的定时器
 */
@Component
public class OrderTimeOutCancelTask {
    private Logger logger = LoggerFactory.getLogger(OrderTimeOutCancelTask.class);

    @Resource
    private RedisUtil redisUtil;
    @Resource
    private PmsProductMapper productMapper;




    /**
     * 商品浏览量
     */
    @Scheduled(cron = "0 0/10 * * * ? ")//每1分钟
    public void SyncGoodsView() {
        logger.info("开始保存点赞数 、浏览数SyncGoodsView");
        try {
            //先获取这段时间的浏览数
            Map<Object,Object> viewCountItem=redisUtil.hGetAll(RedisToolsConstant.GOODS_VIEWCOUNT_KEY);
            //然后删除redis里这段时间的浏览数
            redisUtil.delete(RedisToolsConstant.GOODS_VIEWCOUNT_KEY);
            if(!viewCountItem.isEmpty()){
                for(Object item :viewCountItem.keySet()){
                    String articleKey=item.toString();//viewcount_1
                    String[]  kv=articleKey.split("_");
                    Long articleId=Long.parseLong(kv[1]);
                    Integer viewCount=Integer.parseInt(viewCountItem.get(articleKey).toString());
                    PmsProduct subject = productMapper.selectById(articleId);
                    subject.setId(articleId);
                    subject.setHit(subject.getHit()+viewCount);
                    logger.info("SyncGoodsView"+articleId+","+viewCount);
                    //更新到数据库
                    productMapper.updateById(subject);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        logger.info("结束保存点赞数 、浏览数");
    }
}

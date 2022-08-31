package com.mallplus.goods.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mallplus.common.entity.pms.PmsFavorite;


import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zscat
 * @since 2019-06-15
 */
public interface IPmsFavoriteService extends IService<PmsFavorite> {
    int addProduct(PmsFavorite productCollection);


    List<PmsFavorite> listProduct(Long memberId, int type);

    List<PmsFavorite> listCollect(Long memberId);
}

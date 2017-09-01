 /*
  * Powered By zsCat, Since 2014 - 2020
  */
package com.zscat.shop.service.impl;

import com.alibaba.dubbo.config.annotation.Service;

import com.zsCat.common.base.BaseService;
import com.zsCat.common.base.ServiceMybatis;
import com.zscat.shop.model.Favorites;
import com.zscat.shop.service.FavoriteService;

 /**
 * 
 * @author zsCat 2017-4-14 13:56:18
 * @Email: 951449465@qq.com
 * @version 4.0v
 */
@Service(version = "1.0.0",retries = 0,timeout = 60000)
public class FavoriteServiceImpl extends ServiceMybatis<Favorites> implements FavoriteService {

  
    
}

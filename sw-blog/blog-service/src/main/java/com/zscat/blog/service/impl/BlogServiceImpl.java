 /*
  * Powered By zsCat, Since 2014 - 2020
  */
package com.zscat.blog.service.impl;

import com.zsCat.common.base.ServiceMybatis;
import com.zscat.blog.api.model.Blog;
import com.zscat.blog.api.service.BlogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.dubbo.config.annotation.Service;

import java.util.List;


 /**
 * 
 * @author zsCat 2017-4-14 13:56:18
 * @Email: 951449465@qq.com
 * @version 4.0v
 */
 @Service(version = "1.0.0",retries = 0,timeout = 60000)
public class BlogServiceImpl extends ServiceMybatis<Blog> implements BlogService {
  private static final Logger logger = LoggerFactory.getLogger(BlogServiceImpl.class);
  public List<Blog> selectAll(Blog record) {

   logger.info("BlogServiceImpl====selectAll");
   return mapper.select(record);
  }

    
}

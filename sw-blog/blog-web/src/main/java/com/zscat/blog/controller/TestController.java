package com.zscat.blog.controller;


import com.zsCat.common.base.RedisLink;
import com.zscat.blog.api.model.Blog;
import com.zscat.blog.api.service.BlogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 *
 * @author zs 2016-5-5 11:33:51
 * @Email: 951449465@qq.com
 * @version 4.0v
 *	我的blog
 */
@Controller
@RequestMapping("test/blog")
public class TestController {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);
//@Resource
private BlogService blogService;
  //  @Resource(name = "redisLink")
    private RedisLink redisLink;

   // @Resource(name = "fcRedisLink")
    private RedisLink fcRedisLink;


    @ResponseBody
    @RequestMapping(value = "/test")
    public String test(HttpServletRequest request) {

        logger.info("redis------------------");
        System.out.println(redisLink.getString("biuld_info"));
        System.out.println(fcRedisLink.getString("RecommendVideoData"));
        logger.debug("info---------------");
        List<Blog> blogList = blogService.selectAll(new Blog());
        for (Blog b : blogList){
            System.out.println(b.getImg()+","+b.getTitle());
        }

        return  blogList.get(0).getTitle();
    }
}

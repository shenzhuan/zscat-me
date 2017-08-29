/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014-2016 abel533@gmail.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */


import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.zsCat.common.base.RedisLink;
import com.zscat.CmsWebApplication;
import com.zscat.cms.model.CmsArticle;
import com.zscat.cms.service.CmsArticleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * @author liuzh
 * @since 2016-03-06 17:42
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@Transactional
@SpringBootTest(classes = CmsWebApplication.class)
public class MyBatis331Test {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource(name = "redisLink")
    private RedisLink redisLink;

    @Resource(name = "fcRedisLink")
    private RedisLink fcRedisLink;
    @Reference(version = "1.0.0")
    private CmsArticleService cmsArticleService;
    @Test
    public void querySettings() {
        System.out.println(redisLink.getString("biuld_info"));
        System.out.println(fcRedisLink.getString("RecommendVideoData"));
    }
    @Test
    public void querySettings1() {
        PageInfo<CmsArticle> artList = cmsArticleService.selectPage(1, 40, new CmsArticle(),"");
        System.out.println(artList.getPages());
    }

}

package com.zscat.search.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;

import com.zscat.search.service.LuceneService;
import com.zscat.search.service.SolrSearchService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;


/**
 *
 * @author zs 2016-5-5 11:33:51
 * @Email: 951449465@qq.com
 * @version 4.0v
 *	我的blog
 */
@Controller
@RequestMapping("index")
public class IndexController {


    @Reference(version = "1.0.0")
private LuceneService luceneService;
    @Reference(version = "1.0.0")
private SolrSearchService solrSearchService;

/**
 * 请求主页
 * @return
 * @throws Exception
 */
@RequestMapping("/index")
public ModelAndView index()throws Exception{
    ModelAndView mav=new ModelAndView();
    PageInfo page =luceneService.page(1,15,"zscat");
    mav.addObject("objList", page);
    mav.setViewName("index");
    return mav;
}

    @RequestMapping("/search")
    public ModelAndView search(HttpServletRequest request)throws Exception{
    String wd =request.getParameter("wd");
        ModelAndView mav=new ModelAndView();
        PageInfo page =luceneService.page(1,15,wd);
        mav.addObject("objList", page);
        mav.setViewName("index");
        return mav;
    }
}

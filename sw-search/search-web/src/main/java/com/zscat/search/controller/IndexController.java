package com.zscat.search.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;

import com.zscat.blog.api.model.Blog;
import com.zscat.blog.api.service.BlogService;
import com.zscat.cms.model.CmsArticle;
import com.zscat.cms.service.CmsArticleService;
import com.zscat.search.model.IndexObject;
import com.zscat.search.service.LuceneService;
import com.zscat.search.service.SolrSearchService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import com.zsCat.common.utils.DateUtils;

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
    @Reference(version = "1.0.0")
    private BlogService blogService;
    @Reference(version = "1.0.0")
    private CmsArticleService cmsArticleService;
/**
 * 请求主页
 * @return
 * @throws Exception
 */
@RequestMapping("/index")
public ModelAndView index()throws Exception{
    ModelAndView mav=new ModelAndView();
    try {
        PageInfo page =luceneService.page(1,15,"zscat");
        mav.addObject("objList", page);
        mav.setViewName("index");
    }catch (Exception e){
        e.printStackTrace();
    }
    return mav;
}

    @RequestMapping("/search")
    public ModelAndView search(HttpServletRequest request)throws Exception{
    String wd =request.getParameter("wd");
        ModelAndView mav=new ModelAndView();
        try {
            PageInfo page =luceneService.page(1,15,wd);
            mav.addObject("objList", page);
            mav.setViewName("index");
        }catch (Exception e){
            e.printStackTrace();
        }
        return mav;
    }
    @RequestMapping("/init")
    public ModelAndView init(HttpServletRequest request)throws Exception{
        luceneService.delete(null);
        ModelAndView mav=new ModelAndView();
        try {
            List<Blog> l =blogService.selectAll(new Blog());
            for (Blog content : l) {
                IndexObject indexObject = new IndexObject();
                indexObject.setId(content.getId());
                indexObject.setTitle(content.getTitle());
                indexObject.setKeywords(content.getSummary());
                indexObject.setDescripton(content.getContent());
                indexObject.setPostDate(DateUtils.formatDate(content.getReleasedate()));
                indexObject.setUrl(content.getTitle());
             //   indexObject.setUrl(this.httpProtocol + "://" + ControllerUtil.getDomain() + "/front/" + content.getSiteId() + "/" + content.getCategoryId() + "/" + content.getContentId());
                luceneService.save(indexObject);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            PageInfo page =luceneService.page(1,15,"zscat");
            mav.addObject("objList", page);
            mav.setViewName("index");
        }catch (Exception e){
            e.printStackTrace();
        }
        return mav;
    }
    @RequestMapping("/init1")
    public ModelAndView init1(HttpServletRequest request)throws Exception{
        luceneService.delete(null);
        ModelAndView mav=new ModelAndView();
        try {
            List<CmsArticle> l =cmsArticleService.select(new CmsArticle());
            for (CmsArticle content : l) {
                IndexObject indexObject = new IndexObject();
                indexObject.setId(content.getId());
                indexObject.setTitle(content.getTitle());
                indexObject.setKeywords(content.getDescription());
                indexObject.setDescripton(content.getContent());
                indexObject.setPostDate(DateUtils.formatDate(content.getCreatedate()));
                indexObject.setUrl(content.getTitle());
                //  indexObject.setUrl(this.httpProtocol + "://" + ControllerUtil.getDomain() + "/front/" + content.getSiteId() + "/" + content.getCategoryId() + "/" + content.getContentId());
                luceneService.save(indexObject);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            PageInfo page =luceneService.page(1,15,"华润悦景湾");
            mav.addObject("objList", page);
            mav.setViewName("index");
        }catch (Exception e){
            e.printStackTrace();
        }
        return mav;
    }
}

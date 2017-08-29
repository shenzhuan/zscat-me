package com.zscat.search.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.zsCat.common.utils.DateUtils;
import com.zscat.blog.api.model.Blog;
import com.zscat.blog.api.service.BlogService;
import com.zscat.cms.model.CmsArticle;
import com.zscat.cms.service.CmsArticleService;
import com.zscat.search.model.IndexObject;

import com.zscat.search.service.SolrSearchService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
@RequestMapping("index1")
public class IndexSolrController {

    private String serverUrl = "http://127.0.0.1:8080/solr/core3";

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
        List<IndexObject> page =solrSearchService.search("zscat",serverUrl);
        mav.addObject("objList", page);
        mav.setViewName("index1");
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
            List<IndexObject> page =solrSearchService.search(wd,serverUrl);
            mav.addObject("objList", page);
            mav.setViewName("index1");
        }catch (Exception e){
            e.printStackTrace();
        }
        return mav;
    }
    @RequestMapping("/init")
    public ModelAndView init(HttpServletRequest request)throws Exception{
        solrSearchService.deleteAll(serverUrl);
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
                solrSearchService.upadteIndex(indexObject,serverUrl);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            List<IndexObject> page  =solrSearchService.search("zscat",serverUrl);
            mav.addObject("objList", page);
            mav.setViewName("index");
        }catch (Exception e){
            e.printStackTrace();
        }
        return mav;
    }
    @RequestMapping("/init1")
    public ModelAndView init1(HttpServletRequest request)throws Exception{
        solrSearchService.deleteAll(serverUrl);;
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
                solrSearchService.upadteIndex(indexObject,serverUrl);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            List<IndexObject> page  =solrSearchService.search("华润悦景湾",serverUrl);
            mav.addObject("objList", page);
            mav.setViewName("index1");
        }catch (Exception e){
            e.printStackTrace();
        }
        return mav;
    }
}

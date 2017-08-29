package com.zscat.cms.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.zscat.cms.model.CmsArticle;
import com.zscat.cms.service.CmsArticleService;
import com.zscat.cms.service.CmsCategoryService;
	/**
	 * 
	 * @author zs 2016-5-5 11:33:51
	 * @Email: 951449465@qq.com
	 * @version 4.0v
	 *	我的blog
	 */
@Controller
@RequestMapping("wap/cms")
public class IndexWapCmsController {


		@Reference(version = "1.0.0")
	private CmsArticleService cmsArticleService;
		@Reference(version = "1.0.0")
	private CmsCategoryService cmsCategoryService;
	
	/**
	 * 请求主页
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/index1")
	public ModelAndView index1()throws Exception{
		ModelAndView mav=new ModelAndView();
		PageInfo<CmsArticle> articleList=cmsArticleService.selectPage(1, 31, null);
		mav.addObject("articleList", articleList);
		mav.setViewName("wap/index1");
		return mav;
	}

	@RequestMapping(value = "/cateArticle/{categoryId}")
	public String cateArticel(@PathVariable("categoryId") Long categoryId,@RequestParam(value = "pageNum",required=false,defaultValue="1")Integer pageNum,
			@RequestParam(value = "pageSize",required=false,defaultValue="12")Integer pageSize,Model model) {
		CmsArticle CmsArticle=new CmsArticle ();
		CmsArticle.setCategoryId(categoryId);
		PageInfo<CmsArticle> artList=cmsArticleService.selectPage(pageNum, pageSize, CmsArticle);
		model.addAttribute("page", artList);
		return "wap/news";
	}
	/**
	 * 产品详细信息
	 * @return
	 * @throws Exception
	 */
//	@RequestMapping("/newsDetail/{id}")
//	public ModelAndView newsDetails(@PathVariable("id") Long id,HttpServletRequest request)throws Exception{
//		ModelAndView mav=new ModelAndView();
//				CmsArticle article=cmsArticleService.selectByPrimaryKey(id);
//				mav.addObject("article", article);
//		mav.setViewName("wap/newsDetail");
//		return mav;
//	}
	/**
	 * 请求主页
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/index")
	public ModelAndView index()throws Exception{
		ModelAndView mav=new ModelAndView();
		PageInfo<CmsArticle> articleList=cmsArticleService.selectPage(1, 31, null);
		mav.addObject("articleList", articleList);
		mav.setViewName("wap/index");
		return mav;
	}
	
	
	
	/**
	 * 本地服务
	 * @param pageNum
	 * @param pageSize
	 * @param CmsArticle
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "activity")
	public String activity(@RequestParam(value = "pageNum",required=false,defaultValue="1")Integer pageNum,
			@RequestParam(value = "pageSize",required=false,defaultValue="12")Integer pageSize,
			@ModelAttribute CmsArticle CmsArticle, Model model) {
		PageInfo<CmsArticle> artList=cmsArticleService.selectPage(pageNum, pageSize, CmsArticle);
		model.addAttribute("page", artList);
		
		return "wap/activity";
	}
	/**
	 * 产品详细信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/activityDetail/{id}")
	public ModelAndView localDetails(@PathVariable("id") Long id,HttpServletRequest request)throws Exception{
		ModelAndView mav=new ModelAndView();
				//首页顶部导航
				CmsArticle article=cmsArticleService.selectByPrimaryKey(id);
				mav.addObject("article", article);
		mav.setViewName("wap/activityDetail");
		
		return mav;
	}
	/**
	 * 论坛菜单
	 * @param pageNum
	 * @param pageSize
	 * @param CmsArticle
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "news")
	public String forumAll(@RequestParam(value = "pageNum",required=false,defaultValue="1")Integer pageNum,
			@RequestParam(value = "pageSize",required=false,defaultValue="12")Integer pageSize,@ModelAttribute CmsArticle CmsArticle, Model model) {
		PageInfo<CmsArticle> artList=cmsArticleService.selectPage(pageNum, pageSize, CmsArticle);
		model.addAttribute("page", artList);
		return "wap/news";
	}
	
	/**
	 * 产品详细信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/newsDetail/{id}")
	public ModelAndView newsDetails(@PathVariable("id") Long id,HttpServletRequest request)throws Exception{
		ModelAndView mav=new ModelAndView();
				CmsArticle article=cmsArticleService.selectByPrimaryKey(id);
				mav.addObject("article", article);
		mav.setViewName("wap/newsDetail");
		return mav;
	}
	/**
	 * 专题
	 * @param pageNum
	 * @param pageSize
	 * @param CmsArticle
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "subject")
	public String subject(@RequestParam(value = "pageNum",required=false,defaultValue="1")Integer pageNum,
			@RequestParam(value = "pageSize",required=false,defaultValue="12")Integer pageSize,@ModelAttribute CmsArticle CmsArticle, Model model) {
		
		PageInfo<CmsArticle> artList=cmsArticleService.selectPage(pageNum, pageSize, CmsArticle);
		model.addAttribute("page", artList);
		return "wap/subject";
	}
	/**
	 * 产品详细信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/subjectDetail/{id}")
	public ModelAndView subjectDetails(@PathVariable("id") Long id,HttpServletRequest request)throws Exception{
		ModelAndView mav=new ModelAndView();
				CmsArticle article=cmsArticleService.selectByPrimaryKey(id);
				mav.addObject("article", article);
		mav.setViewName("wap/subjectDetail");
		return mav;
	}
	
	/**
	 * 专题
	 * @param pageNum
	 * @param pageSize
	 * @param CmsArticle
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "vedio")
	public String vedio(@RequestParam(value = "pageNum",required=false,defaultValue="1")Integer pageNum,
			@RequestParam(value = "pageSize",required=false,defaultValue="12")Integer pageSize,@ModelAttribute CmsArticle CmsArticle, Model model) {
		
		PageInfo<CmsArticle> artList=cmsArticleService.selectPage(pageNum, pageSize, CmsArticle);
		model.addAttribute("page", artList);
		return "wap/vedio";
	}
	/**
	 * 产品详细信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/vedioDetail/{id}")
	public ModelAndView vedioDetails(@PathVariable("id") Long id,HttpServletRequest request)throws Exception{
		ModelAndView mav=new ModelAndView();
				CmsArticle article=cmsArticleService.selectByPrimaryKey(id);
				mav.addObject("article", article);
		mav.setViewName("wap/vedioDetail");
		return mav;
	}
	
	
	
	@RequestMapping(value = "quwen")
	public String quwen(@RequestParam(value = "pageNum",required=false,defaultValue="1")Integer pageNum,
			@RequestParam(value = "pageSize",required=false,defaultValue="12")Integer pageSize,@ModelAttribute CmsArticle CmsArticle, Model model) {
		PageInfo<CmsArticle> artList=cmsArticleService.selectPage(pageNum, pageSize, CmsArticle);
		model.addAttribute("page", artList);
		return "wap/quwen";
	}
	/**
	 * 产品详细信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/quwenDetail/{id}")
	public ModelAndView quwenDetail(@PathVariable("id") Long id,HttpServletRequest request)throws Exception{
		ModelAndView mav=new ModelAndView();
				CmsArticle article=cmsArticleService.selectByPrimaryKey(id);
				mav.addObject("article", article);
		mav.setViewName("wap/quwenDetail");
		return mav;
	}
	@RequestMapping(value = "reader")
	public String reader(@RequestParam(value = "pageNum",required=false,defaultValue="1")Integer pageNum,
			@RequestParam(value = "pageSize",required=false,defaultValue="12")Integer pageSize,@ModelAttribute CmsArticle CmsArticle, Model model) {
		
		PageInfo<CmsArticle> artList=cmsArticleService.selectPage(pageNum, pageSize, CmsArticle);
		model.addAttribute("page", artList);
		return "wap/reader";
	}
	/**
	 * 产品详细信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/readerDetail/{id}")
	public ModelAndView readerDetail(@PathVariable("id") Long id,HttpServletRequest request)throws Exception{
		ModelAndView mav=new ModelAndView();
				CmsArticle article=cmsArticleService.selectByPrimaryKey(id);
				mav.addObject("article", article);
		mav.setViewName("wap/readerDetail");
		return mav;
	}
	@RequestMapping(value = "img")
	public String img(@RequestParam(value = "pageNum",required=false,defaultValue="1")Integer pageNum,
			@RequestParam(value = "pageSize",required=false,defaultValue="12")Integer pageSize,@ModelAttribute CmsArticle CmsArticle, Model model) {
		
		PageInfo<CmsArticle> artList=cmsArticleService.selectPage(pageNum, pageSize, CmsArticle);
		model.addAttribute("page", artList);
		return "wap/img";
	}
	/**
	 * 产品详细信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/imgDetail/{id}")
	public ModelAndView imgDetails(@PathVariable("id") Long id,HttpServletRequest request)throws Exception{
		ModelAndView mav=new ModelAndView();
				CmsArticle article=cmsArticleService.selectByPrimaryKey(id);
				mav.addObject("article", article);
		mav.setViewName("wap/imgDetail");
		return mav;
	}
	@RequestMapping(value = "user")
	public String user(@RequestParam(value = "pageNum",required=false,defaultValue="1")Integer pageNum,
			@RequestParam(value = "pageSize",required=false,defaultValue="12")Integer pageSize,@ModelAttribute CmsArticle CmsArticle, Model model) {
		PageInfo<CmsArticle> artList=cmsArticleService.selectPage(pageNum, pageSize, CmsArticle);
		model.addAttribute("page", artList);
		return "pc/user";
	}
}

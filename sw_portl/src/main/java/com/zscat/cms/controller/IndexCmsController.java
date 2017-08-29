package com.zscat.cms.controller;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.zscat.cms.model.CmsArticle;
import com.zscat.cms.model.CmsCategory;
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
@RequestMapping("web/cms")
public class IndexCmsController {


		@Reference(version = "1.0.0")
	private CmsArticleService cmsArticleService;
		@Reference(version = "1.0.0")
	private CmsCategoryService cmsCategoryService;

		@RequestMapping("/index1")
		public String index1( Model model)throws Exception{

			CmsCategory CmsCategory=new CmsCategory();
			CmsCategory.setIsshow(1);
			CmsCategory.setModule("houseNav");
			model.addAttribute("cateList", cmsCategoryService.select(CmsCategory));

			return "pc/index";
		}
	/**
	 * 请求主页
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/index")
	public ModelAndView index()throws Exception{
		ModelAndView mav=new ModelAndView();
		CmsCategory CmsCategory=new CmsCategory();
		CmsCategory.setIsshow(1);
		CmsCategory.setModule("houseNav");
		mav.addObject("cateList", cmsCategoryService.select(CmsCategory));
		mav.setViewName("pc/index");
		return mav;
	}
	@RequestMapping("/index/ajax/bna")
	public String ajaxindex(HttpServletRequest request) {
		try {
			String id = request.getParameter("order");
			if (id != null && !id.equals("")) {
				CmsArticle a=new CmsArticle();
				PageInfo<CmsArticle> artList =null;
				if("reco".equals(id)){
					a.setWeight(1);
					artList = cmsArticleService.selectPage(1, 40, a,"");
				}else if("late".equals(id)){
					artList = cmsArticleService.selectPage(1, 40, a,"createdate desc");
				}else if("hot".equals(id)){
					artList = cmsArticleService.selectPage(1, 40, a,"hits desc");
				}
				
				request.setAttribute("artList", artList.getList());
			}
		} catch (Exception e) {

		}
		return "pc/ajax-index";
	}
	
	@RequestMapping("/indexT")
	public ModelAndView indexT()throws Exception{
		ModelAndView mav=new ModelAndView();
		
		mav.setViewName("pc/indexT");
		return mav;
	}
	
	@RequestMapping("/indexT/ajax/bna")
	public String ajaxindexT(HttpServletRequest request) {
		try {
			String id = request.getParameter("order");
			if (id != null && !id.equals("")) {
				CmsArticle a=new CmsArticle();
				PageInfo<CmsArticle> artList =null;
				if("reco".equals(id)){
					a.setWeight(1);
					artList = cmsArticleService.selectPage(1, 40, a);
				}else if("late".equals(id)){
					artList = cmsArticleService.selectPage(1, 40, a,"createdate desc");
				}else if("hot".equals(id)){
					artList = cmsArticleService.selectPage(1, 40, a,"hits desc");
				}
				
				request.setAttribute("artList", artList.getList());
			}
		} catch (Exception e) {

		}
		return "pc/ajax-indexT";
	}
	/**
	 * 产品详细信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/house/{id}")
	public String house(@PathVariable("id") Long id,
			@RequestParam(value = "pageNum",required=false,defaultValue="1")Integer pageNum,
			@RequestParam(value = "pageSize",required=false,defaultValue="31")Integer pageSize,
			@ModelAttribute CmsArticle CmsArticle, Model model)throws Exception{
		
		CmsCategory CmsCategory=new CmsCategory();
		CmsCategory.setIsshow(1);
		CmsCategory.setParentId(id);
		List<CmsCategory> cateList=cmsCategoryService.select(CmsCategory);
		model.addAttribute("cateList",cateList);
//		if(cateList!=null && cateList.size()>0){
//			CmsArticle.setCategoryId(cateList.get(0).getId());
//		}
//		PageInfo<CmsArticle> artList=cmsArticleService.selectPage(pageNum, pageSize, CmsArticle);
//		model.addAttribute("page", artList);
		
		return "pc/house";
	}
	
	/**
	 * 产品详细信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/houseDetails/{id}")
	public ModelAndView localDetails(@PathVariable("id") Long id,HttpServletRequest request)throws Exception{
		ModelAndView mav=new ModelAndView();
				//首页顶部导航
				CmsArticle article=cmsArticleService.selectByPrimaryKey(id);
				mav.addObject("article", article);
		mav.setViewName("pc/houseDetails");
		
		return mav;
	}
	
	
	/**
	 * 旅游菜单
	 * @param pageNum
	 * @param pageSize
	 * @param CmsArticle
	 * @param model
	 * @return
	 */
	@RequestMapping("/travel/{id}")
	public String travel(@PathVariable("id") Long id,
			@RequestParam(value = "pageNum",required=false,defaultValue="1")Integer pageNum,
			@RequestParam(value = "pageSize",required=false,defaultValue="12")Integer pageSize,
			@ModelAttribute CmsArticle CmsArticle, Model model) {
		PageInfo<CmsArticle> artList=cmsArticleService.selectPage(pageNum, pageSize, CmsArticle);
		model.addAttribute("page", artList);
		
		CmsCategory CmsCategory=new CmsCategory();
		CmsCategory.setIsshow(1);
		CmsCategory.setParentId(id);
		List<CmsCategory> cateList=cmsCategoryService.select(CmsCategory);
		model.addAttribute("cateList",cateList);
		if(cateList!=null && cateList.size()>0){
			model.addAttribute("cateFirstId",cateList.get(0).getId());
		}
		return "pc/travel";
	}
	
		@RequestMapping("/travel/ajax/bna")
		public String queryCourse(HttpServletRequest request) {
			try {
				String id = request.getParameter("order");
				if (id != null && !id.equals("")) {
					CmsArticle a=new CmsArticle();
					a.setCategoryId(Long.parseLong(id));
					List<CmsArticle> artList = cmsArticleService.select(a);
					request.setAttribute("artList", artList);
				}
			} catch (Exception e) {

			}
			return "pc/ajax-travel";
		}
	/**
	 * 产品详细信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/travelDetails/{id}")
	public ModelAndView travelDetails(@PathVariable("id") Long id,HttpServletRequest request)throws Exception{
		ModelAndView mav=new ModelAndView();
				CmsArticle article=cmsArticleService.selectByPrimaryKey(id);
				mav.addObject("article", article);
		mav.setViewName("pc/travelDetail");
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
		
		CmsCategory CmsCategory=new CmsCategory();
		CmsCategory.setIsshow(1);
		CmsCategory.setModule("localClass");
		List<CmsCategory> cateList=cmsCategoryService.select(CmsCategory);
		model.addAttribute("cateList",cateList);
		
		PageInfo<CmsArticle> artList=cmsArticleService.selectPage(pageNum, pageSize, CmsArticle);
		model.addAttribute("page", artList);
		
		return "pc/subject";
	}
	
}

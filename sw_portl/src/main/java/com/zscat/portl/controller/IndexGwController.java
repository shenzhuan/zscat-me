package com.zscat.portl.controller;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.zscat.cms.model.GwInfo;
import com.zscat.cms.model.GwNav;
import com.zscat.cms.model.GwProduct;
import com.zscat.cms.service.GwNavService;
import com.zscat.cms.service.GwProductService;
import com.zscat.cms.service.GwProductTypeService;
import com.zscat.cms.service.GwInfoService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

	/**
	 * 
	 * @author zs 2016-5-5 11:33:51
	 * @Email: 951449465@qq.com
	 * @version 4.0v
	 *	我的blog
	 */
@Controller
@RequestMapping("gw")
public class IndexGwController {

	@Reference(version = "1.0.0")
	private GwInfoService gwInfoService;
		@Reference(version = "1.0.0")
	private GwNavService gwNavService;
		@Reference(version = "1.0.0")
	private GwProductService gwProductService;
		@Reference(version = "1.0.0")
	private GwProductTypeService gwProductTypeService;
	/**
	 * 请求主页
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/index")
	public ModelAndView index()throws Exception{
		ModelAndView mav=new ModelAndView();
		//首页顶部导航
		GwNav nav=new GwNav();
		nav.setDisplay(true);
		nav.setType("0");
		mav.addObject("navList", gwNavService.select(nav));
		//项目
		PageInfo<GwProduct> page = gwProductService.selectPage(1, 9, null);
		for(GwProduct blog:page.getList()){
			List<String> imagesList=blog.getImagesList();
			String blogInfo=blog.getRemark();
			Document doc=Jsoup.parse(blogInfo);
			Elements jpgs=doc.select("img[src]"); //　查找扩展名是jpg的图片
			if(jpgs!=null && jpgs.size()>0){
			Element jpg=jpgs.get(0);
				if(jpgs!=null && jpg!=null){
					String linkHref = jpg.attr("src");
					blog.setImg(linkHref);
				}
			}
			
		}
		mav.addObject("productList", page.getList());
		//信息
		GwInfo gwInfo=new GwInfo();
		gwInfo.setDisplay(true);
		mav.addObject("infoList", gwInfoService.select(gwInfo, "addTime"));
		mav.setViewName("gw/index");
		return mav;
	}
	
	@RequestMapping(value = "productList")
	public String productList(int pageNum,int pageSize,@ModelAttribute GwProduct Product, Model model) {
		PageInfo<GwProduct> page = gwProductService.selectPage(pageNum, pageSize, Product);
		model.addAttribute("page", page);
		return "gw/productList";
	}
	/**
	 * 产品详细信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/product/{id}")
	public ModelAndView productDetails(@PathVariable("id") Long id,HttpServletRequest request)throws Exception{
		ModelAndView mav=new ModelAndView();
		GwProduct  product=gwProductService.selectByPrimaryKey(id);
		mav.addObject("product", product);
		product.setClickhit(product.getClickhit()+1); // 博客点击次数加1
		gwProductService.updateByPrimaryKeySelective(product);
		
		mav.setViewName("gw/productDetail");
		return mav;
	}
	
	@RequestMapping("/gwInfo/{id}")
	public ModelAndView gwInfoDetails(@PathVariable("id") Long id,HttpServletRequest request)throws Exception{
		ModelAndView mav=new ModelAndView();
		GwInfo info=gwInfoService.selectByPrimaryKey(id);
		mav.addObject("info", info);
		
		mav.setViewName("gw/info");
		return mav;
	}
	
	
}

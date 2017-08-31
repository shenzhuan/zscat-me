package com.zscat.shop.youhong;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zscat.shop.util.SysUserUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.zscat.shop.model.Floor;
import com.zscat.shop.model.Member;
import com.zscat.shop.model.Product;
import com.zscat.shop.model.ProductClass;
import com.zscat.shop.model.ProductType;
import com.zscat.shop.service.FloorService;
import com.zscat.shop.service.MemberService;
import com.zscat.shop.service.ProductClassService;
import com.zscat.shop.service.ProductService;
import com.zscat.shop.service.ProductTypeService;
import com.zscat.util.PasswordEncoder;

/**
	 * 
	 * @author zsCat 2016-10-31 14:01:30
	 * @Email: 951449465@qq.com
	 * @version 4.0v
	 *	商品管理
	 */
@Controller
@RequestMapping("/youhong")
public class YouhongIndexController {

	@Reference(version = "1.0.0")
	private ProductClassService ProductClassService;
	@Reference(version = "1.0.0")
	private ProductService ProductService;
	@Reference(version = "1.0.0")
	private MemberService MemberService;
	@Reference(version = "1.0.0")
	private  FloorService floorService;

	@Reference(version = "1.0.0")
	private ProductTypeService ProductTypeService;
	
	 @RequestMapping("")
	  public ModelAndView index() {
	        try {
	            ModelAndView model = new ModelAndView("/youhong/index");
	            Product goods=new Product();
	            PageInfo<Product> page = ProductService.selectPage(1, 4, goods,"orderby desc");
	            model.addObject("page", page);
	            ProductClass gc=new ProductClass();
	            gc.setParentId(1L);
	            List<ProductClass> gcList=ProductClassService.selectPage(1, 15, gc).getList();
	            model.addObject("spList", floorService.select(new Floor()));
	            model.addObject("gcList", gcList);
//	            List<Article> artList=articleService.select(new Article());
//	            model.addObject("artList", artList);
	            List<Member> useList=MemberService.select(new Member(), "no desc");
		        model.addObject("useList", useList);
		        
		        List<ProductType> typeList=ProductTypeService.select(new ProductType());
		        model.addObject("typeList", typeList);
	            return model;
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new RuntimeException("导航失败!");
	        }
	    }
	 @RequestMapping("/index")
	  public ModelAndView index1() {
	        try {
	            ModelAndView model = new ModelAndView("/youhong/home3");
	            Product goods=new Product();
	            PageInfo<Product> page = ProductService.selectPage(1, 4, goods,"orderby desc");
	            model.addObject("page", page);
	            ProductClass gc=new ProductClass();
	            gc.setParentId(1L);
	            List<ProductClass> gcList=ProductClassService.selectPage(1, 15, gc).getList();
	            model.addObject("spList", floorService.select(new Floor()));
	            model.addObject("gcList", gcList);
//	            List<Article> artList=articleService.select(new Article());
//	            model.addObject("artList", artList);
	            List<Member> useList=MemberService.select(new Member(), "no desc");
		        model.addObject("useList", useList);
		        
		        List<ProductType> typeList=ProductTypeService.select(new ProductType());
		        model.addObject("typeList", typeList);
		        
		        PageInfo<Product> xinpin = ProductService.selectPage(1, 24, goods,"create_date desc");
	            model.addObject("xinpin", xinpin);
	            return model;
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new RuntimeException("导航失败!");
	        }
	    }
		
		@RequestMapping("/goodsDetail/{id}")
		public ModelAndView goodsDetail(@PathVariable("id") Long id,HttpServletRequest req)throws Exception{
			ModelAndView mav=new ModelAndView();
			Product goods=ProductService.selectByPrimaryKey(id);
			mav.addObject("goods", goods);
			if(goods.getImgmore()!=null && goods.getImgmore().indexOf(",")>-1){
				mav.addObject("imgs", goods.getImgmore().split(","));
			}
			mav.setViewName("youhong/goodsDetail");
			goods.setClickhit(goods.getClickhit()+1);
			ProductService.updateByPrimaryKeySelective(goods);
			//查询详情商品的 其他商品
			Product p=new Product();
			p.setCreateBy(goods.getCreateBy());
			List<Product> ownGoods=ProductService.selectPage(1, 15, p, "orderby desc").getList();
			mav.addObject("ownGoods", ownGoods);

			return mav;
		}
		
		
		
		 
		/**
		 * 通过菜单类别
		 * @param id
		 * @return
		 * @throws Exception
		 */
		@RequestMapping("/goodsListBygcId/{gcId}")
		public ModelAndView goodsListBygcId(@PathVariable("gcId") Long gcId)throws Exception{
			ModelAndView mav=new ModelAndView();
			Product g=new Product();
			//g.setGcId(gcId);
			PageInfo<Product> page=ProductService.selectgoodsListByType(1, 40, g);
			mav.addObject("page", page);
			mav.setViewName("youhong/search");
			return mav;
		}
		
		@RequestMapping("/goodsListBygcTypeId/{typeId}")
		public ModelAndView goodsListBygcTypeId(@PathVariable("typeId") Long typeId)throws Exception{
			ModelAndView mav=new ModelAndView();
			Product g=new Product();
			g.setTypeid(typeId);
			PageInfo<Product> page=ProductService.selectPage(1, 40, g);
			mav.addObject("page", page);
			mav.setViewName("youhong/search");
			return mav;
		}
		@RequestMapping("/goodsList")
		public ModelAndView goodsList()throws Exception{
			ModelAndView mav=new ModelAndView();
			Product g=new Product();
			
			PageInfo<Product> page=ProductService.selectPage(1, 40, g);
			mav.addObject("page", page);
			mav.setViewName("youhong/search");
			return mav;
		}
	 @RequestMapping("/information/{createBy}")
	  public ModelAndView information(@PathVariable("createBy") Long createBy) {
		 ModelAndView model = new ModelAndView("/youhong/person/information");
        Member member=MemberService.selectByPrimaryKey(createBy);
        model.addObject("member", member);
		 return model;
	 }
	 /**
	  * 商城公告
	  * @param createBy
	  * @return
	  */
	 @RequestMapping("/newD/{id}")
	  public ModelAndView newD(@PathVariable("id") Long id) {
		 ModelAndView model = new ModelAndView("/youhong/person/blog");
//        Article article=articleService.selectByPrimaryKey(id);
//        model.addObject("article", article);
//        List<Article> articleList=articleService.select(new Article());
//        model.addObject("articleList", articleList);
		 return model;
	 }
	   /**
		 * 跳转到登录页面
		 * 
		 * @return
		 */
		@RequestMapping(value = "login", method = RequestMethod.GET)
		public String toLogin() {
			if( SysUserUtils.getSessionLoginUser() != null){
				return "redirect:/youhong";
			}
			return "/youhong/login";
		}
		
		/**
		 * 登录验证
		 * 
		 * @param username
		 * @param password
		 * @param code
		 * @return
		 */
		@RequestMapping(value = "login", method = RequestMethod.POST)
		public @ResponseBody Map<String, Object> checkLogin(String username,
				String password,  HttpServletRequest request) {

			Map<String, Object> msg = new HashMap<String, Object>();
			HttpSession session = request.getSession();
			//code = StringUtils.trim(code);
			username = StringUtils.trim(username);
			password = StringUtils.trim(password);
			//Object scode = session.getAttribute("code");
			String sessionCode = null;
//			if (scode != null)
//				sessionCode = scode.toString();
//			if (!StringUtils.equalsIgnoreCase(code, sessionCode)) {
//				msg.put("error", "验证码错误");
//				return msg;
//			}
			Member user = MemberService.checkUser(username, password);
			if (null != user) {
				session.setAttribute(SysUserUtils.SESSION_LOGIN_USER, user);
			} else {
				msg.put("error", "用户名或密码错误");
			}
			return msg;
		}
	 
		 /**
		 * 跳转到登录页面
		 * 
		 * @return
		 */
		@RequestMapping(value = "reg", method = RequestMethod.GET)
		public String reg() {
			if( SysUserUtils.getSessionLoginUser() != null){
				return "redirect:/youhong";
			}
			return "/youhong/register";
		}
	
		@RequestMapping(value = "reg", method = RequestMethod.POST)
		public @ResponseBody Map<String, Object> reg(
				@RequestParam(value = "password",required=true)String  password,
				@RequestParam(value = "email",required=false)String email,
				@RequestParam(value = "username",required=false)String username,
				@RequestParam(value = "phone",required=false)String phone,
				@RequestParam(value = "passwordRepeat",required=true)String passwordRepeat,HttpServletRequest request) {
			Map<String, Object> msg = new HashMap<String, Object>();
			if (!StringUtils.equalsIgnoreCase(password, passwordRepeat)) {
				msg.put("error", "密码不一致!");
				return msg;
			}
			String secPwd = null ;
			Member m=new Member();
			m.setAddtime(new Date());m.setStatus(1);
				m.setUsername(username);
				secPwd = PasswordEncoder.encrypt(password, username);
			
			m.setPassword(secPwd);
			m.setTruename(m.getUsername());
			try {
				int result = MemberService.insertSelective(m);
			
				HttpSession session = request.getSession();
				if (result>0) {
					session.setAttribute(SysUserUtils.SESSION_LOGIN_USER, m);
				} else {
					msg.put("error", "注册失败");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return msg;
		}
	 	/**
		 * 用户退出
		 * 
		 * @return 跳转到登录页面
		 */
		@RequestMapping("logout")
		public String logout(HttpServletRequest request) {
			request.getSession().invalidate();
			return "redirect:/youhong/login";
		}
	
		@RequestMapping("/search/{createBy}")
		  public ModelAndView search(@PathVariable("createBy") Long createBy) {
			 ModelAndView model = new ModelAndView("/youhong/search");
			 Product p=new Product();
			 p.setCreateBy(createBy);
	        PageInfo<Product> page=ProductService.selectPage(1, 15, p, "orderby desc");
	        model.addObject("page", page);
	        p.setCreateBy(2L);
	        PageInfo<Product> page1=ProductService.selectPage(1, 15, p, "orderby desc");
	        model.addObject("page1", page1);
			 return model;
		 }
		@RequestMapping("/memberList")
		  public ModelAndView memberList() {
			 ModelAndView model = new ModelAndView("/youhong/member");
			 List<Member> page=MemberService.select(new Member(), "no desc");
	        model.addObject("page", page);
			 return model;
		 }
		@RequestMapping("/type")
		  public ModelAndView type() {
			 ModelAndView model = new ModelAndView("/youhong/type");
			 ProductClass gc=new ProductClass();
	            gc.setParentId(1L);
	            List<ProductClass> gcList=ProductClassService.selectPage(1, 15, gc).getList();
	            model.addObject("gcList", gcList);
			 return model;
		 }
	 
		
		//  首页菜单-------------------
		//限时特卖
		@RequestMapping("/Limit_buy")
		  public ModelAndView Limit_buy() {
			 ModelAndView model = new ModelAndView("/youhong/Limit_buy");
			 ProductClass gc=new ProductClass();
	            gc.setParentId(1L);
	            List<ProductClass> gcList=ProductClassService.selectPage(1, 15, gc).getList();
	            model.addObject("gcList", gcList);
			 return model;
		 }
		//品牌
		@RequestMapping("/Brands")
		  public ModelAndView Brands() {
			 ModelAndView model = new ModelAndView("/youhong/Brands");
			 ProductClass gc=new ProductClass();
	            gc.setParentId(1L);
	            List<ProductClass> gcList=ProductClassService.selectPage(1, 15, gc).getList();
	            model.addObject("gcList", gcList);
			 return model;
		 }
		
		//产品列表
	@RequestMapping("/product_list")
	  public ModelAndView product_list() {
		 ModelAndView model = new ModelAndView("/youhong/product_list");
		 ProductClass gc=new ProductClass();
            gc.setParentId(1L);
            List<ProductClass> gcList=ProductClassService.selectPage(1, 15, gc).getList();
            model.addObject("gcList", gcList);
		 return model;
	 }
				
				
}

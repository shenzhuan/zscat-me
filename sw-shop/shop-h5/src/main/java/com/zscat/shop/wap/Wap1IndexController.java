package com.zscat.shop.wap;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zsCat.common.utils.AddressUtils;
import com.zsCat.common.utils.IPUtils;
import com.zsCat.common.utils.JSONSerializerUtil;
import com.zsCat.common.utils.RedisUtils;
import com.zscat.util.MemberUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
@RequestMapping("/wap")
public class Wap1IndexController extends BaseController{

	@Reference(version = "1.0.0")
	private ProductClassService ProductClassService;
	@Reference(version = "1.0.0")
	private ProductService ProductService;
	@Reference(version = "1.0.0")
	private MemberService MemberService;
	@Reference(version = "1.0.0")
	private  FloorService floorService;
	RedisUtils redisUtils = new RedisUtils();
	@Reference(version = "1.0.0")
	private ProductTypeService ProductTypeService;
	
	 @RequestMapping("")
	  public ModelAndView index(HttpServletRequest req) {
	        try {
	            ModelAndView model = new ModelAndView("/wap/index");
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
	            List<Member> useList=MemberService.select(new Member());
		        model.addObject("useList", useList);
//				model.addObject("city", AddressUtils.getCityByIp(CustomSystemUtil.INTERNET_IP));
//				model.addObject("city1", AddressUtils.getCityByIp(CustomSystemUtil.INTRANET_IP));
				if (MemberUtils.getSessionLoginUser()!=null){
					model.addObject("city", MemberUtils.getSessionLoginUser().getUsername());
				}else{
					model.addObject("city", "未登录");
				}


			//	model.addObject("city", AddressUtils.getCityByIp(IPUtils.getIp2(req)));
		        List<ProductType> typeList=ProductTypeService.select(new ProductType());
		        model.addObject("typeList", typeList);
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
			mav.setViewName("wap/goodsDetail");
			goods.setClickhit(goods.getClickhit()+1);
			ProductService.updateByPrimaryKeySelective(goods);
			//查询详情商品的 其他商品
			Product p=new Product();
			p.setCreateBy(goods.getCreateBy());
			List<Product> ownGoods=ProductService.selectPage(1, 15, p, "orderby desc").getList();
			mav.addObject("ownGoods", ownGoods);
//			String ip=IPUtils.getClientAddress(req);
//			    RedisUtils  RedisUtils=new RedisUtils();
//			    String[] properties =new String[]{"id","price","title","img"};
//				RedisUtils.hset(this.SHOPPING_HISTORY + ip ,id+"",JsonUtils.toJsonStr(goods,properties),24*60*60);
			
//			String goodsSpec = goods.getProductSpec();
//			String specName = goods.getSpecName();
//			if(specName == null || specName.equals("")){
//				return null;
//			}
//			Map<String, String> specNameMap = JsonUtils.readJsonToMap(specName);
//			Map<String, List<ProductSpecVo>> goodsSpecMap = ProductUtils.goodsSpecStrToMapList(goodsSpec);
//			ProductSpec sProductSpec=new ProductSpec();
//			sProductSpec.setProductId(id);
//			List<ProductSpec> goodsSpecs = ProductSpecService.select(sProductSpec);
//	        //规格颜色对应的图片
//	        Map<String, String> goodsColImg = ProductUtils.goodsColImgStrToMap(goods.getProductColImg());
//	        //得到该商品的所有goodsvalueId的String,以逗号分割
//	        for(int i = 0; i < goodsSpecs.size(); i++){
//	        	goodsSpecs.get(i).setSpecValueIdStr(
//	        			ProductUtils.getThisProductAllSpecValueId(
//	        					goodsSpecs.get(i).getSpecProductSpec()
//	        			)
//	        	);
//	        }
//			Map<String, Object> specmap = new HashMap<String, Object>();
//			specmap.put("goodsColImg", goodsColImg);
//			specmap.put("specname", specNameMap);
//			specmap.put("specvalue", goodsSpecMap);
//			specmap.put("goodsSpecs", goodsSpecs);
			return mav;
		}
	 @RequestMapping("/index")
	  public ModelAndView index1() {
	        try {
	            ModelAndView model = new ModelAndView("/wap/home3");
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
	 @RequestMapping("/information/{createBy}")
	  public ModelAndView information(@PathVariable("createBy") Long createBy) {
		 ModelAndView model = new ModelAndView("/wap/person/information");
        Member member=MemberService.selectByPrimaryKey(createBy);
        model.addObject("member", member);
		 return model;
	 }
	 /**
	  * 商城公告
	  * @param
	  * @return
	  */
	 @RequestMapping("/newD/{id}")
	  public ModelAndView newD(@PathVariable("id") Long id) {
		 ModelAndView model = new ModelAndView("/wap/person/blog");
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

	@RequestMapping(value = "login")
	public ModelAndView toLogin1() {
		ModelAndView model = new ModelAndView("/wap/login");
		if( this.getSessionLoginUser() != null){
			return new ModelAndView("redirect:/wap");
		}
		return model;
	}

		/**
		 * 登录验证
		 * 
		 * @param username
		 * @param password
		 * @param
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

			Member user = MemberService.checkUser(username, password);
			if (null != user) {
				redisUtils.set(request.getSession().getId(), JSONSerializerUtil.serialize(user),30*60);;
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
			if( this.getSessionLoginUser() != null){
				return "redirect:/wap";
			}
			return "/wap/register";
		}
	
		@RequestMapping(value = "reg", method = RequestMethod.POST)
		public @ResponseBody Map<String, Object> reg(
				@RequestParam(value = "password",required=true)String  password,
				@RequestParam(value = "email",required=false)String email,
				@RequestParam(value = "phone",required=false)String phone,
				@RequestParam(value = "username")String username,
				@RequestParam(value = "passwordRepeat",required=true)String passwordRepeat,HttpServletRequest request) {
			Map<String, Object> msg = new HashMap<String, Object>();
			if (!StringUtils.equalsIgnoreCase(password, passwordRepeat)) {
				msg.put("error", "密码不一致!");
				return msg;
			}
			String secPwd = null ;
			Member m=new Member();
			secPwd = PasswordEncoder.encrypt(password, username);
			m.setUsername(username);
			m.setPassword(secPwd);
			m.setTruename(m.getUsername());
			m.setPhone(phone);
			try {
				
				int result = MemberService.insertSelective(m);
				System.out.println(m.getId());
				if (result>0) {
					//redisUtils.set(request.getSession().getId(), JSONSerializerUtil.serialize(m),30*60);
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
			return "redirect:/wap/login";
		}
	
		@RequestMapping("/search/{createBy}")
		  public ModelAndView search(@PathVariable("createBy") Long createBy) {
			 ModelAndView model = new ModelAndView("/wap/search");
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
			 ModelAndView model = new ModelAndView("/wap/member");
			 List<Member> page=MemberService.select(new Member(), "no desc");
	        model.addObject("page", page);
			 return model;
		 }
		@RequestMapping("/type")
		  public ModelAndView type() {
			 ModelAndView model = new ModelAndView("/wap/type");
			 ProductClass gc=new ProductClass();
	            gc.setParentId(1L);
	            List<ProductClass> gcList=ProductClassService.selectPage(1, 15, gc).getList();
	            model.addObject("gcList", gcList);
			 return model;
		 }
		/**
		  * 分类管理
		  * @return
		  */
		 @RequestMapping("/category")
		  public ModelAndView categoty() {
			 ModelAndView model = new ModelAndView("/wap/category");
			
			 return model;
		 }
			@RequestMapping("/ajax/category")
			public String categotyList(HttpServletRequest request) {
					try {
						String id = request.getParameter("order");
						if (id != null && !id.equals("")) {
							ProductClass gc=new ProductClass();
							gc.setParentId(Long.parseLong(id));
						//	request.setAttribute("imgServer", "http://image.zscat.com");
							request.setAttribute("orderList", ProductClassService.select(gc));
						}
					} catch (Exception e) {

					}
					return "wap/ajax-category";
		}
}

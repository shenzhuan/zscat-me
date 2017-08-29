package com.zscat.shop.front;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.zscat.shop.model.Product;
import com.zscat.shop.model.Reply;
import com.zscat.shop.service.ProductService;
import com.zscat.shop.service.ReplyService;
	/**
	 * 
	 * @author zsCat 2016-10-31 13:59:18
	 * @Email: 951449465@qq.com
	 * @version 4.0v
	 *	商品管理
	 */
@Controller
@RequestMapping("front/goods")
public class ProductFrontController {

		@Reference(version = "1.0.0")
	private ProductService ProductService;
		@Reference(version = "1.0.0")
	private ReplyService ReplyService;
	
	
	@RequestMapping("/goodsDetail/{id}")
	public ModelAndView goodsDetail(@PathVariable("id") Long id,HttpServletRequest req)throws Exception{
		ModelAndView mav=new ModelAndView();
		Product goods=ProductService.selectByPrimaryKey(id);
		mav.addObject("goods", goods);
		if(goods.getImgmore()!=null && goods.getImgmore().indexOf(",")>-1){
			mav.addObject("imgs", goods.getImgmore().split(","));
		}
		mav.setViewName("mall/goodsDetail");
		goods.setClickhit(goods.getClickhit()+1);
		ProductService.updateByPrimaryKeySelective(goods);
		//查询详情商品的 其他商品
		Product p=new Product();
		p.setCreateBy(goods.getCreateBy());
		List<Product> ownGoods=ProductService.selectPage(1, 15, p, "orderby desc").getList();
		mav.addObject("ownGoods", ownGoods);
		//评论
		Reply r=new Reply();
		r.setGoodsid(id);
		List<Reply> rList=ReplyService.select(r, "createdate desc");
		mav.addObject("rList", rList);
//		String ip=IPUtils.getClientAddress(req);
//		    RedisUtils  RedisUtils=new RedisUtils();
//		    String[] properties =new String[]{"id","price","title","img"};
//			RedisUtils.hset(Constant.SHOPPING_HISTORY + ip ,id+"",JsonUtils.toJsonStr(goods,properties),24*60*60);
		
//		String goodsSpec = goods.getProductSpec();
//		String specName = goods.getSpecName();
//		if(specName == null || specName.equals("")){
//			return null;
//		}
//		Map<String, String> specNameMap = JsonUtils.readJsonToMap(specName);
//		Map<String, List<ProductSpecVo>> goodsSpecMap = ProductUtils.goodsSpecStrToMapList(goodsSpec);
//		ProductSpec sProductSpec=new ProductSpec();
//		sProductSpec.setProductId(id);
//		List<ProductSpec> goodsSpecs = ProductSpecService.select(sProductSpec);
//        //规格颜色对应的图片
//        Map<String, String> goodsColImg = ProductUtils.goodsColImgStrToMap(goods.getProductColImg());
//        //得到该商品的所有goodsvalueId的String,以逗号分割
//        for(int i = 0; i < goodsSpecs.size(); i++){
//        	goodsSpecs.get(i).setSpecValueIdStr(
//        			ProductUtils.getThisProductAllSpecValueId(
//        					goodsSpecs.get(i).getSpecProductSpec()
//        			)
//        	);
//        }
//		Map<String, Object> specmap = new HashMap<String, Object>();
//		specmap.put("goodsColImg", goodsColImg);
//		specmap.put("specname", specNameMap);
//		specmap.put("specvalue", goodsSpecMap);
//		specmap.put("goodsSpecs", goodsSpecs);
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
		mav.setViewName("mall/search");
		return mav;
	}
	
	@RequestMapping("/goodsListBygcTypeId/{typeId}")
	public ModelAndView goodsListBygcTypeId(@PathVariable("typeId") Long typeId)throws Exception{
		ModelAndView mav=new ModelAndView();
		Product g=new Product();
		g.setTypeid(typeId);
		PageInfo<Product> page=ProductService.selectPage(1, 40, g);
		mav.addObject("page", page);
		mav.setViewName("mall/search");
		return mav;
	}
	
}

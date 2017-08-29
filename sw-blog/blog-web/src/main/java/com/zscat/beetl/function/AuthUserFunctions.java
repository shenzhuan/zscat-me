package com.zscat.beetl.function;

import java.util.List;

import javax.annotation.Resource;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zscat.blog.api.model.Blog;
import com.zscat.blog.api.model.Blogger;
import com.zscat.blog.api.service.BlogService;
import com.zscat.blog.utils.SysUserUtils;
import com.zscat.conf.JbaseFunctionPackage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;


@Service("auth")
public class AuthUserFunctions implements JbaseFunctionPackage {


	@Reference(version = "1.0.0")
	private BlogService blogService;
	/**
	 * 登录用户
	* @return
	 */
	public Blogger getLoginBlogger(){
		return (Blogger) SysUserUtils.getSessionLoginUser();
	}
	
	/**
	 * 是否为超级管理员
	* @return
	 */
	public boolean isBloggerLogin(){
		return SysUserUtils.getSessionLoginUser()!=null;
	}
	public List<Blog> getArticleListByTypeId(Long id){
		Blog b=new Blog();
		b.setTypeid(id);
		List<Blog> blogList= blogService.select(b);
		for(Blog blog:blogList){
			String blogInfo=blog.getContent();
			Document doc=Jsoup.parse(blogInfo);
			Elements jpgs=doc.select("img[src]"); //　查找扩展名是jpg的图片
			if(jpgs!=null && jpgs.size()>0){
			Element jpg=jpgs.get(0);
				if(jpgs!=null && jpg!=null){
					String linkHref = jpg.attr("src");
					blog.setImg(linkHref);
				}else{
					blog.setImg("static/bbs/img/menu.png");
				}
			}
		}
		return blogList;	
	}
}

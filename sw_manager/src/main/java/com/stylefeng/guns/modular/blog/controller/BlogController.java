package com.stylefeng.guns.modular.blog.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.stylefeng.guns.common.controller.BaseController;
import com.stylefeng.guns.common.exception.BizExceptionEnum;
import com.stylefeng.guns.common.exception.BussinessException;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.common.controller.BaseController;
import com.stylefeng.guns.modular.ConvertUtils;
import com.stylefeng.guns.modular.system.warpper.NullWarpper;
import com.zscat.blog.api.model.Blog;

import com.zscat.blog.api.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 博客控制器
 *
 * @author fengshuonan
 * @Date 2017-08-28 17:30:23
 */
@Controller
@RequestMapping("/blog")
public class BlogController extends BaseController {

    @Reference(version = "1.0.0")
    private BlogService blogService;
    
    private String PREFIX = "/blog/blog/";

    /**
     * 跳转到博客首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "blog.html";
    }

    /**
     * 跳转到添加博客
     */
    @RequestMapping("/blog_add")
    public String blogAdd() {
        return PREFIX + "blog_add.html";
    }

    /**
     * 跳转到修改博客
     */
    @RequestMapping("/blog_edit/{blogId}")
    public String blogUpdate(@PathVariable Long id, Model model) {
     Blog record = blogService.selectByPrimaryKey(id);
            model.addAttribute("record",record);
        return PREFIX + "blog_edit.html";
    }

    /**
     * 获取博客列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        try {
        List<Blog> list =this.blogService.selectAll(new Blog());
        List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
        for(int i=0, n=list.size(); i<n; i++){
            Object bean = list.get(i);
            Map  map = ConvertUtils.convertBean2Map(bean);
            mapList.add(map);
        }
        return super.warpObject(new NullWarpper(mapList));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 新增博客
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Blog record) {
     return this.blogService.insertSelective(record);
    }

    /**
     * 删除博客
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Long id) {
         blogService.deleteByPrimaryKey(id);
         return SUCCESS_TIP;
    }


    /**
     * 修改博客
     */
    @RequestMapping(value = "/edit")
    @ResponseBody
    public Object update(Blog record) {
       if (ToolUtil.isEmpty(record)) {
                   throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
          }
          blogService.updateByPrimaryKeySelective(record);
         return super.SUCCESS_TIP;
    }

    /**
     * 博客详情
     */
    @RequestMapping(value = "/detail/{id}")
    @ResponseBody
    public Object detail(@PathVariable("id") Long id) {
         return blogService.selectByPrimaryKey(id);
    }
}

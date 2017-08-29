package com.stylefeng.guns.modular.blog.controller;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import com.stylefeng.guns.common.exception.BizExceptionEnum;
import com.stylefeng.guns.modular.ConvertUtils;
import com.zscat.blog.api.model.BlogLink;
import org.springframework.web.bind.annotation.RequestParam;
import com.alibaba.dubbo.config.annotation.Reference;
import com.stylefeng.guns.common.controller.BaseController;
import com.stylefeng.guns.common.exception.BussinessException;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.common.controller.BaseController;
import com.stylefeng.guns.modular.system.warpper.NullWarpper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import com.zscat.blog.api.service.BlogLinkService;

/**
 * 博客友链控制器
 *
 * @author fengshuonan
 * @Date 2017-08-28 19:23:12
 */
@Controller
@RequestMapping("/blogLink")
public class BlogLinkController extends BaseController {

   @Reference(version = "1.0.0")
    private BlogLinkService blogLinkService;
    
    private String PREFIX = "/blog/blogLink/";

    /**
     * 跳转到博客友链首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "blogLink.html";
    }

    /**
     * 跳转到添加博客友链
     */
    @RequestMapping("/blogLink_add")
    public String blogLinkAdd() {
        return PREFIX + "blogLink_add.html";
    }

    /**
     * 跳转到修改博客友链
     */
    @RequestMapping("/blogLink_edit/{id}")
    public String blogLinkUpdate(@PathVariable Long id, Model model) {
     BlogLink record = blogLinkService.selectByPrimaryKey(id);
            model.addAttribute("record",record);
        return PREFIX + "blogLink_edit.html";
    }

    /**
     * 获取博客友链列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        try {
                    List<BlogLink> list =this.blogLinkService.select(new BlogLink());
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
     * 新增博客友链
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(BlogLink record) {
     return this.blogLinkService.insertSelective(record);
    }

    /**
     * 删除博客友链
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Long id) {
         blogLinkService.deleteByPrimaryKey(id);
         return SUCCESS_TIP;
    }


    /**
     * 修改博客友链
     */
    @RequestMapping(value = "/edit")
    @ResponseBody
    public Object update(BlogLink record) {
       if (ToolUtil.isEmpty(record)) {
                   throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
          }
          blogLinkService.updateByPrimaryKeySelective(record);
         return super.SUCCESS_TIP;
    }

    /**
     * 博客友链详情
     */
    @RequestMapping(value = "/detail/{id}")
    @ResponseBody
    public Object detail(@PathVariable("id") Long id) {
         return blogLinkService.selectByPrimaryKey(id);
    }
}

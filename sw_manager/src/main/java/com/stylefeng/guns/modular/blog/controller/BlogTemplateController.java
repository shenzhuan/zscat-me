package com.stylefeng.guns.modular.blog.controller;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import com.stylefeng.guns.modular.ConvertUtils;
import com.stylefeng.guns.common.exception.BizExceptionEnum;
import com.zscat.blog.api.model.BlogTemplate;
import com.zscat.blog.api.service.BlogTemplateService;
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


/**
 * 博客模板控制器
 *
 * @author fengshuonan
 * @Date 2017-08-28 20:04:11
 */
@Controller
@RequestMapping("/blogTemplate")
public class BlogTemplateController extends BaseController {

   @Reference(version = "1.0.0")
    private BlogTemplateService blogTemplateService;
    
    private String PREFIX = "/blog/blogTemplate/";

    /**
     * 跳转到博客模板首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "blogTemplate.html";
    }

    /**
     * 跳转到添加博客模板
     */
    @RequestMapping("/blogTemplate_add")
    public String blogTemplateAdd() {
        return PREFIX + "blogTemplate_add.html";
    }

    /**
     * 跳转到修改博客模板
     */
    @RequestMapping("/blogTemplate_edit/{id}")
    public String blogTemplateUpdate(@PathVariable Long id, Model model) {
     BlogTemplate record = blogTemplateService.selectByPrimaryKey(id);
            model.addAttribute("record",record);
        return PREFIX + "blogTemplate_edit.html";
    }

    /**
     * 获取博客模板列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        try {
                    List<BlogTemplate> list =this.blogTemplateService.select(new BlogTemplate());
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
     * 新增博客模板
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(BlogTemplate record) {
     return this.blogTemplateService.insertSelective(record);
    }

    /**
     * 删除博客模板
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Long id) {
         blogTemplateService.deleteByPrimaryKey(id);
         return SUCCESS_TIP;
    }


    /**
     * 修改博客模板
     */
    @RequestMapping(value = "/edit")
    @ResponseBody
    public Object update(BlogTemplate record) {
       if (ToolUtil.isEmpty(record)) {
                   throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
          }
          blogTemplateService.updateByPrimaryKeySelective(record);
         return super.SUCCESS_TIP;
    }

    /**
     * 博客模板详情
     */
    @RequestMapping(value = "/detail/{id}")
    @ResponseBody
    public Object detail(@PathVariable("id") Long id) {
         return blogTemplateService.selectByPrimaryKey(id);
    }
}

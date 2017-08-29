package com.stylefeng.guns.modular.blog.controller;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import com.stylefeng.guns.modular.ConvertUtils;
import com.stylefeng.guns.common.exception.BizExceptionEnum;
import com.zscat.blog.api.model.Blogger;
import com.zscat.blog.api.service.BloggerService;
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
 * 博客用户控制器
 *
 * @author fengshuonan
 * @Date 2017-08-28 20:03:03
 */
@Controller
@RequestMapping("/blogger")
public class BloggerController extends BaseController {

   @Reference(version = "1.0.0")
    private BloggerService bloggerService;
    
    private String PREFIX = "/blog/blogger/";

    /**
     * 跳转到博客用户首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "blogger.html";
    }

    /**
     * 跳转到添加博客用户
     */
    @RequestMapping("/blogger_add")
    public String bloggerAdd() {
        return PREFIX + "blogger_add.html";
    }

    /**
     * 跳转到修改博客用户
     */
    @RequestMapping("/blogger_edit/{id}")
    public String bloggerUpdate(@PathVariable Long id, Model model) {
     Blogger record = bloggerService.selectByPrimaryKey(id);
            model.addAttribute("record",record);
        return PREFIX + "blogger_edit.html";
    }

    /**
     * 获取博客用户列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        try {
                    List<Blogger> list =this.bloggerService.select(new Blogger());
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
     * 新增博客用户
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Blogger record) {
     return this.bloggerService.insertSelective(record);
    }

    /**
     * 删除博客用户
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Long id) {
         bloggerService.deleteByPrimaryKey(id);
         return SUCCESS_TIP;
    }


    /**
     * 修改博客用户
     */
    @RequestMapping(value = "/edit")
    @ResponseBody
    public Object update(Blogger record) {
       if (ToolUtil.isEmpty(record)) {
                   throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
          }
          bloggerService.updateByPrimaryKeySelective(record);
         return super.SUCCESS_TIP;
    }

    /**
     * 博客用户详情
     */
    @RequestMapping(value = "/detail/{id}")
    @ResponseBody
    public Object detail(@PathVariable("id") Long id) {
         return bloggerService.selectByPrimaryKey(id);
    }
}

package com.stylefeng.guns.modular.blog.controller;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import com.stylefeng.guns.modular.ConvertUtils;
import com.stylefeng.guns.common.exception.BizExceptionEnum;
import com.zscat.blog.api.model.BlogType;
import com.zscat.blog.api.service.BlogTypeService;
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
 * 博客类型控制器
 *
 * @author fengshuonan
 * @Date 2017-08-28 20:03:26
 */
@Controller
@RequestMapping("/blogType")
public class BlogTypeController extends BaseController {

   @Reference(version = "1.0.0")
    private BlogTypeService blogTypeService;
    
    private String PREFIX = "/blog/blogType/";

    /**
     * 跳转到博客类型首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "blogType.html";
    }

    /**
     * 跳转到添加博客类型
     */
    @RequestMapping("/blogType_add")
    public String blogTypeAdd() {
        return PREFIX + "blogType_add.html";
    }

    /**
     * 跳转到修改博客类型
     */
    @RequestMapping("/blogType_edit/{id}")
    public String blogTypeUpdate(@PathVariable Long id, Model model) {
     BlogType record = blogTypeService.selectByPrimaryKey(id);
            model.addAttribute("record",record);
        return PREFIX + "blogType_edit.html";
    }

    /**
     * 获取博客类型列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        try {
                    List<BlogType> list =this.blogTypeService.select(new BlogType());
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
     * 新增博客类型
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(BlogType record) {
     return this.blogTypeService.insertSelective(record);
    }

    /**
     * 删除博客类型
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Long id) {
         blogTypeService.deleteByPrimaryKey(id);
         return SUCCESS_TIP;
    }


    /**
     * 修改博客类型
     */
    @RequestMapping(value = "/edit")
    @ResponseBody
    public Object update(BlogType record) {
       if (ToolUtil.isEmpty(record)) {
                   throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
          }
          blogTypeService.updateByPrimaryKeySelective(record);
         return super.SUCCESS_TIP;
    }

    /**
     * 博客类型详情
     */
    @RequestMapping(value = "/detail/{id}")
    @ResponseBody
    public Object detail(@PathVariable("id") Long id) {
         return blogTypeService.selectByPrimaryKey(id);
    }
}

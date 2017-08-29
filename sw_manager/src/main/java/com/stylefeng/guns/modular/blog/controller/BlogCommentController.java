package com.stylefeng.guns.modular.blog.controller;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import com.stylefeng.guns.modular.ConvertUtils;
import com.stylefeng.guns.common.exception.BizExceptionEnum;
import com.zscat.blog.api.model.BlogComment;
import com.zscat.blog.api.service.BlogCommentService;
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
 * 博客评论控制器
 *
 * @author fengshuonan
 * @Date 2017-08-28 20:04:28
 */
@Controller
@RequestMapping("/blogComment")
public class BlogCommentController extends BaseController {

   @Reference(version = "1.0.0")
    private BlogCommentService blogCommentService;
    
    private String PREFIX = "/blog/blogComment/";

    /**
     * 跳转到博客评论首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "blogComment.html";
    }

    /**
     * 跳转到添加博客评论
     */
    @RequestMapping("/blogComment_add")
    public String blogCommentAdd() {
        return PREFIX + "blogComment_add.html";
    }

    /**
     * 跳转到修改博客评论
     */
    @RequestMapping("/blogComment_edit/{id}")
    public String blogCommentUpdate(@PathVariable Long id, Model model) {
     BlogComment record = blogCommentService.selectByPrimaryKey(id);
            model.addAttribute("record",record);
        return PREFIX + "blogComment_edit.html";
    }

    /**
     * 获取博客评论列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        try {
                    List<BlogComment> list =this.blogCommentService.select(new BlogComment());
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
     * 新增博客评论
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(BlogComment record) {
     return this.blogCommentService.insertSelective(record);
    }

    /**
     * 删除博客评论
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Long id) {
         blogCommentService.deleteByPrimaryKey(id);
         return SUCCESS_TIP;
    }


    /**
     * 修改博客评论
     */
    @RequestMapping(value = "/edit")
    @ResponseBody
    public Object update(BlogComment record) {
       if (ToolUtil.isEmpty(record)) {
                   throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
          }
          blogCommentService.updateByPrimaryKeySelective(record);
         return super.SUCCESS_TIP;
    }

    /**
     * 博客评论详情
     */
    @RequestMapping(value = "/detail/{id}")
    @ResponseBody
    public Object detail(@PathVariable("id") Long id) {
         return blogCommentService.selectByPrimaryKey(id);
    }
}

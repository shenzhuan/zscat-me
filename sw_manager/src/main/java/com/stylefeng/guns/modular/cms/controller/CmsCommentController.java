package com.stylefeng.guns.modular.cms.controller;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import com.stylefeng.guns.modular.ConvertUtils;
import com.stylefeng.guns.common.exception.BizExceptionEnum;
import com.zscat.cms.model.CmsComment;
import com.zscat.cms.service.CmsCommentService;
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
 * 评论控制器
 *
 * @author fengshuonan
 * @Date 2017-08-28 20:24:41
 */
@Controller
@RequestMapping("/cmsComment")
public class CmsCommentController extends BaseController {

   @Reference(version = "1.0.0")
    private CmsCommentService cmsCommentService;
    
    private String PREFIX = "/cms/cmsComment/";

    /**
     * 跳转到评论首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "cmsComment.html";
    }

    /**
     * 跳转到添加评论
     */
    @RequestMapping("/cmsComment_add")
    public String cmsCommentAdd() {
        return PREFIX + "cmsComment_add.html";
    }

    /**
     * 跳转到修改评论
     */
    @RequestMapping("/cmsComment_edit/{id}")
    public String cmsCommentUpdate(@PathVariable Long id, Model model) {
     CmsComment record = cmsCommentService.selectByPrimaryKey(id);
            model.addAttribute("record",record);
        return PREFIX + "cmsComment_edit.html";
    }

    /**
     * 获取评论列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        try {
                    List<CmsComment> list =this.cmsCommentService.select(new CmsComment());
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
     * 新增评论
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(CmsComment record) {
     return this.cmsCommentService.insertSelective(record);
    }

    /**
     * 删除评论
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Long id) {
         cmsCommentService.deleteByPrimaryKey(id);
         return SUCCESS_TIP;
    }


    /**
     * 修改评论
     */
    @RequestMapping(value = "/edit")
    @ResponseBody
    public Object update(CmsComment record) {
       if (ToolUtil.isEmpty(record)) {
                   throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
          }
          cmsCommentService.updateByPrimaryKeySelective(record);
         return super.SUCCESS_TIP;
    }

    /**
     * 评论详情
     */
    @RequestMapping(value = "/detail/{id}")
    @ResponseBody
    public Object detail(@PathVariable("id") Long id) {
         return cmsCommentService.selectByPrimaryKey(id);
    }
}

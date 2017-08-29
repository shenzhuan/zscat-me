package com.stylefeng.guns.modular.cms.controller;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import com.stylefeng.guns.modular.ConvertUtils;
import com.stylefeng.guns.common.exception.BizExceptionEnum;
import com.zscat.cms.model.CmsArticle;
import com.zscat.cms.service.CmsArticleService;
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
 * 内容控制器
 *
 * @author fengshuonan
 * @Date 2017-08-28 20:24:03
 */
@Controller
@RequestMapping("/cmsArticle")
public class CmsArticleController extends BaseController {

   @Reference(version = "1.0.0")
    private CmsArticleService cmsArticleService;
    
    private String PREFIX = "/cms/cmsArticle/";

    /**
     * 跳转到内容首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "cmsArticle.html";
    }

    /**
     * 跳转到添加内容
     */
    @RequestMapping("/cmsArticle_add")
    public String cmsArticleAdd() {
        return PREFIX + "cmsArticle_add.html";
    }

    /**
     * 跳转到修改内容
     */
    @RequestMapping("/cmsArticle_edit/{id}")
    public String cmsArticleUpdate(@PathVariable Long id, Model model) {
     CmsArticle record = cmsArticleService.selectByPrimaryKey(id);
            model.addAttribute("record",record);
        return PREFIX + "cmsArticle_edit.html";
    }

    /**
     * 获取内容列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        try {
                    List<CmsArticle> list =this.cmsArticleService.select(new CmsArticle());
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
     * 新增内容
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(CmsArticle record) {
     return this.cmsArticleService.insertSelective(record);
    }

    /**
     * 删除内容
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Long id) {
         cmsArticleService.deleteByPrimaryKey(id);
         return SUCCESS_TIP;
    }


    /**
     * 修改内容
     */
    @RequestMapping(value = "/edit")
    @ResponseBody
    public Object update(CmsArticle record) {
       if (ToolUtil.isEmpty(record)) {
                   throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
          }
          cmsArticleService.updateByPrimaryKeySelective(record);
         return super.SUCCESS_TIP;
    }

    /**
     * 内容详情
     */
    @RequestMapping(value = "/detail/{id}")
    @ResponseBody
    public Object detail(@PathVariable("id") Long id) {
         return cmsArticleService.selectByPrimaryKey(id);
    }
}

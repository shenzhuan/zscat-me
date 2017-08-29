package com.stylefeng.guns.modular.cms.controller;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import com.stylefeng.guns.modular.ConvertUtils;
import com.stylefeng.guns.common.exception.BizExceptionEnum;
import com.zscat.cms.model.CmsCategory;
import com.zscat.cms.service.CmsCategoryService;
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
 * 类别控制器
 *
 * @author fengshuonan
 * @Date 2017-08-28 20:24:21
 */
@Controller
@RequestMapping("/cmsCategory")
public class CmsCategoryController extends BaseController {

   @Reference(version = "1.0.0")
    private CmsCategoryService cmsCategoryService;
    
    private String PREFIX = "/cms/cmsCategory/";

    /**
     * 跳转到类别首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "cmsCategory.html";
    }

    /**
     * 跳转到添加类别
     */
    @RequestMapping("/cmsCategory_add")
    public String cmsCategoryAdd() {
        return PREFIX + "cmsCategory_add.html";
    }

    /**
     * 跳转到修改类别
     */
    @RequestMapping("/cmsCategory_edit/{id}")
    public String cmsCategoryUpdate(@PathVariable Long id, Model model) {
     CmsCategory record = cmsCategoryService.selectByPrimaryKey(id);
            model.addAttribute("record",record);
        return PREFIX + "cmsCategory_edit.html";
    }

    /**
     * 获取类别列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        try {
                    List<CmsCategory> list =this.cmsCategoryService.select(new CmsCategory());
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
     * 新增类别
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(CmsCategory record) {
     return this.cmsCategoryService.insertSelective(record);
    }

    /**
     * 删除类别
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Long id) {
         cmsCategoryService.deleteByPrimaryKey(id);
         return SUCCESS_TIP;
    }


    /**
     * 修改类别
     */
    @RequestMapping(value = "/edit")
    @ResponseBody
    public Object update(CmsCategory record) {
       if (ToolUtil.isEmpty(record)) {
                   throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
          }
          cmsCategoryService.updateByPrimaryKeySelective(record);
         return super.SUCCESS_TIP;
    }

    /**
     * 类别详情
     */
    @RequestMapping(value = "/detail/{id}")
    @ResponseBody
    public Object detail(@PathVariable("id") Long id) {
         return cmsCategoryService.selectByPrimaryKey(id);
    }
}

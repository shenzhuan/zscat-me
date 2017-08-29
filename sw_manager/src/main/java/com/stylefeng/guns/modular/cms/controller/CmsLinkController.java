package com.stylefeng.guns.modular.cms.controller;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import com.stylefeng.guns.modular.ConvertUtils;
import com.stylefeng.guns.common.exception.BizExceptionEnum;
import com.zscat.cms.model.CmsLink;
import com.zscat.cms.service.CmsLinkService;
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
 * 友链控制器
 *
 * @author fengshuonan
 * @Date 2017-08-28 20:24:58
 */
@Controller
@RequestMapping("/cmsLink")
public class CmsLinkController extends BaseController {

   @Reference(version = "1.0.0")
    private CmsLinkService cmsLinkService;
    
    private String PREFIX = "/cms/cmsLink/";

    /**
     * 跳转到友链首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "cmsLink.html";
    }

    /**
     * 跳转到添加友链
     */
    @RequestMapping("/cmsLink_add")
    public String cmsLinkAdd() {
        return PREFIX + "cmsLink_add.html";
    }

    /**
     * 跳转到修改友链
     */
    @RequestMapping("/cmsLink_edit/{id}")
    public String cmsLinkUpdate(@PathVariable Long id, Model model) {
     CmsLink record = cmsLinkService.selectByPrimaryKey(id);
            model.addAttribute("record",record);
        return PREFIX + "cmsLink_edit.html";
    }

    /**
     * 获取友链列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        try {
                    List<CmsLink> list =this.cmsLinkService.select(new CmsLink());
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
     * 新增友链
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(CmsLink record) {
     return this.cmsLinkService.insertSelective(record);
    }

    /**
     * 删除友链
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Long id) {
         cmsLinkService.deleteByPrimaryKey(id);
         return SUCCESS_TIP;
    }


    /**
     * 修改友链
     */
    @RequestMapping(value = "/edit")
    @ResponseBody
    public Object update(CmsLink record) {
       if (ToolUtil.isEmpty(record)) {
                   throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
          }
          cmsLinkService.updateByPrimaryKeySelective(record);
         return super.SUCCESS_TIP;
    }

    /**
     * 友链详情
     */
    @RequestMapping(value = "/detail/{id}")
    @ResponseBody
    public Object detail(@PathVariable("id") Long id) {
         return cmsLinkService.selectByPrimaryKey(id);
    }
}

package com.stylefeng.guns.modular.cms.controller;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import com.stylefeng.guns.modular.ConvertUtils;
import com.stylefeng.guns.common.exception.BizExceptionEnum;
import com.zscat.cms.model.CmsSite;
import com.zscat.cms.service.CmsSiteService;
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
 * 站点控制器
 *
 * @author fengshuonan
 * @Date 2017-08-28 20:25:12
 */
@Controller
@RequestMapping("/cmsSite")
public class CmsSiteController extends BaseController {

   @Reference(version = "1.0.0")
    private CmsSiteService cmsSiteService;
    
    private String PREFIX = "/cms/cmsSite/";

    /**
     * 跳转到站点首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "cmsSite.html";
    }

    /**
     * 跳转到添加站点
     */
    @RequestMapping("/cmsSite_add")
    public String cmsSiteAdd() {
        return PREFIX + "cmsSite_add.html";
    }

    /**
     * 跳转到修改站点
     */
    @RequestMapping("/cmsSite_edit/{id}")
    public String cmsSiteUpdate(@PathVariable Long id, Model model) {
     CmsSite record = cmsSiteService.selectByPrimaryKey(id);
            model.addAttribute("record",record);
        return PREFIX + "cmsSite_edit.html";
    }

    /**
     * 获取站点列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        try {
                    List<CmsSite> list =this.cmsSiteService.select(new CmsSite());
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
     * 新增站点
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(CmsSite record) {
     return this.cmsSiteService.insertSelective(record);
    }

    /**
     * 删除站点
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Long id) {
         cmsSiteService.deleteByPrimaryKey(id);
         return SUCCESS_TIP;
    }


    /**
     * 修改站点
     */
    @RequestMapping(value = "/edit")
    @ResponseBody
    public Object update(CmsSite record) {
       if (ToolUtil.isEmpty(record)) {
                   throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
          }
          cmsSiteService.updateByPrimaryKeySelective(record);
         return super.SUCCESS_TIP;
    }

    /**
     * 站点详情
     */
    @RequestMapping(value = "/detail/{id}")
    @ResponseBody
    public Object detail(@PathVariable("id") Long id) {
         return cmsSiteService.selectByPrimaryKey(id);
    }
}

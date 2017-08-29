package com.stylefeng.guns.modular.weixin.controller;

import com.stylefeng.guns.common.controller.BaseController;
import com.stylefeng.guns.common.exception.BizExceptionEnum;
import com.stylefeng.guns.common.exception.BussinessException;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.warpper.NullWarpper;
import com.stylefeng.guns.modular.weixin.model.WxMessage;
import com.stylefeng.guns.modular.weixin.service.IWxmessageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 微信公众号控制器
 *
 * @author fengshuonan
 * @Date 2017-08-01 19:37:40
 */
@Controller
@RequestMapping("/wxmessage")
public class WxmessageController extends BaseController {

    private String PREFIX = "/weixin/wxmessage/";

    @Resource
    private IWxmessageService wxmessageService;
    /**
     * 跳转到微信公众号首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "wxmessage.html";
    }

    /**
     * 跳转到添加微信公众号
     */
    @RequestMapping("/wxmessage_add")
    public String wxmessageAdd() {
        return PREFIX + "wxmessage_add.html";
    }

    /**
     * 跳转到修改微信公众号
     */
    @RequestMapping("/wxmessage_update/{id}")
    public String wxmessageUpdate(@PathVariable Long id, Model model) {
        WxMessage record = wxmessageService.selectById(id);
        model.addAttribute("record",record);
        return PREFIX + "wxmessage_edit.html";
    }

    /**
     * 获取微信公众号列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        List<Map<String, Object>> list = this.wxmessageService.selectMaps(null);
        return super.warpObject(new NullWarpper(list));
    }

    /**
     * 新增微信公众号
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(WxMessage record) {
        return this.wxmessageService.insert(record);
    }

    /**
     * 删除微信公众号
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Long id) {
        wxmessageService.deleteById(id);
        return SUCCESS_TIP;
    }


    /**
     * 修改微信公众号
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(WxMessage record) {
        if (ToolUtil.isEmpty(record)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        wxmessageService.updateById(record);
        return super.SUCCESS_TIP;
    }

    /**
     * 微信公众号详情
     */
    @RequestMapping(value = "/detail/{id}")
    @ResponseBody
    public Object detail(@PathVariable("id") Long id) {
        return wxmessageService.selectById(id);
    }
}

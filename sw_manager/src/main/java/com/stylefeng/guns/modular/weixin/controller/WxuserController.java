package com.stylefeng.guns.modular.weixin.controller;

import com.stylefeng.guns.common.controller.BaseController;
import com.stylefeng.guns.core.datascope.DataScope;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.modular.system.warpper.DeptWarpper;
import com.stylefeng.guns.modular.system.warpper.NullWarpper;
import com.stylefeng.guns.modular.system.warpper.UserWarpper;
import com.stylefeng.guns.modular.weixin.service.IWxuserService;
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
 * 微信后台控制器
 *
 * @author fengshuonan
 * @Date 2017-08-01 19:04:49
 */
@Controller
@RequestMapping("/wxuser")
public class WxuserController extends BaseController {

    private String PREFIX = "/weixin/wxuser/";
    @Resource
    private IWxuserService wxuserService;
    /**
     * 跳转到微信后台首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "wxuser.html";
    }

    /**
     * 跳转到添加微信后台
     */
    @RequestMapping("/wxuser_add")
    public String wxuserAdd() {
        return PREFIX + "wxuser_add.html";
    }

    /**
     * 跳转到修改微信后台
     */
    @RequestMapping("/wxuser_update/{wxuserId}")
    public String wxuserUpdate(@PathVariable Integer wxuserId, Model model) {
        return PREFIX + "wxuser_edit.html";
    }

    /**
     * 获取微信后台列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
            List<Map<String, Object>> list = this.wxuserService.selectMaps(null);
            return super.warpObject(new NullWarpper(list));
    }

    /**
     * 新增微信后台
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add() {
        return super.SUCCESS_TIP;
    }

    /**
     * 删除微信后台
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Long id) {
        wxuserService.deleteById(id);
        return SUCCESS_TIP;
    }


    /**
     * 修改微信后台
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update() {
        return super.SUCCESS_TIP;
    }

    /**
     * 微信后台详情
     */
    @RequestMapping(value = "/detail")
    @ResponseBody
    public Object detail() {
        return null;
    }
}

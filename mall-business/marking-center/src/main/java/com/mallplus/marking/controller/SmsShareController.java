package com.mallplus.marking.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mallplus.common.annotation.SysLog;
import com.mallplus.common.entity.sms.SmsShare;
import com.mallplus.marking.service.ISmsShareService;
import com.mallplus.common.utils.CommonResult;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 分享列表 前端控制器
 * </p>
 *
 * @author zscat
 * @since 2019-10-17
 */
@Slf4j
@RestController
@RequestMapping("/sms/smsShare")
public class SmsShareController {
    @Resource
    private ISmsShareService smsShareService;

    @SysLog(MODULE = "sms", REMARK = "根据条件查询分享列表")
    @ApiOperation("根据条件查询分享列表")
    @GetMapping(value = "/list")
    public Object getSmsCouponByPage(SmsShare entity,
                                     @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                     @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        try {
            return new CommonResult().success(smsShareService.page(new Page<SmsShare>(pageNum, pageSize), new QueryWrapper<>(entity)));
        } catch (Exception e) {
            log.error("根据条件查询所有优惠卷表列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }


}


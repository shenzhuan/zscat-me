package com.mallplus.marking.controller;

import com.mallplus.common.utils.CommonResult;



import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import com.mallplus.common.entity.sms.SmsFlashPromotionLog;
import com.mallplus.marking.service.ISmsFlashPromotionLogService;
import com.mallplus.common.utils.ValidatorUtils;
import com.mallplus.common.annotation.SysLog;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 限时购通知记录
 * </p>
 *
 * @author zscat
 * @since 2019-04-19
 */
@Slf4j
@RestController
@Api(tags = "SmsFlashPromotionLogController", description = "限时购通知记录管理")
@RequestMapping("/marking/SmsFlashPromotionLog")
public class SmsFlashPromotionLogController {
    @Resource
    private ISmsFlashPromotionLogService ISmsFlashPromotionLogService;

    @SysLog(MODULE = "sms", REMARK = "根据条件查询所有限时购通知记录列表")
    @ApiOperation("根据条件查询所有限时购通知记录列表")
    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority('marking:SmsFlashPromotionLog:read')")
    public Object getSmsFlashPromotionLogByPage(SmsFlashPromotionLog entity,
                                                @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        try {
            return new CommonResult().success(ISmsFlashPromotionLogService.page(new Page<SmsFlashPromotionLog>(pageNum, pageSize), new QueryWrapper<>(entity)));
        } catch (Exception e) {
            log.error("根据条件查询所有限时购通知记录列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "sms", REMARK = "保存限时购通知记录")
    @ApiOperation("保存限时购通知记录")
    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('marking:SmsFlashPromotionLog:create')")
    public Object saveSmsFlashPromotionLog(@RequestBody SmsFlashPromotionLog entity) {
        try {
            if (ISmsFlashPromotionLogService.save(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("保存限时购通知记录：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "sms", REMARK = "更新限时购通知记录")
    @ApiOperation("更新限时购通知记录")
    @PostMapping(value = "/update/{id}")
    @PreAuthorize("hasAuthority('marking:SmsFlashPromotionLog:update')")
    public Object updateSmsFlashPromotionLog(@RequestBody SmsFlashPromotionLog entity) {
        try {
            if (ISmsFlashPromotionLogService.updateById(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("更新限时购通知记录：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "sms", REMARK = "删除限时购通知记录")
    @ApiOperation("删除限时购通知记录")
    @GetMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('marking:SmsFlashPromotionLog:delete')")
    public Object deleteSmsFlashPromotionLog(@ApiParam("限时购通知记录id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("限时购通知记录id");
            }
            if (ISmsFlashPromotionLogService.removeById(id)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("删除限时购通知记录：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "sms", REMARK = "给限时购通知记录分配限时购通知记录")
    @ApiOperation("查询限时购通知记录明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('marking:SmsFlashPromotionLog:read')")
    public Object getSmsFlashPromotionLogById(@ApiParam("限时购通知记录id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("限时购通知记录id");
            }
            SmsFlashPromotionLog coupon = ISmsFlashPromotionLogService.getById(id);
            return new CommonResult().success(coupon);
        } catch (Exception e) {
            log.error("查询限时购通知记录明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }

    }

    @ApiOperation(value = "批量删除限时购通知记录")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.POST)
    @ResponseBody
    @SysLog(MODULE = "pms", REMARK = "批量删除限时购通知记录")
    @PreAuthorize("hasAuthority('marking:SmsFlashPromotionLog:delete')")
    public Object deleteBatch(@RequestParam("ids") List<Long> ids) {
        boolean count = ISmsFlashPromotionLogService.removeByIds(ids);
        if (count) {
            return new CommonResult().success(count);
        } else {
            return new CommonResult().failed();
        }
    }

}

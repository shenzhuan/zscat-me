package com.mallplus.order.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mallplus.common.annotation.SysLog;

import com.mallplus.common.entity.oms.OmsPayments;
import com.mallplus.common.utils.CommonResult;
import com.mallplus.common.utils.ValidatorUtils;
import com.mallplus.order.service.IOmsPaymentsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author mallplus
 * @date 2019-12-07
 * 支付配置
 */
@Slf4j
@Api(tags = "oms", description = "支付配置")
@RestController
@RequestMapping("/oms/omsPayments")
public class OmsPaymentsController {

    @Resource
    private com.mallplus.order.service.IOmsPaymentsService IOmsPaymentsService;

    @SysLog(MODULE = "oms", REMARK = "根据条件查询所有支付配置列表")
    @ApiOperation("根据条件查询所有支付配置列表")
    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority('oms:omsPayments:read')")
    public Object getOmsPaymentsByPage(OmsPayments entity,
                                       @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                       @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        try {
            return new CommonResult().success(IOmsPaymentsService.page(new Page<OmsPayments>(pageNum, pageSize), new QueryWrapper<>(entity)));
        } catch (Exception e) {
            log.error("根据条件查询所有支付配置列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "oms", REMARK = "保存支付配置")
    @ApiOperation("保存支付配置")
    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('oms:omsPayments:create')")
    public Object saveOmsPayments(@RequestBody OmsPayments entity) {
        try {

            if (IOmsPaymentsService.save(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("保存支付配置：%s", e.getMessage(), e);
            return new CommonResult().failed(e.getMessage());
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "oms", REMARK = "更新支付配置")
    @ApiOperation("更新支付配置")
    @PostMapping(value = "/update/{id}")
    @PreAuthorize("hasAuthority('oms:omsPayments:update')")
    public Object updateOmsPayments(@RequestBody OmsPayments entity) {
        try {
            if (IOmsPaymentsService.updateById(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("更新支付配置：%s", e.getMessage(), e);
            return new CommonResult().failed(e.getMessage());
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "oms", REMARK = "删除支付配置")
    @ApiOperation("删除支付配置")
    @GetMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('oms:omsPayments:delete')")
    public Object deleteOmsPayments(@ApiParam("支付配置id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("支付配置id");
            }
            if (IOmsPaymentsService.removeById(id)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("删除支付配置：%s", e.getMessage(), e);
            return new CommonResult().failed(e.getMessage());
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "oms", REMARK = "给支付配置分配支付配置")
    @ApiOperation("查询支付配置明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('oms:omsPayments:read')")
    public Object getOmsPaymentsById(@ApiParam("支付配置id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("支付配置id");
            }
            OmsPayments coupon = IOmsPaymentsService.getById(id);
            return new CommonResult().success(coupon);
        } catch (Exception e) {
            log.error("查询支付配置明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }

    }

    @ApiOperation(value = "批量删除支付配置")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.GET)
    @SysLog(MODULE = "oms", REMARK = "批量删除支付配置")
    @PreAuthorize("hasAuthority('oms:omsPayments:delete')")
    public Object deleteBatch(@RequestParam("ids") List<Long> ids) {
        boolean count = IOmsPaymentsService.removeByIds(ids);
        if (count) {
            return new CommonResult().success(count);
        } else {
            return new CommonResult().failed();
        }
    }


}



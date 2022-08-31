package com.mallplus.order.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mallplus.common.annotation.SysLog;
import com.mallplus.common.entity.oms.OmsOrderReturnReason;
import com.mallplus.order.service.IOmsOrderReturnReasonService;
import com.mallplus.common.utils.CommonResult;
import com.mallplus.common.utils.ValidatorUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 退货原因表
 * </p>
 *
 * @author zscat
 * @since 2019-04-19
 */
@Slf4j
@RestController
@Api(tags = "OmsOrderReturnReasonController", description = "退货原因表管理")
@RequestMapping("/oms/OmsOrderReturnReason")
public class OmsOrderReturnReasonController {
    @Resource
    private IOmsOrderReturnReasonService IOmsOrderReturnReasonService;

    @SysLog(MODULE = "oms", REMARK = "根据条件查询所有退货原因表列表")
    @ApiOperation("根据条件查询所有退货原因表列表")
    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority('oms:OmsOrderReturnReason:read')")
    public Object getOmsOrderReturnReasonByPage(OmsOrderReturnReason entity,
                                                @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        try {
            return new CommonResult().success(IOmsOrderReturnReasonService.page(new Page<OmsOrderReturnReason>(pageNum, pageSize), new QueryWrapper<>(entity)));
        } catch (Exception e) {
            log.error("根据条件查询所有退货原因表列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "oms", REMARK = "保存退货原因表")
    @ApiOperation("保存退货原因表")
    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('oms:OmsOrderReturnReason:create')")
    public Object saveOmsOrderReturnReason(@RequestBody OmsOrderReturnReason entity) {
        try {
            if (IOmsOrderReturnReasonService.save(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("保存退货原因表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "oms", REMARK = "更新退货原因表")
    @ApiOperation("更新退货原因表")
    @PostMapping(value = "/update/{id}")
    @PreAuthorize("hasAuthority('oms:OmsOrderReturnReason:update')")
    public Object updateOmsOrderReturnReason(@RequestBody OmsOrderReturnReason entity) {
        try {
            if (IOmsOrderReturnReasonService.updateById(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("更新退货原因表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "oms", REMARK = "删除退货原因表")
    @ApiOperation("删除退货原因表")
    @GetMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('oms:OmsOrderReturnReason:delete')")
    public Object deleteOmsOrderReturnReason(@ApiParam("退货原因表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("退货原因表id");
            }
            if (IOmsOrderReturnReasonService.removeById(id)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("删除退货原因表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "oms", REMARK = "给退货原因表分配退货原因表")
    @ApiOperation("查询退货原因表明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('oms:OmsOrderReturnReason:read')")
    public Object getOmsOrderReturnReasonById(@ApiParam("退货原因表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("退货原因表id");
            }
            OmsOrderReturnReason coupon = IOmsOrderReturnReasonService.getById(id);
            return new CommonResult().success(coupon);
        } catch (Exception e) {
            log.error("查询退货原因表明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }

    }

    @ApiOperation(value = "批量删除退货原因表")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.POST)
    @ResponseBody
    @SysLog(MODULE = "pms", REMARK = "批量删除退货原因表")
    @PreAuthorize("hasAuthority('oms:OmsOrderReturnReason:delete')")
    public Object deleteBatch(@RequestParam("ids") List<Long> ids) {
        boolean count = IOmsOrderReturnReasonService.removeByIds(ids);
        if (count) {
            return new CommonResult().success(count);
        } else {
            return new CommonResult().failed();
        }
    }
    @SysLog(MODULE = "oms", REMARK = "获取所有收货地址")
    @ApiOperation("修改退货原因启用状态")
    @RequestMapping(value = "/update/status", method = RequestMethod.POST)
    @ResponseBody
    public Object updateStatus(@RequestParam(value = "status") Integer status,
                               @RequestParam("ids") List<Long> ids) {
        int count = IOmsOrderReturnReasonService.updateStatus(ids, status);
        if (count > 0) {
            return new CommonResult().success(count);
        }
        return new CommonResult().failed();
    }
}

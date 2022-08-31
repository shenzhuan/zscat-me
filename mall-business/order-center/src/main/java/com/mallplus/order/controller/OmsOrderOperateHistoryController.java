package com.mallplus.order.controller;

import com.mallplus.common.utils.CommonResult;



import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import com.mallplus.common.entity.oms.OmsOrderOperateHistory;
import com.mallplus.order.service.IOmsOrderOperateHistoryService;
import com.mallplus.common.utils.ValidatorUtils;
import com.mallplus.common.annotation.SysLog;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 订单操作历史记录
 * </p>
 *
 * @author zscat
 * @since 2019-04-19
 */
@Slf4j
@RestController
@Api(tags = "OmsOrderOperateHistoryController", description = "订单操作历史记录管理")
@RequestMapping("/oms/OmsOrderOperateHistory")
public class OmsOrderOperateHistoryController {
    @Resource
    private IOmsOrderOperateHistoryService IOmsOrderOperateHistoryService;

    @SysLog(MODULE = "oms", REMARK = "根据条件查询所有订单操作历史记录列表")
    @ApiOperation("根据条件查询所有订单操作历史记录列表")
    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority('oms:OmsOrderOperateHistory:read')")
    public Object getOmsOrderOperateHistoryByPage(OmsOrderOperateHistory entity,
                                                  @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                  @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        try {
            return new CommonResult().success(IOmsOrderOperateHistoryService.page(new Page<OmsOrderOperateHistory>(pageNum, pageSize), new QueryWrapper<>(entity).orderByDesc("create_time")));
        } catch (Exception e) {
            log.error("根据条件查询所有订单操作历史记录列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "oms", REMARK = "保存订单操作历史记录")
    @ApiOperation("保存订单操作历史记录")
    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('oms:OmsOrderOperateHistory:create')")
    public Object saveOmsOrderOperateHistory(@RequestBody OmsOrderOperateHistory entity) {
        try {
            if (IOmsOrderOperateHistoryService.save(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("保存订单操作历史记录：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "oms", REMARK = "更新订单操作历史记录")
    @ApiOperation("更新订单操作历史记录")
    @PostMapping(value = "/update/{id}")
    @PreAuthorize("hasAuthority('oms:OmsOrderOperateHistory:update')")
    public Object updateOmsOrderOperateHistory(@RequestBody OmsOrderOperateHistory entity) {
        try {
            if (IOmsOrderOperateHistoryService.updateById(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("更新订单操作历史记录：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "oms", REMARK = "删除订单操作历史记录")
    @ApiOperation("删除订单操作历史记录")
    @GetMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('oms:OmsOrderOperateHistory:delete')")
    public Object deleteOmsOrderOperateHistory(@ApiParam("订单操作历史记录id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("订单操作历史记录id");
            }
            if (IOmsOrderOperateHistoryService.removeById(id)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("删除订单操作历史记录：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "oms", REMARK = "给订单操作历史记录分配订单操作历史记录")
    @ApiOperation("查询订单操作历史记录明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('oms:OmsOrderOperateHistory:read')")
    public Object getOmsOrderOperateHistoryById(@ApiParam("订单操作历史记录id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("订单操作历史记录id");
            }
            OmsOrderOperateHistory coupon = IOmsOrderOperateHistoryService.getById(id);
            return new CommonResult().success(coupon);
        } catch (Exception e) {
            log.error("查询订单操作历史记录明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }

    }

    @ApiOperation(value = "批量删除订单操作历史记录")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.POST)
    @ResponseBody
    @SysLog(MODULE = "pms", REMARK = "批量删除订单操作历史记录")
    @PreAuthorize("hasAuthority('oms:OmsOrderOperateHistory:delete')")
    public Object deleteBatch(@RequestParam("ids") List<Long> ids) {
        boolean count = IOmsOrderOperateHistoryService.removeByIds(ids);
        if (count) {
            return new CommonResult().success(count);
        } else {
            return new CommonResult().failed();
        }
    }

}

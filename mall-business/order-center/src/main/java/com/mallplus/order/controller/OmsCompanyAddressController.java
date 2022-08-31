package com.mallplus.order.controller;

import com.mallplus.common.utils.CommonResult;



import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import com.mallplus.common.entity.oms.OmsCompanyAddress;
import com.mallplus.order.service.IOmsCompanyAddressService;
import com.mallplus.common.utils.ValidatorUtils;
import com.mallplus.common.annotation.SysLog;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 公司收发货地址表
 * </p>
 *
 * @author zscat
 * @since 2019-04-19
 */
@Slf4j
@RestController
@Api(tags = "OmsCompanyAddressController", description = "公司收发货地址表管理")
@RequestMapping("/oms/OmsCompanyAddress")
public class OmsCompanyAddressController {
    @Resource
    private IOmsCompanyAddressService IOmsCompanyAddressService;

    @SysLog(MODULE = "oms", REMARK = "根据条件查询所有公司收发货地址表列表")
    @ApiOperation("根据条件查询所有公司收发货地址表列表")
    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority('oms:OmsCompanyAddress:read')")
    public Object getOmsCompanyAddressByPage(OmsCompanyAddress entity,
                                             @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                             @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        try {
            return new CommonResult().success(IOmsCompanyAddressService.page(new Page<OmsCompanyAddress>(pageNum, pageSize), new QueryWrapper<>(entity)));
        } catch (Exception e) {
            log.error("根据条件查询所有公司收发货地址表列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "oms", REMARK = "保存公司收发货地址表")
    @ApiOperation("保存公司收发货地址表")
    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('oms:OmsCompanyAddress:create')")
    public Object saveOmsCompanyAddress(@RequestBody OmsCompanyAddress entity) {
        try {
            if (IOmsCompanyAddressService.save(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("保存公司收发货地址表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "oms", REMARK = "更新公司收发货地址表")
    @ApiOperation("更新公司收发货地址表")
    @PostMapping(value = "/update/{id}")
    @PreAuthorize("hasAuthority('oms:OmsCompanyAddress:update')")
    public Object updateOmsCompanyAddress(@RequestBody OmsCompanyAddress entity) {
        try {
            if (IOmsCompanyAddressService.updateById(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("更新公司收发货地址表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "oms", REMARK = "删除公司收发货地址表")
    @ApiOperation("删除公司收发货地址表")
    @GetMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('oms:OmsCompanyAddress:delete')")
    public Object deleteOmsCompanyAddress(@ApiParam("公司收发货地址表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("公司收发货地址表id");
            }
            if (IOmsCompanyAddressService.removeById(id)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("删除公司收发货地址表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "oms", REMARK = "给公司收发货地址表分配公司收发货地址表")
    @ApiOperation("查询公司收发货地址表明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('oms:OmsCompanyAddress:read')")
    public Object getOmsCompanyAddressById(@ApiParam("公司收发货地址表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("公司收发货地址表id");
            }
            OmsCompanyAddress coupon = IOmsCompanyAddressService.getById(id);
            return new CommonResult().success(coupon);
        } catch (Exception e) {
            log.error("查询公司收发货地址表明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }

    }

    @ApiOperation(value = "批量删除公司收发货地址表")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.POST)
    @ResponseBody
    @SysLog(MODULE = "pms", REMARK = "批量删除公司收发货地址表")
    @PreAuthorize("hasAuthority('oms:OmsCompanyAddress:delete')")
    public Object deleteBatch(@RequestParam("ids") List<Long> ids) {
        boolean count = IOmsCompanyAddressService.removeByIds(ids);
        if (count) {
            return new CommonResult().success(count);
        } else {
            return new CommonResult().failed();
        }
    }

}

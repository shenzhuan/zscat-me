package com.mallplus.goods.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mallplus.common.annotation.SysLog;
import com.mallplus.common.utils.CommonResult;
import com.mallplus.common.utils.ValidatorUtils;
import com.mallplus.common.entity.pms.PmsProductFullReduction;
import com.mallplus.goods.service.IPmsProductFullReductionService;
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
 * 产品满减表(只针对同商品)
 * </p>
 *
 * @author zscat
 * @since 2019-04-19
 */
@Slf4j
@RestController
@Api(tags = "PmsProductFullReductionController", description = "产品满减表(只针对同商品)管理")
@RequestMapping("/pms/PmsProductFullReduction")
public class PmsProductFullReductionController {
    @Resource
    private IPmsProductFullReductionService IPmsProductFullReductionService;

    @SysLog(MODULE = "pms", REMARK = "根据条件查询所有产品满减表(只针对同商品)列表")
    @ApiOperation("根据条件查询所有产品满减表(只针对同商品)列表")
    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority('pms:PmsProductFullReduction:read')")
    public Object getPmsProductFullReductionByPage(PmsProductFullReduction entity,
                                                   @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                   @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        try {
            return new CommonResult().success(IPmsProductFullReductionService.page(new Page<PmsProductFullReduction>(pageNum, pageSize), new QueryWrapper<>(entity)));
        } catch (Exception e) {
            log.error("根据条件查询所有产品满减表(只针对同商品)列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "pms", REMARK = "保存产品满减表(只针对同商品)")
    @ApiOperation("保存产品满减表(只针对同商品)")
    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('pms:PmsProductFullReduction:create')")
    public Object savePmsProductFullReduction(@RequestBody PmsProductFullReduction entity) {
        try {
            if (IPmsProductFullReductionService.save(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("保存产品满减表(只针对同商品)：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "pms", REMARK = "更新产品满减表(只针对同商品)")
    @ApiOperation("更新产品满减表(只针对同商品)")
    @PostMapping(value = "/update/{id}")
    @PreAuthorize("hasAuthority('pms:PmsProductFullReduction:update')")
    public Object updatePmsProductFullReduction(@RequestBody PmsProductFullReduction entity) {
        try {
            if (IPmsProductFullReductionService.updateById(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("更新产品满减表(只针对同商品)：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "pms", REMARK = "删除产品满减表(只针对同商品)")
    @ApiOperation("删除产品满减表(只针对同商品)")
    @GetMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('pms:PmsProductFullReduction:delete')")
    public Object deletePmsProductFullReduction(@ApiParam("产品满减表(只针对同商品)id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("产品满减表(只针对同商品)id");
            }
            if (IPmsProductFullReductionService.removeById(id)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("删除产品满减表(只针对同商品)：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "pms", REMARK = "给产品满减表(只针对同商品)分配产品满减表(只针对同商品)")
    @ApiOperation("查询产品满减表(只针对同商品)明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('pms:PmsProductFullReduction:read')")
    public Object getPmsProductFullReductionById(@ApiParam("产品满减表(只针对同商品)id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("产品满减表(只针对同商品)id");
            }
            PmsProductFullReduction coupon = IPmsProductFullReductionService.getById(id);
            return new CommonResult().success(coupon);
        } catch (Exception e) {
            log.error("查询产品满减表(只针对同商品)明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }

    }

    @ApiOperation(value = "批量删除产品满减表(只针对同商品)")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.POST)
    @ResponseBody
    @SysLog(MODULE = "pms", REMARK = "批量删除产品满减表(只针对同商品)")
    @PreAuthorize("hasAuthority('pms:PmsProductFullReduction:delete')")
    public Object deleteBatch(@RequestParam("ids") List<Long> ids) {
        boolean count = IPmsProductFullReductionService.removeByIds(ids);
        if (count) {
            return new CommonResult().success(count);
        } else {
            return new CommonResult().failed();
        }
    }

}

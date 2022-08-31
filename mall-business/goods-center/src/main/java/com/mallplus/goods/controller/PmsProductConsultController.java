package com.mallplus.goods.controller;

import com.mallplus.common.utils.CommonResult;



import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import com.mallplus.common.entity.pms.PmsProductConsult;
import com.mallplus.goods.service.IPmsProductConsultService;
import com.mallplus.common.utils.ValidatorUtils;
import com.mallplus.common.annotation.SysLog;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 产品咨询表
 * </p>
 *
 * @author zscat
 * @since 2019-04-19
 */
@Slf4j
@RestController
@Api(tags = "PmsProductConsultController", description = "产品咨询表管理")
@RequestMapping("/pms/PmsProductConsult")
public class PmsProductConsultController {
    @Resource
    private IPmsProductConsultService IPmsProductConsultService;

    @SysLog(MODULE = "pms", REMARK = "根据条件查询所有产品咨询表列表")
    @ApiOperation("根据条件查询所有产品咨询表列表")
    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority('pms:PmsProductConsult:read')")
    public Object getPmsProductConsultByPage(PmsProductConsult entity,
                                             @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                             @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        try {
            return new CommonResult().success(IPmsProductConsultService.page(new Page<PmsProductConsult>(pageNum, pageSize), new QueryWrapper<>(entity).orderByDesc("id")));
        } catch (Exception e) {
            log.error("根据条件查询所有产品咨询表列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "pms", REMARK = "保存产品咨询表")
    @ApiOperation("保存产品咨询表")
    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('pms:PmsProductConsult:create')")
    public Object savePmsProductConsult(@RequestBody PmsProductConsult entity) {
        try {
            if (IPmsProductConsultService.save(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("保存产品咨询表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "pms", REMARK = "更新产品咨询表")
    @ApiOperation("更新产品咨询表")
    @PostMapping(value = "/update/{id}")
    @PreAuthorize("hasAuthority('pms:PmsProductConsult:update')")
    public Object updatePmsProductConsult(@RequestBody PmsProductConsult entity) {
        try {
            if (IPmsProductConsultService.updateById(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("更新产品咨询表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "pms", REMARK = "删除产品咨询表")
    @ApiOperation("删除产品咨询表")
    @GetMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('pms:PmsProductConsult:delete')")
    public Object deletePmsProductConsult(@ApiParam("产品咨询表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("产品咨询表id");
            }
            if (IPmsProductConsultService.removeById(id)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("删除产品咨询表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "pms", REMARK = "给产品咨询表分配产品咨询表")
    @ApiOperation("查询产品咨询表明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('pms:PmsProductConsult:read')")
    public Object getPmsProductConsultById(@ApiParam("产品咨询表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("产品咨询表id");
            }
            PmsProductConsult coupon = IPmsProductConsultService.getById(id);
            return new CommonResult().success(coupon);
        } catch (Exception e) {
            log.error("查询产品咨询表明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }

    }

    @ApiOperation(value = "批量删除产品咨询表")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.POST)
    @ResponseBody
    @SysLog(MODULE = "pms", REMARK = "批量删除产品咨询表")
    @PreAuthorize("hasAuthority('pms:PmsProductConsult:delete')")
    public Object deleteBatch(@RequestParam("ids") List<Long> ids) {
        boolean count = IPmsProductConsultService.removeByIds(ids);
        if (count) {
            return new CommonResult().success(count);
        } else {
            return new CommonResult().failed();
        }
    }

}

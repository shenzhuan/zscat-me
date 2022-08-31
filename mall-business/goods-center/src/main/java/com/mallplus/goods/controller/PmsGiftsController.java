package com.mallplus.goods.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.mallplus.common.annotation.SysLog;
import com.mallplus.common.entity.pms.PmsGifts;
import com.mallplus.common.utils.CommonResult;
import com.mallplus.common.utils.ValidatorUtils;
import com.mallplus.goods.service.IPmsGiftsService;
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
 * 帮助表
 * </p>
 *
 * @author zscat
 * @since 2019-07-07
 */
@Slf4j
@RestController
@Api(tags = "PmsGiftsController", description = "帮助表管理")
@RequestMapping("/pms/PmsGifts")
public class PmsGiftsController {
    @Resource
    private IPmsGiftsService IPmsGiftsService;

    @SysLog(MODULE = "pms", REMARK = "根据条件查询所有帮助表列表")
    @ApiOperation("根据条件查询所有帮助表列表")
    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority('pms:PmsGifts:read')")
    public Object getPmsGiftsByPage(PmsGifts entity,
                                    @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                    @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        try {
            if (ValidatorUtils.notEmpty(entity.getTitle())) {
                return new CommonResult().success(IPmsGiftsService.page(new Page<PmsGifts>(pageNum, pageSize), new QueryWrapper<PmsGifts>(new PmsGifts()).like("title" , entity.getTitle())));
            }
            return new CommonResult().success(IPmsGiftsService.page(new Page<PmsGifts>(pageNum, pageSize), new QueryWrapper<>(entity)));

        } catch (Exception e) {
            log.error("根据条件查询所有帮助表列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "pms", REMARK = "保存帮助表")
    @ApiOperation("保存帮助表")
    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('pms:PmsGifts:create')")
    public Object savePmsGifts(@RequestBody PmsGifts entity) {
        try {
            if (IPmsGiftsService.save(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("保存帮助表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "pms", REMARK = "更新帮助表")
    @ApiOperation("更新帮助表")
    @PostMapping(value = "/update/{id}")
    @PreAuthorize("hasAuthority('pms:PmsGifts:update')")
    public Object updatePmsGifts(@RequestBody PmsGifts entity) {
        try {
            if (IPmsGiftsService.updateById(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("更新帮助表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "pms", REMARK = "删除帮助表")
    @ApiOperation("删除帮助表")
    @GetMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('pms:PmsGifts:delete')")
    public Object deletePmsGifts(@ApiParam("帮助表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("帮助表id");
            }
            if (IPmsGiftsService.removeById(id)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("删除帮助表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "pms", REMARK = "给帮助表分配帮助表")
    @ApiOperation("查询帮助表明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('pms:PmsGifts:read')")
    public Object getPmsGiftsById(@ApiParam("帮助表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("帮助表id");
            }
            PmsGifts coupon = IPmsGiftsService.getById(id);
            return new CommonResult().success(coupon);
        } catch (Exception e) {
            log.error("查询帮助表明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }

    }

    @ApiOperation(value = "批量删除帮助表")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.GET)
    @ResponseBody
    @SysLog(MODULE = "pms", REMARK = "批量删除帮助表")
    @PreAuthorize("hasAuthority('pms:PmsGifts:delete')")
    public Object deleteBatch(@RequestParam("ids") List<Long> ids) {
        boolean count = IPmsGiftsService.removeByIds(ids);
        if (count) {
            return new CommonResult().success(count);
        } else {
            return new CommonResult().failed();
        }
    }

}

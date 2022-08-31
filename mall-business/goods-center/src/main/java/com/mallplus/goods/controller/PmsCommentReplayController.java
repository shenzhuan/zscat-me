package com.mallplus.goods.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mallplus.common.annotation.SysLog;
import com.mallplus.common.entity.pms.PmsCommentReplay;
import com.mallplus.goods.service.IPmsCommentReplayService;
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
 * 产品评价回复表
 * </p>
 *
 * @author zscat
 * @since 2019-04-19
 */
@Slf4j
@RestController
@Api(tags = "PmsCommentReplayController", description = "产品评价回复表管理")
@RequestMapping("/pms/PmsCommentReplay")
public class PmsCommentReplayController {
    @Resource
    private IPmsCommentReplayService IPmsCommentReplayService;

    @SysLog(MODULE = "pms", REMARK = "根据条件查询所有产品评价回复表列表")
    @ApiOperation("根据条件查询所有产品评价回复表列表")
    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority('pms:PmsCommentReplay:read')")
    public Object getPmsCommentReplayByPage(PmsCommentReplay entity,
                                            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        try {
            return new CommonResult().success(IPmsCommentReplayService.page(new Page<PmsCommentReplay>(pageNum, pageSize), new QueryWrapper<>(entity)));
        } catch (Exception e) {
            log.error("根据条件查询所有产品评价回复表列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "pms", REMARK = "保存产品评价回复表")
    @ApiOperation("保存产品评价回复表")
    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('pms:PmsCommentReplay:create')")
    public Object savePmsCommentReplay(@RequestBody PmsCommentReplay entity) {
        try {
            if (IPmsCommentReplayService.save(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("保存产品评价回复表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "pms", REMARK = "更新产品评价回复表")
    @ApiOperation("更新产品评价回复表")
    @PostMapping(value = "/update/{id}")
    @PreAuthorize("hasAuthority('pms:PmsCommentReplay:update')")
    public Object updatePmsCommentReplay(@RequestBody PmsCommentReplay entity) {
        try {
            if (IPmsCommentReplayService.updateById(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("更新产品评价回复表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "pms", REMARK = "删除产品评价回复表")
    @ApiOperation("删除产品评价回复表")
    @GetMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('pms:PmsCommentReplay:delete')")
    public Object deletePmsCommentReplay(@ApiParam("产品评价回复表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("产品评价回复表id");
            }
            if (IPmsCommentReplayService.removeById(id)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("删除产品评价回复表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "pms", REMARK = "给产品评价回复表分配产品评价回复表")
    @ApiOperation("查询产品评价回复表明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('pms:PmsCommentReplay:read')")
    public Object getPmsCommentReplayById(@ApiParam("产品评价回复表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("产品评价回复表id");
            }
            PmsCommentReplay coupon = IPmsCommentReplayService.getById(id);
            return new CommonResult().success(coupon);
        } catch (Exception e) {
            log.error("查询产品评价回复表明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }

    }

    @ApiOperation(value = "批量删除产品评价回复表")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.POST)
    @ResponseBody
    @SysLog(MODULE = "pms", REMARK = "批量删除产品评价回复表")
    @PreAuthorize("hasAuthority('pms:PmsCommentReplay:delete')")
    public Object deleteBatch(@RequestParam("ids") List<Long> ids) {
        boolean count = IPmsCommentReplayService.removeByIds(ids);
        if (count) {
            return new CommonResult().success(count);
        } else {
            return new CommonResult().failed();
        }
    }

}

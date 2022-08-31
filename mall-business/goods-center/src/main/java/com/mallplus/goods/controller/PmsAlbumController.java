package com.mallplus.goods.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mallplus.common.annotation.SysLog;
import com.mallplus.common.utils.CommonResult;
import com.mallplus.common.utils.ValidatorUtils;
import com.mallplus.common.entity.pms.PmsAlbum;
import com.mallplus.goods.service.IPmsAlbumService;
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
 * 相册表
 * </p>
 *
 * @author zscat
 * @since 2019-04-19
 */
@Slf4j
@RestController
@Api(tags = "PmsAlbumController", description = "相册表管理")
@RequestMapping("/pms/PmsAlbum")
public class PmsAlbumController {
    @Resource
    private IPmsAlbumService IPmsAlbumService;

    @SysLog(MODULE = "pms", REMARK = "根据条件查询所有相册表列表")
    @ApiOperation("根据条件查询所有相册表列表")
    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority('pms:PmsAlbum:read')")
    public Object getPmsAlbumByPage(PmsAlbum entity,
                                    @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                    @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        try {
            return new CommonResult().success(IPmsAlbumService.page(new Page<PmsAlbum>(pageNum, pageSize), new QueryWrapper<>(entity)));
        } catch (Exception e) {
            log.error("根据条件查询所有相册表列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "pms", REMARK = "保存相册表")
    @ApiOperation("保存相册表")
    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('pms:PmsAlbum:create')")
    public Object savePmsAlbum(@RequestBody PmsAlbum entity) {
        try {
            if (IPmsAlbumService.save(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("保存相册表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "pms", REMARK = "更新相册表")
    @ApiOperation("更新相册表")
    @PostMapping(value = "/update/{id}")
    @PreAuthorize("hasAuthority('pms:PmsAlbum:update')")
    public Object updatePmsAlbum(@RequestBody PmsAlbum entity) {
        try {
            if (IPmsAlbumService.updateById(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("更新相册表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "pms", REMARK = "删除相册表")
    @ApiOperation("删除相册表")
    @GetMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('pms:PmsAlbum:delete')")
    public Object deletePmsAlbum(@ApiParam("相册表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("相册表id");
            }
            if (IPmsAlbumService.removeById(id)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("删除相册表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "pms", REMARK = "给相册表分配相册表")
    @ApiOperation("查询相册表明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('pms:PmsAlbum:read')")
    public Object getPmsAlbumById(@ApiParam("相册表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("相册表id");
            }
            PmsAlbum coupon = IPmsAlbumService.getById(id);
            return new CommonResult().success(coupon);
        } catch (Exception e) {
            log.error("查询相册表明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }

    }

    @ApiOperation(value = "批量删除相册表")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.POST)
    @ResponseBody
    @SysLog(MODULE = "pms", REMARK = "批量删除相册表")
    @PreAuthorize("hasAuthority('pms:PmsAlbum:delete')")
    public Object deleteBatch(@RequestParam("ids") List<Long> ids) {
        boolean count = IPmsAlbumService.removeByIds(ids);
        if (count) {
            return new CommonResult().success(count);
        } else {
            return new CommonResult().failed();
        }
    }

}

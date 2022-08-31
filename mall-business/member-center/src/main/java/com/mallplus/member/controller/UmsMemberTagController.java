package com.mallplus.member.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mallplus.common.annotation.SysLog;
import com.mallplus.common.entity.ums.UmsMemberTag;
import com.mallplus.member.service.IUmsMemberTagService;
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
 * 用户标签表
 * </p>
 *
 * @author zscat
 * @since 2019-04-19
 */
@Slf4j
@RestController
@Api(tags = "UmsMemberTagController", description = "用户标签表管理")
@RequestMapping("/ums/UmsMemberTag")
public class UmsMemberTagController {
    @Resource
    private IUmsMemberTagService IUmsMemberTagService;

    @SysLog(MODULE = "ums", REMARK = "根据条件查询所有用户标签表列表")
    @ApiOperation("根据条件查询所有用户标签表列表")
    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority('ums:UmsMemberTag:read')")
    public Object getUmsMemberTagByPage(UmsMemberTag entity,
                                        @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                        @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        try {
            return new CommonResult().success(IUmsMemberTagService.page(new Page<UmsMemberTag>(pageNum, pageSize), new QueryWrapper<>(entity)));
        } catch (Exception e) {
            log.error("根据条件查询所有用户标签表列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "ums", REMARK = "保存用户标签表")
    @ApiOperation("保存用户标签表")
    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('ums:UmsMemberTag:create')")
    public Object saveUmsMemberTag(@RequestBody UmsMemberTag entity) {
        try {
            if (IUmsMemberTagService.save(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("保存用户标签表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "ums", REMARK = "更新用户标签表")
    @ApiOperation("更新用户标签表")
    @PostMapping(value = "/update/{id}")
    @PreAuthorize("hasAuthority('ums:UmsMemberTag:update')")
    public Object updateUmsMemberTag(@RequestBody UmsMemberTag entity) {
        try {
            if (IUmsMemberTagService.updateById(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("更新用户标签表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "ums", REMARK = "删除用户标签表")
    @ApiOperation("删除用户标签表")
    @GetMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('ums:UmsMemberTag:delete')")
    public Object deleteUmsMemberTag(@ApiParam("用户标签表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("用户标签表id");
            }
            if (IUmsMemberTagService.removeById(id)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("删除用户标签表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "ums", REMARK = "给用户标签表分配用户标签表")
    @ApiOperation("查询用户标签表明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('ums:UmsMemberTag:read')")
    public Object getUmsMemberTagById(@ApiParam("用户标签表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("用户标签表id");
            }
            UmsMemberTag coupon = IUmsMemberTagService.getById(id);
            return new CommonResult().success(coupon);
        } catch (Exception e) {
            log.error("查询用户标签表明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }

    }

    @ApiOperation(value = "批量删除用户标签表")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.POST)
    @ResponseBody
    @SysLog(MODULE = "pms", REMARK = "批量删除用户标签表")
    @PreAuthorize("hasAuthority('ums:UmsMemberTag:delete')")
    public Object deleteBatch(@RequestParam("ids") List<Long> ids) {
        boolean count = IUmsMemberTagService.removeByIds(ids);
        if (count) {
            return new CommonResult().success(count);
        } else {
            return new CommonResult().failed();
        }
    }

}

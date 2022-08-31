package com.mallplus.member.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mallplus.common.annotation.SysLog;
import com.mallplus.common.utils.CommonResult;
import com.mallplus.common.utils.ValidatorUtils;
import com.mallplus.common.entity.ums.UmsMemberLevel;
import com.mallplus.member.service.IUmsMemberLevelService;
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
 * 会员等级表
 * </p>
 *
 * @author zscat
 * @since 2019-04-19
 */
@Slf4j
@RestController
@Api(tags = "UmsMemberLevelController", description = "会员等级表管理")
@RequestMapping("/ums/UmsMemberLevel")
public class UmsMemberLevelController {
    @Resource
    private IUmsMemberLevelService IUmsMemberLevelService;

    @SysLog(MODULE = "ums", REMARK = "根据条件查询所有会员等级表列表")
    @ApiOperation("根据条件查询所有会员等级表列表")
    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority('ums:UmsMemberLevel:read')")
    public Object getUmsMemberLevelByPage(UmsMemberLevel entity,
                                          @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                          @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        try {
            return new CommonResult().success(IUmsMemberLevelService.page(new Page<UmsMemberLevel>(pageNum, pageSize), new QueryWrapper<>(entity)));
        } catch (Exception e) {
            log.error("根据条件查询所有会员等级表列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "ums", REMARK = "保存会员等级表")
    @ApiOperation("保存会员等级表")
    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('ums:UmsMemberLevel:create')")
    public Object saveUmsMemberLevel(@RequestBody UmsMemberLevel entity) {
        try {
            if (IUmsMemberLevelService.save(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("保存会员等级表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "ums", REMARK = "更新会员等级表")
    @ApiOperation("更新会员等级表")
    @PostMapping(value = "/update/{id}")
    @PreAuthorize("hasAuthority('ums:UmsMemberLevel:update')")
    public Object updateUmsMemberLevel(@RequestBody UmsMemberLevel entity) {
        try {
            if (IUmsMemberLevelService.updateById(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("更新会员等级表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "ums", REMARK = "删除会员等级表")
    @ApiOperation("删除会员等级表")
    @GetMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('ums:UmsMemberLevel:delete')")
    public Object deleteUmsMemberLevel(@ApiParam("会员等级表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("会员等级表id");
            }
            if (IUmsMemberLevelService.removeById(id)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("删除会员等级表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "ums", REMARK = "给会员等级表分配会员等级表")
    @ApiOperation("查询会员等级表明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('ums:UmsMemberLevel:read')")
    public Object getUmsMemberLevelById(@ApiParam("会员等级表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("会员等级表id");
            }
            UmsMemberLevel coupon = IUmsMemberLevelService.getById(id);
            return new CommonResult().success(coupon);
        } catch (Exception e) {
            log.error("查询会员等级表明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }

    }

    @ApiOperation(value = "批量删除会员等级表")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.POST)
    @ResponseBody
    @SysLog(MODULE = "pms", REMARK = "批量删除会员等级表")
    @PreAuthorize("hasAuthority('ums:UmsMemberLevel:delete')")
    public Object deleteBatch(@RequestParam("ids") List<Long> ids) {
        boolean count = IUmsMemberLevelService.removeByIds(ids);
        if (count) {
            return new CommonResult().success(count);
        } else {
            return new CommonResult().failed();
        }
    }

}

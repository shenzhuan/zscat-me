package com.mallplus.member.controller;

import com.mallplus.common.utils.CommonResult;



import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import com.mallplus.common.entity.ums.UmsMemberMemberTagRelation;
import com.mallplus.member.service.IUmsMemberMemberTagRelationService;
import com.mallplus.common.utils.ValidatorUtils;
import com.mallplus.common.annotation.SysLog;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 用户和标签关系表
 * </p>
 *
 * @author zscat
 * @since 2019-04-19
 */
@Slf4j
@RestController
@Api(tags = "UmsMemberMemberTagRelationController", description = "用户和标签关系表管理")
@RequestMapping("/ums/UmsMemberMemberTagRelation")
public class UmsMemberMemberTagRelationController {
    @Resource
    private IUmsMemberMemberTagRelationService IUmsMemberMemberTagRelationService;

    @SysLog(MODULE = "ums", REMARK = "根据条件查询所有用户和标签关系表列表")
    @ApiOperation("根据条件查询所有用户和标签关系表列表")
    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority('ums:UmsMemberMemberTagRelation:read')")
    public Object getUmsMemberMemberTagRelationByPage(UmsMemberMemberTagRelation entity,
                                                      @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                      @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        try {
            return new CommonResult().success(IUmsMemberMemberTagRelationService.page(new Page<UmsMemberMemberTagRelation>(pageNum, pageSize), new QueryWrapper<>(entity)));
        } catch (Exception e) {
            log.error("根据条件查询所有用户和标签关系表列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "ums", REMARK = "保存用户和标签关系表")
    @ApiOperation("保存用户和标签关系表")
    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('ums:UmsMemberMemberTagRelation:create')")
    public Object saveUmsMemberMemberTagRelation(@RequestBody UmsMemberMemberTagRelation entity) {
        try {
            if (IUmsMemberMemberTagRelationService.save(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("保存用户和标签关系表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "ums", REMARK = "更新用户和标签关系表")
    @ApiOperation("更新用户和标签关系表")
    @PostMapping(value = "/update/{id}")
    @PreAuthorize("hasAuthority('ums:UmsMemberMemberTagRelation:update')")
    public Object updateUmsMemberMemberTagRelation(@RequestBody UmsMemberMemberTagRelation entity) {
        try {
            if (IUmsMemberMemberTagRelationService.updateById(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("更新用户和标签关系表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "ums", REMARK = "删除用户和标签关系表")
    @ApiOperation("删除用户和标签关系表")
    @GetMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('ums:UmsMemberMemberTagRelation:delete')")
    public Object deleteUmsMemberMemberTagRelation(@ApiParam("用户和标签关系表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("用户和标签关系表id");
            }
            if (IUmsMemberMemberTagRelationService.removeById(id)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("删除用户和标签关系表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "ums", REMARK = "给用户和标签关系表分配用户和标签关系表")
    @ApiOperation("查询用户和标签关系表明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('ums:UmsMemberMemberTagRelation:read')")
    public Object getUmsMemberMemberTagRelationById(@ApiParam("用户和标签关系表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("用户和标签关系表id");
            }
            UmsMemberMemberTagRelation coupon = IUmsMemberMemberTagRelationService.getById(id);
            return new CommonResult().success(coupon);
        } catch (Exception e) {
            log.error("查询用户和标签关系表明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }

    }

    @ApiOperation(value = "批量删除用户和标签关系表")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.POST)
    @ResponseBody
    @SysLog(MODULE = "pms", REMARK = "批量删除用户和标签关系表")
    @PreAuthorize("hasAuthority('ums:UmsMemberMemberTagRelation:delete')")
    public Object deleteBatch(@RequestParam("ids") List<Long> ids) {
        boolean count = IUmsMemberMemberTagRelationService.removeByIds(ids);
        if (count) {
            return new CommonResult().success(count);
        } else {
            return new CommonResult().failed();
        }
    }

}

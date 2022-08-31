package com.mallplus.member.controller;

import com.mallplus.common.utils.CommonResult;



import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import com.mallplus.common.entity.ums.UmsMemberStatisticsInfo;
import com.mallplus.member.service.IUmsMemberStatisticsInfoService;
import com.mallplus.common.utils.ValidatorUtils;
import com.mallplus.common.annotation.SysLog;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 会员统计信息
 * </p>
 *
 * @author zscat
 * @since 2019-04-19
 */
@Slf4j
@RestController
@Api(tags = "UmsMemberStatisticsInfoController", description = "会员统计信息管理")
@RequestMapping("/ums/UmsMemberStatisticsInfo")
public class UmsMemberStatisticsInfoController {
    @Resource
    private IUmsMemberStatisticsInfoService IUmsMemberStatisticsInfoService;

    @SysLog(MODULE = "ums", REMARK = "根据条件查询所有会员统计信息列表")
    @ApiOperation("根据条件查询所有会员统计信息列表")
    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority('ums:UmsMemberStatisticsInfo:read')")
    public Object getUmsMemberStatisticsInfoByPage(UmsMemberStatisticsInfo entity,
                                                   @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                   @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        try {
            return new CommonResult().success(IUmsMemberStatisticsInfoService.page(new Page<UmsMemberStatisticsInfo>(pageNum, pageSize), new QueryWrapper<>(entity)));
        } catch (Exception e) {
            log.error("根据条件查询所有会员统计信息列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "ums", REMARK = "保存会员统计信息")
    @ApiOperation("保存会员统计信息")
    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('ums:UmsMemberStatisticsInfo:create')")
    public Object saveUmsMemberStatisticsInfo(@RequestBody UmsMemberStatisticsInfo entity) {
        try {
            if (IUmsMemberStatisticsInfoService.save(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("保存会员统计信息：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "ums", REMARK = "更新会员统计信息")
    @ApiOperation("更新会员统计信息")
    @PostMapping(value = "/update/{id}")
    @PreAuthorize("hasAuthority('ums:UmsMemberStatisticsInfo:update')")
    public Object updateUmsMemberStatisticsInfo(@RequestBody UmsMemberStatisticsInfo entity) {
        try {
            if (IUmsMemberStatisticsInfoService.updateById(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("更新会员统计信息：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "ums", REMARK = "删除会员统计信息")
    @ApiOperation("删除会员统计信息")
    @GetMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('ums:UmsMemberStatisticsInfo:delete')")
    public Object deleteUmsMemberStatisticsInfo(@ApiParam("会员统计信息id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("会员统计信息id");
            }
            if (IUmsMemberStatisticsInfoService.removeById(id)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("删除会员统计信息：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "ums", REMARK = "给会员统计信息分配会员统计信息")
    @ApiOperation("查询会员统计信息明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('ums:UmsMemberStatisticsInfo:read')")
    public Object getUmsMemberStatisticsInfoById(@ApiParam("会员统计信息id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("会员统计信息id");
            }
            UmsMemberStatisticsInfo coupon = IUmsMemberStatisticsInfoService.getById(id);
            return new CommonResult().success(coupon);
        } catch (Exception e) {
            log.error("查询会员统计信息明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }

    }

    @ApiOperation(value = "批量删除会员统计信息")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.POST)
    @ResponseBody
    @SysLog(MODULE = "pms", REMARK = "批量删除会员统计信息")
    @PreAuthorize("hasAuthority('ums:UmsMemberStatisticsInfo:delete')")
    public Object deleteBatch(@RequestParam("ids") List<Long> ids) {
        boolean count = IUmsMemberStatisticsInfoService.removeByIds(ids);
        if (count) {
            return new CommonResult().success(count);
        } else {
            return new CommonResult().failed();
        }
    }

}

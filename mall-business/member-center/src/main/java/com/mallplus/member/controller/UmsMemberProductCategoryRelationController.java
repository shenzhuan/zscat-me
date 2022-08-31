package com.mallplus.member.controller;

import com.mallplus.common.utils.CommonResult;



import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import com.mallplus.common.entity.ums.UmsMemberProductCategoryRelation;
import com.mallplus.member.service.IUmsMemberProductCategoryRelationService;
import com.mallplus.common.utils.ValidatorUtils;
import com.mallplus.common.annotation.SysLog;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 会员与产品分类关系表（用户喜欢的分类）
 * </p>
 *
 * @author zscat
 * @since 2019-04-19
 */
@Slf4j
@RestController
@Api(tags = "UmsMemberProductCategoryRelationController", description = "会员与产品分类关系表（用户喜欢的分类）管理")
@RequestMapping("/ums/UmsMemberProductCategoryRelation")
public class UmsMemberProductCategoryRelationController {
    @Resource
    private IUmsMemberProductCategoryRelationService IUmsMemberProductCategoryRelationService;

    @SysLog(MODULE = "ums", REMARK = "根据条件查询所有会员与产品分类关系表（用户喜欢的分类）列表")
    @ApiOperation("根据条件查询所有会员与产品分类关系表（用户喜欢的分类）列表")
    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority('ums:UmsMemberProductCategoryRelation:read')")
    public Object getUmsMemberProductCategoryRelationByPage(UmsMemberProductCategoryRelation entity,
                                                            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        try {
            return new CommonResult().success(IUmsMemberProductCategoryRelationService.page(new Page<UmsMemberProductCategoryRelation>(pageNum, pageSize), new QueryWrapper<>(entity)));
        } catch (Exception e) {
            log.error("根据条件查询所有会员与产品分类关系表（用户喜欢的分类）列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "ums", REMARK = "保存会员与产品分类关系表（用户喜欢的分类）")
    @ApiOperation("保存会员与产品分类关系表（用户喜欢的分类）")
    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('ums:UmsMemberProductCategoryRelation:create')")
    public Object saveUmsMemberProductCategoryRelation(@RequestBody UmsMemberProductCategoryRelation entity) {
        try {
            if (IUmsMemberProductCategoryRelationService.save(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("保存会员与产品分类关系表（用户喜欢的分类）：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "ums", REMARK = "更新会员与产品分类关系表（用户喜欢的分类）")
    @ApiOperation("更新会员与产品分类关系表（用户喜欢的分类）")
    @PostMapping(value = "/update/{id}")
    @PreAuthorize("hasAuthority('ums:UmsMemberProductCategoryRelation:update')")
    public Object updateUmsMemberProductCategoryRelation(@RequestBody UmsMemberProductCategoryRelation entity) {
        try {
            if (IUmsMemberProductCategoryRelationService.updateById(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("更新会员与产品分类关系表（用户喜欢的分类）：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "ums", REMARK = "删除会员与产品分类关系表（用户喜欢的分类）")
    @ApiOperation("删除会员与产品分类关系表（用户喜欢的分类）")
    @GetMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('ums:UmsMemberProductCategoryRelation:delete')")
    public Object deleteUmsMemberProductCategoryRelation(@ApiParam("会员与产品分类关系表（用户喜欢的分类）id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("会员与产品分类关系表（用户喜欢的分类）id");
            }
            if (IUmsMemberProductCategoryRelationService.removeById(id)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("删除会员与产品分类关系表（用户喜欢的分类）：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "ums", REMARK = "给会员与产品分类关系表（用户喜欢的分类）分配会员与产品分类关系表（用户喜欢的分类）")
    @ApiOperation("查询会员与产品分类关系表（用户喜欢的分类）明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('ums:UmsMemberProductCategoryRelation:read')")
    public Object getUmsMemberProductCategoryRelationById(@ApiParam("会员与产品分类关系表（用户喜欢的分类）id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("会员与产品分类关系表（用户喜欢的分类）id");
            }
            UmsMemberProductCategoryRelation coupon = IUmsMemberProductCategoryRelationService.getById(id);
            return new CommonResult().success(coupon);
        } catch (Exception e) {
            log.error("查询会员与产品分类关系表（用户喜欢的分类）明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }

    }

    @ApiOperation(value = "批量删除会员与产品分类关系表（用户喜欢的分类）")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.POST)
    @ResponseBody
    @SysLog(MODULE = "pms", REMARK = "批量删除会员与产品分类关系表（用户喜欢的分类）")
    @PreAuthorize("hasAuthority('ums:UmsMemberProductCategoryRelation:delete')")
    public Object deleteBatch(@RequestParam("ids") List<Long> ids) {
        boolean count = IUmsMemberProductCategoryRelationService.removeByIds(ids);
        if (count) {
            return new CommonResult().success(count);
        } else {
            return new CommonResult().failed();
        }
    }

}

package com.mallplus.goods.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mallplus.common.annotation.SysLog;
import com.mallplus.common.entity.pms.PmsComment;
import com.mallplus.goods.service.IPmsCommentService;
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
 * 商品评价表
 * </p>
 *
 * @author zscat
 * @since 2019-04-19
 */
@Slf4j
@RestController
@Api(tags = "PmsCommentController", description = "商品评价表管理")
@RequestMapping("/pms/PmsComment")
public class PmsCommentController {
    @Resource
    private IPmsCommentService IPmsCommentService;

    @SysLog(MODULE = "pms", REMARK = "根据条件查询所有商品评价表列表")
    @ApiOperation("根据条件查询所有商品评价表列表")
    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority('pms:PmsComment:read')")
    public Object getPmsCommentByPage(PmsComment entity,
                                      @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                      @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        try {
            return new CommonResult().success(IPmsCommentService.page(new Page<PmsComment>(pageNum, pageSize), new QueryWrapper<>(entity)));
        } catch (Exception e) {
            log.error("根据条件查询所有商品评价表列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "pms", REMARK = "保存商品评价表")
    @ApiOperation("保存商品评价表")
    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('pms:PmsComment:create')")
    public Object savePmsComment(@RequestBody PmsComment entity) {
        try {
            if (IPmsCommentService.save(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("保存商品评价表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "pms", REMARK = "更新商品评价表")
    @ApiOperation("更新商品评价表")
    @PostMapping(value = "/update/{id}")
    @PreAuthorize("hasAuthority('pms:PmsComment:update')")
    public Object updatePmsComment(@RequestBody PmsComment entity) {
        try {
            if (IPmsCommentService.updateById(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("更新商品评价表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "pms", REMARK = "删除商品评价表")
    @ApiOperation("删除商品评价表")
    @GetMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('pms:PmsComment:delete')")
    public Object deletePmsComment(@ApiParam("商品评价表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("商品评价表id");
            }
            if (IPmsCommentService.removeById(id)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("删除商品评价表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "pms", REMARK = "给商品评价表分配商品评价表")
    @ApiOperation("查询商品评价表明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('pms:PmsComment:read')")
    public Object getPmsCommentById(@ApiParam("商品评价表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("商品评价表id");
            }
            PmsComment coupon = IPmsCommentService.getById(id);
            return new CommonResult().success(coupon);
        } catch (Exception e) {
            log.error("查询商品评价表明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }

    }

    @ApiOperation(value = "批量删除商品评价表")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.POST)
    @ResponseBody
    @SysLog(MODULE = "pms", REMARK = "批量删除商品评价表")
    @PreAuthorize("hasAuthority('pms:PmsComment:delete')")
    public Object deleteBatch(@RequestParam("ids") List<Long> ids) {
        boolean count = IPmsCommentService.removeByIds(ids);
        if (count) {
            return new CommonResult().success(count);
        } else {
            return new CommonResult().failed();
        }
    }

}

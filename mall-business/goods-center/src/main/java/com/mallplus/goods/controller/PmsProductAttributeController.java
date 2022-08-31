package com.mallplus.goods.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mallplus.common.annotation.SysLog;
import com.mallplus.common.entity.pms.PmsProductAttribute;
import com.mallplus.goods.service.IPmsProductAttributeService;
import com.mallplus.goods.vo.ProductAttrInfo;
import com.mallplus.common.utils.CommonResult;
import com.mallplus.common.utils.ValidatorUtils;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 商品属性参数表
 * </p>
 *
 * @author zscat
 * @since 2019-04-19
 */
@Slf4j
@RestController
@Api(tags = "PmsProductAttributeController", description = "商品属性参数表管理")
@RequestMapping("/pms/PmsProductAttribute")
public class PmsProductAttributeController {
    @Resource
    private IPmsProductAttributeService IPmsProductAttributeService;

    @SysLog(MODULE = "pms", REMARK = "根据条件查询所有商品属性参数表列表")
    @ApiOperation("根据条件查询所有商品属性参数表列表")
    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority('pms:PmsProductAttribute:read')")
    public Object getPmsProductAttributeByPage(PmsProductAttribute entity,
                                               @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                               @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        try {
            return new CommonResult().success(IPmsProductAttributeService.page(new Page<PmsProductAttribute>(pageNum, pageSize), new QueryWrapper<>(entity)));
        } catch (Exception e) {
            log.error("根据条件查询所有商品属性参数表列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "pms", REMARK = "根据分类查询属性列表或参数列表")
    @ApiOperation("根据分类查询属性列表或参数列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "type", value = "0表示属性，1表示参数", required = true, paramType = "query", dataType = "integer")})
    @RequestMapping(value = "/list/{cid}", method = RequestMethod.GET)
    @ResponseBody
    public Object getList(@PathVariable Long cid,
                          @RequestParam(value = "type") Integer type,
                          @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                          @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        PmsProductAttribute entity = new PmsProductAttribute();
        entity.setProductAttributeCategoryId(cid);
        entity.setType(type);
        try {
            return new CommonResult().success(IPmsProductAttributeService.page(new Page<PmsProductAttribute>(pageNum, pageSize), new QueryWrapper<>(entity)));
        } catch (Exception e) {
            log.error("根据条件查询所有商品属性参数表列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "pms", REMARK = "保存商品属性参数表")
    @ApiOperation("保存商品属性参数表")
    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('pms:PmsProductAttribute:create')")
    public Object savePmsProductAttribute(@RequestBody PmsProductAttribute entity) {
        try {
            if (entity.getType().equals(null)) {
                entity.setType(0);
            }
            if (IPmsProductAttributeService.saveAndUpdate(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("保存商品属性参数表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "pms", REMARK = "更新商品属性参数表")
    @ApiOperation("更新商品属性参数表")
    @PostMapping(value = "/update/{id}")
    @PreAuthorize("hasAuthority('pms:PmsProductAttribute:update')")
    public Object updatePmsProductAttribute(@RequestBody PmsProductAttribute entity) {
        try {
            if (IPmsProductAttributeService.updateById(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("更新商品属性参数表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "pms", REMARK = "删除商品属性参数表")
    @ApiOperation("删除商品属性参数表")
    @GetMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('pms:PmsProductAttribute:delete')")
    public Object deletePmsProductAttribute(@ApiParam("商品属性参数表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("商品属性参数表id");
            }
            if (IPmsProductAttributeService.removeById(id)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("删除商品属性参数表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "pms", REMARK = "给商品属性参数表分配商品属性参数表")
    @ApiOperation("查询商品属性参数表明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('pms:PmsProductAttribute:read')")
    public Object getPmsProductAttributeById(@ApiParam("商品属性参数表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("商品属性参数表id");
            }
            PmsProductAttribute coupon = IPmsProductAttributeService.getById(id);
            return new CommonResult().success(coupon);
        } catch (Exception e) {
            log.error("查询商品属性参数表明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }

    }

    @ApiOperation(value = "批量删除商品属性参数表")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.POST)
    @ResponseBody
    @SysLog(MODULE = "pms", REMARK = "批量删除商品属性参数表")
    @PreAuthorize("hasAuthority('pms:PmsProductAttribute:delete')")
    public Object deleteBatch(@RequestParam("ids") List<Long> ids) {
        boolean count = IPmsProductAttributeService.removeByIds(ids);
        if (count) {
            return new CommonResult().success(count);
        } else {
            return new CommonResult().failed();
        }
    }

    @SysLog(MODULE = "pms", REMARK = "根据商品分类的id获取商品属性及属性分类")
    @ApiOperation("根据商品分类的id获取商品属性及属性分类")
    @RequestMapping(value = "/attrInfo/{productCategoryId}", method = RequestMethod.GET)
    @ResponseBody
    public Object getAttrInfo(@PathVariable Long productCategoryId) {
        List<ProductAttrInfo> productAttrInfoList = IPmsProductAttributeService.getProductAttrInfo(productCategoryId);
        return new CommonResult().success(productAttrInfoList);
    }
}

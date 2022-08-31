package com.mallplus.member.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mallplus.common.annotation.SysLog;
import com.mallplus.common.utils.CommonResult;
import com.mallplus.common.utils.DateUtils;
import com.mallplus.common.utils.ValidatorUtils;
import com.mallplus.common.entity.ums.UmsMember;
import com.mallplus.member.service.IUmsMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 会员表
 * </p>
 *
 * @author zscat
 * @since 2019-04-19
 */
@Slf4j
@RestController
@Api(tags = "UmsMemberController", description = "会员表管理")
@RequestMapping("/ums/UmsMember")
public class UmsMemberController {
    @Resource
    private IUmsMemberService IUmsMemberService;

    @SysLog(MODULE = "ums", REMARK = "根据条件查询所有会员表列表")
    @ApiOperation("根据条件查询所有会员表列表")
    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority('ums:UmsMember:read')")
    public Object getUmsMemberByPage(UmsMember entity,
                                     @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                     @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        try {
            return new CommonResult().success(IUmsMemberService.page(new Page<UmsMember>(pageNum, pageSize), new QueryWrapper<>(entity).orderByDesc("create_time")));
        } catch (Exception e) {
            log.error("根据条件查询所有会员表列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "ums", REMARK = "保存会员表")
    @ApiOperation("保存会员表")
    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('ums:UmsMember:create')")
    public Object saveUmsMember(@RequestBody UmsMember entity) {
        try {
            if (IUmsMemberService.save(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("保存会员表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "ums", REMARK = "更新会员表")
    @ApiOperation("更新会员表")
    @PostMapping(value = "/update/{id}")
    @PreAuthorize("hasAuthority('ums:UmsMember:update')")
    public Object updateUmsMember(@RequestBody UmsMember entity) {
        try {
            if (IUmsMemberService.updateById(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("更新会员表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "ums", REMARK = "删除会员表")
    @ApiOperation("删除会员表")
    @GetMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('ums:UmsMember:delete')")
    public Object deleteUmsMember(@ApiParam("会员表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("会员表id");
            }
            if (IUmsMemberService.removeById(id)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("删除会员表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "ums", REMARK = "给会员表分配会员表")
    @ApiOperation("查询会员表明细")
    @GetMapping(value = "/detail/{id}")
    @PreAuthorize("hasAuthority('ums:UmsMember:read')")
    public Object getUmsMemberById(@ApiParam("会员表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("会员表id");
            }
            UmsMember coupon = IUmsMemberService.getById(id);
            return new CommonResult().success(coupon);
        } catch (Exception e) {
            log.error("查询会员表明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }

    }

    @ApiOperation(value = "批量删除会员表")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.POST)
    @ResponseBody
    @SysLog(MODULE = "pms", REMARK = "批量删除会员表")
    @PreAuthorize("hasAuthority('ums:UmsMember:delete')")
    public Object deleteBatch(@RequestParam("ids") List<Long> ids) {
        boolean count = IUmsMemberService.removeByIds(ids);
        if (count) {
            return new CommonResult().success(count);
        } else {
            return new CommonResult().failed();
        }
    }
    /**
     * 根据OpenId查询用户信息
     *
     * @param openId openId
     */
    @GetMapping(value = "/openId", params = "openId")
    @ApiOperation(value = "根据OpenId查询用户")
    public UmsMember findByOpenId(String openId) {
        return IUmsMemberService.findByOpenId(openId);
    }
    @GetMapping(value = "/id", params = "id")
    @ApiOperation(value = "根据Id查询用户")
    public UmsMember findById(Long id) {
        return IUmsMemberService.getById(id);
    }
    /**
     * 根据OpenId查询用户信息
     *
     * @param username openId
     */
    @GetMapping(value = "/username", params = "username")
    @ApiOperation(value = "username")
    public UmsMember findByusername(String username) {
        return IUmsMemberService.getOne(
                new QueryWrapper<UmsMember>().eq("username", username)
        );
    }

    /**
     * 根据OpenId查询用户信息
     *
     * @param  mobile
     */
    @GetMapping(value = "/mobile", params = "mobile")
    @ApiOperation(value = "mobile")
    public UmsMember findBymobile(String mobile) {
        return IUmsMemberService.getOne(
                new QueryWrapper<UmsMember>().eq("phone", mobile)
        );
    }

    @ApiOperation("首页会员统计")
    @SysLog(MODULE = "home", REMARK = "首页会员统计")
    @RequestMapping(value = "/userStatic", method = RequestMethod.GET)
    public Object userStatic() throws Exception {
        List<UmsMember> memberList = IUmsMemberService.list();
        int nowCount = 0;
        int yesUserCount = 0; // 昨日
        int qiUserCount = 0; // 当日
        for (UmsMember member : memberList) {
            if (DateUtils.format(member.getCreateTime()).equals(DateUtils.addDay(new Date(), -1))) {
                yesUserCount++;
            }
            if (member.getCreateTime().getTime()>=DateUtils.geFirstDayDateByMonth().getTime()) {
                qiUserCount++;
            }
            if (DateUtils.format(member.getCreateTime()).equals(DateUtils.format(new Date()))) {
                nowCount++;
            }
        }
        Map<String, Object> map = new HashMap();
        map.put("qiUserCount", qiUserCount);
        map.put("yesUserCount", yesUserCount);
        map.put("nowCount", nowCount);
        map.put("allCount", memberList.size());
        return new CommonResult().success(map);
    }

    @ApiOperation("更新会员的订单信息")
    @PostMapping(value = "/updateMemberOrderInfo")
    public Object updateMemberOrderInfo() {
        try {
            IUmsMemberService.updataMemberOrderInfo();
            return new CommonResult().success();
        } catch (Exception e) {
            log.error("更新会员的订单信息：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
    }
}

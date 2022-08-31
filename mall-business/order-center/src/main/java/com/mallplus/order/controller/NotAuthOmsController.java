package com.mallplus.order.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mallplus.common.annotation.IgnoreAuth;
import com.mallplus.common.annotation.SysLog;
import com.mallplus.common.entity.oms.OmsOrder;
import com.mallplus.common.utils.CommonResult;
import com.mallplus.common.vo.CartParam;
import com.mallplus.order.service.IOmsOrderService;
import com.mallplus.order.vo.GroupAndOrderVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Auther: shenzhuan
 * @Date: 2019/4/2 15:02
 * @Description:
 */
@RestController
@Api(tags = "NotAuthOmsController", description = "订单管理系统")
@RequestMapping("/notAuth")
public class NotAuthOmsController {



}

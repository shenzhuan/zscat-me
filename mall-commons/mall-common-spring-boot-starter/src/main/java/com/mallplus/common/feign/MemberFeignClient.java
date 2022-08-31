package com.mallplus.common.feign;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mallplus.common.constant.ServiceNameConstants;
import com.mallplus.common.entity.ums.*;
import com.mallplus.common.feign.fallback.MemberFeignClientFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author mall
 */
@FeignClient(name = ServiceNameConstants.MEMBER_SERVICE, fallbackFactory = MemberFeignClientFallbackFactory.class, decode404 = true)
public interface MemberFeignClient {
    /**
     * 根据OpenId查询用户信息
     *
     * @param openId openId
     */
    @GetMapping(value = "/ums/UmsMember/openId", params = "openId")
    UmsMember findByOpenId(@RequestParam("openId") String openId);

    @GetMapping(value = "/ums/UmsMember/id", params = "id")
    UmsMember findById(@RequestParam("id") Long id);

    @PostMapping(value = "/ums/UmsMember/create")
    Object saveUmsMember(@RequestBody UmsMember entity);


    @GetMapping(value = "/ums/UmsMember/username", params = "username")
    UmsMember findByUsername(@RequestParam("username") String username);

    @GetMapping(value = "/ums/UmsMember/mobile", params = "mobile")
    UmsMember findByMobile(@RequestParam("mobile") String mobile);

    @GetMapping(value = "/ums/UmsMember/updateIntegration")
    void updateIntegration(@RequestParam("id")Long id, @RequestParam("i")int i);

    @GetMapping(value = "/ums/UmsMember/selectIntegrationConsumeSettingById", params = "l")
    UmsIntegrationConsumeSetting selectIntegrationConsumeSettingById(long l);

    @GetMapping(value = "/notAuth/getSchoolById")
    SysSchool getSchoolById(@RequestParam("id") Long id);

    @GetMapping(value = "/notAuth/pageSchool")
    IPage<SysSchool>  pageSchool(@RequestParam("entity") SysSchool entity,
                        @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                        @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum);

    @PostMapping(value = "/notAuth/updateMember")
    void updateMember(@RequestBody UmsMember member);

    @GetMapping(value = "/notAuth/getMemberLevelById")
    UmsMemberLevel getMemberLevelById(@RequestParam("memberLevelId") Long memberLevelId);

    @PostMapping(value = "/notAuth/saveBlanceLog")
    void saveBlanceLog(@RequestBody UmsMemberBlanceLog blog);
}

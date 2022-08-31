package com.mallplus.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mallplus.common.model.SysStore;
import com.mallplus.common.model.SysUser;
import com.mallplus.common.vo.ApiContext;
import com.mallplus.user.mapper.SysStoreMapper;
import com.mallplus.user.mapper.SysUserMapper;
import com.mallplus.user.service.ISysStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zscat
 * @since 2019-05-18
 */
@Service
public class SysStoreServiceImpl extends ServiceImpl<SysStoreMapper, SysStore> implements ISysStoreService {

    @Resource
    private SysStoreMapper storeMapper;
    @Resource
    private SysUserMapper userMapper;
    @Autowired
    private ApiContext apiContext;
    @Resource
    private PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public boolean saveStore(SysStore entity) {
        storeMapper.insert(entity);
        SysUser user = new SysUser();
        user.setUsername(entity.getName());

        user.setPassword(passwordEncoder.encode(entity.getSupportName()));
        user.setCreateTime(new Date());
        user.setIcon(entity.getLogo());
        user.setNickName(entity.getName());
        //user.setStoreId(entity.getId());
        user.setEmail(entity.getSupportPhone());
        apiContext.setCurrentProviderId(entity.getId());
        return userMapper.insert(user) > 0;
    }
}

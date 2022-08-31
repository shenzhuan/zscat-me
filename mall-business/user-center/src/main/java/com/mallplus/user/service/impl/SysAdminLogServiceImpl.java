package com.mallplus.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mallplus.common.model.SysAdminLog;
import com.mallplus.common.vo.LogParam;
import com.mallplus.common.vo.LogStatisc;
import com.mallplus.user.mapper.SysAdminLogMapper;
import com.mallplus.user.service.ISysAdminLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zscat
 * @since 2019-04-14
 */
@Service
public class SysAdminLogServiceImpl extends ServiceImpl<SysAdminLogMapper, SysAdminLog> implements ISysAdminLogService {

    @Resource
    private SysAdminLogMapper logMapper;
    @Override
    public List<LogStatisc> selectPageExt(LogParam entity) {
        return logMapper.getLogStatisc(entity);
    }
}

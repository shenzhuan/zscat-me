package com.mallplus.cms.mapper;

import com.mallplus.common.entity.cms.CmsSubject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 专题表 Mapper 接口
 * </p>
 *
 * @author zscat
 * @since 2019-04-17
 */
public interface CmsSubjectMapper extends BaseMapper<CmsSubject> {

    int countByToday(Long id);
}

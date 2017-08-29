package com.ants.monitor.dao.mapper;

import com.ants.monitor.bean.entity.InvokeDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvokeDOMapper {
    int deleteByPrimaryKey(String uuId);

    int insertSelective(InvokeDO record);

    InvokeDO selectByPrimaryKey(String uuId);

    int updateByPrimaryKeySelective(InvokeDO record);


    List<InvokeDO> selectByInvokeDO(InvokeDO searchDO);

    int deleteByDate(@Param(value = "date") String minDate);

}
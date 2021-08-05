package com.cms.dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cms.entity.YlLaAgentEntity;
import com.cms.pojo.IdCheckAllPojo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IdCheckDao extends BaseMapper<YlLaAgentEntity> {
    List<IdCheckAllPojo> getAllYlLaAgent(@Param("ew") QueryWrapper<IdCheckAllPojo> qw);
}

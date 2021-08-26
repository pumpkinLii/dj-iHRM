package com.cms.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cms.entity.YlLaAgentEntity;
import com.cms.pojo.DismissQueryReturnPojo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DismissQueryDao extends BaseMapper<YlLaAgentEntity> {
    List<DismissQueryReturnPojo> DismissQueryList(@Param("ew") QueryWrapper<DismissQueryReturnPojo> qw);
}

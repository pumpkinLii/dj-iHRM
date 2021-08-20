package com.cms.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cms.entity.YlLaAgentEntity;
import com.cms.pojo.IdCheckAllPojo;
import com.cms.pojo.YlMSelectExcellPojo;
import com.cms.pojo.YlSelectExcellPojo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface YlSelectExcellDao extends BaseMapper<YlLaAgentEntity> {
    YlMSelectExcellPojo YlSelectExcell(@Param("ew") QueryWrapper<YlMSelectExcellPojo> qw);
}
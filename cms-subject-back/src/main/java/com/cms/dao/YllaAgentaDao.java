package com.cms.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.cms.entity.YlLaAgentEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cms.entity.YlUserInfoEntity;
import com.cms.pojo.UYllaBranchGroupReturnPojo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface YllaAgentaDao extends BaseMapper<YlLaAgentEntity> {
    List<UYllaBranchGroupReturnPojo> getManagerInfo(@Param(Constants.WRAPPER) QueryWrapper<UYllaBranchGroupReturnPojo> qw);
}

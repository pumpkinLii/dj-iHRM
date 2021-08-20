package com.cms.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.cms.entity.LdCodeEntity;
import com.cms.entity.LdComNewEntity;
import com.cms.pojo.Com4Pojo;
import com.cms.pojo.UYllaBranchGroupReturnPojo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComCodeDao extends BaseMapper<LdComNewEntity> {
    List<Com4Pojo> getCom4 (@Param(Constants.WRAPPER)QueryWrapper<Com4Pojo> qw);
}

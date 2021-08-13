package com.cms.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.cms.pojo.QueryWithCodeReturn;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;
@Repository
public interface QueryWithCodeDao extends BaseMapper<QueryWithCodeReturn> {
    QueryWithCodeReturn queryInfo(@Param(Constants.WRAPPER) QueryWrapper qw);
}

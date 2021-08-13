package com.cms.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.cms.pojo.NQueryStaffReturn;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface NQueryStaffDao extends BaseMapper<NQueryStaffReturn> {
    List<NQueryStaffReturn> queryStaff(@Param(Constants.WRAPPER) QueryWrapper<Map<String,Object>> qw);
}

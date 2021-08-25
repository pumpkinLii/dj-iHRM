package com.cms.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cms.pojo.DepartQueryReturnPojo;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartQueryDao extends BaseMapper<DepartQueryReturnPojo> {
    DepartQueryReturnPojo departQuery(@Param(Constants.WRAPPER) QueryWrapper wrapper);
}

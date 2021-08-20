package com.cms.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.cms.pojo.GradeQueryReturnPojo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface GradeQueryDao extends BaseMapper<GradeQueryReturnPojo> {
    List<GradeQueryReturnPojo> gradeQuery(@Param(Constants.WRAPPER)QueryWrapper wrapper);
}

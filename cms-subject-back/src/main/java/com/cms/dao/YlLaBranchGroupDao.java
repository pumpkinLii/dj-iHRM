package com.cms.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.cms.entity.YlLaBranchGroupEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cms.pojo.GradeQueryReturnPojo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface YlLaBranchGroupDao extends BaseMapper<YlLaBranchGroupEntity> {
    //职级调整的查询
    List<GradeQueryReturnPojo> gradeQuery(@Param(Constants.WRAPPER) QueryWrapper wrapper);

}

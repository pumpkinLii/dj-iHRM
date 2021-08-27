package com.cms.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.cms.entity.YlLaAgentEntity;
import com.cms.pojo.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author :zhanhaoze
 * @description :
 * @create :2021-08-03
 */
@Repository
public interface YlLaAgentDao extends BaseMapper<YlLaAgentEntity> {
    //人员信息查询
    List<QueryStaffReturn> queryStaff(@Param(Constants.WRAPPER) QueryWrapper</*Map<String,Object>*/QueryStaffReturn> qw);

    //根据agentCode查询人员信息
    QueryWithCodeReturn queryInfo(@Param(Constants.WRAPPER) QueryWrapper qw);

    //离职回显
    List<SearchDimissionPojo> SearchDimission(@Param("ew") QueryWrapper<SearchDimissionPojo> qw);
    //人员异动下拉列表查询
    List<ResultListPojo> SearchList(@Param("ew") QueryWrapper<ResultListPojo> qw);


}

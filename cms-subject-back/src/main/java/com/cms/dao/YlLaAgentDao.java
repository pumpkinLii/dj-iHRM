package com.cms.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.cms.entity.YlLaAgentEntity;
import com.cms.pojo.DepartQueryReturnPojo;
import com.cms.pojo.QueryStaffReturn;
import com.cms.pojo.QueryWithCodeReturn;
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

    //离司申请修改



}

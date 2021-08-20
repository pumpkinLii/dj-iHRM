package com.cms.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cms.entity.YlLaAgentEntity;
import com.cms.pojo.ResultListPojo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface SearchListDao extends BaseMapper<YlLaAgentEntity> {
    List<ResultListPojo> SearchList(@Param("ew") QueryWrapper<ResultListPojo> qw);
}

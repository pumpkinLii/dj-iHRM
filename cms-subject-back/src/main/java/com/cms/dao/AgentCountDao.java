package com.cms.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cms.entity.YlLaAgentEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentCountDao extends BaseMapper<YlLaAgentEntity> {
    //查询人员表 某个团队的人员数
}

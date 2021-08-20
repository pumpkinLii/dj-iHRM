package com.cms.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cms.entity.YlLaAgentManoeuvreEntity;
import org.springframework.stereotype.Repository;

/**
 * 持久层，此Dao用于扩展对YlLaAgentManoeuvreEntity(yl_la_agent_manoeuvre表)BaseMapper,提供多种对yl_la_agent_manoeuvre表的增删改查操作
 * */
@Repository
public interface YlLaAgentManoeuvreDao extends BaseMapper<YlLaAgentManoeuvreEntity> {
}

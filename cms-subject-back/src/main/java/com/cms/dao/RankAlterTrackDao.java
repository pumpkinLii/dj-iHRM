package com.cms.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cms.entity.YlLaAgentEntity;
import com.cms.entity.YlLaAgentManoeuvreEntity;
import com.cms.pojo.IdCheckAllPojo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RankAlterTrackDao extends BaseMapper<YlLaAgentManoeuvreEntity> {
}

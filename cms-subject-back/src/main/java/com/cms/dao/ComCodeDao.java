package com.cms.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cms.entity.LdCodeEntity;
import com.cms.entity.LdComNewEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface ComCodeDao extends BaseMapper<LdComNewEntity> {
}

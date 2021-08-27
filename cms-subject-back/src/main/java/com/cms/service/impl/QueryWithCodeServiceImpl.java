package com.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cms.dao.YlLaAgentDao;
import com.cms.entity.YlLaAgentEntity;
import com.cms.pojo.QueryWithCodeReturn;
import com.cms.service.QueryWithCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class QueryWithCodeServiceImpl extends ServiceImpl<YlLaAgentDao, YlLaAgentEntity> implements QueryWithCodeService {
    //根据员工工号进行查询
    @Autowired
    YlLaAgentDao ylLaAgentDao;
    @Override
    public QueryWithCodeReturn queryInfo(String agentCode){
        if (StringUtils.isEmpty(agentCode)){
            return null;
        }
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("t1.agent_code",agentCode);
        QueryWithCodeReturn info = ylLaAgentDao.queryInfo(wrapper);
        return info;
    }

}

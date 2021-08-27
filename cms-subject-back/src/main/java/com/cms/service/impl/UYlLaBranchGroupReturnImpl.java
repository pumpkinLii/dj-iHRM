package com.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cms.dao.YlLaAgentDao;
import com.cms.entity.YlLaAgentEntity;
import com.cms.pojo.UYllaBranchGroupReturnPojo;
import com.cms.service.UYllaBranchGroupReturnService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UYlLaBranchGroupReturnImpl extends ServiceImpl<YlLaAgentDao, YlLaAgentEntity> implements UYllaBranchGroupReturnService {

    @Override
    public UYllaBranchGroupReturnPojo updataRe(String agentCode){
        QueryWrapper<UYllaBranchGroupReturnPojo> qw = new QueryWrapper<>();
        qw.eq("yl_la_agent.agent_code",agentCode);
        List<UYllaBranchGroupReturnPojo> list = this.baseMapper.getManagerInfo(qw);
        if(list.size()==0){
            return null;
        }
        UYllaBranchGroupReturnPojo uYllaBranchGroupReturnPojo =list.get(0);

        return uYllaBranchGroupReturnPojo;
    }


}

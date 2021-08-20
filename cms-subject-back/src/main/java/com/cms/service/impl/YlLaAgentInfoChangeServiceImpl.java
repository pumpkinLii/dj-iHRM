package com.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cms.common.SelectAgentGroupInfo;
import com.cms.dao.YlLaAgentDao;
import com.cms.entity.YlLaAgentEntity;
import com.cms.pojo.YlLaAgentChangePojo;
import com.cms.service.YlLaAgentInfoChangeService;
import com.cms.service.YlLaAgentManoeuvreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 此类时用于人员异动(更改团队)使用，提供一个public方法ylLaAgentChange(YlLaAgentChangePojo ylLaAgentChangePojo)
 * */
@Service
@Slf4j
public class YlLaAgentInfoChangeServiceImpl extends ServiceImpl<YlLaAgentDao, YlLaAgentEntity> implements YlLaAgentInfoChangeService {

    @Autowired
    private YlLaAgentManoeuvreService ylLaAgentManoeuvreService;

    @Autowired
    private SelectAgentGroupInfo selectAgentGroupInfo;

    /**
     *此方法使用时传入一个YlLaAgentChangePojo对象，该对象中包含了人员异动后的信息，成功返回true，失败返回false
     * */
    @Override
    public boolean ylLaAgentChange(YlLaAgentChangePojo ylLaAgentChangePojo) {
        return this.agentChange(ylLaAgentChangePojo);
    }

    /**
     * 此私有方法用于检查YlLaAgentChangePojo中的信息是否合法，如果合法则在数据库中更新信息以及在异动表中插入数据然后返回true，失败则返回false
     * */
    private boolean agentChange(YlLaAgentChangePojo ylLaAgentChangePojo){
        QueryWrapper queryWrapper = new QueryWrapper();
        for (String i: ylLaAgentChangePojo.getAgentCodeList()) {
            queryWrapper.eq("agent_code",i);
            queryWrapper.or();
        }
        List<YlLaAgentEntity> resultSet = this.baseMapper.selectList(queryWrapper);
        if(resultSet.size() != ylLaAgentChangePojo.getAgentCodeList().size()){
            return false;
        }
        if(!selectAgentGroupInfo.checkBranchGroup(ylLaAgentChangePojo)){
            return false;
        }
        UpdateWrapper<YlLaAgentEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("manage_com",ylLaAgentChangePojo.getManageCom());
        updateWrapper.set("agent_group",ylLaAgentChangePojo.getAgentGroup());
        updateWrapper.set("modify_date",ylLaAgentChangePojo.getModifyDate());
        updateWrapper.set("operator",ylLaAgentChangePojo.getOperator());
        for (String i: ylLaAgentChangePojo.getAgentCodeList()) {
            updateWrapper.eq("agent_code",i);
            updateWrapper.or();
        }
        int result = this.baseMapper.update(null,updateWrapper);
        if(result == ylLaAgentChangePojo.getAgentCodeList().size() && ylLaAgentManoeuvreService.buildNewAgentManoeuvre(resultSet,ylLaAgentChangePojo)){
            return true;
        }
        return false;
    }
}

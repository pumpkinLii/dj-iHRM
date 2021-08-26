package com.cms.common;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cms.entity.YlLaBranchGroupEntity;
import com.cms.pojo.YlLaAgentChangePojo;
import com.cms.service.impl.AgentGroupImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 此类是个工具，提供两个public方法
 *
 * selectAgentGroup(String index)方法传入一个团队的内码字符串，查询数据库后返回该团队的数据实体
 * checkBranchGroup(YlLaAgentChangePojo ylLaAgentChangePojo)方法传入一个YlLaAgentChangePojo对象(YlLaAgentChangePojo对象是用于接受人员更改团队的)，用于检查YlLaAgentChangePojo中的团队和管理机构是否存在
 * */
@Component
public class SelectAgentGroupInfo {

    @Autowired
    private AgentGroupImpl agentGroup;

    /**
     * 此方法用于将团队内码转换为团队外码
     * */
    public String agentGroupToBranchAttr(String agentGroupIndex){
        return this.groupToAttr(agentGroupIndex);
    }

    /**
     * 此方法用于将团队内外码转换为团队内码
     * */
    public String branchAttrToAgentGroup(String branchAttrIndex){
        return this.attrToGroup(branchAttrIndex);
    }

    /**
     * 此方法用于获取指定工号的团队信息，传入一个团队的内码字符串，返回该团队的Entity，里面包含团队的所有信息
     * 在调用此方法前最好调用checkBranchGroup(YlLaAgentChangePojo ylLaAgentChangePojo)方法，并将这两个方法lock为一个原子操作，保证获取信息的正确和完整性
     * */
    public YlLaBranchGroupEntity selectAgentGroup(String index){
        return this.selectOneAgentGroup(index);
    }

    /**
     * 此方法用于联合团队内码和管理机构查询此团队是否存在，存在返回true，不存在返回false
     * */
    public boolean checkBranchGroup(YlLaAgentChangePojo ylLaAgentChangePojo){
        return this.checkBranchGroupInfo(ylLaAgentChangePojo);
    }

    /**
     * 封装了获取团队信息的私有方法
     * */
    private YlLaBranchGroupEntity selectOneAgentGroup(String index){
        QueryWrapper<YlLaBranchGroupEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("agent_group",index);
        YlLaBranchGroupEntity ylLaBranchGroupEntity = this.agentGroup.getBaseMapper().selectOne(queryWrapper);
        return ylLaBranchGroupEntity;
    }

    /**
     *封装了检查团队是否存在的方法
     * */
    private boolean checkBranchGroupInfo(YlLaAgentChangePojo ylLaAgentChangePojo){
        QueryWrapper<YlLaBranchGroupEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("manage_com",ylLaAgentChangePojo.getManageCom());
        queryWrapper.eq("agent_group",ylLaAgentChangePojo.getAgentGroup());
        List<YlLaBranchGroupEntity> resultSet = this.agentGroup.getBaseMapper().selectList(queryWrapper);
        if(resultSet.size() == 1){
            return true;
        }
        return false;
    }

    /**
     * 封装了团队内码转团队外码
     * */
    private String groupToAttr(String agentGroupIndex){
        QueryWrapper<YlLaBranchGroupEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("agent_group",agentGroupIndex);
        YlLaBranchGroupEntity ylLaBranchGroupEntity = agentGroup.getBaseMapper().selectOne(queryWrapper);
        return ylLaBranchGroupEntity.getBranchAttr();
    }

    /**
     * 封装了团队外码转团队内码
     * */
    private String attrToGroup(String branchAttrIndex){
        QueryWrapper<YlLaBranchGroupEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("branch_attr",branchAttrIndex);
        YlLaBranchGroupEntity ylLaBranchGroupEntity = agentGroup.getBaseMapper().selectOne(queryWrapper);
        return ylLaBranchGroupEntity.getAgentGroup();
    }

}

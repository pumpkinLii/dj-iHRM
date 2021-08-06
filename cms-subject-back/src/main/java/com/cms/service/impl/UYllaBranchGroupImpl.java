package com.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cms.dao.YlLaAgentDao;
import com.cms.dao.YllaAgentaDao;
import com.cms.dao.YllaBranchGroupDao;
import com.cms.entity.YlLaAgentEntity;
import com.cms.entity.YlLaBranchGroupEntity;
import com.cms.pojo.UYllaBranchGroupReturnPojo;
import com.cms.pojo.UYllabranchGroupPojo;
import com.cms.service.UYllaBranchGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service

public class UYllaBranchGroupImpl extends ServiceImpl<YllaBranchGroupDao, YlLaBranchGroupEntity> implements UYllaBranchGroupService {
    @Autowired
    YllaBranchGroupDao groupMapper;
    @Autowired
    YllaAgentaDao yllaAgentaDao;

    @Override
    public YlLaBranchGroupEntity update(UYllabranchGroupPojo mes) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        YlLaBranchGroupEntity ylLaBranchGroupEntity;
        UpdateWrapper<YlLaBranchGroupEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("branch_attr", mes.getBranchAttr());
        List<YlLaBranchGroupEntity> list = groupMapper.selectList(updateWrapper);
        if (list.size() == 0) {
            return null;
        }
        ylLaBranchGroupEntity = list.get(0);
        mes.setAgentGroup(ylLaBranchGroupEntity.getAgentGroup());
        ylLaBranchGroupEntity.setBranchName(mes.getBranchName());
        ylLaBranchGroupEntity.setBranchManager(mes.getBranchManager());
        ylLaBranchGroupEntity.setBranchStatus(mes.getBranchStatus());
        ylLaBranchGroupEntity.setOperator(mes.getOperator());
        ylLaBranchGroupEntity.setChatName(mes.getChatName());
        try {
            Date eef = simpleDateFormat.parse(mes.getBranchEffDate());
            Date teef;
            if (!mes.getBranchTerminateEffDate().isEmpty()||!(mes.getBranchTerminateEffDate()==null)) {
                teef = simpleDateFormat.parse(mes.getBranchTerminateEffDate());
            } else {
                teef = null;
            }
            ylLaBranchGroupEntity.setBranchEffDate(eef);
            ylLaBranchGroupEntity.setBranchTerminateEffDate(teef);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int checkState =100;
        if(mes.getBranchManager()!=null) checkState = managerCheck(mes.getBranchManager());
        if (checkState == 0) {
            ylLaBranchGroupEntity.setBranchManager("查无此人");
            return ylLaBranchGroupEntity;
        }
        if (checkState == 1) {
            ylLaBranchGroupEntity.setBranchManager("此人并非主管");
            return ylLaBranchGroupEntity;
        }
        if (mes.getBranchStatus().equals("Y")) {
            if (!GroupStopCheck(mes.getAgentGroup())||!ylLaBranchGroupEntity.getBranchManager().isEmpty()) {
                ylLaBranchGroupEntity.setAgentGroup("该团队下有在职员工");
                return ylLaBranchGroupEntity;
            }
        }
        if (mes.getBranchManager()==null||mes.getBranchManager().equals("")) ylLaBranchGroupEntity.setBranchManager(null);
        Integer rows = groupMapper.update(ylLaBranchGroupEntity, updateWrapper);
        if (rows > 0) {
            if (ylLaBranchGroupEntity.getBranchManager()!=null) {
                //任命主管修改此人团队
                QueryWrapper<YlLaAgentEntity> qw = new QueryWrapper<>();
                qw.eq("agent_code", ylLaBranchGroupEntity.getBranchManager());
                YlLaAgentEntity manager = yllaAgentaDao.selectList(qw).get(0);
                manager.setAgentGroup(mes.getAgentGroup());
                yllaAgentaDao.update(manager, qw);
            }
            return ylLaBranchGroupEntity;
        } else {
            return null;
        }
    }

    //检查主管信息
    private int managerCheck(String ManagerCode) {
        if (ManagerCode == null) return 100;
        if (ManagerCode.equals("")) return 100;
        QueryWrapper<UYllaBranchGroupReturnPojo> qw = new QueryWrapper<>();
        qw.eq("yl_la_agent.agent_code", ManagerCode);
        List<UYllaBranchGroupReturnPojo> list = yllaAgentaDao.getManagerInfo(qw);
        if (list.size() == 0) return 0;
        if (list.size() > 0 && (!list.get(0).getAgentGrade().equals("MA01")) && (!list.get(0).getAgentGrade().equals("MA02")) && (!list.get(0).getAgentGrade().equals("MA03")))
            return 1;
        UpdateWrapper<YlLaBranchGroupEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("branch_manager", ManagerCode);
        List<YlLaBranchGroupEntity> list1 = groupMapper.selectList(updateWrapper);
        if (list1.size() > 0) {
            list1.get(0).setBranchManager(null);
            groupMapper.update(list1.get(0), updateWrapper);
        }
        return 100;
    }

    //检查团队是否能关停
    private boolean GroupStopCheck(String agentGroup) {
        QueryWrapper<YlLaAgentEntity> qw = new QueryWrapper<>();
        qw.eq("agent_group", agentGroup);
        List<YlLaAgentEntity> list = yllaAgentaDao.selectList(qw);
        if (list.size() == 0) return true;
        else return false;
    }
}

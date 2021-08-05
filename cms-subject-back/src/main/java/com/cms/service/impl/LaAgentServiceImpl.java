package com.cms.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cms.dao.LaAgentDao;
import com.cms.entity.YlLaAgentAttrEntity;
import com.cms.entity.YlLaAgentEntity;
import com.cms.pojo.LaAgentPojo;
import com.cms.service.LaAgentService;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class LaAgentServiceImpl extends ServiceImpl<LaAgentDao, YlLaAgentEntity> implements LaAgentService {
    @Override
    public boolean laAgentSubmit(LaAgentPojo laAgent){
        //在此处调用创建YlLaAgentEntity的方法，用于数据库操作
        YlLaAgentEntity ylLaAgentEntity = this.buildAgentEntity(laAgent);

        //存入数据库
        int result = this.baseMapper.insert(ylLaAgentEntity);
        if(result > 0){
            return true;
        }
        return false;
    }

    /**
     * 此方法传入一个LaAgentPojo对象，返回一个YlLaAgentEntity对象，YlLaAgentEntity可以用于对数据库中yl_la_agent表进行操作
     */
    private YlLaAgentEntity buildAgentEntity(LaAgentPojo laAgent){
        YlLaAgentEntity ylLaAgentEntity = new YlLaAgentEntity();
        ylLaAgentEntity.setAgentCode(laAgent.getAgentCode());
        ylLaAgentEntity.setEmployDate(laAgent.getEmployDate());
        ylLaAgentEntity.setAgentGrade(laAgent.getAgentGrade());
        ylLaAgentEntity.setInitGrade(laAgent.getAgentGrade());
        ylLaAgentEntity.setAgentJob(laAgent.getAgentJob());
        ylLaAgentEntity.setAgentName(laAgent.getAgentName());
        ylLaAgentEntity.setSapCode(laAgent.getSapCode());
        ylLaAgentEntity.setManageCom(laAgent.getManageCom4());
        ylLaAgentEntity.setAgentGroup(laAgent.getBranchAttr());
        ylLaAgentEntity.setRepeatFlag("01");//调用wxy的返回接口
        ylLaAgentEntity.setOperator(laAgent.getOperator());
        /*****************************************************************************/
        ylLaAgentEntity.setAgentState("01");
        ylLaAgentEntity.setBranchType("01");
        /*****************************************************************************/
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("HH-mm-ss");
        String time = df.format(date);
        ylLaAgentEntity.setMakeDate(date);
        ylLaAgentEntity.setMakeTime(time);
        ylLaAgentEntity.setModifyDate(date);
        ylLaAgentEntity.setModifyTime(time);
        return ylLaAgentEntity;
    }
}

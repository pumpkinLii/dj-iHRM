package com.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cms.dao.InsertDimissionDao;
import com.cms.dao.YlLaDimissionDao;
import com.cms.entity.YlLaDimissionEntity;
import com.cms.pojo.AccInsertDimissionPojo;
import com.cms.service.InsertDimissionService;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.Date;


@Service
public class InsertDimissionImpl extends ServiceImpl<YlLaDimissionDao, YlLaDimissionEntity> implements InsertDimissionService {
    @Override
    public int InsertDimission(AccInsertDimissionPojo accInsertDimissionPojo) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("agent_code",accInsertDimissionPojo.getAgentCode());
        YlLaDimissionEntity ylLaDimissionEntity1 = this.baseMapper.selectOne(queryWrapper);
        if (ylLaDimissionEntity1==null){
            YlLaDimissionEntity ylLaDimissionEntity = new YlLaDimissionEntity();
            ylLaDimissionEntity.setAgentCode(accInsertDimissionPojo.getAgentCode());
            ylLaDimissionEntity.setManageCom(accInsertDimissionPojo.getManageCom());
            ylLaDimissionEntity.setAgentGroup(accInsertDimissionPojo.getAgentGroup());
            ylLaDimissionEntity.setDepartReason(accInsertDimissionPojo.getDepartReason());
            int DepartTimes = Integer.parseInt(accInsertDimissionPojo.getDepartTimes());
            ylLaDimissionEntity.setDepartTimes(DepartTimes);
            ylLaDimissionEntity.setNoti(accInsertDimissionPojo.getNoti());
            ylLaDimissionEntity.setDepartState(accInsertDimissionPojo.getDepartState());
            ylLaDimissionEntity.setOperator(accInsertDimissionPojo.getOperator());
            ylLaDimissionEntity.setBranchType("4");
            Date date = new Date();
            ylLaDimissionEntity.setDepartDate(date);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date1 = sdf.format(date);
            ylLaDimissionEntity.setMakeTime(date1);
            ylLaDimissionEntity.setModifyTime(date1);
            int insert = 0;
            insert = this.baseMapper.insert(ylLaDimissionEntity);
            return insert;
        }else {
            ylLaDimissionEntity1.setAgentCode(accInsertDimissionPojo.getAgentCode());
            ylLaDimissionEntity1.setManageCom(accInsertDimissionPojo.getManageCom());
            ylLaDimissionEntity1.setAgentGroup(accInsertDimissionPojo.getAgentGroup());
            ylLaDimissionEntity1.setDepartReason(accInsertDimissionPojo.getDepartReason());
            int DepartTimes = Integer.parseInt(accInsertDimissionPojo.getDepartTimes());
            ylLaDimissionEntity1.setDepartTimes(DepartTimes);
            ylLaDimissionEntity1.setNoti(accInsertDimissionPojo.getNoti());
            ylLaDimissionEntity1.setDepartState(accInsertDimissionPojo.getDepartState());
            ylLaDimissionEntity1.setOperator(accInsertDimissionPojo.getOperator());
            ylLaDimissionEntity1.setBranchType("4");
            Date date = new Date();
            ylLaDimissionEntity1.setDepartDate(date);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date1 = sdf.format(date);
            ylLaDimissionEntity1.setMakeTime(date1);
            ylLaDimissionEntity1.setModifyTime(date1);
            int update = this.baseMapper.update(ylLaDimissionEntity1, queryWrapper);
            return update;
        }
    }
}

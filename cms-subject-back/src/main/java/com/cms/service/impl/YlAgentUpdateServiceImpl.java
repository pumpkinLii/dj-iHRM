package com.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cms.dao.YlLaAgentDao;
import com.cms.entity.YlLaAgentEntity;
import com.cms.pojo.QueryStaffPojo;
import com.cms.pojo.QueryStaffReturn;
import com.cms.pojo.QueryWithCodeReturn;
import com.cms.service.YlAgentUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class YlAgentUpdateServiceImpl extends ServiceImpl<YlLaAgentDao, YlLaAgentEntity> implements YlAgentUpdateService {
    //根据给出的条件返回查询结果
    @Autowired
    YlLaAgentDao ylLaAgentDao;
    public List<QueryStaffReturn> queryStaff(QueryStaffPojo queryStaffPojo) throws ParseException {
        QueryWrapper<QueryStaffReturn> wrapper = new QueryWrapper<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        wrapper.eq(!StringUtils.isEmpty(queryStaffPojo.getAgentCode()),"t1.agent_code",queryStaffPojo.getAgentCode());
        wrapper.like(!StringUtils.isEmpty(queryStaffPojo.getAgentName()),"t1.agent_name",queryStaffPojo.getAgentName());
        wrapper.eq(!StringUtils.isEmpty(queryStaffPojo.getAgentState()),"t1.agent_state",queryStaffPojo.getAgentState());
        wrapper.eq(!StringUtils.isEmpty(queryStaffPojo.getBranchAttr()),"t3.branch_attr",queryStaffPojo.getBranchAttr());
        if (queryStaffPojo.getStartDate()!=null){
            String sd = dateFormat.format(queryStaffPojo.getStartDate());
            Date date1=dateFormat.parse(sd);
            wrapper.ge("t1.employ_date",date1);
        }
        if (queryStaffPojo.getEndDate()!=null){
            String ed = dateFormat.format(queryStaffPojo.getEndDate());
            Date date2=dateFormat.parse(ed);
            wrapper.le("t1.employ_date",date2);
        }
        if(!"".equals(queryStaffPojo.getManageCom4())){
            wrapper.eq("t2.com_code",queryStaffPojo.getManageCom4());
            //wrapper.eq(!StringUtils.isEmpty(queryStaffPojo.getBranchAttr()),"t3.branch_attr",queryStaffPojo.getBranchAttr());
        }else if (!"".equals(queryStaffPojo.getManageCom3())){
            wrapper.likeRight("t2.com_code",queryStaffPojo.getManageCom3());
        } else if (!"".equals(queryStaffPojo.getManageCom2())) {
            wrapper.likeRight("t2.com_code",queryStaffPojo.getManageCom2());
        }
        List<QueryStaffReturn> list = ylLaAgentDao.queryStaff(wrapper);
        return list;
    }

    //根据员工工号进行查询
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

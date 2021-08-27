package com.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cms.common.ComnewSon;
import com.cms.dao.YlLaAgentDao;
import com.cms.dao.YlLaDimissionDao;
import com.cms.entity.YlLaAgentEntity;
import com.cms.pojo.DismissQueryPojo;
import com.cms.pojo.DismissQueryReturnPojo;
import com.cms.service.DismissQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class DismissQueryServiceImpl extends ServiceImpl<YlLaAgentDao, YlLaAgentEntity> implements DismissQueryService {
    @Autowired
    YlLaAgentDao ylLaAgentDao;

    public List<DismissQueryReturnPojo> searchList(DismissQueryPojo dismissQueryPojo){
        QueryWrapper<DismissQueryReturnPojo> qw =new QueryWrapper();
        String[] comCodesA = dismissQueryPojo.getManageCom().split(",");
        String comCode = comCodesA[comCodesA.length-1];

        qw.likeRight(!StringUtils.isEmpty(comCode),"t2.manage_com",comCode);
        qw.eq(!StringUtils.isEmpty(dismissQueryPojo.getAgentGroup()),"t1.agent_group",dismissQueryPojo.getAgentGroup());
        qw.like(!StringUtils.isEmpty(dismissQueryPojo.getBranchName()),"t2.branch_name",dismissQueryPojo.getBranchName());
        qw.eq(!StringUtils.isEmpty(dismissQueryPojo.getAgentCode()),"t1.agent_code",dismissQueryPojo.getAgentCode());
        qw.like(!StringUtils.isEmpty(dismissQueryPojo.getAgentName()),"t1.agent_name",dismissQueryPojo.getAgentName());
        qw.eq(!StringUtils.isEmpty(dismissQueryPojo.getAgentGrade()),"t1.agent_grade",dismissQueryPojo.getAgentGrade());
        qw.eq(!StringUtils.isEmpty(dismissQueryPojo.getAgydepart()),"t3.depart_state",dismissQueryPojo.getAgydepart()); //审核状态
        qw.eq(!StringUtils.isEmpty(dismissQueryPojo.getDiffCause()),"t3.depart_reason",dismissQueryPojo.getDiffCause());
        qw.ge(!StringUtils.isEmpty(dismissQueryPojo.getDiffStartDate()),"t3.depart_date",dismissQueryPojo.getDiffStartDate());
        qw.le(!StringUtils.isEmpty(dismissQueryPojo.getDiffEndDate()),"t3.depart_date",dismissQueryPojo.getDiffEndDate());

        List<DismissQueryReturnPojo> list = ylLaAgentDao.DismissQueryList(qw);
        return list;
    }
}

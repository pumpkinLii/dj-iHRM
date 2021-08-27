package com.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cms.dao.YlLaBranchGroupDao;
import com.cms.pojo.GradeQueryPojo;
import com.cms.pojo.GradeQueryReturnPojo;
import com.cms.service.GradeQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
@Service
public class GradeQueryServiceImpl implements GradeQueryService {
    //代理人职级调整时的按条件查询
    @Autowired
    YlLaBranchGroupDao yllaBranchGroupDao;
    public List<GradeQueryReturnPojo> gradeQuery(GradeQueryPojo gradeQueryPojo){
        QueryWrapper<GradeQueryReturnPojo> wrapper = new QueryWrapper<>();
        wrapper.likeRight(!StringUtils.isEmpty(gradeQueryPojo.getManageCom()),"t1.manage_com",gradeQueryPojo.getManageCom());
        wrapper.eq(!StringUtils.isEmpty(gradeQueryPojo.getAgentGroup()),"t1.agent_group",gradeQueryPojo.getAgentGroup());
        wrapper.like(!StringUtils.isEmpty(gradeQueryPojo.getBranchName()),"t1.branch_name",gradeQueryPojo.getBranchName());//效率，判不判空
        wrapper.eq(!StringUtils.isEmpty(gradeQueryPojo.getAgentCode()),"t2.agent_code",gradeQueryPojo.getAgentCode());
        wrapper.like(!StringUtils.isEmpty(gradeQueryPojo.getAgentName()),"t2.agent_name",gradeQueryPojo.getAgentName());
        wrapper.eq(!StringUtils.isEmpty(gradeQueryPojo.getPhone()),"t3.phone",gradeQueryPojo.getPhone());
        wrapper.eq(!StringUtils.isEmpty(gradeQueryPojo.getIdNo()),"t3.id_no",gradeQueryPojo.getIdNo());
        wrapper.eq(!StringUtils.isEmpty(gradeQueryPojo.getAgentGrade()),"t2.agent_grade",gradeQueryPojo.getAgentGrade());
        List<GradeQueryReturnPojo> list = yllaBranchGroupDao.gradeQuery(wrapper);
        return list;
    }
}

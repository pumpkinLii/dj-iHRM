package com.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cms.common.ComnewSon;
import com.cms.dao.SearchListDao;
import com.cms.entity.YlLaAgentEntity;
import com.cms.pojo.ResultListPojo;
import com.cms.pojo.SearchListPojo;
import com.cms.service.SearchLIstService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.List;

@Service
public class SearchListServiceImpl extends ServiceImpl<SearchListDao, YlLaAgentEntity>implements SearchLIstService {
    @Autowired
    SearchListDao searchListDao;
    @Autowired
    ComnewSon comnewSon;
    public List<ResultListPojo> searchList(SearchListPojo searchListPojo){
        QueryWrapper<ResultListPojo> qw =new QueryWrapper();
        String[] comCodesA = searchListPojo.getManageCom().split(",");
        String comCode = comCodesA[comCodesA.length-1];
        qw.likeRight(!StringUtils.isEmpty(comCode),"t4.manage_com",comCode);
        qw.likeRight("t1.agent_grade","YL");
        qw.eq(!StringUtils.isEmpty(searchListPojo.getBranchName()),"t4.branch_name",searchListPojo.getBranchName());
        qw.eq(!StringUtils.isEmpty(searchListPojo.getBranchAttr()),"t1.agent_group",searchListPojo.getBranchAttr());
        qw.eq(!StringUtils.isEmpty(searchListPojo.getAgentCode()),"t1.agent_code",searchListPojo.getAgentCode());
        qw.like(!StringUtils.isEmpty(searchListPojo.getAgentName()),"t1.agent_name",searchListPojo.getAgentName());
        qw.eq(!StringUtils.isEmpty(searchListPojo.getAgentPhone()),"t3.phone",searchListPojo.getAgentPhone());
        qw.eq((!StringUtils.isEmpty(searchListPojo.getIdNo())),"t3.id_no",searchListPojo.getIdNo());
        List<ResultListPojo> list = searchListDao.SearchList(qw);
        return list;
    }

}

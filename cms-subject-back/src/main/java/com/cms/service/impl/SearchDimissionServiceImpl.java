package com.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cms.dao.SearchDimissionDao;
import com.cms.dao.YlLaAgentDao;
import com.cms.entity.YlLaAgentEntity;
import com.cms.pojo.AcceptDimissionPojo;
import com.cms.pojo.SearchDimissionPojo;
import com.cms.service.SearchDimissionService;
import com.cms.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.List;

@Service
public class SearchDimissionServiceImpl extends ServiceImpl<SearchDimissionDao, YlLaAgentEntity> implements SearchDimissionService {
    @Autowired
    SearchDimissionDao searchDimissionDao;
    @Autowired
    YlLaAgentDao ylLaAgentDao;

    @Override
    public R SearchDimission(AcceptDimissionPojo acceptDimissionPojo) {
        if (StringUtils.isEmpty(acceptDimissionPojo.getAgentCode())) {
            return R.ok("输入工号不能为空").put("code", 501);
        }
        QueryWrapper<SearchDimissionPojo> qw = new QueryWrapper();
        qw.eq(!StringUtils.isEmpty(acceptDimissionPojo.getAgentCode()), "yla.agent_code", acceptDimissionPojo.getAgentCode());
        List<SearchDimissionPojo> list = searchDimissionDao.SearchDimission(qw);
        QueryWrapper<YlLaAgentEntity> qw1 = new QueryWrapper();
        qw1.eq("agent_code", acceptDimissionPojo.getAgentCode());
        List<YlLaAgentEntity> list1 = ylLaAgentDao.selectList(qw1);
        if (!(list1.size() == 0)) {
            YlLaAgentEntity ylLaAgentEntity = list1.get(0);
            {
                if (ylLaAgentEntity.getAgentState().equals("04")) {
                    return R.ok("该员工已离职").put("code", 501).put("list", list.get(0));
                }
                return R.ok().put("list", list.get(0));
            }
        } else {
            return R.ok("工号不存在").put("code", 501);
        }
    }
}

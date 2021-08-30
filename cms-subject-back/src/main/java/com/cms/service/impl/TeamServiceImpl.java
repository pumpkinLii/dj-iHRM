package com.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cms.dao.YlLaBranchGroupDao;
import com.cms.entity.YlLaBranchGroupEntity;
import com.cms.pojo.QueryGroupPojo;
import com.cms.service.TeamService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TeamServiceImpl extends ServiceImpl<YlLaBranchGroupDao, YlLaBranchGroupEntity> implements TeamService {
    //根据管理机构查询团队
    @Override
    public List<Map<String,String>> queryGroup(QueryGroupPojo queryGroupPojo){
        QueryWrapper<YlLaBranchGroupEntity> qw = new QueryWrapper<>();
        qw.eq("manage_com", queryGroupPojo.getManageCom4());
        List<YlLaBranchGroupEntity> list = this.baseMapper.selectList(qw);
        List<Map<String ,String>> list0 = new ArrayList<>();
        if (list.size()>0) {
            for (YlLaBranchGroupEntity entity : list) {
                Map<String, String> map = new HashMap<>();
                map.put("label", entity.getBranchName());
                map.put("value", entity.getBranchAttr());
                //map.put("value", entity.getAgentGroup());
                list0.add(map);
            }
            return list0;
        }else {
            return null;
        }
    }

}

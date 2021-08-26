package com.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cms.dao.YlLaBranchGroupDao;
import com.cms.entity.YlLaBranchGroupEntity;
import com.cms.pojo.QueryGroupPojo;
import com.cms.service.QueryGroupService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QueryGroupServiceImpl extends ServiceImpl<YlLaBranchGroupDao, YlLaBranchGroupEntity> implements QueryGroupService {
    @Override
    public List<Map<String,String>> queryGroup(QueryGroupPojo queryGroupPojo){
        QueryWrapper<YlLaBranchGroupEntity> qw = new QueryWrapper<>();
        qw.eq("manage_com", queryGroupPojo.getManageCom4());
        List<YlLaBranchGroupEntity> list = this.baseMapper.selectList(qw);
        List<Map<String ,String>> list0 = new ArrayList<>();
        if (list.size()>0) {
            for (YlLaBranchGroupEntity ybge : list) {
                Map<String, String> map = new HashMap<>();
                map.put("label", ybge.getBranchName());
                map.put("value", ybge.getAgentGroup());
                list0.add(map);
            }
            return list0;
        }else {
            return null;
        }
    }

}

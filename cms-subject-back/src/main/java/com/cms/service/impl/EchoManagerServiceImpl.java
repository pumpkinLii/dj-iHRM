package com.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cms.dao.EchoManagerDao;
import com.cms.entity.YlLaBranchGroupEntity;
import com.cms.pojo.GroupPojo;
import com.cms.service.EchoManagerService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EchoManagerServiceImpl extends ServiceImpl<EchoManagerDao, YlLaBranchGroupEntity> implements EchoManagerService {

    @Override
    public Map<String,String> getManager(GroupPojo groupPojo){
        QueryWrapper<YlLaBranchGroupEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("branch_attr",groupPojo.getGroupId());
        YlLaBranchGroupEntity groupEntity = this.baseMapper.selectOne(queryWrapper);
        if (groupEntity!=null) {
            Map<String,String> map = new HashMap<>();
            map.put("branchManager",groupEntity.getBranchManager());
            map.put("branchManagerName",groupEntity.getBranchManagerName());
            return map;
        }else {
            return null;
        }

    }

}

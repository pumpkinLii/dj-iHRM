package com.cms.service.impl;

import com.alibaba.excel.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cms.dao.YlLaBranchGroupDao;
import com.cms.entity.YlLaBranchGroupEntity;
import com.cms.pojo.StaffPojo;
import com.cms.service.EchoGroupService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EchoGroupServiceImpl extends ServiceImpl<YlLaBranchGroupDao, YlLaBranchGroupEntity> implements EchoGroupService {
    //根据四级管理机构与职级查询团队，总监查询无主管团队，客户经理查询所有团队
    public List<Map<String, String>> getGroup(StaffPojo staffPojo) {
        QueryWrapper<YlLaBranchGroupEntity> queryWrapper = new QueryWrapper<>();
        Map<String,Object> eqMap = new HashMap<>();
        queryWrapper.eq("branch_status", "N");
        queryWrapper.eq("manage_com", staffPojo.getManagerCom());
        List<YlLaBranchGroupEntity> list = this.baseMapper.selectList(queryWrapper);
        List<Map<String,String>> listMap = new ArrayList<>();
            if (list.size() > 0) {
                for (YlLaBranchGroupEntity branchGroup:list){
                    Map<String,String> map = new HashMap<>();
                    //若职级为总监，前端传参为“0”，需查询无主管团队
                    if("0".equals(staffPojo.getGradeName()) && !StringUtils.isEmpty(branchGroup.getBranchManager()))
                    {
                        continue;
                    }
                    map.put("value",branchGroup.getBranchAttr());
                    map.put("label",branchGroup.getBranchName());
                    listMap.add(map);
                }
                return listMap;
            } else {
                return null;
            }

    }
}

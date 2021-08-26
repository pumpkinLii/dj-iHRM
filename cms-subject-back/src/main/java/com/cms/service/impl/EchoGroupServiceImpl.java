package com.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cms.dao.YllaBranchGroupDao;
import com.cms.entity.YlLaBranchGroupEntity;
import com.cms.pojo.StaffPojo;
import com.cms.service.EchoGroupService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EchoGroupServiceImpl extends ServiceImpl<YllaBranchGroupDao, YlLaBranchGroupEntity> implements EchoGroupService {
    //@Override
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
                    if(staffPojo.getGradeName().equals(/*"总监"*/"0") && branchGroup.getBranchManager()!=null)
                    {
                        continue;
                    }
                    map.put("value",branchGroup.getBranchAttr());
                    map.put("label",branchGroup.getBranchName());
                    /*map.put("label",branchGroup.getBranchAttr());
                    map.put("value",branchGroup.getBranchName());*/
                    listMap.add(map);
                }
                return listMap;
            } else {
                return null;
            }

    }
}

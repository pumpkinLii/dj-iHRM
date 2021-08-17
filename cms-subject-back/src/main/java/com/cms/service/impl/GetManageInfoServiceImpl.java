package com.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cms.dao.YllaBranchGroupDao;
import com.cms.entity.YlLaBranchGroupEntity;
import com.cms.pojo.ManagerPojo;
import com.cms.service.GetManageInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetManageInfoServiceImpl implements GetManageInfoService {
    @Autowired
    YllaBranchGroupDao yllaBranchGroupDao;
    @Override
    public ManagerPojo getManageInfo(String branchAttr){
        QueryWrapper<YlLaBranchGroupEntity> qw = new QueryWrapper<>();
        qw.eq("branch_attr",branchAttr);
        YlLaBranchGroupEntity ylLaBranchGroupEntity = yllaBranchGroupDao.selectOne(qw);
        ManagerPojo managerPojo = new ManagerPojo();
        if(ylLaBranchGroupEntity==null) {
            return null;
        }
        try{
            managerPojo.setManagerId( ylLaBranchGroupEntity.getBranchManager());
            managerPojo.setManagerName(ylLaBranchGroupEntity.getBranchManagerName());
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return  managerPojo;
    }
}

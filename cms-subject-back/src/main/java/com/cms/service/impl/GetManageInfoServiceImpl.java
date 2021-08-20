package com.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cms.dao.ComCodeDao;
import com.cms.dao.YllaBranchGroupDao;
import com.cms.entity.LdComNewEntity;
import com.cms.entity.YlLaBranchGroupEntity;
import com.cms.pojo.Com4Pojo;
import com.cms.pojo.GroupInfoPojo;
import com.cms.pojo.ManagerPojo;
import com.cms.service.GetManageInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetManageInfoServiceImpl implements GetManageInfoService {
    @Autowired
    YllaBranchGroupDao yllaBranchGroupDao;
    @Autowired
    ComCodeDao comCodeDao;
    @Override
    //返回负责人信息
    public ManagerPojo getManageInfo(String branchAttr){
        QueryWrapper<YlLaBranchGroupEntity> qw = new QueryWrapper<>();
        qw.eq("agent_group",branchAttr);
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

    @Override
    //返回四级机构列表
    public List<Com4Pojo> getCom4(){
        QueryWrapper<Com4Pojo> qw =new QueryWrapper<>();
        List<Com4Pojo> list = comCodeDao.getCom4(qw);
        return list;
    }

    @Override
    //得到团队列表
    public List<GroupInfoPojo> getGroup(String comCodes){
        String[] comCodesA = comCodes.split(",");
        String comCode = comCodesA[comCodesA.length-1];
        QueryWrapper<YlLaBranchGroupEntity> qw = new QueryWrapper<>();
        qw.likeRight("manage_com",comCode);
        List<GroupInfoPojo> list = new ArrayList<>();
        List<YlLaBranchGroupEntity> list1 = yllaBranchGroupDao.selectList(qw);
        try {
            for(YlLaBranchGroupEntity i:list1){
                GroupInfoPojo groupInfoPojo = new GroupInfoPojo();
                groupInfoPojo.setBranchAttr(i.getAgentGroup());
                groupInfoPojo.setName(i.getBranchName());
                list.add(groupInfoPojo);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}

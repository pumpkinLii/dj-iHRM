package com.cms.service.impl;

import com.alibaba.excel.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cms.dao.LdComNewDao;
import com.cms.dao.YlLaAgentDao;
import com.cms.dao.YlLaBranchGroupDao;
import com.cms.entity.YlLaAgentEntity;
import com.cms.entity.YlLaBranchGroupEntity;
import com.cms.pojo.*;
import com.cms.service.BackMsgService;
import com.cms.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BackMsgServiceImpl extends ServiceImpl<YlLaBranchGroupDao, YlLaBranchGroupEntity> implements BackMsgService {
    @Autowired
    LdComNewDao comCodeDao;
    @Autowired
    RYlLaBranchGroupServiceImpl rYlLaBranchGroupServiceImpl;
    @Autowired
    YlLaAgentDao ylLaAgentDao;

    /**
     * 此方法根据职级返回架构团队的代码,传入一个GradeTeamPojo对象，返回一个R对象
     * */
    @Override
    public R getgradeteam(GradeTeamPojo gradeTeamPojo){
        return this.getgrade(gradeTeamPojo);
    }

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

    @Override
    //返回四级机构列表
    public List<Com4Pojo> getCom4(){
        QueryWrapper<Com4Pojo> qw =new QueryWrapper<>();
        List<Com4Pojo> list = comCodeDao.getCom4(qw);
        return list;
    }

    @Override
    //返回负责人信息
    public ManagerPojo getManageInfo(String branchAttr){
        QueryWrapper<YlLaBranchGroupEntity> qw = new QueryWrapper<>();
        qw.eq("agent_group",branchAttr);
        YlLaBranchGroupEntity ylLaBranchGroupEntity = this.baseMapper.selectOne(qw);
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
    //得到团队列表
    public List<GroupInfoPojo> getGroup(String comCodes){
        String[] comCodesA = comCodes.split(",");
        String comCode = comCodesA[comCodesA.length-1];
        QueryWrapper<YlLaBranchGroupEntity> qw = new QueryWrapper<>();
        qw.likeRight("manage_com",comCode);
        List<GroupInfoPojo> list = new ArrayList<>();
        List<YlLaBranchGroupEntity> list1 = this.baseMapper.selectList(qw);
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




    /**
     * 封装根据职级返回架构团队
     * */
    private R getgrade(GradeTeamPojo gradeTeamPojo) {
        //与前端协商 只有当她请求
        String oldgrade=gradeTeamPojo.getOldgradecode();
        String nowgrade=gradeTeamPojo.getNowgradecode();
        String comcode=gradeTeamPojo.getComecode();
        List result=new ArrayList();
        if (comcode.equals(null)|| org.springframework.util.StringUtils.isEmpty(comcode)){
            return R.ok("请先选择管理机构");
        }
        if (oldgrade.substring(0,2).equals("MA")){
            //总监的情况 返回所有机构以下的团队
            QueryWrapper queryWrapper=new QueryWrapper();
            queryWrapper.eq("manage_com",comcode);
            List<YlLaBranchGroupEntity> laBranchGroupEntities = rYlLaBranchGroupServiceImpl.getBaseMapper().selectList(queryWrapper);
            if (laBranchGroupEntities.size()==0){return R.ok("没有团队可选");}
            for (int i = 0; i < laBranchGroupEntities.size(); i++) {
                Map map=new HashMap();
                map.put("groupname",laBranchGroupEntities.get(i).getBranchName());
                map.put("groupcode",laBranchGroupEntities.get(i).getAgentGroup());
                result.add(map);
            }
        }else {
            if(nowgrade.substring(0,2).equals("YL")){
                // yl -> yl
                QueryWrapper queryWrapper=new QueryWrapper();
                queryWrapper.eq("agent_code",gradeTeamPojo.getAgentcode());
                YlLaAgentEntity ylLaAgentEntity = ylLaAgentDao.selectOne(queryWrapper);
                QueryWrapper queryWrapper1=new QueryWrapper();
                queryWrapper1.eq("agent_group",ylLaAgentEntity.getAgentGroup());
                YlLaBranchGroupEntity ylLaBranchGroupEntity = rYlLaBranchGroupServiceImpl.getBaseMapper().selectOne(queryWrapper1);
                Map map=new HashMap();
                map.put("groupname",ylLaBranchGroupEntity.getBranchName());
                map.put("groupcode",ylLaBranchGroupEntity.getAgentGroup());
                result.add(map);
            }else {
                // yl->MA
                QueryWrapper qw=new QueryWrapper();
                qw.eq("branch_status","N");
                qw.eq("manage_com",comcode);
                List<YlLaBranchGroupEntity> laBranchGroupEntities = rYlLaBranchGroupServiceImpl.getBaseMapper().selectList(qw);
                if (laBranchGroupEntities.size()==0){
                    return R.ok("无符合条件的团队");
                }
                for (int i = 0; i < laBranchGroupEntities.size(); i++) {
                    if (laBranchGroupEntities.get(i).getBranchManager()==null|| org.springframework.util.StringUtils.isEmpty(laBranchGroupEntities.get(i).getBranchManager())){
                        Map map=new HashMap();
                        map.put("groupname",laBranchGroupEntities.get(i).getBranchName());
                        map.put("groupcode",laBranchGroupEntities.get(i).getAgentGroup());
                        result.add(map);
                    }
                }
                if (result.size()==0){
                    return R.ok().put("msg","请先增加无主管团队 再添加主管");
                }
            }
        }
        return R.ok().put("list",result);
    }

}

package com.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cms.common.IdCheck;
import com.cms.common.SelectAgentGroupInfo;
import com.cms.dao.YlLaAgentDao;
import com.cms.entity.YlLaAgentEntity;
import com.cms.entity.YlLaBranchGroupEntity;
import com.cms.pojo.GradeTeamPojo;
import com.cms.pojo.LaAgentPojo;
import com.cms.pojo.LaAgentUpdatePojo;
import com.cms.service.AgentGroup;
import com.cms.service.RYlLaBranchGroupService;
import com.cms.service.YlAgentInfoService;
import com.cms.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author :zhanhaoze
 * @description :
 * @create :2021-08-03
 * 此类提供两个方法,laAgentSubmit(LaAgentPojo laAgent)用于在数据库的yl_la_agent表插入数据,
 * laAgentUpdate(LaAgentUpdatePojo laAgent)用于在数据库的yl_la_agent表更新数据
 */
@Service
@Slf4j
public class YlAgentInfoServiceImpl extends ServiceImpl<YlLaAgentDao, YlLaAgentEntity> implements YlAgentInfoService {
    @Autowired
    public IdCheck idCheck;

    @Autowired
    private SelectAgentGroupInfo selectAgentGroupInfo;

    /**
     * 增加人员信息方法，传入LaAgentPojo，返回boolean值结果
     */
    @Autowired
    RYlLaBranchGroupServiceImpl rYlLaBranchGroupServiceImpl;
    @Override
    public boolean laAgentSubmit(LaAgentPojo laAgent){
        //在此处调用创建YlLaAgentEntity的方法，用于数据库操作
        YlLaAgentEntity ylLaAgentEntity = this.buildAgentEntity(laAgent);

        //存入数据库
        int result = this.baseMapper.insert(ylLaAgentEntity);
        if(result > 0){
            return true;
        }
        return false;
    }

    /**
     * 修改人员信息方法，传入LaAgenUpdatetPojo，返回boolean值结果
     */
    @Override
    public boolean laAgentUpdate(LaAgentUpdatePojo laAgent) {
        YlLaAgentEntity ylLaAgentEntity = this.buildUpdateAgentEntity(laAgent);
        UpdateWrapper<YlLaAgentEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("agent_code",laAgent.getAgentCode());
        int affectRows = this.baseMapper.update(ylLaAgentEntity,updateWrapper);
        if(affectRows > 0){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * 此方法传入一个LaAgentPojo对象，返回一个YlLaAgentEntity对象，YlLaAgentEntity可以用于对数据库中yl_la_agent表进行insert操作
     */
    private YlLaAgentEntity buildAgentEntity(LaAgentPojo laAgent){
        YlLaAgentEntity ylLaAgentEntity = new YlLaAgentEntity();
        ylLaAgentEntity.setAgentCode(laAgent.getAgentCode());
        ylLaAgentEntity.setEmployDate(laAgent.getEmployDate());
        ylLaAgentEntity.setAgentGrade(laAgent.getAgentGrade());
        ylLaAgentEntity.setInitGrade(laAgent.getAgentGrade());
        ylLaAgentEntity.setAgentJob(laAgent.getAgentJob());
        ylLaAgentEntity.setAgentName(laAgent.getAgentName());
        ylLaAgentEntity.setSapCode(laAgent.getSapCode());
        ylLaAgentEntity.setManageCom(laAgent.getManageCom4());
        ylLaAgentEntity.setAgentGroup(selectAgentGroupInfo.branchAttrToAgentGroup(laAgent.getBranchAttr()));
        ylLaAgentEntity.setRepeatFlag(this.checkRepeatFlag(laAgent));//调用wxy的返回接口
        ylLaAgentEntity.setOperator(laAgent.getOperator());
        /*****************************************************************************/
        ylLaAgentEntity.setAgentState("01");
        ylLaAgentEntity.setBranchType("4");
        /*****************************************************************************/
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("HH-mm-ss");
        String time = df.format(date);
        ylLaAgentEntity.setMakeDate(date);
        ylLaAgentEntity.setMakeTime(time);
        ylLaAgentEntity.setModifyDate(date);
        ylLaAgentEntity.setModifyTime(time);
        return ylLaAgentEntity;
    }

    /**
     * 此方法传入一个LaAgentUpdatePojo对象，返回一个YlLaAgentEntity对象，用于yl_la_agent表的update操作
     *
     */
    private YlLaAgentEntity buildUpdateAgentEntity(LaAgentUpdatePojo laAgent){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("agent_code",laAgent.getAgentCode());
        YlLaAgentEntity ylLaAgentEntity = this.baseMapper.selectOne(queryWrapper);
        ylLaAgentEntity.setAgentGrade(laAgent.getAgentGrade());
        ylLaAgentEntity.setOperator(laAgent.getOperator());
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("HH-mm-ss");
        String time = df.format(date);
        ylLaAgentEntity.setModifyDate(date);
        ylLaAgentEntity.setModifyTime(time);
        return ylLaAgentEntity;
    }

    /**
     * 检查是否为二次入司,如果是返回代码"05"，如果不是根据情况返回"02"
     *
     */
    private String checkRepeatFlag(LaAgentPojo laAgent){
        int f = idCheck.idcheck(laAgent);
        if(f == 4){
            return "05";
        }
        else{
            return "02";
        }
    }
    //进行根据职级返回架构团队的代码
    @Override
    public R getgradeteam(GradeTeamPojo gradeTeamPojo) {
        //与前端协商 只有当她请求
        String oldgrade=gradeTeamPojo.getOldgradecode();
        String nowgrade=gradeTeamPojo.getNowgradecode();
        String comcode=gradeTeamPojo.getComecode();
        List result=new ArrayList();
        if (comcode.equals(null)||StringUtils.isEmpty(comcode)){
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
                YlLaAgentEntity ylLaAgentEntity = this.getBaseMapper().selectOne(queryWrapper);
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
                    if (laBranchGroupEntities.get(i).getBranchManager()==null|| StringUtils.isEmpty(laBranchGroupEntities.get(i).getBranchManager())){
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

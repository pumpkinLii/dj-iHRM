package com.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cms.dao.*;
import com.cms.entity.YlLaAgentAttrEntity;
import com.cms.entity.YlLaAgentEntity;
import com.cms.entity.YlLaAgentManoeuvreEntity;
import com.cms.entity.YlLaBranchGroupEntity;
import com.cms.pojo.RankAlterPojo;
import com.cms.service.ExcelLaAgentService;
import com.cms.service.QueryStaffService;
import com.cms.service.RankAlterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class RankAlterServiceImpl extends ServiceImpl<RankAlterDao, YlLaAgentEntity> implements RankAlterService {
    @Autowired
    RankAlterTrackDao rankAltexrTrackDao;

    @Autowired
    RankAlterGroupDao rankAlterGroupDao;

    @Autowired
    LaAgentAttrDao laAgentAttrDao;


    public String rankAlter(RankAlterPojo rankAlterPojo) {
        QueryWrapper<YlLaAgentEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("agent_code",rankAlterPojo.getAgentCode());
        YlLaAgentEntity old = this.baseMapper.selectOne(queryWrapper);

        QueryWrapper<YlLaAgentAttrEntity> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("agent_code",rankAlterPojo.getAgentCode());
        YlLaAgentAttrEntity old1 = laAgentAttrDao.selectOne(queryWrapper1);

        if(rankAlterPojo.getTargetAgentGrade()==null||rankAlterPojo.getTargetAgentGrade().equals(""))
        {
            return "目标职级不能为空";
        }
        if(rankAlterPojo.getTargetBranchCode()==null||rankAlterPojo.getTargetBranchCode().equals(""))
        {
            return "目标团队架构不能为空";
        }
        if(rankAlterPojo.getTargetManageCom()==null||rankAlterPojo.getTargetManageCom().equals(""))
        {
            return "目标四级管理机构不能为空";
        }
        if(rankAlterPojo.getAlterCause()==null||rankAlterPojo.getAlterCause().equals(""))
        {
            return "调整原因不能为空";
        }
        if(!rankAlterPojo.getAgentState().equals("01"))
        {
            return "当前人员不处于在职状态，无法进行职级调动";
        }
        if(rankAlterPojo.getTargetAgentGrade().equals(rankAlterPojo.getCurAgentGrade()))
        {
            return "当前职级和目标职级一致，不允许保存";
        }
        String cur=rankAlterPojo.getCurAgentGrade().substring(0,2);
        String target=rankAlterPojo.getTargetAgentGrade().substring(0,2);
        boolean fg=false;
        if(cur.equals(target))
        {
            fg=true;
        }
        YlLaAgentManoeuvreEntity ylLaAgentManoeuvreEntity=new YlLaAgentManoeuvreEntity();
        String edorNo=buildNewEdorNo();
        ylLaAgentManoeuvreEntity.setEdorNo(edorNo);
        ylLaAgentManoeuvreEntity.setEdorType("02");
        ylLaAgentManoeuvreEntity.setFunctType("04");
        ylLaAgentManoeuvreEntity.setAdjustReason(rankAlterPojo.getAlterCause());
        ylLaAgentManoeuvreEntity.setAgentCode(rankAlterPojo.getAgentCode());
        ylLaAgentManoeuvreEntity.setBranchType("4");
        Date date=new Date();
        ylLaAgentManoeuvreEntity.setManoeuvreDate(date);

        ylLaAgentManoeuvreEntity.setPreManageCom(old.getManageCom());
        ylLaAgentManoeuvreEntity.setCurManageCom(rankAlterPojo.getTargetManageCom());

        ylLaAgentManoeuvreEntity.setPreBranchCode(old.getAgentCode());
        ylLaAgentManoeuvreEntity.setCurBranchCode(rankAlterPojo.getAgentGroup());

        ylLaAgentManoeuvreEntity.setPreAgentGrade(rankAlterPojo.getCurAgentGrade());
        ylLaAgentManoeuvreEntity.setCurAgentGrade(rankAlterPojo.getTargetAgentGrade());
        ylLaAgentManoeuvreEntity.setPreDepartment(rankAlterPojo.getAgentGroup());
        ylLaAgentManoeuvreEntity.setCurDepartment(rankAlterPojo.getTargetBranchCode());


        if(fg==false)
        {
            //调整前的主管编号
            QueryWrapper<YlLaBranchGroupEntity> qw = new QueryWrapper<>();
            qw.eq("agent_group",rankAlterPojo.getAgentGroup());
            YlLaBranchGroupEntity group = rankAlterGroupDao.selectOne(qw);
            ylLaAgentManoeuvreEntity.setPreDepartmentManager(group.getBranchManager());

            //调整后的主管编号
            QueryWrapper<YlLaBranchGroupEntity> qw1 = new QueryWrapper<>();
            qw1.eq("agent_group",rankAlterPojo.getTargetBranchCode());
            YlLaBranchGroupEntity group1 = rankAlterGroupDao.selectOne(qw1);
            if(group1.getBranchManager()==null||group1.getBranchManager().equals(""))
            {
                ylLaAgentManoeuvreEntity.setCurDepartmentManager(rankAlterPojo.getAgentCode());
            }
            else{
                ylLaAgentManoeuvreEntity.setCurDepartmentManager(group1.getBranchManager());
            }

            ylLaAgentManoeuvreEntity.setCurAgentGroupName(group1.getBranchName());
        }

        ylLaAgentManoeuvreEntity.setOperator("wxy");
        SimpleDateFormat df = new SimpleDateFormat("HH-mm-ss");
        String time = df.format(date);
        ylLaAgentManoeuvreEntity.setMakeDate(date);
        ylLaAgentManoeuvreEntity.setMakeTime(time);
        ylLaAgentManoeuvreEntity.setModifyDate(date);
        ylLaAgentManoeuvreEntity.setModifyTime(time);
        int insert = rankAltexrTrackDao.insert(ylLaAgentManoeuvreEntity);

        if(insert<=0)
        {
            return "出现未知异常，职级调整失败";
        }
        if(cur.equals(target)) {//不跨序列
            UpdateWrapper<YlLaAgentEntity> updateWrapper=new UpdateWrapper<>();
            updateWrapper.set("agent_grade",rankAlterPojo.getTargetAgentGrade());
            updateWrapper.eq("agent_code",rankAlterPojo.getAgentCode());
            int affectRows = this.baseMapper.update(null,updateWrapper);
            if(affectRows<=0)
            {
                return "出现未知异常，职级调整失败";
            }
        }
        else{
            if(cur.equals("YL")) {//YL:客户经理升总监 升职

                //团队表更新
                UpdateWrapper<YlLaBranchGroupEntity> updateWrapper1=new UpdateWrapper<>();
                updateWrapper1.set("branch_manager",rankAlterPojo.getAgentCode());
                updateWrapper1.set("branch_manager_name",old.getAgentName());
                updateWrapper1.set("branch_manager_phone",old1.getPhone());
                updateWrapper1.set("manager_eff_date",date);
                updateWrapper1.eq("agent_group",rankAlterPojo.getTargetBranchCode());
                int affectRows1 = rankAlterGroupDao.update(null,updateWrapper1);
                if(affectRows1<=0)
                {
                    return "出现未知异常，职级调整失败";
                }
                //人员表更新
                UpdateWrapper<YlLaAgentEntity> updateWrapper2=new UpdateWrapper<>();
                updateWrapper2.set("agent_job","0");
                updateWrapper2.set("agent_grade",rankAlterPojo.getTargetAgentGrade());
                updateWrapper2.set("agent_group",rankAlterPojo.getTargetBranchCode());
                updateWrapper2.eq("agent_code",rankAlterPojo.getAgentCode());
                int affectRows2 = this.baseMapper.update(null,updateWrapper2);

                if(affectRows2<=0)
                {
                    return "出现未知异常，职级调整失败";
                }
            }
            else {//总监到客户经理 降职
                //团队表更新
                UpdateWrapper<YlLaBranchGroupEntity> updateWrapper3=new UpdateWrapper<>();
                updateWrapper3.set("branch_manager",null);
                updateWrapper3.set("branch_manager_name",null);
                updateWrapper3.set("branch_manager_phone",null);
                updateWrapper3.set("manager_eff_date",null);
                updateWrapper3.eq("agent_group",rankAlterPojo.getTargetBranchCode());
                int affectRows3 = rankAlterGroupDao.update(null,updateWrapper3);
                if(affectRows3<=0)
                {
                    return "出现未知异常，职级调整失败";
                }
                //人员表更新
                UpdateWrapper<YlLaAgentEntity> updateWrapper4=new UpdateWrapper<>();
                updateWrapper4.set("agent_job","1");
                updateWrapper4.set("agent_grade",rankAlterPojo.getTargetAgentGrade());
                updateWrapper4.set("agent_group",rankAlterPojo.getTargetBranchCode());
                updateWrapper4.eq("agent_code",rankAlterPojo.getAgentCode());
                int affectRows4 = this.baseMapper.update(null,updateWrapper4);

                if(affectRows4<=0)
                {
                    return "出现未知异常，职级调整失败";
                }
            }
        }
        return "保存成功";
    }

    /**
     * 自动生成转储号码
     * */
    public String buildNewEdorNo(){
        QueryWrapper<YlLaAgentManoeuvreEntity> queryWrapper = new QueryWrapper<>();
        List<YlLaAgentManoeuvreEntity> resultSet = rankAltexrTrackDao.selectList(queryWrapper);
        if(resultSet.size() == 0){
            return "YD00000001";
        }
        else{
            YlLaAgentManoeuvreEntity ylLaAgentManoeuvreEntity = resultSet.get(resultSet.size() - 1);
            String newEdorNo;
            newEdorNo = ylLaAgentManoeuvreEntity.getEdorNo();
            newEdorNo = newEdorNo.substring(2);
            int edorNo = Integer.parseInt(newEdorNo) + 1;
            return "YL" + String.format("%08d", edorNo);
        }
    }

}

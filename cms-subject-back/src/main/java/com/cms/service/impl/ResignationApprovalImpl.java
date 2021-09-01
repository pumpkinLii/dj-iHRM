package com.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cms.common.ParseDate;
import com.cms.dao.YlLaDimissionDao;
import com.cms.dao.YlLaDimissionDao;
import com.cms.entity.YlLaAgentEntity;
import com.cms.entity.YlLaAgentManoeuvreEntity;
import com.cms.entity.YlLaBranchGroupEntity;
import com.cms.entity.YlLaDimissionEntity;
import com.cms.pojo.ResignationApprovalPojo;
import com.cms.service.ResignationApprovalService;
import com.cms.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
public class ResignationApprovalImpl extends ServiceImpl<YlLaDimissionDao, YlLaDimissionEntity> implements ResignationApprovalService {
    @Autowired
    RYlLaBranchGroupServiceImpl rYlLaBranchGroupService;
    @Autowired
    YlAgentInfoServiceImpl ylAgentInfoServiceimpl;
    @Autowired
    YlLaAgentManoeuvreServiceImpl ylLaAgentManoeuvreServiceimpl;
    @Override
    public R approval(ResignationApprovalPojo resignationApprovalPojo,int num) throws ParseException {
        List<String> agentGroupList = resignationApprovalPojo.getAgentGroupList();//团队代码集合
        List<String> agentStateList = resignationApprovalPojo.getAgentStateList();//人员状态集合
        List<String> agentCodeList = resignationApprovalPojo.getAgentCodeList();//人员代码集合
        List<String> manageComList = resignationApprovalPojo.getManageComList();//管理机构代码
        List<String> agydepartStateList = resignationApprovalPojo.getAgydepartStateList();//审核状态集合
        List<String> reasonList = resignationApprovalPojo.getReasonList();
        if (agentCodeList.size() == 0) {
            return R.ok("请选择有效数据再提交请求").put("code", 501);
        }
        for (int i = 0; i < agydepartStateList.size(); i++) {
            //数据校验 检查前端给的数据是否符合格式
            if (agydepartStateList.get(i).equals("2") == false) {
                return R.ok("提交的数据不符合条件，请申请已提交审核的数据 ").put("code", 501);
            }
        }
        for (int i = 0; i < agentCodeList.size(); i++) {
            //人员离职表的修改
            String agentGroup = agentGroupList.get(i);
            String agentState = agentStateList.get(i);
            String agentCode = agentCodeList.get(i);
            String manageCom = manageComList.get(i);
            String agydepartState = agydepartStateList.get(i);
            String reason = reasonList.get(i);

            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("agent_code", agentCode);
            queryWrapper.eq("depart_state", "2");//赛选已提交审核的内容
            List list = this.baseMapper.selectList(queryWrapper);
            if (list.size() == 0) {
                return R.ok("人员离职数据库中并无该员工");
            }
            YlLaDimissionEntity ylLaDimissionEntity = (YlLaDimissionEntity) list.get(0);
            if (num == 1) {
                ylLaDimissionEntity.setDepartTimes(ylLaDimissionEntity.getDepartTimes() + 1);
                ylLaDimissionEntity.setDepartState("4");//3 审核不通过  4审核通过
                ylLaDimissionEntity.setModifyDate(ParseDate.getCurrentDate());
                ylLaDimissionEntity.setModifyTime(ParseDate.getCurrentTime());
                this.baseMapper.update(ylLaDimissionEntity, queryWrapper);
                //人员异动表的修改
                YlLaAgentManoeuvreEntity ylLaAgentManoeuvreEntity = new YlLaAgentManoeuvreEntity();
                ylLaAgentManoeuvreEntity.setEdorNo(buildNewEdorNo());
                ylLaAgentManoeuvreEntity.setEdorType("04");//离司
                ylLaAgentManoeuvreEntity.setFunctType("06");//离司
                ylLaAgentManoeuvreEntity.setBranchType("4");//
                ylLaAgentManoeuvreEntity.setAdjustReason(reason);
                ylLaAgentManoeuvreEntity.setAgentCode(agentCode);
                ylLaAgentManoeuvreEntity.setManoeuvreDate(ParseDate.getCurrentDate());
                ylLaAgentManoeuvreEntity.setPreManageCom(manageCom);
                ylLaAgentManoeuvreEntity.setPreBranchCode(agentGroup);
                QueryWrapper qw = new QueryWrapper();
                qw.eq("agent_group", agentGroup);
                YlLaBranchGroupEntity ylLaBranchGroupEntity1 = rYlLaBranchGroupService.getBaseMapper().selectOne(qw);
                if (ylLaBranchGroupEntity1 != null) {
                    ylLaAgentManoeuvreEntity.setPreDepartmentManager(ylLaBranchGroupEntity1.getBranchManager());
                    ylLaAgentManoeuvreEntity.setPreBranchAttr(ylLaBranchGroupEntity1.getBranchAttr());
                } else {
                    return R.ok("团队表中没有该团队").put("code", 501);
                }
                ylLaAgentManoeuvreEntity.setPreAgentGrade(agentState);
                ylLaAgentManoeuvreEntity.setPreAgentState("01");//在职
                ylLaAgentManoeuvreEntity.setCurManageCom(null);
                ylLaAgentManoeuvreEntity.setCurBranchAttr(null);
                ylLaAgentManoeuvreEntity.setCurBranchCode(null);
                ylLaAgentManoeuvreEntity.setCurAgentGrade(null);
                ylLaAgentManoeuvreEntity.setCurAgentState("04");
                ylLaAgentManoeuvreEntity.setOperator("0");
                ylLaAgentManoeuvreEntity.setMakeDate(ParseDate.getCurrentDate());
                ylLaAgentManoeuvreEntity.setMakeTime(ParseDate.getCurrentTime());
                ylLaAgentManoeuvreEntity.setModifyDate(ParseDate.getCurrentDate());
                ylLaAgentManoeuvreEntity.setModifyTime(ParseDate.getCurrentTime());
                ylLaAgentManoeuvreServiceimpl.getBaseMapper().insert(ylLaAgentManoeuvreEntity);
                QueryWrapper queryWrapper1 = new QueryWrapper();
                queryWrapper1.eq("agent_code", agentCode);
                YlLaAgentEntity ylLaAgentEntity = ylAgentInfoServiceimpl.getBaseMapper().selectOne(queryWrapper1);
                if (ylLaAgentEntity == null) {
                    return R.ok("异常操作 人员表中无该员工");
                }
                ylLaAgentEntity.setAgentState("04");//设置离职
                ylLaAgentEntity.setOutWorkDate(ParseDate.getCurrentDate());
                ylLaAgentEntity.setDoneDate(ParseDate.getCurrentDate());
                ylLaAgentEntity.setModifyTime(ParseDate.getCurrentTime());
                ylLaAgentEntity.setModifyDate(ParseDate.getCurrentDate());
                ylAgentInfoServiceimpl.getBaseMapper().update(ylLaAgentEntity, queryWrapper1);//跟新状态完成

                if (agentState.substring(0, 2).equals("MA")) {
                    QueryWrapper queryWrapper2 = new QueryWrapper();
                    queryWrapper2.eq("branch_manager", agentCode);
                    List list1 = rYlLaBranchGroupService.getBaseMapper().selectList(queryWrapper2);
                    if (list1.size() == 0) {

                    } else {
                        YlLaBranchGroupEntity ylLaBranchGroupEntity = (YlLaBranchGroupEntity) list1.get(0);
                        ylLaBranchGroupEntity.setBranchManager(null);
                        ylLaBranchGroupEntity.setBranchManagerName(null);
                        ylLaBranchGroupEntity.setBranchManagerPhone(null);
                        ylLaBranchGroupEntity.setModifyDate(ParseDate.getCurrentDate());
                        ylLaBranchGroupEntity.setModifyTime(ParseDate.getCurrentTime());
                        rYlLaBranchGroupService.getBaseMapper().update(ylLaBranchGroupEntity, queryWrapper2);
                    }
                }

            } else {
                ylLaDimissionEntity.setDepartState("3");
                ylLaDimissionEntity.setModifyDate(ParseDate.getCurrentDate());
                ylLaDimissionEntity.setModifyTime(ParseDate.getCurrentTime());
                this.baseMapper.update(ylLaDimissionEntity, queryWrapper);
            }
        }
        if (num==0){
            return R.ok("审核未通过,操作完成");
        }else {
            return R.ok("审核通过,操作完成");
        }
    }

    String buildNewEdorNo(){
        QueryWrapper<YlLaAgentManoeuvreEntity> queryWrapper = new QueryWrapper<>();
        List<YlLaAgentManoeuvreEntity> resultSet = ylLaAgentManoeuvreServiceimpl.getBaseMapper().selectList(queryWrapper);
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

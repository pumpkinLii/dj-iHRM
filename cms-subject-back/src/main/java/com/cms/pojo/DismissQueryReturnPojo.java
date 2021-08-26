package com.cms.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
public class DismissQueryReturnPojo {
    String agentCode;       //人员工号
    String agentName;       //人员姓名
    String comCode4;        //四级管理机构代码
    String manageCom4;      //四级管理机构名称
    String agentGroup;      //团队代码
    String branchName;      //团队名称
    String agentGradeCom;      //职级代码
    String agentGradeName;      //职级名称
    String diffDate;      //解约日期
    String diffCauseCom;       //解约原因代码
    String diffCauseName;       //解约原因名称
    String illustrate;     //说明
    String agentStateCom;      //审核状态代码
    String agentStateName;      //审核状态名称
    String branchAttr;    //团队外码
}

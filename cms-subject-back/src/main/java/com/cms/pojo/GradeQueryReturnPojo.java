package com.cms.pojo;

import lombok.Data;

@Data
public class GradeQueryReturnPojo {
    private String agentCode;  //人员工号
    private String agentName;  //人员姓名
    private String manageCom4;
    private String comCode4;
    private String manageCom3;  //分公司
    private String comCode3;
    private String manageCom2;  //中心支公司
    private String comCode2;
    private String agentGroup;  //团队代码
    private String branchName;  //团队名称
    private String employDate;  //入司日期   date
    private String agentState;  //人员状态
    private String agentStateName;
    private String contractType;  //合同类型
    private String contractTypeName;
    private String agentGrade;  //职级
    private String agentGradeName;
}

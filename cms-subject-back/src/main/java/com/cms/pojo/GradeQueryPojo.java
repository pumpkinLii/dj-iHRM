package com.cms.pojo;

import lombok.Data;

@Data
public class GradeQueryPojo {
    private String manageCom;    //管理机构
    private String agentGroup;   //团队代码
    private String branchName;   //团队名称
    private String agentCode;       //代理人代码
    private String agentName;   //代理人姓名
    private String phone;  //手机号
    private String idNo;         //证件号码
    private String agentGrade;   //当前职级
}

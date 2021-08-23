package com.cms.pojo;

import lombok.Data;

@Data
public class DepartQueryReturnPojo {
    private String agentCode;  //人员工号
    private String agentName;  //人员姓名
    private String manageCom;  //管理机构代码
    private String manageComName;  //管理机构名称
    private String branchAttr;  //团队代码
    private String branchName;  //团队名称
    private String gradeName;   //职级
}

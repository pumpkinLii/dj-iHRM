package com.cms.pojo;

import lombok.Data;


@Data
public class ResultListPojo {
    private String agentCode;
    private String agentName;
    private String agentGroup;
    private String manageCom2;//02分公司
    private String manageCom3;//03中心支公司
    private String branchName;
    private String employDate;
    private String agentState;//人员状态
    private String contractType;
    private String agentGrade;
}

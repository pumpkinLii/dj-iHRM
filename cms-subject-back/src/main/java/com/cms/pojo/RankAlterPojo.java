package com.cms.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class RankAlterPojo {
    private String agentCode;//人员代码
    private String agentGroup;//团队代码
    private String agentState;//人员状态
    private String curAgentGrade;//当前职级
    private String targetAgentGrade;//目标职级
    private String targetBranchCode;//目标团队架构
    private String targetManageCom;//目标四级管理机构
    private  Date   alterDate;//修改日期
    private String alterCause;//调整原因
}

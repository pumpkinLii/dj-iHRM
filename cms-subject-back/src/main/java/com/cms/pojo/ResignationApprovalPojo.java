package com.cms.pojo;

import lombok.Data;

import java.util.List;

@Data
public class ResignationApprovalPojo {
        List<String> agentCodeList;//人员代码集合
        List<String> manageComList;//管理机构代码集合
        List<String> agentGroupList;//团队代码集合
        List<String> agentStateList;//人员职级集合
        List<String> agydepartStateList;//审核状态集合
        List<String> reasonList;
}

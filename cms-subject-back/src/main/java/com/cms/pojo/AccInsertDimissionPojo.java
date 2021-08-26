package com.cms.pojo;

import lombok.Data;

@Data
public class AccInsertDimissionPojo {
    private String agentCode;
    private String manageCom;//定死为4
    private String agentGroup;//branchAttr
    private String departDate;
    private String departReason;
    private String departTimes;//定死为0
    private String noti;
    private String departState;//定死为1
    private String operator;//定死为admin
}

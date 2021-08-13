package com.cms.pojo;

import lombok.Data;

import java.util.Date;
@Data
public class QueryStaffPojo {
    private String agentCode;
    private String agentName;
    private String agentState;
    private String branchAttr;
    private String manageCom2;
    private String manageCom3;
    private String manageCom4;
    private Date startDate;
    private Date endDate;
}

package com.cms.pojo;

import lombok.Data;
@Data
public class NQueryStaffReturn {
    private String agentCode;
    private String agentName;
    private String agentGrade;
    private String manageCom2="大家人寿北京分公司";
    private String manageCom3="北京分公司";
    private String manageCom4="北京分公司本部";
    private String branchAttr;
    private String branchName;
    private String agentState;
    private String employDate;
    private String outWorkDate;

    /* "agentCode": "YL00000001",
             "agentName": "01",
             "agentGrade": "MA02",
             "manageCom2": "大家人寿北京分公司",
             "manageCom3": "北京分公司",
             "manageCom4": "东城区服务部",
             "branchAttr": "1000000011",
             "branchName": "自行测选择",
             "agentState": "04",
             "employDate": "2021-08-03",
             "outWorkDate": "2021-08-09"*/
}
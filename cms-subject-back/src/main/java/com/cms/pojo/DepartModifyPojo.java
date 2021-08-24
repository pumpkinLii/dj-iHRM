package com.cms.pojo;

import lombok.Data;

@Data
public class DepartModifyPojo {
    private String agentCode;
    private String diffDate;     //解约日期 departDate
    private String diffCause;    //解约原因 departReason 码
    private String explain;      //说明noti
}

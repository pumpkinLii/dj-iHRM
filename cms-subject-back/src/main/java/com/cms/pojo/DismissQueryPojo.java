package com.cms.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
public class DismissQueryPojo {
    String manageCom;       //管理机构
    String agentGroup;      //团队代码
    String branchName;      //团队名称
    String agentCode;       //人员代码
    String agentName;       //人员姓名
    String agentGrade;      //职级
    String agydepart;      //审核状态
    String diffCause;       //解约原因
    Date diffStartDate;       //解约日期起期
    Date diffEndDate;     //解约日期止期
}

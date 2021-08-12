package com.cms.pojo;

import lombok.Data;

/**
 * 1 * @Author: zhangYiLong
 * 2 * @Date: 2021/8/9 11:21
 * 3 * @ClassName : ceInsertPojo
 */
@Data
public class CeInsertPojo {
    private String agentCode;
    private String agentName;
    private String certificateType;
    private String certificateName;
    private String certificateNo;
    private String releaseDate;
    private String reissueDate;
    private String startEffectiveDate;
    private String endEffectiveDate;
    private String approver;
    private String edorNo;
}

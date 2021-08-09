package com.cms.pojo;

import lombok.Data;

@Data
public class ChangeCertificatePojo {
    String agentCode;//人员工号
    String agentName;//人员姓名
    String certificateType;//资格证类型
    String certificateName;//资格证名称
    String oldCertificateNo;//修改前资格证号
    String certificateNol;//资格证号
    String releaseDate;//发放日期
    String reissueDate;//补发日期
    String startEffectiveDate;//有效起期 要求前端给字符串
    String endEffectiveDate;//有效停期
    String approver;//批准单位

}

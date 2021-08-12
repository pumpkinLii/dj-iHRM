package com.cms.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class CertificateConditionPojo {
    String agentCode;//人员工号
    String mangeCom2;//二级机构编码
    String mangeCom3;//三级机构编码
    String mangeCom4;//四级机构编码
    String branchAttr;//团队编码（非主码）
    String certificateType;//资格证书类型
    String startEffectiveDate;//有效起期
    String endEffectiveDate;//有效止期
    String agentName;//人员姓名
}

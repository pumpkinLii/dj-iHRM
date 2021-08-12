package com.cms.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class RetrieveCertificatePojo {
    String agentCode;//人员工号
    String agentName;//人员姓名
    String manageCom2;//二级机构代码
    String manageComName2;//二级机构名字
    String manageCom3;//三级机构代码
    String manageComName3;//三级机构名字
    String manageCom4;//四级机构代码
    String manageComName4;//四级机构名字
    String branchAttr;//团队编码
    String branchName;//团队名字
    String certificateNo;//资格证书号
    String certificateName;//资格证书名称
    String certificateCode;// 码值
    String releaseDate;//发放日期
    String reissueDate;//补发日期
    String startEffectiveDate;//有效起期
    String endEffectiveDate;//有效止期
    String approveBy;//批准单位
}

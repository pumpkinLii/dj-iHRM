package com.cms.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;
@Data
@Component
public class YlSelectExcellPojo {
    private String manageCom2;//二级管理机构代码*
    private String manageName2;//二级管理机构名称*
    private String manageCom3;//三级管理机构代码*
    private String manageName3;//二级管理机构名称*
    private String manageCom4;//四级管理机构代码*
    private String manageName4;//四级管理机构名称*
    private String agentCode;//工号
    private String sapCode; // SAP工号
    private String agentName;// 姓名*
    private String agentState;//人员状态
    private String agentJob;//岗位
    private String agentGrade;//人员职级
    private String sex;// 性别*
    private String birthday; // 出生日期*
    private String idType;// 证件类型*
    private String idNo;// 证件号码*
    private String employDate;// 入司日期*
    private String outDate;// 离司日期*
    private String rgtType;// 户口类型*
    private String rgtProvince;// 户口所在省*
    private String highestDegree;// 最高学历*
    private String firstDegree;// 第一学历
    private String degree;// 最高学位*
    private String graduateSchool;// 毕业院校*
    private String major;// 专业*
    private String nationality;//民族*
    private String nativeplace;// 籍贯*
    private String oldIndustryType; // 最近一份工作行业类型*
    private String oldOccupation; // 最近一份职业*
    private String oldCom;// 最近一家工作单位*
    private String oldJobDuty; // 最近一份工作职务*
    private String workAge;// 从业年限*
    private String homeAddress;// 家庭地址*
    private String postcode;// 邮编
    private String homephone;// 住宅电话
    private String phone;// 手机*
    private String email;// E-mail*
    private String outlookStatus;// 政治面貌*
    private String bankCode;// 账户银行总行*
    private String bankAccount;// 银行账号*
    private String uniteBankNum;// 银行卡开户行联行号*
    private String bankProvince;// 开户行省份*
    private String bankCity;// 开户行所在市*
    private String contractType;// 合同类型*
    private String contractStartDate;// 劳动合同起期*
    private String contractEndDate;// 劳动合同止期*
    private String branchAttr;// 团队架构代码*
    private String branchName;// 团队架构名称
    private String branchManagerCode;// 团队主管工号
    private String branchManagerName;// 团队主管姓名
    private String branchManagerPhone;// 团队主管手机号

    @Override
    public String toString() {
        return
                manageCom2 +
                "," + manageName2 +
                "," + manageCom3 +
                ","+ manageName3 +
                "," + manageCom4 +
                "," + manageName4 +
                "," + agentCode +
                "," + sapCode +
                "," + agentName +
                "," + agentState +
                "," + agentJob +
                "," + agentGrade +
                "," + sex +
                "," + birthday +
                "," + idType +
                "," + idNo +
                "," + employDate +
                "," + outDate +
                "," + rgtType +
                "," + rgtProvince +
                "," + highestDegree +
                "," + firstDegree +
                "," + degree +
                "," + graduateSchool +
                "," + major +
                "," + nationality +
                "," + nativeplace +
                "," + oldIndustryType +
                "," + oldOccupation +
                "," + oldCom +
                "," + oldJobDuty +
                "," + workAge +
                "," + homeAddress +
                "," + postcode +
                "," + homephone +
                "," + phone +
                "," + email +
                "," + outlookStatus +
                "," + bankCode +
                "," + bankAccount +
                "," + uniteBankNum +
                "," + bankProvince +
                "," + bankCity +
                "," + contractType +
                "," + contractStartDate +
                "," + contractEndDate +
                "," + branchAttr +
                "," + branchName +
                "," + branchManagerCode +
                "," + branchManagerName +
                ","+ branchManagerPhone ;
    }
}

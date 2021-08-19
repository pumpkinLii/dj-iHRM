package com.cms.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.io.Serializable;

@Data
@HeadRowHeight(30)
@ContentRowHeight(18)
@ColumnWidth(30)
public class YlLaAgentAttrExcelUpdatePojo  extends BaseRowModel implements Serializable {
    @ExcelProperty(value = "二级管理机构代码",index = 0)
    private String comCode2;
    @ExcelProperty(value = "二级管理机构名称",index = 1)
    private String comName2;
    @ExcelProperty(value = "三级管理机构代码",index = 2)
    private String comCode3;
    @ExcelProperty(value = "三级管理机构名称",index = 3)
    private String comName3;
    @ExcelProperty(value = "四级管理机构代码",index = 4)
    private String comCode4;
    @ExcelProperty(value = "四级管理机构名称",index = 5)
    private String comName4;
    @ExcelProperty(value = "工号",index = 6)
    private String agentCode;
    @ExcelProperty(value = "SAP工号",index = 7)
    private String spaagentCode;
    @ExcelProperty(value = "姓名",index = 8)
    private String name;
    @ExcelProperty(value = "人员状态",index = 9)
    private String agentState;
    @ExcelProperty(value = "岗位",index = 10)
    private String agentJob;
    @ExcelProperty(value = "人员职级",index = 11)
    private String agentGrade;
    @ExcelProperty(value = "性别",index = 12)
    private String sex;
    @ExcelProperty(value = "出生日期",index = 13)
    private String birthday;
    @ExcelProperty(value = "证件类型",index = 14)
    private String idtype;
    @ExcelProperty(value = "证件号码",index = 15)
    private String idno;
    @ExcelProperty(value = "入司日期",index = 16)
    private String employDate;
    @ExcelProperty(value = "离司日期",index = 17)
    private String outWorkDate;
    @ExcelProperty(value = "户口类型",index = 18)
    private String rgtType;
    @ExcelProperty(value = "户口所在省",index = 19)
    private String regAddress;
    @ExcelProperty(value = "最高学历",index = 20)
    private String highestDegree;
    @ExcelProperty(value = "第一学历",index = 21)
    private String firstDegree;
    @ExcelProperty(value = "最高学位",index = 22)
    private String degree;
    @ExcelProperty(value = "毕业院校",index = 23)
    private String graduateSchool;
    @ExcelProperty(value = "专业",index = 24)
    private String major;
    @ExcelProperty(value = "名族",index = 25)
    private String nationality;
    @ExcelProperty(value = "籍贯",index = 26)
    private String nativeplace;
    @ExcelProperty(value = "最近一份工作行业类型",index = 27)
    private String oldIndustryType;
    @ExcelProperty(value = "最近一份职业",index = 28)
    private String oldOccupation;
    @ExcelProperty(value = "最近一家工作单位",index = 29)
    private String oldCom;
    @ExcelProperty(value = "最近一份工作职务",index = 30)
    private String oldJodDuty;
    @ExcelProperty(value = "从业年限",index = 31)
    private String workage;
    @ExcelProperty(value = "家庭住址",index = 32)
    private String address;
    @ExcelProperty(value = "邮政编码",index = 33)
    private String postCode;
    @ExcelProperty(value = "住宅电话",index = 34)
    private String homephone;
    @ExcelProperty(value = "手机",index = 35)
    private String phone;
    @ExcelProperty(value = "E-mail",index = 36)
    private String email;
    @ExcelProperty(value = "政治面貌",index = 37)
    private String outLookStatus;
    @ExcelProperty(value = "账户银行总行",index = 38)
    private String bankCode;
    @ExcelProperty(value = "银行账号",index = 39)
    private String brankAccount;
    @ExcelProperty(value = "银行卡开户行联行号",index = 40)
    private String uniteBankNum;
    @ExcelProperty(value = "开户行省份",index = 41)
    private String bankProvince;
    @ExcelProperty(value = "开户行市辖区",index = 42)
    private String bankCity;
    @ExcelProperty(value = "合同类型",index = 43)
    private String contractType;
    @ExcelProperty(value = "劳动合同起期",index = 44)
    private String contractStartDate;
    @ExcelProperty(value = "劳动合同止期",index = 45)
    private String ContractEndDate;
    @ExcelProperty(value = "团队架构代码",index = 46)
    private String agentGroup;
    @ExcelProperty(value = "团队架构名称",index = 47)
    private String branchName;
    @ExcelProperty(value = "团队主管工号",index = 48)
    private String branchManager;
    @ExcelProperty(value = "团队主管姓名",index = 49)
    private String branchManagerName;
    @ExcelProperty(value = "团队主管手机号",index = 50)
    private String branchManagerPhone;
}

package com.cms.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@HeadRowHeight(20)
@ContentRowHeight(18)
@ColumnWidth(30)
public class YlLaAgentAttrExcelUpdatePojo  extends BaseRowModel implements Serializable {
    @ExcelProperty(value = {"人员信息收集","二级管理机构代码"},index = 0)
    private String comCode2;
    @ExcelProperty(value = {"人员信息收集","二级管理机构名称"},index = 1)
    private String comName2;
    @ExcelProperty(value ={"人员信息收集","三级管理机构代码"},index = 2)
    private String comCode3;
    @ExcelProperty(value = {"人员信息收集","三级管理机构名称"},index = 3)
    private String comName3;
    @ExcelProperty(value = {"人员信息收集","四级管理机构代码"},index = 4)
    private String comCode4;
    @ExcelProperty(value = {"人员信息收集","四级管理机构名称"},index = 5)
    private String comName4;
    @ExcelProperty(value = {"人员信息收集","工号"},index = 6)
    private String agentCode;
    @ExcelProperty(value ={"人员信息收集", "SAP工号"},index = 7)
    private String spaagentCode;
    @ExcelProperty(value = {"人员信息收集","姓名"},index = 8)
    private String name;
    @ExcelProperty(value = {"人员信息收集","人员状态"},index = 9)
    private String agentState;
    @ExcelProperty(value = {"人员信息收集","岗位"},index = 10)
    private String agentJob;
    @ExcelProperty(value = {"人员信息收集","人员职级"},index = 11)
    private String agentGrade;
    @ExcelProperty(value = {"人员信息收集","性别"},index = 12)
    private String sex;
    @ExcelProperty(value = {"人员信息收集","出生日期"},index = 13)
    @DateTimeFormat("yyyy/MM/dd")
    private Date birthday;
    @ExcelProperty(value = {"人员信息收集","证件类型"},index = 14)
    private String idtype;
    @ExcelProperty(value = {"人员信息收集","证件号码"},index = 15)
    private String idno;
    @ExcelProperty(value = {"人员信息收集","入司日期"},index = 16)
    @DateTimeFormat("yyyy/MM/dd")
    private Date employDate;
    @ExcelProperty(value = {"人员信息收集","离司日期"},index = 17)
    @DateTimeFormat("yyyy/MM/dd")
    private Date outWorkDate;
    @ExcelProperty(value = {"人员信息收集","户口类型"},index = 18)
    private String rgtType;
    @ExcelProperty(value = {"人员信息收集","户口所在省"},index = 19)
    private String regAddress;
    @ExcelProperty(value = {"人员信息收集","最高学历"},index = 20)
    private String highestDegree;
    @ExcelProperty(value = {"人员信息收集","第一学历"},index = 21)
    private String firstDegree;
    @ExcelProperty(value = {"人员信息收集","最高学位"},index = 22)
    private String degree;
    @ExcelProperty(value = {"人员信息收集","毕业院校"},index = 23)
    private String graduateSchool;
    @ExcelProperty(value = {"人员信息收集","专业"},index = 24)
    private String major;
    @ExcelProperty(value = {"人员信息收集","名族"},index = 25)
    private String nationality;
    @ExcelProperty(value = {"人员信息收集","籍贯"},index = 26)
    private String nativeplace;
    @ExcelProperty(value = {"人员信息收集","最近一份工作行业类型"},index = 27)
    private String oldIndustryType;
    @ExcelProperty(value ={"人员信息收集", "最近一份职业"},index = 28)
    private String oldOccupation;
    @ExcelProperty(value = {"人员信息收集","最近一家工作单位"},index = 29)
    private String oldCom;
    @ExcelProperty(value = {"人员信息收集","最近一份工作职务"},index = 30)
    private String oldJodDuty;
    @ExcelProperty(value = {"人员信息收集","从业年限"},index = 31)
    private String workage;
    @ExcelProperty(value = {"人员信息收集","家庭住址"},index = 32)
    private String address;
    @ExcelProperty(value = {"人员信息收集","邮政编码"},index = 33)
    private String postCode;
    @ExcelProperty(value = {"人员信息收集","住宅电话"},index = 34)
    private String homephone;
    @ExcelProperty(value = {"人员信息收集","手机"},index = 35)
    private String phone;
    @ExcelProperty(value = {"人员信息收集","E-mail"},index = 36)
    private String email;
    @ExcelProperty(value = {"人员信息收集","政治面貌"},index = 37)
    private String outLookStatus;
    @ExcelProperty(value = {"人员信息收集","账户银行总行"},index = 38)
    private String bankCode;
    @ExcelProperty(value = {"人员信息收集","银行账号"},index = 39)
    private String brankAccount;
    @ExcelProperty(value = {"人员信息收集","银行卡开户行联行号"},index = 40)
    private String uniteBankNum;
    @ExcelProperty(value = {"人员信息收集","开户行省份"},index = 41)
    private String bankProvince;
    @ExcelProperty(value = {"人员信息收集","开户行市辖区"},index = 42)
    private String bankCity;
    @ExcelProperty(value = {"人员信息收集","合同类型"},index = 43)
    private String contractType;
    @ExcelProperty(value = {"人员信息收集","劳动合同起期"},index = 44)
    @DateTimeFormat("yyyy/MM/dd")
    private Date contractStartDate;
    @ExcelProperty(value = {"人员信息收集","劳动合同止期"},index = 45)
    @DateTimeFormat("yyyy/MM/dd")
    private Date ContractEndDate;
    @ExcelProperty(value = {"人员信息收集","团队架构代码"},index = 46)
    private String agentGroup;
    @ExcelProperty(value = {"人员信息收集","团队架构名称"},index = 47)
    private String branchName;
    @ExcelProperty(value = {"人员信息收集","团队主管工号"},index = 48)
    private String branchManager;
    @ExcelProperty(value = {"人员信息收集","团队主管姓名"},index = 49)
    private String branchManagerName;
    @ExcelProperty(value = {"人员信息收集","团队主管手机号"},index = 50)
    private String branchManagerPhone;
}

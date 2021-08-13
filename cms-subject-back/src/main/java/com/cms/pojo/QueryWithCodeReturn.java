package com.cms.pojo;

import lombok.Data;

@Data
public class QueryWithCodeReturn {
    private String agentCode;	//人员工号
    private String sapCode;	    //sap工号
    private String agentName;	//人员姓名
    private String  idType;	    //证件类型
    private String idNo;    	//证件号码
    private String birthday;	//出生日期
    private String sex;	        //性别
    private String rgtType;	    //户口类型
    private String rgtProvince;	//户口所在省
    private String highestDegree;	//最高学历
    private String firstDegree;	//第一学历
    private String degree;	    //最高学位
    private String graduateSchool;	//毕业院校
    private String major;	    //专业
    private String nationality;	//民族
    private String nativeplace;	//籍贯
    private String oldIndustryType;	//最近一份工作行业类型
    private String oldOccupation;	//最近一份职业
    private String oldCom;	    //最近一家工作单位
    private String oldJobDuty;	    //最近一份工作职务
    private String workAge;	    //从业年限
    private String homeAddress;	    //家庭地址
    private String postcode;	    //邮政编码
    private String homephone;	//宅电
    private String phone;	//电话号码
    private String email;	//邮箱
    private String outlookStatus;	//政治面貌
    private String bankCode;	    //账户银行总行
    private String bankAccount;	//银行账户
    private String uniteBankNum;	    //银行卡开户行联行号
    private String bankProvince;    //开户行省份
    private String bankCity;	        //开户行地市
    private String certificateNo;	//专业资格证书号
    private String manageCom2;	    //二级管理机构名
    private String manageCom3;	    //三级管理机构名
    private String manageCom4;	    //四级管理机构名
    private String agentJob;	        //岗位
    private String agentGrade;	    //职级
    private String branchAttr;	    //团队编码
    private String branchManager;	//团队主管工号
    private String branchManagerName;	//团队主管姓名
    private String contractType;	    //合同类型
    private String contractStartDate;	//劳动合同起期
    private String contractEndDate;	//劳动合同止期
    private String employDate;	//入司时间
    private String operator;	    //操作者
    private String agentGradeName;	//岗位名称
    private String branchName;	//团队名称
    private String outWorkDate;	//离司日期
    private String agentState;	//人员状态
    private String repeatFlag;	//是否二次入司

}

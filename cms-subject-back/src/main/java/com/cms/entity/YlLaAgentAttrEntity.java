package com.cms.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@TableName("yl_la_agent_attr")
@Data
@EqualsAndHashCode(callSuper = false)
public class YlLaAgentAttrEntity {
    /**
     * 人员工号
     */
    @TableId("agent_code")
    private String agentCode;

    /**
     * 推荐人代码
     */
    private String recomAgentCode;

    /**
     * 推荐人名称
     */
    private String recomAgentName;

    /**
     * 身份证类型
     */
    private String idType;

    /**
     * 身份证号码
     */
    private String idNo;

    /**
     * 性别
     */
    private String sex;

    /**
     * 出生日期
     */
    private Date birthday;

    /**
     * 婚姻状态
     */
    private String marriageStatus;

    /**
     * 民族
     */
    private String nationality;

    /**
     * 籍贯
     */
    private String nativeplace;

    /**
     * 邮政编码
     */
    private String postcode;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 宅电
     */
    private String homephone;

    /**
     * 健康状态
     */
    private String healthStatus;

    /**
     * 政治面貌
     */
    private String outlookStatus;

    /**
     * 健康备注
     */
    private String healthRemark;

    /**
     * 考勤号码
     */
    private String attendanceNo;

    /**
     * 毕业院校
     */
    private String graduateSchool;

    /**
     * 专业
     */
    private String major;

    /**
     * 最高学历
     */
    private String highestDegree;

    /**
     * 第一学历
     */
    private String firstDegree;

    /**
     * 最高学位
     */
    private String degree;

    /**
     * 学历类型
     */
    private String degreeCode;

    /**
     * 学历名称
     */
    private String degreeName;

    /**
     * 最近一份工作行业类型
     */
    private String oldIndustryType;

    /**
     * 最近一份职业
     */
    private String oldOccupation;

    /**
     * 最近一家工作单位
     */
    private String oldCom;

    /**
     * 最近一份工作职务
     */
    private String oldJobDuty;

    /**
     * 从业年限
     */
    private String workAge;

    /**
     * 省份
     */
    private String province;

    /**
     * 地市
     */
    private String city;

    /**
     * 区
     */
    private String area;

    /**
     * 家庭地址
     */
    private String address;

    /**
     * 培训情况
     */
    private String train;

    /**
     * 合同类型
     */
    private String contractType;

    /**
     * 劳动合同起期
     */
    private Date contractStartDate;

    /**
     * 劳动合同止期
     */
    private Date contractEndDate;

    /**
     * 专业资格证书号
     */
    private String certificateNo;

    /**
     * 银行卡开户行联行号
     */
    private String uniteBankNum;

    /**
     * 银行类别
     */
    private String bankCode;

    /**
     * 银行账号
     */
    private String bankAccount;

    /**
     * 开户行省份
     */
    private String bankProvince;

    /**
     * 开户行地市
     */
    private String bankCity;

    /**
     * 开户行详细地址
     */
    private String bankAddress;

    /**
     * 操作者
     */
    private String operator;

    /**
     * 创建日期
     */
    private Date makeDate;

    /**
     * 创建时间
     */
    private String makeTime;

    /**
     * 修改日期
     */
    private Date modifyDate;

    /**
     * 修改时间
     */
    private String modifyTime;

    /**
     * 户口类型
     */
    private String rgtType;

    /**
     * 户口所在地
     */
    private String regAddress;

}

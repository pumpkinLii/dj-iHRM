package com.cms.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author :zhanhaoze
 * @description :
 * @create :2021-07-26
 */
@TableName("ld_com_new")
@Data
@EqualsAndHashCode(callSuper = false)
public class LdComNewEntity {

    /**
     * 机构编码
     */
    @TableId("com_code")
    private String comCode;

    /**
     * 对外公布的机构代码
     */
    private String outComCode;

    /**
     * 机构名称
     */
    private String name;

    /**
     * 核心编码
     */
    private String coreCode;

    /**
     * 核心名称
     */
    private String coreName;

    /**
     * 短名称
     */
    private String shortName;

    /**
     * 标准地市
     */
    private String areaCode;

    /**
     * 机构地址
     */
    private String address;

    /**
     * 机构邮编
     */
    private String zipCode;

    /**
     * 机构电话
     */
    private String phone;

    /**
     * 机构传真
     */
    private String fax;

    /**
     * Email
     */
    private String email;

    /**
     * 是否生效
     */
    private String effectState;

    /**
     * 是否撤销
     */
    private String revocation;

    /**
     * 撤销原因
     */
    private String reason;

    /**
     * 撤销日期
     */
    private Date revocationDate;

    /**
     * 恢复日期
     */
    private Date recoverDate;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 县级及县级以下机构
     */
    private String countyCom;

    /**
     * 生效日期
     */
    private Date effectDate;

    /**
     * 财务代码
     */
    private String financialCode;

    /**
     * 网址
     */
    private String webAddress;

    /**
     * 主管人姓名
     */
    private String satrapName;

    /**
     * 对应保监办代码
     */
    private String insuMonitorCode;

    /**
     * 保险公司id
     */
    private String insureId;

    /**
     * 标识码
     */
    private String signId;

    /**
     * 行政区划代码
     */
    private String regionalismCode;

    /**
     * 公司性质
     */
    private String comNature;

    /**
     * 校验码
     */
    private String validCode;

    /**
     * 标志
     */
    private String sign;

    /**
     * 收费员渠道机构地区类型
     */
    private String comCitySize;

    /**
     * 客户服务机构名称
     */
    private String serviceName;

    /**
     * 客户服务机构编码
     */
    private String serviceNo;

    /**
     * 客户服务电话
     */
    private String servicePhone;

    /**
     * 客户服务投递地址
     */
    private String servicePostAddress;

    /**
     * 机构级别
     */
    private String comGrade;

    /**
     * 机构地区类型
     */
    private String comAreaType;

    /**
     * 上级管理机构
     */
    private String upComCode;

    /**
     * 上上级管理机构
     */
    private String upUpComCode;

    /**
     * 直属属性
     */
    private String isDirUnder;

    /**
     * 创建日期
     */
    private Date foundDate;

    /**
     * 是否适用个险
     */
    private String agy;

    /**
     * 个险对应机构名称
     */
    private String agyName;

    /**
     * 是否适用银保
     */
    private String bk;

    /**
     * 银保对应机构名称
     */
    private String bkName;

    /**
     * 是否适用养老
     */
    private String yl;

    /**
     * 养老对应机构名称
     */
    private String ylName;

    /**
     * 是否适用中介
     */
    private String br;

    /**
     * 中介渠道对应名称
     */
    private String brName;

    /**
     * 是否适用团险
     */
    private String tm;

    /**
     * 团队渠道对应名称
     */
    private String tmName;

    /**
     * 是否适用收展
     */
    private String rw;

    /**
     * 收展渠道对应名称
     */
    private String rwName;

    /**
     * 操作员代码
     */
    private String operator;

    /**
     * 入机日期
     */
    private Date makeDate;

    /**
     * 入机时间
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

}

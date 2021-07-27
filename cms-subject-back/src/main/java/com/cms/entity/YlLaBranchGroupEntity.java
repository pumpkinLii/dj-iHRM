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
@TableName("yl_la_branch_group")
@Data
@EqualsAndHashCode(callSuper = false)
public class YlLaBranchGroupEntity {
    /**
     * 组织内码
     */
    @TableId
    private String agentGroup;

    /**
     * 管理机构
     */
    private String manageCom;

    /**
     * 渠道
     */
    private String branchType;

    /**
     * 组织编码
     */
    private String branchAttr;

    /**
     * 组织级别
     */
    private String branchLevel;

    /**
     * 组织名称
     */
    private String branchName;

    /**
     * 组织英文名称
     */
    private String branchNameEng;

    /**
     * 组织中文名称
     */
    private String branchNameChi;

    /**
     * 生效日期
     */
    private Date branchEffDate;

    /**
     * 地址
     */
    private String branchAddress;

    /**
     * 电话
     */
    private String branchPhoneNo;

    /**
     * 传真号码
     */
    private String branchFaxNo;

    /**
     * 组织状态
     */
    private String branchStatus;

    /**
     * 停业日期
     */
    private Date branchTerminateEffDate;

    /**
     * 停业原因
     */
    private String branchTerminateReason;

    /**
     * 直辖标识
     */
    private String directFlag;

    /**
     * 上级组织代码
     */
    private String upAgentGroup;

    /**
     * 组织主管
     */
    private String branchManager;

    /**
     * 主管名字
     */
    private String branchManagerName;

    /**
     * 主管手机号
     */
    private String branchManagerPhone;

    /**
     * 主管任命日期
     */
    private Date managerEffDate;

    /**
     * 报告主管
     */
    private String reportingManager;

    /**
     * 群聊名称
     */
    private String chatName;

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
     * 操作者
     */
    private String operator;

}

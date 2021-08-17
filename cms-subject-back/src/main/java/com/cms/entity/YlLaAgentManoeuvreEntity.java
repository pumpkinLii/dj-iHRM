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
@TableName("yl_la_agent_manoeuvre")
@Data
@EqualsAndHashCode(callSuper = false)
public class YlLaAgentManoeuvreEntity {
    /**
     * 转储号码
     */
    @TableId("edor_no")
    private String edorNo;

    /**
     * 转储类型#01-组织变动，02-职级变动
     */
    private String edorType;

    /**
     * 功能类型
     */
    private String functType;

    /**
     * 调整原因
     */
    private String adjustReason;

    /**
     * 人员工号
     */
    private String agentCode;

    /**
     * 渠道类型
     */
    private String branchType;

    /**
     * 展业类型
     */
    private String branchType2;

    /**
     * 生效日期
     */
    private Date manoeuvreDate;

    /**
     * 调整前所在管理机构
     */
    private String preManageCom;

    /**
     * 调整后所在管理机构
     */
    private String curManageCom;

    /**
     * 调整前所在团队内码
     */
    private String preBranchCode;

    /**
     * 调整后所在团队内码
     */
    private String curBranchCode;

    /**
     * 调整前所在团队外码
     */
    private String preBranchAttr;

    /**
     * 调整后所在团队外码
     */
    private String curBranchAttr;

    /**
     * 调整前职级
     */
    private String preAgentGrade;

    /**
     * 调整后职级
     */
    private String curAgentGrade;

    /**
     * 调整前人员状态
     */
    private String preAgentState;

    /**
     * 调整后人员状态
     */
    private String curAgentState;

    /**
     * 调整前所在区
     */
    private String preDistict;

    /**
     * 调整后所在区
     */
    private String curDistict;

    /**
     * 调整前所在区主管
     */
    private String preDistictManager;

    /**
     * 调整后所在区主管
     */
    private String curDistictManager;

    /**
     * 调整前所在部
     */
    private String preDepartment;

    /**
     * 调整后所在部
     */
    private String curDepartment;

    /**
     * 调整前所在部主管
     */
    private String preDepartmentManager;

    /**
     * 调整后所在部主管
     */
    private String curDepartmentManager;

    /**
     * 调整后团队名称
     */
    private String curAgentGroupName;

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

}

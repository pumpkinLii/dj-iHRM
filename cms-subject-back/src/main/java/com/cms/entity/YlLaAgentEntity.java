package com.cms.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@TableName("yl_la_agent")
@Data
@EqualsAndHashCode(callSuper = false)
public class YlLaAgentEntity {
    /**
     * 人员工号
     */
    @TableId("agent_code")
    private String agentCode;

    /**
     * sap工号
     */
    private String sapCode;

    /**
     * 人员姓名
     */
    private String agentName;

    /**
     * 岗位
     */
    private String agentJob;

    /**
     * 职级
     */
    private String agentGrade;

    /**
     * 人员状态
     */
    private String agentState;

    /**
     * 渠道
     */
    private String branchType;

    /**
     * 管理机构
     */
    private String manageCom;

    /**
     * 初始职级
     */
    private String initGrade;

    /**
     * 入司时间
     */
    private Date employDate;

    /**
     * 上岗日期
     */
    private Date signDate;

    /**
     * 转正日期
     */
    private Date inDueFormDate;

    /**
     * 离司日期
     */
    private Date outWorkDate;

    /**
     * 离司交接日期
     */
    private Date doneDate;

    /**
     * 增员类型0-增员1-高权限增员
     */
    private String insertKind;

    /**
     * 代理人展业机构代码
     */
    private String agentGroup;

    /**
     * 参加工作时间
     */
    private Date workDate;

    /**
     * 是否同业引进
     */
    private String introduce;

    /**
     * 是否二次入司
     */
    private String repeatFlag;

    /**
     * 操作者
     */
    private String operator;

    /**
     * 是否同业
     */
    private String isPeers;

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

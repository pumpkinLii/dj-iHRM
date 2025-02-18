package com.cms.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@TableName("yl_la_dimission")
@Data
@EqualsAndHashCode(callSuper = false)
public class YlLaDimissionEntity {
    /**
     * 代理人编码
     */
    @TableId("agent_code")
    private String agentCode;

    /**
     * 管理机构
     */
    private String manageCom;

    /**
     * 团队代码
     */
    private String agentGroup;

    /**
     * 展业类型
     */
    private String branchType;

    /**
     * 离职日期
     */
    private Date departDate;

    /**
     * 离职原因
     */
    private String departReason;

    /**
     * 离职次数
     */
    private int departTimes;

    /**
     * 交接人编码
     */
    private String handoverCode;

    /**
     * 离职状态
     */
    private String departState;

    /**
     * 离职操作日期
     */
    private Date doneDate;

    /**
     * 离职恢复日期
     */
    private Date reDate;

    /**
     * 上传附件
     */
    private String departUrl;

    /**
     * 备注
     */
    private String noti;

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

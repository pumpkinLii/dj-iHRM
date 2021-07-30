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
@TableName("yl_la_agent_certificate")
@Data
@EqualsAndHashCode(callSuper = false)
public class YlLaAgentCertificateEntity {
    /**
     * 流水号
     */
    @TableId("edor_no")
    private String edorNo;

    /**
     * 资格证号
     */
    private String certificateNo;

    /**
     * 人员工号
     */
    private String agentCode;

    /**
     * 证书类型
     */
    private String certificateType;

    /**
     * 证书名称
     */
    private String certificateName;

    /**
     * 证书有效起期
     */
    private Date startEffectiveDate;

    /**
     * 证书有效止期
     */
    private Date endEffectiveDate;

    /**
     * 发放日期
     */
    private Date releaseDate;

    /**
     * 补发日期
     */
    private Date reissueDate;

    /**
     * 批准单位
     */
    private String approver;

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

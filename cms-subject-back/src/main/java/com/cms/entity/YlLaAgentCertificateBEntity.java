package com.cms.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("yl_la_agent_certificate_b")
public class YlLaAgentCertificateBEntity extends YlLaAgentCertificateEntity {
    /**
     * 备份表主键
     */
    @TableId("enor_no_back")
    private String enorNoBack;

    /**
     * 备份人
     */
    private String lastOperator;
    /**
     * 备份时间
     */
    private Timestamp lastMakeDateTime;
}

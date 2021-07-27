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
@Data
@TableName("la_agent_grade")
@EqualsAndHashCode(callSuper = false)
public class LaAgentGradeEntity {
    /**
     * 职级唯一ID
     */
    @TableId("grade_id")
    private String gradeId;
    /**
     * 职级编码
     */
    private String gradeCode;
    /**
     * 职级名称
     */
    private String gradeName;
    /**
     * 渠道
     */
    private String branchType;
    /**
     * 职级序号
     */
    private int gradeOrder;
    /**
     * 职级序列
     */
    private String gradeSeries;
    /**
     * 职级级别
     */
    private String gradeLevel;
    /**
     * 备注
     */
    private String remark;

    /**
     * 职级缩写
     */
    private String gradeAbridge;

    /**
     * 虚拟职级标识
     */
    private String virtualGrade;

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

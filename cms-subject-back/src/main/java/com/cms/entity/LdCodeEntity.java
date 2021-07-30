package com.cms.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author :zhanhaoze
 * @description :
 * @create :2021-07-26
 */
@Data
@TableName("ld_code")
public class LdCodeEntity implements Serializable {

    /**
     * Code类型
     */
    @TableId("code_type")
    private String codeType;
    /**
     * code
     */
    private String code;

    /**
     * codename
     */
    private String codeName;

    /**
     * comcode
     */
    private String comCode;


    private String otherSign;

}

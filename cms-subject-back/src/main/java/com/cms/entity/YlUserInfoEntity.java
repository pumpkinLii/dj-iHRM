package com.cms.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author zhanhaoze
 * @description
 * @date 2021/7/22
 **/
@TableName("yl_user_info")
@Data
@EqualsAndHashCode(callSuper = false)
public class YlUserInfoEntity {
    /**
     * 用户id
     */
    @TableId("user_id")
    private String userId;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     *  用户密码
     */
    private String userPassword;

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
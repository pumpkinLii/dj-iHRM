package com.cms.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@TableName("edm_user_info")
@Data
@EqualsAndHashCode(callSuper = false)
public class UserInfo {
    @TableId("userid")
    private String userid;
    private String username;
    private String userpassword;
    private String partner;
    private String parentid;
    private String userstate;
    private String noti;

}
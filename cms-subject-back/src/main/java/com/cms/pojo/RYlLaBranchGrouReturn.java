package com.cms.pojo;

import lombok.Data;

import java.util.Date;
@Data
public class RYlLaBranchGrouReturn {
    //这个类用于返回给前端 VO模式
            /*节点名	节点名称	类型	长度	节点可无	数据可空	说明
        manageComCode	管理机构代码	字符		否	否
        manageCom	管理机构名称	字符		否	否
        branchLevel	组织级别	字符		否	否
        branchAttr	团队编码	字符		否	否
        branchName	团队名称	字符	60	否	否
        chatName	群聊名称	字符		否	否
        branchManager	负责人代码	字符		否	否
        branchManagerName	负责人姓名	字符		否	否
        branchManagerPhone	负责人手机号	字符		否	否
        agentCount	代理人个数	字符		否	否
        branchEffDate	成立时间	日期		否	否
        branchStatus	停业标志	字符		否	否
        branchStatusName	停业标志名称	字符		否	否
        branchTerminateEffDate	停业日期	日期		否	否
        operator	操作员	字符		否	否
        */
    //创建对应的返回新信息的对象
    private String manageComCode;
    private String manageCom;
    private String branchLevel;
    private String branchAttr;
    private String branchName;
    private String chatName;
    private String branchManager;
    private String branchManagerName;
    private String branchManagerPhone;
    private String agentCount;
    private String branchEffDate;
    private String branchStatus;
    private String branchStatusName;
    private String branchTerminateEffDate;
    private String operator;
}

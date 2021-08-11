package com.cms.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class CYlLaBranchGroupPojo {
    private String manageComCode4;
    private String branchLevel;
    private String branchName;
    private String branchManager;
    private String branchManagerName;
    private String branchManagerPhone;
    private String branchEffDate;
    private String branchStatus;
    private String branchTerminateEffDate;
    private String operator;
    private String chatName;
}

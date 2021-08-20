package com.cms.pojo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 此类储存前端传输而来的人员异动(团队更改)的信息，
 * */
@Data
public class YlLaAgentChangePojo {
    private String manageCom;
    private String agentGroup;
    private Date modifyDate;
    private String operator;
    private List<String> agentCodeList;
}

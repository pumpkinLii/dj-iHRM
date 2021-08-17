package com.cms.service;

import com.cms.pojo.YlLaAgentChangePojo;

/**
 * 人员异动使用的用于对yl_la_branch_group表操作的接口
 * */
public interface YlLaAgentInfoChangeService {
    /**
     * 此方法用于人员异动(更改团队),传入一个YlLaAgentChangePojo,如果异动成功返回true，异动失败返回false
     * */
    public boolean ylLaAgentChange(YlLaAgentChangePojo ylLaAgentChangePojo);
}

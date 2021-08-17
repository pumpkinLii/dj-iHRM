package com.cms.service;

import com.cms.entity.YlLaAgentEntity;
import com.cms.pojo.YlLaAgentChangePojo;

import java.util.List;
/**
 * 人员异动使用的用于对yl_la_agent_manoeuvre操作的接口
 * */
public interface YlLaAgentManoeuvreService{
    /**
     * 此方法用于人员异动(团队更改)时在人员异动表(yl_la_agent_manoeuvre)插入数据，传入旧的员工信息的集合和YlLaAgentChangePojo(包含更改新消息)
     * 插入成功返回true，插入失败返回false
     * 此方法专用于YlLaAgentInfoChangeServiceImpl中使用，是YlLaAgentInfoChangeServiceImpl的一部分
     * */
    public boolean buildNewAgentManoeuvre(List<YlLaAgentEntity> oldAgentInfo, YlLaAgentChangePojo ylLaAgentChangePojo);
}

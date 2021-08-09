package com.cms.service;

import com.cms.pojo.LaAgentPojo;
import com.cms.pojo.LaAgentUpdatePojo;

public interface YlAgentAttrInfoService {
    /**人员添加接口，传入一个LaAgentPojo对象， 返回字符串信息，表示成功或者失败的原因*/
    public String agentSubmit(LaAgentPojo laAgent);
    /**人员添加接口，传入一个LaAgentPojo对象， 返回字符串信息，表示成功或者失败的原因*/
    public String agentUpdate(LaAgentUpdatePojo laAgent);
    /**获取工号接口，无参方法，查询数据库后返回生成的新工号*/
    public String getNewstr();
}

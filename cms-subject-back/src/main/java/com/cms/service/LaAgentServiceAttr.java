package com.cms.service;


import com.cms.pojo.LaAgentPojo;
import com.cms.pojo.LaAgentUpdatePojo;

public interface LaAgentServiceAttr {
    public String agentSubmit(LaAgentPojo laAgent);
    public String agentUpdate(LaAgentUpdatePojo laAgent);
    public String getNewstr();
}

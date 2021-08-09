package com.cms.service;

import com.cms.pojo.LaAgentPojo;
import com.cms.pojo.LaAgentUpdatePojo;

public interface LaAgentService {
    public boolean laAgentSubmit(LaAgentPojo laAgent);
    public boolean laAgentUpdate(LaAgentUpdatePojo laAgent);
}

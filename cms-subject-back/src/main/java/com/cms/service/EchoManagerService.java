package com.cms.service;

import com.cms.pojo.GroupPojo;
import com.cms.pojo.ManagerPojo;

import java.util.Map;

public interface EchoManagerService {
    public Map<String,String> getManager(GroupPojo groupPojo);
}

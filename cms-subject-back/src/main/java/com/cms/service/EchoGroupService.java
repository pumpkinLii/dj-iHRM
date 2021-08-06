package com.cms.service;

import com.cms.pojo.GroupPojo;
import com.cms.pojo.StaffPojo;

import java.util.List;
import java.util.Map;

public interface EchoGroupService {
    public List<Map<String,String>> getGroup(StaffPojo staffPojo);
}

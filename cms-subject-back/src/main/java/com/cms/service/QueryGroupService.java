package com.cms.service;

import com.cms.pojo.QueryGroupPojo;

import java.util.List;
import java.util.Map;

public interface QueryGroupService {
    public List<Map<String,String>> queryGroup(QueryGroupPojo queryGroupPojo);
}

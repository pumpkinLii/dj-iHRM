package com.cms.service;

import com.cms.pojo.QueryStaffPojo;
import com.cms.pojo.QueryStaffReturn;
import com.cms.pojo.QueryWithCodeReturn;

import java.text.ParseException;
import java.util.List;

public interface YlAgentUpdateService {
    public List<QueryStaffReturn> queryStaff(QueryStaffPojo queryStaffPojo) throws ParseException;
    public QueryWithCodeReturn queryInfo(String agentCode) throws ParseException;
}

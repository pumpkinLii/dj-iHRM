package com.cms.service;

import com.cms.pojo.QueryStaffPojo;
import com.cms.pojo.QueryStaffReturn;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface QueryStaffService {
    public List<QueryStaffReturn> queryStaff(QueryStaffPojo queryStaffPojo) throws ParseException;
}

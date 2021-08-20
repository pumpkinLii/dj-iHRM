package com.cms.service;

import com.cms.pojo.QueryStaffPojo;
import com.cms.pojo.NQueryStaffReturn;

import java.text.ParseException;
import java.util.List;

public interface NQueryStaffService {
    public List<NQueryStaffReturn> queryStaff(QueryStaffPojo queryStaffPojo) throws ParseException;
}

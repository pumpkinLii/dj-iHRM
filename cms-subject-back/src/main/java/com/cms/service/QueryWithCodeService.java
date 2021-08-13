package com.cms.service;


import com.cms.pojo.QueryWithCodeReturn;

import java.text.ParseException;
import java.util.Map;

public interface QueryWithCodeService {
    public QueryWithCodeReturn queryInfo(String agentCode) throws ParseException;
}

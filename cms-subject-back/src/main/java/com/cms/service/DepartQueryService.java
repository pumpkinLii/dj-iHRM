package com.cms.service;

import com.cms.pojo.DepartQueryReturnPojo;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.text.ParseException;

public interface DepartQueryService {
    public DepartQueryReturnPojo departQuery(String agentCode) throws ParseException;
}

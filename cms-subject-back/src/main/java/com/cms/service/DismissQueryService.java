package com.cms.service;

import com.cms.pojo.DismissQueryPojo;
import com.cms.pojo.DismissQueryReturnPojo;

import java.util.List;


public interface DismissQueryService {
    public List<DismissQueryReturnPojo> searchList(DismissQueryPojo dismissQueryPojo);
}

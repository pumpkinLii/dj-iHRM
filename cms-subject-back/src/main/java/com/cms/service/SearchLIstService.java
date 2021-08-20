package com.cms.service;

import com.cms.pojo.ResultListPojo;
import com.cms.pojo.SearchListPojo;
import java.util.List;
public interface SearchLIstService {
    public List<ResultListPojo> searchList(SearchListPojo searchListPojo);
}

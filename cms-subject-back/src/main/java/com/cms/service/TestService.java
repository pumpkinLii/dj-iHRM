package com.cms.service;

import com.cms.pojo.TestPojo;
import com.github.pagehelper.PageInfo;


/**
 * @author :zhanhaoze
 * @description :
 * @create :2021-07-22
 */
public interface TestService {
    PageInfo<TestPojo> getList(TestPojo testPojo, int page, int limit);
}

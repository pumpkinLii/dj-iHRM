package com.cms.service;

import com.cms.pojo.TestPojo;
import com.cms.util.PageUtils;


/**
 * @author :zhanhaoze
 * @description :
 * @create :2021-07-22
 */
public interface TestService {
    PageUtils getList(TestPojo testPojo, int page, int limit);
}

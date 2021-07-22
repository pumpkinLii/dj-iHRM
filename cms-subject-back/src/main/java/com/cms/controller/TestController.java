package com.cms.controller;

import com.cms.pojo.TestPojo;
import com.cms.service.TestService;
import com.cms.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author :zhanhaoze
 * @description :
 * @create :2021-07-22
 */
@RestController
@CrossOrigin
@RequestMapping("/test")
public class TestController {
    @Autowired
    private TestService testService;

    @PostMapping("/userinfo")
    public PageUtils test(@RequestBody TestPojo testPojo, int page, int limit) {
        PageUtils list = testService.getList(testPojo, page, limit);
        return list;
    }
}

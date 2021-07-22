package com.cms.controller;


import com.cms.pojo.TestPojo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author :zhanhaoze
 * @description :
 * @create :2021-07-22
 */
@RestController
public class TestController {

    @PostMapping("/test")
    public TestPojo test() {
        TestPojo testPojo = new TestPojo();
        testPojo.setName("张三");
        return testPojo;
    }
}

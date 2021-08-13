package com.cms.controller;

import com.cms.pojo.TestPojo;
import com.cms.service.TestService;
import com.cms.util.R;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author :zhanhaoze
 * @description :
 * @create :2021-07-22
 */
@RestController
@CrossOrigin
@RequestMapping("/test")
@Slf4j
@Api("养老人管模块课题")
public class TestController {
    @Autowired
    private TestService testService;

    @ApiOperation("测试接口")
    @PostMapping("/info")
    public R test(@RequestBody TestPojo testPojo, int page, int limit) {
        PageInfo<TestPojo> list = testService.getList(testPojo, page, limit);
        Map<Object,Object> map =new HashMap<>();
        map.put("total",list.getTotal());
        map.put("list",list.getList());
        System.out.println("abc");
        System.out.println("123");
        System.out.println("fsdfsdfsdf");
        System.out.println("hello world");
        return R.ok().put("page", map);
//        return "hello world";
    }
}

package com.cms.controller;

import com.cms.pojo.TestPojo;
import com.cms.service.TestService;
import com.cms.util.PageUtils;
import com.cms.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api("养老人管模块课题")
public class TestController {
    @Autowired
    private TestService testService;

    @ApiOperation("测试接口")
    @PostMapping("/info")
    public String test() {
//        @RequestBody TestPojo testPojo, int page, int limit
//        PageUtils list = testService.getList(testPojo, page, limit);
//        return R.ok().put("page", list);
        return "hello world";
    }
}

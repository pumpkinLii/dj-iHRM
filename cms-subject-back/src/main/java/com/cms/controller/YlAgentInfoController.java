package com.cms.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author :zhanhaoze
 * @description :
 * @create :2021-08-03
 */
@RestController
@CrossOrigin
@RequestMapping("test")
@Api("养老渠道-人员管理-人员入司与信息维护")
public class YlAgentInfoController {

    @PostMapping("/s")
    @ApiOperation("测试接口")
    public String test(){
        return "true";
    }

}

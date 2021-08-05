package com.cms.controller;

import com.cms.service.IdCodeService;
import com.cms.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/start")
@Api("团队查询课题")
public class PageResourceController {
    @Autowired
    IdCodeService idCodeService;

    /*@ApiOperation("团队查询接口")
    @PostMapping("/get")
    public Map<String,Map<String,String>> getResource(){

        return idCodeService.getResource();
    }*/

    @ApiOperation("团队查询接口")
    @PostMapping("/get")
    public R getResource(){

        return R.ok().put("resource",idCodeService.getResource());
    }
}

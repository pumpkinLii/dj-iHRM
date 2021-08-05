package com.cms.controller;

import cn.hutool.core.util.PageUtil;
import com.cms.pojo.CYlLaBranchGroupPojo;
import com.cms.service.impl.CYlLaBranchGroupServiceImpl;
import com.cms.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@CrossOrigin
@RequestMapping("/create")
@Api("团队新增课题")
public class CYlLaBranchGroupController {

    @Autowired
    CYlLaBranchGroupServiceImpl c_ylLaBranchGroupService;

    @ApiOperation("团队新增接口")
    @PostMapping("/group")
    public R creat(@RequestBody CYlLaBranchGroupPojo c_ylLaBranchGroupPojo) throws ParseException {
        //抛出异常 是因为 前端传进来的Date的不会有错误的
        c_ylLaBranchGroupService.creat(c_ylLaBranchGroupPojo);
        return new R();
    }
}

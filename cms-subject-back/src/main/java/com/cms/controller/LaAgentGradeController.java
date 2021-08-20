package com.cms.controller;

import com.cms.service.LaAgentGradeService;
import com.cms.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/get")
@Api("职级下拉列表")
public class LaAgentGradeController {
    @Autowired
    LaAgentGradeService laAgentGradeService;

    @ApiOperation("职级下拉框接口")
    @PostMapping("/grade")
    public R  getAgentGrade(){
        return R.ok().put("list",laAgentGradeService.method());
    }
}

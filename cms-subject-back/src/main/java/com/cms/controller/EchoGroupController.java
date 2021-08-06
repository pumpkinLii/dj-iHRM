package com.cms.controller;


import com.cms.pojo.GroupPojo;
import com.cms.pojo.StaffPojo;
import com.cms.service.EchoGroupService;
import com.cms.service.EchoManagerService;
import com.cms.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/test")
@Api("回显团队架构的检测")
public class EchoGroupController {
    @Autowired
    EchoGroupService echoGroupService;

    @PostMapping("/EchoGroup")
    @ApiOperation("回显团队架构接口")
    public R echoManager(@RequestBody StaffPojo staffPojo) {
        List<Map<String, String>> mapList = echoGroupService.getGroup(staffPojo);
        if (mapList != null) {
            return R.ok().put("list",mapList);
        } else {
            return R.error("失败：无对应团队");
        }
    }
}

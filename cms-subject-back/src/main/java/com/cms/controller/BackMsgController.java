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
@RequestMapping("/login/back")
@Api("养老人管模块课题")
public class BackMsgController {
    /*回显团队架构*/
    @Autowired
    EchoGroupService echoGroupService;
    @PostMapping("/group")
    @ApiOperation("回显团队架构接口")
    public R echoGroup(@RequestBody StaffPojo staffPojo) {
        List<Map<String, String>> mapList = echoGroupService.getGroup(staffPojo);
        if (mapList != null) {
            return R.ok().put("list",mapList);
        } else {
            return R.error(501,"失败：无对应团队");
        }
    }

    /*回显团队主管工号及姓名*/
    @Autowired
    EchoManagerService echoManagerService;
    @PostMapping("/manager")
    @ApiOperation("回显团队主管工号及姓名接口")
    public R echoManager(@RequestBody GroupPojo groupPojo) {
        Map<String,String> map = echoManagerService.getManager(groupPojo);
        if(map!=null){
            return R.ok().put("data",map);
        }else {
            return R.error(501,"该团队无对应管理员");
        }
    }

}

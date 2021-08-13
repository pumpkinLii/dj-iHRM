package com.cms.controller;

import com.cms.pojo.GroupPojo;
import com.cms.pojo.ManagerPojo;
import com.cms.service.EchoManagerService;
import com.cms.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/test")
@Api("回显主管的检测")
public class EchoManagerController {
    @Autowired
    EchoManagerService echoManagerService;

    @PostMapping("/EchoManager")
    @ApiOperation("回显接口")
    public R echoManager(@RequestBody/*@RequestParams*/ GroupPojo groupPojo) {
        Map<String,String> map = echoManagerService.getManager(groupPojo);
        if(map!=null){
            return R.ok().put("data",map);
        }else {
            return R.error(501,"失败，无对应管理员");
        }
    }
}

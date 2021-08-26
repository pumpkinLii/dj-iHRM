package com.cms.controller;

import com.cms.pojo.GradeTeamPojo;
import com.cms.service.YlAgentInfoService;
import com.cms.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/login/back")
@Api("养老人管模块课题")
public class BackMsgController {
    @Autowired
    YlAgentInfoService ylAgentInfoService;
    @PostMapping("/gradeteam")
    @ApiOperation("根据职级返回架构团队")
    public R getgradeteam(@RequestBody GradeTeamPojo gradeTeam){
        return ylAgentInfoService.getgradeteam(gradeTeam);
    }
}

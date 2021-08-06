package com.cms.controller;

import com.cms.pojo.LaAgentPojo;
import com.cms.service.LaAgentService;
import com.cms.service.LaAgentServiceAttr;
import com.cms.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/test")
@Api("人员录入与导入")
public class LaAgentController {
    @Autowired
    private LaAgentServiceAttr laAgentServiceAttr;
    @Autowired
    private LaAgentService laAgentService;

    private String agentCode;
    @PostMapping("/doSave")
    @ApiOperation("人员录入与导入接口")
    public R laAgentSubmit(@RequestBody LaAgentPojo laAgent){
        String agentSubmit = laAgentServiceAttr.agentSubmit(laAgent);
        agentCode = laAgentServiceAttr.getNewstr();
        laAgent.setAgentCode(agentCode);
        if(agentSubmit.equals("success")){
            if(laAgentService.laAgentSubmit(laAgent)){
                return R.ok("操作成功，人员工号为：" + agentCode);
            }
            return R.error("录入失败,程序有bug");
        }
        else{
            return R.error(agentSubmit);
        }
    }

    @PostMapping("/update")
    @ApiOperation("人员信息修改接口")
    public R laAgentUpdate(@RequestBody LaAgentPojo laAgent){
        String agentUpdate = laAgentServiceAttr.agentUpdate(laAgent);
        if(agentUpdate.equals("success")){
            if(laAgentService.laAgentUpdate(laAgent)){
                return R.ok("操作成功！");
            }
            return R.error("录入失败,程序有bug");
        }
        else{
            return R.error(agentUpdate);
        }
    }

}

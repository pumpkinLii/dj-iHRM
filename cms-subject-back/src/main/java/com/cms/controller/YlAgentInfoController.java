package com.cms.controller;

import com.cms.pojo.LaAgentPojo;
import com.cms.pojo.LaAgentUpdatePojo;
import com.cms.pojo.YlLaAgentChangePojo;
import com.cms.service.YlAgentAttrInfoService;
import com.cms.service.YlAgentInfoService;
import com.cms.service.YlLaAgentInfoChangeService;
import com.cms.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author :zhanhaoze
 * @description :
 * @create :2021-08-03
 */
@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/agent")
@Api("养老渠道-人员管理-人员入司与信息维护")
public class YlAgentInfoController {

    @Autowired
    private YlAgentAttrInfoService ylAgentAttrInfoService;

    @Autowired
    private YlAgentInfoService ylAgentInfoService;

    @Autowired
    private YlLaAgentInfoChangeService ylLaAgentInfoChangeService;

    private String agentCode;
    @PostMapping("/doSave")
    @ApiOperation("人员录入与导入接口")
    public R laAgentSubmit(@RequestBody LaAgentPojo laAgent){
        agentCode = ylAgentAttrInfoService.getNewstr();
        laAgent.setAgentCode(agentCode);
        String agentSubmit = ylAgentAttrInfoService.agentSubmit(laAgent);
        if(agentSubmit.equals("success")){
            if(ylAgentInfoService.laAgentSubmit(laAgent)){
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
    public R laAgentUpdate(@RequestBody LaAgentUpdatePojo laAgent){
        String agentUpdate = ylAgentAttrInfoService.agentUpdate(laAgent);
        if(agentUpdate.equals("success")){
            if(ylAgentInfoService.laAgentUpdate(laAgent)){
                return R.ok("操作成功！");
            }
            return R.error("录入失败,请联系后端维护人员");
        }
        else{
            return R.error(agentUpdate);
        }
    }

    @PostMapping("/change")
    @ApiOperation("人员异动接口")
    public R laAgentChange(@RequestBody YlLaAgentChangePojo ylLaAgentChangePojo){
        if(ylLaAgentInfoChangeService.ylLaAgentChange(ylLaAgentChangePojo)){
            return R.ok("操作成功！");
        }
        else{
            return R.error("操作失败！");
        }
    }
}

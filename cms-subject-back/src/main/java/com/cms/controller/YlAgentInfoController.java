package com.cms.controller;

import com.cms.pojo.LaAgentUpdatePojo;
import com.cms.service.LaAgentService;
import com.cms.service.LaAgentServiceAttr;
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
    private LaAgentServiceAttr laAgentServiceAttr;
    @Autowired
    private LaAgentService laAgentService;
    @PostMapping("/update")
    @ApiOperation("人员信息修改接口")
    public R laAgentUpdate(@RequestBody LaAgentUpdatePojo laAgent){
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

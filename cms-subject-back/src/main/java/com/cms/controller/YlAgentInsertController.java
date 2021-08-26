package com.cms.controller;

import com.cms.pojo.IdCheckPojo;
import com.cms.pojo.LaAgentPojo;
import com.cms.service.NIdCheckService;
import com.cms.service.YlAgentAttrInfoService;
import com.cms.service.YlAgentInfoService;
import com.cms.service.YlLaAgentInfoChangeService;
import com.cms.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/login/YlAgentInsert")
@Api("人员录入模块")
public class YlAgentInsertController {

    //王欣艺
    @Autowired
    private NIdCheckService nIdCheckService;
    @ApiOperation("证件校验接口")
    @PostMapping({"/idCheck"})
    public R idcheck(@RequestBody IdCheckPojo idCheckPojo) {
        int fg = nIdCheckService.idcheck(idCheckPojo);
        switch(fg) {
            case 1:
                return R.ok().put("0","证件校验成功");
            case 2:
                return R.error(500, "具有相同证件号码的人已经存在或此人已在其他渠道任职，并且尚未离职");
            case 3:
                return R.error(500, "该人员离职不满六个月，不可二次入司");
            case 4:
                return R.ok().put("0","具有相同证件号码的人已存在且离职，此人员为二次入司");
            case 5:
                return R.error(500, "出现未知错误");
            case 6:
                return R.error(500, "请输入证件号码");
            case 7:
                return R.error(500, "证件号码不是字母数字格式");
            case 8:
                return R.error(500, "证件号码应小于等于18位");
            case 9:
                return R.error(500, "身份证号码错误");
        }
        return R.error(500, "出现未知错误");
    }

    //张晓成
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
}

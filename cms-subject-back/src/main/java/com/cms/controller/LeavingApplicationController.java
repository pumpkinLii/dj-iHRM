package com.cms.controller;

import com.cms.pojo.DepartQueryReturnPojo;
import com.cms.service.DepartQueryService;
import com.cms.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@CrossOrigin
@RequestMapping("/depart")
@Api("离司申请修改接口")
public class LeavingApplicationController {
    @Autowired
    DepartQueryService departQueryService;

    @PostMapping("/Query")
    @ApiOperation("离司申请查询信息接口")
    public R departQuery(@RequestParam("agentCode") String agentCode) throws ParseException {
        DepartQueryReturnPojo dqrp = departQueryService.departQuery(agentCode);
        if (dqrp!=null){
            return R.ok().put("form",dqrp);
        }else {
            return R.error("未查询到相关人员");
        }
    }

}

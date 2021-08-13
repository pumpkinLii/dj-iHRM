package com.cms.controller;

import com.cms.pojo.QueryWithCodeReturn;
import com.cms.service.QueryWithCodeService;
import com.cms.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@CrossOrigin
@RequestMapping("/test")
@Api("查询人员信息的检测")
public class QueryWithCodeController {
    @Autowired
    QueryWithCodeService queryWithCodeService;

    @PostMapping("/QueryWithCode")
    @ApiOperation("根据agentcode查询接口")
    public R queryWithCode(@RequestParam("agentCode") String agentCode) throws ParseException {
        QueryWithCodeReturn qwcr = queryWithCodeService.queryInfo(agentCode);
        if (!qwcr.getAgentName().equals("")){
            return R.ok().put("form",qwcr);
        }else {
            return R.ok("失败，无信息");
        }
    }




}

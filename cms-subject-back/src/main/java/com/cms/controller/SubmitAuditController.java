package com.cms.controller;

import com.cms.service.SubmitAuditService;
import com.cms.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/dimission")
@Api("离职")
public class SubmitAuditController {
    @Autowired
    SubmitAuditService submitAuditService;

    @ApiOperation("提交审核接口")
    @PostMapping("/audit")
    public  R audit(@RequestBody List<String> list){
        boolean result =submitAuditService.audit(list);
        if (result) return R.ok();
        else return R.error("失败");
    }
}

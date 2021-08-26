package com.cms.controller;

import com.cms.pojo.ResignationApprovalPojo;
import com.cms.service.impl.ResignationApprovalImpl;
import com.cms.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@CrossOrigin
@RequestMapping("/regisnate")
@Api("离职审核模块")
public class ResignationApprovalController {
    @Autowired
    ResignationApprovalImpl resignationApproval;
    @ApiOperation("离职审核模块接口")
    @PostMapping("/approval")
    public R approval(@RequestBody ResignationApprovalPojo resignationApprovalPojo,int num) throws ParseException {
        return resignationApproval.approval(resignationApprovalPojo,num);
    }
}

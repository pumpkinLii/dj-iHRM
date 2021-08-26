package com.cms.controller;

import com.cms.entity.YlLaDimissionEntity;
import com.cms.pojo.AccInsertDimissionPojo;
import com.cms.service.InsertDimissionService;
import com.cms.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/insertDimission")
@Api("新增离职接口")
public class InsertDimissionController {
    @Autowired
    InsertDimissionService insertDimissionService;
    @ApiOperation("插入离职信息")
    @PostMapping("/insertDimission")
    public R InsertDimission(@RequestBody AccInsertDimissionPojo accInsertDimissionPojo) {
        int i = insertDimissionService.InsertDimission(accInsertDimissionPojo);
        if (i==1) {
            return R.ok("离司申请已提交");
        }else {
            return R.error(501,"此人员已离职无法提交");
        }
    }
}

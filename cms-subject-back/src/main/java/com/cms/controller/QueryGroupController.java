package com.cms.controller;

import com.cms.pojo.QueryGroupPojo;
import com.cms.service.QueryGroupService;
import com.cms.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/test")
@Api("团队查询的检测")
public class QueryGroupController {
    @Autowired
    QueryGroupService queryGroupService;

    @PostMapping("/QueryGroup")
    @ApiOperation("查询团队接口")
    public R queryGroup(@RequestBody QueryGroupPojo queryGroupPojo) {
        List<Map<String,String>> list = queryGroupService.queryGroup(queryGroupPojo);
        if(list!=null){
            return R.ok().put("list",list);
        }else {
            return R.error("未查询到对应团队");
        }
    }

}

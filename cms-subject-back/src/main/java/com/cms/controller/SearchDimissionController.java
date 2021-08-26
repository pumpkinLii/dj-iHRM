package com.cms.controller;

import com.cms.pojo.AcceptDimissionPojo;
import com.cms.pojo.SearchDimissionPojo;
import com.cms.service.SearchDimissionService;
import com.cms.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/Dimission")
@Api("离职")
public class SearchDimissionController {
    @Autowired
    SearchDimissionService searchDimissionService;

    @PostMapping("/Dimission")
    @ApiOperation("离职接口")
    public R SearchDimission(@RequestBody AcceptDimissionPojo acceptDimissionPojo ) {
        return searchDimissionService.SearchDimission(acceptDimissionPojo);
//        if (StringUtils.isEmpty(acceptDimissionPojo.getAgentCode())) {
//            return R.error("工号未输入");
//        } else {
//            if (list.size()==0){
//            return R.ok("工号不存在");
//        }else {
//                return R.ok().put("list", list);
//            }
//            }
    }
}

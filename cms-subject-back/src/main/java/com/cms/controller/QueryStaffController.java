package com.cms.controller;

import com.cms.pojo.QueryStaffPojo;
import com.cms.pojo.QueryStaffReturn;
import com.cms.service.QueryStaffService;
import com.cms.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import static com.cms.util.SlelectPage.getPage;

@RestController
@CrossOrigin
@RequestMapping("/test")
@Api("人员信息查询的检测")
public class QueryStaffController {
    @Autowired
    QueryStaffService queryStaffService;

    @PostMapping("/QueryStaff")
    @ApiOperation("查询人员接口")
    public R queryStaff(@RequestBody QueryStaffPojo queryStaffPojo,int limit,int page) throws ParseException {
        List<QueryStaffReturn> list = queryStaffService.queryStaff(queryStaffPojo);
        List<QueryStaffReturn> list1 = getPage(limit,page,list);
        if (list1.size()>=0){
            return R.ok().put("list",list1).put("totalCount",list.size());
        }else {
            return R.error(500,"未查询到人员信息");
        }
    }
}

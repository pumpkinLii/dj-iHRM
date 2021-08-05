package com.cms.controller;

import cn.hutool.core.util.PageUtil;
import com.cms.util.SlelectPage;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cms.pojo.RYlLaBranchGrouReturn;
import com.cms.pojo.RYlLaBranchGroupPojo;
import com.cms.service.impl.RYlLaBranchGroupServiceImpl;
import com.cms.util.PageUtils;
import com.cms.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/query")
@Api("团队查询课题")
public class RYlLaBranchGroupController {
    //接受传进来的Pojo对象
    @Autowired
    RYlLaBranchGroupServiceImpl r_YlLaBranchGroupService;

    @ApiOperation("团队查询接口")
    @PostMapping("/group")
    public R creat(@RequestBody RYlLaBranchGroupPojo r_ylLaBranchGroupPojo, int page, int limit) {
        //page为页面的限制 就是一个页面存多少数据 limit是代表第几页
        //将会接受进来的数据封装为 一个 Pojo对象
        //  List<RYlLaBranchGrouReturn> list = r_YlLaBranchGroupService.Retrieve_group(r_ylLaBranchGroupPojo,page,limit);

        List list = r_YlLaBranchGroupService.Retrieve_group(r_ylLaBranchGroupPojo);
        Integer totalcount;
        totalcount = list.size();
        return R.ok().put("list", SlelectPage.getPage(limit,page,list)).put("totalcount", totalcount);
    }
}

package com.cms.controller;

import com.cms.service.EmpService;
import com.cms.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * 1 * @Author: zhangYiLong
 * 2 * @Date: 2021/8/2 20:10
 * 3 * @ClassName : EmpController
 */
@RestController
@CrossOrigin
@RequestMapping("/tes")
@Api("岗位对应职级")
public class EmpController {
    @Autowired
    private EmpService empService;

    @ApiOperation("岗位对应职级接口s")
    @PostMapping("/m")//1对应客户经理，0对应总监
    public R SearchM() {
       List list = empService.SearchM();
       return R.ok().put("list",list);
    }
    @ApiOperation("岗位对应职级接口a")
    @PostMapping("/g")
    public R SearchG() {
        List list = empService.SearchG();
        return R.ok().put("list",list);
    }
}

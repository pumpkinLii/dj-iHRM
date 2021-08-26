package com.cms.controller;

import com.cms.service.EmpService;
import com.cms.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cms.service.EmpService;
import com.cms.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/login/get")
@Api("下拉列表控制层")
public class DropDownController {


    /**
     * 1 * @Author: zhangYiLong
     * 2 * @Date: 2021/8/2 20:10
     * 3 * @ClassName : EmpController
     */

        @Autowired
        private EmpService empService;

        @ApiOperation("岗位对应职级接口s")
        @PostMapping("/manage")//1对应客户经理，0对应总监
        public R SearchM() {
            List list = empService.SearchM();
            return R.ok().put("list",list);
        }
        @ApiOperation("岗位对应职级接口a")
        @PostMapping("/director")
        public R SearchG() {
            List list = empService.SearchG();
            return R.ok().put("list",list);
        }
    }

package com.cms.controller;

import com.cms.common.ComnewSon;
import com.cms.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/get")
@Api("管理机构下拉列表查询端口")
public class MagGEtTest {
    @Autowired
    ComnewSon comnewSon;
    @ApiOperation("码表资源接口")
    @PostMapping("/da")
    public R test(String s){
        List<Map<String, String>> son = comnewSon.getSon(s);
        return R.ok().put("list",son);
    }

}

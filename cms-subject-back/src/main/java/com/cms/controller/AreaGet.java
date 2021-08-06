package com.cms.controller;

import com.cms.service.impl.AreaServiceImpl;
import com.cms.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/area")
@Api("团队新增课题")
public class AreaGet {
    @Autowired
    AreaServiceImpl areaService;

    @ApiOperation("城市列表接口")
    @PostMapping("/get")
    public Map getArea(){
        HashMap hashMap=areaService.getArea();
        hashMap.put("code",0);
        hashMap.put("msg","success");

        return hashMap;
    }

}

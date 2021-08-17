package com.cms.controller;

import com.cms.pojo.ManagerPojo;
import com.cms.service.GetManageInfoService;
import io.swagger.annotations.Api;
import com.cms.util.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/manager")
@Api("负责人")
public class GetManageInfoController  {
    @Autowired
    GetManageInfoService getManageInfoService;
    @ApiOperation("返回负责人信息接口")
    @PostMapping("/info")
    public R retManager(@RequestParam(value = "branchAttr") String branchAttr){
        ManagerPojo managerPojo= getManageInfoService.getManageInfo(branchAttr);
        if(managerPojo==null){
            return R.error("团队编码不存在");
        }
        return R.ok().put("data",managerPojo);
    }

}

package com.cms.controller;

import com.cms.pojo.Com4Pojo;
import com.cms.pojo.GroupInfoPojo;
import com.cms.pojo.ManagerPojo;
import com.cms.service.GetManageInfoService;
import io.swagger.annotations.Api;
import com.cms.util.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/returnMes")
@Api("回显")
public class GetManageInfoController  {
    @Autowired
    GetManageInfoService getManageInfoService;
    @ApiOperation("返回负责人信息接口")
    @PostMapping("/managerInfo")
    public R retManager(@RequestParam(value = "branchAttr") String branchAttr){
        ManagerPojo managerPojo= getManageInfoService.getManageInfo(branchAttr);
        if(managerPojo==null){
            return R.error("团队编码不存在");
        }
        return R.ok().put("data",managerPojo);
    }

    @ApiOperation("返回四级机构列表")
    @PostMapping("/com4Info")
    public R retCom4(){
        List<Com4Pojo> list = getManageInfoService.getCom4();
        return R.ok().put("list",list);
    }

    @ApiOperation("返回团队信息列表")
    @PostMapping("/groupInfo")
    public  R retGroup(@RequestParam(value = "manageCode") String agentGroup){
        List<GroupInfoPojo> list =getManageInfoService.getGroup(agentGroup);
        return R.ok().put("list",list);
    }



}

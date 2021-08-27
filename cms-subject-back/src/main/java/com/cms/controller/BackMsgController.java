package com.cms.controller;

import com.cms.pojo.*;
import com.cms.service.*;
import com.cms.service.impl.RCertificateImpl;
import com.cms.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/login/back")
@Api("养老人管模块课题")
public class BackMsgController {
    @Autowired
    YlAgentInfoService ylAgentInfoService;
    @Autowired
    RCertificateImpl rCertificateService;
    @Autowired
    GetManageInfoService getManageInfoService;
    @Autowired
    YlAgentCertificateService ylAgentCertificateService;
    @Autowired
    EchoGroupService echoGroupService;
    @Autowired
    EchoManagerService echoManagerService;
    @Autowired
    UYllaBranchGroupReturnService uYllaBranchGroupReturnService;

    @PostMapping("/gradeteam")
    @ApiOperation("根据职级返回架构团队")
    public R getgradeteam(@RequestBody GradeTeamPojo gradeTeam) {
        return ylAgentInfoService.getgradeteam(gradeTeam);
    }

    /*回显团队架构*/
    @PostMapping("/echogroup")
    @ApiOperation("回显团队架构接口")
    public R echoGroup(@RequestBody StaffPojo staffPojo) {
        List<Map<String, String>> mapList = echoGroupService.getGroup(staffPojo);
        if (mapList != null) {
            return R.ok().put("list", mapList);
        } else {
            return R.error(501, "失败：无对应团队");
        }
    }

    /*回显团队主管工号及姓名*/
    @PostMapping("/echomanager")
    @ApiOperation("回显团队主管工号及姓名接口")
    public R echoManager(@RequestBody GroupPojo groupPojo) {
        Map<String, String> map = echoManagerService.getManager(groupPojo);
        if (map != null) {
            return R.ok().put("data", map);
        } else {
            return R.error(501, "该团队无对应管理员");
        }
    }

    //陈益轩
    @ApiOperation("返回负责人信息接口")
    @PostMapping("/managerinfo")
    public R retManager(@RequestParam(value = "branchAttr") String branchAttr) {
        ManagerPojo managerPojo = getManageInfoService.getManageInfo(branchAttr);
        if (managerPojo == null) {
            return R.error("团队编码不存在");
        }
        return R.ok().put("data", managerPojo);
    }

    @ApiOperation("返回四级机构列表")
    @PostMapping("/com4info")
    public R retCom4() {
        List<Com4Pojo> list = getManageInfoService.getCom4();
        return R.ok().put("list", list);
    }

    @ApiOperation("返回团队信息列表")
    @PostMapping("/groupinfo")
    public R retGroup(@RequestParam(value = "manageCode") String agentGroup) {
        List<GroupInfoPojo> list = getManageInfoService.getGroup(agentGroup);
        return R.ok().put("list", list);
    }

    @PostMapping("/returnCom")
    @ApiOperation("机构回显接口")
    public R getComSon(@RequestParam(value = "code")String fatherCode){
        List<Map> list = rCertificateService.getComSon(fatherCode);
        if(list==null) {
            list = new ArrayList<>();
            return R.ok().put("list",list);
        }
        return R.ok().put("comList",list);
    }

    @PostMapping("/getgroupmanager")
    @ApiOperation("查询接口")
    public R find(@RequestBody ManagerCodePojo manager) {
        UYllaBranchGroupReturnPojo uYllaBranchGroupReturnPojo = uYllaBranchGroupReturnService.updataRe(manager.getAgentCode());
        if (uYllaBranchGroupReturnPojo != null) {
            if (uYllaBranchGroupReturnPojo.getAgentGrade().equals( "MA01")||uYllaBranchGroupReturnPojo.getAgentGrade().equals( "MA02")||uYllaBranchGroupReturnPojo.getAgentGrade() .equals( "MA03")) {
                if(uYllaBranchGroupReturnPojo.getAgentState().equals("03")||uYllaBranchGroupReturnPojo.getAgentState().equals("04")){
                    return R.error("该员工已经离职");
                }
                else return R.ok().put("data", uYllaBranchGroupReturnPojo);
            } else return R.error("该员工不是主管");
        } else {
            return R.error("该员工号码不存在");
        }
    }

    //张毅泷
    @ApiOperation("工号回显姓名")
    @PostMapping("/searchNameById")
    public R SearchNameById(@RequestBody CeInsertPojo ceInsertPojo) {
        String result1 = ylAgentCertificateService.SearchNameById(ceInsertPojo);
        if (result1!=null) {
            return R.ok().put("agentName", result1);
        }else {
            return R.ok().put("code",501).put("msg","该工号不存在");
        }
    }

}

package com.cms.controller;

import com.cms.entity.YlLaBranchGroupEntity;
import com.cms.pojo.UYllaBranchGroupReturnPojo;
import com.cms.pojo.UYllabranchGroupPojo;
import com.cms.service.UYllaBranchGroupReturnService;
import com.cms.service.UYllaBranchGroupService;
import com.cms.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/test")
@Api("用户登录的检测")
public class UYllaBranchGroupController {
    @Autowired
    UYllaBranchGroupService uyllaBranchGroupService;


    @PostMapping("/UpdateGroup")
    @ApiOperation("团队修改接口")
    public R updataGroup(@RequestBody UYllabranchGroupPojo uYllabranchGroupPojo) {
        if (uYllabranchGroupPojo.getManageComCode().equals("")) return R.error("管理机构不能为空");
        if (uYllabranchGroupPojo.getBranchLevel().equals("")) return R.error("组织级别不能为空");
        if (uYllabranchGroupPojo.getBranchName().equals("")) return R.error("组织名称不能为空");
        if (uYllabranchGroupPojo.getBranchEffDate().equals("")) return R.error("成立时间不能为空");
        if (uYllabranchGroupPojo.getBranchStatus().equals("")) return R.error("停业标志不能为空");
        if (uYllabranchGroupPojo.getBranchStatus().equals("Y") && uYllabranchGroupPojo.getBranchTerminateEffDate().equals(""))
            return R.error("停业时停业时间不能为空");
        if(uYllabranchGroupPojo.getBranchStatus().equals("N")&&!uYllabranchGroupPojo.getBranchTerminateEffDate().equals(""))
            return R.error("尚未停业");
        if (uYllabranchGroupPojo.getOperator().equals("")) return R.error("操作员不能为空");
        if (uYllabranchGroupPojo.getChatName().equals("")) return R.error("群聊名称不能为空");
        YlLaBranchGroupEntity uu = uyllaBranchGroupService.update(uYllabranchGroupPojo);
        if (uu != null) {
            if (uu.getBranchManager() != null) {
                if (uu.getBranchManager().equals("查无此人")) return R.error("负责人工号不存在");
                if (uu.getBranchManager().equals("此人并非主管")) return R.error("职级不足");
            }
            if (uu.getAgentGroup().equals("该团队下有在职员工")) return R.error("该团队有在职员工");
            return R.ok().put("0", "修改成功！");
        } else {
            return R.error("该团队号码不存在");
        }
    }

    @Autowired
    UYllaBranchGroupReturnService uYllaBranchGroupReturnService;

    @PostMapping("/UpdataGroupManager")
    @ApiOperation("查询接口")
    public R find(@RequestParam String agentCode) {
        UYllaBranchGroupReturnPojo uYllaBranchGroupReturnPojo = uYllaBranchGroupReturnService.updataRe(agentCode);
        if (uYllaBranchGroupReturnPojo != null) {
            if (uYllaBranchGroupReturnPojo.getAgentGrade() == "主管") {
                return R.ok().put("data", uYllaBranchGroupReturnPojo);
            } else return R.error("该员工不是主管");
        } else {
            return R.error("该员工号码不存在");
        }
    }

}


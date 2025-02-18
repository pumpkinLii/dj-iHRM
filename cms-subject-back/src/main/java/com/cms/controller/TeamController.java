package com.cms.controller;

import com.cms.entity.YlLaBranchGroupEntity;
import com.cms.pojo.CYlLaBranchGroupPojo;
import com.cms.pojo.QueryGroupPojo;
import com.cms.pojo.RYlLaBranchGroupPojo;
import com.cms.pojo.UYllabranchGroupPojo;
import com.cms.service.TeamService;
import com.cms.service.UYllaBranchGroupService;
import com.cms.service.impl.CYlLaBranchGroupServiceImpl;
import com.cms.service.impl.RYlLaBranchGroupServiceImpl;
import com.cms.util.R;
import com.cms.util.SlelectPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/login/Team")
@Api("团队模块")
public class TeamController {
    @Autowired
    CYlLaBranchGroupServiceImpl c_ylLaBranchGroupService;
    @Autowired
    RYlLaBranchGroupServiceImpl r_YlLaBranchGroupService;
    @Autowired
    TeamService teamService;
    @Autowired
    UYllaBranchGroupService uyllaBranchGroupService;

    @ApiOperation("团队新增接口")
    @PostMapping("/insertgroup")
    public R creat(@RequestBody CYlLaBranchGroupPojo c_ylLaBranchGroupPojo) throws ParseException {
        //抛出异常 是因为 前端传进来的Date的不会有错误的
        return c_ylLaBranchGroupService.creat(c_ylLaBranchGroupPojo);
    }

    //接受传进来的Pojo对象
    @ApiOperation("团队查询接口")
    @PostMapping("/querygroup")
    public R creat(@RequestBody RYlLaBranchGroupPojo r_ylLaBranchGroupPojo, int page, int limit) {
        //page为页面的限制 就是一个页面存多少数据 limit是代表第几页
        //将会接受进来的数据封装为 一个 Pojo对象
        //  List<RYlLaBranchGrouReturn> list = r_YlLaBranchGroupService.Retrieve_group(r_ylLaBranchGroupPojo,page,limit);
        List list = r_YlLaBranchGroupService.Retrieve_group(r_ylLaBranchGroupPojo);
        Integer totalcount=0;
        if (list!=null){
            totalcount = list.size();
        }
        return R.ok().put("list", SlelectPage.getPage(limit,page,list)).put("totalcount", totalcount);
    }

    //池浩玥
    @PostMapping("/queryGroupbyCom4")
    @ApiOperation("查询团队四级机构接口")
    public R queryGroup(@RequestBody QueryGroupPojo queryGroupPojo) {
        List<Map<String,String>> list = teamService.queryGroup(queryGroupPojo);
        if(list!=null){
            return R.ok().put("list",list);
        }else {
            return R.error("未查询到对应团队");
        }
    }

    //陈益轩
    @PostMapping("/updateGroup")
    @ApiOperation("团队修改接口")
    public R updataGroup(@RequestBody UYllabranchGroupPojo uYllabranchGroupPojo) {
        if (uYllabranchGroupPojo.getManageComCode()==null||uYllabranchGroupPojo.getManageComCode().equals("")) return R.error("管理机构不能为空");
        if (uYllabranchGroupPojo.getBranchLevel()==null||uYllabranchGroupPojo.getBranchLevel().equals("")) return R.error("组织级别不能为空");
        if (uYllabranchGroupPojo.getBranchName()==null||uYllabranchGroupPojo.getBranchName().equals("")) return R.error("组织名称不能为空");
        if (uYllabranchGroupPojo.getBranchEffDate()==null||uYllabranchGroupPojo.getBranchEffDate().equals("")) return R.error("成立时间不能为空");
        if (uYllabranchGroupPojo.getBranchStatus()==null||uYllabranchGroupPojo.getBranchStatus().equals("")) return R.error("停业标志不能为空");
        if (uYllabranchGroupPojo.getBranchStatus().equals("Y") &&(uYllabranchGroupPojo.getBranchTerminateEffDate()==null|| uYllabranchGroupPojo.getBranchTerminateEffDate().equals("")))
            return R.error("停业时停业时间不能为空");
        if(uYllabranchGroupPojo.getBranchStatus().equals("N")&&uYllabranchGroupPojo.getBranchTerminateEffDate()!=null&&!uYllabranchGroupPojo.getBranchTerminateEffDate().equals(""))
            return R.error("尚未停业");
        if (uYllabranchGroupPojo.getOperator()==null||uYllabranchGroupPojo.getOperator().equals("")) return R.error("操作员不能为空");
        if (uYllabranchGroupPojo.getChatName()==null||uYllabranchGroupPojo.getChatName().equals("")) return R.error("群聊名称不能为空");
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
}

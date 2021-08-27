package com.cms.controller;

import com.cms.pojo.LaAgentUpdatePojo;
import com.cms.pojo.QueryStaffPojo;
import com.cms.pojo.QueryStaffReturn;
import com.cms.pojo.QueryWithCodeReturn;
import com.cms.service.*;
import com.cms.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import static com.cms.util.SlelectPage.getPage;

@RestController
@CrossOrigin
@RequestMapping("/login/YlAgentUpdate")
@Api("人员维护模块")
public class YlAgentUpdateController {
    @Autowired
    QueryStaffService queryStaffService;
    @Autowired
    QueryWithCodeService queryWithCodeService;
    @Autowired
    private YlAgentInfoService ylAgentInfoService;
    @Autowired
    private YlAgentAttrInfoService ylAgentAttrInfoService;

    //池浩玥
    @PostMapping("/queryStaff")
    @ApiOperation("查询人员接口")
    public R queryStaff(@RequestBody QueryStaffPojo queryStaffPojo, int limit, int page) throws ParseException {
        List<QueryStaffReturn> list = queryStaffService.queryStaff(queryStaffPojo);
        List<QueryStaffReturn> list1 = getPage(limit,page,list);
        if (list1.size()>=0){
            return R.ok().put("list",list1).put("totalCount",list.size());
        }else {
            return R.error(500,"未查询到人员信息");
        }
    }
    //池浩玥
    @PostMapping("/queryWithCode")
    @ApiOperation("根据agentcode查询接口")
    public R queryWithCode(@RequestParam("agentCode") String agentCode) throws ParseException {
        QueryWithCodeReturn qwcr = queryWithCodeService.queryInfo(agentCode);
        if (!qwcr.getAgentName().equals("")){
            return R.ok().put("form",qwcr);
        }else {
            return R.ok("未查到人员信息");
        }
    }

    //张晓成
    @PostMapping("/update")
    @ApiOperation("人员信息修改接口")
    public R laAgentUpdate(@RequestBody LaAgentUpdatePojo laAgent){
        String agentUpdate = ylAgentAttrInfoService.agentUpdate(laAgent);
        if(agentUpdate.equals("success")){
            if(ylAgentInfoService.laAgentUpdate(laAgent)){
                return R.ok("操作成功！");
            }
            return R.error("录入失败,请联系后端维护人员");
        }
        else{
            return R.error(agentUpdate);
        }
    }
}

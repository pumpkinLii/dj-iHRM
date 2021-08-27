package com.cms.controller;

import com.cms.pojo.ResultListPojo;
import com.cms.pojo.SearchListPojo;
import com.cms.pojo.YlLaAgentChangePojo;
import com.cms.service.SearchLIstService;
import com.cms.service.YlLaAgentInfoChangeService;
import com.cms.util.R;
import com.cms.util.SlelectPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/login/YlAgentInsert")
@Api("人员异动模块")
public class YlAgentToTeamController {
    @Autowired
    SearchLIstService searchLIstService;
    @Autowired
    private YlLaAgentInfoChangeService ylLaAgentInfoChangeService;

    //张毅珑
    @PostMapping("/return")
    @ApiOperation("列表查询接口")
    @ResponseBody
    public R returnList(@RequestParam(value ="page") int page,@RequestParam(value ="limit") int limit, @RequestBody SearchListPojo searchListPojo) {
        List<ResultListPojo> list = searchLIstService.searchList(searchListPojo);
        List page1 = SlelectPage.getPage(limit, page, list);
        if (page1.size()>0) {
            return R.ok().put("list",page1).put("totalCount",list.size());
        }else {
            return R.error("无符合数据").put("code",501).put("totalCount",0);
        }
    }

    //张晓成
    private String resultInfo;
    @PostMapping("/change")
    @ApiOperation("人员异动接口")
    public R laAgentChange(@RequestBody YlLaAgentChangePojo ylLaAgentChangePojo){
        resultInfo = ylLaAgentInfoChangeService.ylLaAgentChange(ylLaAgentChangePojo);
        if(resultInfo.equals("success")){
            return R.ok("操作成功！");
        }
        else{
            return R.error("操作失败！" + resultInfo);
        }
    }
}

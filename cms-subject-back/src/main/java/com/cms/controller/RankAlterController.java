package com.cms.controller;

import com.cms.pojo.IdCheckPojo;
import com.cms.pojo.RankAlterPojo;
import com.cms.service.NIdCheckService;
import com.cms.service.RankAlterService;
import com.cms.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping({"/rank"})
@Api("养老人管模块课题")
public class RankAlterController {
    @Autowired
    private RankAlterService rankAlterService;

    @ApiOperation("职级调整保存接口")
    @PostMapping({"/alter"})
    public R rankAlter(@RequestBody RankAlterPojo rankAlterPojo) {
        String str = rankAlterService.rankAlter(rankAlterPojo);
        if(str.equals("保存成功"))
        {
            return R.ok().put("0",str);

        }
        return R.error(500,str);
    }

}

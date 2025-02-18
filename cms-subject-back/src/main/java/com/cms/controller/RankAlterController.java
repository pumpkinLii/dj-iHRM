package com.cms.controller;

import com.cms.pojo.GradeQueryPojo;
import com.cms.pojo.GradeQueryReturnPojo;
import com.cms.pojo.RankAlterPojo;
import com.cms.service.RankAlterService;
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
@RequestMapping({"/login/rank"})
@Api("职级调整模块")
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

    @PostMapping("/gradeQuery")
    @ApiOperation("职级查询接口")
    public R gradeQuery(@RequestBody GradeQueryPojo gradeQueryPojo, int limit, int page) throws ParseException {
        if (gradeQueryPojo.getManageCom().isEmpty()){
            return R.error(501,"请补全必填项");
        }
        List<GradeQueryReturnPojo> list = rankAlterService.gradeQuery(gradeQueryPojo);
        List<GradeQueryReturnPojo> list1 = getPage(limit,page,list);
        if (list1.size()>=0){
            return R.ok().put("list",list1).put("totalCount",list.size());
        }else {
            return R.error(500,"未查到信息");
        }
    }
}

package com.cms.controller;

import com.cms.pojo.GradeQueryPojo;
import com.cms.pojo.GradeQueryReturnPojo;
import com.cms.service.GradeQueryService;
import com.cms.service.impl.GradeQueryServiceImpl;
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
@RequestMapping("/test")
@Api("养老渠道-职级查询")
public class GradeQueryController {
    @Autowired
    GradeQueryService gradeQueryService;
    @PostMapping("/GradeQuery")
    @ApiOperation("职级查询接口")
    public R gradeQuery(@RequestBody GradeQueryPojo gradeQueryPojo,int limit,int page) throws ParseException {
        if (gradeQueryPojo.getManageCom().isEmpty()){
            return R.error(501,"请补全必填项");
        }
        List<GradeQueryReturnPojo> list = gradeQueryService.gradeQuery(gradeQueryPojo);
        List<GradeQueryReturnPojo> list1 = getPage(limit,page,list);
        if (list1.size()>=0){
            return R.ok().put("list",list1).put("totalCount",list.size());
        }else {
            return R.error(500,"未查到信息");
        }
    }
}

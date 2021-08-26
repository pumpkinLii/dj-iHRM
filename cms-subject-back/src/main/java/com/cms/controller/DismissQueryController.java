package com.cms.controller;

import com.cms.pojo.DismissQueryPojo;
import com.cms.pojo.DismissQueryReturnPojo;
import com.cms.pojo.ResultListPojo;
import com.cms.pojo.SearchListPojo;
import com.cms.service.DismissQueryService;
import com.cms.service.SearchLIstService;
import com.cms.util.R;
import com.cms.util.SlelectPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/dismiss")
@Api("离职申请查询")
public class DismissQueryController {
    @Autowired
    DismissQueryService dismissQueryService;

    @PostMapping("/query")
    @ApiOperation("离职申请查询")
    @ResponseBody
    public R returnList(@RequestParam(value ="page") int page, @RequestParam(value ="limit") int limit, @RequestBody DismissQueryPojo dismissQueryPojo) {
        List<DismissQueryReturnPojo> list = dismissQueryService.searchList(dismissQueryPojo);
        List page1 = SlelectPage.getPage(limit, page, list);
        if (page1.size()>0) {
            return R.ok().put("list",page1).put("totalCount",page1.size());
        }else {
            return R.error("无符合数据");
        }
    }
}

package com.cms.controller;

import com.cms.pojo.ResultListPojo;
import com.cms.pojo.SearchListPojo;
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
@RequestMapping("/search")
@Api("列表查询")
public class SearchListController {
    @Autowired
    SearchLIstService searchLIstService;

    @PostMapping("/return")
    @ApiOperation("列表查询接口")
    @ResponseBody
    public R returnList(@RequestParam(value ="page") int page,@RequestParam(value ="limit") int limit, @RequestBody SearchListPojo searchListPojo) {
        List<ResultListPojo> list = searchLIstService.searchList(searchListPojo);
        List page1 = SlelectPage.getPage(limit, page, list);
        if (page1.size()>0) {
            return R.ok().put("list",page1).put("totalCount",page1.size());
        }else {
            return R.error("无符合数据");
        }
    }
}

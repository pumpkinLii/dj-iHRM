package com.cms.controller;

import com.cms.service.MagService;
import com.cms.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 1 * @Author: zhangYiLong
 * 2 * @Date: 2021/8/3 19:17
 * 3 * @ClassName : MagController
 */
@RestController
@CrossOrigin
@RequestMapping("/get")
@Api("管理机构下拉列表查询端口")
public class MagController {
    @Autowired
    private MagService magService;

    @ApiOperation("管理机构下拉列表")
    @PostMapping("/managecom")
    public R SearchMap() {
        List list= magService.SearchMap();
        List list1=(List)list.get(0);
        List list2=(List) list.get(1);
        List list3=(List)list.get(2);
        List list4=(List)list.get(3);
        List totallist=(List) list.get(4);
        return R.ok().put("list1",list1).put("list2",list2).put("list3",list3).put("list4",list4).put("totallist",totallist);

    }
}

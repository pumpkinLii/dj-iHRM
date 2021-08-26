package com.cms.controller;

import com.cms.pojo.DepartModifyPojo;
import com.cms.pojo.DepartQueryReturnPojo;
import com.cms.service.DepartModifyService;
import com.cms.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@CrossOrigin
@RequestMapping("/depart")
@Api("离司申请修改接口")
public class LeavingApplicationController {
    @Autowired
    DepartModifyService departModifyService;
    @PostMapping("/Modify")
    @ApiOperation("离司申请修改接口")
    public R departModify(@RequestBody DepartModifyPojo departModifyPojo) throws ParseException{
        String keyString = departModifyService.departModify(departModifyPojo);
        if (keyString.equals("0")){
            return R.ok().put("msg","修改成功");
        }else if (keyString.equals("1")){
            return R.error(501,"离职申请已提交审核或已通过审核");
        }else if (keyString.equals("2")){
            return R.error(501,"请补全必填项");
        }
        return R.error(500,"发生错误请重试");
    }


}

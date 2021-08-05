package com.cms.controller;

import com.cms.pojo.IdCheckPojo;
import com.cms.pojo.LaAgentPojo;
import com.cms.service.IdCheckService;
import com.cms.service.NIdCheckService;
import com.cms.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping({"/test"})
@Api("养老人管模块课题")
public class IdCheckController {
    @Autowired
    private NIdCheckService nIdCheckService;

    @ApiOperation("测试接口")
    @PostMapping({"/idCheck"})
    public R idcheck(IdCheckPojo idCheckPojo) {
        System.out.println(idCheckPojo.getIdNo());
        System.out.println(idCheckPojo.getIdType());
        int fg = nIdCheckService.idcheck(idCheckPojo);
        switch(fg) {
            case 1:
                return R.ok().put("0", "证件检验正确");
            case 2:
                return R.error(500, "具有相同证件号码的人已经存在或此人已在其他渠道任职，并且尚未离职");
            case 3:
                return R.error(500, "该人员离职不满六个月，不可二次入司");
            case 4:
                return R.error(500, "具有相同证件号码的人已存在且离职，此人员为二次入司");
            case 5:
                return R.error(500, "出现未知错误");
            case 6:
                return R.error(500, "请输入证件号码");
            case 7:
                return R.error(500, "证件号码不是字母数字格式");
            case 8:
                return R.error(500, "证件号码应小于等于18位");
            case 9:
                return R.error(500, "身份证号码错误");
        }
        return R.error(500, "出现未知错误");
    }
}

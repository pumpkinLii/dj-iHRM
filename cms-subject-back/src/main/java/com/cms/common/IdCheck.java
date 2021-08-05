package com.cms.common;


import com.cms.pojo.LaAgentPojo;
import com.cms.service.IdCheckService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class IdCheck {
    @Autowired
    private IdCheckService idCheckService;

    @ApiOperation("测试接口")
    public int idcheck(LaAgentPojo laAgent) {
        int fg = idCheckService.idcheck(laAgent);
        switch(fg) {
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 3;
            case 4:
                return 4;
            case 5:
                return 5;
            case 6:
                return 6;
            case 7:
                return 7;
            case 8:
                return 8;
            case 9:
                return 9;
        }
        return  9;
    }
}

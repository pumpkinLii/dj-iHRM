package com.cms.controller;

import com.cms.pojo.UserPojo;
import com.cms.service.UserService;
import com.cms.service.impl.UserServiceImpl;
import com.cms.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
@RequestMapping("/test")
@Api("用户登录的检测")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/login")
    @ApiOperation("登陆接口")
    public R login(@RequestBody UserPojo userPojo) {
        String userId = userPojo.getUserId();
        String userPassword = userPojo.getUserPassword();
        boolean bl = userService.login(userId, userPassword);
        if (bl == true) {
            return R.ok().put("0", "登陆成功！");
        } else {
            return R.error("登陆失败！");
        }
    }
}

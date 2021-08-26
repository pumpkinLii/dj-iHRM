package com.cms.controller;

import com.cms.common.TokenUtils;
import com.cms.pojo.UserPojo;
import com.cms.service.UserService;
import com.cms.service.impl.UserServiceImpl;
import com.cms.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@CrossOrigin
@RequestMapping("/test")
@Api("用户登录的检测")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/login")
    @ApiOperation("登陆接口")
    public R login(@RequestBody UserPojo userPojo,HttpServletRequest httpServletRequest) throws InvalidKeyException, NoSuchAlgorithmException {
        String creattoken= TokenUtils.SH256parse(userPojo.toString());
        String token=httpServletRequest.getHeader("x-token");
        System.out.println("token 为"+token);
        if (StringUtils.isEmpty(token)==true||token==null){
            boolean bl = userService.login(userPojo.getUserId(),userPojo.getUserPassword());
            if (bl==true){
                return R.ok().put("token",creattoken);
            }else {
                return R.error("账号密码错误");
            }
        }else{
            if (creattoken.equals(token)){
                return R.ok().put("msg","通过token登陆成功(未从数据库验证用户信息 节省服务器资源)");
            }else {
                return R.ok().put("code",502).put("msg","您的token过期请您重新登陆");
            }
        }


    }
    @PostMapping("/logout")
    @ApiOperation("登陆接口")
    public R loginout(){
        TokenUtils.setsecret_key();
        //密钥更新完毕
        return R.ok();
    }
}

package com.cms.controller;

import com.cms.entity.YlLaAgentCertificateEntity;
import com.cms.pojo.ChangeCertificatePojo;
import com.cms.service.impl.YlAgentCertificateServiceImpl;
import com.cms.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

/**
 * @author :zhanhaoze
 * @description :
 * @create :2021-08-06
 */
@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/certificate")
@Api("养老渠道-人员管理-资格证管理")
public class YlAgentCertificateController {
    @Autowired
    YlAgentCertificateServiceImpl ylAgentCertificateService;

    @PostMapping("/Update")
    @ApiOperation("资格证书修改接口")
    public R Change(@RequestBody ChangeCertificatePojo changeCertificatePojo) throws ParseException {
        //只用一个批准单位为空 其他的均为必填选项
        R r = ylAgentCertificateService.changeCertificateService(changeCertificatePojo);
        return r;
    }
}

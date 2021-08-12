package com.cms.controller;

import com.cms.entity.YlLaAgentCertificateEntity;
import com.cms.pojo.CertificateConditionPojo;
import com.cms.pojo.ChangeCertificatePojo;
import com.cms.pojo.RetrieveCertificatePojo;
import com.cms.service.impl.RCertificateImpl;
import com.cms.service.impl.YlAgentCertificateServiceImpl;
import com.cms.util.R;
import com.cms.util.SlelectPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

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

    @Autowired
    RCertificateImpl rCertificateService;

    @PostMapping("/Update")
    @ApiOperation("资格证书修改接口")
    public R Change(@RequestBody ChangeCertificatePojo changeCertificatePojo) throws ParseException {
        //只用一个批准单位为空 其他的均为必填选项
        R r = ylAgentCertificateService.changeCertificateService(changeCertificatePojo);
        return r;
    }

    @PostMapping("/retrieve")
    @ApiOperation("资格证查询接口")
    public R getCertificate(@RequestParam(value = "currentPage") int currentPage,
                            @RequestParam(value = "pageSize") int pageSize,
                            @RequestBody CertificateConditionPojo getCertificateConditionPojo) {
        List<RetrieveCertificatePojo> list = rCertificateService.getCertificate( getCertificateConditionPojo);
        if(list==null) return  R.error("未查到");
        return R.ok().put("list", SlelectPage.getPage(pageSize,currentPage,list)).put("totalcount",list.size());
    }
    @PostMapping("/returnCom")
    @ApiOperation("机构回显接口")
    public R getComSon(@RequestParam(value = "code")String fatherCode){
        List<Map> list = rCertificateService.getComSon(fatherCode);
        if(list==null) return R.error("未查到");
        return R.ok().put("comList",list);
    }
    @PostMapping("/initList")
    @ApiOperation("机构初始列表")
    public R initList(){
        List<Map> com2List = rCertificateService.initCom2();
        List<Map> cerficateTypeList = rCertificateService.initCertificate();
        return R.ok().put("com2List",com2List).put("certificateTypeList",cerficateTypeList);
    }
}

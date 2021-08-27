package com.cms.controller;

import com.cms.pojo.CertificateConditionPojo;
import com.cms.pojo.ChangeCertificatePojo;
import com.cms.pojo.RetrieveCertificatePojo;
import com.cms.service.impl.RCertificateImpl;
import com.cms.util.R;
import com.cms.util.SlelectPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import com.cms.pojo.CeInsertPojo;
import com.cms.service.YlAgentCertificateService;

import java.text.ParseException;
import java.util.ArrayList;
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
@RequestMapping("/login/YlAgentCertificate")
@Api("资格证管理")
public class YlAgentCertificateController {
    @Autowired
    YlAgentCertificateService ylAgentCertificateService;
    @Autowired
    RCertificateImpl rCertificateService;

    //王佳智
    @PostMapping("/update")
    @ApiOperation("资格证书修改接口")
    public R Change(@RequestBody ChangeCertificatePojo changeCertificatePojo) throws ParseException {
        //只用一个批准单位为空 其他的均为必填选项
        R r = ylAgentCertificateService.changeCertificateService(changeCertificatePojo);
        return r;
    }

    //陈益轩
    @PostMapping("/retrieve")
    @ApiOperation("资格证查询接口")
    public R getCertificate(@RequestParam(value = "currentPage") int currentPage,
                            @RequestParam(value = "pageSize") int pageSize,
                            @RequestBody CertificateConditionPojo getCertificateConditionPojo) {
        List<RetrieveCertificatePojo> list = rCertificateService.getCertificate( getCertificateConditionPojo);
        if(list==null) {
            list = new ArrayList<>();
            return R.ok().put("list",list).put("totalcount",0);
        }
        return R.ok().put("list", SlelectPage.getPage(pageSize,currentPage,list)).put("totalcount",list.size());
    }

    //陈益轩
    @PostMapping("/initList")
    @ApiOperation("机构初始列表")
    public R initList(){
        List<Map> com2List = rCertificateService.initCom2();
        List<Map> cerficateTypeList = rCertificateService.initCertificate();
        return R.ok().put("com2List",com2List).put("certificateTypeList",cerficateTypeList);
    }

    //张毅珑
    @ApiOperation("校验工号是否存在")
    @PostMapping("/noIdcheck")
    public String idcheck(@RequestBody CeInsertPojo ceInsertPojo) {
        String result = ylAgentCertificateService.Idcheck(ceInsertPojo);
        return result;
    }

    //张毅珑
    @ApiOperation("工号回显姓名")
    @PostMapping("/searchNameById")
    public R SearchNameById(@RequestBody CeInsertPojo ceInsertPojo) {
        String result1 = ylAgentCertificateService.SearchNameById(ceInsertPojo);
        if (result1!=null) {
            return R.ok().put("agentName", result1);
        }else {
            return R.ok().put("code",501).put("msg","数据库没有此工号");
        }
    }

    //张毅珑
    @ApiOperation("插入资格证信息")
    @PostMapping("/insert")
    //@RequestBody注解是接收前端返回的json并封装为****pojo
    public R insertCertificate(@RequestBody CeInsertPojo ceInsertPojo) throws Exception {
        if (StringUtils.isEmpty(ceInsertPojo.getAgentCode())){
            return R.error("人员工号不能为空");
        }
        if (StringUtils.isEmpty(ceInsertPojo.getCertificateType())){
            return R.error("资格证类型不能为空");
        }
        if (StringUtils.isEmpty(ceInsertPojo.getCertificateName())){
            return R.error("资格证姓名不能为空");
        }
        if (StringUtils.isEmpty(ceInsertPojo.getCertificateNo())){
            return R.error("资格证号码不能为空");
        }
        ylAgentCertificateService.initializeEdorno(ceInsertPojo);
        int i = ylAgentCertificateService.InsertCertificate(ceInsertPojo);
        if(i > 0) {
            return R.ok();
        }else if(i == 0) {
            return R.error("插入资格证信息重复");
        }else {
            return R.error("插入资格证信息失败");
        }
    }
}

package com.cms.controller;

import com.alibaba.excel.util.StringUtils;
import com.cms.common.ComnewSon;
import com.cms.common.ProxyComNewReturn;
import com.cms.service.*;
import com.cms.service.impl.AreaServiceImpl;
import com.cms.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cms.service.EmpService;
import com.cms.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/login/get")
@Api("下拉列表控制层")
public class DropDownController {


    /**
     * 1 * @Author: zhangYiLong
     * 2 * @Date: 2021/8/2 20:10
     * 3 * @ClassName : EmpController
     */
    @Autowired
    AreaServiceImpl areaService;
    @Autowired
    private EmpService empService;
    @Autowired
    IdCodeService idCodeService;
    @Autowired
    ComnewSon comnewSon;
    @Autowired
    private MagService magService;
    @Autowired
    ProxyComNewReturn proxyComNewReturn;
    @Autowired
    LaAgentGradeService laAgentGradeService;

    @ApiOperation("管理机构下拉列表")
    @PostMapping("/managecom")
    public R SearchMap() {
        List list = magService.SearchMap();
        List list1 = (List) list.get(0);
        List list2 = (List) list.get(1);
        List list3 = (List) list.get(2);
        List list4 = (List) list.get(3);
        List totallist = (List) list.get(4);
        return R.ok().put("list1", list1).put("list2", list2).put("list3", list3).put("list4", list4).put("totallist", totallist);

    }

    @ApiOperation("城市列表接口")
    @PostMapping("/area")
    public Map getArea(){
        List list =areaService.getArea();
        return R.ok().put("province",list);
    }
    //下面这个是 有树状图的下拉列表 分级管理的模式 根据获取code的长度从而返回不同的
//请求参数为“”的下拉款 我返回总的数据 请求参数为两位 就返回4位 同理进行操作
    @ApiOperation("树状图管理机构下拉列表")
    @PostMapping("/gets")
    public R getMap(String Code) {
        //这里进行条件的判断
        if (StringUtils.isEmpty(Code) == true) {
            return R.ok().put("list", comnewSon.getHighManageCom());
        } else {
            return R.ok().put("list", comnewSon.getSonManageCom(Code));
        }
    }

    @ApiOperation("children的结构管理机构")
    @PostMapping("/comresource")
    public R getResource() {
        return R.ok().put("result", proxyComNewReturn.Start());
    }


    @ApiOperation("码表资源接口")
    @PostMapping("/idcode")
    public R getidResource() {

        return R.ok().put("resource", idCodeService.getResource());
    }


    @ApiOperation("职级下拉框接口")
    @PostMapping("/grade")
    public R getAgentGrade() {
        return R.ok().put("list", laAgentGradeService.method());
    }

    @ApiOperation("下载新增接口")
    @PostMapping("/load")
    public R download(HttpServletResponse httpServletResponse, MultipartFile file) throws IOException {
        OutputStream outputStream = new FileOutputStream("D:\\附件目录\\" + file.getOriginalFilename());
        InputStream inputStream = file.getInputStream();
        byte[] bytes = new byte[1024];
        while ((inputStream.read(bytes)) > 0) {
            outputStream.write(bytes);
        }
        outputStream.flush();
        outputStream.close();
        return R.ok();
    }

    @ApiOperation("岗位对应职级接口s")
    @PostMapping("/manage")//1对应客户经理，0对应总监
    public R SearchM() {
        List list = empService.SearchM();
        return R.ok().put("list", list);
    }

    @ApiOperation("岗位对应职级接口a")
    @PostMapping("/director")
    public R SearchG() {
        List list = empService.SearchG();
        return R.ok().put("list", list);
    }
}

package com.cms.controller;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import com.cms.pojo.CertExcellPojo;
import com.cms.pojo.IdCheckPojo;
import com.cms.pojo.YlSelectExcellPojo;
import com.cms.service.CertInsertExcellService;
import com.cms.service.NIdCheckService;
import com.cms.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping({"/cert"})
@Api("养老人管模块课题")
public class CertInsertExcellController {

//    @ApiOperation("测试接口")
//    @PostMapping({"/ExcellInsert"})
//    public void idcheck(@RequestParam("file") MultipartFile file) throws Exception {
//        ImportParams Params=new ImportParams();
//        Params.setTitleRows(1);
//        Params.setHeadRows(1);
//        System.out.println(file.getInputStream());
//        List<CertExcellPojo> list=ExcelImportUtil.importExcel(file.getInputStream(),CertExcellPojo.class,Params);
//
//        System.out.println(list.size());
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println(list);
//        }
//    }
//    @ApiOperation("测试接口")
//    @RequestMapping("/import2")
//    public void upload(MultipartFile file) throws Exception {
//        //导入的基本配置
//        ImportParams params = new ImportParams();
//        //表头一行
//        params.setHeadRows(1);
//        //标题一行
//        params.setTitleRows(1);
//        //代表导入这里是需要验证的（根据字段上的注解校验）
//        params.setNeedVerify(true);
//        //使用框架自身导入工具
//        ExcelImportResult<CertExcellPojo> result = ExcelImportUtil.importExcelMore(file.getInputStream(), CertExcellPojo.class, params);
//
//        System.out.println(result);
//
//        //        //导入成功的数据
////        List<UserVo> list = result.getList();
////        //失败结果集
////        List<UserVo> failList = result.getFailList();
////        return failList;
//    }
//    @ApiOperation("测试接口")
//    @PostMapping("/import1")
//    public void importExcel(@RequestParam("file") MultipartFile file) throws IOException {
//        List<CertExcellPojo> list = ExcelUtils.importExcel(file.getInputStream(),1,1,true, CertExcellPojo.class);
//        for (int i = 0; i <list.size() ; i++) {
//            System.out.println(list);
//        }
//    }

    @Autowired
    CertInsertExcellService certInsertExcellService;

    @ApiOperation("测试接口")
    @PostMapping({"/ExcellInsert"})
    public R ylLaAgentAttrExcelIn(@RequestParam("file") MultipartFile file) throws IOException, ParseException {
        ArrayList<HashMap<String,String>> list=new ArrayList<HashMap<String,String>>();
        list=this.certInsertExcellService.check(file);
        HashMap<String,String> mapp=new HashMap<String,String>();
        if(list.size()==1)
        {
            HashMap<String,String> map= new HashMap<String, String>();
            map = list.get(0);
            if(map.get("批量导入失败！Excel文件的内容不能为空！")!=null)
            {
                return R.error(500,"批量导入失败！Excel文件的内容不能为空！");
            }
            else if(map.get("导入成功！")!=null)
            {
                return R.ok().put("0","导入成功!");
            }
            else{
                return R.error().put("500",list);
            }
        }
        else {
            return R.error(500,"批量导入失败！").put("错误原因:",list);
        }
    }
}

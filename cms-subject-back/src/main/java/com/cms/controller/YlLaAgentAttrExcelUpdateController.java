package com.cms.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.cms.common.ModelExcelListener;
import com.cms.dao.YlLaAgentAttrExcelUpdateDao;
import com.cms.dao.YlLaAgentDao;
import com.cms.entity.YlLaAgentEntity;
import com.cms.pojo.TablePropertiesPojo;
import com.cms.pojo.YlLaAgentAttrExcelUpdatePojo;
import com.cms.service.YlAgentInfoService;
import com.cms.service.impl.YlAgentInfoServiceImpl;
import com.cms.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/excel")
@Api("养老渠道-人员管理-人员Excel导入")
public class YlLaAgentAttrExcelUpdateController {
    @Autowired
    YlLaAgentAttrExcelUpdateDao ylLaAgentAttrExcelUpdateDao;

    @Autowired
    ModelExcelListener modelExcelListener;
    @PostMapping("/dowload")
    @ApiOperation("模板下载模块未完待续")
    public void exceldowload(HttpServletResponse httpServletResponse) throws IOException {
        //获取所有的数据库注释信息然后插入Excel表中
        List<TablePropertiesPojo> agentTableProperties = ylLaAgentAttrExcelUpdateDao.getAgentTableProperties();
        List<TablePropertiesPojo> agentAttrTableProperties = ylLaAgentAttrExcelUpdateDao.getAgentAttrTableProperties();
        //接下来涉及到 操作Excle的时刻了 涉及到的表有三个表 都是需要我们进行设置的
        //获取每一行的属性配置
        //进行模板的 导出 要设置Excle表格的属性 已经涉及到所有的字段的信息
        httpServletResponse.setHeader("Content-Disposition", "attachment;filename=\""+"Updatefile.xlsx"+"\"");
        httpServletResponse.setContentType("\"application/x-excel");
        httpServletResponse.setCharacterEncoding("utf-8");
        ServletOutputStream outputStream = httpServletResponse.getOutputStream();
        ExcelWriter excelWriter=new ExcelWriter(outputStream,ExcelTypeEnum.XLS,true);
        Sheet sheet=new Sheet(1,0,YlLaAgentAttrExcelUpdatePojo.class);
        sheet.setSheetName("我的第一个Sheet");
        excelWriter.write(new ArrayList(),sheet);
        excelWriter.finish();
        outputStream.flush();

    }
    @PostMapping("/update")
    @ApiOperation("人员导入模块")
    public R readExcel(@RequestParam("file") MultipartFile file){
        List<YlLaAgentAttrExcelUpdatePojo> list = new ArrayList<>();//这个Pojo 从表中中获取的数据
        try {
            list = EasyExcel.read(file.getInputStream(),YlLaAgentAttrExcelUpdatePojo.class,modelExcelListener).sheet().doReadSync();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return R.ok().put("list",list);
    }

}

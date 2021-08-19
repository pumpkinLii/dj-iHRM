package com.cms.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.ExcelBuilderImpl;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
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

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
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
        httpServletResponse.setHeader("Content-Disposition", "attachment;filename=\""+"Updatefile.xlsx"+"\"");
        httpServletResponse.setContentType("\"application/x-excel");
        ServletOutputStream outputStream = httpServletResponse.getOutputStream();
        //标头的设置
        WriteCellStyle headwriteCellStyle=new WriteCellStyle();
        headwriteCellStyle.setFillForegroundColor(IndexedColors.CORAL.getIndex());
        WriteFont writeFont=new WriteFont();
        writeFont.setFontHeightInPoints((short)11);
        headwriteCellStyle.setWriteFont(writeFont);



        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        WriteFont contentWriteFont = new WriteFont();
        contentWriteFont.setFontHeightInPoints((short) 15);
        contentWriteCellStyle.setWriteFont(contentWriteFont);
        // 这个策略是 头是头的样式 内容是内容的样式 其他的策略可以自己实现
        contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);//设置字体居中
        HorizontalCellStyleStrategy horizontalCellStyleStrategy = new HorizontalCellStyleStrategy(headwriteCellStyle, contentWriteCellStyle);
        YlLaAgentAttrExcelUpdatePojo ylLaAgentAttrExcelUpdatePojo=new YlLaAgentAttrExcelUpdatePojo();
        ylLaAgentAttrExcelUpdatePojo.setComCode2("hahaa");
        ArrayList arrayList=new ArrayList();
        arrayList.add(ylLaAgentAttrExcelUpdatePojo);
        EasyExcel.write(outputStream,YlLaAgentAttrExcelUpdatePojo.class).registerWriteHandler(horizontalCellStyleStrategy).sheet("第一个Sheet").doWrite(arrayList);
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

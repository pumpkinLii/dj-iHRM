package com.cms.controller;

import com.cms.pojo.IdCodePojo;
import com.cms.service.NIdCodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.logging.Logger;

@RestController
@CrossOrigin
@RequestMapping({"/cert"})
@Api("养老人管模块课题")
@Slf4j
public class CertExcellBoardController {


    @Autowired
    private NIdCodeService nIdCodeService;

    @Autowired
    private IdCodePojo idCodePojo;

    @ApiOperation("资格证导入模板下载")
    @PostMapping({"/board"})

    public void getData(HttpServletResponse response) throws FileNotFoundException {
        try {
            log.info("项目启动");
            XSSFWorkbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("资格证导入模板");
            sheet.createFreezePane(0,1,0,1);
            sheet.autoSizeColumn(0, true);            //默认列宽
            sheet.setDefaultColumnWidth(20);            //默认列宽
            sheet.setDefaultRowHeightInPoints(23);      //默认行高

            log.debug("创建工作区样式，获取到sheet为：'{}' ",sheet);

            Row row0 = sheet.createRow(0);
            Cell cell0 = row0.createCell(0);
            cell0.setCellValue("导入资格证模版");
            XSSFCellStyle titleStyle = genTitleStyle(workbook);//创建标题样式
            cell0.setCellStyle(titleStyle);//设置标题样式
            CellRangeAddress region=new CellRangeAddress(0, 0, 0, 7);//            firstRow 区域中第一个单元格的行号,lastRow 区域中最后一个单元格的行号,firstCol 区域中第一个单元格的列号,lastCol 区域中最后一个单元格的列号
            sheet.addMergedRegion(region);

            log.debug("创建标题样式，获取到sheet为：'{}' ",sheet);
            log.debug("创建标题样式，获取到cell为：'{}' ",cell0);

            String[] str = new String[]{"人员工号*","资格证书名称*","资格证书号*","发放日期*","补发日期","有效起期*","有效止期*","批准单位"};
            XSSFCellStyle textStyle = genTextStyle(workbook);//创建标题样式
            Row row1 = sheet.createRow(1);
            for(int i = 0; i <8; ++i) {
                Cell cell1 = row1.createCell(i);
                cell1.setCellValue(str[i]);
                cell1.setCellStyle(textStyle);
            }

            log.debug("创建表头样式，获取到sheet为：'{}'",sheet);

            for(int i=0;i<40;i++)
            {
                if(i==1)
                {
                    CodeType(i,"certificatename",sheet);
                }
            }

            log.debug("创建数据样式，获取到sheet为：'{}'",sheet);

            ServletOutputStream outputStream=response.getOutputStream();
            response.setHeader("content-disposition","attachment;fileName="+ URLEncoder.encode("导入资格证模版.xlsx","UTF-8"));
            workbook.write(outputStream);
            log.debug("获取输出流为：'{}'",outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (IOException var11) {
            var11.printStackTrace();
        }
    }
    //生成标题样式
    public static XSSFCellStyle genTitleStyle(XSSFWorkbook workbook){

        XSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setWrapText(true);

        //标题居中，没有边框，所以这里没有设置边框，设置标题文字样式
        XSSFFont titleFont = workbook.createFont();
        titleFont.setBold(true);//加粗
        titleFont.setFontHeight((short)13);//文字尺寸
        titleFont.setFontHeightInPoints((short)13);
        style.setFont(titleFont);

        style.setFillForegroundColor(IndexedColors.CORAL.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        return style;
    }
    public static XSSFCellStyle genTextStyle(XSSFWorkbook workbook){

        XSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setWrapText(true);

        //标题居中，没有边框，所以这里没有设置边框，设置标题文字样式
        XSSFFont textFont = workbook.createFont();
        textFont.setBold(true);//加粗
        textFont.setFontHeight((short)10);//文字尺寸
        textFont.setFontHeightInPoints((short)10);
        style.setFont(textFont);

        style.setBorderBottom(BorderStyle.THIN);//设置文本边框
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);

        style.setFillForegroundColor(IndexedColors.CORAL.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        return style;
    }
    public static void getSelect(Sheet sheet, int i, List<String> list)
    {
        DataValidationHelper helper = sheet.getDataValidationHelper();
        CellRangeAddressList addressList = new CellRangeAddressList(2, 500, i, i);
        String[] pos = list.toArray(new String[list.size()]);
        DataValidationConstraint constraint = helper.createExplicitListConstraint(pos);
        DataValidation dataValidation = helper.createValidation(constraint, addressList);
        sheet.addValidationData(dataValidation);
    }

    public void CodeType(int i,String str,Sheet sheet)
    {
        idCodePojo.setCodeType(str);
        List<String> list = this.nIdCodeService.idcode(idCodePojo);
        getSelect(sheet,i,list);
    }
}
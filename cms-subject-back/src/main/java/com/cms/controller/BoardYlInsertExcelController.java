package com.cms.controller;

import com.cms.entity.YlLaAgentAttrEntity;
import com.cms.pojo.IdCheckPojo;
import com.cms.pojo.IdCodePojo;
import com.cms.service.NIdCodeService;
import com.cms.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin
@RequestMapping({"/Board"})
@Api("养老人管模块课题")
public class BoardYlInsertExcelController {

    @Autowired
    private NIdCodeService nIdCodeService;

    @Autowired
    private IdCodePojo idCodePojo;

    @ApiOperation("测试接口")
    @PostMapping({"/YlInsertExcel"})

    public R getData(HttpServletResponse response) throws FileNotFoundException {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("员工批量导入模板");
            sheet.createFreezePane(0,1,0,1);
            sheet.autoSizeColumn(0, true);            //默认列宽
            sheet.setDefaultColumnWidth(20);            //默认列宽
            sheet.setDefaultRowHeightInPoints(23);      //默认行高

            Row row0 = sheet.createRow(0);
            Cell cell0 = row0.createCell(0);
            cell0.setCellValue("员工批量导入");
            XSSFCellStyle titleStyle = genTitleStyle(workbook);//创建标题样式
            cell0.setCellStyle(titleStyle);//设置标题样式
            CellRangeAddress region=new CellRangeAddress(0, 0, 0, 39);//            firstRow 区域中第一个单元格的行号,lastRow 区域中最后一个单元格的行号,firstCol 区域中第一个单元格的列号,lastCol 区域中最后一个单元格的列号
            sheet.addMergedRegion(region);


            String[] str = new String[]{"四级管理机构代码*", "姓名*", "性别*", "出生日期*", "证件类型*", "证件号码*", "入司日期*", "户口类型*", "户口所在省*", "最高学历*", "第一学历", "最高学位*", "毕业院校*", "专业*", "民族*", "籍贯*", "最近一份工作行业类型*", "最近一份职业*", "最近一家工作单位*", "最近一份工作职务*", "从业年限*", "家庭地址*", "邮编", "住宅电话","手机*", "E-mail*", "政治面貌*", "账户银行总行*", "银行账号*", "银行卡开户行联行号*", "开户行省份*", "开户行所在市*", "岗位*", "人员职级*", "合同类型*", "劳动合同起期*", "劳动合同止期*", "专业资格证书号", "团队架构代码*", "SAP工号"};
            XSSFCellStyle textStyle = genTextStyle(workbook);//创建标题样式
            Row row1 = sheet.createRow(1);
            for(int i = 0; i <40; ++i) {
                Cell cell1 = row1.createCell(i);
                cell1.setCellValue(str[i]);
                cell1.setCellStyle(textStyle);
            }

            for(int i=0;i<40;i++)
            {
                if(i==2)//性别
                {
                    CodeType(i,"sex",sheet);
                }
                else if(i==4)
                {
                    CodeType(i,"idtype",sheet);
                }
                else if(i==7)
                {
                    CodeType(i,"rgttype",sheet);
                }
//                else if(i==8)
//                {
//                    CodeType(i,"nativeplace",sheet);
//                }
                else if(i==9)
                {
                    CodeType(i,"highestdegree",sheet);
                }
                else if(i==10)
                {
                    CodeType(i,"highestdegree",sheet);
                }
                else if(i==11)
                {
                    CodeType(i,"degree",sheet);
                }
//                else if(i==14)
//                {
//                    CodeType(i,"nationality",sheet);
//                }
//                else if(i==15)
//                {
//                    CodeType(i,"nativeplace",sheet);
//                }
                else if(i==16)
                {
                    CodeType(i,"industrytype",sheet);
                }
                else if(i==26)
                {
                    CodeType(i,"polityvisage",sheet);
                }
//                else if(i==27)
//                {
//                    CodeType(i,"bankcode",sheet);
//                }
//                else if(i==30)
//                {
//                    CodeType(i,"nativeplace",sheet);
//                }
//                else if(i==31)//*********************************
//                {
//                    CodeType(i,"nativeplace",sheet);
//                }
//                else if(i==32)
//                {
//                    CodeType(i,"ylpost",sheet);
//                }
//                else if(i==33)//*************************************
//                {
//                    CodeType(i,"ylpost",sheet);
//                }
//                else if(i==34)
//                {
//                    CodeType(i,"contracttype",sheet);
//                }
            }

            //FileOutputStream fos = new FileOutputStream(new File("C:\\Users\\LENOVO\\Desktop\\员工批量导入.xlsx"));\
            ServletOutputStream outputStream=response.getOutputStream();
            response.setHeader("content-disposition","attachment;fileName="+ URLEncoder.encode("员工批量导入模板.xlsx","UTF-8"));
//          response.setContentType("application/vnd.ms-excel");
//          response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
//            workbook.write(fos);
//            fos.flush(); 吧
//            fos.close();
        } catch (IOException var11) {
            var11.printStackTrace();
        }
        return R.ok().put("0","模板下载成功");
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
    public static void getSelect(Sheet sheet,int i,List<String> list)
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
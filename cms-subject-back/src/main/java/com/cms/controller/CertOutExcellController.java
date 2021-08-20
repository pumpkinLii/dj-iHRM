package com.cms.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import cn.afterturn.easypoi.word.entity.params.ExcelListEntity;
import com.cms.pojo.*;
import com.cms.service.CertOutExcellService;
import com.cms.service.NIdCheckService;
import com.cms.service.impl.RCertificateImpl;
import com.cms.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin
@RequestMapping({"/cert"})
@Api("养老人管模块课题")
public class CertOutExcellController {
    @Autowired
    RCertificateImpl rCertificateService;

    @Autowired
    CertOutExcellService certOutExcellService;
    @ApiOperation("测试接口")
    @PostMapping({"/ExcellOut"})
    //@RequestBody CertificateConditionPojo getCertificateConditionPojo,
    private R CertExcellOut( HttpServletResponse response) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("资格证批量导出");
        sheet.autoSizeColumn(0, true);          //默认列宽
        sheet.setDefaultColumnWidth(23);            //默认列宽
        sheet.setDefaultRowHeightInPoints(23);      //默认行高


        Row row0 = sheet.createRow(0);
        Cell cell0 = row0.createCell(0);
        cell0.setCellValue("资格证批量导出");
        XSSFCellStyle titleStyle = genTitleStyle(workbook);//创建标题样式
        cell0.setCellStyle(titleStyle);//设置标题样式
        CellRangeAddress region=new CellRangeAddress(0, 0, 0, 7);//           firstRow 区域中第一个单元格的行号,lastRow 区域中最后一个单元格的行号,firstCol 区域中第一个单元格的列号,lastCol 区域中最后一个单元格的列号
        sheet.addMergedRegion(region);


        String[] str=new String[]{"人员工号*","资格证书名称*","资格证书号*","发放日期*","补发日期","有效起期*","有效止期*","批准单位"};
        XSSFCellStyle textStyle = genTextStyle(workbook);//创建标题样式
        Row row1 = sheet.createRow(1);
        for(int i = 0; i <8; ++i) {
            Cell cell1 = row1.createCell(i);
            cell1.setCellValue(str[i]);
            cell1.setCellStyle(textStyle);
        }

        ArrayList qlist=new ArrayList();
//        List<RetrieveCertificatePojo> list = rCertificateService.getCertificate(getCertificateConditionPojo);
//        if(list.size()==0)
//        {
//            return R.error(500, "查询不到值");
//        }
//        else
//        {
//            for (int i = 0; i <list.size(); i++) {
//                qlist.add(list.get(i).getCertificateNo());
//            }
//        }
//        qlist.add("123123123125WQE");
//        qlist.add("11");

        List<List> biglist = this.certOutExcellService.certselect(qlist);
        XSSFCellStyle DataStyle = genDataStyle(workbook);//创建标题样式
        for(int i = 2; i < biglist.size() +2 ; ++i) {
            List<CertOutPojo> smalllist= biglist.get(i-2);

            String[] pos=new String[]{};
            for (int i1 = 0; i1 < smalllist.size(); i1++) {
                pos=smalllist.get(i1).toString().split(",");
            }

            Row row = sheet.createRow(i);

            for(int j=0;j<pos.length;j++)
            {
                if(pos[j].equals("null")||pos[j]==null)
                {
                    Cell cell = row.createCell(j);
                    cell.setCellValue("");
                    cell.setCellStyle(DataStyle);
                }
                else {
                    Cell cell = row.createCell(j);
                    cell.setCellValue(pos[j]);
                    cell.setCellStyle(DataStyle);
                }
            }
        }


//            FileOutputStream fos = new FileOutputStream(new File("C:\\Users\\LENOVO\\Desktop\\员工批量导出.xlsx"));
//            workbook.write(fos);
//            fos.flush();
//            fos.close();
        ServletOutputStream outputStream=response.getOutputStream();
        response.setHeader("content-disposition","attachment;fileName="+ URLEncoder.encode("员工批量导出.xlsx","UTF-8"));
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
        return R.ok().put("0","导出成功");
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
    //生成数据类型样式
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

    public static XSSFCellStyle genDataStyle(XSSFWorkbook workbook){

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

        return style;
    }
}

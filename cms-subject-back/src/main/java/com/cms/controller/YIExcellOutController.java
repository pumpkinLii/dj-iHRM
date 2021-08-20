package com.cms.controller;

import com.cms.pojo.QueryStaffPojo;
import com.cms.pojo.NQueryStaffReturn;
import com.cms.pojo.QueryStaffReturn;
import com.cms.pojo.YlSelectExcellPojo;
import com.cms.service.NQueryStaffService;
import com.cms.service.QueryStaffService;
import com.cms.service.YlSelectExcellService;
import com.cms.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
@RequestMapping({"/test"})
@Api("养老人管模块课题")
class YlLaAgentAttrExcelOutController {

    @Autowired
    private YlSelectExcellService ylSelectExcellService;

    @Autowired
    private QueryStaffService queryStaffService;


    @ApiOperation("员工查询批量导出接口")
    @PostMapping({"/ylLaAgentAttrExcelOut"})

    private R getData(@RequestBody QueryStaffPojo queryStaffPojo,HttpServletResponse response) throws FileNotFoundException {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("员工查询批量导出");
            sheet.autoSizeColumn(0, true);          //默认列宽
            sheet.setDefaultColumnWidth(23);            //默认列宽
            sheet.setDefaultRowHeightInPoints(23);      //默认行高


            Row row0 = sheet.createRow(0);
            Cell cell0 = row0.createCell(0);
            cell0.setCellValue("员工查询批量导出");
            XSSFCellStyle titleStyle = genTitleStyle(workbook);//创建标题样式
            cell0.setCellStyle(titleStyle);//设置标题样式
            CellRangeAddress region=new CellRangeAddress(0, 0, 0, 50);//           firstRow 区域中第一个单元格的行号,lastRow 区域中最后一个单元格的行号,firstCol 区域中第一个单元格的列号,lastCol 区域中最后一个单元格的列号
            sheet.addMergedRegion(region);


            String[] str=new String[]{"二级管理机构代码","二级管理机构名称","三级管理机构代码","三级管理机构名称","四级管理机构代码","四级管理机构名称","工号","SAP工号","姓名","人员状态","岗位","人员职级","性别","出生日期","证件类型","证件号码","入司日期","离司日期","户口类型","户口所在省","最高学历","第一学历","最高学位","毕业院校","专业","民族","籍贯","最近一份工作行业类型","最近一份职业","最近一家工作单位","最近一份工作职务","从业年限","家庭地址","邮政编码","住宅电话","手机","E-mail","政治面貌","账户银行总行","银行账号","银行卡开户行联行号","开户行省份","开户行市辖区","合同类型","劳动合同起期","劳动合同止期","团队架构代码","团队架构名称","团队主管工号","团队主管姓名","团队主管手机号"};
            XSSFCellStyle textStyle = genTextStyle(workbook);//创建标题样式
            Row row1 = sheet.createRow(1);
            for(int i = 0; i <51; ++i) {
                Cell cell1 = row1.createCell(i);
                cell1.setCellValue(str[i]);
                cell1.setCellStyle(textStyle);
            }

            ArrayList qlist=new ArrayList();
            List<QueryStaffReturn> list = queryStaffService.queryStaff(queryStaffPojo);
            System.out.println(list.size());
            if(list.size()==0)
            {
                return R.error(500, "查询不到值");
            }
            else
            {
                for (int i = 0; i <list.size(); i++) {
                    qlist.add(list.get(i).getAgentCode());
                }
            }

            List<List> biglist = this.ylSelectExcellService.ylselect(qlist);
            XSSFCellStyle DataStyle = genDataStyle(workbook);//创建标题样式
            for(int i = 2; i < biglist.size() +2 ; ++i) {
                List<YlSelectExcellPojo> smalllist= biglist.get(i-2);

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
        } catch (IOException | ParseException var11) {
            var11.printStackTrace();
        }
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
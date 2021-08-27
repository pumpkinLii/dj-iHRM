package com.cms.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.cms.common.ModelExcelListener;
import com.cms.common.MyDoUtils;
import com.cms.common.nowHandler;
import com.cms.dao.YlLaAgentAttrExcelUpdateDao;
import com.cms.pojo.*;
import com.cms.service.*;
import com.cms.service.impl.RCertificateImpl;
import com.cms.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
@RestController
@CrossOrigin
@RequestMapping({"/login/Excel"})
@Api("Excel模块")
@Slf4j
public class ExcelController {

    //王佳智
    @ApiOperation("下载新增接口")
    @PostMapping("/load")
    public R download(HttpServletResponse httpServletResponse, MultipartFile file) throws IOException {
        OutputStream outputStream=new FileOutputStream("D:\\附件目录\\"+file.getOriginalFilename());
        InputStream inputStream = file.getInputStream();
        byte[] bytes=new byte[1024];
        while ((inputStream.read(bytes))>0){
            outputStream.write(bytes);
        }
        outputStream.flush();
        outputStream.close();
        return R.ok();
    }

    //王欣艺
    @Autowired
    private NIdCodeService nIdCodeService;

    @Autowired
    private IdCodePojo idCodePojo;

    @ApiOperation("员工批量导入模板下载")
    @PostMapping({"/YlInsertboard"})

    public R getData1(HttpServletResponse response) throws FileNotFoundException {
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

//          response.setContentType("application/vnd.ms-excel");
//          response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            ServletOutputStream outputStream=response.getOutputStream();
            response.setHeader("content-disposition","attachment;fileName="+ URLEncoder.encode("员工批量导入模板.xlsx","UTF-8"));
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

    //王欣艺
    @ApiOperation("资格证导入模板下载")
    @PostMapping({"/board"})

    public void getData2(HttpServletResponse response) throws FileNotFoundException {
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

    //王欣艺
    @Autowired
    CertInsertExcellService certInsertExcellService;

    @ApiOperation("资格证批量导入")
    @PostMapping({"/ExcelInsert"})
    public R certInsert(@RequestParam("file") MultipartFile file) throws IOException, ParseException {
        ArrayList<HashMap<String,String>> list=new ArrayList<HashMap<String,String>>();
        list=this.certInsertExcellService.check(file);
        HashMap<String,String> mapp=new HashMap<String,String>();
        if(list.size()==1)
        {
            HashMap<String,String> map= new HashMap<String, String>();
            map = list.get(0);
            if(map.get("msg").equals("批量导入失败！Excel文件的内容不能为空！"))
            {
//                return R.error(500,"批量导入失败！Excel文件的内容不能为空！");
                return R.ok().put("code",501).put("msg","参数错误").put("list",list);
            }
            else if(map.get("msg").equals("导入成功！"))
            {
                return R.ok().put("msg","导入成功！");
            }
            else{
                return R.ok().put("code",501).put("msg","参数错误").put("list",list);
            }
        }
        else {
            return R.ok().put("code",501).put("msg","参数错误").put("list",list);
        }
    }

    @Autowired
    RCertificateImpl rCertificateService;

    @Autowired
    CertOutExcellService certOutExcellService;
    @ApiOperation("资格证批量导出")
    @PostMapping({"/ExcelOut"})
    //@RequestBody CertificateConditionPojo getCertificateConditionPojo,
    private R CertExcellOut(@RequestBody CertificateConditionPojo getCertificateConditionPojo, HttpServletResponse response) throws IOException {
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
        List<RetrieveCertificatePojo> list = rCertificateService.getCertificate(getCertificateConditionPojo);
        if(list==null)
        {
            return R.error(500, "查询不到值");
        }
        else
        {
            for (int i = 0; i <list.size(); i++) {
                qlist.add(list.get(i).getCertificateNo());
            }
        }
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
//                    if(j==1)
//                    {
//                        XSSFCellStyle ActionDataStyle = genActionDataStyle(workbook,pos[j].length());
//                        cell.setCellStyle(ActionDataStyle);
//                    }
//                    else{
                    cell.setCellStyle(DataStyle);
//                    }
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

    //王欣艺
    @Autowired
    private YlExcellInsertService ylExcellService;
    @Autowired
    private ExcelLaAgentService laAgentService;

    @ApiOperation("测试接口")
    @PostMapping({"/ylLaAgentAttrIn"})
    public R ylLaAgentAttrExcelIn(@RequestParam("file") MultipartFile file) throws IOException, ParseException {
        ArrayList<HashMap<String,String>> list=new ArrayList<HashMap<String,String>>();
        list=this.ylExcellService.check(file);
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

    //王佳智
    @Autowired
    YlLaAgentAttrExcelUpdateDao ylLaAgentAttrExcelUpdateDao;

    @Autowired
    ModelExcelListener modelExcelListener;
    @PostMapping("/dowload")
    @ApiOperation("模板下载模块未完待续")
    public void exceldowload(HttpServletResponse httpServletResponse) throws IOException {
        //获取所有的数据库注释信息然后插入Excel表中
        List<Integer> colunmindex=new ArrayList();
        for (int i = 0; i < 51; i++) {
            colunmindex.add(i);
        }
        Short colorindex=IndexedColors.AUTOMATIC.getIndex();
        httpServletResponse.setHeader("Content-Disposition", "attachment;filename=\""+"Updatefile.xlsx"+"\"");
        httpServletResponse.setContentType("\"application/x-excel");
        ServletOutputStream outputStream = httpServletResponse.getOutputStream();
        HashMap annotationMAP=new HashMap();
        HashMap dropMap=new HashMap();
        MyDoUtils.writeExcelWithModel(outputStream,new ArrayList(),YlLaAgentAttrExcelUpdatePojo.class,"new",new nowHandler(colunmindex,colorindex,annotationMAP,dropMap));
        //标头的设置
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

    //王欣艺
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
}

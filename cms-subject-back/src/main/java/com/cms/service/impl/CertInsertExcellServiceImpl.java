package com.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cms.dao.YlLaAgentAttrDao;
import com.cms.dao.YlLaAgentCertificateDao;
import com.cms.entity.YlLaAgentAttrEntity;
import com.cms.entity.YlLaAgentCertificateEntity;
import com.cms.service.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class CertInsertExcellServiceImpl extends ServiceImpl<YlLaAgentCertificateDao, YlLaAgentCertificateEntity> implements CertInsertExcellService {
    @Autowired
    YlLaAgentAttrDao ylLaAgentAttrDao;

    @Override
    public ArrayList<HashMap<String, String>> check(MultipartFile file) throws IOException, ParseException {
        //基础的配置
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        ArrayList<HashMap<String, String>> lmm = new  ArrayList<HashMap<String, String>>();
        boolean successfg = false;
        String m="(^\\d{4}-\\d{2}-\\d{2}$)";

        //POI操作
        ArrayList<Map<String, Map<Integer, Integer>>> list = new ArrayList<>();
        InputStream is = file.getInputStream();
        XSSFWorkbook workbook = new XSSFWorkbook(is);
//        Sheet sheet = workbook.getSheet("资格证导入模板");
        Sheet sheet = workbook.getSheetAt(0);
        //逻辑判断
        if (sheet.getFirstRowNum() == sheet.getLastRowNum()) {
            HashMap<String, String> map = new HashMap<String, String>();
            //map.put("批量导入失败！Excel文件的内容不能为空！","第0行,第0列");
            map.put("address","第0行,第0列");
            map.put("msg","批量导入失败！Excel文件的内容不能为空！");
            lmm.add(map);
            return lmm;
        } else {
            if(sheet.getPhysicalNumberOfRows()==2)//Excel文件的内容为空
            {
                HashMap<String, String> map = new HashMap<String, String>();
//                map.put("批量导入失败！Excel文件的内容不能为空！","第0行,第0列");
                map.put("address","第0行,第0列");
                map.put("msg","批量导入失败！Excel文件的内容不能为空！");
                lmm.add(map);
                return lmm;
            }
            for (int i = 2; i < sheet.getPhysicalNumberOfRows(); ++i) {
                List<String> value = new ArrayList<>();
                Row row = sheet.getRow(i);
                for (int j = 0; j < 8; ++j) {
                    String cellData=new String();
                    if(row.getCell(j)==null)
                    {
                        cellData="null";
                    }
                    else
                    {

                        Cell cell = row.getCell(j);
                        cellData = getCellValue(cell);
                        boolean dflag=false;
                        for(int k=0;k<cellData.length();k++)
                        {
                            if(cellData.charAt(k)=='_')
                            {
                                dflag=true;
                            }
                        }
                        if(dflag==true)
                        {
                            String[] parts = cellData.split("_");
                            cellData = parts[0];
                        }
                    }
                    value.add(cellData);
                }
                boolean codef=false;
                if (value.get(0).equals("null")) {
                    codef=false;
                    successfg=true;
                    lmm.add(errorCause(i,0,"错误！人员工号不能为空"));
                }

                if (value.get(1).equals("null")) {
                    successfg=true;
                    lmm.add(errorCause(i,1,"错误！资格证书名称不能为空"));
                }
                boolean no=false;
                if (value.get(2).equals("null")) {
                    successfg=true;
                    no=true;
                    lmm.add(errorCause(i,2,"错误！资格证书号不能为空"));
                }

                boolean releaseDate=value.get(3).matches(m);
                if (value.get(3).equals("null")) {
                    successfg=true;
                    lmm.add(errorCause(i,3,"错误！发放日期不能为空"));
                }
                else if(releaseDate==false){
                    successfg=true;
                    lmm.add(errorCause(i,3,"错误！发放日期格式不符合要求"));
                }
                int timefg=0;
                boolean startEffectiveDate=value.get(5).matches(m);
                if (value.get(5).equals("null")) {
                    timefg++;
                    successfg=true;
                    lmm.add(errorCause(i,5,"错误！有效起期不能为空"));
                }
                else if(startEffectiveDate==false){
                    timefg++;
                    successfg=true;
                    lmm.add(errorCause(i,5,"错误！有效起期格式不符合要求"));
                }

                boolean endEffectiveDate=value.get(6).matches(m);
                if (value.get(6).equals("null")) {
                    timefg++;
                    successfg=true;
                    lmm.add(errorCause(i,6,"错误！有效止期不能为空"));
                }
                else if(endEffectiveDate==false){
                    timefg++;
                    successfg=true;
                    lmm.add(errorCause(i,6,"错误！有效止期格式不符合要求"));
                }
                if((timefg!=2)&&(timefg!=1))
                {
                    Date startEffectiveDate1 = df.parse(value.get(5));
                    Date endEffectiveDate2 = df.parse(value.get(6));
                    long start_long = startEffectiveDate1.getTime() / 3600000;
                    long end_long = endEffectiveDate2.getTime() / 3600000;
                    if (start_long > end_long) {
                        successfg=true;
                        lmm.add(errorCause(i,36,"错误！有效止期不能早于有效起期"));
                    }
                }

                if(no==false)
                {
                    YlLaAgentCertificateEntity ylLaAgentCertificateEntity = new YlLaAgentCertificateEntity();
                    QueryWrapper<YlLaAgentCertificateEntity> qw = new QueryWrapper<>();
                    qw.eq("certificate_no", value.get(2));
                    List<YlLaAgentCertificateEntity> list1= this.baseMapper.selectList(qw);
                    if(list1.size()>0)
                    {
                        successfg=true;
                        lmm.add(errorCause(i,2,"错误！资格证书号已经存在"));
                    }
                }
                if(codef==false)
                {
                    YlLaAgentAttrEntity ylLaAgentAttrEntity = new YlLaAgentAttrEntity();
                    QueryWrapper<YlLaAgentAttrEntity> qw = new QueryWrapper<>();
                    qw.eq("agent_code", value.get(0));
                    List<YlLaAgentAttrEntity> list2= ylLaAgentAttrDao.selectList(qw);
                    if(list2.size()==0)
                    {
                        successfg=true;
                        lmm.add(errorCause(i,0,"错误! 人员工号不存在"));
                    }
                }

            }
        }
        if(successfg==true)
        {
            return lmm;
        }
        else//导入数据库
        {
            for (int i = 2; i < sheet.getPhysicalNumberOfRows(); ++i) {
                List<String> value = new ArrayList<>();
                Row row = sheet.getRow(i);
                String initializeEdorno=this.initializeEdorno();

                for (int j = 0; j < 8; ++j) {
                    String cellData=new String();
                    if(row.getCell(j)==null)
                    {
                        cellData="null";
                    }
                    else
                    {

                        Cell cell = row.getCell(j);
                        cellData = getCellValue(cell);
                        boolean dflag=false;
                        for(int k=0;k<cellData.length();k++)
                        {
                            if(cellData.charAt(k)=='_')
                            {
                                dflag=true;
                            }
                        }
                        if(dflag==true)
                        {
                            String[] parts = cellData.split("_");
                            cellData = parts[0];
                        }
                    }
                    value.add(cellData);
                }

                YlLaAgentCertificateEntity ylLaAgentCertificateEntity = new YlLaAgentCertificateEntity();
                ylLaAgentCertificateEntity.setEdorNo(initializeEdorno);
                ylLaAgentCertificateEntity.setAgentCode(value.get(0));
                ylLaAgentCertificateEntity.setCertificateType("1");
                ylLaAgentCertificateEntity.setCertificateName(value.get(1));
                ylLaAgentCertificateEntity.setCertificateNo(value.get(2));

                Date releaseDate=df.parse(value.get(3));
                ylLaAgentCertificateEntity.setReleaseDate(releaseDate);
                if(!value.get(4).equals("null"))
                {
                    Date reissueDate=df.parse(value.get(4));
                    ylLaAgentCertificateEntity.setReissueDate(reissueDate);
                }
                Date startEffectiveDate=df.parse(value.get(5));
                ylLaAgentCertificateEntity.setStartEffectiveDate(startEffectiveDate);

                Date endEffectiveDate=df.parse(value.get(6));
                ylLaAgentCertificateEntity.setEndEffectiveDate(endEffectiveDate);

                if(!value.get(7).equals("null"))
                {
                    ylLaAgentCertificateEntity.setApprover(value.get(7));
                }
                ylLaAgentCertificateEntity.setOperator("wxy");
                Date date = new Date();
                SimpleDateFormat newtime = new SimpleDateFormat("HH-mm-ss");//设置日期格式
                String time =newtime.format(date);//获取String类型的时间
                ylLaAgentCertificateEntity.setMakeDate(date);
                ylLaAgentCertificateEntity.setMakeTime(time);
                ylLaAgentCertificateEntity.setModifyDate(date);
                ylLaAgentCertificateEntity.setModifyTime(time);

                //存入数据库
                int result = this.baseMapper.insert(ylLaAgentCertificateEntity);
                if (result<=0) {
                    lmm.add(errorCause(0,0,"数据库插入出现问题"));
                }
            }
            HashMap<String, String> map = new HashMap<String, String>();
//            map.put("导入成功！", "第0行,第0列");
            map.put("address","第0行,第0列");
            map.put("msg","导入成功！");
            lmm.add(map);
        }
        workbook.close();
        return lmm;
    }
    public static String getCellValue(Cell cell) {
        String cellValue = "";
        // 以下是判断数据的类型
        switch (cell.getCellTypeEnum()) {
            case NUMERIC: // 数字
                if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    cellValue = sdf.format(org.apache.poi.ss.usermodel.DateUtil.getJavaDate(cell.getNumericCellValue())).toString();
                } else {
                    DataFormatter dataFormatter = new DataFormatter();
                    cellValue = dataFormatter.formatCellValue(cell);
                }
                break;
            case STRING: // 字符串
                cellValue = cell.getStringCellValue();
                break;
            case BOOLEAN: // Boolean
                cellValue = cell.getBooleanCellValue() + "";
                break;
            case FORMULA: // 公式
                cellValue = cell.getCellFormula() + "";
                break;
            case BLANK: // 空值
                cellValue = "null";
                break;
            case ERROR: // 故障
                cellValue = "非法字符";
                break;
            default:
                cellValue = "未知类型";
                break;
        }
        return cellValue;
    }
    public static HashMap<String, String>  errorCause(int row,int col,String error) {
        col++;
        row++;
        String strcol="第"+col+"列";
        String strrow="第"+row+"行";
        String str=strrow+","+strcol;
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("address",str);
        map.put("msg",error);
//        map.put(error, str);
        return map;
    }

    public String initializeEdorno() {
        List<YlLaAgentCertificateEntity> pbListBlacks = this.baseMapper.selectList(null);
        String str = "";
        if (pbListBlacks.size() != 0) {
            YlLaAgentCertificateEntity ylLaAgentCertificateEntity = pbListBlacks.get(pbListBlacks.size() - 1);
            str = ylLaAgentCertificateEntity.getEdorNo();
            int result = Integer.parseInt(str) + 1;
            return String.valueOf(result);
        }
        String newStr = this.getEdorNo(str);
        return newStr;
    }

    public String getEdorNo(String str) {
        String newStr = null;
        newStr = "10000001";
        return newStr;
    }
}
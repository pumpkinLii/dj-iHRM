package com.cms.service.impl;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cms.common.IdCheck;
import com.cms.dao.LaAgentAttrDao;

import com.cms.entity.YlLaAgentAttrEntity;
import com.cms.entity.YlUserInfoEntity;
import com.cms.pojo.LaAgentPojo;
import com.cms.service.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.cms.service.impl.LaAgentServiceAttrImpl.getYlNo;

@Service
public class YlExcellServiceImpl extends ServiceImpl<LaAgentAttrDao, YlLaAgentAttrEntity> implements YlExcellInsertService {

    @Autowired
    public IdCheck idCheck;

    @Autowired
    public ExcelLaAgentService laAgentService;

    @Autowired
    public ExcelLaAgentServiceAttr laAgentServiceAttr;

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
       // Sheet sheet = workbook.getSheet("人员信息批量导入模板");
        Sheet sheet = workbook.getSheetAt(0);
        //逻辑判断
        if (sheet.getFirstRowNum() == sheet.getLastRowNum()) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("批量导入失败！Excel文件的内容不能为空！","第0行,第0列");
            lmm.add(map);
            return lmm;
        } else {
            if(sheet.getPhysicalNumberOfRows()==2)//Excel文件的内容为空
            {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("批量导入失败！Excel文件的内容不能为空！","第0行,第0列");
                lmm.add(map);
                return lmm;
            }
            for (int i = 2; i < sheet.getPhysicalNumberOfRows(); ++i) {
                List<String> value = new ArrayList<>();
                Row row = sheet.getRow(i);
                for (int j = 0; j < 40; ++j) {
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

                LaAgentPojo laAgent = new LaAgentPojo();
                laAgent.setIdType(value.get(4));// 证件类型*
                laAgent.setIdNo(value.get(5));// 证件号码*

                if (value.get(0).equals("null")) {
                    successfg=true;
                    lmm.add(errorCause(i,0,"错误！四级管理机构代码不能为空"));
                }
                else if(value.get(0).length()>20){
                    successfg=true;
                    lmm.add(errorCause(i,0,"错误！四级管理机构代码长度不能超过20字符"));
                }

                if (value.get(1).equals("null")) {
                    successfg=true;
                    lmm.add(errorCause(i,1,"错误！姓名不能为空"));
                }
                else if(value.get(1).length()>200){
                    successfg=true;
                    lmm.add(errorCause(i,1,"错误！姓名长度不能超过200字符"));
                }

                if (value.get(2).equals("null")) {
                    successfg=true;
                    lmm.add(errorCause(i,2,"错误！性别不能为空"));
                }
                else if(value.get(2).length()>4){
                    successfg=true;
                    lmm.add(errorCause(i,2,"错误！姓名长度不能超过4字符"));
                }
                boolean birf=value.get(3).matches(m);
                if (value.get(3).equals("null")) {
                    successfg=true;
                    lmm.add(errorCause(i,3,"错误！出生日期不能为空"));
                }
                else if(birf==false){
                    successfg=true;
                    lmm.add(errorCause(i,3,"错误！出生日期格式不符合要求"));
                }

                if (value.get(4).equals("null")) {
                    successfg=true;
                    lmm.add(errorCause(i,4,"错误！证件类型不能为空"));
                }
                else if(value.get(4).length()>4){
                    successfg=true;
                    lmm.add(errorCause(i,4,"错误！证件类型长度不能超过4字符"));
                }

                if (value.get(5).equals("null")) {
                    successfg=true;
                    lmm.add(errorCause(i,5,"错误！证件号码不能为空"));
                }

                int f = idCheck.idcheck(laAgent);
                switch (f) {
                    case 1:
                        break;
                    case 2:
                        successfg=true;
                        lmm.add(errorCause(i,5,"具有相同证件号码的人已经存在或此人已在其他渠道任职，并且尚未离职"));
                        break;
                    case 3:
                        successfg=true;
                        lmm.add(errorCause(i,5,"该人员离职不满六个月，不可二次入司"));
                        break;
                    case 4:
                        break;
                    case 5:
                        successfg=true;
                        lmm.add(errorCause(i,5,"出现未知错误"));
                        break;
                    case 6:
                        break;
                    case 7:
                        successfg=true;
                        lmm.add(errorCause(i,5,"证件号码不是字母数字格式"));
                        break;
                    case 8:
                        successfg=true;
                        lmm.add(errorCause(i,5,"证件号码应小于等于18位"));
                        break;
                    case 9:
                        successfg=true;
                        lmm.add(errorCause(i,5,"身份证号码错误"));
                        break;
                }
                boolean empolyeeflag=value.get(6).matches(m);
                if (value.get(6).equals("null")) {
                    successfg=true;
                    lmm.add(errorCause(i,6,"错误！入司日期不能为空"));
                }
                else if(empolyeeflag==false) {
                    successfg=true;
                    lmm.add(errorCause(i,6,"错误！入司日期格式不符合要求"));
                }

                if (value.get(7).equals("null")) {
                    successfg=true;
                    lmm.add(errorCause(i,7,"错误！户口类型不能为空"));
                }
                else if(value.get(7).length()>2){
                    successfg=true;
                    lmm.add(errorCause(i,7,"错误！户口类型长度不能超过2字符"));
                }

                if (value.get(8).equals("null")) {
                    successfg=true;
                    lmm.add(errorCause(i,8,"错误！户口所在省不能为空"));
                }
                else if(value.get(8).length()>60){
                    successfg=true;
                    lmm.add(errorCause(i,8,"错误！户口所在省长度不能超过60字符"));
                }

                if (value.get(9).equals("null")) {
                    successfg=true;
                    lmm.add(errorCause(i,9,"错误！最高学历不能为空"));
                }
                else if(value.get(9).length()>2){
                    successfg=true;
                    lmm.add(errorCause(i,9,"错误！最高学历长度不能超过2字符"));
                }

                if (value.get(11).equals("null")) {
                    successfg=true;
                    lmm.add(errorCause(i,11,"错误！最高学位不能为空"));
                }
                else if(value.get(11).length()>4){
                    successfg=true;
                    lmm.add(errorCause(i,11,"错误！最高学位长度不能超过4字符"));
                }

                if (value.get(12).equals("null")) {
                    successfg=true;
                    lmm.add(errorCause(i,12,"错误！毕业院校不能为空"));
                }
                else if(value.get(12).length()>40)
                {
                    successfg=true;
                    lmm.add(errorCause(i,12,"错误！毕业院校长度不能超过40字符"));
                }

                if (value.get(13).equals("null")) {
                    successfg=true;
                    lmm.add(errorCause(i,13,"错误！专业不能为空"));
                }
                else if(value.get(13).length()>40)
                {
                    successfg=true;
                    lmm.add(errorCause(i,13,"错误！专业长度不能超过40字符"));
                }

                if (value.get(14).equals("null")) {
                    successfg=true;
                    lmm.add(errorCause(i,14,"错误！民族不能为空"));
                }
                else if(value.get(14).length()>40)
                {
                    successfg=true;
                    lmm.add(errorCause(i,14,"错误！民族长度不能超过40字符"));
                }

                if (value.get(15).equals("null")) {
                    successfg=true;
                    lmm.add(errorCause(i,15,"错误！籍贯不能为空"));
                }
                else if(value.get(15).length()>3)
                {
                    successfg=true;
                    lmm.add(errorCause(i,15,"错误！籍贯长度不能超过3字符"));
                }
                if (value.get(16).equals("null")) {
                    successfg=true;
                    lmm.add(errorCause(i,16,"错误！ 最近一份工作行业类型不能为空"));
                }
                else if(value.get(16).length()>2)
                {
                    successfg=true;
                    lmm.add(errorCause(i,16,"错误！ 最近一份工作行业类型不能超过2字符"));
                }

                if (value.get(17).equals("null")) {
                    successfg=true;
                    lmm.add(errorCause(i,17,"错误！最近一份职业不能为空"));
                }
                else if(value.get(17).length()>20)
                {
                    successfg=true;
                    lmm.add(errorCause(i,17,"错误！最近一份职业不能超过20字符"));
                }
                if (value.get(18).equals("null")) {
                    successfg=true;
                    lmm.add(errorCause(i,18,"错误！最近一家工作单位不能为空"));
                }
                else if(value.get(18).length()>40)
                {
                    successfg=true;
                    lmm.add(errorCause(i,18,"错误！最近一家工作单位不能超过40字符"));
                }

                if (value.get(19).equals("null")) {
                    successfg=true;
                    lmm.add(errorCause(i,19,"错误！最近一份工作职务不能为空"));
                }
                else if(value.get(19).length()>60)
                {
                    successfg=true;
                    lmm.add(errorCause(i,19,"错误！最近一份工作职务不能超过60字符"));
                }
                if (value.get(20).equals("null")) {
                    successfg=true;
                    lmm.add(errorCause(i,20,"错误！从业年限不能为空"));
                }
                else if(value.get(20).length()>10)
                {
                    successfg=true;
                    lmm.add(errorCause(i,20,"错误！从业年限不能超过10字符"));
                }

                if (value.get(21).equals("null")) {
                    successfg=true;
                    lmm.add(errorCause(i,21,"错误！家庭地址不能为空"));
                }
                else if(value.get(21).length()>50)
                {
                    successfg=true;
                    lmm.add(errorCause(i,21,"错误！家庭地址不能超过50字符"));
                }

                if (value.get(24).equals("null")) {
                    successfg=true;
                    lmm.add(errorCause(i,24,"错误！手机号码不能为空"));
                }
                else if(value.get(24).length()>64)
                {
                    successfg=true;
                    lmm.add(errorCause(i,24,"错误！手机号码不能超过64字符"));
                }

                if (value.get(25).equals("null")) {
                    successfg=true;
                    lmm.add(errorCause(i,25,"错误！E-mail不能为空"));
                }
                else if(value.get(25).length()>100)
                {
                    successfg=true;
                    lmm.add(errorCause(i,25,"错误！E-mail不能超过100字符"));
                }

                if (value.get(26).equals("null")) {
                    successfg=true;
                    lmm.add(errorCause(i,26,"错误！政治面貌不能为空"));
                }
                else if(value.get(26).length()>4)
                {
                    successfg=true;
                    lmm.add(errorCause(i,26,"错误！政治面貌不能超过4字符"));
                }
                if (value.get(27).equals("null")) {
                    successfg=true;
                    lmm.add(errorCause(i,27,"错误！ 账户银行总行不能为空"));
                }
                else if(value.get(27).length()>20)
                {
                    successfg=true;
                    lmm.add(errorCause(i,27,"错误！账户银行总行不能超过20字符"));
                }

                if (value.get(28).equals("null")) {
                    successfg=true;
                    lmm.add(errorCause(i,28,"错误！ 银行账号不能为空"));
                }
                else if(value.get(28).length()>200)
                {
                    successfg=true;
                    lmm.add(errorCause(i,28,"错误！ 银行账号不能超过200字符"));
                }

                if (value.get(29).equals("null")) {
                    successfg=true;
                    lmm.add(errorCause(i,29,"错误！银行卡开户行联行号不能为空"));
                }
                else if(value.get(29).length()>60)
                {
                    successfg=true;
                    lmm.add(errorCause(i,29,"错误！银行卡开户行联行号不能超过60字符"));
                }

                if (value.get(30).equals("null")) {
                    successfg=true;
                    lmm.add(errorCause(i,30,"错误！开户行省份不能为空"));
                }
                else if(value.get(30).length()>10)
                {
                    successfg=true;
                    lmm.add(errorCause(i,30,"错误！开户行省份不能超过10字符"));
                }
                if (value.get(31).equals("null")) {
                    successfg=true;
                    lmm.add(errorCause(i,31,"错误！开户行所在市不能为空"));
                }
                else if(value.get(31).length()>10)
                {
                    successfg=true;
                    lmm.add(errorCause(i,31,"错误！开户行所在市不能超过10字符"));
                }
                if (value.get(32).equals("null")) {
                    successfg=true;
                    lmm.add(errorCause(i,32,"错误！岗位不能为空"));
                }
                else if(value.get(32).length()>2)
                {
                    successfg=true;
                    lmm.add(errorCause(i,32,"错误！岗位不能超过2字符"));
                }

                if (value.get(33).equals("null")) {
                    successfg=true;
                    lmm.add(errorCause(i,33,"错误！人员职级不能为空"));
                }
                else if(value.get(33).length()>10)
                {
                    successfg=true;
                    lmm.add(errorCause(i,32,"错误！职级不能超过10字符"));
                }
                if (value.get(34).equals("null")) {
                    successfg=true;
                    lmm.add(errorCause(i,34,"错误！合同类型不能为空"));
                }
                else if(value.get(34).length()>6)
                {
                    successfg=true;
                    lmm.add(errorCause(i,32,"错误！职级不能超过6字符"));
                }

                int timefg=0;
                boolean startflag=value.get(35).matches(m);
                if (value.get(35).equals("null")) {
                    timefg++;
                    successfg=true;
                    lmm.add(errorCause(i,35,"错误！劳动合同起期不能为空"));
                }
                else if(startflag==false)
                {
                    timefg++;
                    successfg=true;
                    lmm.add(errorCause(i,35,"错误！劳动合同起期格式不符合要求"));
                }
                boolean endflag=value.get(36).matches(m);
                if (value.get(36).equals("null")) {
                    timefg++;
                    successfg=true;
                    lmm.add(errorCause(i,36,"错误！劳动合同止期不能为空"));
                }
                else if(endflag==false)
                {
                    timefg++;
                    successfg=true;
                    lmm.add(errorCause(i,36,"错误！劳动合同止期格式不符合要求"));
                }
                if((timefg!=2)&&(timefg!=1))
                {
                    Date ContractStartDate = df.parse(value.get(35));
                    laAgent.setContractStartDate(ContractStartDate);// 劳动合同起期*
                    Date ContractEndDate = df.parse(value.get(36));
                    laAgent.setContractEndDate(ContractEndDate);// 劳动合同止期*
                    long start_long = laAgent.getContractStartDate().getTime() / 3600000;
                    long end_long = laAgent.getContractEndDate().getTime() / 3600000;
                    if (start_long > end_long) {
                        successfg=true;
                        lmm.add(errorCause(i,36,"错误！劳动合同起期不能早于劳动合同止期"));
                    }
                }
                if (value.get(38).equals("null")) {
                    successfg=true;
                    lmm.add(errorCause(i,38,"错误！ 团队架构代码不能为空"));
                }
                else if(value.get(38).length()>20)
                {
                    successfg=true;
                    lmm.add(errorCause(i,38,"错误！ 团队架构代码不能超过20字符"));
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

                //检查数据库中的工号，调用方法生成新工号
                QueryWrapper<YlUserInfoEntity> sectionQueryWrapper = new QueryWrapper<>();
                List<YlLaAgentAttrEntity> pbListBlacks = this.baseMapper.selectList(null);
                boolean fg = false;
                String str = "";
                if (pbListBlacks.size() == 0) {
                    fg = false;
                } else {
                    fg = true;
                }
                if (fg == true) {
                    YlLaAgentAttrEntity ylLaAgentAttrEntity = pbListBlacks.get(pbListBlacks.size() - 1);
                    str = ylLaAgentAttrEntity.getAgentCode();
                }
                String newstr = getYlNo(str, fg);

                for (int j = 0; j < 40; ++j) {
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
                LaAgentPojo laAgent = new LaAgentPojo();
                laAgent.setManageCom4((String) value.get(0));//四级管理机构代码*
                laAgent.setAgentName((String) value.get(1));// 姓名*
                laAgent.setSex((String) value.get(2));// 性别*
                Date birthday = df.parse(value.get(3));
                laAgent.setBirthday(birthday); // 出生日期*
                laAgent.setIdType((String) value.get(4));// 证件类型*
                laAgent.setIdNo((String) value.get(5));// 证件号码*
                Date EmployDate = df.parse(value.get(6));
                laAgent.setEmployDate((Date) EmployDate);// 入司日期*
                laAgent.setRgtType((String) value.get(7));// 户口类型*
                laAgent.setRgtProvince((String) value.get(8));// 户口所在省*
                laAgent.setHighestDegree((String) value.get(9));// 最高学历*
                if(!value.get(10).equals("null"))
                {
                    laAgent.setFirstDegree((String) value.get(10));// 第一学历
                }
                laAgent.setDegree((String) value.get(11));// 最高学位*
                laAgent.setGraduateSchool((String) value.get(12));// 毕业院校*
                laAgent.setMajor((String) value.get(13));// 专业*
                laAgent.setNationality((String) value.get(14));//民族*
                laAgent.setNativeplace((String) value.get(15));// 籍贯*
                laAgent.setOldIndustryType((String) value.get(16)); // 最近一份工作行业类型*
                laAgent.setOldOccupation((String) value.get(17)); // 最近一份职业*
                laAgent.setOldCom((String) value.get(18));// 最近一家工作单位*
                laAgent.setOldJobDuty((String) value.get(19)); // 最近一份工作职务*
                laAgent.setWorkAge((String) value.get(20));// 从业年限*
                laAgent.setHomeAddress((String) value.get(21));// 家庭地址*
                if(!value.get(22).equals("null"))
                {
                    laAgent.setPostcode((String) value.get(22));// 邮编
                }
                if(!value.get(23).equals("null"))
                {
                    laAgent.setHomephone((String) value.get(23));// 住宅电话
                }
                laAgent.setPhone((String) value.get(24));// 手机*
                laAgent.setEmail((String) value.get(25));// E-mail*
                laAgent.setOutlookStatus((String) value.get(26));// 政治面貌*
                laAgent.setBankCode((String) value.get(27));// 账户银行总行*
                laAgent.setBankAccount((String) value.get(28));// 银行账号*
                laAgent.setUniteBankNum((String) value.get(29));// 银行卡开户行联行号*
                laAgent.setBankProvince((String) value.get(30));// 开户行省份*
                laAgent.setBankCity((String) value.get(31));// 开户行所在市*
                laAgent.setAgentJob((String) value.get(32));// 岗位*
                laAgent.setAgentGrade((String) value.get(33));// 人员职级*
                laAgent.setContractType((String) value.get(34));// 合同类型*
                Date ContractStartDate = df.parse(value.get(35));
                laAgent.setContractStartDate((Date) ContractStartDate);// 劳动合同起期*
                Date ContractEndDate = df.parse(value.get(35));
                laAgent.setContractEndDate((Date) ContractEndDate);// 劳动合同止期*
                if(!value.get(37).equals("null"))
                {
                    laAgent.setCertificateNo((String) value.get(37));// 专业资格证书号
                }
                laAgent.setBranchAttr((String) value.get(38));// 团队架构代码*
                if(!value.get(39).equals("null"))
                {
                    laAgent.setSapCode((String) value.get(39)); // SAP工号
                }
                laAgent.setAgentCode(newstr);
                laAgent.setOperator("wxy");//************


                boolean repeartflag=false;
                int f = idCheck.idcheck(laAgent);
                switch (f) {
                    case 1:
                        break;
                    case 2:
                        successfg=true;
                        lmm.add(errorCause(i,5,"具有相同证件号码的人已经存在或此人已在其他渠道任职，并且尚未离职"));
                    case 3:
                        successfg=true;
                        lmm.add(errorCause(i,5,"该人员离职不满六个月，不可二次入司"));
                    case 4:
                        repeartflag=true;
                    case 5:
                        successfg=true;
                        lmm.add(errorCause(i,5,"出现未知错误"));
                    case 6:
                        break;
                    case 7:
                        successfg=true;
                        lmm.add(errorCause(i,5,"证件号码不是字母数字格式"));
                    case 8:
                        successfg=true;
                        lmm.add(errorCause(i,5,"证件号码应小于等于18位"));
                    case 9:
                        successfg=true;
                        lmm.add(errorCause(i,5,"身份证号码错误"));
                }
                if(repeartflag=true)
                {
                    laAgent.setRepeatFalg("02");//不确定
                }
                else{
                    laAgent.setRepeatFalg("01");
                }
                //验证后，填充数据库表对应的实体对象的属性，用于存入数据库
                YlLaAgentAttrEntity ylLaAgentAttrEntity = new YlLaAgentAttrEntity();
                ylLaAgentAttrEntity.setAgentCode(newstr);
                ylLaAgentAttrEntity.setBankAccount(laAgent.getBankAccount());
                ylLaAgentAttrEntity.setBankCity(laAgent.getBankCity());
                ylLaAgentAttrEntity.setBankCode(laAgent.getBankCode());
                ylLaAgentAttrEntity.setBankProvince(laAgent.getBankProvince());
                ylLaAgentAttrEntity.setBirthday(laAgent.getBirthday());
                ylLaAgentAttrEntity.setCertificateNo(laAgent.getCertificateNo());
                ylLaAgentAttrEntity.setContractEndDate(laAgent.getContractEndDate());
                ylLaAgentAttrEntity.setContractStartDate(laAgent.getContractStartDate());
                ylLaAgentAttrEntity.setContractType(laAgent.getContractType());
                ylLaAgentAttrEntity.setDegree(laAgent.getDegree());
                ylLaAgentAttrEntity.setEmail(laAgent.getEmail());
                ylLaAgentAttrEntity.setFirstDegree(laAgent.getFirstDegree());
                ylLaAgentAttrEntity.setGraduateSchool(laAgent.getGraduateSchool());
                ylLaAgentAttrEntity.setHighestDegree(laAgent.getHighestDegree());
                ylLaAgentAttrEntity.setAddress(laAgent.getHomeAddress());
                ylLaAgentAttrEntity.setHomephone(laAgent.getHomephone());
                ylLaAgentAttrEntity.setIdNo(laAgent.getIdNo());
                ylLaAgentAttrEntity.setIdType(laAgent.getIdType());
                ylLaAgentAttrEntity.setMajor(laAgent.getMajor());
                ylLaAgentAttrEntity.setNationality(laAgent.getNationality());
                ylLaAgentAttrEntity.setNativeplace(laAgent.getNativeplace());
                ylLaAgentAttrEntity.setOldCom(laAgent.getOldCom());
                ylLaAgentAttrEntity.setOldIndustryType(laAgent.getOldIndustryType());
                ylLaAgentAttrEntity.setOldOccupation(laAgent.getOldOccupation());
                ylLaAgentAttrEntity.setOldJobDuty(laAgent.getOldJobDuty());
                ylLaAgentAttrEntity.setOutlookStatus(laAgent.getOutlookStatus());
                ylLaAgentAttrEntity.setPhone(laAgent.getPhone());
                ylLaAgentAttrEntity.setPostcode(laAgent.getPostcode());
                ylLaAgentAttrEntity.setRgtType(laAgent.getRgtType());
                ylLaAgentAttrEntity.setRegAddress(laAgent.getRgtProvince());
                ylLaAgentAttrEntity.setSex(laAgent.getSex());
                ylLaAgentAttrEntity.setUniteBankNum(laAgent.getUniteBankNum());
                ylLaAgentAttrEntity.setWorkAge(laAgent.getWorkAge());
                ylLaAgentAttrEntity.setOperator(laAgent.getOperator());
                Date date = new Date();
                String time = df.format(date);//获取String类型的时间
                ylLaAgentAttrEntity.setMakeDate(date);
                ylLaAgentAttrEntity.setMakeTime(time);
                ylLaAgentAttrEntity.setModifyDate(date);
                ylLaAgentAttrEntity.setModifyTime(time);

                ylLaAgentAttrEntity.setOperator("wxy");

                //存入数据库
                int result = this.baseMapper.insert(ylLaAgentAttrEntity);
                boolean flag = laAgentService.laAgentSubmit(laAgent);
                if (result <0||flag == false) {
                    lmm.add(errorCause(0,0,"数据库插入出现问题"));
                }
            }
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("导入成功！", "第0行,第0列");
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
        map.put(error, str);
        return map;
    }
}

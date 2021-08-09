package com.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cms.common.IdCheck;
import com.cms.dao.LaAgentAttrDao;
import com.cms.entity.YlLaAgentAttrEntity;
import com.cms.entity.YlUserInfoEntity;
import com.cms.pojo.LaAgentPojo;
import com.cms.pojo.LaAgentUpdatePojo;
import com.cms.service.LaAgentServiceAttr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class LaAgentServiceAttrImpl extends ServiceImpl<LaAgentAttrDao, YlLaAgentAttrEntity> implements LaAgentServiceAttr {
    @Autowired
    public IdCheck idCheck;


    private String newstr;

    /**
     * 获取生成的工号，无参方法，返回储存在newstr的工号，请务必在调用agentSubmit一次后调用，否则会出现空指针或者生成的工号被覆盖
     */
    @Override
    public String getNewstr(){
        return this.newstr;
    }


    /**
     * 新增人员信息，传入一个LaAgentPojo对象，返回字符串，成功则返回"success",校验失败则返回失败原因。导入失败则返回“请联系管理员的问题”
     */
    @Override
    public String agentSubmit(LaAgentPojo laAgent){
        //检查数据库中的工号，调用方法生成新工号
        QueryWrapper<YlUserInfoEntity> sectionQueryWrapper = new QueryWrapper<>();
        List<YlLaAgentAttrEntity> pbListBlacks =this.baseMapper.selectList(null);
        boolean fg = false;
        String str = "";
        if(pbListBlacks.size() == 0){
            fg = false;
        }
        else{
            fg = true;
        }
        if(fg == true) {
            YlLaAgentAttrEntity ylLaAgentAttrEntity = pbListBlacks.get(pbListBlacks.size() - 1);
            str = ylLaAgentAttrEntity.getAgentCode();
        }
        newstr = this.getYlNo(str,fg);

        //在这调用验证信息的方法
        String checkAgentInfoResult = this.checkAgentInformation(laAgent);
        if(!checkAgentInfoResult.equals("success")){
            return checkAgentInfoResult;
        }

        //在此处调用包装用户信息实体对象，用于数据库操作
        YlLaAgentAttrEntity ylLaAgentAttrEntity = this.buildAgentAttrEntity(laAgent);

        int result = this.baseMapper.insert(ylLaAgentAttrEntity);
        if(result > 0){
            return "success";
        }
        return "录入失败，请程序员检查程序";
    }

    /**
     *更新人员信息，传入一个LaAgentPojo对象，返回字符串，成功则返回"success",校验失败则返回失败原因。导入失败则返回“请联系管理员的问题”
     */
    @Override
    public String agentUpdate(LaAgentUpdatePojo laAgent){
        //在这里调用验证信息的方法
        String checkAgentInfoUpdateResult = this.checkAgentInfoUpdate(laAgent);
        if(!checkAgentInfoUpdateResult.equals("success")){
            return  checkAgentInfoUpdateResult;
        }
        UpdateWrapper<YlLaAgentAttrEntity> updateWrapper = new UpdateWrapper<>();
        YlLaAgentAttrEntity ylLaAgentAttrEntity =  this.buildUpdateAgentAttrEntity(laAgent);
        updateWrapper.eq("agent_code",laAgent.getAgentCode());
        int affectRows = this.baseMapper.update(ylLaAgentAttrEntity,updateWrapper);
        if(affectRows > 0){
            return "success";
        }
        else{
            return "更新失败，请联系系统管理员zxc";
        }
    }

    /**
     * 此方法用于生成工号，例如传入一个格式为"YL00000000"工号字符串，自动生成"YL00000001"
     */
    private String getYlNo(String YlNo,boolean fg){
        String newYlNo;
        if(fg==false)
        {
            newYlNo = "YL00000001";
            return newYlNo;
        }
        else{
            newYlNo=YlNo.substring(YlNo.length() - 8);
            long no = Integer.parseInt(newYlNo);
            long n= ++no;
            newYlNo= String.format("YL" + "%08d", n);
        }
        return newYlNo;
    }

    /**
     * 此方法用于检验录入模块用户信息是否非法
     * 此方法传入一个用户信息的pojo对象，然后一次检验其信息，如果错误则直接返回错误信息，如果正确则返回字符串"success"
     */
    private String checkAgentInformation(LaAgentPojo laAgent){
        if(laAgent.getAgentGrade() == null){
            return "错误！职级信息为空";
        }
        if(laAgent.getAgentGrade().length() > 10){
            return "错误！职级信息超出长度范围";
        }
        if(laAgent.getAgentJob() == null){
            return "错误！岗位信息为空";
        }
        if(laAgent.getAgentJob().length() > 2){
            return "错误！岗位信息超出长度范围";
        }
        if(laAgent.getAgentName() == null){
            return "错误！人员姓名信息为空";
        }
        if(laAgent.getAgentName().length() > 200){
            return "错误！人员姓名信息超出长度范围";
        }
        if(laAgent.getBankAccount() == null){
            return "错误！银行账号信息为空";
        }
        if(laAgent.getBankAccount().length() > 200){
            return "错误！银行账号信息超出长度范围";
        }
        if(laAgent.getBankCity() == null){
            return "错误！开户行地市信息为空";
        }
        if(laAgent.getBankCity().length() > 10){
            return "错误！开户行地市信息超出长度范围";
        }
        if(laAgent.getBankCode() == null){
            return "错误！银行类别信息为空";
        }
        if(laAgent.getBankCode().length() > 20){
            return "错误！银行类别信息超出长度范围";
        }
        if(laAgent.getBankProvince() == null){
            return "错误！开户行省份信息为空";
        }
        if(laAgent.getBankProvince().length() > 10){
            return "错误！开户行省份信息超出长度范围";
        }
        if(laAgent.getBirthday() == null){
            return "错误！出生日期信息为空";
        }
        if(laAgent.getBranchAttr() == null){
            return "错误！团队编码信息为空";
        }
        if(laAgent.getBranchAttr().length() > 20){
            return "错误！团队编码信息超出长度范围";
        }
        if(laAgent.getCertificateNo() != null){
            if(laAgent.getCertificateNo() != null && laAgent.getCertificateNo().length() > 50){
                return "错误！专业资格证书号信息超出长度范围";
            }
            if(false){//此处调用资格证验证模块
                return "错误！未查询到资格证";
            }
        }
        if(laAgent.getContractEndDate() == null){
            return "错误！劳动合同止期信息为空";
        }
        if(laAgent.getContractStartDate() == null){
            return "错误！劳动合同起期信息为空";
        }
        if(laAgent.getContractType() == null){
            return "错误！合同类型信息为空";
        }
        if(laAgent.getContractType().length() > 6){
            return "错误！合同类型信息超出长度范围";
        }
        if(laAgent.getDegree() == null){
            return "错误！最高学位信息为空";
        }
        if(laAgent.getDegree().length() > 4){
            return "错误！最高学位信息超出长度范围";
        }
        if(laAgent.getEmail() == null){
            return "错误！邮箱信息为空";
        }
        if(laAgent.getEmail().length() > 100){
            return "错误！邮箱信息超出长度范围";
        }
        if(laAgent.getEmployDate() == null){
            return "错误！入司日期信息为空";
        }
        if(laAgent.getFirstDegree() != null && laAgent.getFirstDegree().length() > 2){
            return "错误！第一学历信息超出长度范围";
        }
        if(laAgent.getGraduateSchool() == null){
            return "错误！毕业院校信息为空";
        }
        if(laAgent.getGraduateSchool().length() > 40){
            return "错误！毕业院校信息超出长度范围";
        }
        if(laAgent.getHighestDegree() == null){
            return "错误！最高学历信息为空";
        }
        if(laAgent.getHighestDegree().length() > 2){
            return "错误！最高学历信息超出长度范围";
        }
        if(laAgent.getHomeAddress() == null){
            return "错误！家庭地址信息为空";
        }
        if(laAgent.getHomeAddress().length() > 50){
            return "错误！家庭地址信息超出长度范围";
        }
        if(laAgent.getHomephone() != null && laAgent.getHomephone().length() > 16){
            return "错误！宅电信息超出长度范围";
        }
        if(laAgent.getIdType() == null){
            return "错误！证件类型信息为空";
        }
        if(laAgent.getIdType().length() > 4){
            return "错误！证件类型信息超出长度范围";
        }
        int f = idCheck.idcheck(laAgent);
        switch (f)
        {
            case 1:
                break;
            case 2:
                return "具有相同证件号码的人已经存在或此人已在其他渠道任职，并且尚未离职";
            case 3:
                return "该人员离职不满六个月，不可二次入司";
            case 4:
                break;
            //return "具有相同证件号码的人已存在且离职，此人员为二次入司";
            case 5:
                return "出现未知错误";
            case 6:
                return "请输入证件号码";
            case 7:
                return "证件号码不是字母数字格式";
            case 8:
                return "证件号码应小于等于18位";
            case 9:
                return "身份证号码错误";
        }
        if(laAgent.getMajor() == null){
            return "错误！专业信息为空";
        }
        if(laAgent.getMajor().length() > 40){
            return "错误！专业信息超出长度范围";
        }
        if(laAgent.getManageCom2() == null){
            return "错误！二级管理机构编码信息为空";
        }
        if(laAgent.getManageCom2().length() > 20){
            return "错误！二级管理机构编码信息超出长度范围";
        }
        if(laAgent.getManageCom3() == null){
            return "错误！三级管理机构编码信息为空";
        }
        if(laAgent.getManageCom3().length() > 20){
            return "错误！三级管理机构编码信息超出长度范围";
        }
        if(laAgent.getManageCom4() == null){
            return "错误！四级管理机构编码信息为空";
        }
        if(laAgent.getManageCom4().length() > 20){
            return "错误！四级管理机构编码信息超出长度范围";
        }
        if(laAgent.getNationality() == null){
            return "错误！民族信息为空";
        }
        if(laAgent.getNationality().length() > 50){
            return "错误！民族信息超出长度范围";
        }
        if(laAgent.getNativeplace() == null){
            return "错误！籍贯信息为空";
        }
        if(laAgent.getNativeplace().length() > 3){
            return "错误！籍贯信息超出长度范围";
        }
        if(laAgent.getOldCom() == null){
            return "错误！最近一家工作单位信息为空";
        }
        if(laAgent.getOldCom().length() > 40){
            return "错误！最近一家工作单位信超出长度范围";
        }
        if(laAgent.getOldIndustryType() == null){
            return "错误！最近一份工作行业类型信息为空";
        }
        if(laAgent.getOldIndustryType().length() > 2){
            return "错误！最近一份工作行业类型信息超出长度范围";
        }
        if(laAgent.getOldJobDuty() == null){
            return "错误！最近一份工作职务信息为空";
        }
        if(laAgent.getOldJobDuty().length() > 60){
            return "错误！最近一份工作职务信息超出长度范围";
        }
        if(laAgent.getOldOccupation() == null){
            return "错误！最近一份职业信息为空";
        }
        if(laAgent.getOldOccupation().length() > 20){
            return "错误！最近一份职业信息超出长度范围";
        }
        if(laAgent.getOutlookStatus() == null){
            return "错误！政治面貌信息为空";
        }
        if(laAgent.getOutlookStatus().length() > 4){
            return "错误！政治面貌信息超出长度范围";
        }
        if(laAgent.getPhone() == null){
            return "错误！手机号码信息为空";
        }
        if(laAgent.getPhone().length() > 64){
            return "错误！手机号码信息超出长度范围";
        }
        if(laAgent.getPostcode() != null && laAgent.getPostcode().length() > 10){
            return "错误！邮编信息超出长度范围";
        }
        if(laAgent.getRgtProvince() == null){
            return "错误！户口所在省信息为空";
        }
        if(laAgent.getRgtProvince().length() > 60){
            return "错误！户口所在省信息超出长度范围";
        }
        if(laAgent.getRgtType() == null){
            return "错误！户口类型信息为空";
        }
        if(laAgent.getRgtType().length() > 2){
            return "错误！户口类型信息超出长度范围";
        }
        if(laAgent.getSapCode() != null){
            if(laAgent.getSapCode() != null && laAgent.getSapCode().length() > 60){
                return "错误！SAP工号信息超出长度范围";
            }
            if(false){//此处调用sap工号调用模块
                return "错误！查询不到SAP工号";
            }
        }
        if(laAgent.getSex() == null){
            return "错误！性别信息为空";
        }
        if(laAgent.getSex().length() > 4){
            return "错误！性别信息超出长度范围";
        }
        if(laAgent.getUniteBankNum() == null){
            return "错误！银行卡开户行联行号信息为空";
        }
        if(laAgent.getUniteBankNum().length() > 60){
            return "错误！银行卡开户行联行号信息超出长度范围";
        }
        if(laAgent.getWorkAge() == null){
            return "错误！从业年限信息为空";
        }
        if(laAgent.getWorkAge().length() > 10){
            return "错误！从业年限信息超出长度范围";
        }
        if(laAgent.getOperator() == null){
            return "错误！操作员信息为空";
        }
        if(laAgent.getOperator().length() > 60){
            return "错误！操作员信息超出长度范围";
        }
        return "success";
    }

    /**
     * 此方法用于打包成与数据库对应的实体对象
     * 使用时传入一个LaAgentPojo对象，返回一个YlLaAgentAttrEntity对象
     * 返回的对象可以用于对数据库中yl_la_agent_attr表的insert操作
     */
    private YlLaAgentAttrEntity buildAgentAttrEntity(LaAgentPojo laAgent){
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
        SimpleDateFormat df = new SimpleDateFormat("HH-mm-ss");//设置日期格式
        String time = df.format(date);//获取String类型的时间
        ylLaAgentAttrEntity.setMakeDate(date);
        ylLaAgentAttrEntity.setMakeTime(time);
        ylLaAgentAttrEntity.setModifyDate(date);
        ylLaAgentAttrEntity.setModifyTime(time);
        return ylLaAgentAttrEntity;
    }

    /**
     * 此方法用于检验修改模块用户信息是否非法
     * 此方法传入一个用户信息的pojo对象，然后一次检验其信息，如果错误则直接返回错误信息，如果正确则返回字符串"success"
     */
    private String checkAgentInfoUpdate(LaAgentUpdatePojo laAgent){
        if(laAgent.getAgentCode() == null){
            return "错误！代理人工号信息为空";
        }
        if(laAgent.getAgentCode().length() > 60){
            return "错误！代理人工号超出长度范围";
        }
        if(laAgent.getAgentGrade() == null){
            return "错误！职级信息为空";
        }
        if(laAgent.getAgentGrade().length() > 10){
            return "错误！职级信息超出长度范围";
        }
        if(laAgent.getBankAccount() == null){
            return "错误！银行账号信息为空";
        }
        if(laAgent.getBankAccount().length() > 200){
            return "错误！银行账号信息超出长度范围";
        }
        if(laAgent.getBankCity() == null){
            return "错误！开户行地市信息为空";
        }
        if(laAgent.getBankCity().length() > 10){
            return "错误！开户行地市信息超出长度范围";
        }
        if(laAgent.getBankCode() == null){
            return "错误！银行类别信息为空";
        }
        if(laAgent.getBankCode().length() > 20){
            return "错误！银行类别信息超出长度范围";
        }
        if(laAgent.getBankProvince() == null){
            return "错误！开户行省份信息为空";
        }
        if(laAgent.getBankProvince().length() > 10){
            return "错误！开户行省份信息超出长度范围";
        }
        if(laAgent.getContractEndDate() == null){
            return "错误！劳动合同止期信息为空";
        }
        if(laAgent.getContractStartDate() == null){
            return "错误！劳动合同起期信息为空";
        }
        if(laAgent.getContractType() == null){
            return "错误！合同类型信息为空";
        }
        if(laAgent.getContractType().length() > 6){
            return "错误！合同类型信息超出长度范围";
        }
        if(laAgent.getDegree() == null){
            return "错误！最高学位信息为空";
        }
        if(laAgent.getDegree().length() > 4){
            return "错误！最高学位信息超出长度范围";
        }
        if(laAgent.getEmail() == null){
            return "错误！邮箱信息为空";
        }
        if(laAgent.getEmail().length() > 100){
            return "错误！邮箱信息超出长度范围";
        }
        if(laAgent.getFirstDegree() != null && laAgent.getFirstDegree().length() > 2){
            return "错误！第一学历信息超出长度范围";
        }
        if(laAgent.getGraduateSchool() == null){
            return "错误！毕业院校信息为空";
        }
        if(laAgent.getGraduateSchool().length() > 40){
            return "错误！毕业院校信息超出长度范围";
        }
        if(laAgent.getHighestDegree() == null){
            return "错误！最高学历信息为空";
        }
        if(laAgent.getHighestDegree().length() > 2){
            return "错误！最高学历信息超出长度范围";
        }
        if(laAgent.getHomeAddress() == null){
            return "错误！家庭地址信息为空";
        }
        if(laAgent.getHomeAddress().length() > 50){
            return "错误！家庭地址信息超出长度范围";
        }
        if(laAgent.getHomephone() != null && laAgent.getHomephone().length() > 16){
            return "错误！宅电信息超出长度范围";
        }
        if(laAgent.getMajor() == null){
            return "错误！专业信息为空";
        }
        if(laAgent.getMajor().length() > 40){
            return "错误！专业信息超出长度范围";
        }
        if(laAgent.getNationality() == null){
            return "错误！民族信息为空";
        }
        if(laAgent.getNationality().length() > 50){
            return "错误！民族信息超出长度范围";
        }
        if(laAgent.getNativeplace() == null){
            return "错误！籍贯信息为空";
        }
        if(laAgent.getNativeplace().length() > 3){
            return "错误！籍贯信息超出长度范围";
        }
        if(laAgent.getOldIndustryType() == null){
            return "错误！最近一份工作行业类型信息为空";
        }
        if(laAgent.getOldIndustryType().length() > 2){
            return "错误！最近一份工作行业类型信息超出长度范围";
        }
        if(laAgent.getOldJobDuty() == null){
            return "错误！最近一份工作职务信息为空";
        }
        if(laAgent.getOldJobDuty().length() > 60){
            return "错误！最近一份工作职务信息超出长度范围";
        }
        if(laAgent.getOldOccupation() == null){
            return "错误！最近一份职业信息为空";
        }
        if(laAgent.getOldOccupation().length() > 20){
            return "错误！最近一份职业信息超出长度范围";
        }
        if(laAgent.getOutlookStatus() == null){
            return "错误！政治面貌信息为空";
        }
        if(laAgent.getOutlookStatus().length() > 4){
            return "错误！政治面貌信息超出长度范围";
        }
        if(laAgent.getPhone() == null){
            return "错误！手机号码信息为空";
        }
        if(laAgent.getPhone().length() > 64){
            return "错误！手机号码信息超出长度范围";
        }
        if(laAgent.getPostcode() != null && laAgent.getPostcode().length() > 10){
            return "错误！邮编信息超出长度范围";
        }
        if(laAgent.getRgtProvince() == null){
            return "错误！户口所在省信息为空";
        }
        if(laAgent.getRgtProvince().length() > 60){
            return "错误！户口所在省信息超出长度范围";
        }
        if(laAgent.getRgtType() == null){
            return "错误！户口类型信息为空";
        }
        if(laAgent.getRgtType().length() > 2){
            return "错误！户口类型信息超出长度范围";
        }
        if(laAgent.getUniteBankNum() == null){
            return "错误！银行卡开户行联行号信息为空";
        }
        if(laAgent.getUniteBankNum().length() > 60){
            return "错误！银行卡开户行联行号信息超出长度范围";
        }
        if(laAgent.getWorkAge() == null){
            return "错误！从业年限信息为空";
        }
        if(laAgent.getWorkAge().length() > 10){
            return "错误！从业年限信息超出长度范围";
        }
        if(laAgent.getOperator() == null){
            return "错误！操作员信息为空";
        }
        if(laAgent.getOperator().length() > 60){
            return "错误！操作员信息超出长度范围";
        }
        return "success";
    }

    /**
     * 此方法用于打包与数据库对应的实体对象，用于update操作
     * 传入一个LaAgentUpdatePojo对象，返回一个YlLaAgnetAttrEntity对象，该对象中的部分值已被更新，可用来对yl_la_agent_attr表进行更新操作
     */
    private YlLaAgentAttrEntity buildUpdateAgentAttrEntity(LaAgentUpdatePojo laAgent){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("agent_code",laAgent.getAgentCode());
        YlLaAgentAttrEntity ylLaAgentAttrEntity = this.baseMapper.selectOne(queryWrapper);
        ylLaAgentAttrEntity.setBankAccount(laAgent.getBankAccount());
        ylLaAgentAttrEntity.setBankCity(laAgent.getBankCity());
        ylLaAgentAttrEntity.setBankCode(laAgent.getBankCode());
        ylLaAgentAttrEntity.setBankProvince(laAgent.getBankProvince());
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
        ylLaAgentAttrEntity.setMajor(laAgent.getMajor());
        ylLaAgentAttrEntity.setNationality(laAgent.getNationality());
        ylLaAgentAttrEntity.setNativeplace(laAgent.getNativeplace());
        ylLaAgentAttrEntity.setOldIndustryType(laAgent.getOldIndustryType());
        ylLaAgentAttrEntity.setOldOccupation(laAgent.getOldOccupation());
        ylLaAgentAttrEntity.setOldJobDuty(laAgent.getOldJobDuty());
        ylLaAgentAttrEntity.setOutlookStatus(laAgent.getOutlookStatus());
        ylLaAgentAttrEntity.setPhone(laAgent.getPhone());
        ylLaAgentAttrEntity.setPostcode(laAgent.getPostcode());
        ylLaAgentAttrEntity.setRgtType(laAgent.getRgtType());
        ylLaAgentAttrEntity.setRegAddress(laAgent.getRgtProvince());
        ylLaAgentAttrEntity.setUniteBankNum(laAgent.getUniteBankNum());
        ylLaAgentAttrEntity.setWorkAge(laAgent.getWorkAge());
        ylLaAgentAttrEntity.setOperator(laAgent.getOperator());
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("HH-mm-ss");//设置日期格式
        String time = df.format(date);//获取String类型的时间
        ylLaAgentAttrEntity.setModifyDate(date);
        ylLaAgentAttrEntity.setModifyTime(time);
        return ylLaAgentAttrEntity;
    }
}

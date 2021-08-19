package com.cms.common;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.read.listener.ReadListener;
import com.cms.entity.LaAgentGradeEntity;
import com.cms.entity.YlLaAgentAttrEntity;
import com.cms.entity.YlLaAgentEntity;
import com.cms.pojo.YlLaAgentAttrExcelUpdatePojo;
import com.cms.service.impl.LaAgentServiceAttrImpl;
import com.cms.service.impl.LaAgentServiceImpl;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;

@Component
public class ModelExcelListener extends AnalysisEventListener {
    //注入一个人员表Impl 人员表Attr表Impl
    @Autowired
    LaAgentServiceAttrImpl laAgentServiceAttr;//Attr 表
    @Autowired
    LaAgentServiceImpl laAgentServiceImpl;

    @SneakyThrows
    @Override
    public void invoke(Object o, AnalysisContext analysisContext) {
        //这里对数据进行校验


        YlLaAgentAttrExcelUpdatePojo ylLaAgentAttrExcelUpdatePojo=(YlLaAgentAttrExcelUpdatePojo) o;

        if (ylLaAgentAttrExcelUpdatePojo.getComCode2().equals("二级管理机构代码")){
            //这是表头行 不进行设置
        }else {
            YlLaAgentAttrEntity ylLaAgentAttrEntity=new YlLaAgentAttrEntity();
            ylLaAgentAttrEntity.setIdType(ylLaAgentAttrExcelUpdatePojo.getIdtype());
            ylLaAgentAttrEntity.setIdNo(ylLaAgentAttrExcelUpdatePojo.getIdno());
            ylLaAgentAttrEntity.setSex(ylLaAgentAttrExcelUpdatePojo.getSex());
            ylLaAgentAttrEntity.setBirthday(ylLaAgentAttrExcelUpdatePojo.getBirthday());
            ylLaAgentAttrEntity.setNativeplace(ylLaAgentAttrExcelUpdatePojo.getNativeplace());
            ylLaAgentAttrEntity.setPhone(ylLaAgentAttrExcelUpdatePojo.getPhone());
            //ylLaAgentAttrEntity.setHighestDegree(ylLaAgentAttrExcelUpdatePojo.getHighestDegree());
            ylLaAgentAttrEntity.setHighestDegree("1");
            ylLaAgentAttrEntity.setDegree(ylLaAgentAttrExcelUpdatePojo.getDegree());
            ylLaAgentAttrEntity.setOldIndustryType(ylLaAgentAttrExcelUpdatePojo.getOldIndustryType());
            ylLaAgentAttrEntity.setOldOccupation(ylLaAgentAttrExcelUpdatePojo.getOldOccupation());
            ylLaAgentAttrEntity.setOldCom(ylLaAgentAttrExcelUpdatePojo.getOldCom());
            ylLaAgentAttrEntity.setOldJobDuty(ylLaAgentAttrExcelUpdatePojo.getOldJodDuty());
            ylLaAgentAttrEntity.setWorkAge(ylLaAgentAttrExcelUpdatePojo.getWorkage());
            ylLaAgentAttrEntity.setContractType(ylLaAgentAttrExcelUpdatePojo.getContractType());
            System.out.println("合同开始"+ylLaAgentAttrExcelUpdatePojo.getContractStartDate());
            System.out.println("合同终止日期"+ylLaAgentAttrExcelUpdatePojo.getContractEndDate());
            ylLaAgentAttrEntity.setContractStartDate(ylLaAgentAttrExcelUpdatePojo.getContractStartDate());
            ylLaAgentAttrEntity.setContractEndDate(ylLaAgentAttrExcelUpdatePojo.getContractStartDate());
            ylLaAgentAttrEntity.setBankProvince(ylLaAgentAttrExcelUpdatePojo.getBankProvince());
            ylLaAgentAttrEntity.setBankCity(ylLaAgentAttrExcelUpdatePojo.getBankCity());
            ylLaAgentAttrEntity.setOperator("0");
            ylLaAgentAttrEntity.setMakeDate(ParseDate.getCurrentDate());
            ylLaAgentAttrEntity.setMakeTime(ParseDate.getCurrentTime());
            ylLaAgentAttrEntity.setModifyDate(ParseDate.getCurrentDate());
            ylLaAgentAttrEntity.setModifyTime(ParseDate.getCurrentTime());
            //ylLaAgentAttrEntity.setRgtType(ylLaAgentAttrExcelUpdatePojo.getRgtType());
            ylLaAgentAttrEntity.setRgtType("1");
            ylLaAgentAttrEntity.setIdType("1");
            ylLaAgentAttrEntity.setSex("1");
            ylLaAgentAttrEntity.setNativeplace("1");
            ylLaAgentAttrEntity.setDegree("1");
            ylLaAgentAttrEntity.setOldIndustryType("1");
            ylLaAgentAttrEntity.setAgentCode("YL00000099");
            laAgentServiceAttr.getBaseMapper().insert(ylLaAgentAttrEntity);
            YlLaAgentEntity ylLaAgentEntity=new YlLaAgentEntity();
            ylLaAgentEntity.setAgentName(ylLaAgentAttrExcelUpdatePojo.getName());
            ylLaAgentEntity.setAgentJob(ylLaAgentAttrExcelUpdatePojo.getAgentJob());
            ylLaAgentEntity.setAgentGrade(ylLaAgentAttrExcelUpdatePojo.getAgentGrade());
            ylLaAgentEntity.setAgentState(ylLaAgentAttrExcelUpdatePojo.getAgentState());
            ylLaAgentEntity.setBranchType("4");
            ylLaAgentEntity.setManageCom(ylLaAgentAttrExcelUpdatePojo.getComCode4());
            ylLaAgentEntity.setInitGrade(ylLaAgentAttrExcelUpdatePojo.getAgentGrade());
            ylLaAgentEntity.setAgentGroup(ylLaAgentAttrExcelUpdatePojo.getAgentGroup());
            ylLaAgentEntity.setRepeatFlag("05");//是否第一次入职
            ylLaAgentEntity.setOperator("0");
            ylLaAgentEntity.setMakeDate(ParseDate.getCurrentDate());
            ylLaAgentEntity.setMakeTime(ParseDate.getCurrentTime());
            ylLaAgentEntity.setModifyDate(ParseDate.getCurrentDate());
            ylLaAgentEntity.setModifyTime(ParseDate.getCurrentTime());
            ylLaAgentEntity.setAgentCode("YL000000099");
            laAgentServiceImpl.getBaseMapper().insert(ylLaAgentEntity);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println("Excel表格解析完毕 已经添加到数据库了");
    }
}

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ModelExcelListener extends AnalysisEventListener {
    //注入一个人员表Impl 人员表Attr表Impl
    @Autowired
    LaAgentServiceAttrImpl laAgentServiceAttr;//Attr 表
    @Autowired
    LaAgentServiceImpl laAgentServiceImpl;

    @Override
    public void invoke(Object o, AnalysisContext analysisContext) {
        //这里对数据进行校验
        YlLaAgentEntity ylLaAgentEntity=new YlLaAgentEntity();
        YlLaAgentAttrEntity ylLaAgentAttrEntity=new YlLaAgentAttrEntity();
        YlLaAgentAttrExcelUpdatePojo ylLaAgentAttrExcelUpdatePojo=(YlLaAgentAttrExcelUpdatePojo) o;
        if (ylLaAgentAttrExcelUpdatePojo.getComCode2().equals("二级管理机构代码")){
            //这是表头行 不进行设置
        }else {



        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println("Excel表格解析完毕 已经添加到数据库了");
    }
}

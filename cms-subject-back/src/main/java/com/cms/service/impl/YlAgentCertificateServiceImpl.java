package com.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cms.common.ParseDate;
import com.cms.dao.YlLaAgentCertificateDao;
import com.cms.entity.YlLaAgentCertificateEntity;
import com.cms.pojo.ChangeCertificatePojo;
import com.cms.service.YlAgentCertificateService;
import com.cms.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cms.dao.YlLaAgentCertificateDao;
import com.cms.dao.YlLaAgentDao;
import com.cms.entity.YlLaAgentAttrEntity;
import com.cms.entity.YlLaAgentCertificateEntity;
import com.cms.entity.YlLaAgentEntity;
import com.cms.entity.YlUserInfoEntity;
import com.cms.pojo.CeInsertPojo;
import com.cms.pojo.UYllaBranchGroupReturnPojo;
import com.cms.service.YlAgentCertificateService;
import com.cms.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author :zhanhaoze
 * @description :
 * @create :2021-08-06
 */
@Service
@Slf4j
public class YlAgentCertificateServiceImpl extends ServiceImpl<YlLaAgentCertificateDao, YlLaAgentCertificateEntity> implements YlAgentCertificateService {
    @Autowired
    IdCodeServiceImpl idCodeService;

    @Autowired
    YlLaAgentCertificateEntity ylLaAgentCertificateEntity;
    @Override
    public R changeCertificateService(ChangeCertificatePojo changeCertificatePojo) throws ParseException {
        QueryWrapper queryWrapper=new QueryWrapper();
        if (StringUtils.isEmpty(changeCertificatePojo.getCertificateName())==true){
            return R.ok().put("code",501).put("msg","请选择资格证类型");
        }
        if (StringUtils.isEmpty(changeCertificatePojo.getOldCertificateNo())==true){
            return R.ok().put("code",501).put("msg","旧的资格证为空 请您核查");
        }
        if (StringUtils.isEmpty(changeCertificatePojo.getAgentCode())==true){
            return R.ok().put("code",501).put("msg","人员数据不可为空");
        }

        queryWrapper.eq(StringUtils.isEmpty(changeCertificatePojo.getAgentCode()),"agent_code",changeCertificatePojo.getAgentCode());
        queryWrapper.eq(StringUtils.isEmpty(changeCertificatePojo.getCertificateName()),"certificate_name",changeCertificatePojo.getCertificateName());
        queryWrapper.eq(StringUtils.isEmpty(changeCertificatePojo.getOldCertificateNo()),"certificate_no",changeCertificatePojo.getOldCertificateNo());
        //根据人员工号 职业证类型 旧的资格证号进行判断
        List<YlLaAgentCertificateEntity> list=this.baseMapper.selectList(queryWrapper);
        YlLaAgentCertificateEntity result;
        if (list.size()==1){
             result=list.get(0);
        }else {
            return R.error().put("msg","信息重复").put("code",501);

        }
        if (result==null){ return R.error("数据库中没这个员工，该员工没有该证件，该证件号不存在"); }
        //姓名无 不设置
        this.ylLaAgentCertificateEntity.setAgentCode(changeCertificatePojo.getAgentCode());
        this.ylLaAgentCertificateEntity.setCertificateType("1");//不可修改 type是数字类型的 与名称一起给
        this.ylLaAgentCertificateEntity.setCertificateName(changeCertificatePojo.getCertificateName());//不可修改 必须存在
        this.ylLaAgentCertificateEntity.setCertificateNo(changeCertificatePojo.getOldCertificateNo());//可修改
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        this.ylLaAgentCertificateEntity.setReleaseDate(simpleDateFormat.parse(changeCertificatePojo.getReleaseDate()));
        this.ylLaAgentCertificateEntity.setReissueDate(simpleDateFormat.parse(changeCertificatePojo.getReissueDate()));
        this.ylLaAgentCertificateEntity.setStartEffectiveDate(simpleDateFormat.parse(changeCertificatePojo.getStartEffectiveDate()));
        this.ylLaAgentCertificateEntity.setEndEffectiveDate(simpleDateFormat.parse(changeCertificatePojo.getEndEffectiveDate()));
        if (StringUtils.isEmpty(changeCertificatePojo.getApprover())==false){ this.ylLaAgentCertificateEntity.setApprover(changeCertificatePojo.getApprover()); }else {
            this.ylLaAgentCertificateEntity.setApprover("无");
        }
        if (result.getMakeTime()==null||StringUtils.isEmpty(result.getMakeTime())==true||"".equals(result.getMakeTime())){
            ylLaAgentCertificateEntity.setMakeDate(ParseDate.getCurrentDate());
            ylLaAgentCertificateEntity.setMakeTime(ParseDate.getCurrentTime());
        }else {
            this.ylLaAgentCertificateEntity.setMakeDate(result.getMakeDate());
            this.ylLaAgentCertificateEntity.setMakeTime(result.getMakeTime());
        }
        this.ylLaAgentCertificateEntity.setModifyDate(ParseDate.getCurrentDate());
        this.ylLaAgentCertificateEntity.setModifyTime(ParseDate.getCurrentTime());
        this.ylLaAgentCertificateEntity.setOperator("0");
        int update = this.baseMapper.update(this.ylLaAgentCertificateEntity, queryWrapper);
            return R.ok();
    }

        //创建QueryWrapper对象
        @Autowired
        IdCheckServiceImpl laAgentService;

        @Autowired
        YlAgentCertificateService ylAgentCertificateService;

        @Override
        public String Idcheck(CeInsertPojo ceInsertPojo) {
            //判断输入的人员工号是否为空
            if (ceInsertPojo.getAgentCode() == null) {
                return "输入工号为空";
            } else {
                //输入的人员工号非空
                return "输入的工号存在";
            }
        }

        @Override
        public String SearchNameById(CeInsertPojo ceInsertPojo) {
            if (laAgentService.getBaseMapper().selectById(ceInsertPojo.getAgentCode()) != null) {
                String name = laAgentService.getBaseMapper().selectById(ceInsertPojo.getAgentCode()).getAgentName();
                return name;
            } else {
                return null;
            }
        }

        @Override
        public String initializeEdorno(CeInsertPojo ceInsertPojo) {
            List<YlLaAgentCertificateEntity> pbListBlacks = this.baseMapper.selectList(null);
            boolean fg = false;
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


        @Override
        public int InsertCertificate(CeInsertPojo ceInsertPojo) throws Exception {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date newDat = new Date();
            //按照pojo的值插入到数据库
            YlLaAgentCertificateEntity ylLaAgentCertificateEntity = new YlLaAgentCertificateEntity();
            ylLaAgentCertificateEntity.setEdorNo(ylAgentCertificateService.initializeEdorno(ceInsertPojo));
            ylLaAgentCertificateEntity.setAgentCode(ceInsertPojo.getAgentCode());
            ylLaAgentCertificateEntity.setCertificateType(ceInsertPojo.getCertificateType());
            ylLaAgentCertificateEntity.setCertificateName(ceInsertPojo.getCertificateName());
            ylLaAgentCertificateEntity.setCertificateNo(ceInsertPojo.getCertificateNo());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
            String format = simpleDateFormat.format(newDat);
            String[] s = format.split(" ");
            if (StringUtils.isEmpty(ceInsertPojo.getReissueDate())){
                ylLaAgentCertificateEntity.setReissueDate(sd.parse(s[0]));
            }else {
                ylLaAgentCertificateEntity.setReissueDate(sd.parse(ceInsertPojo.getReissueDate()));
            }
            ylLaAgentCertificateEntity.setReleaseDate(sd.parse(ceInsertPojo.getReleaseDate()));
            if (StringUtils.isEmpty(ceInsertPojo.getStartEffectiveDate())){
                ylLaAgentCertificateEntity.setStartEffectiveDate(sd.parse(s[0]));
            }else {
                ylLaAgentCertificateEntity.setStartEffectiveDate(sd.parse(ceInsertPojo.getStartEffectiveDate()));
            }
            if (StringUtils.isEmpty(ceInsertPojo.getEndEffectiveDate())){
                ylLaAgentCertificateEntity.setEndEffectiveDate(sd.parse(s[0]));
            }else {
                ylLaAgentCertificateEntity.setEndEffectiveDate(sd.parse(ceInsertPojo.getEndEffectiveDate()));
            }
            if (StringUtils.isEmpty(ceInsertPojo.getApprover())){
                ylLaAgentCertificateEntity.setApprover("0");
            }else {
                ylLaAgentCertificateEntity.setApprover(ceInsertPojo.getApprover());
            }
            ylLaAgentCertificateEntity.setOperator("0");
            ylLaAgentCertificateEntity.setMakeDate(newDat);
            ylLaAgentCertificateEntity.setMakeTime(ParseDate.getCurrentTime());
            ylLaAgentCertificateEntity.setModifyDate(newDat);
            ylLaAgentCertificateEntity.setModifyTime(ParseDate.getCurrentTime());
            QueryWrapper<YlLaAgentCertificateEntity> qw = new QueryWrapper<>();
            qw.eq("certificate_no", ylLaAgentCertificateEntity.getCertificateNo());
            YlLaAgentCertificateEntity ylLaAgentCertificateEntity1 = this.baseMapper.selectOne(qw);
            int insert = 0;
            if (ylLaAgentCertificateEntity1 == null) {
                insert = this.baseMapper.insert(ylLaAgentCertificateEntity);
            }
            return insert;
        }
    }



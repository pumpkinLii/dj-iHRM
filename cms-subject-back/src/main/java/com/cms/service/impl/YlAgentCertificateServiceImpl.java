package com.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cms.dao.YlLaAgentCertificateDao;
import com.cms.entity.YlLaAgentCertificateEntity;
import com.cms.pojo.ChangeCertificatePojo;
import com.cms.service.YlAgentCertificateService;
import com.cms.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
    YlLaAgentCertificateEntity ylLaAgentCertificateEntity;
    @Override
    public R changeCertificateService(ChangeCertificatePojo changeCertificatePojo) throws ParseException {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq(StringUtils.isEmpty(changeCertificatePojo.getAgentCode()),"agent_code",changeCertificatePojo.getAgentCode());
        queryWrapper.eq(StringUtils.isEmpty(changeCertificatePojo.getCertificateType()),"certificate_type",changeCertificatePojo.getCertificateType());
        queryWrapper.eq(StringUtils.isEmpty(changeCertificatePojo.getOldCertificateNo()),"certificate_no",changeCertificatePojo.getCertificateNol());
        List list = this.baseMapper.selectList(queryWrapper);
        if (list.size()==0){ return R.error("数据库中没这个员工，该员工没有该证件类型，该证件号不存在"); }
        //姓名无 不设置
        ylLaAgentCertificateEntity.setAgentCode(changeCertificatePojo.getAgentCode());
        ylLaAgentCertificateEntity.setCertificateType(changeCertificatePojo.getCertificateType());//不可修改 type是数字类型的 与名称一起给
        ylLaAgentCertificateEntity.setCertificateName(changeCertificatePojo.getCertificateName());//
        ylLaAgentCertificateEntity.setCertificateNo(changeCertificatePojo.getOldCertificateNo());//可修改
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        ylLaAgentCertificateEntity.setReleaseDate(simpleDateFormat.parse(changeCertificatePojo.getReleaseDate()));
        ylLaAgentCertificateEntity.setReissueDate(simpleDateFormat.parse(changeCertificatePojo.getReissueDate()));
        ylLaAgentCertificateEntity.setStartEffectiveDate(simpleDateFormat.parse(changeCertificatePojo.getStartEffectiveDate()));
        ylLaAgentCertificateEntity.setEndEffectiveDate(simpleDateFormat.parse(changeCertificatePojo.getEndEffectiveDate()));
        if (StringUtils.isEmpty(changeCertificatePojo.getApprover())==false){ ylLaAgentCertificateEntity.setApprover(changeCertificatePojo.getApprover()); }
        int update = this.baseMapper.update(ylLaAgentCertificateEntity, queryWrapper);
        if (update==1){
            return R.ok();

        }else {
            return R.error("没有修改成功");

        }
    }
}

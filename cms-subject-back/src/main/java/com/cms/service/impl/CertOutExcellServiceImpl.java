package com.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cms.common.ComnewSon;
import com.cms.dao.YlLaAgentCertificateDao;
import com.cms.dao.YlSelectExcellDao;
import com.cms.entity.YlLaAgentCertificateEntity;
import com.cms.entity.YlLaAgentEntity;
import com.cms.pojo.CertOutPojo;
import com.cms.pojo.YlMSelectExcellPojo;
import com.cms.pojo.YlSelectExcellPojo;
import com.cms.service.CertOutExcellService;
import com.cms.service.YlSelectExcellService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
@Slf4j
public class CertOutExcellServiceImpl  extends ServiceImpl<YlLaAgentCertificateDao, YlLaAgentCertificateEntity> implements CertOutExcellService {

    public List<List> certselect(List list) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        List<List> bigList=new ArrayList<>();
        for(int i=0;i<list.size();i++)
        {

            CertOutPojo certOutPojo=new CertOutPojo();
            List<CertOutPojo> l=new ArrayList<>();
            QueryWrapper<YlLaAgentCertificateEntity> qw = new QueryWrapper();
            qw.eq("certificate_no", list.get(i));
            YlLaAgentCertificateEntity certificateEntity = this.baseMapper.selectOne(qw);
            certOutPojo.setAgentCode(certificateEntity.getAgentCode());
            certOutPojo.setCertificateName(certificateEntity.getCertificateName());
            certOutPojo.setCertificateNo(certificateEntity.getCertificateNo());

            String ReleaseDate= df.format(certificateEntity.getReleaseDate());
            certOutPojo.setReleaseDate(ReleaseDate);
            if(certificateEntity.getReissueDate()!=null)
            {
                String ReissueDate = df.format(certificateEntity.getReissueDate());
                certOutPojo.setReissueDate(ReissueDate);
            }
            String StartEffectiveDate = df.format(certificateEntity.getStartEffectiveDate());
            certOutPojo.setStartEffectiveDate(StartEffectiveDate);

            String EndEffectiveDate = df.format(certificateEntity.getEndEffectiveDate());
            certOutPojo.setEndEffectiveDate(EndEffectiveDate);
            certOutPojo.setApprover(certificateEntity.getApprover());
            l.add(certOutPojo);
            bigList.add(l);
        }
        return bigList;
    }
}

package com.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cms.common.ComnewSon;
import com.cms.dao.*;
import com.cms.entity.*;
import com.cms.pojo.CertificateConditionPojo;
import com.cms.pojo.RetrieveCertificatePojo;
import com.cms.service.RCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class RCertificateImpl implements RCertificateService {
    @Autowired
    IdCodeDao idCodeDao;
    @Autowired
    ComCodeDao comCodeDao;
    @Autowired
    YlLaAgentDao ylLaAgentDao;
    @Autowired
    YlLaAgentCertificateDao ylLaAgentCertificateDao;
    @Autowired
    YlLaBranchGroupDao yllaBranchGroupDao;
    @Autowired
    ComnewSon comnewSon;

    @Override
    public List<Map> getComSon(String fatherCode) {
        //返回下属结构团队的列表
        if (sCheckNull(fatherCode)) fatherCode = "86";
        if (fatherCode.length() == 8) {
            List<Map> relist = new ArrayList<>();
            QueryWrapper<YlLaBranchGroupEntity> qw = new QueryWrapper<>();
            qw.eq("manage_com", fatherCode);
            List<YlLaBranchGroupEntity> list = yllaBranchGroupDao.selectList(qw);
            if (list.size() == 0) return relist;
            else {
                for (YlLaBranchGroupEntity i : list) {
                    Map map = new HashMap();
                    map.put("code", i.getAgentGroup());
                    map.put("name", i.getBranchName());
                    relist.add(map);
                }
                return relist;
            }
        } else {
            List<Map> relist = new ArrayList<>();
            relist = comnewSon.getSonManageCom(fatherCode);
            return relist;
        }
    }

    @Override
    public List<Map> initCertificate() {
        //获取码值
        QueryWrapper<LdCodeEntity> qw = new QueryWrapper<>();
        qw.eq("code_type", "certificatename");
        List<Map> list = new ArrayList<>();
        List<LdCodeEntity> clist = idCodeDao.selectList(qw);
        for (LdCodeEntity i : clist) {
            Map map = new HashMap();
            map.put("code", i.getCode());
            map.put("name", i.getCodeName());
            list.add(map);
        }
        return list;
    }
    @Override
    public List<RetrieveCertificatePojo> getCertificate(CertificateConditionPojo certificateConditionPojo) {
        //查询结果
        List<Map> certificateCode = initCertificate();
        QueryWrapper<RetrieveCertificatePojo> qw = new QueryWrapper<>();
        if (!sCheckNull(certificateConditionPojo.getAgentCode()))
            qw.eq("yla.agent_code", certificateConditionPojo.getAgentCode());
        if (!sCheckNull(certificateConditionPojo.getCertificateType()))
            qw.eq("ylc.certificate_type", certificateConditionPojo.getCertificateType());
        if (!sCheckNull(certificateConditionPojo.getStartEffectiveDate())) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date seef = simpleDateFormat.parse(certificateConditionPojo.getStartEffectiveDate());
                qw.eq("ylc.start_effective_date", seef);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (!sCheckNull(certificateConditionPojo.getEndEffectiveDate())) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date eeef = simpleDateFormat.parse(certificateConditionPojo.getEndEffectiveDate());
                qw.eq("ylc.end_effective_date", eeef);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (!sCheckNull(certificateConditionPojo.getBranchAttr()))
            qw.eq("ylbg.agent_group", certificateConditionPojo.getBranchAttr());
        if (!sCheckNull(certificateConditionPojo.getAgentName()))
            qw.eq("yla.agent_name", certificateConditionPojo.getAgentName());
        if (!sCheckNull(certificateConditionPojo.getManageCom2()))
            qw.likeRight("yla.manage_com", certificateConditionPojo.getManageCom2());
        if (!sCheckNull(certificateConditionPojo.getManageCom3()))
            qw.likeRight("yla.manage_com", certificateConditionPojo.getManageCom3());
        if (!sCheckNull(certificateConditionPojo.getManageCom4()))
            qw.likeRight("yla.manage_com", certificateConditionPojo.getManageCom4());
        List<RetrieveCertificatePojo> list = ylLaAgentCertificateDao.getCertificateInfo(qw);
        for (RetrieveCertificatePojo i : list) {
            try {
                i.setManageCom3((String) comnewSon.getFatherManageCom(i.getManageCom4()).get("comcode"));
                i.setManageComName3((String) comnewSon.getFatherManageCom(i.getManageCom4()).get("name"));
                i.setManageCom2((String) comnewSon.getFatherManageCom(i.getManageCom3()).get("comcode"));
                i.setManageComName2((String) comnewSon.getFatherManageCom(i.getManageCom3()).get("name"));
                for (Map j:certificateCode){
                    if(((String)j.get("code")).equals(i.getCertificateCode())){
                        i.setCertificateName((String) j.get("name"));
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if (list.size() == 0) return null;
        else return list;
    }

    @Override
    public List<Map> initCom2() {
        //获取全部二级机构
        return comnewSon.getSonManageCom("86");
    }

    private boolean sCheckNull(String str) {
        if (str == null || str.equals("")) return true;
        else return false;
    }
}
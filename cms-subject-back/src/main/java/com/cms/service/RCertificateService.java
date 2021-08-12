package com.cms.service;

import com.cms.pojo.CertificateConditionPojo;
import com.cms.pojo.RetrieveCertificatePojo;

import java.util.List;
import java.util.Map;

public interface RCertificateService {
    public List<Map> getComSon(String fatherCode);
    public List<RetrieveCertificatePojo> getCertificate(CertificateConditionPojo certificateConditionPojo);
    public List<Map> initCom2();
    public List<Map> initCertificate();
}

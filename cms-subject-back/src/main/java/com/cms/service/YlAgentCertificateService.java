package com.cms.service;

import com.cms.pojo.ChangeCertificatePojo;
import com.cms.util.R;
import com.cms.pojo.CeInsertPojo;
import org.hibernate.validator.constraints.EAN;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import java.text.ParseException;

/**
 * @author :zhanhaoze
 * @description :
 * @create :2021-08-06
 */
public interface YlAgentCertificateService {
    public R changeCertificateService(ChangeCertificatePojo changeCertificatePojo) throws ParseException;
    String Idcheck(CeInsertPojo ceInsertPojo);
    int InsertCertificate(CeInsertPojo ceInsertPojo) throws Exception;
    String initializeEdorno(CeInsertPojo ceInsertPojo);
    String SearchNameById(CeInsertPojo ceInsertPojo);

}


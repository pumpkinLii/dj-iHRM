package com.cms.service;

import com.cms.pojo.ChangeCertificatePojo;
import com.cms.util.R;

import java.text.ParseException;

/**
 * @author :zhanhaoze
 * @description :
 * @create :2021-08-06
 */
public interface YlAgentCertificateService {
    public R changeCertificateService(ChangeCertificatePojo changeCertificatePojo) throws ParseException;
}

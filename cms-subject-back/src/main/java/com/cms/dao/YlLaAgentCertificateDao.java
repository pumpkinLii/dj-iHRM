package com.cms.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.cms.entity.YlLaAgentCertificateEntity;
import com.cms.pojo.RetrieveCertificatePojo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author :zhanhaoze
 * @description :
 * @create :2021-08-06
 */
@Repository
public interface YlLaAgentCertificateDao extends BaseMapper<YlLaAgentCertificateEntity> {
    List<RetrieveCertificatePojo> getCertificateInfo(@Param(Constants.WRAPPER) QueryWrapper<RetrieveCertificatePojo> qw);
}

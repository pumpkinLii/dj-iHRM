package com.cms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cms.dao.YlLaAgentDao;
import com.cms.entity.YlLaAgentEntity;
import com.cms.service.YlAgentInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author :zhanhaoze
 * @description :
 * @create :2021-08-03
 */
@Service
@Slf4j
public class YlAgentInfoServiceImpl extends ServiceImpl<YlLaAgentDao, YlLaAgentEntity> implements YlAgentInfoService {
}

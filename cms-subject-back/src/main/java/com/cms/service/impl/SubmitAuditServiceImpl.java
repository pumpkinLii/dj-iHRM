package com.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.cms.dao.YlLaDimissionDao;
import com.cms.entity.YlLaDimissionEntity;
import com.cms.service.SubmitAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubmitAuditServiceImpl implements SubmitAuditService {
    @Autowired
    YlLaDimissionDao ylLaDimissionDao;
    @Override
    public boolean audit(List<String> list){
        try {
            for (String i :list){
                UpdateWrapper<YlLaDimissionEntity> updateWrapper = new UpdateWrapper<>();
                updateWrapper.eq("agent_code",i);
                YlLaDimissionEntity ylLaDimissionEntity = ylLaDimissionDao.selectOne(updateWrapper);
                if(ylLaDimissionEntity==null) return false;
                ylLaDimissionEntity.setDepartState("2");
                ylLaDimissionDao.update(ylLaDimissionEntity,updateWrapper);
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}

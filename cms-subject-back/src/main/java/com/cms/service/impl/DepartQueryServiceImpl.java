package com.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cms.dao.DepartQueryDao;
import com.cms.pojo.DepartQueryReturnPojo;
import com.cms.service.DepartQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.Query;
import java.text.ParseException;

@Service
public class DepartQueryServiceImpl extends ServiceImpl<DepartQueryDao, DepartQueryReturnPojo> implements DepartQueryService {
    @Autowired
    DepartQueryDao departQueryDao;
    @Override
    public DepartQueryReturnPojo departQuery(String agentCode) throws ParseException{
        if (agentCode.equals("")){
            return null;
        }
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("t1.agent_code",agentCode);
        DepartQueryReturnPojo dqrp = departQueryDao.departQuery(wrapper);
        return dqrp;
    }
}

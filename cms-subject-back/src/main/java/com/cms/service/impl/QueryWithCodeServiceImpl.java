package com.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cms.dao.QueryWithCodeDao;
import com.cms.pojo.QueryWithCodeReturn;
import com.cms.service.QueryWithCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public class QueryWithCodeServiceImpl extends ServiceImpl<QueryWithCodeDao, QueryWithCodeReturn> implements QueryWithCodeService {
    @Autowired
    QueryWithCodeDao queryWithCodeDao;
    @Override
    public QueryWithCodeReturn queryInfo(String agentCode) throws ParseException{
        if (agentCode.equals("")||agentCode==null){
            return null;
        }
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("t1.agent_code",agentCode);
        QueryWithCodeReturn qwcr = queryWithCodeDao.queryInfo(wrapper);
        return qwcr;
    }

}

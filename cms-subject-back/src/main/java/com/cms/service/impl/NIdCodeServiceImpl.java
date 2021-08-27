
package com.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cms.dao.LdCodeDao;
import com.cms.entity.LdCodeEntity;
import com.cms.pojo.IdCodePojo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.cms.service.NIdCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NIdCodeServiceImpl extends ServiceImpl<LdCodeDao, LdCodeEntity> implements NIdCodeService {
    public List<String> idcode(IdCodePojo idCodePojo) {
        QueryWrapper<LdCodeEntity> qw = new QueryWrapper();
        qw.eq("code_type", idCodePojo.getCodeType());
        List<LdCodeEntity> list = this.baseMapper.selectList(qw);

        List<String> listMap = new ArrayList();
        Iterator var5 = list.iterator();

        while(var5.hasNext()) {
            LdCodeEntity ldCodeEntity = (LdCodeEntity)var5.next();
            String str;
            String code=ldCodeEntity.getCode();
            String name=ldCodeEntity.getCodeName();
            str=code+"_"+name;
            listMap.add(str);
        }

        return listMap;
    }
}

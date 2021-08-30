package com.cms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cms.dao.LdCodeDao;
import com.cms.entity.LdCodeEntity;
import com.cms.service.IdCodeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class IdCodeServiceImpl extends ServiceImpl<LdCodeDao, LdCodeEntity> implements IdCodeService {
    @Override
    public Map<String, Map<String, String>> getResource() {
        List<LdCodeEntity> list = this.baseMapper.selectList(null);
        Map<String,Map<String,String>> bigMap=new HashMap<>();

        List<String> typelist=new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            LdCodeEntity ldCodeEntity=list.get(i);
            if (typelist.contains(ldCodeEntity.getCodeType())==false){
                //不包含的情况
                typelist.add(ldCodeEntity.getCodeType());
                Map<String,String> map=new HashMap<>();
                map.put(ldCodeEntity.getCode(), ldCodeEntity.getCodeName());
                bigMap.put(ldCodeEntity.getCodeType(),map);
            }{
                Map<String, String> map = bigMap.get(ldCodeEntity.getCodeType());
                map.put(ldCodeEntity.getCode(), ldCodeEntity.getCodeName());
                bigMap.put(ldCodeEntity.getCodeType(),map);
            }
        }
      return bigMap;
    }

}

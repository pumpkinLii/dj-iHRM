package com.cms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cms.dao.IdCodeDao;
import com.cms.entity.LdCodeEntity;
import com.cms.service.IdCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class IdCodeServiceImpl extends ServiceImpl<IdCodeDao, LdCodeEntity> implements IdCodeService {
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
//    @Override
//    public Map<String, Map<String, String>> getResource() {
//        Map<String,Map<String,String>> bigMap=new HashMap<>();
//        List<LdCodeEntity> list = this.baseMapper.selectList(null);
//        String temp="null";
//        int code=0;
//        Map<String,String> litmap=new HashMap<>();
//        for (int i = 0; i < list.size(); i++) {
//            LdCodeEntity ldCodeEntity=list.get(i);
//            if (code==0){
//                litmap.put(ldCodeEntity.getCode(),ldCodeEntity.getCodeName());
//                bigMap.put(ldCodeEntity.getCodeType(),litmap);
//            }else if (temp==ldCodeEntity.getCodeType()){
//                litmap.put(ldCodeEntity.getCode(),ldCodeEntity.getCodeName());
//                bigMap.put(ldCodeEntity.getCodeType(),litmap);
//            }else {
//                //出现了类型不同的情况了 这时候需要进行新设置值
//                litmap=new HashMap<>();
//                litmap.put(ldCodeEntity.getCode(),ldCodeEntity.getCodeName());
//                bigMap.put(ldCodeEntity.getCodeType(),litmap);
//            }
//            temp=ldCodeEntity.getCodeType();
//            code++;
//            //每次以后替换中间值
//            //获取中间值 与上一次进行区分 用于添加不同的Map
//        }
//        return bigMap;
//    }

}

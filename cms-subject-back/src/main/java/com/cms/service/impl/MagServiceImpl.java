package com.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cms.dao.MagDao;
import com.cms.entity.LdComNewEntity;
import com.cms.service.MagService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 1 * @Author: zhangYiLong
 * 2 * @Date: 2021/8/4 9:24
 * 3 * @ClassName : MagServiceImpl
 */
@Service
public class MagServiceImpl extends ServiceImpl<MagDao, LdComNewEntity> implements MagService {


    @Override
    public List<List> SearchMap() {
        List<List> biglist=new ArrayList<>();
        List<LdComNewEntity> ldComNewEntities = this.baseMapper.selectList(null);
        List list1=new ArrayList();
        List list2=new ArrayList();
        List list3=new ArrayList();
        List list4=new ArrayList();
        List totallist=new ArrayList();
        for (int i = 0; i < ldComNewEntities.size(); i++) {
            //对于不同级别的数据进行分级
            LdComNewEntity ldComNewEntity = ldComNewEntities.get(i);
            String grade=ldComNewEntity.getComGrade();
            Map<String,String> map=new HashMap<>();
            map.put("label",ldComNewEntity.getYlName());
            map.put("value",ldComNewEntity.getComCode());
            if ("02".equals(ldComNewEntity.getComGrade())){
                //
                list2.add(map);
            }else if ("03".equals(grade)){
                list3.add(map);
            }else if ("04".equals(grade)){
                list4.add(map);
            }else if ("01".equals(grade)) {
                list1.add(map);
            }
            totallist.add(map);
        }
        biglist.add(list1);
        biglist.add(list2);
        biglist.add(list3);
        biglist.add(list4);
        biglist.add(totallist);
        return biglist;
    }
}

package com.cms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cms.dao.LaAgentGradeDao;
import com.cms.entity.LaAgentGradeEntity;
import com.cms.service.LaAgentGradeService;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LaAgentGradeServiceImpl extends ServiceImpl<LaAgentGradeDao, LaAgentGradeEntity> implements LaAgentGradeService {
    @Override
    public List method() {
        List<LaAgentGradeEntity> laAgentGradeEntities = this.baseMapper.selectList(null);
        List result=new ArrayList();
        for (int i = 0; i < laAgentGradeEntities.size(); i++) {
            Map map=new HashMap();
            map.put("gradename",laAgentGradeEntities.get(i).getGradeName());
            map.put("gradecode",laAgentGradeEntities.get(i).getGradeCode());
            result.add(map);
        }
        return result;
    }
}

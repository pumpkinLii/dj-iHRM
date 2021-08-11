package com.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cms.dao.EmpDao;
import com.cms.entity.LaAgentGradeEntity;
import com.cms.service.EmpService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 1 * @Author: zhangYiLong
 * 2 * @Date: 2021/8/2 20:56
 * 3 * @ClassName : EmpServicelmpl
 */
@Service
public class EmpServiceImpl extends ServiceImpl<EmpDao, LaAgentGradeEntity> implements EmpService {
    @Override
    public List<Map<String, String>> SearchM() {
        List list = new ArrayList();
        QueryWrapper<LaAgentGradeEntity> qw = new QueryWrapper<>();
        //去看咱们qq群
        qw.eq("grade_series","1");
        List<LaAgentGradeEntity> laAgentGradeEntities = this.baseMapper.selectList(qw);
        for (int i = 0; i < laAgentGradeEntities.size(); i++) {
            Map map = new HashMap();
            map.put("value",laAgentGradeEntities.get(i).getGradeCode());
            map.put("label",laAgentGradeEntities.get(i).getGradeName());
            list.add(map);
        }
        return list;
    }


    @Override
    public List<Map<String,String>> SearchG() {
        List list = new ArrayList();
        QueryWrapper<LaAgentGradeEntity> qw = new QueryWrapper<>();
        qw.eq("grade_series","0");
        List<LaAgentGradeEntity> laAgentGradeEntities = this.baseMapper.selectList(qw);
        for (int i = 0; i < laAgentGradeEntities.size(); i++) {
            Map map = new HashMap();
            map.put("value",laAgentGradeEntities.get(i).getGradeCode());
            map.put("label",laAgentGradeEntities.get(i).getGradeName());
            list.add(map);
        }
        return  list;
    }
}

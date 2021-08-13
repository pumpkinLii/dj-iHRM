package com.cms.common;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cms.entity.LdComNewEntity;
import com.cms.service.impl.MagServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ComnewSon {
    @Autowired
    MagServiceImpl magService;

    public List<Map<String, String>> getSon(String fatherComcode) {
        //Map放三个值 name code
        List<Map<String, String>> result = new ArrayList();
        //获取当前管理机构的下属所有四级机构列表的编码
        if (fatherComcode.length() == 2) {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("com_grade", "04");
            List<LdComNewEntity> ldComNewEntities = this.magService.getBaseMapper().selectList(queryWrapper);
            for (int i = 0; i < ldComNewEntities.size(); i++) {
                Map map = new HashMap();
                map.put("name", ldComNewEntities.get(i).getName());
                map.put("comcode", ldComNewEntities.get(i).getComCode());
                result.add(map);
            }
            return result;
        } else if (fatherComcode.length() == 8) {
            //就是说传来的是四级管理机构
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("com_code", fatherComcode);
            LdComNewEntity ldComNewEntity = this.magService.getBaseMapper().selectOne(queryWrapper);
            Map map = new HashMap();
            map.put("name", ldComNewEntity.getName());
            map.put("comcode", fatherComcode);
            result.add(map);
            return result;

        } else if (fatherComcode.length() == 6) {
            //这种情况是三级管理机构
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("up_com_code", fatherComcode);
            List<LdComNewEntity> list = this.magService.getBaseMapper().selectList(queryWrapper);
            for (int i = 0; i < list.size(); i++) {
                Map map = new HashMap();
                map.put("name", list.get(i).getName());
                map.put("comcode", list.get(i).getComCode());
                result.add(map);
            }
            return result;
        } else if (fatherComcode.length() == 4) {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("up_com_code", fatherComcode);
            List<LdComNewEntity> list = this.magService.getBaseMapper().selectList(queryWrapper);
            //获取三级机构的结果集了 861101
            for (int i = 0; i < list.size(); i++) {
                //三级选四级
                QueryWrapper queryWrapperson = new QueryWrapper();
                queryWrapperson.eq("up_com_code", list.get(i).getComCode());
                List<LdComNewEntity> listson = this.magService.getBaseMapper().selectList(queryWrapperson);
                for (int i1 = 0; i1 < listson.size(); i1++) {
                    Map map = new HashMap();
                    map.put("name", listson.get(i1).getName());
                    map.put("comcode", listson.get(i1).getComCode());
                    result.add(map);
                }
            }
            return result;
        } else {
            return new ArrayList<>();
        }
    }

    public Map getFatherManageCom(String ManageComcode) {
        //获取这个机构的父机构的编码
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("com_code", ManageComcode);
        Map map = new HashMap();
        LdComNewEntity ldComNewEntity = magService.getBaseMapper().selectOne(queryWrapper);
        if (ldComNewEntity == null) {
            return null;
        } else {
            map.put("comcode", ldComNewEntity.getUpComCode());//返回父机构的代码
            QueryWrapper q=new QueryWrapper();
            q.eq("com_code",ldComNewEntity.getUpComCode());
            LdComNewEntity ldComNewEntity1 = magService.getBaseMapper().selectOne(q);
            map.put("name", ldComNewEntity1.getName());
            return map;
        }
    }

    public List<Map> getSonManageCom(String ManageComcode) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("up_com_code", ManageComcode);//这个机构码是
        List re = new ArrayList();
        List<LdComNewEntity> list = this.magService.getBaseMapper().selectList(queryWrapper);//获取子集
        if (list.size() == 0) {
            return null;
        } else {
            for (int i = 0; i < list.size(); i++) {
                Map map=new HashMap();
                map.put("comcode",list.get(i).getComCode());
                map.put("name",list.get(i).getName());
                re.add(map);
            }
            return re;
        }
    }
    public List<Map> getHighManageCom(){
        //返回最高级的下拉列表的集合
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("com_grade","01");
        List<LdComNewEntity> list = magService.getBaseMapper().selectList(queryWrapper);
        List result=new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            Map map=new HashMap();
            map.put("name",list.get(i).getName());
            map.put("comcode",list.get(i).getComCode());
            result.add(map);
        }
        return result;
    }
}

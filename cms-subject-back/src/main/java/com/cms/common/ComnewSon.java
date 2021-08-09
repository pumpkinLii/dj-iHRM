package com.cms.common;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cms.entity.LdComNewEntity;
import com.cms.service.impl.MagServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ComnewSon {
    @Autowired
    MagServiceImpl magService;
    public  List<String> getSon(String fatherComcode){
        List result=new ArrayList();
        //获取当前管理机构的下属所有四级机构列表的编码
        if (fatherComcode.length()==2){
            QueryWrapper queryWrapper=new QueryWrapper();
            queryWrapper.eq("com_grade","04");
            List<LdComNewEntity> ldComNewEntities = this.magService.getBaseMapper().selectList(queryWrapper);
            for (int i = 0; i < ldComNewEntities.size(); i++) {
                result.add(ldComNewEntities.get(i).getComCode());
            }
            return result;
        }else if (fatherComcode.length()==8){
            //就是说传来的是四级管理机构
            result.add(fatherComcode);
            return result;

        }else if (fatherComcode.length()==6){
            //这种情况是三级管理机构
            QueryWrapper queryWrapper=new QueryWrapper();
            queryWrapper.eq("up_com_code",fatherComcode);
            List<LdComNewEntity> list = this.magService.getBaseMapper().selectList(queryWrapper);
            for (int i = 0; i < list.size(); i++) {
                result.add(list.get(i).getComCode());
            }
            return result;
        }else if (fatherComcode.length()==4){
            QueryWrapper queryWrapper=new QueryWrapper();
            queryWrapper.eq("up_com_code",fatherComcode);
            List<LdComNewEntity> list = this.magService.getBaseMapper().selectList(queryWrapper);
            //获取三级机构的结果集了
            for (int i = 0; i < list.size(); i++) {
                //三级选四级
                QueryWrapper queryWrapperson=new QueryWrapper();
                queryWrapperson.eq("up_com_code",list.get(i).getComCode());
                List<LdComNewEntity> listson = this.magService.getBaseMapper().selectList(queryWrapperson);
                for (int i1 = 0; i1 < listson.size(); i1++) {
                    result.add(listson.get(i1).getComCode());
                }
            }
            return result;
        }else {
            return new ArrayList<>();
        }
    }

    public String getFatherManageCom(String ManageComcode){
        //获取这个机构的父机构的编码
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("com_code",ManageComcode);
        LdComNewEntity ldComNewEntity=magService.getBaseMapper().selectOne(queryWrapper);
        if (ldComNewEntity==null){
            return null;

        }else {
            return ldComNewEntity.getUpUpComCode();//返回父机构的代码
             }
    }
    public List getSonManageCom(String ManageComcode){
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("up_com_code",ManageComcode);//这个机构码是
        List re=new ArrayList();
        List<LdComNewEntity> list=this.magService.getBaseMapper().selectList(queryWrapper);//获取子集
        if (list.size()==0){
            return null;
        }else {
            for (int i = 0; i < list.size(); i++) {
                re.add(list.get(i).getComCode());
            }
            return  re;
        }
    }
}

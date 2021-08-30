package com.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cms.dao.TestDao;
import com.cms.dao.UserDao;
import com.cms.dao.YlUserInfoDao;
import com.cms.entity.YlUserInfoEntity;
import com.cms.pojo.TestPojo;
import com.cms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl extends ServiceImpl<YlUserInfoDao, YlUserInfoEntity> implements UserService {

    //自动装填好了


    @Override
    public  boolean login(String userId, String userPassword) {
        QueryWrapper<YlUserInfoEntity> sectionQueryWrapper = new QueryWrapper<>();
        sectionQueryWrapper.eq("user_id",userId);
        sectionQueryWrapper.eq("user_password",userPassword);
        List<YlUserInfoEntity> pbListBlacks =this.baseMapper.selectList(sectionQueryWrapper);
        if (pbListBlacks==null){
            return false;
        }else if (pbListBlacks.size()!=0){
            return true;
        }else {
            return false;
        }
    }
}

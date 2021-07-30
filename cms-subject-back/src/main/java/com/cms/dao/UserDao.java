package com.cms.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cms.entity.YlUserInfoEntity;
import com.cms.pojo.TestPojo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserDao extends BaseMapper<YlUserInfoEntity> {
        //继承了这个BaseMapper 就可以实现 四个功能了
        //这里可能需要进行调试
}

package com.cms.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cms.entity.YlUserInfoEntity;
import com.cms.pojo.TestPojo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author :zhanhaoze
 * @description :
 * @create :2021-07-22
 */
public interface TestDao extends BaseMapper<YlUserInfoEntity> {
    List<TestPojo> getList(Page<TestPojo> pages, @Param(Constants.WRAPPER) QueryWrapper<YlUserInfoEntity> qw);
}

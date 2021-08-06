package com.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cms.dao.TestDao;
import com.cms.entity.YlUserInfoEntity;
import com.cms.pojo.TestPojo;
import com.cms.service.TestService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author :zhanhaoze
 * @description :
 * @create :2021-07-22
 */
@Slf4j
@Service
public class TestServiceImpl extends ServiceImpl<TestDao, YlUserInfoEntity> implements TestService {

    @Override
    public PageInfo<TestPojo> getList(TestPojo testPojo, int page, int limit) {
        PageHelper.startPage(limit, page);
        QueryWrapper<YlUserInfoEntity> qw = new QueryWrapper<>();
        qw.eq("operator", testPojo.getUserId());
        List<TestPojo> list = this.baseMapper.getList(qw);
        PageInfo<TestPojo> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}

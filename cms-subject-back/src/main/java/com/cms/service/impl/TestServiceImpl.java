package com.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cms.dao.TestDao;
import com.cms.entity.UserInfo;
import com.cms.pojo.TestPojo;
import com.cms.service.TestService;
import com.cms.util.PageUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author :zhanhaoze
 * @description :
 * @create :2021-07-22
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestDao, UserInfo> implements TestService {

    @Override
    public PageUtils getList(TestPojo testPojo, int page, int limit) {
        Page<TestPojo> pages = new Page<>(page, limit);
        QueryWrapper<UserInfo> qw = new QueryWrapper<>();
        qw.eq("partner", testPojo.getPartner());
        List<TestPojo> list = this.baseMapper.getList(pages, qw);
        pages.setRecords(list);
        return new PageUtils(pages);
    }
}

package com.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cms.dao.AgentCountDao;
import com.cms.dao.ComCodeDao;
import com.cms.dao.RYlLaBranchGroupDao;
import com.cms.entity.LdCodeEntity;
import com.cms.entity.LdComNewEntity;
import com.cms.entity.YlLaBranchGroupEntity;
import com.cms.pojo.RYlLaBranchGrouReturn;
import com.cms.pojo.RYlLaBranchGroupPojo;
import com.cms.service.RYlLaBranchGroupService;
import com.cms.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RYlLaBranchGroupServiceImpl extends ServiceImpl<RYlLaBranchGroupDao, YlLaBranchGroupEntity> implements RYlLaBranchGroupService {


    //诸如一下
    @Autowired
    AgentCountDao agentCountDao;

    @Autowired
    ComCodeDao comCodeDao;

    @Override
    public List Retrieve_group(RYlLaBranchGroupPojo r_ylLaBranchGroupPojo) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        QueryWrapper<YlLaBranchGroupEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(!StringUtils.isEmpty(r_ylLaBranchGroupPojo.getManageComCode()), "manage_com", r_ylLaBranchGroupPojo.getManageComCode());
        queryWrapper.eq(!StringUtils.isEmpty(r_ylLaBranchGroupPojo.getBranchLevel()), "branch_level", r_ylLaBranchGroupPojo.getBranchLevel());
        queryWrapper.eq(!StringUtils.isEmpty(r_ylLaBranchGroupPojo.getBranchAttr()), "branch_attr", r_ylLaBranchGroupPojo.getBranchAttr());
        queryWrapper.eq(!StringUtils.isEmpty(r_ylLaBranchGroupPojo.getBranchManager()), "branch_manager", r_ylLaBranchGroupPojo.getBranchManager());
        queryWrapper.eq(!StringUtils.isEmpty(r_ylLaBranchGroupPojo.getBranchManagerName()), "branch_manager_name", r_ylLaBranchGroupPojo.getBranchManagerName());
        queryWrapper.eq(!StringUtils.isEmpty(r_ylLaBranchGroupPojo.getBranchManagerPhone()), "branch_manager_phone", r_ylLaBranchGroupPojo.getBranchManagerPhone());
        queryWrapper.eq(!StringUtils.isEmpty(r_ylLaBranchGroupPojo.getBranchEffDate()), "branch_eff_date", r_ylLaBranchGroupPojo.getBranchEffDate());
        queryWrapper.eq(!StringUtils.isEmpty(r_ylLaBranchGroupPojo.getBranchStatus()), "branch_status", r_ylLaBranchGroupPojo.getBranchStatus());
        List<RYlLaBranchGrouReturn> Return_list = new ArrayList<>();
        List<YlLaBranchGroupEntity> list = this.baseMapper.selectList(queryWrapper);
        if (list.size()==0){
            return null;
        }
        QueryWrapper queryWrapper1 = new QueryWrapper();
        QueryWrapper queryWrapper2 = new QueryWrapper();
        for (int i = 0; i < list.size(); i++) {
            RYlLaBranchGrouReturn r_ylLaBranchGrou_return = new RYlLaBranchGrouReturn();
            r_ylLaBranchGrou_return.setBranchAttr(list.get(i).getBranchAttr());
            r_ylLaBranchGrou_return.setBranchLevel(list.get(i).getBranchLevel());
            r_ylLaBranchGrou_return.setBranchManager(list.get(i).getBranchManager());
            r_ylLaBranchGrou_return.setBranchStatus(list.get(i).getBranchStatus());
            /*branchstatus,N,否,
              branchstatus,Y,是,
            */
            if (list.get(i).getBranchStatus().equals("N")) {
                r_ylLaBranchGrou_return.setBranchStatusName("否");
            } else if (list.get(i).getBranchStatus().equals("Y")) {
                r_ylLaBranchGrou_return.setBranchStatusName("是");
            } else {
                r_ylLaBranchGrou_return.setBranchStatusName("团队状态码需设置");
            }
            r_ylLaBranchGrou_return.setBranchManagerName(list.get(i).getBranchManagerName());
            if (list.get(i).getBranchEffDate() != null) {
                r_ylLaBranchGrou_return.setBranchEffDate(simpleDateFormat.format(list.get(i).getBranchEffDate()));
            } else {
                r_ylLaBranchGrou_return.setBranchEffDate("");
            }
            queryWrapper1.eq("agent_group", list.get(i).getBranchAttr());
            //在人员表中 获取与团队名称相同的人的
            List list1 = agentCountDao.selectList(queryWrapper1);
            String number = String.valueOf(list1.size());

            r_ylLaBranchGrou_return.setAgentCount(number);//表格只读项	团队下当前实时统计在职代理人个数
            if (list.get(i).getBranchTerminateEffDate() != null) {
                r_ylLaBranchGrou_return.setBranchTerminateEffDate(simpleDateFormat.format(list.get(i).getBranchTerminateEffDate()));
            } else {
                r_ylLaBranchGrou_return.setBranchTerminateEffDate("");
            }

            //根据管理机构 获取代码
            /*86,,大家人寿总公司测试2,86
            8611,,大家人寿北京分公司,8611
            861101,,北京分公司,861100
            86110101,,东城区服务部,86110000
            */
            //去查那个表
            String com = list.get(i).getManageCom();//这个是代码
            queryWrapper2.eq("com_code", com);
            LdComNewEntity ldComNewEntity = comCodeDao.selectOne(queryWrapper2);
            if (ldComNewEntity != null) {

                r_ylLaBranchGrou_return.setManageCom(ldComNewEntity.getName());//这个是名子
            } else {
                r_ylLaBranchGrou_return.setManageComCode("数据库无代码");
            }
            r_ylLaBranchGrou_return.setManageComCode(list.get(i).getManageCom());
            r_ylLaBranchGrou_return.setBranchAttr(list.get(i).getBranchAttr());
            r_ylLaBranchGrou_return.setBranchName(list.get(i).getBranchName());
            r_ylLaBranchGrou_return.setChatName(list.get(i).getChatName());
            r_ylLaBranchGrou_return.setBranchManagerName(list.get(i).getBranchManagerName());
            r_ylLaBranchGrou_return.setBranchManagerPhone(list.get(i).getBranchManagerPhone());
            r_ylLaBranchGrou_return.setOperator(list.get(i).getOperator());
            Return_list.add(r_ylLaBranchGrou_return);
        }
        return Return_list;
    }
}

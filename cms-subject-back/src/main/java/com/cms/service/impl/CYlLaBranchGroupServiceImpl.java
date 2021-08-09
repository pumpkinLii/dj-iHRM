package com.cms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.cms.dao.CYlLaBranchGroupDao;
import com.cms.entity.YlLaBranchGroupEntity;
import com.cms.pojo.CYlLaBranchGroupPojo;
import com.cms.service.CYlLaBranchGroupService;
import com.cms.util.R;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class CYlLaBranchGroupServiceImpl extends ServiceImpl<CYlLaBranchGroupDao,YlLaBranchGroupEntity> implements CYlLaBranchGroupService {
    @Autowired
    AgentGroupImpl agentGroup;




    @Override
    public R creat(CYlLaBranchGroupPojo c_ylLaBranchGroupPojo) throws ParseException {

        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format1 = simpleDateFormat.format(new Date());
        String[] s = format1.split(" ");
        System.out.println(c_ylLaBranchGroupPojo);
        YlLaBranchGroupEntity ybge=new YlLaBranchGroupEntity();
        ybge.setManageCom(c_ylLaBranchGroupPojo.getManageComCode4());//管理机构代码
        ybge.setBranchLevel("1");//团队等级 默认为1
        ybge.setBranchName(c_ylLaBranchGroupPojo.getBranchName());//团队名
        SimpleDateFormat xx=new SimpleDateFormat("yyyy-MM-dd");
        String format =c_ylLaBranchGroupPojo.getBranchEffDate();
        ybge.setBranchEffDate(xx.parse(format));
        ybge.setMakeDate(xx.parse(format));
        ybge.setMakeTime(s[1]);
        ybge.setModifyDate(xx.parse(format));
        ybge.setModifyTime(s[1]);
        ybge.setBranchStatus(c_ylLaBranchGroupPojo.getBranchStatus());
        ybge.setChatName(c_ylLaBranchGroupPojo.getChatName());//群聊名称
        if (StringUtils.isEmpty(c_ylLaBranchGroupPojo.getBranchManager())==false){
            ybge.setBranchManager(c_ylLaBranchGroupPojo.getBranchManager());//负责人代码
        }
        if (StringUtils.isEmpty(c_ylLaBranchGroupPojo.getBranchManagerName())==false){
            ybge.setBranchManagerName(c_ylLaBranchGroupPojo.getBranchManagerName());//负责人代码
        }
        if (StringUtils.isEmpty(c_ylLaBranchGroupPojo.getBranchManagerPhone())==false){
            ybge.setBranchManagerPhone(c_ylLaBranchGroupPojo.getBranchManagerPhone());//负责人代码
        }
        ybge.setBranchTerminateEffDate(null);//停业时间 前端给不了 而且不为空
        //必须去设置的值 我自己设置
        ybge.setOperator(c_ylLaBranchGroupPojo.getOperator());
        ybge.setAgentGroup(String.valueOf(agentGroup.getAgentGroup()));//组织内码 需要我调用自己写好的方法去创建一个组织内码
        ybge.setBranchType("4");//码表中养老为4
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append("T").append(c_ylLaBranchGroupPojo.getManageComCode4().substring(2,6)).append(agentGroup.teamCode());
        ybge.setBranchAttr(stringBuffer.toString());//根据下拉机构去创建团队的组指编码？ ok 在哪个管理机构下面 就创建在哪里
        ybge.setBranchStatus(c_ylLaBranchGroupPojo.getBranchStatus());
        /*
        branchtype,1,个险,,sys
        branchtype,3,银保,,sys
        branchtype,4,养老,,sys
        */
        int insert = this.baseMapper.insert(ybge);//将这个数据条添加到数据库中
        SqlSession sqlSession = this.sqlSessionBatch();
        sqlSession.commit();
        if (insert==1){
           return new R();//默认构造方法是成功的
        }else {
            return R.error();//默认的是失败的信息
        }
    }
}

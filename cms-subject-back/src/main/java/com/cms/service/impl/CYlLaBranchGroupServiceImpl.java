package com.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.cms.dao.YlLaBranchGroupDao;
import com.cms.entity.YlLaAgentEntity;
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
import java.util.List;

@Service
public class CYlLaBranchGroupServiceImpl extends ServiceImpl<YlLaBranchGroupDao,YlLaBranchGroupEntity> implements CYlLaBranchGroupService {
    @Autowired
    AgentGroupImpl agentGroup;

    //注入人员表的实现类
    @Autowired
    IdCheckServiceImpl idCheckService;
    //用于查询主管是否存在

    @Override
    public R creat(CYlLaBranchGroupPojo c_ylLaBranchGroupPojo) throws ParseException {
        //还要做到修改人员表里 所给主管的信息 如果人员表里 该主管不存在 就加入数据 该人员不存在 请先在人员表先添加数据
        //返回R.error("请在人员表添加该员工再进行操作") 然后就是修改人员表中这个主管所在的团队 需要绑定的是agent_group
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

        //查询人员表中主管是否存在 前端已经做好回显了
        String agentGroup = this.agentGroup.getAgentGroup();
        if (StringUtils.isEmpty(c_ylLaBranchGroupPojo.getBranchManager())==false){
            List<YlLaBranchGroupEntity> laBranchGroupEntities = this.baseMapper.selectList(null);
            ybge.setBranchManager(c_ylLaBranchGroupPojo.getBranchManager());//负责人代码
            ybge.setBranchManagerName(c_ylLaBranchGroupPojo.getBranchManagerName());
            ybge.setBranchManagerPhone(c_ylLaBranchGroupPojo.getBranchManagerPhone());
            QueryWrapper queryWrapper=new QueryWrapper();
            queryWrapper.eq("agent_code",c_ylLaBranchGroupPojo.getBranchManager());
            YlLaAgentEntity ylLaAgentEntity = idCheckService.getBaseMapper().selectOne(queryWrapper);
            if (ylLaAgentEntity==null){
                return R.ok().put("msg","请先员工数据添加数据到人员表再操作").put("code",501);
            }
            if (ylLaAgentEntity.getAgentGrade().substring(0,2).equals("MA")==false){
                return R.ok().put("msg","该员工不是主管,不可任命团队主管").put("code",501);
            }
            ylLaAgentEntity.setAgentGroup(agentGroup);
            idCheckService.getBaseMapper().update(ylLaAgentEntity,queryWrapper);
            System.out.println("-------------------------------------人员表数据已经更新-----------------------------------");
        }
        ybge.setBranchTerminateEffDate(null);//停业时间 前端给不了 而且不为空
        //必须去设置的值 我自己设置
        ybge.setOperator(c_ylLaBranchGroupPojo.getOperator());
        ybge.setAgentGroup(String.valueOf(agentGroup));//组织内码 需要我调用自己写好的方法去创建一个组织内码
        ybge.setBranchType("4");//码表中养老为4
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append("T").append(c_ylLaBranchGroupPojo.getManageComCode4().substring(2,6)).append(this.agentGroup.teamCode());
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

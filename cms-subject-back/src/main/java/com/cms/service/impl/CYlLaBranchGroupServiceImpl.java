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

        /*
        * 在数据正确的情况下
        * 哪些数据是我从前端获取的 哪些数据是我需要去自己添加的
        * （数据上面加上*说明为必须要填入的数据）
        * 需要从前端获取的数据：
        * {必须要设置的选项：
        *manageComCode4	管理机构 ---》managerCom 管理机构 需要接受的是Code 由前端给我数据
         branchLevel	组织级别 ---》branchLevel
         branchName	团队名称
         branchEffDate	成立时间 ---》brancheffDate makedate maketime modifydate modifytime
         branchStatus	停业标志
         operator	操作员
         chatName	群聊名称
        * 不是必须要设置的选项：
        *branchManager	负责人代码
         branchManagerName	负责人姓名
         branchManagerPhone	负责人手机号
         branchTerminateEffDate	停业日期(这个数据前端无法传递给我 先设置为空)
        * }
        * 需要自己进行设置的数据
        * setAgentGroup("10");组织内码 需要我调用自己写好的方法去创建一个组织内码
        * setBranchType("4");码表中养老为4
        * setBranchAttr("");根据下拉机构去 创建团队的组指编码？ ok 在哪个管理机构下面 就创建在哪里
        * setOperator("张三")//这里要获取用户的登陆信息？然后加进去代码
        * */
        //如何判断 没有进行赋值 都是null 对吧？
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format1 = simpleDateFormat.format(new Date());
        String[] s = format1.split(" ");
        System.out.println(c_ylLaBranchGroupPojo);
        //下列数据不需要判空 必须操作
        YlLaBranchGroupEntity ybge=new YlLaBranchGroupEntity();
        ybge.setManageCom(c_ylLaBranchGroupPojo.getManageComCode4());//管理机构代码
        ybge.setBranchLevel(c_ylLaBranchGroupPojo.getBranchLevel());//团队等级
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
        //不必要的值


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

package com.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cms.common.SelectAgentGroupInfo;
import com.cms.dao.YlLaAgentManoeuvreDao;
import com.cms.entity.YlLaAgentEntity;
import com.cms.entity.YlLaAgentManoeuvreEntity;
import com.cms.entity.YlLaBranchGroupEntity;
import com.cms.pojo.YlLaAgentChangePojo;
import com.cms.service.YlLaAgentManoeuvreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 此类用于人员异动(更改团队),提供一个public方法 buildNewAgentManoeuvre(List<YlLaAgentEntity> oldAgentInfo, YlLaAgentChangePojo ylLaAgentChangePojo)
 * */
@Service
@Slf4j
public class YlLaAgentManoeuvreServiceImpl extends ServiceImpl<YlLaAgentManoeuvreDao, YlLaAgentManoeuvreEntity> implements YlLaAgentManoeuvreService {
    @Autowired
    private SelectAgentGroupInfo selectAgentGroupInfo;

    /**
     * 此方法用于人员异动(团队更改)时在人员异动表(yl_la_agent_manoeuvre)插入数据，传入旧的员工信息的集合和YlLaAgentChangePojo(包含更改新消息)
     * 插入成功返回true，插入失败返回false
     * 此方法专用于YlLaAgentInfoChangeServiceImpl中使用，是YlLaAgentInfoChangeServiceImpl的一部分
     * */
    @Override
    public boolean buildNewAgentManoeuvre(List<YlLaAgentEntity> oldAgentInfo, YlLaAgentChangePojo ylLaAgentChangePojo) {
        return this.insertNewAgentManeuvre(oldAgentInfo,ylLaAgentChangePojo);
    }

    /**
     *此私有方法用于yl_la_agent_manoeuvre插入异动信息，传入一个List<YlLaAgentEntity>对象，成功返回true，失败返回false
     * */
    private boolean insertNewAgentManeuvre(List<YlLaAgentEntity> oldAgentInfo, YlLaAgentChangePojo ylLaAgentChangePojo){
        int count = 0;
        for (YlLaAgentEntity i:oldAgentInfo) {
            count = count + this.baseMapper.insert(this.buildAgentManoeuvreEntity(i,ylLaAgentChangePojo));
        }
        if(count == oldAgentInfo.size()){
            System.out.println("Mission Success!");
            return true;
        }
        return false;
    }

    /**
     * 信息打包成数据库用的实体，用于在yl_la_agent_manoeuvre插入时使用
     * */
    private YlLaAgentManoeuvreEntity buildAgentManoeuvreEntity(YlLaAgentEntity oldAgentInfo, YlLaAgentChangePojo ylLaAgentChangePojo){
        YlLaAgentManoeuvreEntity ylLaAgentManoeuvreEntity = new YlLaAgentManoeuvreEntity();
        YlLaBranchGroupEntity oldAgentGroupInfo = selectAgentGroupInfo.selectAgentGroup(oldAgentInfo.getAgentGroup());
        YlLaBranchGroupEntity newAgentGroupInfo = selectAgentGroupInfo.selectAgentGroup(ylLaAgentChangePojo.getAgentGroup());
        ylLaAgentManoeuvreEntity.setEdorNo(this.buildNewEdorNo());
        ylLaAgentManoeuvreEntity.setEdorType("01");
        ylLaAgentManoeuvreEntity.setFunctType("03");
        ylLaAgentManoeuvreEntity.setAgentCode(oldAgentInfo.getAgentCode());
        ylLaAgentManoeuvreEntity.setBranchType(oldAgentInfo.getBranchType());
        ylLaAgentManoeuvreEntity.setManoeuvreDate(new Date());
        ylLaAgentManoeuvreEntity.setPreManageCom(oldAgentGroupInfo.getManageCom());
        ylLaAgentManoeuvreEntity.setCurManageCom(ylLaAgentChangePojo.getManageCom());
        ylLaAgentManoeuvreEntity.setPreBranchCode(oldAgentGroupInfo.getAgentGroup());
        ylLaAgentManoeuvreEntity.setCurBranchCode(newAgentGroupInfo.getAgentGroup());
        ylLaAgentManoeuvreEntity.setPreBranchAttr(oldAgentGroupInfo.getBranchAttr());
        ylLaAgentManoeuvreEntity.setCurBranchAttr(newAgentGroupInfo.getBranchAttr());
        ylLaAgentManoeuvreEntity.setPreDepartment(oldAgentGroupInfo.getAgentGroup());
        ylLaAgentManoeuvreEntity.setCurDepartment(newAgentGroupInfo.getAgentGroup());
        ylLaAgentManoeuvreEntity.setPreDepartmentManager(oldAgentGroupInfo.getBranchManager());
        ylLaAgentManoeuvreEntity.setCurDepartmentManager(newAgentGroupInfo.getBranchManager());
        ylLaAgentManoeuvreEntity.setCurAgentGroupName(newAgentGroupInfo.getBranchName());
        ylLaAgentManoeuvreEntity.setOperator(ylLaAgentChangePojo.getOperator());
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");//设置日期格式
        String time = df.format(new Date());//获取String类型的时间
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(ylLaAgentChangePojo.getModifyDate(), pos);
        ylLaAgentManoeuvreEntity.setMakeDate(strtodate);
        ylLaAgentManoeuvreEntity.setMakeTime(time);
        ylLaAgentManoeuvreEntity.setModifyDate(strtodate);
        ylLaAgentManoeuvreEntity.setModifyTime(time);
        return ylLaAgentManoeuvreEntity;
    }

    /**
     * 自动生成转储号码
     * */
    private String buildNewEdorNo(){
        QueryWrapper<YlLaAgentManoeuvreEntity> queryWrapper = new QueryWrapper<>();
        List<YlLaAgentManoeuvreEntity> resultSet = this.baseMapper.selectList(queryWrapper);
        if(resultSet.size() == 0){
            return "YD00000001";
        }
        else{
            YlLaAgentManoeuvreEntity ylLaAgentManoeuvreEntity = resultSet.get(resultSet.size() - 1);
            String newEdorNo;
            newEdorNo = ylLaAgentManoeuvreEntity.getEdorNo();
            newEdorNo = newEdorNo.substring(2);
            int edorNo = Integer.parseInt(newEdorNo) + 1;
            return "YL" + String.format("%08d", edorNo);
        }
    }
}

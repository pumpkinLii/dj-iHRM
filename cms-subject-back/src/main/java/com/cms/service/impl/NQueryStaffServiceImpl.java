package com.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cms.dao.NQueryStaffDao;
import com.cms.pojo.QueryStaffPojo;
import com.cms.pojo.NQueryStaffReturn;
import com.cms.service.NQueryStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class NQueryStaffServiceImpl extends ServiceImpl<NQueryStaffDao, NQueryStaffReturn> implements NQueryStaffService {
    @Autowired
    NQueryStaffDao queryStaffDao;

    //管理机构暂未实现查询，以及养老Y的判断
    public List<NQueryStaffReturn> queryStaff(QueryStaffPojo queryStaffPojo) throws ParseException {
        System.out.println(queryStaffPojo.getStartDate());
        Map<String,Object> map = new HashMap<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        // SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        if (!queryStaffPojo.getAgentCode().equals("")){
            map.put("t1.agent_code",queryStaffPojo.getAgentCode());
        }
        if (!queryStaffPojo.getAgentName().equals("")){
            map.put("t1.agent_name",queryStaffPojo.getAgentName());
        }

        if (!queryStaffPojo.getAgentState().equals("")){
            map.put("t1.agent_state",queryStaffPojo.getAgentState());
        }
        if (!queryStaffPojo.getBranchAttr().equals("")){
            map.put("t1.branch_attr",queryStaffPojo.getBranchAttr());
        }
        if (queryStaffPojo.getStartDate()!=null){
            String sd = dateFormat.format(queryStaffPojo.getStartDate());
            System.out.println(sd);
            Date date1=dateFormat.parse(sd);
            map.put("t1.employ_date",date1);
            //map.put("employ_date",queryStaffPojo.getStartDate());
        }
        if (queryStaffPojo.getEndDate()!=null){
            String ed = dateFormat.format(queryStaffPojo.getEndDate());
            System.out.println(ed);
            Date date2=dateFormat.parse(ed);
            map.put("t1.out_work_date",date2);
        }
        String manageCom = "";
        if(queryStaffPojo.getManageCom4()!=""){
            manageCom=queryStaffPojo.getManageCom4();
            map.put("yl_name",manageCom);
            if (queryStaffPojo.getBranchAttr()!=""){
                map.put("t1.branchAttr",queryStaffPojo.getBranchAttr());
            }
        }else if (queryStaffPojo.getManageCom3()!=""){
            manageCom=queryStaffPojo.getManageCom3();
            map.put("yl_name",manageCom);
        } else if (queryStaffPojo.getManageCom2() != "") {
            manageCom=queryStaffPojo.getManageCom2();
            map.put("yl_name",manageCom);
        }
        /*if (!manageCom.equals("")){
            ComnewSon cs = new ComnewSon();
            ComnewCode cc = new ComnewCode();
            String code = cc.getCode(manageCom);
            List<String> comList = cs.getSon(code);

        }*/
        QueryWrapper<Map<String,Object>> wrapper = new QueryWrapper<>();
        wrapper.allEq(map);
        //List<QueryStaffReturn> list = this.baseMapper.selectByMap(wrapper);
        List<NQueryStaffReturn> list =queryStaffDao.queryStaff(wrapper);
        //List<QueryStaffReturn> list = this.baseMapper.selectByMap(map);
        return list;

        /*
        QueryWrapper<Map<String,Object>> wrapper = new QueryWrapper<>();
        wrapper.allEq(map);
        List<Map<String,Object>> mapList = new ArrayList<>();
        List<YlLaAgentEntity> entityList = this.baseMapper.selectByMap(map);
        for (YlLaAgentEntity y:entityList){
            Map<String,Object> resultMap = new HashMap<>();
            resultMap.put("agentCode", y.getAgentCode());
            resultMap.put("agentName",y.getAgentName());
            resultMap.put("agentState",y.getAgentState());
            resultMap.put("branchAttr",y.getManageCom());
            String employDate = dateFormat.format(y.getEmployDate());
            String endDate = dateFormat.format(y.getOutWorkDate());
            resultMap.put("startDate",employDate);
            resultMap.put("endDate",endDate);
            mapList.add(resultMap);
        }
        return mapList;*/
    }
}

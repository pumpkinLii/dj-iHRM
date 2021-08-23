package com.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cms.dao.QueryStaffDao;
import com.cms.pojo.QueryStaffPojo;
import com.cms.pojo.NQueryStaffReturn;
import com.cms.pojo.QueryStaffReturn;
import com.cms.service.NQueryStaffService;
import com.cms.service.QueryStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class QueryStaffServiceImpl extends ServiceImpl<QueryStaffDao, QueryStaffReturn> implements QueryStaffService {
    @Autowired
    QueryStaffDao queryStaffDao;

    public List<QueryStaffReturn> queryStaff(QueryStaffPojo queryStaffPojo) throws ParseException {
        QueryWrapper<QueryStaffReturn> wrapper = new QueryWrapper<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        wrapper.eq(!StringUtils.isEmpty(queryStaffPojo.getAgentCode()),"t1.agent_code",queryStaffPojo.getAgentCode());
        wrapper.like(!StringUtils.isEmpty(queryStaffPojo.getAgentName()),"t1.agent_name",queryStaffPojo.getAgentName());
        wrapper.eq(!StringUtils.isEmpty(queryStaffPojo.getAgentState()),"t1.agent_state",queryStaffPojo.getAgentState());
        //wrapper.eq(!StringUtils.isEmpty(queryStaffPojo.getBranchAttr()),"t1.branch_attr",queryStaffPojo.getBranchAttr());
        if (queryStaffPojo.getStartDate()!=null){
            String sd = dateFormat.format(queryStaffPojo.getStartDate());
            Date date1=dateFormat.parse(sd);
            wrapper.ge("t1.employ_date",date1);
        }
        if (queryStaffPojo.getEndDate()!=null){
            String ed = dateFormat.format(queryStaffPojo.getEndDate());
            Date date2=dateFormat.parse(ed);
            wrapper.le("t1.employ_date",date2);
        }

        if(queryStaffPojo.getManageCom4()!=""){
            wrapper.eq("t2.com_code",queryStaffPojo.getManageCom4());
            wrapper.eq(!StringUtils.isEmpty(queryStaffPojo.getBranchAttr()),"t1.agent_group",queryStaffPojo.getBranchAttr());
        }else if (queryStaffPojo.getManageCom3()!=""){
            wrapper.likeRight("t2.com_code",queryStaffPojo.getManageCom3());
        } else if (queryStaffPojo.getManageCom2() != "") {
            wrapper.likeRight("t2.com_code",queryStaffPojo.getManageCom2());
        }
        List<QueryStaffReturn> list = queryStaffDao.queryStaff(wrapper);
        return list;




       /* //queryStaffPojo.setManageCom4("86110101");
        QueryWrapper<Map<String,Object>> wrapper = new QueryWrapper<>();
        Map<String,Object> map = new HashMap<>();
        List<QueryStaffReturn> list = new ArrayList<>();
        ComnewCode cnc = new ComnewCode();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
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
            map.put("t2.com_code",manageCom);
            if (queryStaffPojo.getBranchAttr()!=""){
                map.put("t1.branchAttr",queryStaffPojo.getBranchAttr());
            }
        }else if (queryStaffPojo.getManageCom3()!=""){
            System.out.println(queryStaffPojo.getManageCom3());
            List<String> sonList = cnc.getSon(queryStaffPojo.getManageCom3());
            System.out.println(sonList.size());
            if (sonList.size()>0) {
                for (String com4 : sonList) {
                    map.put("t2.com_code", com4);
                    wrapper.allEq(map);
                    List<QueryStaffReturn> list0 = queryStaffDao.queryStaff(wrapper);
                    list.addAll(list0);
                }
                return list;
            }
        } else if (queryStaffPojo.getManageCom2() != "") {
            List<String> sonList = cnc.getSon(queryStaffPojo.getManageCom2());
            for (String com4 : sonList){
                map.put("t2.com_code",com4);
                wrapper.allEq(map);
                List<QueryStaffReturn> list0 =queryStaffDao.queryStaff(wrapper);
                list.addAll(list0);
            }
            return list;
        }
        wrapper.allEq(map);
        list =queryStaffDao.queryStaff(wrapper);
        return list;*/
    }
}

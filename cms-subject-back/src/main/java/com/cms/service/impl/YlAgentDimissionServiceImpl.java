package com.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cms.dao.YlLaDimissionDao;
import com.cms.entity.YlLaDimissionEntity;
import com.cms.pojo.DepartModifyPojo;
import com.cms.service.YlAgentDimissionService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class YlAgentDimissionServiceImpl extends ServiceImpl<YlLaDimissionDao, YlLaDimissionEntity> implements YlAgentDimissionService {
    //离司申请的修改
    @Override
    public String departModify(DepartModifyPojo departModifyPojo) throws ParseException{
        QueryWrapper<YlLaDimissionEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("agent_code",departModifyPojo.getAgentCode());
        YlLaDimissionEntity entity = this.baseMapper.selectOne(queryWrapper);
        String departState = entity.getDepartState();
        //1：判断若为未提交审核或已通过审核，则不能修改
        if (departState.equals("02") || departState.equals("04")){
            return "1";
        }
        UpdateWrapper<YlLaDimissionEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("agent_code",departModifyPojo.getAgentCode());
        if (!StringUtils.isEmpty(departModifyPojo.getDiffDate())) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dateFormat.parse(departModifyPojo.getDiffDate());
            updateWrapper.set("depart_date",date);
        }else {
            //2：判断必填项是否填写
            return "2";
        }
        if (!StringUtils.isEmpty(departModifyPojo.getDiffCause())){
            updateWrapper.set("depart_reason",departModifyPojo.getDiffCause());
        }else {
            return "2";
        }
        updateWrapper.set(!StringUtils.isEmpty(departModifyPojo.getExplain()),"noti",departModifyPojo.getExplain());
        Date modifyDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("HH:mm:ss");
        String modifyDateString = dateFormat.format(modifyDate);
        String modifyTimeString = dateFormat2.format(modifyDate);
        updateWrapper.set("modify_date",modifyDateString);
        updateWrapper.set("modify_time",modifyTimeString);
        this.baseMapper.update(null,updateWrapper);
        //0:修改完成
        return "0";
    }
}

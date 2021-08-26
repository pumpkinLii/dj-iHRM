package com.cms.service;

import com.cms.pojo.ResignationApprovalPojo;
import com.cms.util.R;
import org.springframework.web.bind.annotation.RequestBody;

import java.text.ParseException;

public interface ResignationApprovalService {
    public R approval(@RequestBody ResignationApprovalPojo resignationApprovalPojo,int num) throws ParseException;
}

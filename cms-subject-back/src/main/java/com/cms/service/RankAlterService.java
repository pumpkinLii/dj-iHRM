package com.cms.service;

import com.cms.pojo.GradeQueryPojo;
import com.cms.pojo.GradeQueryReturnPojo;
import com.cms.pojo.RankAlterPojo;

import java.text.ParseException;
import java.util.List;

public interface RankAlterService {
    public  String  rankAlter(RankAlterPojo rankAlterPojo);

    public List<GradeQueryReturnPojo> gradeQuery(GradeQueryPojo gradeQueryPojo) throws ParseException;
}

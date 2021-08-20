package com.cms.service;

import com.cms.pojo.GradeQueryPojo;
import com.cms.pojo.GradeQueryReturnPojo;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.util.List;
@Repository
public interface GradeQueryService {
    public List<GradeQueryReturnPojo> gradeQuery(GradeQueryPojo gradeQueryPojo) throws ParseException;
}

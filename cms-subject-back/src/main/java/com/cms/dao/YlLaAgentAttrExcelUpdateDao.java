package com.cms.dao;

import com.cms.pojo.TablePropertiesPojo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface YlLaAgentAttrExcelUpdateDao {
    @Select("show full columns from yl_la_agent")
    public List<TablePropertiesPojo> getTableProperties();
}

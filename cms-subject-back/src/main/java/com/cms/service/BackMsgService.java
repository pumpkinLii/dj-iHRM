package com.cms.service;

import com.cms.pojo.*;
import com.cms.util.R;

import java.util.List;
import java.util.Map;

public interface BackMsgService {

    public R getgradeteam(GradeTeamPojo gradeTeamPojo);

    //回显团队架构
    public List<Map<String,String>> getGroup(StaffPojo staffPojo);

    //回显团队主管
    public Map<String,String> getManager(GroupPojo groupPojo);

    //陈益轩
    public List<Com4Pojo> getCom4();
    public ManagerPojo getManageInfo(String branchAttr);
    public List<GroupInfoPojo> getGroup(String comCode);
}

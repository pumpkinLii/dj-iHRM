package com.cms.service;

import com.cms.pojo.Com4Pojo;
import com.cms.pojo.GroupInfoPojo;
import com.cms.pojo.ManagerPojo;

import java.util.List;

public interface GetManageInfoService {
    public List<Com4Pojo> getCom4();
    public ManagerPojo getManageInfo(String branchAttr);
    public List<GroupInfoPojo> getGroup(String comCode);
}

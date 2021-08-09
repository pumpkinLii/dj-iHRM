package com.cms.service;

import com.cms.pojo.LaAgentPojo;
import com.cms.pojo.LaAgentUpdatePojo;

/**
 * @author :zhanhaoze
 * @description :
 * @create :2021-08-03
 */
public interface YlAgentInfoService {
    /**人员添加接口，传入一个LaAgentPojo接口，返回boolean值表示添加成功/失败*/
    public boolean laAgentSubmit(LaAgentPojo laAgent);
    /**人员修改接口，传入一个LaAgentUpdatePojo接口，返回boolean值表示添加成功/失败*/
    public boolean laAgentUpdate(LaAgentUpdatePojo laAgent);
}

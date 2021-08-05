package com.cms.service;


import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 1 * @Author: zhangYiLong
 * 2 * @Date: 2021/8/2 20:16
 * 3 * @ClassName : EmpService
 */

/**
 * 该接口呈现了岗位对应的职级接口的方法
 */

public interface    EmpService  {
    public List<Map<String,String>> SearchM();//查询客户经理所用方法
    public  List<Map<String,String>> SearchG();//查询总监职级所用方法
}

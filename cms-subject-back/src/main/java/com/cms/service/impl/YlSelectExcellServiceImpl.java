package com.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cms.common.ComnewSon;
import com.cms.dao.IdCheckDao;
import com.cms.dao.YlSelectExcellDao;
import com.cms.entity.LdCodeEntity;
import com.cms.entity.YlLaAgentEntity;
import com.cms.pojo.IdCheckAllPojo;
import com.cms.pojo.IdCodePojo;
import com.cms.pojo.YlMSelectExcellPojo;
import com.cms.pojo.YlSelectExcellPojo;
import com.cms.service.IdCheckService;
import com.cms.service.YlSelectExcellService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
@Service
@Slf4j
public class YlSelectExcellServiceImpl  extends ServiceImpl<YlSelectExcellDao, YlLaAgentEntity> implements YlSelectExcellService {
    @Autowired
    ComnewSon comnewSon;

    @Autowired
    YlSelectExcellDao ylSelectExcellDao;



    public List<List> ylselect(List list) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        List<List> bigList=new ArrayList<>();
        for(int i=0;i<list.size();i++)
        {
            YlSelectExcellPojo ylSelectExcellPojo=new YlSelectExcellPojo();
            List<YlSelectExcellPojo> l=new ArrayList<>();
            QueryWrapper<YlMSelectExcellPojo> qw = new QueryWrapper();
            qw.eq("t1.agent_code", list.get(i));
            YlMSelectExcellPojo ylMSelectExcellPojo = this.baseMapper.YlSelectExcell(qw);

            Map m3 = comnewSon.getFatherManageCom(ylMSelectExcellPojo.getManageCom4());//三级机构
            if(m3==null)
            {
                ylSelectExcellPojo.setManageCom2(null);
                ylSelectExcellPojo.setManageName2(null);
                ylSelectExcellPojo.setManageCom3(null);
                ylSelectExcellPojo.setManageName3(null);
            }
            else{
                ylSelectExcellPojo.setManageCom3((String) m3.get("comcode"));
                ylSelectExcellPojo.setManageName3((String) m3.get("name"));
                Map m2 = comnewSon.getFatherManageCom((String) m3.get("comcode"));//二级机构
                if(m2==null)
                {
                    ylSelectExcellPojo.setManageCom2(null);
                    ylSelectExcellPojo.setManageName2(null);
                }
                else{
                    ylSelectExcellPojo.setManageCom2((String) m2.get("comcode"));
                    ylSelectExcellPojo.setManageName2((String) m2.get("name"));
                }
            }

            ylSelectExcellPojo.setManageCom4(ylMSelectExcellPojo.getManageCom4());
            ylSelectExcellPojo.setManageName4(ylMSelectExcellPojo.getManageName4());
            ylSelectExcellPojo.setAgentCode((String) list.get(i));//工号
            ylSelectExcellPojo.setSapCode(ylMSelectExcellPojo.getSapCode()); // SAP工号
            ylSelectExcellPojo.setAgentName(ylMSelectExcellPojo.getAgentName());// 姓名*
            ylSelectExcellPojo.setAgentState(ylMSelectExcellPojo.getAgentState());//人员状态
            ylSelectExcellPojo.setAgentJob(ylMSelectExcellPojo.getAgentJob());// 岗位*
            ylSelectExcellPojo.setAgentGrade(ylMSelectExcellPojo.getAgentGrade());// 人员职级*
            ylSelectExcellPojo.setSex(ylMSelectExcellPojo.getSex());// 性别*
            String Birthday = df.format(ylMSelectExcellPojo.getBirthday());
            ylSelectExcellPojo.setBirthday(Birthday); // 出生日期*
            ylSelectExcellPojo.setIdType(ylMSelectExcellPojo.getIdType());// 证件类型*
            ylSelectExcellPojo.setIdNo(ylMSelectExcellPojo.getIdNo());// 证件号码*
            String EmployDate = df.format(ylMSelectExcellPojo.getEmployDate());
            ylSelectExcellPojo.setEmployDate(EmployDate);// 入司日期*
            if(ylMSelectExcellPojo.getOutDate()!=null)
            {
                String OutDate = df.format(ylMSelectExcellPojo.getOutDate());
                ylSelectExcellPojo.setOutDate(OutDate);// 离司日期*
            }
            else
            {
                ylSelectExcellPojo.setOutDate(null);// 离司日期*
            }
            ylSelectExcellPojo.setRgtType(ylMSelectExcellPojo.getRgtType());// 户口类型*
            ylSelectExcellPojo.setRgtProvince(ylMSelectExcellPojo.getRgtProvince());// 户口所在省*
            ylSelectExcellPojo.setHighestDegree(ylMSelectExcellPojo.getHighestDegree());// 最高学历*
            ylSelectExcellPojo.setFirstDegree(ylMSelectExcellPojo.getFirstDegree());// 第一学历
            ylSelectExcellPojo.setDegree(ylMSelectExcellPojo.getDegree());// 最高学位*
            ylSelectExcellPojo.setGraduateSchool(ylMSelectExcellPojo.getGraduateSchool());// 毕业院校*
            ylSelectExcellPojo.setMajor(ylMSelectExcellPojo.getMajor());// 专业*
            ylSelectExcellPojo.setNationality(ylMSelectExcellPojo.getNationality());//民族*
            ylSelectExcellPojo.setNativeplace(ylMSelectExcellPojo.getNativeplace());// 籍贯*
            ylSelectExcellPojo.setOldIndustryType(ylMSelectExcellPojo.getOldIndustryType()); // 最近一份工作行业类型*
            ylSelectExcellPojo.setOldOccupation(ylMSelectExcellPojo.getOldOccupation()); // 最近一份职业*
            ylSelectExcellPojo.setOldCom(ylMSelectExcellPojo.getOldCom());// 最近一家工作单位*
            ylSelectExcellPojo.setOldJobDuty(ylMSelectExcellPojo.getOldJobDuty()); // 最近一份工作职务*
            ylSelectExcellPojo.setWorkAge(ylMSelectExcellPojo.getWorkAge());// 从业年限*
            ylSelectExcellPojo.setHomeAddress(ylMSelectExcellPojo.getHomeAddress());// 家庭地址*
            ylSelectExcellPojo.setPostcode(ylMSelectExcellPojo.getPostcode());// 邮编
            ylSelectExcellPojo.setHomephone(ylMSelectExcellPojo.getHomephone());// 住宅电话
            ylSelectExcellPojo.setPhone(ylMSelectExcellPojo.getPhone());// 手机*
            ylSelectExcellPojo.setEmail(ylMSelectExcellPojo.getEmail());// E-mail*
            ylSelectExcellPojo.setOutlookStatus(ylMSelectExcellPojo.getOutlookStatus());// 政治面貌*
            ylSelectExcellPojo.setBankCode(ylMSelectExcellPojo.getBankCode());// 账户银行总行*
            ylSelectExcellPojo.setBankAccount(ylMSelectExcellPojo.getBankAccount());// 银行账号*
            ylSelectExcellPojo.setUniteBankNum(ylMSelectExcellPojo.getUniteBankNum());// 银行卡开户行联行号*
            ylSelectExcellPojo.setBankProvince(ylMSelectExcellPojo.getBankProvince());// 开户行省份*
            ylSelectExcellPojo.setBankCity(ylMSelectExcellPojo.getBankCity());// 开户行所在市*
            ylSelectExcellPojo.setContractType("合同制");// 合同类型*
            String ContractStartDate = df.format(ylMSelectExcellPojo.getContractStartDate());
            ylSelectExcellPojo.setContractStartDate(ContractStartDate);// 劳动合同起期*
            String ContractEndDate = df.format(ylMSelectExcellPojo.getContractEndDate());
            ylSelectExcellPojo.setContractEndDate(ContractEndDate);// 劳动合同止期*
            ylSelectExcellPojo.setBranchAttr(ylMSelectExcellPojo.getBranchAttr());// 团队架构代码*
            ylSelectExcellPojo.setBranchName(ylMSelectExcellPojo.getBranchName());// 团队架构名称
            ylSelectExcellPojo.setBranchManagerCode(ylMSelectExcellPojo.getBranchManagerCode());// 团队主管工号
            ylSelectExcellPojo.setBranchManagerName(ylMSelectExcellPojo.getBranchManagerName());// 团队主管姓名
            ylSelectExcellPojo.setBranchManagerPhone(ylMSelectExcellPojo.getBranchManagerPhone());// 团队主管手机号
            l.add(ylSelectExcellPojo);
            bigList.add(l);
        }
        return bigList;
    }
}

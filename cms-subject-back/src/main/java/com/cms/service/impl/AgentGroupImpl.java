package com.cms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cms.dao.RYlLaBranchGroupDao;
import com.cms.entity.YlLaBranchGroupEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AgentGroupImpl extends ServiceImpl<RYlLaBranchGroupDao, YlLaBranchGroupEntity>implements com.cms.service.AgentGroup {

    @Override
    public String getAgentGroup() {
        List<YlLaBranchGroupEntity> list=this.baseMapper.selectList(null);
        if (list.size()==0){
            return "1000000001";
        }else {
            int temp=0;
            for (int i = 0; i < list.size(); i++) {
                if (temp<Integer.parseInt(list.get(i).getAgentGroup())){
                    temp=Integer.parseInt(list.get(i).getAgentGroup());
                }
            }
            temp+=1;
            return String.valueOf(temp);

        }
    }

    @Override
    public String teamCode() {
        List<YlLaBranchGroupEntity> list=this.baseMapper.selectList(null);
        if (list.size()==0){
            return "100001";
        }else {
            Integer teap=0;
            StringBuffer stringBuffer=new StringBuffer();
            for (int i = 0; i < list.size(); i++) {
                stringBuffer.append(list.get(i).getBranchAttr());
                String value=stringBuffer.substring(stringBuffer.length()-6,stringBuffer.length());
                if (teap<Integer.parseInt(value)){teap=Integer.parseInt(value);}
            }
            teap+=1;
            return String.valueOf(teap);

        }
    }


}

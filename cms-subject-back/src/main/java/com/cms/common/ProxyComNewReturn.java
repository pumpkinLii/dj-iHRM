package com.cms.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Component
public class ProxyComNewReturn {
    //使用代理模式 实现额外的操作
    @Autowired
    public ComnewSon comnewSon;
    public  ComNewReturn Start(){
        ComNewReturn comNewReturn=new ComNewReturn();
        comNewReturn.setLabel((String) comnewSon.getHighManageCom().get(0).get("name"));
        comNewReturn.setValue((String) comnewSon.getHighManageCom().get(0).get("comcode"));
        comNewReturn.setChildren(initSonList(comNewReturn));
        return comNewReturn;
    }
    private  List initSonList(ComNewReturn comNewReturn){
        //获取自己的所有的子
        List<Map> sonManageCom = comnewSon.getSonManageCom(comNewReturn.value);
        if (sonManageCom==null){
            return null;
        }
        List result=new ArrayList();
        for (int i = 0; i < sonManageCom.size(); i++) {
            ComNewReturn comNewReturnPojo=doComNewReturn((String) sonManageCom.get(i).get("comcode"),(String) sonManageCom.get(i).get("name"));
            result.add(comNewReturnPojo);
        }
        return result;
    }

    private   ComNewReturn doComNewReturn(String comcode, String name) {
        ComNewReturn comNewReturn=new ComNewReturn();
        comNewReturn.setLabel(name);
        comNewReturn.setValue(comcode);
        comNewReturn.setChildren(initSonList(comNewReturn));
        return comNewReturn;
    }
}

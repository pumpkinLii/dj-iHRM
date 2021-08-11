package com.cms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cms.dao.AreaDao;
import com.cms.entity.YlLaAreaEntity;
import com.cms.pojo.AreaPojo;
import com.cms.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AreaServiceImpl extends ServiceImpl<AreaDao, YlLaAreaEntity> implements AreaService {

    @Override
    public List<AreaPojo> getArea() {
        List result=new ArrayList();
        List<YlLaAreaEntity> list = this.baseMapper.selectList(null);
        Map map=new HashMap();
        for (int i = 0; i < list.size(); i++) {
            YlLaAreaEntity ylLaAreaEntity = list.get(i);//获取每一行的数据
            if ("01".equals(ylLaAreaEntity.getPlaceType())){
                //这是省的情况 需要按这些 创造list
                List list1=new ArrayList();
                map.put(ylLaAreaEntity.getPlaceName(),list1);
            }
        }
        //map为每个省名对应了一个List 接下来就是往对应的List添加数据
        for (int j = 0; j < list.size(); j++) {
            AreaPojo areaPojo=new AreaPojo();
            //大循环
            List provinList=new ArrayList();//包含这个省的对象的集合
            YlLaAreaEntity ylLaAreaEntityfather = list.get(j);//获取每一行的数据
            if ("01".equals(ylLaAreaEntityfather.getPlaceType())) {
                //为每个省的List 添加Map对象 有多少个市 添加多少个Map对象
                for (int i = 0; i < list.size(); i++) {
                    YlLaAreaEntity ylLaAreaEntityson = list.get(i);//获取每一行的数据
                    if ("02".equals(ylLaAreaEntityson.getPlaceType())==true&&ylLaAreaEntityson.getUpPlaceName().equals(ylLaAreaEntityfather.getPlaceCode())==true) {
                        //是市级单位 然后还是这个省的子集
                        Map mapson=new HashMap();
                        mapson.put("label",ylLaAreaEntityson.getPlaceName());
                        mapson.put("value",ylLaAreaEntityson.getPlaceCode());
                        //得到这个省的List
                        provinList.add(mapson);
                    }
                }
                areaPojo.setList(provinList);
                areaPojo.setLabel(ylLaAreaEntityfather.getPlaceName());
                areaPojo.setValue(ylLaAreaEntityfather.getPlaceCode());
                result.add(areaPojo);
            }
        }
                return result;
    }

    //要进行的操作是 返回数据 大的List放每个省 小的List放每个省里面的数据
    //遍历两次 第一次根据01进行key的设置 顺便获取相同码 第二次遍历里面的数据然后进行操作

}

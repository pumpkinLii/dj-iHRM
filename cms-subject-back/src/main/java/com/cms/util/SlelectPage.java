package com.cms.util;

import java.util.ArrayList;
import java.util.List;

public class SlelectPage {
    public static List getPage(int limit,int page,List list) {
        // limit 2 page 2
        //limit为当前第几页 page为当前的页面限制;
        //对于List进行筛选
        int tataocount;
        tataocount = list.size();//总数
        int tatalpage;
        int returni=0;
        //如何获取索引呢？
        List result = new ArrayList();
        int currentindex = 0;
        if (page == 1) {
            currentindex = 0;
        } else if (tataocount-((page-1)*limit)==1){//某个页面以后的第二页只有一条数据
                result.add(list.get((page-1)*limit));
                return result;
        }else
            {
            currentindex = (page-1)*limit;//2 10
            }
            int lastindex = currentindex + limit - 1;//3 10+9=19
            if (lastindex >= list.size()) {//5
                lastindex = (list.size() - 1);
            }
                for (int i = currentindex; i <=lastindex; i++) {
                    result.add(list.get(i));
            }

            return result;
        }

}


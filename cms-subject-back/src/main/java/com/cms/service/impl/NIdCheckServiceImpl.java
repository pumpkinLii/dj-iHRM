//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cms.dao.IdCheckDao;
import com.cms.entity.YlLaAgentEntity;
import com.cms.pojo.IdCheckAllPojo;
import com.cms.pojo.IdCheckPojo;
import com.cms.pojo.LaAgentPojo;

import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cms.service.NIdCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class NIdCheckServiceImpl extends ServiceImpl<IdCheckDao, YlLaAgentEntity> implements NIdCheckService {
    @Autowired
    IdCheckDao idCheckDao;

    public int idcheck(IdCheckPojo idCheckPojo) {
        String idtype = idCheckPojo.getIdType();
        String idno = idCheckPojo.getIdNo();
        if (idno.length() == 0) {
            return 6;
        } else if (isAlphaNumeric(idno)) {
            exChange(idno);
            if (!idtype.equals("0")) {
                if (idno.length() > 18) {
                    return 8;
                }
            } else if (!sfzcheck(idno)) {
                return 9;
            }

            QueryWrapper<IdCheckAllPojo> qw = new QueryWrapper();
            qw.eq("id_no", idno);
            qw.eq("id_type", idtype);
            List<IdCheckAllPojo> list = ((IdCheckDao)this.baseMapper).getAllYlLaAgent(qw);
            if (list.size() == 0) {
                return 1;
            } else {
                IdCheckAllPojo idCheckOne = (IdCheckAllPojo)list.get(0);
                String state = idCheckOne.getAgentState();
                if (state.equals("01")) {
                    return 2;
                } else {
                    if (state.equals("04")) {
                        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        new Date();
                        Date endDate = idCheckOne.getOutWorkDate();
                        long now_long = System.currentTimeMillis();
                        long end_long = endDate.getTime();
                        long cur = (now_long - end_long) / 1000;
                        long timper = 15552000;
                        if (cur < 0) {
                            return 2;
                        }

                        if (cur < timper && cur > 0) {
                            return 3;
                        }

                        if (cur > timper) {
                            return 4;
                        }
                    }

                    return 5;
                }
            }
        } else {
            return 7;
        }
    }

    public static boolean isAlphaNumeric(String s) {
        Pattern p = Pattern.compile("[0-9a-zA-Z]{1,}");
        Matcher m = p.matcher(s);
        return m.matches();
    }

    public static String exChange(String str) {
        StringBuffer sb = new StringBuffer();
        if (str != null) {
            for(int i = 0; i < str.length(); ++i) {
                char c = str.charAt(i);
                if (Character.isLowerCase(c)) {
                    sb.append(Character.toUpperCase(c));
                }
            }
        }

        return sb.toString();
    }

    public static boolean sfzcheck(String IDNumber) {
        if (IDNumber != null && !"".equals(IDNumber)) {
            String regularExpression = "(^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}$)";
            boolean matches = IDNumber.matches(regularExpression);
            if (matches && IDNumber.length() == 18) {
                try {
                    char[] charArray = IDNumber.toCharArray();
                    int[] idCardWi = new int[]{7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
                    String[] idCardY = new String[]{"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};
                    int sum = 0;

                    int idCardMod;
                    for(int i = 0; i < idCardWi.length; ++i) {
                        idCardMod = Integer.parseInt(String.valueOf(charArray[i]));
                        int count = idCardMod * idCardWi[i];
                        sum += count;
                    }

                    char idCardLast = charArray[17];
                    idCardMod = sum % 11;
                    return idCardY[idCardMod].toUpperCase().equals(String.valueOf(idCardLast).toUpperCase());
                } catch (Exception var10) {
                    var10.printStackTrace();
                    return false;
                }
            } else {
                return matches;
            }
        } else {
            return false;
        }
    }
}

package com.cms.util;

import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author :zhanhaoze
 * @description : 封装返回校验结果
 * @create :2021-07-26
 */
public class CheckReturn implements Serializable {

    private static final String NOT_NULL_TIP_END = "，信息不可为空！";

    private static final long serialVersionUID = -832368822363510096L;

    private List<String> errors = new ArrayList<String>();

    /**
     *
     * @return 是否存在错误
     */
    public boolean hasError() {
        return !errors.isEmpty();
    }

    /**
     * @param msg 需要添加的错误提示信息
     * @return
     */
    public CheckReturn addError(String msg) {
        this.errors.add(msg);
        return this;
    }

    /**
     * @param msg 需要添加的错误提示信息
     * @return
     */
    public CheckReturn addNotNullError(String msg) {
        this.errors.add(msg + NOT_NULL_TIP_END);
        return this;
    }

    /**
     *
     * @return 获取错误信息
     */
    public String getError() {
        if (hasError()) {
            return this.errors.get(0);
        }
        return null;
    }

    @Override
    public String toString() {
        return "CheckReturn [errors=" + errors + "]";
    }

    /**
     * 判断对象中属性值是否全为空
     *
     * @param object
     * @return
     */
    public static boolean checkObjAllFieldsIsNull(Object object) {
        if (null == object) {
            return true;
        }
        try {
            for (Field f : object.getClass().getDeclaredFields()) {
                f.setAccessible(true);
                if (f.get(object)==null || StringUtils.isEmpty(f.get(object).toString())) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}

package com.cms.pojo;

import lombok.Data;

@Data
public class TablePropertiesPojo {
    String Field;
    String Type;
    String Collation;
    String Null;//需要我去判断
    String Key;
    String Default;
    String Extra;
    String Privileges;
    String Comment;
}

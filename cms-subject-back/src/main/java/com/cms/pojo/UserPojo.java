package com.cms.pojo;

import lombok.Data;

@Data
public class UserPojo {
    String userId;
    String userPassword;

    @Override
    public String toString() {
        return "UserPojo{" +
                "userId='" + userId + '\'' +
                ", userPassword='" + userPassword + '\'' +
                '}';
    }
}

package com.cms.pojo;

import lombok.Data;

@Data
public class UserPojo {
    String userId;
    String userPassword;
    String token;

    @Override
    public String toString() {
        return "UserPojo{" +
                "userId='" + userId + '\'' +
                ", userPassword='" + userPassword + '\'' +
                '}';
    }
}

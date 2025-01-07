package org.itheima.easychatback.entity.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class TokenUserInfoDto implements Serializable {

    private String token;
    private String userId;
    private String nickName;
    private Boolean admin;

    @Override
    public String toString() {
        return "TokenUserInfoDto{" +
                "token='" + token + '\'' +
                ", userId='" + userId + '\'' +
                ", nickName='" + nickName + '\'' +
                ", admin=" + admin +
                '}';
    }

}

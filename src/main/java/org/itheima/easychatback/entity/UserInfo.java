package org.itheima.easychatback.entity;

import lombok.Data;

import java.sql.Time;
import java.time.LocalDateTime;

@Data
public class UserInfo {


    private String email;
    private String passWord;
    private String nickName;
    private String userId;

    private Integer sex;

    private Integer joinType;

    private String personalSignature;

    private String areaCode;

    private String areaName;

    private LocalDateTime createTime;
    private Boolean Status;
    private LocalDateTime lastLoginTime;
    private Long lastOffTime;

}

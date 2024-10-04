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
    private LocalDateTime createTime;
    private String Status;
    private LocalDateTime lastLoginTime;

}
package org.itheima.easychatback.service;


import org.itheima.easychatback.entity.UserInfo;

import java.util.Map;

public interface UserInfoService {

    //用户注册
    Map<String, Object> register(String email, String passWord, String nickName);

    //用户登录
    UserInfo login(String email, String passWord);
}

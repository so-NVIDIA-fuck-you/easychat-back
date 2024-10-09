package org.itheima.easychatback.service;


import org.itheima.easychatback.entity.UserInfo;
import org.itheima.easychatback.entity.dto.TokenUserInfoDto;

import java.util.Map;

public interface UserInfoService {

    //用户注册
    void register(String email, String passWord, String nickName);

    //用户登录
    TokenUserInfoDto login(String email, String passWord);
}

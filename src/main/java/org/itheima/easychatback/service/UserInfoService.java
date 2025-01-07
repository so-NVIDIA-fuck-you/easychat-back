package org.itheima.easychatback.service;


import com.baomidou.mybatisplus.extension.service.IService;
import org.itheima.easychatback.entity.po.UserInfo;
import org.itheima.easychatback.entity.vo.UserInfoVO;

//extends IService<UserInfo>
public interface UserInfoService  {

    //用户注册
    void register(String email, String passWord, String nickName);

    //用户登录
    UserInfoVO login(String email, String passWord);
}

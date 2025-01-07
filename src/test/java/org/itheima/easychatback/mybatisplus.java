package org.itheima.easychatback;


import org.itheima.easychatback.entity.po.UserInfo;
import org.itheima.easychatback.mapper.UserInfoMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class mybatisplus {
    @Autowired
    private  UserInfoMapper userInfoMapper;
    @Test
    void getAll()
    {
        List<UserInfo> users=userInfoMapper.selectList(null);
        System.out.println(users);
    }
}

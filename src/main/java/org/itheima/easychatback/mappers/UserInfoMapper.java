package org.itheima.easychatback.mappers;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.itheima.easychatback.entity.UserInfo;

@Mapper
public interface UserInfoMapper {

    @Select("select * from user_info where email=#{email}")
    public UserInfo selectByEmail(String email);

    //添加一名用户
    void insert(UserInfo userInfo);
}

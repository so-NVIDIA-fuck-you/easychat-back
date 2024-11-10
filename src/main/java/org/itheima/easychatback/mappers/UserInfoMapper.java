package org.itheima.easychatback.mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.itheima.easychatback.entity.UserInfo;

@Mapper
public interface UserInfoMapper {

    @Select("select * from user_info where email=#{email}")
    public UserInfo selectByEmail(String email);

    //添加一名用户
    @Insert("insert into user_info (user_id, email, nick_name, join_type, sex, password, personal_signature, status, create_time, last_login_time, area, area_code,last_off_time) " +
            "VALUES " +
            "(#{userId},#{email},#{nickName},#{joinType},#{sex},#{passWord},#{personalSignature},#{Status},#{createTime},#{lastLoginTime},#{areaName},#{areaCode},#{lastOffTime})")
    void insert(UserInfo userInfo);

    //根据id查询用户
    @Select("select * from user_info where user_id=#{userId}")
    UserInfo getUserInfoByUserId(String userId);
}

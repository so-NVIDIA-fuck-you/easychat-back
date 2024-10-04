package org.itheima.easychatback.mappers;

import org.apache.ibatis.annotations.Select;
import org.itheima.easychatback.entity.UserInfoBeauty;

public interface UserInfoBeautyMapper {

    @Select("select * from user_info_beauty where email=#{email}")
    UserInfoBeauty selectByEmail(String email);
}

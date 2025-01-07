package org.itheima.easychatback.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.itheima.easychatback.entity.po.UserInfoBeauty;

@Mapper
public interface UserInfoBeautyMapper extends BaseMapper<UserInfoBeauty> {

    @Select("select * from user_info_beauty where email=#{email}")
    UserInfoBeauty selectByEmail(String email);

    void updateById(UserInfoBeauty updateBeauty, int id);
}

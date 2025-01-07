package org.itheima.easychatback.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.itheima.easychatback.entity.po.User_contact;

/**
 * <p>
 * 联系人 Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2024-11-16
 */
@Mapper
public interface User_contactMapper extends BaseMapper<User_contact> {

    @Insert("insert into user_contact (user_id, contact_id, contact_type, create_time, status, last_update_time) VALUES " +
            "(#{user_id},#{contact_id},#{contact_type},#{create_time},#{ status}, #{last_update_time})")
    int insert(User_contact userContact);
}

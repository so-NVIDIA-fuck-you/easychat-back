package org.itheima.easychatback.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.itheima.easychatback.entity.po.Group_info;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2024-11-16
 */
@Mapper
public interface Group_infoMapper extends BaseMapper<Group_info> {

//    @Select("select count(*) from group_info where group_owner_id=#{group_own_id} ")
//    Integer selectCount(String group_own_id);
//
//
//    @Insert("insert into group_info (group_id, group_name, group_owner_id, create_time, group_notice, join_type,status) VALUES " +
//            "(#{group_id},#{group_name},#{group_owner_id},#{create_time},#{group_notice},#{join_type},#{status})")
//    int insert(Group_info entity);
}

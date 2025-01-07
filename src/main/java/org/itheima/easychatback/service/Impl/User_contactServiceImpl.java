package org.itheima.easychatback.service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.itheima.easychatback.entity.po.Group_info;
import org.itheima.easychatback.entity.po.User_contact;
import org.itheima.easychatback.mapper.User_contactMapper;
import org.itheima.easychatback.service.IUser_contactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 联系人 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2024-11-16
 */
@Service
public class User_contactServiceImpl  implements IUser_contactService {

    @Autowired
    private User_contactMapper user_contactMapper;

    @Override
    public List<User_contact> getContact(String userId) {
        LambdaQueryWrapper<User_contact> lqw = new  LambdaQueryWrapper();
        lqw.eq(User_contact::getUser_id,userId);
        return user_contactMapper.selectList(lqw);
    }
}

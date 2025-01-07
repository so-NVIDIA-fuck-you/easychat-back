package org.itheima.easychatback.service;


import com.baomidou.mybatisplus.extension.service.IService;
import org.itheima.easychatback.entity.po.User_contact;

import java.util.List;

/**
 * <p>
 * 联系人 服务类
 * </p>
 *
 * @author ${author}
 * @since 2024-11-16
 */
public interface IUser_contactService  {

    List<User_contact> getContact(String userId);
}

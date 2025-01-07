package org.itheima.easychatback.service;


import com.baomidou.mybatisplus.extension.service.IService;
import org.itheima.easychatback.entity.po.Group_info;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2024-11-16
 */
public interface IGroup_infoService extends IService<Group_info> {

    void saveGroup(Group_info group_info, MultipartFile avatarFile,MultipartFile avatarCover) throws IOException;

    List<Group_info> loadGroup(String userId);

    Group_info getGroupInfoByGroupId(String groupId);
}

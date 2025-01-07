package org.itheima.easychatback.service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.itheima.easychatback.Utils.StringTools;
import org.itheima.easychatback.entity.config.AppConfig;
import org.itheima.easychatback.entity.constants.Constants;
import org.itheima.easychatback.entity.dto.SysSettingDto;
import org.itheima.easychatback.entity.enums.ResponseCodeEnum;
import org.itheima.easychatback.entity.enums.UserContactStatusEnum;
import org.itheima.easychatback.entity.enums.UserContactTypeEnum;
import org.itheima.easychatback.entity.po.Group_info;
import org.itheima.easychatback.entity.po.User_contact;
import org.itheima.easychatback.exception.BusinessException;
import org.itheima.easychatback.mapper.Group_infoMapper;
import org.itheima.easychatback.mapper.User_contactMapper;
import org.itheima.easychatback.redis.RedisComponent;
import org.itheima.easychatback.service.IGroup_infoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2024-11-16
 */
@Service
public class Group_infoServiceImpl extends ServiceImpl<Group_infoMapper, Group_info> implements IGroup_infoService {

    @Autowired
    private  Group_infoMapper group_infoMapper;
    @Autowired
    private User_contactMapper user_contactMapper;
    @Autowired
    private RedisComponent redisComponent;
    @Resource
    private AppConfig appConfig;


    Date curDate = new Date();


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveGroup(Group_info group_info, MultipartFile avatarFile, MultipartFile avatarCover) throws IOException {
        if(StringTools.isEmpty(group_info.getGroup_id()))
        {
            LambdaQueryWrapper<Group_info> lqw = new  LambdaQueryWrapper<Group_info>();
            lqw.eq(Group_info::getGroup_owner_id, group_info.getGroup_owner_id());
            System.out.println(group_info.toString());
            Long count=group_infoMapper.selectCount(lqw);
            SysSettingDto sysSettingDto= redisComponent.getSysSetting();
            if(count>=sysSettingDto.getMaxGroupCount())
            {
                throw new BusinessException("创建群的数量已达上限");
            }
//            if(null==avatarFile)
//            {
//                throw new BusinessException(ResponseCodeEnum.CODE_600.getMsg());
//            }
            group_info.setCreate_time(curDate);
            group_info.setGroup_id(StringTools.getGroupId());

            group_infoMapper.insert(group_info);

            //将群组添加为联系人
            User_contact userContact=new User_contact();
            userContact.setStatus(UserContactStatusEnum.FRIEND.getStatus());
            userContact.setContact_type(UserContactTypeEnum.GROUP.getType());
            userContact.setContact_id(group_info.getGroup_id());
            userContact.setUser_id(group_info.getGroup_owner_id());
            userContact.setCreate_time(curDate);
            userContact.setLast_update_time(curDate);
            user_contactMapper.insert(userContact);
            //TODO 创建会话
            //TODO 发送消息

        }else {
            Group_info dbInfo=group_infoMapper.selectById(group_info.getGroup_id());
            if(!dbInfo.getGroup_owner_id().equals(group_info.getGroup_owner_id()))
            {
                throw new BusinessException(ResponseCodeEnum.CODE_600.getMsg());
            }
            //更新群组的信息
            LambdaQueryWrapper<Group_info> lqw = new  LambdaQueryWrapper();
            lqw.eq(Group_info::getGroup_id, group_info.getGroup_id());
            group_infoMapper.update(group_info,lqw);
            //TODO 更新相关表冗余信息

            //TODO 如果修改群昵称发送ws消息
        }
        if(null==avatarFile)
        {
            return;
        }
        String baseFolder=appConfig.getProjectFolder()+ Constants.FILE_FOLDER_FILE;
        File targetFile=new File(baseFolder+Constants.FILE_FOLDER_AVATAR_NAME);
        if(!targetFile.exists())
        {
            targetFile.mkdirs();
        }
        String filePath=targetFile.getPath()+"/"+group_info.getGroup_id()+Constants.IMAGE_SUFFIX;

        avatarFile.transferTo(new File(filePath));
        avatarCover.transferTo(new File(filePath+Constants.COVER_IMAGE_SUFFIX));

    }

    @Override
    public List<Group_info> loadGroup(String userId)
    {
        LambdaQueryWrapper<Group_info> lqw = new  LambdaQueryWrapper();
        lqw.orderByDesc(Group_info::getCreate_time);
        lqw.eq(Group_info::getGroup_owner_id, userId);
        return group_infoMapper.selectList(lqw);
    }

    @Override
    public Group_info getGroupInfoByGroupId(String groupId) {
        LambdaQueryWrapper<Group_info> lqw = new  LambdaQueryWrapper();
        lqw.eq(Group_info::getGroup_id, groupId);
        return group_infoMapper.selectOne(lqw);
    }
}

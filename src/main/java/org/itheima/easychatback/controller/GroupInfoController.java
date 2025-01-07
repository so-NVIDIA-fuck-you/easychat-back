package org.itheima.easychatback.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.itheima.easychatback.annotation.GlobalInterceptor;
import org.itheima.easychatback.entity.dto.TokenUserInfoDto;
import org.itheima.easychatback.entity.enums.GroupStatusEnum;
import org.itheima.easychatback.entity.enums.UserContactStatusEnum;
import org.itheima.easychatback.entity.po.Group_info;
import org.itheima.easychatback.entity.po.User_contact;
import org.itheima.easychatback.entity.query.UserContactQuery;
import org.itheima.easychatback.exception.BusinessException;
import org.itheima.easychatback.mapper.Group_infoMapper;
import org.itheima.easychatback.mapper.User_contactMapper;
import org.itheima.easychatback.result.Result;
import org.itheima.easychatback.service.IGroup_infoService;
import org.itheima.easychatback.service.IUser_contactService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2024-11-16
 */
@Controller
@RestController("groupInfoController")
@RequestMapping("/group")
public class GroupInfoController extends BaseController  {

    @Resource
    private IGroup_infoService groupInfoService;

    @Resource
    private IUser_contactService userContactService;

    @Resource
    private User_contactMapper userContactMapper;



    @RequestMapping("/saveGroup")
    @GlobalInterceptor
    public Result saveGroup(HttpServletRequest request,
                            String groupId,
                            @NotEmpty String groupName,
                            String groupNotice,
                            @NotNull Integer joinType,
                            MultipartFile avatarFile,
                            MultipartFile avatarCover) throws IOException {
        TokenUserInfoDto tokenUserInfoDto=getTokenUserInfo(request);
        Group_info groupInfo=new Group_info();
        groupInfo.setGroup_id(groupId);
        groupInfo.setGroup_owner_id(tokenUserInfoDto.getUserId());
        groupInfo.setGroup_name(groupName);
        groupInfo.setGroup_notice(groupNotice);
        groupInfo.setJoin_type(joinType);

        groupInfoService.saveGroup(groupInfo,avatarFile,avatarCover);
        return Result.success();
    }

    @RequestMapping("/loadMyGroup")
    @GlobalInterceptor
    public Result<List<Group_info>> loadMyGroup( HttpServletRequest request)
    {
        TokenUserInfoDto tokenUserInfoDto=getTokenUserInfo(request);
        List<Group_info> myGroup=groupInfoService.loadGroup(tokenUserInfoDto.getUserId());
        return  Result.success(myGroup);
    }


    @RequestMapping("/getGroupInfo")
    @GlobalInterceptor
    public Result<Group_info> getGroupInfo(HttpServletRequest request,@NotEmpty String groupId)
    {
        Group_info groupInfo= getGroupDetailCommon(request,groupId);
        //查询群成员数量
        LambdaQueryWrapper<User_contact> lqw = new  LambdaQueryWrapper();
        lqw.eq(User_contact::getContact_id, groupId);
        Long count=userContactMapper.selectCount(lqw);
        groupInfo.setMemberCount(count);
        return Result.success(groupInfo);

    }




    private Group_info getGroupDetailCommon(HttpServletRequest request,String groupId)
    {
        TokenUserInfoDto tokenUserInfoDto=getTokenUserInfo(request);
        List<User_contact> userContacts=userContactService.getContact(tokenUserInfoDto.getUserId());
        Group_info groupInfo=null;
        if(userContacts!=null&&userContacts.size()>0)
        {

            for (User_contact contacts:userContacts)
            {
                if(contacts.getContact_id().equals(groupId))
                {
                    groupInfo=groupInfoService.getGroupInfoByGroupId(groupId);
                }
                else throw new BusinessException("你不在此群聊中或此群不存在");
            }
        }



        if(groupInfo!=null && !GroupStatusEnum.NORMAL.getStatus().equals(groupInfo.getStatus()))
        {
            throw new BusinessException("群聊不存在或已经解散");
        }
        return groupInfo;
    }

    //查询用户联系人
    @RequestMapping("/getGroupInfo4Chat")
    @GlobalInterceptor
    private Result getGroupInfo4Chat(HttpServletRequest request,@NotEmpty String groupId)
    {
        Group_info groupInfo= getGroupDetailCommon(request,groupId);
        LambdaQueryWrapper<User_contact> lqw = new  LambdaQueryWrapper();
        lqw.eq(User_contact::getContact_id, groupId);

//        List<User_contact> userContactList=userContactService
        return Result.success();
    }






}


package org.itheima.easychatback.service.Impl;

import org.itheima.easychatback.Utils.StringTools;
import org.itheima.easychatback.entity.UserInfo;
import org.itheima.easychatback.entity.UserInfoBeauty;
import org.itheima.easychatback.entity.config.AppConfig;
import org.itheima.easychatback.entity.constants.Constants;
import org.itheima.easychatback.entity.dto.TokenUserInfoDto;
import org.itheima.easychatback.entity.enums.BeautyAccountStatusEnum;
import org.itheima.easychatback.entity.enums.UserContactTypeEnum;
import org.itheima.easychatback.exception.BusinessException;
import org.itheima.easychatback.mappers.UserInfoBeautyMapper;
import org.itheima.easychatback.mappers.UserInfoMapper;
import org.itheima.easychatback.redis.RedisComponent;
import org.itheima.easychatback.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private UserInfoBeautyMapper userInfoBeautyMapper;

    @Autowired
    private AppConfig appConfig;

    @Autowired
    private RedisComponent redisComponent;


    @Override
    public void register(String email, String passWord, String nickName) {
        Map<String, Object> result = new HashMap<>();
        UserInfo userInfo = userInfoMapper.selectByEmail(email);

        if (null != userInfo) {
            throw new BusinessException("邮箱账号已存在");
        }
        String userId = StringTools.getUserId();
        UserInfoBeauty beautyAccount = userInfoBeautyMapper.selectByEmail(email);
        Boolean userBeautyAccount = null != beautyAccount && BeautyAccountStatusEnum.NO_USE.getStatus().equals(beautyAccount.getStatus());
        if (userBeautyAccount) {
            userId = UserContactTypeEnum.USER.getPrefix() + beautyAccount.getUserId();

        }
        userInfo = new UserInfo();
        userInfo.setUserId(userId);
        userInfo.setEmail(email);
        userInfo.setNickName(nickName);
        userInfo.setPassWord(StringTools.encodeMD5(passWord));
        userInfo.setCreateTime(LocalDateTime.now());
        userInfo.setLastLoginTime(LocalDateTime.now());
        userInfoMapper.insert(userInfo);
        if (userBeautyAccount) {
            UserInfoBeauty updateBeauty = new UserInfoBeauty();
            updateBeauty.setStatus(BeautyAccountStatusEnum.USEED.getStatus());
        }
        //TODO 创建机器人好友

    }



    @Override
    public TokenUserInfoDto login(String email, String passWord) {

        UserInfo userInfo=userInfoMapper.selectByEmail(email);
        if (null == userInfo||!userInfo.getPassWord().equals(StringTools.encodeMD5(passWord))) {
            throw new BusinessException("账号或者密码不存在");
        }
        if (Constants.ENABLE!= userInfo.getStatus()) {
            throw new BusinessException("账号已被禁用");
        }
        //TODO 查询我的群组
        //TODO 查询我的联系人
        TokenUserInfoDto tokenUserInfoDto=getTokenUserInfoDto(userInfo);


        return tokenUserInfoDto;


    }

    private TokenUserInfoDto getTokenUserInfoDto(UserInfo userInfo){
        TokenUserInfoDto tokenUserInfoDto=new TokenUserInfoDto();
        tokenUserInfoDto.setUserId(userInfo.getUserId());
        tokenUserInfoDto.setNickName(userInfo.getNickName());

        String adminEmail=appConfig.getAdminEmails();
        if (!adminEmail.equals(userInfo.getEmail()))
        {
            tokenUserInfoDto.setAdmin(true);
        }else{
            tokenUserInfoDto.setAdmin(false);
        }
        return tokenUserInfoDto;
    }




}

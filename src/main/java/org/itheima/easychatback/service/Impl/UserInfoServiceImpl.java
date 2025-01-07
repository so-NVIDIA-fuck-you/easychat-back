package org.itheima.easychatback.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.itheima.easychatback.Utils.StringTools;
import org.itheima.easychatback.entity.po.UserInfo;
import org.itheima.easychatback.entity.po.UserInfoBeauty;
import org.itheima.easychatback.entity.config.AppConfig;
import org.itheima.easychatback.entity.constants.Constants;
import org.itheima.easychatback.entity.dto.TokenUserInfoDto;
import org.itheima.easychatback.entity.enums.BeautyAccountStatusEnum;
import org.itheima.easychatback.entity.enums.JoinTypeEnum;
import org.itheima.easychatback.entity.enums.UserContactTypeEnum;
import org.itheima.easychatback.entity.vo.UserInfoVO;
import org.itheima.easychatback.exception.BusinessException;
import org.itheima.easychatback.mapper.UserInfoBeautyMapper;
import org.itheima.easychatback.mapper.UserInfoMapper;
import org.itheima.easychatback.redis.RedisComponent;
import org.itheima.easychatback.service.UserInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
//extends ServiceImpl<UserInfoMapper, UserInfo>
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
    @Transactional(rollbackFor = Exception.class)
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
        userInfo.setStatus(Constants.ENABLE);
        userInfo.setLastOffTime(System.currentTimeMillis());
        userInfo.setJoinType(JoinTypeEnum.APPLY.getType());


        userInfoMapper.insert(userInfo);
        if (userBeautyAccount) {
            UserInfoBeauty updateBeauty = new UserInfoBeauty();
            updateBeauty.setStatus(BeautyAccountStatusEnum.USEED.getStatus());
            userInfoBeautyMapper.updateById(updateBeauty,beautyAccount.getId());
        }
        //TODO 创建机器人好友

    }



    @Override
    public UserInfoVO login(String email, String passWord) {

        UserInfo userInfo=userInfoMapper.selectByEmail(email);
        System.out.println(userInfo.toString());
        System.out.println(StringTools.encodeMD5(passWord));
        if (null == userInfo||!userInfo.getPassWord().equals(StringTools.encodeMD5(passWord))) {
            throw new BusinessException("账号或者密码不存在");
        }
        if (Constants.ENABLE!= userInfo.getStatus()) {
            throw new BusinessException("账号已被禁用");
        }
        //TODO 查询我的群组
        //TODO 查询我的联系人
        TokenUserInfoDto tokenUserInfoDto=getTokenUserInfoDto(userInfo);
         Long lastHeartBeat=redisComponent.getUserHeartBeat(userInfo.getUserId());
         if (null != lastHeartBeat) {
             throw new BusinessException("此账号已在别处登录，请退出后在登陆");
         }
         //生成token
         String token=StringTools.encodeMD5(tokenUserInfoDto.getUserId()+StringTools.getRandomString(Constants.LENGTH_20));
         tokenUserInfoDto.setToken(token);
         redisComponent.saveTokenUserInfoDto(tokenUserInfoDto);


        UserInfoVO userInfoVO= new UserInfoVO();
        BeanUtils.copyProperties(userInfo,userInfoVO);

        userInfoVO.setToken(tokenUserInfoDto.getToken());
        userInfoVO.setAdmin(tokenUserInfoDto.getAdmin());

        return userInfoVO;


    }

    private TokenUserInfoDto getTokenUserInfoDto(UserInfo userInfo){
        TokenUserInfoDto tokenUserInfoDto=new TokenUserInfoDto();
        tokenUserInfoDto.setUserId(userInfo.getUserId());
        tokenUserInfoDto.setNickName(userInfo.getNickName());

        String adminEmail=appConfig.getAdminEmails();
        if (adminEmail.equals(userInfo.getEmail()))
        {
            tokenUserInfoDto.setAdmin(true);
        }else{
            tokenUserInfoDto.setAdmin(false);
        }
        return tokenUserInfoDto;
    }




}

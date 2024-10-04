package org.itheima.easychatback.service.Impl;

import org.itheima.easychatback.Utils.StringTools;
import org.itheima.easychatback.entity.UserInfo;
import org.itheima.easychatback.entity.UserInfoBeauty;
import org.itheima.easychatback.entity.enums.BeautyAccountStatusEnum;
import org.itheima.easychatback.entity.enums.UserContactTypeEnum;
import org.itheima.easychatback.mappers.UserInfoBeautyMapper;
import org.itheima.easychatback.mappers.UserInfoMapper;
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




    @Override
    public Map<String, Object> register(String email, String passWord, String nickName) {
        Map<String, Object> result = new HashMap<>();
       UserInfo userInfo=userInfoMapper.selectByEmail(email);
        if(userInfo==null){
            String userId= StringTools.getUserId();
            UserInfoBeauty beautyAccount=userInfoBeautyMapper.selectByEmail(email);
            Boolean userBeautyAccount=null!=beautyAccount&& BeautyAccountStatusEnum.NO_USE.getStatus().equals(beautyAccount.getStatus());
            if(userBeautyAccount){
                userId=UserContactTypeEnum.USER.getPrefix()+beautyAccount.getUserId();

            }
            userInfo=new UserInfo();
            userInfo.setUserId(userId);
            userInfo.setEmail(email);
            userInfo.setNickName(nickName);
            userInfo.setPassWord(StringTools.encodeMD5(passWord));
            userInfo.setCreateTime(LocalDateTime.now());
            userInfo.setLastLoginTime(LocalDateTime.now());
            userInfoMapper.insert(userInfo);
            if(userBeautyAccount){
                UserInfoBeauty updateBeauty=new UserInfoBeauty();
                updateBeauty.setStatus(BeautyAccountStatusEnum.USEED.getStatus());
            }
            //TODO 创建机器人好友

        }else
        {
            result.put("succuess",false);
            result.put("errorKey","邮箱已存在");
            return result;
        }
        return result;


    }

    @Override
    public UserInfo login(String email, String passWord) {
        return null;
    }
}

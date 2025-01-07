package org.itheima.easychatback.redis;

import com.alibaba.fastjson.JSON;
import org.itheima.easychatback.Utils.RedisUtils;
import org.itheima.easychatback.entity.constants.Constants;
import org.itheima.easychatback.entity.dto.SysSettingDto;
import org.itheima.easychatback.entity.dto.TokenUserInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisComponent {

    @Autowired
    private RedisUtils redisUtils;

    public Long getUserHeartBeat(String userId) {
        return (Long)redisUtils.get(Constants.REDIS_KEY_WS_USER_HEART_BEAT+userId);
    }

    public void saveTokenUserInfoDto(TokenUserInfoDto tokenUserInfoDto)
    {
        redisUtils.setEx(Constants.REDIS_KEY_WS_TOKEN+tokenUserInfoDto.getToken(), JSON.toJSONString(tokenUserInfoDto),Constants.REDIS_KEY_EXPIRES_DAY, TimeUnit.SECONDS);
        redisUtils.setEx(Constants.REDIS_KEY_WS_TOKEN_USERID+tokenUserInfoDto.getToken(),tokenUserInfoDto.getToken(),Constants.REDIS_KEY_EXPIRES_DAY, TimeUnit.SECONDS);
    }
    public SysSettingDto getSysSetting() {
        SysSettingDto sysSettingDto=(SysSettingDto)redisUtils.get(Constants.REDIS_KEY_SYS_SETTING);
        sysSettingDto=sysSettingDto==null?new SysSettingDto():sysSettingDto;
        return sysSettingDto;


    }


}

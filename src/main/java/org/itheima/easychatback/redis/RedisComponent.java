package org.itheima.easychatback.redis;

import org.itheima.easychatback.Utils.RedisUtils;
import org.itheima.easychatback.entity.constants.Constants;
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
        redisUtils.setEx(Constants.REDIS_KEY_WS_TOKEN+tokenUserInfoDto.getToken(),tokenUserInfoDto.toString(),Constants.REDIS_KEY_EXPIRES_DAY, TimeUnit.SECONDS);
        redisUtils.setEx(Constants.REDIS_KEY_WS_TOKEN_USERID+tokenUserInfoDto.getToken(),tokenUserInfoDto.getToken(),Constants.REDIS_KEY_EXPIRES_DAY, TimeUnit.SECONDS);
    }


}

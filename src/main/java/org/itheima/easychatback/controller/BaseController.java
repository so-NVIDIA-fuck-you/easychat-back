package org.itheima.easychatback.controller;

import com.alibaba.fastjson.JSON;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.el.parser.Token;
import org.itheima.easychatback.Utils.RedisUtils;
import org.itheima.easychatback.entity.constants.Constants;
import org.itheima.easychatback.entity.dto.TokenUserInfoDto;

public class BaseController {
    @Resource
    private RedisUtils redisUtils;

    protected TokenUserInfoDto getTokenUserInfo(HttpServletRequest request) {
        String token = request.getHeader("token");
        TokenUserInfoDto tokenUserInfoDto = JSON.parseObject((String) redisUtils.get(Constants.REDIS_KEY_WS_TOKEN+token),TokenUserInfoDto.class);
        return tokenUserInfoDto;
    };
}

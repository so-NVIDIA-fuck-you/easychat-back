package org.itheima.easychatback.redis;

import org.itheima.easychatback.Utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RedisComponent {

    @Autowired
    private RedisUtils redisUtils;


}

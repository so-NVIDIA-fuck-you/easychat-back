package org.itheima.easychatback.Utils;

import io.lettuce.core.RedisConnectionException;
import jakarta.annotation.Resource;
import org.apache.ibatis.session.SqlSessionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;

@Component("initRun")
public class InitRun implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(InitRun.class);

    @Resource
    private DataSource dataSource;

    @Resource
    private RedisUtils redisUtils;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        try {
            dataSource.getConnection();
            redisUtils.get("test");
            logger.info("服务启动成功！");
        } catch (SQLException e) {
            logger.error("数据库配置错误");
        }catch (RedisConnectionException e) {
            logger.error("redis配置错误");
        }catch (Exception e) {
            logger.error("服务启动失败:"+e.getMessage());
        }
    }
}

package org.itheima.easychatback;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAsync
@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
@MapperScan(basePackages = "org.itheima.easychatback.mapper")
public class EasychatBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasychatBackApplication.class, args);
    }

}

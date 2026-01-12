package com.iqexception.fxhelper.agg;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.apache.rocketmq.spring.autoconfigure.RocketMQAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jooq.JooqAutoConfiguration;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class,
        RocketMQAutoConfiguration.class, JooqAutoConfiguration.class, RedisAutoConfiguration.class })
@EnableDubbo
public class FxHelperAggApplication {

    public static void main(String[] args) throws UnknownHostException {
        //FIXME
        System.setProperty("nacos.logging.default.config.enabled", "false");
        System.setProperty("HOST_NAME", InetAddress.getLocalHost().getHostName());
        System.setProperty("HOST_IP", InetAddress.getLocalHost().getHostAddress());

        SpringApplication.run(FxHelperAggApplication.class, args);
    }
}

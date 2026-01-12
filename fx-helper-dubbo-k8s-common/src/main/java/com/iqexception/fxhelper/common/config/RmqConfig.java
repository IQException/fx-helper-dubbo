package com.iqexception.fxhelper.common.config;

import com.iqexception.fxhelper.common.JsonMapper;
import com.iqexception.fxhelper.common.RmqHelper;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RmqConfig {

    @Bean
    public RmqHelper rmqHelper(RocketMQTemplate rmqTemplate, JsonMapper jsonMapper)
    {
        return new RmqHelper(rmqTemplate, jsonMapper);
    }
}

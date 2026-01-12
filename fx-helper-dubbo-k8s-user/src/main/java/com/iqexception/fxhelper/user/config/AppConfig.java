package com.iqexception.fxhelper.user.config;

import com.google.common.collect.Lists;
import com.iqexception.fxhelper.common.config.CommonConfig;
import com.iqexception.fxhelper.common.constant.CommonConstants;
import com.iqexception.fxhelper.common.filter.InjectMdcFields;
import io.lettuce.core.resource.ClientResources;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.time.Duration;

@Configuration
@Import({CommonConfig.class})
public class AppConfig {

    @Bean
    public InjectMdcFields injectMDCFromRequestFields() {
        return new InjectMdcFields(Lists.newArrayList(
                Pair.of("userId", CommonConstants.MDC_USER_ID),
                Pair.of("openId", CommonConstants.MDC_OPEN_ID)));
    }
}

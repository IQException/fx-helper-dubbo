package com.iqexception.fxhelper.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({HttpConverterConfig.class, RestTemplateConfig.class,
        ObservationConfig.class, MessageSourceConfig.class, JsonConfig.class,
        JooqConfig.class, RmqConfig.class, RedisConfig.class})
public class CommonConfig {
}

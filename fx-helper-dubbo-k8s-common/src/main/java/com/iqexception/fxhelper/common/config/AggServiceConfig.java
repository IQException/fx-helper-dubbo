package com.iqexception.fxhelper.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({RestTemplateConfig.class, ObservationConfig.class,
        MessageSourceConfig.class, JsonConfig.class})
public class AggServiceConfig {
}

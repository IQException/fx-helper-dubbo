package com.iqexception.fxhelper.order.config;

import com.google.common.collect.Lists;
import com.iqexception.fxhelper.common.config.CommonConfig;
import com.iqexception.fxhelper.common.constant.CommonConstants;
import com.iqexception.fxhelper.common.filter.InjectMdcFields;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({CommonConfig.class})
public class AppConfig {
    @Bean
    public InjectMdcFields injectMDCFromRequestFields() {
        return new InjectMdcFields(Lists.newArrayList(Pair.of("orderId", CommonConstants.MDC_ORDER_ID)));
    }
}

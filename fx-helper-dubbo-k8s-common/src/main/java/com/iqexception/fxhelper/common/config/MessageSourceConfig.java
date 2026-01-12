package com.iqexception.fxhelper.common.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Locale;
@Configuration
public class MessageSourceConfig {
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.addBasenames("i18n/error");
        messageSource.addBasenames("i18n/message");
        messageSource.setDefaultLocale(Locale.CHINA);
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}

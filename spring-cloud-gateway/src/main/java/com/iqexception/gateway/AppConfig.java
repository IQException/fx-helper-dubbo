package com.iqexception.gateway;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iqexception.fxhelper.client.webclient.user.ApiClient;
import com.iqexception.fxhelper.client.webclient.user.api.UserApi;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {

    @Bean
    public ApiClient apiClient(WebClient webClient) {
        return new ApiClient(webClient);
    }

    @Bean
    public UserApi userApi(ApiClient apiClient) {
        return new UserApi(apiClient);
    }

    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        return builder.build();
    }

    @LoadBalanced
    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    public JsonMapper jsonMapper(ObjectMapper mapper) {
        return new JsonMapper(mapper);
    }
}

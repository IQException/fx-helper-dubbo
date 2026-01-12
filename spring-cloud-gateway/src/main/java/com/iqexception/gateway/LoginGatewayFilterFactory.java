package com.iqexception.gateway;

import com.iqexception.fxhelper.client.webclient.user.api.UserApi;
import com.iqexception.fxhelper.client.webclient.user.model.InternalCheckLoginRequest;
import com.iqexception.gateway.dto.BaseResponse;
import com.iqexception.gateway.dto.ResponseStatus;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Component
public class LoginGatewayFilterFactory extends AbstractGatewayFilterFactory<LoginGatewayFilterFactory.Config> {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginGatewayFilterFactory.class);

    private static final String HEADER_USER_TOKEN = "FX-USER-TOKEN";

    private static final String HEADER_USER_ID = "FX-USER-ID";

    private static final String HEADER_OPEN_ID = "FX-OPEN-ID";


    private final UserApi userApi;
    private final JsonMapper jsonMapper;

    public LoginGatewayFilterFactory(UserApi userApi, JsonMapper jsonMapper) {
        super(Config.class);
        this.userApi = userApi;
        this.jsonMapper = jsonMapper;
    }

    @Override
    public GatewayFilter apply(Config config) {

        return (exchange, chain) -> {

            String tokenKey = config == null || StringUtils.isBlank(config.getTokenKey()) ? HEADER_USER_TOKEN : config.getTokenKey();
            String tokenValue = exchange.getRequest().getHeaders().getFirst(tokenKey);
            ServerHttpResponse response = exchange.getResponse();
            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);

            BaseResponse result = new BaseResponse();
            result.setStatus(new ResponseStatus());
            result.getStatus().setErrorCode("100003");
            result.getStatus().setErrorMessage("session expired!");
            if (StringUtils.isBlank(tokenValue)) {
                DataBuffer responseBuffer = response.bufferFactory().wrap(jsonMapper.serialize(result).getBytes(StandardCharsets.UTF_8));
                return response.writeWith(Mono.just(responseBuffer));
            } else {
                return userApi.internalCheckLogin(new InternalCheckLoginRequest().token(tokenValue))
                        .flatMap(ret -> {
                            if (ret.getStatus().getErrorCode().equals("0")) {
                                return chain.filter(exchange.mutate().request(exchange.getRequest().mutate()
                                        .header(HEADER_USER_ID, String.valueOf(ret.getResult().getUserId()))
                                        .header(HEADER_OPEN_ID, ret.getResult().getOpenId())
                                        .build()).build());
                            } else {
                                LOGGER.error("check login error! token:{},errorMessage:{}", tokenValue, ret.getStatus().getErrorMessage());
                                DataBuffer responseBuffer = response.bufferFactory().wrap(jsonMapper.serialize(result).getBytes(StandardCharsets.UTF_8));
                                return response.writeWith(Mono.just(responseBuffer));
                            }
                        })
                        .onErrorResume(throwable -> {
                            LOGGER.error("check login error! token:{}", tokenValue, throwable);
                            result.getStatus().setErrorCode("90001");
                            result.getStatus().setErrorMessage("system error!");
                            DataBuffer responseBuffer = response.bufferFactory().wrap(jsonMapper.serialize(result).getBytes(StandardCharsets.UTF_8));
                            return response.writeWith(Mono.just(responseBuffer));
                        });
            }
        };
    }

    public static class Config {
        private String tokenKey;

        public String getTokenKey() {
            return tokenKey;
        }

        public void setTokenKey(String tokenKey) {
            this.tokenKey = tokenKey;
        }
    }
}

package com.iqexception.fxhelper.pay.stub;

import com.iqexception.fxhelper.api.user.UserService;
import com.iqexception.fxhelper.api.user.VerifyPaySecretRequest;
import com.iqexception.fxhelper.api.user.VerifyPaySecretResponse;
import com.iqexception.fxhelper.common.JsonMapper;
import com.iqexception.fxhelper.common.util.ResponseUtil;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class UserServiceStub {
    private final Logger LOGGER = LoggerFactory.getLogger(UserServiceStub.class);

    @DubboReference
    private UserService userApi;

    private final JsonMapper jsonMapper;

    public UserServiceStub(JsonMapper jsonMapper) {
        this.jsonMapper = jsonMapper;
    }

    public VerifyPaySecretResponse verifyPaySecret(Long userId, String paySecret) {
        return userApi.verifyPaySecret(
                VerifyPaySecretRequest.newBuilder()
                        .setUserId(userId)
                        .setPaySecret(paySecret)
                        .build());
    }

    public boolean verifyPaySecretQuietly(Long userId, String paySecret) {
        try {
            VerifyPaySecretResponse response = verifyPaySecret(userId, paySecret);
            if (ResponseUtil.isSuccess(response.getStatus().getErrorCode())) {
                return response.getResult().getIsValid();
            } else {
                LOGGER.error("verifyPaySecret failed! userId:{},response:{}",
                        userId, jsonMapper.serialize(response));
            }
        } catch (Exception e) {
            LOGGER.error("verifyPaySecret failed! userId:{}", userId, e);
        }
        return false;
    }

}

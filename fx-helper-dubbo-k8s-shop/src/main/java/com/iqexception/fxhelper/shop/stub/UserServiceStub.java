package com.iqexception.fxhelper.shop.stub;

import com.iqexception.fxhelper.api.common.BaseResponse;
import com.iqexception.fxhelper.api.user.UpdatePaySecretRequest;
import com.iqexception.fxhelper.api.user.UserService;
import com.iqexception.fxhelper.common.JsonMapper;
import com.iqexception.fxhelper.common.util.ResponseUtil;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class UserServiceStub {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @DubboReference
    private UserService userApi;

    private final JsonMapper jsonMapper;

    public UserServiceStub(JsonMapper jsonMapper) {
        this.jsonMapper = jsonMapper;
    }

    public boolean updatePaySecretQuietly(Long userId, String paySecret) {

        try {
            BaseResponse response = changePaySecret(userId, paySecret);
            if (ResponseUtil.isSuccess(response.getStatus().getErrorCode())) {
                return true;
            } else {
                LOGGER.error("update pay secret failed! userId:{},response:{}", userId, jsonMapper.serialize(response));
            }
        } catch (Exception e) {
            LOGGER.error("update pay secret failed! userId: {}", userId, e);
        }
        return false;
    }

    public BaseResponse changePaySecret(Long userId, String paySecret) {
        return userApi.updatePaySecret(
                UpdatePaySecretRequest.newBuilder()
                        .setUserId(userId)
                        .setPaySecret(paySecret)
                        .build());
    }
}

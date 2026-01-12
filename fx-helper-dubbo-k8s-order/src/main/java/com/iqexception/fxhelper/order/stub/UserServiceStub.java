package com.iqexception.fxhelper.order.stub;

import com.iqexception.fxhelper.api.user.GetUserRequest;
import com.iqexception.fxhelper.api.user.GetUserResponse;
import com.iqexception.fxhelper.api.user.GetUserResult;
import com.iqexception.fxhelper.api.user.UserService;
import com.iqexception.fxhelper.common.JsonMapper;
import com.iqexception.fxhelper.common.util.ResponseUtil;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;

@Component
public class UserServiceStub {

    private final Logger LOGGER = LoggerFactory.getLogger(UserServiceStub.class);

    @DubboReference
    private UserService userApi;

    public GetUserResult getUserQuietly(Long userId) {
        try {
            GetUserResponse response = getUser(userId);
            if (ResponseUtil.isSuccess(response.getStatus().getErrorCode())) {
                return response.getResult();
            } else {
                LOGGER.error("get user failed! userId:{},response:{}", userId, response);
            }
        } catch (RestClientException e) {
            LOGGER.error("get user failed! userId:{}", userId, e);
        }
        return null;
    }

    public GetUserResponse getUser(Long userId) {
        return userApi.getUser(
                GetUserRequest.newBuilder()
                        .setUserId(userId)
                        .build());
    }
}

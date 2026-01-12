package com.iqexception.fxhelper.agg.stub;

import com.iqexception.fxhelper.api.user.GetUserListRequest;
import com.iqexception.fxhelper.api.user.GetUserListResponse;
import com.iqexception.fxhelper.api.user.UserInfo;
import com.iqexception.fxhelper.api.user.UserService;
import com.iqexception.fxhelper.common.JsonMapper;
import com.iqexception.fxhelper.common.util.ResponseUtil;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class UserServiceStub {

    private final Logger LOGGER = LoggerFactory.getLogger(UserServiceStub.class);

    @DubboReference
    private UserService userApi;

    private final JsonMapper jsonMapper;

    public UserServiceStub(JsonMapper jsonMapper) {
        this.jsonMapper = jsonMapper;
    }

    public GetUserListResponse getUserList(List<Long> userIds) {
        return userApi.getUserList(
                GetUserListRequest.newBuilder()
                        .addAllUserIds(userIds)
                        .build());
    }

    public List<UserInfo> getUserListQuietly(List<Long> userIds) {
        try {
            GetUserListResponse response = getUserList(userIds);
            if (ResponseUtil.isSuccess(response.getStatus().getErrorCode())) {
                return response.getResult().getUserInfosList();
            } else {
                LOGGER.error("getUserListQuietly error! response:{}", jsonMapper.serialize(response));
            }
        } catch (Exception e) {
            LOGGER.error("getUserListQuietly error", e);
        }
        return Collections.emptyList();
    }
}

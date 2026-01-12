package com.iqexception.fxhelper.user.stub;

import com.iqexception.fxhelper.api.account.*;
import com.iqexception.fxhelper.common.JsonMapper;
import com.iqexception.fxhelper.common.util.ResponseUtil;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class AccountServiceStub {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @DubboReference
    private AccountService accountApi;

    private final JsonMapper jsonMapper;

    public AccountServiceStub(JsonMapper jsonMapper) {
        this.jsonMapper = jsonMapper;
    }

    public boolean createAccountQuietly(Long userId) {
        try {
            CreateResponse response = createAccount(userId);
            if (ResponseUtil.isSuccess(response.getStatus().getErrorCode())) {
                return true;
            } else {
                LOGGER.error("create account failed! userId:{},response:{}", userId, jsonMapper.serialize(response));
            }
        } catch (Exception e) {
            LOGGER.error("create account failed! userId:{}", userId, e);
        }
        return false;
    }

    public CreateResponse createAccount(Long userId) {
        return accountApi.create(
                CreateRequest.newBuilder()
                        .setUserId(userId)
                        .build());

    }

    public GetAccountResponse getAccount(Long userId) {
        return accountApi.getAccount(
                GetAccountRequest.newBuilder()
                        .setUserId(userId)
                        .build());
    }

    public GetAccountResult getAccountQuietly(Long userId) {
        try {
            GetAccountResponse response = getAccount(userId);
            if (ResponseUtil.isSuccess(response.getStatus().getErrorCode())) {
                return getAccount(userId).getResult();
            } else {
                LOGGER.error("get account failed! userId:{},response:{}", userId, jsonMapper.serialize(response));
            }
        } catch (Exception e) {
            LOGGER.error("internalQuery error! userId:{}", userId, e);
        }
        return null;
    }

}

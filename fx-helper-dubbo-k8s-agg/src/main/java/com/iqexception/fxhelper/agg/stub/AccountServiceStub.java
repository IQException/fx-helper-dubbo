package com.iqexception.fxhelper.agg.stub;

import com.iqexception.fxhelper.api.account.AccountService;
import com.iqexception.fxhelper.api.account.GetAccountRequest;
import com.iqexception.fxhelper.api.account.GetAccountResponse;
import com.iqexception.fxhelper.api.account.GetAccountResult;
import com.iqexception.fxhelper.common.JsonMapper;
import com.iqexception.fxhelper.common.util.ResponseUtil;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;

@Component
public class AccountServiceStub {


    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @DubboReference
    private AccountService accountApi;

    private final JsonMapper jsonMapper;

    public AccountServiceStub(JsonMapper jsonMapper) {
        this.jsonMapper = jsonMapper;
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
                return response.getResult();
            } else {
                LOGGER.error("get account failed! userId:{},response:{}", userId, jsonMapper.serialize(response));
            }
        } catch (RestClientException e) {
            LOGGER.error("get account failed! userId:{}", userId, e);
        }
        return null;
    }
}

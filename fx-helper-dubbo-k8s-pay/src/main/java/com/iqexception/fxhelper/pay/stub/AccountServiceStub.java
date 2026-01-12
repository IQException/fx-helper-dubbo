package com.iqexception.fxhelper.pay.stub;

import com.iqexception.fxhelper.api.account.*;
import com.iqexception.fxhelper.api.common.BaseResponse;
import com.iqexception.fxhelper.common.JsonMapper;
import com.iqexception.fxhelper.common.util.ResponseUtil;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class AccountServiceStub {

    private final Logger LOGGER = LoggerFactory.getLogger(AccountServiceStub.class);

    @DubboReference
    private AccountService accountApi;

    private final JsonMapper jsonMapper;

    public AccountServiceStub(JsonMapper jsonMapper) {
        this.jsonMapper = jsonMapper;
    }

    public GetAccountResponse getAccount(Long userId) {
        return accountApi.getAccount(
                GetAccountRequest.newBuilder()
                        .setUserId(userId).build());
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

    public BaseResponse incrBalance(Long accountId, BigDecimal amount) {

        return accountApi.incrBalance(
                IncrBalanceRequest.newBuilder()
                        .setAccountId(accountId)
                        .setAmount(amount.setScale(1, RoundingMode.HALF_UP).toString())
                        .build());

    }

    public boolean incrBalanceQuietly(Long accountId, BigDecimal amount) {

        try {
            BaseResponse response = incrBalance(accountId, amount);
            if (ResponseUtil.isSuccess(response.getStatus().getErrorCode())) {
                return true;
            } else {
                LOGGER.error("incrBalance failed! userId:{}, amount:{}, response:{}", accountId, amount, jsonMapper.serialize(response));
            }
        } catch (Exception e) {
            LOGGER.error("internalIncrBalance error! userId:{}, amount:{}", accountId, amount, e);
        }
        return false;
    }
}

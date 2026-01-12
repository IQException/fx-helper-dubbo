package com.iqexception.fxhelper.order.stub;

import com.iqexception.fxhelper.api.pay.Acct2wxRequest;
import com.iqexception.fxhelper.api.pay.Acct2wxResponse;
import com.iqexception.fxhelper.api.pay.PayService;
import com.iqexception.fxhelper.common.JsonMapper;
import com.iqexception.fxhelper.common.util.ResponseUtil;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class PayServiceStub {

    private final Logger LOGGER = LoggerFactory.getLogger(PayServiceStub.class);

    @DubboReference
    private PayService payApi;

    public Acct2wxResponse acct2wx(Long accountId, String openId, BigDecimal amount) {
        return payApi.acct2wx(
                Acct2wxRequest.newBuilder()
                        .setAccountId(accountId)
                        .setOpenId(openId)
                        .setAmount(amount.setScale(1, RoundingMode.HALF_UP).toString()).build());
    }

    public Long acct2wxQuietly(Long accountId, String openId, BigDecimal amount) {
        try {
            Acct2wxResponse response = acct2wx(accountId, openId, amount);
            if (ResponseUtil.isSuccess(response.getStatus().getErrorCode())) {
                return response.getResult().getPayId();
            } else {
                LOGGER.error("acct2wx failed! accountId:{}, openId:{}, amount:{}, response:{}",
                        accountId, openId, amount, response);
            }
        } catch (RestClientException e) {
            LOGGER.error("acct2wx error! accountId:{}, openId:{}, amount:{}", accountId, openId, amount, e);
        }
        return null;
    }
}

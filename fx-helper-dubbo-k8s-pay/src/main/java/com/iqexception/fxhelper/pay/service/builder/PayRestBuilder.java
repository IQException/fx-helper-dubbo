package com.iqexception.fxhelper.pay.service.builder;

import com.iqexception.fxhelper.api.rest.pay.DepositResponse;
import com.iqexception.fxhelper.api.rest.pay.DepositResult;
import com.iqexception.fxhelper.common.util.ResponseUtil;
import com.wechat.pay.java.service.payments.jsapi.model.PrepayWithRequestPaymentResponse;

public class PayRestBuilder {
    public static DepositResponse buildDepositResponse(PrepayWithRequestPaymentResponse response) {
        return DepositResponse.newBuilder()
                .setStatus(ResponseUtil.statusOk())
                .setResult(buildDepositResult(response))
                .build();
    }

    private static DepositResult buildDepositResult(PrepayWithRequestPaymentResponse response) {
        return DepositResult.newBuilder()
                .setAppId(response.getAppId())
                .setPaySign(response.getPaySign())
                .setNonceStr(response.getNonceStr())
                .setTimeStamp(response.getTimeStamp())
                .setPackage(response.getPackageVal())
                .setSignType(response.getSignType())
                .build();
    }
}

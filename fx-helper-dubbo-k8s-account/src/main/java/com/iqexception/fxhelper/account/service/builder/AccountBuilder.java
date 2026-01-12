package com.iqexception.fxhelper.account.service.builder;

import com.iqexception.fxhelper.account.dal.generator.tables.pojos.FxAccount;
import com.iqexception.fxhelper.api.account.GetAccountResponse;
import com.iqexception.fxhelper.api.account.GetAccountResult;
import com.iqexception.fxhelper.common.util.PbUtil;
import com.iqexception.fxhelper.common.util.ResponseUtil;

import java.math.RoundingMode;

public class AccountBuilder {


    public static FxAccount buildNewAccount(Long userId) {

        FxAccount account = new FxAccount();
        account.setUserId(userId);
        return account;

    }

    public static GetAccountResult buildGetAccountResult(FxAccount account) {
        return GetAccountResult.newBuilder()
                .setAccountId(account.getAccountId())
                .setStatus(account.getStatus())
                .setBalance(account.getBalance().setScale(1, RoundingMode.HALF_UP).toString())
                .setUserId(account.getUserId())
                .setCreatedAt(PbUtil.toTimestamp(account.getCreatedAt()))
                .setUpdatedAt(PbUtil.toTimestamp(account.getUpdatedAt()))
                .build();
    }


    public static GetAccountResponse buildGetAccountResponse(FxAccount account) {
        return GetAccountResponse.newBuilder()
                .setStatus(ResponseUtil.statusOk())
                .setResult(buildGetAccountResult(account))
                .build();

    }

}

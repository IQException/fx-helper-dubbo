package com.iqexception.fxhelper.pay.service;

import com.iqexception.fxhelper.api.common.BaseResponse;
import com.iqexception.fxhelper.api.rest.pay.DepositRequest;
import com.iqexception.fxhelper.api.rest.pay.DepositResponse;
import com.iqexception.fxhelper.api.rest.pay.WithdrawRequest;

public interface PayRestService {
    BaseResponse withdraw(WithdrawRequest request);

    DepositResponse deposit(DepositRequest request);
}

package com.iqexception.fxhelper.account.service;

import com.iqexception.fxhelper.api.account.*;
import com.iqexception.fxhelper.api.common.BaseResponse;


public interface AccountService {


    CreateResponse create(CreateRequest request);

    GetAccountResponse getAccount(GetAccountRequest request);

    BaseResponse transfer(TransferRequest request);

    BaseResponse incrBalance(IncrBalanceRequest request);
}

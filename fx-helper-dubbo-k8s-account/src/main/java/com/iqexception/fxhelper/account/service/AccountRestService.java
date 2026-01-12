package com.iqexception.fxhelper.account.service;

import com.iqexception.fxhelper.api.common.BaseRequest;
import com.iqexception.fxhelper.api.rest.account.GetAccountResponse;

public interface AccountRestService {
    GetAccountResponse getAccount(BaseRequest request);
}

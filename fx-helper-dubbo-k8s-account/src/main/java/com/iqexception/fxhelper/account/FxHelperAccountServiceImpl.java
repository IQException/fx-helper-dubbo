package com.iqexception.fxhelper.account;

import com.iqexception.fxhelper.account.service.AccountService;
import com.iqexception.fxhelper.api.account.*;
import com.iqexception.fxhelper.api.common.BaseResponse;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService
public class FxHelperAccountServiceImpl extends DubboAccountServiceTriple.AccountServiceImplBase {

    private final AccountService accountService;

    public FxHelperAccountServiceImpl(AccountService accountService) {
        this.accountService = accountService;
    }

    public CreateResponse create(CreateRequest request) {
        return accountService.create(request);
    }

    public GetAccountResponse getAccount(GetAccountRequest request) {
        return accountService.getAccount(request);
    }

    public BaseResponse transfer(TransferRequest request) {
        return accountService.transfer(request);
    }

    public BaseResponse incrBalance(IncrBalanceRequest request) {
        return accountService.incrBalance(request);
    }
}

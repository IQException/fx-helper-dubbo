package com.iqexception.fxhelper.account;

import com.iqexception.fxhelper.account.service.AccountRestService;
import com.iqexception.fxhelper.api.common.BaseRequest;
import com.iqexception.fxhelper.api.rest.account.DubboAccountRestServiceTriple;
import com.iqexception.fxhelper.api.rest.account.GetAccountResponse;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@DubboService
public class FxHelperAccountRestServiceImpl extends DubboAccountRestServiceTriple.AccountRestServiceImplBase {

    private final AccountRestService accountRestService;

    public FxHelperAccountRestServiceImpl(AccountRestService accountRestService) {
        this.accountRestService = accountRestService;
    }

    @PostMapping("/get_account")
    public GetAccountResponse getAccount(@RequestBody BaseRequest request) {
        return accountRestService.getAccount(request);
    }
}

package com.iqexception.fxhelper.pay;

import com.iqexception.fxhelper.api.common.BaseResponse;
import com.iqexception.fxhelper.api.rest.pay.DepositRequest;
import com.iqexception.fxhelper.api.rest.pay.DepositResponse;
import com.iqexception.fxhelper.api.rest.pay.DubboPayRestServiceTriple;
import com.iqexception.fxhelper.api.rest.pay.WithdrawRequest;
import com.iqexception.fxhelper.pay.service.PayRestService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@DubboService
public class FxHelperPayRestServiceImpl extends DubboPayRestServiceTriple.PayRestServiceImplBase {

    private final PayRestService payRestService;

    public FxHelperPayRestServiceImpl(PayRestService payRestService) {
        this.payRestService = payRestService;
    }
    @PostMapping("/withdraw")
    public BaseResponse withdraw(@RequestBody WithdrawRequest request) {
        return payRestService.withdraw(request);
    }
    @PostMapping("/deposit")
    public DepositResponse deposit(@RequestBody DepositRequest request) {
        return payRestService.deposit(request);
    }
}

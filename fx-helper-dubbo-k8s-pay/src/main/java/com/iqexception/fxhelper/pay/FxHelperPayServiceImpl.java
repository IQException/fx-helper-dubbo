package com.iqexception.fxhelper.pay;


import com.iqexception.fxhelper.api.pay.Acct2wxRequest;
import com.iqexception.fxhelper.api.pay.Acct2wxResponse;
import com.iqexception.fxhelper.api.pay.DubboPayServiceTriple;
import com.iqexception.fxhelper.pay.service.PayService;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService
public class FxHelperPayServiceImpl extends DubboPayServiceTriple.PayServiceImplBase {

    private final PayService payService;

    public FxHelperPayServiceImpl(PayService payService) {
        this.payService = payService;
    }

    public Acct2wxResponse acct2wx(Acct2wxRequest request) {
        return payService.acct2wx(request);
    }
}

package com.iqexception.fxhelper.pay.service;



import com.iqexception.fxhelper.api.pay.Acct2wxRequest;
import com.iqexception.fxhelper.api.pay.Acct2wxResponse;
import com.iqexception.fxhelper.pay.dal.generator.tables.pojos.FxPayInfo;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

public interface PayService {


    ResponseEntity<String> payNotify(String body,
                                     String sign,
                                     String serial,
                                     String nonce,
                                     String timestamp,
                                     String signType);

    Acct2wxResponse acct2wx(Acct2wxRequest request);

    FxPayInfo acct2wx(long accountId, String openId, BigDecimal amount);
}

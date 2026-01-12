package com.iqexception.fxhelper.pay.service.impl;


import com.iqexception.fxhelper.api.account.GetAccountResult;
import com.iqexception.fxhelper.api.common.BaseResponse;
import com.iqexception.fxhelper.api.pay.Acct2wxRequest;
import com.iqexception.fxhelper.api.pay.Acct2wxResponse;
import com.iqexception.fxhelper.api.pay.Acct2wxResult;
import com.iqexception.fxhelper.api.rest.pay.DepositRequest;
import com.iqexception.fxhelper.api.rest.pay.DepositResponse;
import com.iqexception.fxhelper.api.rest.pay.WithdrawRequest;
import com.iqexception.fxhelper.api.shop.ShopInfo;
import com.iqexception.fxhelper.common.BaseService;
import com.iqexception.fxhelper.common.JsonMapper;
import com.iqexception.fxhelper.common.TLVarManager;
import com.iqexception.fxhelper.common.constant.ErrorCode;
import com.iqexception.fxhelper.common.constant.PayChannel;
import com.iqexception.fxhelper.pay.constant.BillType;
import com.iqexception.fxhelper.pay.dal.ext.FxPayInfoExtDao;
import com.iqexception.fxhelper.pay.dal.generator.tables.pojos.FxPayInfo;
import com.iqexception.fxhelper.pay.service.PayRestService;
import com.iqexception.fxhelper.pay.service.PayService;
import com.iqexception.fxhelper.pay.service.builder.PayBuilder;
import com.iqexception.fxhelper.pay.stub.AccountServiceStub;
import com.iqexception.fxhelper.pay.stub.ShopServiceStub;
import com.iqexception.fxhelper.pay.wx.WxMsgService;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class FakePayService extends BaseService implements PayService, PayRestService {


    private final FxPayInfoExtDao payInfoExtDao;

    private final AccountServiceStub accountServiceStub;

    private final WxMsgService wxMsgService;

    private final ShopServiceStub shopServiceStub;

    public FakePayService(MessageSource messageSource,
                          JsonMapper jsonMapper,
                          FxPayInfoExtDao payInfoExtDao,
                          AccountServiceStub accountServiceStub,
                          WxMsgService wxMsgService,
                          ShopServiceStub shopServiceStub) {
        super(messageSource, jsonMapper);
        this.payInfoExtDao = payInfoExtDao;
        this.accountServiceStub = accountServiceStub;
        this.shopServiceStub = shopServiceStub;
        this.wxMsgService = wxMsgService;
    }

    @Override
    public DepositResponse deposit(DepositRequest request) {

        Long userId = TLVarManager.getUserId();
        String openId = TLVarManager.getOpenId();

        GetAccountResult account = accountServiceStub.getAccountQuietly(userId);
        if (account == null)
            return DepositResponse.newBuilder()
                    .setStatus(status(ErrorCode.REQUEST_ERROR))
                    .build();

        FxPayInfo payInfo = new FxPayInfo();
        payInfo.setAmount(new BigDecimal(request.getParam().getAmount()));
        payInfo.setFromAccount(openId);
        payInfo.setFromChannel(PayChannel.WX.getVal());
        payInfo.setToAccount(String.valueOf(account.getAccountId()));
        payInfo.setToChannel(PayChannel.ACCOUNT.getVal());
        payInfo.setBizId(openId + PayBuilder.BIZ_ID_SEPARATOR_WX + System.currentTimeMillis());
        //fake
        payInfo.setFromTrxId("fake");
        payInfo.setStatus("fake");
        // FIXME 状态管理
        payInfoExtDao.insert(payInfo);

        accountServiceStub.incrBalanceQuietly(account.getAccountId(), new BigDecimal(request.getParam().getAmount()));

        List<ShopInfo> shops = shopServiceStub.getShopListQuietly(userId);
        wxMsgService.sendBillMessage(BillType.DEPOSIT, shops.getFirst().getShopId(), shops.getFirst().getShopName(),
                openId, new BigDecimal(request.getParam().getAmount()), payInfo.getId(), LocalDateTime.now());

        return DepositResponse.newBuilder()
                .setStatus(statusOk())
                .build();
    }

    @Override
    public BaseResponse withdraw(WithdrawRequest request) {

        Long userId = TLVarManager.getUserId();
        String openId = TLVarManager.getOpenId();

        GetAccountResult account = accountServiceStub.getAccountQuietly(userId);
        if (account == null)
            return response(ErrorCode.REQUEST_ERROR);

        FxPayInfo payInfo = acct2wx(account.getAccountId(), openId, new BigDecimal(request.getParam().getAmount()));

        // FIXME 状态管理
        List<ShopInfo> shops = shopServiceStub.getShopListQuietly(userId);
        wxMsgService.sendBillMessage(
                BillType.WITHDRAW, shops.getFirst().getShopId(), shops.getFirst().getShopName(),
                openId, new BigDecimal(request.getParam().getAmount()),
                payInfo.getId(), LocalDateTime.now());

        return responseOk();
    }

    @Override
    public FxPayInfo acct2wx(long accountId, String openId, BigDecimal amount) {

        //生成订单
        FxPayInfo payInfo = PayBuilder.build(amount, accountId, openId);
        payInfoExtDao.insert(payInfo);

        //先扣余额
        boolean success = accountServiceStub.incrBalanceQuietly(accountId, amount.negate());
        // FIXME 状态管理
//        if(!success){
//            payInfoExtDao.updateStatus();
//        }

        // FIXME 转账
        // wxApi.initTransfer(payInfo.getId(), amount, openId);
        // FIXME 状态管理
        // TODO
        return payInfo;
    }

    @Override
    public ResponseEntity<String> payNotify(String body,
                                            String sign,
                                            String serial,
                                            String nonce,
                                            String timestamp,
                                            String signType) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public Acct2wxResponse acct2wx(Acct2wxRequest request) {

        FxPayInfo payInfo = acct2wx(request.getAccountId(), request.getOpenId(), new BigDecimal(request.getAmount()));

        return Acct2wxResponse.newBuilder()
                .setStatus(statusOk())
                .setResult(Acct2wxResult.newBuilder().setPayId(payInfo.getId()))
                .build();
    }
}

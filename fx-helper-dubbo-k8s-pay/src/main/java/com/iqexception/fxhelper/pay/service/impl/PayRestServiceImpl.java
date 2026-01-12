package com.iqexception.fxhelper.pay.service.impl;

import com.iqexception.fxhelper.api.account.GetAccountResult;
import com.iqexception.fxhelper.api.common.BaseResponse;
import com.iqexception.fxhelper.api.rest.pay.DepositRequest;
import com.iqexception.fxhelper.api.rest.pay.DepositResponse;
import com.iqexception.fxhelper.api.rest.pay.WithdrawRequest;
import com.iqexception.fxhelper.common.BaseService;
import com.iqexception.fxhelper.common.JsonMapper;
import com.iqexception.fxhelper.common.RmqHelper;
import com.iqexception.fxhelper.common.TLVarManager;
import com.iqexception.fxhelper.common.constant.ErrorCode;
import com.iqexception.fxhelper.pay.dal.ext.FxPayInfoExtDao;
import com.iqexception.fxhelper.pay.dal.generator.tables.pojos.FxPayInfo;
import com.iqexception.fxhelper.pay.service.PayRestService;
import com.iqexception.fxhelper.pay.service.PayService;
import com.iqexception.fxhelper.pay.service.builder.PayBuilder;
import com.iqexception.fxhelper.pay.service.builder.PayRestBuilder;
import com.iqexception.fxhelper.pay.stub.AccountServiceStub;
import com.iqexception.fxhelper.pay.wx.WxPayApi;
import com.wechat.pay.java.service.payments.jsapi.model.PrepayWithRequestPaymentResponse;
import org.springframework.context.MessageSource;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PayRestServiceImpl extends BaseService implements PayRestService {

    private final FxPayInfoExtDao payInfoExtDao;

    private final AccountServiceStub accountServiceStub;

    private final WxPayApi wxPayApi;

    private final PayService payService;

    public PayRestServiceImpl(MessageSource messageSource,
                              JsonMapper jsonMapper,
                              StringRedisTemplate redisTemplate,
                              RmqHelper rmqHelper,
                              WxPayApi wxPayApi,
                              FxPayInfoExtDao payInfoExtDao,
                              PayService payService,
                              AccountServiceStub accountServiceStub) {
        super(messageSource, jsonMapper, redisTemplate, rmqHelper);
        this.wxPayApi = wxPayApi;
        this.accountServiceStub = accountServiceStub;
        this.payInfoExtDao = payInfoExtDao;
        this.payService = payService;
    }


    @Override
    public BaseResponse withdraw(WithdrawRequest request) {

        Long userId = TLVarManager.getUserId();
        String openId = TLVarManager.getOpenId();

        GetAccountResult account = accountServiceStub.getAccountQuietly(userId);
        if (account == null) {
            return response(ErrorCode.REQUEST_ERROR);
        }

        payService.acct2wx(account.getAccountId(), openId, new BigDecimal(request.getParam().getAmount()));
        return responseOk();
    }

    @Override
    public DepositResponse deposit(DepositRequest request) {
        //https://pay.weixin.qq.com/wiki/doc/apiv3/assets/img/pay/wechatpay/6_2.png

        Long userId = TLVarManager.getUserId();
        String openId = TLVarManager.getOpenId();

        // FIXME
        GetAccountResult account = accountServiceStub.getAccountQuietly(userId);
        if (account == null) {
            return DepositResponse.newBuilder()
                    .setStatus(status(ErrorCode.REQUEST_ERROR))
                    .build();
        }
        //生成订单
        FxPayInfo payInfo = PayBuilder.build(new BigDecimal(request.getParam().getAmount()),
                openId, account.getAccountId());
        // FIXME 状态管理
        payInfoExtDao.insert(payInfo);
        //请求微信支付，获得预付单标识
        //生成带签名支付信息
        PrepayWithRequestPaymentResponse response =
                wxPayApi.prepay("充值",
                        payInfo.getId().toString(),
                        LocalDateTime.now().plusMinutes(30),
                        new BigDecimal(request.getParam().getAmount()), openId);
        //返回支付信息
        return PayRestBuilder.buildDepositResponse(response);

        //用户调用wx.requestPayment发起支付
        //支付成功后回调接口
        //调用查单接口查询支付结果

    }
}

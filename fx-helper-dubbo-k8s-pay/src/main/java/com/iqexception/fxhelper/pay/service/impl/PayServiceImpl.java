package com.iqexception.fxhelper.pay.service.impl;

import com.iqexception.fxhelper.api.pay.Acct2wxRequest;
import com.iqexception.fxhelper.api.pay.Acct2wxResponse;
import com.iqexception.fxhelper.api.pay.Acct2wxResult;
import com.iqexception.fxhelper.common.BaseService;
import com.iqexception.fxhelper.common.JsonMapper;
import com.iqexception.fxhelper.common.RmqHelper;
import com.iqexception.fxhelper.pay.dal.ext.FxPayInfoExtDao;
import com.iqexception.fxhelper.pay.dal.generator.tables.pojos.FxPayInfo;
import com.iqexception.fxhelper.pay.service.PayService;
import com.iqexception.fxhelper.pay.service.builder.PayBuilder;
import com.iqexception.fxhelper.pay.stub.AccountServiceStub;
import com.iqexception.fxhelper.pay.wx.WxPayApi;
import com.wechat.pay.java.core.exception.ValidationException;
import com.wechat.pay.java.core.notification.RequestParam;
import com.wechat.pay.java.service.partnerpayments.jsapi.model.Transaction;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

public class PayServiceImpl extends BaseService implements PayService {

    private final FxPayInfoExtDao payInfoExtDao;

    private final AccountServiceStub accountServiceStub;

    private final WxPayApi wxPayApi;

    public PayServiceImpl(MessageSource messageSource,
                          JsonMapper jsonMapper,
                          StringRedisTemplate redisTemplate,
                          RmqHelper rmqHelper,
                          WxPayApi wxPayApi,
                          FxPayInfoExtDao payInfoExtDao,
                          AccountServiceStub accountServiceStub) {
        super(messageSource, jsonMapper, redisTemplate, rmqHelper);
        this.wxPayApi = wxPayApi;
        this.accountServiceStub = accountServiceStub;
        this.payInfoExtDao = payInfoExtDao;
    }

    @Override
    public ResponseEntity<String> payNotify(String body,
                                            String sign,
                                            String serial,
                                            String nonce,
                                            String timestamp,
                                            String signType) {
        // 构造 RequestParam
        RequestParam requestParam = new RequestParam.Builder()
                .serialNumber(serial)
                .nonce(nonce)
                .signature(sign)
                .timestamp(timestamp)
                .body(body)
                .build();

        try {
            // 以支付通知回调为例，验签、解密并转换成 Transaction
            Transaction trx = wxPayApi.parse(requestParam, Transaction.class);
            // 更新转出交易状态
            FxPayInfo payInfo = payInfoExtDao.fetchOneById(Long.valueOf(trx.getOutTradeNo()));
            if (StringUtils.isNotBlank(payInfo.getFromTrxId())) {
                //防止重复处理
                return ResponseEntity.status(HttpStatus.OK).build();
            }
            payInfo.setFromTrxId(trx.getTransactionId());
            payInfo.setStatus(trx.getTradeState().name());
            payInfoExtDao.update(payInfo);
            // 创建转入交易（增加账户余额）
            // FIXME 异步处理
            if (trx.getTradeState() == Transaction.TradeStateEnum.SUCCESS) {
                accountServiceStub.incrBalanceQuietly(
                        Long.valueOf(trx.getOutTradeNo()),
                        new BigDecimal(trx.getAmount().getTotal() / 100));

            }
        } catch (ValidationException e) {
            // 签名验证失败，返回 401 UNAUTHORIZED 状态码
            LOG.error("sign verification failed", e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (Exception e) {
            // 如果处理失败，应返回 4xx/5xx 的状态码，例如 500 INTERNAL_SERVER_ERROR
            LOG.error("pay notify failed", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        // 处理成功，返回 200 OK 状态码
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


    @Override
    public FxPayInfo acct2wx(long accountId, String openId, BigDecimal amount) {

        //生成订单
        FxPayInfo payInfo = PayBuilder.build(amount, accountId, openId);
        // FIXME 状态管理
        payInfoExtDao.insert(payInfo);
        // FIXME SAGA事务
        //先扣余额
        accountServiceStub.incrBalanceQuietly(accountId, amount.negate());
        // FIXME
        //转账
        wxPayApi.initTransfer(payInfo.getId(), amount, openId);
        //TODO 状态管理

        return payInfo;
    }
}

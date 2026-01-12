package com.iqexception.fxhelper.account.service.impl;

import com.iqexception.fxhelper.account.constant.BizErrorCode;
import com.iqexception.fxhelper.account.dal.ext.FxAccountExtDao;
import com.iqexception.fxhelper.account.dal.generator.tables.pojos.FxAccount;
import com.iqexception.fxhelper.account.service.AccountService;
import com.iqexception.fxhelper.account.service.builder.AccountBuilder;
import com.iqexception.fxhelper.api.account.*;
import com.iqexception.fxhelper.api.common.BaseResponse;
import com.iqexception.fxhelper.common.BaseService;
import com.iqexception.fxhelper.common.BizException;
import com.iqexception.fxhelper.common.JsonMapper;
import com.iqexception.fxhelper.common.constant.ErrorCode;
import com.iqexception.fxhelper.common.util.ResponseUtil;
import org.jooq.DSLContext;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountServiceImpl extends BaseService implements AccountService {

    private final FxAccountExtDao accountExtDao;

    private final DSLContext jooqClient;

    public AccountServiceImpl(MessageSource messageSource,
                              JsonMapper jsonMapper,
                              DSLContext jooqClient,
                              FxAccountExtDao accountExtDao) {
        super(messageSource, jsonMapper);
        this.jooqClient = jooqClient;
        this.accountExtDao = accountExtDao;

    }

    @Override
    public CreateResponse create(CreateRequest request) {
        //幂等
        FxAccount account = accountExtDao.fetchOneByUserId(request.getUserId());
        if (account == null) {
            account = AccountBuilder.buildNewAccount(request.getUserId());
            accountExtDao.insert(account);
        }
        CreateResult result = CreateResult.newBuilder()
                .setAccountId(account.getAccountId())
                .build();
        return CreateResponse.newBuilder()
                .setStatus(ResponseUtil.statusOk())
                .setResult(result)
                .build();
    }

    @Override
    public BaseResponse incrBalance(IncrBalanceRequest request) {
        FxAccount account = accountExtDao.fetchOneByAccountId(request.getAccountId());
        if (account == null)
            return response(ErrorCode.PARAM_ERROR);

        int ret = accountExtDao.incrBalanceByAcctId(request.getAccountId(), new BigDecimal(request.getAmount()));
        return ret == 1 ? responseOk() : response(BizErrorCode.ACCOUNT_BALANCE_INSUFFICIENT);
    }

    @Override
    public BaseResponse transfer(TransferRequest request) {

        try {
            jooqClient.transaction(conf -> {
                FxAccount account = accountExtDao.fetchOneByUserId(request.getFromAccountId());
                //转出账户不存在
                if (account == null) {
                    throw new BizException(BizErrorCode.ACCOUNT_FROM_NOT_EXIST);
                }

                int decr = accountExtDao.incrBalanceByAcctId(
                        request.getFromAccountId(),
                        new BigDecimal(request.getAmount()).negate());

                //转出账户余额不足
                if (decr == 0) {
                    throw new BizException(BizErrorCode.ACCOUNT_BALANCE_INSUFFICIENT);
                }

                int incr = accountExtDao.incrBalanceByAcctId(
                        request.getToAccountId(),
                        new BigDecimal(request.getAmount()));
                //转入账户不存在
                if (incr == 0) {
                    throw new BizException(BizErrorCode.ACCOUNT_TO_NOT_EXIST);
                }
            });

        } catch (BizException e) {
            return response(status(e.getErrorCode()));
        }

        return responseOk();
    }

    @Override
    public GetAccountResponse getAccount(GetAccountRequest request) {
        if (request.getAccountId() <= 0 && request.getUserId() <= 0) {
            return GetAccountResponse.newBuilder()
                    .setStatus(status(ErrorCode.PARAM_ERROR))
                    .build();
        }
        FxAccount account;
        if (request.getAccountId() > 0) {
            account = accountExtDao.fetchOneByAccountId(request.getAccountId());
        } else {
            account = accountExtDao.fetchOneByUserId(request.getUserId());
        }
        if (account == null)
            return GetAccountResponse.newBuilder()
                    .setStatus(status(ErrorCode.PARAM_ERROR))
                    .build();

        return AccountBuilder.buildGetAccountResponse(account);
    }


}

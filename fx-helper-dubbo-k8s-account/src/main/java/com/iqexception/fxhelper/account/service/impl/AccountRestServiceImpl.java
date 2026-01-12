package com.iqexception.fxhelper.account.service.impl;

import com.iqexception.fxhelper.account.dal.ext.FxAccountExtDao;
import com.iqexception.fxhelper.account.dal.generator.tables.pojos.FxAccount;
import com.iqexception.fxhelper.account.service.AccountRestService;
import com.iqexception.fxhelper.account.service.builder.AccountRestBuilder;
import com.iqexception.fxhelper.api.common.BaseRequest;
import com.iqexception.fxhelper.api.rest.account.GetAccountResponse;
import com.iqexception.fxhelper.common.BaseService;
import com.iqexception.fxhelper.common.JsonMapper;
import com.iqexception.fxhelper.common.TLVarManager;
import com.iqexception.fxhelper.common.constant.ErrorCode;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
public class AccountRestServiceImpl extends BaseService implements AccountRestService {

    private final FxAccountExtDao accountExtDao;

    public AccountRestServiceImpl(MessageSource messageSource,
                                  JsonMapper jsonMapper,
                                  FxAccountExtDao accountExtDao) {
        super(messageSource, jsonMapper);
        this.accountExtDao = accountExtDao;

    }

    @Override
    public GetAccountResponse getAccount(BaseRequest request) {
        FxAccount account = accountExtDao.fetchOneByUserId(TLVarManager.getUserId());
        if (account == null)
            return GetAccountResponse.newBuilder()
                    .setStatus(status(ErrorCode.PARAM_ERROR))
                    .build();

        return AccountRestBuilder.buildGetAccountResponse(account);
    }

}

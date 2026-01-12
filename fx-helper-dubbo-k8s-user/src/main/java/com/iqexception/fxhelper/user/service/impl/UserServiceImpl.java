package com.iqexception.fxhelper.user.service.impl;

import com.iqexception.fxhelper.api.common.BaseResponse;
import com.iqexception.fxhelper.api.user.*;
import com.iqexception.fxhelper.common.BaseService;
import com.iqexception.fxhelper.common.JsonMapper;
import com.iqexception.fxhelper.common.RmqHelper;
import com.iqexception.fxhelper.user.constant.BizErrorCode;
import com.iqexception.fxhelper.user.dal.ext.FxUserExtDao;
import com.iqexception.fxhelper.user.dal.generator.tables.pojos.FxUser;
import com.iqexception.fxhelper.user.service.UserService;
import com.iqexception.fxhelper.user.service.builder.UserBuilder;
import com.iqexception.fxhelper.user.stub.AccountServiceStub;
import com.iqexception.fxhelper.user.wx.WxApi;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends BaseService implements UserService {

    private static final int TOKEN_TTL_SECONDS = 60 * 60 * 24 * 7;

    private final FxUserExtDao userExtDao;

    @Value("${rocketmq.producer.topic.create}")
    private String RMQ_TOPIC_CREATE_USER;

    public UserServiceImpl(MessageSource messageSource,
                           JsonMapper jsonMapper,
                           StringRedisTemplate redisTemplate,
                           RmqHelper rmqHelper,
                           FxUserExtDao userExtDao) {

        super(messageSource, jsonMapper, redisTemplate, rmqHelper);
        this.userExtDao = userExtDao;
    }

    @Override
    public VerifyPaySecretResponse verifyPaySecret(VerifyPaySecretRequest request) {

        FxUser user = userExtDao.fetchOneByUserId(request.getUserId());

        if (user == null)
            return VerifyPaySecretResponse.newBuilder()
                    .setStatus(status(BizErrorCode.USER_NOT_EXIST))
                    .build();

        VerifyPaySecretResult result = VerifyPaySecretResult.newBuilder()
                .setIsValid(user.getPaySecret().equals(UserBuilder.encryptPaySecret(request.getPaySecret())))
                .build();
        return VerifyPaySecretResponse.newBuilder()
                .setStatus(statusOk())
                .setResult(result)
                .build();

    }

    @Override
    public CheckLoginResponse checkLogin(CheckLoginRequest request) {

        String tokenValue = redisTemplate.opsForValue().get(UserBuilder.buildTokenKey(request.getToken()));
        if (StringUtils.isBlank(tokenValue)) {
            LOG.error("token invalid: {}", request.getToken());
            return CheckLoginResponse.newBuilder()
                    .setStatus(status(BizErrorCode.USER_SESSION_EXPIRED))
                    .build();
        }
        Pair<Long, String> pair = UserBuilder.parseTokenValue(tokenValue);
        CheckLoginResult result = CheckLoginResult.newBuilder()
                .setUserId(pair.getLeft())
                .setOpenId(pair.getRight())
                .build();
        return CheckLoginResponse.newBuilder()
                .setStatus(statusOk())
                .setResult(result)
                .build();
    }

    @Override
    public BaseResponse updatePaySecret(UpdatePaySecretRequest request) {

        FxUser user = userExtDao.fetchOneByUserId(request.getUserId());

        if (user == null)
            return response(BizErrorCode.USER_NOT_EXIST);

        userExtDao.updatePaySecret(request.getUserId(), UserBuilder.encryptPaySecret(request.getPaySecret()));

        return responseOk();
    }

    @Override
    public GetUserResponse getUser(GetUserRequest request) {
        FxUser user = userExtDao.fetchOneByUserId(request.getUserId());

        if (user == null)
            return GetUserResponse.newBuilder()
                    .setStatus(status(BizErrorCode.USER_NOT_EXIST))
                    .build();

        return UserBuilder.buildGetUserResponse(user);
    }

    @Override
    public GetUserListResponse getUserList(GetUserListRequest request) {
        List<FxUser> users = userExtDao.fetchByUserId(request.getUserIdsList().toArray(new Long[0]));
        return UserBuilder.buildGetUserListResponse(users);
    }


}

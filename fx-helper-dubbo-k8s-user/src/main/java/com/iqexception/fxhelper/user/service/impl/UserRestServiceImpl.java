package com.iqexception.fxhelper.user.service.impl;

import com.iqexception.fxhelper.api.rest.user.LoginRequest;
import com.iqexception.fxhelper.api.rest.user.LoginResponse;
import com.iqexception.fxhelper.api.rest.user.LoginResult;
import com.iqexception.fxhelper.common.BaseService;
import com.iqexception.fxhelper.common.JsonMapper;
import com.iqexception.fxhelper.common.RmqHelper;
import com.iqexception.fxhelper.common.constant.ErrorCode;
import com.iqexception.fxhelper.user.dal.ext.FxUserExtDao;
import com.iqexception.fxhelper.user.dal.generator.tables.pojos.FxUser;
import com.iqexception.fxhelper.user.service.UserRestService;
import com.iqexception.fxhelper.user.service.builder.UserBuilder;
import com.iqexception.fxhelper.user.stub.AccountServiceStub;
import com.iqexception.fxhelper.user.wx.WxApi;
import com.iqexception.fxhelper.user.wx.model.Code2SessionResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class UserRestServiceImpl extends BaseService implements UserRestService {

    private static final int TOKEN_TTL_SECONDS = 60 * 60 * 24 * 7;

    private final WxApi wxApi;

    private final FxUserExtDao userExtDao;

    private final AccountServiceStub accountServiceStub;

    @Value("${rocketmq.producer.topic.create}")
    private String RMQ_TOPIC_CREATE_USER;

    public UserRestServiceImpl(MessageSource messageSource,
                               JsonMapper jsonMapper,
                               StringRedisTemplate redisTemplate,
                               RmqHelper rmqHelper,
                               WxApi wxApi,
                               FxUserExtDao userExtDao,
                               AccountServiceStub accountServiceStub) {

        super(messageSource, jsonMapper, redisTemplate, rmqHelper);
        this.wxApi = wxApi;
        this.userExtDao = userExtDao;
        this.accountServiceStub = accountServiceStub;
    }

    @Override
    public LoginResponse login(LoginRequest request) {

        Code2SessionResponse resp = wxApi.code2Session(request.getParam().getCode());

        String openId = resp.getOpenId();
        if (StringUtils.isBlank(openId)) {
            LOG.error("code2Session error: {}", jsonMapper.serialize(resp));
            return LoginResponse.newBuilder()
                    .setStatus(status(ErrorCode.SYSTEM_ERROR))
                    .build();
        }

        FxUser user = userExtDao.fetchOneByOpenId(openId);
        if (user != null) {
            //已存在的用户更新token
            user.setSessionKey(refreshToken(user.getUserId(), openId, user.getSessionKey()));
            userExtDao.update(user);
            LoginResult result = LoginResult.newBuilder()
                    .setToken(user.getSessionKey())
                    .setNewUser(false)
                    .build();
            return LoginResponse.newBuilder()
                    .setStatus(statusOk())
                    .setResult(result)
                    .build();
        } else {
            // 创建新用户
            // insert user
            FxUser fakeUser = UserBuilder.buildUserByFakeData(openId);
            userExtDao.insert(fakeUser);
            // save token
            String token = generateAndSaveToken(fakeUser.getUserId(), openId);
            userExtDao.updateSessionKey(fakeUser.getUserId(), token);
            // 调用接口创建用户账户（若有异步接口，调用异步接口;同步接口要屏蔽掉异常，允许失败）
            accountServiceStub.createAccountQuietly(fakeUser.getUserId());
            // send message （兜底）。 在本项目中，只在此处做了这种处理，其他业务应当也这样兜底，但是简单（偷懒）起见，只做了同步调用
            rmqHelper.asyncSend(RMQ_TOPIC_CREATE_USER, Map.of("userId", fakeUser.getUserId()));

            LoginResult result = LoginResult.newBuilder()
                    .setToken(token)
                    .setNewUser(true)
                    .build();
            return LoginResponse.newBuilder()
                    .setStatus(statusOk())
                    .setResult(result)
                    .build();
        }
    }

    public String refreshToken(Long userId, String openId, String oldToken) {
        invalidateToken(oldToken);
        return generateAndSaveToken(userId, openId);
    }

    public void invalidateToken(String token) {
        redisTemplate.delete(UserBuilder.buildTokenKey(token));
    }

    public String generateAndSaveToken(Long userId, String openId) {

        String token = UserBuilder.generateToken();
        boolean success = Boolean.TRUE.equals(redisTemplate.opsForValue().setIfAbsent(UserBuilder.buildTokenKey(token),
                UserBuilder.buildTokenValue(userId, openId), TOKEN_TTL_SECONDS, TimeUnit.SECONDS));
        if (!success) {
            LOG.error("token exists! token: {}", token);
            throw new RuntimeException("token exists!");
        }
        return token;
    }
}

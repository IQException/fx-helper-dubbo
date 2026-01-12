package com.iqexception.fxhelper.user.service.builder;

import com.google.common.hash.Hashing;
import com.iqexception.fxhelper.api.user.*;
import com.iqexception.fxhelper.common.util.ResponseUtil;
import com.iqexception.fxhelper.user.dal.generator.tables.pojos.FxUser;
import com.iqexception.fxhelper.user.util.FakeDataUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class UserBuilder {

    protected final Logger LOG = LoggerFactory.getLogger(getClass());

    private static final String TOKEN_KEY_PREFIX = "USER:LOGIN:TOKEN:";

    private static final String TOKEN_VALUE_SEPARATOR = "#";


    public static FxUser buildUserByFakeData(String openId) {
        FxUser user = new FxUser();
        user.setOpenId(openId);
        user.setAvatar(FakeDataUtil.getRandomGravatarUrl());
        user.setNickName(FakeDataUtil.getRandomNickname());
        return user;

    }

    public static FxUser buildUserByFakeData(FxUser user, String sessionKey) {

        user.setSessionKey(sessionKey);
        user.setAvatar(FakeDataUtil.getRandomGravatarUrl());
        user.setNickName(FakeDataUtil.getRandomNickname());
        user.setUpdatedAt(LocalDateTime.now());
        return user;

    }

    public static GetUserResponse buildGetUserResponse(FxUser user) {
        return GetUserResponse.newBuilder()
                .setStatus(ResponseUtil.statusOk())
                .setResult(buildGetUserResult(user))
                .build();
    }

    public static GetUserResult buildGetUserResult(FxUser user) {
        return GetUserResult.newBuilder()
                .setUserId(user.getUserId())
                .setNickname(user.getNickName())
                .setAvatar(user.getAvatar())
                .setOpenId(user.getOpenId())
                .build();
    }

    public static GetUserListResponse buildGetUserListResponse(List<FxUser> users) {

        return GetUserListResponse.newBuilder()
                .setStatus(ResponseUtil.statusOk())
                .setResult(buildGetUserListResult(users))
                .build();

    }

    public static GetUserListResult buildGetUserListResult(List<FxUser> users) {

        return GetUserListResult.newBuilder()
                .addAllUserInfos(users.stream().map(UserBuilder::buildUserInfo).collect(Collectors.toList()))
                .build();
    }

    private static UserInfo buildUserInfo(FxUser user) {
        return UserInfo.newBuilder()
                .setUserId(user.getUserId())
                .setNickname(user.getNickName())
                .setAvatar(user.getAvatar())
                .setOpenId(user.getOpenId())
                .build();
    }


    public static String encryptPaySecret(String paySecret) {

        if (StringUtils.isBlank(paySecret)) return null;

        return Hashing.sha256().hashString(paySecret, StandardCharsets.UTF_8).toString();

    }

    public static String generateToken() {
        return Hashing.sha256().hashString(UUID.randomUUID().toString(), StandardCharsets.UTF_8).toString();
    }


    public static String buildTokenKey(String token) {
        return TOKEN_KEY_PREFIX + token;
    }

    public static String buildTokenValue(Long userId, String openId) {
        return userId + TOKEN_VALUE_SEPARATOR + openId;
    }

    public static Pair<Long, String> parseTokenValue(String tokenValue) {
        String[] split = tokenValue.split(TOKEN_VALUE_SEPARATOR);
        return Pair.of(Long.valueOf(split[0]), split[1]);
    }
}

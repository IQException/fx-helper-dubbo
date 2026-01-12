package com.iqexception.fxhelper.user.service;


import com.iqexception.fxhelper.api.common.BaseResponse;
import com.iqexception.fxhelper.api.user.*;

public interface UserService {

    VerifyPaySecretResponse verifyPaySecret(VerifyPaySecretRequest request);

    GetUserListResponse getUserList(GetUserListRequest request);

    GetUserResponse getUser(GetUserRequest request);

    CheckLoginResponse checkLogin(CheckLoginRequest request);

    BaseResponse updatePaySecret(UpdatePaySecretRequest request);
}

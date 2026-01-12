package com.iqexception.fxhelper.user;

import com.iqexception.fxhelper.api.common.BaseResponse;
import com.iqexception.fxhelper.api.user.*;
import com.iqexception.fxhelper.user.service.UserService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@DubboService
public class FxHelperUserServiceImpl extends DubboUserServiceTriple.UserServiceImplBase {

    private final UserService userService;

    public FxHelperUserServiceImpl(UserService userService) {
        this.userService = userService;
    }

    public VerifyPaySecretResponse verifyPaySecret(VerifyPaySecretRequest request) {
        return userService.verifyPaySecret(request);
    }

    public GetUserListResponse getUserList(GetUserListRequest request) {
        return userService.getUserList(request);
    }

    public GetUserResponse getUser(GetUserRequest request) {
        return userService.getUser(request);
    }

    @PostMapping("/internal/check_login")
    public CheckLoginResponse checkLogin(@RequestBody CheckLoginRequest request) {
        return userService.checkLogin(request);
    }

    public BaseResponse updatePaySecret(UpdatePaySecretRequest request) {
        return userService.updatePaySecret(request);
    }
}

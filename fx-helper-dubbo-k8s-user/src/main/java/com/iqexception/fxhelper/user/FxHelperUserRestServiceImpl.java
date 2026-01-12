package com.iqexception.fxhelper.user;

import com.iqexception.fxhelper.api.rest.user.HelloResponse;
import com.iqexception.fxhelper.api.rest.user.LoginRequest;
import com.iqexception.fxhelper.api.rest.user.LoginResponse;
import com.iqexception.fxhelper.common.constant.CommonConstants;
import com.iqexception.fxhelper.common.util.ResponseUtil;
import com.iqexception.fxhelper.user.api.FxHelperUserRestService;
import com.iqexception.fxhelper.user.service.UserRestService;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.remoting.http12.HttpResponse;

@DubboService
public class FxHelperUserRestServiceImpl implements FxHelperUserRestService {

    private final UserRestService userRestService;

    public FxHelperUserRestServiceImpl(UserRestService userRestService) {
        this.userRestService = userRestService;
    }

    public LoginResponse login(LoginRequest request, HttpResponse httpResponse) {
        LoginResponse response = userRestService.login(request);
        if (ResponseUtil.isSuccess(response.getStatus())) {
            httpResponse.addHeader(CommonConstants.HEADER_USER_TOKEN, response.getResult().getToken());
        }
        return response;
    }

    public HelloResponse hello() {
        return HelloResponse.newBuilder()
                .setReply("Hello World !")
                .build();
    }
}

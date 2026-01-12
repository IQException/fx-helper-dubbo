package com.iqexception.fxhelper.user.service;

import com.iqexception.fxhelper.api.rest.user.LoginRequest;
import com.iqexception.fxhelper.api.rest.user.LoginResponse;

public interface UserRestService {
    LoginResponse login(LoginRequest request);
}

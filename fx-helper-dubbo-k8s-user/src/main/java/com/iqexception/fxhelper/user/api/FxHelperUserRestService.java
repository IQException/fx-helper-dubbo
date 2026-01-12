package com.iqexception.fxhelper.user.api;

import com.iqexception.fxhelper.api.rest.user.HelloResponse;
import com.iqexception.fxhelper.api.rest.user.LoginRequest;
import com.iqexception.fxhelper.api.rest.user.LoginResponse;
import org.apache.dubbo.remoting.http12.HttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface FxHelperUserRestService {
    @PostMapping("/login")
    LoginResponse login(@RequestBody LoginRequest request, HttpResponse response);

    @GetMapping("/hello")
    HelloResponse hello();


}

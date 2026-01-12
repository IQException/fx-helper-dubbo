/*
* Licensed to the Apache Software Foundation (ASF) under one or more
* contributor license agreements.  See the NOTICE file distributed with
* this work for additional information regarding copyright ownership.
* The ASF licenses this file to You under the Apache License, Version 2.0
* (the "License"); you may not use this file except in compliance with
* the License.  You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.iqexception.fxhelper.api.user;

import org.apache.dubbo.common.stream.StreamObserver;
import com.google.protobuf.Message;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.concurrent.CompletableFuture;

public interface UserService extends org.apache.dubbo.rpc.model.DubboStub {

    String JAVA_SERVICE_NAME = "com.iqexception.fxhelper.api.user.UserService";
    String SERVICE_NAME = "com.iqexception.fxhelper.api.user.UserService";
    com.iqexception.fxhelper.api.user.VerifyPaySecretResponse verifyPaySecret(com.iqexception.fxhelper.api.user.VerifyPaySecretRequest request);

    CompletableFuture<com.iqexception.fxhelper.api.user.VerifyPaySecretResponse> verifyPaySecretAsync(com.iqexception.fxhelper.api.user.VerifyPaySecretRequest request);



    com.iqexception.fxhelper.api.user.GetUserListResponse getUserList(com.iqexception.fxhelper.api.user.GetUserListRequest request);

    CompletableFuture<com.iqexception.fxhelper.api.user.GetUserListResponse> getUserListAsync(com.iqexception.fxhelper.api.user.GetUserListRequest request);



    com.iqexception.fxhelper.api.user.GetUserResponse getUser(com.iqexception.fxhelper.api.user.GetUserRequest request);

    CompletableFuture<com.iqexception.fxhelper.api.user.GetUserResponse> getUserAsync(com.iqexception.fxhelper.api.user.GetUserRequest request);



    com.iqexception.fxhelper.api.user.CheckLoginResponse checkLogin(com.iqexception.fxhelper.api.user.CheckLoginRequest request);

    CompletableFuture<com.iqexception.fxhelper.api.user.CheckLoginResponse> checkLoginAsync(com.iqexception.fxhelper.api.user.CheckLoginRequest request);



    com.iqexception.fxhelper.api.common.BaseResponse updatePaySecret(com.iqexception.fxhelper.api.user.UpdatePaySecretRequest request);

    CompletableFuture<com.iqexception.fxhelper.api.common.BaseResponse> updatePaySecretAsync(com.iqexception.fxhelper.api.user.UpdatePaySecretRequest request);








}

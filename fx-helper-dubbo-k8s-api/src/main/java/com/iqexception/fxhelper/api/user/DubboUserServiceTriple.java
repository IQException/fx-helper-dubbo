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
import org.apache.dubbo.common.URL;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.PathResolver;
import org.apache.dubbo.rpc.RpcException;
import org.apache.dubbo.rpc.ServerService;
import org.apache.dubbo.rpc.TriRpcStatus;
import org.apache.dubbo.rpc.model.MethodDescriptor;
import org.apache.dubbo.rpc.model.ServiceDescriptor;
import org.apache.dubbo.rpc.model.StubMethodDescriptor;
import org.apache.dubbo.rpc.model.StubServiceDescriptor;
import org.apache.dubbo.rpc.stub.BiStreamMethodHandler;
import org.apache.dubbo.rpc.stub.ServerStreamMethodHandler;
import org.apache.dubbo.rpc.stub.StubInvocationUtil;
import org.apache.dubbo.rpc.stub.StubInvoker;
import org.apache.dubbo.rpc.stub.StubMethodHandler;
import org.apache.dubbo.rpc.stub.StubSuppliers;
import org.apache.dubbo.rpc.stub.UnaryStubMethodHandler;

import com.google.protobuf.Message;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.concurrent.CompletableFuture;

public final class DubboUserServiceTriple {

    public static final String SERVICE_NAME = UserService.SERVICE_NAME;

    private static final StubServiceDescriptor serviceDescriptor = new StubServiceDescriptor(SERVICE_NAME,UserService.class);

    static {
        org.apache.dubbo.rpc.protocol.tri.service.SchemaDescriptorRegistry.addSchemaDescriptor(SERVICE_NAME,User.getDescriptor());
        StubSuppliers.addSupplier(SERVICE_NAME, DubboUserServiceTriple::newStub);
        StubSuppliers.addSupplier(UserService.JAVA_SERVICE_NAME,  DubboUserServiceTriple::newStub);
        StubSuppliers.addDescriptor(SERVICE_NAME, serviceDescriptor);
        StubSuppliers.addDescriptor(UserService.JAVA_SERVICE_NAME, serviceDescriptor);
    }

    @SuppressWarnings("all")
    public static UserService newStub(Invoker<?> invoker) {
        return new UserServiceStub((Invoker<UserService>)invoker);
    }

    private static final StubMethodDescriptor verifyPaySecretMethod = new StubMethodDescriptor("verifyPaySecret",
    com.iqexception.fxhelper.api.user.VerifyPaySecretRequest.class, com.iqexception.fxhelper.api.user.VerifyPaySecretResponse.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.user.VerifyPaySecretRequest::parseFrom,
    com.iqexception.fxhelper.api.user.VerifyPaySecretResponse::parseFrom);

    private static final StubMethodDescriptor verifyPaySecretAsyncMethod = new StubMethodDescriptor("verifyPaySecret",
    com.iqexception.fxhelper.api.user.VerifyPaySecretRequest.class, java.util.concurrent.CompletableFuture.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.user.VerifyPaySecretRequest::parseFrom,
    com.iqexception.fxhelper.api.user.VerifyPaySecretResponse::parseFrom);

    private static final StubMethodDescriptor verifyPaySecretProxyAsyncMethod = new StubMethodDescriptor("verifyPaySecretAsync",
    com.iqexception.fxhelper.api.user.VerifyPaySecretRequest.class, com.iqexception.fxhelper.api.user.VerifyPaySecretResponse.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.user.VerifyPaySecretRequest::parseFrom,
    com.iqexception.fxhelper.api.user.VerifyPaySecretResponse::parseFrom);
    private static final StubMethodDescriptor getUserListMethod = new StubMethodDescriptor("getUserList",
    com.iqexception.fxhelper.api.user.GetUserListRequest.class, com.iqexception.fxhelper.api.user.GetUserListResponse.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.user.GetUserListRequest::parseFrom,
    com.iqexception.fxhelper.api.user.GetUserListResponse::parseFrom);

    private static final StubMethodDescriptor getUserListAsyncMethod = new StubMethodDescriptor("getUserList",
    com.iqexception.fxhelper.api.user.GetUserListRequest.class, java.util.concurrent.CompletableFuture.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.user.GetUserListRequest::parseFrom,
    com.iqexception.fxhelper.api.user.GetUserListResponse::parseFrom);

    private static final StubMethodDescriptor getUserListProxyAsyncMethod = new StubMethodDescriptor("getUserListAsync",
    com.iqexception.fxhelper.api.user.GetUserListRequest.class, com.iqexception.fxhelper.api.user.GetUserListResponse.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.user.GetUserListRequest::parseFrom,
    com.iqexception.fxhelper.api.user.GetUserListResponse::parseFrom);
    private static final StubMethodDescriptor getUserMethod = new StubMethodDescriptor("getUser",
    com.iqexception.fxhelper.api.user.GetUserRequest.class, com.iqexception.fxhelper.api.user.GetUserResponse.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.user.GetUserRequest::parseFrom,
    com.iqexception.fxhelper.api.user.GetUserResponse::parseFrom);

    private static final StubMethodDescriptor getUserAsyncMethod = new StubMethodDescriptor("getUser",
    com.iqexception.fxhelper.api.user.GetUserRequest.class, java.util.concurrent.CompletableFuture.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.user.GetUserRequest::parseFrom,
    com.iqexception.fxhelper.api.user.GetUserResponse::parseFrom);

    private static final StubMethodDescriptor getUserProxyAsyncMethod = new StubMethodDescriptor("getUserAsync",
    com.iqexception.fxhelper.api.user.GetUserRequest.class, com.iqexception.fxhelper.api.user.GetUserResponse.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.user.GetUserRequest::parseFrom,
    com.iqexception.fxhelper.api.user.GetUserResponse::parseFrom);
    private static final StubMethodDescriptor checkLoginMethod = new StubMethodDescriptor("checkLogin",
    com.iqexception.fxhelper.api.user.CheckLoginRequest.class, com.iqexception.fxhelper.api.user.CheckLoginResponse.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.user.CheckLoginRequest::parseFrom,
    com.iqexception.fxhelper.api.user.CheckLoginResponse::parseFrom);

    private static final StubMethodDescriptor checkLoginAsyncMethod = new StubMethodDescriptor("checkLogin",
    com.iqexception.fxhelper.api.user.CheckLoginRequest.class, java.util.concurrent.CompletableFuture.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.user.CheckLoginRequest::parseFrom,
    com.iqexception.fxhelper.api.user.CheckLoginResponse::parseFrom);

    private static final StubMethodDescriptor checkLoginProxyAsyncMethod = new StubMethodDescriptor("checkLoginAsync",
    com.iqexception.fxhelper.api.user.CheckLoginRequest.class, com.iqexception.fxhelper.api.user.CheckLoginResponse.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.user.CheckLoginRequest::parseFrom,
    com.iqexception.fxhelper.api.user.CheckLoginResponse::parseFrom);
    private static final StubMethodDescriptor updatePaySecretMethod = new StubMethodDescriptor("updatePaySecret",
    com.iqexception.fxhelper.api.user.UpdatePaySecretRequest.class, com.iqexception.fxhelper.api.common.BaseResponse.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.user.UpdatePaySecretRequest::parseFrom,
    com.iqexception.fxhelper.api.common.BaseResponse::parseFrom);

    private static final StubMethodDescriptor updatePaySecretAsyncMethod = new StubMethodDescriptor("updatePaySecret",
    com.iqexception.fxhelper.api.user.UpdatePaySecretRequest.class, java.util.concurrent.CompletableFuture.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.user.UpdatePaySecretRequest::parseFrom,
    com.iqexception.fxhelper.api.common.BaseResponse::parseFrom);

    private static final StubMethodDescriptor updatePaySecretProxyAsyncMethod = new StubMethodDescriptor("updatePaySecretAsync",
    com.iqexception.fxhelper.api.user.UpdatePaySecretRequest.class, com.iqexception.fxhelper.api.common.BaseResponse.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.user.UpdatePaySecretRequest::parseFrom,
    com.iqexception.fxhelper.api.common.BaseResponse::parseFrom);




    static{
        serviceDescriptor.addMethod(verifyPaySecretMethod);
        serviceDescriptor.addMethod(verifyPaySecretProxyAsyncMethod);
        serviceDescriptor.addMethod(getUserListMethod);
        serviceDescriptor.addMethod(getUserListProxyAsyncMethod);
        serviceDescriptor.addMethod(getUserMethod);
        serviceDescriptor.addMethod(getUserProxyAsyncMethod);
        serviceDescriptor.addMethod(checkLoginMethod);
        serviceDescriptor.addMethod(checkLoginProxyAsyncMethod);
        serviceDescriptor.addMethod(updatePaySecretMethod);
        serviceDescriptor.addMethod(updatePaySecretProxyAsyncMethod);
    }

    public static class UserServiceStub implements UserService{
        private final Invoker<UserService> invoker;

        public UserServiceStub(Invoker<UserService> invoker) {
            this.invoker = invoker;
        }

        @Override
        public com.iqexception.fxhelper.api.user.VerifyPaySecretResponse verifyPaySecret(com.iqexception.fxhelper.api.user.VerifyPaySecretRequest request){
            return StubInvocationUtil.unaryCall(invoker, verifyPaySecretMethod, request);
        }

        public CompletableFuture<com.iqexception.fxhelper.api.user.VerifyPaySecretResponse> verifyPaySecretAsync(com.iqexception.fxhelper.api.user.VerifyPaySecretRequest request){
            return StubInvocationUtil.unaryCall(invoker, verifyPaySecretAsyncMethod, request);
        }

        public void verifyPaySecret(com.iqexception.fxhelper.api.user.VerifyPaySecretRequest request, StreamObserver<com.iqexception.fxhelper.api.user.VerifyPaySecretResponse> responseObserver){
            StubInvocationUtil.unaryCall(invoker, verifyPaySecretMethod , request, responseObserver);
        }
        @Override
        public com.iqexception.fxhelper.api.user.GetUserListResponse getUserList(com.iqexception.fxhelper.api.user.GetUserListRequest request){
            return StubInvocationUtil.unaryCall(invoker, getUserListMethod, request);
        }

        public CompletableFuture<com.iqexception.fxhelper.api.user.GetUserListResponse> getUserListAsync(com.iqexception.fxhelper.api.user.GetUserListRequest request){
            return StubInvocationUtil.unaryCall(invoker, getUserListAsyncMethod, request);
        }

        public void getUserList(com.iqexception.fxhelper.api.user.GetUserListRequest request, StreamObserver<com.iqexception.fxhelper.api.user.GetUserListResponse> responseObserver){
            StubInvocationUtil.unaryCall(invoker, getUserListMethod , request, responseObserver);
        }
        @Override
        public com.iqexception.fxhelper.api.user.GetUserResponse getUser(com.iqexception.fxhelper.api.user.GetUserRequest request){
            return StubInvocationUtil.unaryCall(invoker, getUserMethod, request);
        }

        public CompletableFuture<com.iqexception.fxhelper.api.user.GetUserResponse> getUserAsync(com.iqexception.fxhelper.api.user.GetUserRequest request){
            return StubInvocationUtil.unaryCall(invoker, getUserAsyncMethod, request);
        }

        public void getUser(com.iqexception.fxhelper.api.user.GetUserRequest request, StreamObserver<com.iqexception.fxhelper.api.user.GetUserResponse> responseObserver){
            StubInvocationUtil.unaryCall(invoker, getUserMethod , request, responseObserver);
        }
        @Override
        public com.iqexception.fxhelper.api.user.CheckLoginResponse checkLogin(com.iqexception.fxhelper.api.user.CheckLoginRequest request){
            return StubInvocationUtil.unaryCall(invoker, checkLoginMethod, request);
        }

        public CompletableFuture<com.iqexception.fxhelper.api.user.CheckLoginResponse> checkLoginAsync(com.iqexception.fxhelper.api.user.CheckLoginRequest request){
            return StubInvocationUtil.unaryCall(invoker, checkLoginAsyncMethod, request);
        }

        public void checkLogin(com.iqexception.fxhelper.api.user.CheckLoginRequest request, StreamObserver<com.iqexception.fxhelper.api.user.CheckLoginResponse> responseObserver){
            StubInvocationUtil.unaryCall(invoker, checkLoginMethod , request, responseObserver);
        }
        @Override
        public com.iqexception.fxhelper.api.common.BaseResponse updatePaySecret(com.iqexception.fxhelper.api.user.UpdatePaySecretRequest request){
            return StubInvocationUtil.unaryCall(invoker, updatePaySecretMethod, request);
        }

        public CompletableFuture<com.iqexception.fxhelper.api.common.BaseResponse> updatePaySecretAsync(com.iqexception.fxhelper.api.user.UpdatePaySecretRequest request){
            return StubInvocationUtil.unaryCall(invoker, updatePaySecretAsyncMethod, request);
        }

        public void updatePaySecret(com.iqexception.fxhelper.api.user.UpdatePaySecretRequest request, StreamObserver<com.iqexception.fxhelper.api.common.BaseResponse> responseObserver){
            StubInvocationUtil.unaryCall(invoker, updatePaySecretMethod , request, responseObserver);
        }



    }

    public static abstract class UserServiceImplBase implements UserService, ServerService<UserService> {

        private <T, R> BiConsumer<T, StreamObserver<R>> syncToAsync(java.util.function.Function<T, R> syncFun) {
            return new BiConsumer<T, StreamObserver<R>>() {
                @Override
                public void accept(T t, StreamObserver<R> observer) {
                    try {
                        R ret = syncFun.apply(t);
                        observer.onNext(ret);
                        observer.onCompleted();
                    } catch (Throwable e) {
                        observer.onError(e);
                    }
                }
            };
        }

        @Override
        public CompletableFuture<com.iqexception.fxhelper.api.user.VerifyPaySecretResponse> verifyPaySecretAsync(com.iqexception.fxhelper.api.user.VerifyPaySecretRequest request){
                return CompletableFuture.completedFuture(verifyPaySecret(request));
        }
        @Override
        public CompletableFuture<com.iqexception.fxhelper.api.user.GetUserListResponse> getUserListAsync(com.iqexception.fxhelper.api.user.GetUserListRequest request){
                return CompletableFuture.completedFuture(getUserList(request));
        }
        @Override
        public CompletableFuture<com.iqexception.fxhelper.api.user.GetUserResponse> getUserAsync(com.iqexception.fxhelper.api.user.GetUserRequest request){
                return CompletableFuture.completedFuture(getUser(request));
        }
        @Override
        public CompletableFuture<com.iqexception.fxhelper.api.user.CheckLoginResponse> checkLoginAsync(com.iqexception.fxhelper.api.user.CheckLoginRequest request){
                return CompletableFuture.completedFuture(checkLogin(request));
        }
        @Override
        public CompletableFuture<com.iqexception.fxhelper.api.common.BaseResponse> updatePaySecretAsync(com.iqexception.fxhelper.api.user.UpdatePaySecretRequest request){
                return CompletableFuture.completedFuture(updatePaySecret(request));
        }

        /**
        * This server stream type unary method is <b>only</b> used for generated stub to support async unary method.
        * It will not be called if you are NOT using Dubbo3 generated triple stub and <b>DO NOT</b> implement this method.
        */
        public void verifyPaySecret(com.iqexception.fxhelper.api.user.VerifyPaySecretRequest request, StreamObserver<com.iqexception.fxhelper.api.user.VerifyPaySecretResponse> responseObserver){
            verifyPaySecretAsync(request).whenComplete((r, t) -> {
                if (t != null) {
                    responseObserver.onError(t);
                } else {
                    responseObserver.onNext(r);
                    responseObserver.onCompleted();
                }
            });
        }
        public void getUserList(com.iqexception.fxhelper.api.user.GetUserListRequest request, StreamObserver<com.iqexception.fxhelper.api.user.GetUserListResponse> responseObserver){
            getUserListAsync(request).whenComplete((r, t) -> {
                if (t != null) {
                    responseObserver.onError(t);
                } else {
                    responseObserver.onNext(r);
                    responseObserver.onCompleted();
                }
            });
        }
        public void getUser(com.iqexception.fxhelper.api.user.GetUserRequest request, StreamObserver<com.iqexception.fxhelper.api.user.GetUserResponse> responseObserver){
            getUserAsync(request).whenComplete((r, t) -> {
                if (t != null) {
                    responseObserver.onError(t);
                } else {
                    responseObserver.onNext(r);
                    responseObserver.onCompleted();
                }
            });
        }
        public void checkLogin(com.iqexception.fxhelper.api.user.CheckLoginRequest request, StreamObserver<com.iqexception.fxhelper.api.user.CheckLoginResponse> responseObserver){
            checkLoginAsync(request).whenComplete((r, t) -> {
                if (t != null) {
                    responseObserver.onError(t);
                } else {
                    responseObserver.onNext(r);
                    responseObserver.onCompleted();
                }
            });
        }
        public void updatePaySecret(com.iqexception.fxhelper.api.user.UpdatePaySecretRequest request, StreamObserver<com.iqexception.fxhelper.api.common.BaseResponse> responseObserver){
            updatePaySecretAsync(request).whenComplete((r, t) -> {
                if (t != null) {
                    responseObserver.onError(t);
                } else {
                    responseObserver.onNext(r);
                    responseObserver.onCompleted();
                }
            });
        }

        @Override
        public final Invoker<UserService> getInvoker(URL url) {
            PathResolver pathResolver = url.getOrDefaultFrameworkModel()
            .getExtensionLoader(PathResolver.class)
            .getDefaultExtension();
            Map<String,StubMethodHandler<?, ?>> handlers = new HashMap<>();

            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/verifyPaySecret");
            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/verifyPaySecretAsync");
            // for compatibility
            pathResolver.addNativeStub( "/" + JAVA_SERVICE_NAME + "/verifyPaySecret");
            pathResolver.addNativeStub( "/" + JAVA_SERVICE_NAME + "/verifyPaySecretAsync");

            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/getUserList");
            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/getUserListAsync");
            // for compatibility
            pathResolver.addNativeStub( "/" + JAVA_SERVICE_NAME + "/getUserList");
            pathResolver.addNativeStub( "/" + JAVA_SERVICE_NAME + "/getUserListAsync");

            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/getUser");
            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/getUserAsync");
            // for compatibility
            pathResolver.addNativeStub( "/" + JAVA_SERVICE_NAME + "/getUser");
            pathResolver.addNativeStub( "/" + JAVA_SERVICE_NAME + "/getUserAsync");

            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/checkLogin");
            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/checkLoginAsync");
            // for compatibility
            pathResolver.addNativeStub( "/" + JAVA_SERVICE_NAME + "/checkLogin");
            pathResolver.addNativeStub( "/" + JAVA_SERVICE_NAME + "/checkLoginAsync");

            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/updatePaySecret");
            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/updatePaySecretAsync");
            // for compatibility
            pathResolver.addNativeStub( "/" + JAVA_SERVICE_NAME + "/updatePaySecret");
            pathResolver.addNativeStub( "/" + JAVA_SERVICE_NAME + "/updatePaySecretAsync");


            BiConsumer<com.iqexception.fxhelper.api.user.VerifyPaySecretRequest, StreamObserver<com.iqexception.fxhelper.api.user.VerifyPaySecretResponse>> verifyPaySecretFunc = this::verifyPaySecret;
            handlers.put(verifyPaySecretMethod.getMethodName(), new UnaryStubMethodHandler<>(verifyPaySecretFunc));
            BiConsumer<com.iqexception.fxhelper.api.user.VerifyPaySecretRequest, StreamObserver<com.iqexception.fxhelper.api.user.VerifyPaySecretResponse>> verifyPaySecretAsyncFunc = syncToAsync(this::verifyPaySecret);
            handlers.put(verifyPaySecretProxyAsyncMethod.getMethodName(), new UnaryStubMethodHandler<>(verifyPaySecretAsyncFunc));
            BiConsumer<com.iqexception.fxhelper.api.user.GetUserListRequest, StreamObserver<com.iqexception.fxhelper.api.user.GetUserListResponse>> getUserListFunc = this::getUserList;
            handlers.put(getUserListMethod.getMethodName(), new UnaryStubMethodHandler<>(getUserListFunc));
            BiConsumer<com.iqexception.fxhelper.api.user.GetUserListRequest, StreamObserver<com.iqexception.fxhelper.api.user.GetUserListResponse>> getUserListAsyncFunc = syncToAsync(this::getUserList);
            handlers.put(getUserListProxyAsyncMethod.getMethodName(), new UnaryStubMethodHandler<>(getUserListAsyncFunc));
            BiConsumer<com.iqexception.fxhelper.api.user.GetUserRequest, StreamObserver<com.iqexception.fxhelper.api.user.GetUserResponse>> getUserFunc = this::getUser;
            handlers.put(getUserMethod.getMethodName(), new UnaryStubMethodHandler<>(getUserFunc));
            BiConsumer<com.iqexception.fxhelper.api.user.GetUserRequest, StreamObserver<com.iqexception.fxhelper.api.user.GetUserResponse>> getUserAsyncFunc = syncToAsync(this::getUser);
            handlers.put(getUserProxyAsyncMethod.getMethodName(), new UnaryStubMethodHandler<>(getUserAsyncFunc));
            BiConsumer<com.iqexception.fxhelper.api.user.CheckLoginRequest, StreamObserver<com.iqexception.fxhelper.api.user.CheckLoginResponse>> checkLoginFunc = this::checkLogin;
            handlers.put(checkLoginMethod.getMethodName(), new UnaryStubMethodHandler<>(checkLoginFunc));
            BiConsumer<com.iqexception.fxhelper.api.user.CheckLoginRequest, StreamObserver<com.iqexception.fxhelper.api.user.CheckLoginResponse>> checkLoginAsyncFunc = syncToAsync(this::checkLogin);
            handlers.put(checkLoginProxyAsyncMethod.getMethodName(), new UnaryStubMethodHandler<>(checkLoginAsyncFunc));
            BiConsumer<com.iqexception.fxhelper.api.user.UpdatePaySecretRequest, StreamObserver<com.iqexception.fxhelper.api.common.BaseResponse>> updatePaySecretFunc = this::updatePaySecret;
            handlers.put(updatePaySecretMethod.getMethodName(), new UnaryStubMethodHandler<>(updatePaySecretFunc));
            BiConsumer<com.iqexception.fxhelper.api.user.UpdatePaySecretRequest, StreamObserver<com.iqexception.fxhelper.api.common.BaseResponse>> updatePaySecretAsyncFunc = syncToAsync(this::updatePaySecret);
            handlers.put(updatePaySecretProxyAsyncMethod.getMethodName(), new UnaryStubMethodHandler<>(updatePaySecretAsyncFunc));




            return new StubInvoker<>(this, url, UserService.class, handlers);
        }


        @Override
        public com.iqexception.fxhelper.api.user.VerifyPaySecretResponse verifyPaySecret(com.iqexception.fxhelper.api.user.VerifyPaySecretRequest request){
            throw unimplementedMethodException(verifyPaySecretMethod);
        }

        @Override
        public com.iqexception.fxhelper.api.user.GetUserListResponse getUserList(com.iqexception.fxhelper.api.user.GetUserListRequest request){
            throw unimplementedMethodException(getUserListMethod);
        }

        @Override
        public com.iqexception.fxhelper.api.user.GetUserResponse getUser(com.iqexception.fxhelper.api.user.GetUserRequest request){
            throw unimplementedMethodException(getUserMethod);
        }

        @Override
        public com.iqexception.fxhelper.api.user.CheckLoginResponse checkLogin(com.iqexception.fxhelper.api.user.CheckLoginRequest request){
            throw unimplementedMethodException(checkLoginMethod);
        }

        @Override
        public com.iqexception.fxhelper.api.common.BaseResponse updatePaySecret(com.iqexception.fxhelper.api.user.UpdatePaySecretRequest request){
            throw unimplementedMethodException(updatePaySecretMethod);
        }





        @Override
        public final ServiceDescriptor getServiceDescriptor() {
            return serviceDescriptor;
        }
        private RpcException unimplementedMethodException(StubMethodDescriptor methodDescriptor) {
            return TriRpcStatus.UNIMPLEMENTED.withDescription(String.format("Method %s is unimplemented",
                "/" + serviceDescriptor.getInterfaceName() + "/" + methodDescriptor.getMethodName())).asException();
        }
    }

}

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

package com.iqexception.fxhelper.api.account;

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

public final class DubboAccountServiceTriple {

    public static final String SERVICE_NAME = AccountService.SERVICE_NAME;

    private static final StubServiceDescriptor serviceDescriptor = new StubServiceDescriptor(SERVICE_NAME,AccountService.class);

    static {
        org.apache.dubbo.rpc.protocol.tri.service.SchemaDescriptorRegistry.addSchemaDescriptor(SERVICE_NAME,Account.getDescriptor());
        StubSuppliers.addSupplier(SERVICE_NAME, DubboAccountServiceTriple::newStub);
        StubSuppliers.addSupplier(AccountService.JAVA_SERVICE_NAME,  DubboAccountServiceTriple::newStub);
        StubSuppliers.addDescriptor(SERVICE_NAME, serviceDescriptor);
        StubSuppliers.addDescriptor(AccountService.JAVA_SERVICE_NAME, serviceDescriptor);
    }

    @SuppressWarnings("all")
    public static AccountService newStub(Invoker<?> invoker) {
        return new AccountServiceStub((Invoker<AccountService>)invoker);
    }

    private static final StubMethodDescriptor createMethod = new StubMethodDescriptor("create",
    com.iqexception.fxhelper.api.account.CreateRequest.class, com.iqexception.fxhelper.api.account.CreateResponse.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.account.CreateRequest::parseFrom,
    com.iqexception.fxhelper.api.account.CreateResponse::parseFrom);

    private static final StubMethodDescriptor createAsyncMethod = new StubMethodDescriptor("create",
    com.iqexception.fxhelper.api.account.CreateRequest.class, java.util.concurrent.CompletableFuture.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.account.CreateRequest::parseFrom,
    com.iqexception.fxhelper.api.account.CreateResponse::parseFrom);

    private static final StubMethodDescriptor createProxyAsyncMethod = new StubMethodDescriptor("createAsync",
    com.iqexception.fxhelper.api.account.CreateRequest.class, com.iqexception.fxhelper.api.account.CreateResponse.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.account.CreateRequest::parseFrom,
    com.iqexception.fxhelper.api.account.CreateResponse::parseFrom);
    private static final StubMethodDescriptor getAccountMethod = new StubMethodDescriptor("getAccount",
    com.iqexception.fxhelper.api.account.GetAccountRequest.class, com.iqexception.fxhelper.api.account.GetAccountResponse.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.account.GetAccountRequest::parseFrom,
    com.iqexception.fxhelper.api.account.GetAccountResponse::parseFrom);

    private static final StubMethodDescriptor getAccountAsyncMethod = new StubMethodDescriptor("getAccount",
    com.iqexception.fxhelper.api.account.GetAccountRequest.class, java.util.concurrent.CompletableFuture.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.account.GetAccountRequest::parseFrom,
    com.iqexception.fxhelper.api.account.GetAccountResponse::parseFrom);

    private static final StubMethodDescriptor getAccountProxyAsyncMethod = new StubMethodDescriptor("getAccountAsync",
    com.iqexception.fxhelper.api.account.GetAccountRequest.class, com.iqexception.fxhelper.api.account.GetAccountResponse.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.account.GetAccountRequest::parseFrom,
    com.iqexception.fxhelper.api.account.GetAccountResponse::parseFrom);
    private static final StubMethodDescriptor transferMethod = new StubMethodDescriptor("transfer",
    com.iqexception.fxhelper.api.account.TransferRequest.class, com.iqexception.fxhelper.api.common.BaseResponse.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.account.TransferRequest::parseFrom,
    com.iqexception.fxhelper.api.common.BaseResponse::parseFrom);

    private static final StubMethodDescriptor transferAsyncMethod = new StubMethodDescriptor("transfer",
    com.iqexception.fxhelper.api.account.TransferRequest.class, java.util.concurrent.CompletableFuture.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.account.TransferRequest::parseFrom,
    com.iqexception.fxhelper.api.common.BaseResponse::parseFrom);

    private static final StubMethodDescriptor transferProxyAsyncMethod = new StubMethodDescriptor("transferAsync",
    com.iqexception.fxhelper.api.account.TransferRequest.class, com.iqexception.fxhelper.api.common.BaseResponse.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.account.TransferRequest::parseFrom,
    com.iqexception.fxhelper.api.common.BaseResponse::parseFrom);
    private static final StubMethodDescriptor incrBalanceMethod = new StubMethodDescriptor("incrBalance",
    com.iqexception.fxhelper.api.account.IncrBalanceRequest.class, com.iqexception.fxhelper.api.common.BaseResponse.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.account.IncrBalanceRequest::parseFrom,
    com.iqexception.fxhelper.api.common.BaseResponse::parseFrom);

    private static final StubMethodDescriptor incrBalanceAsyncMethod = new StubMethodDescriptor("incrBalance",
    com.iqexception.fxhelper.api.account.IncrBalanceRequest.class, java.util.concurrent.CompletableFuture.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.account.IncrBalanceRequest::parseFrom,
    com.iqexception.fxhelper.api.common.BaseResponse::parseFrom);

    private static final StubMethodDescriptor incrBalanceProxyAsyncMethod = new StubMethodDescriptor("incrBalanceAsync",
    com.iqexception.fxhelper.api.account.IncrBalanceRequest.class, com.iqexception.fxhelper.api.common.BaseResponse.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.account.IncrBalanceRequest::parseFrom,
    com.iqexception.fxhelper.api.common.BaseResponse::parseFrom);




    static{
        serviceDescriptor.addMethod(createMethod);
        serviceDescriptor.addMethod(createProxyAsyncMethod);
        serviceDescriptor.addMethod(getAccountMethod);
        serviceDescriptor.addMethod(getAccountProxyAsyncMethod);
        serviceDescriptor.addMethod(transferMethod);
        serviceDescriptor.addMethod(transferProxyAsyncMethod);
        serviceDescriptor.addMethod(incrBalanceMethod);
        serviceDescriptor.addMethod(incrBalanceProxyAsyncMethod);
    }

    public static class AccountServiceStub implements AccountService{
        private final Invoker<AccountService> invoker;

        public AccountServiceStub(Invoker<AccountService> invoker) {
            this.invoker = invoker;
        }

        @Override
        public com.iqexception.fxhelper.api.account.CreateResponse create(com.iqexception.fxhelper.api.account.CreateRequest request){
            return StubInvocationUtil.unaryCall(invoker, createMethod, request);
        }

        public CompletableFuture<com.iqexception.fxhelper.api.account.CreateResponse> createAsync(com.iqexception.fxhelper.api.account.CreateRequest request){
            return StubInvocationUtil.unaryCall(invoker, createAsyncMethod, request);
        }

        public void create(com.iqexception.fxhelper.api.account.CreateRequest request, StreamObserver<com.iqexception.fxhelper.api.account.CreateResponse> responseObserver){
            StubInvocationUtil.unaryCall(invoker, createMethod , request, responseObserver);
        }
        @Override
        public com.iqexception.fxhelper.api.account.GetAccountResponse getAccount(com.iqexception.fxhelper.api.account.GetAccountRequest request){
            return StubInvocationUtil.unaryCall(invoker, getAccountMethod, request);
        }

        public CompletableFuture<com.iqexception.fxhelper.api.account.GetAccountResponse> getAccountAsync(com.iqexception.fxhelper.api.account.GetAccountRequest request){
            return StubInvocationUtil.unaryCall(invoker, getAccountAsyncMethod, request);
        }

        public void getAccount(com.iqexception.fxhelper.api.account.GetAccountRequest request, StreamObserver<com.iqexception.fxhelper.api.account.GetAccountResponse> responseObserver){
            StubInvocationUtil.unaryCall(invoker, getAccountMethod , request, responseObserver);
        }
        @Override
        public com.iqexception.fxhelper.api.common.BaseResponse transfer(com.iqexception.fxhelper.api.account.TransferRequest request){
            return StubInvocationUtil.unaryCall(invoker, transferMethod, request);
        }

        public CompletableFuture<com.iqexception.fxhelper.api.common.BaseResponse> transferAsync(com.iqexception.fxhelper.api.account.TransferRequest request){
            return StubInvocationUtil.unaryCall(invoker, transferAsyncMethod, request);
        }

        public void transfer(com.iqexception.fxhelper.api.account.TransferRequest request, StreamObserver<com.iqexception.fxhelper.api.common.BaseResponse> responseObserver){
            StubInvocationUtil.unaryCall(invoker, transferMethod , request, responseObserver);
        }
        @Override
        public com.iqexception.fxhelper.api.common.BaseResponse incrBalance(com.iqexception.fxhelper.api.account.IncrBalanceRequest request){
            return StubInvocationUtil.unaryCall(invoker, incrBalanceMethod, request);
        }

        public CompletableFuture<com.iqexception.fxhelper.api.common.BaseResponse> incrBalanceAsync(com.iqexception.fxhelper.api.account.IncrBalanceRequest request){
            return StubInvocationUtil.unaryCall(invoker, incrBalanceAsyncMethod, request);
        }

        public void incrBalance(com.iqexception.fxhelper.api.account.IncrBalanceRequest request, StreamObserver<com.iqexception.fxhelper.api.common.BaseResponse> responseObserver){
            StubInvocationUtil.unaryCall(invoker, incrBalanceMethod , request, responseObserver);
        }



    }

    public static abstract class AccountServiceImplBase implements AccountService, ServerService<AccountService> {

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
        public CompletableFuture<com.iqexception.fxhelper.api.account.CreateResponse> createAsync(com.iqexception.fxhelper.api.account.CreateRequest request){
                return CompletableFuture.completedFuture(create(request));
        }
        @Override
        public CompletableFuture<com.iqexception.fxhelper.api.account.GetAccountResponse> getAccountAsync(com.iqexception.fxhelper.api.account.GetAccountRequest request){
                return CompletableFuture.completedFuture(getAccount(request));
        }
        @Override
        public CompletableFuture<com.iqexception.fxhelper.api.common.BaseResponse> transferAsync(com.iqexception.fxhelper.api.account.TransferRequest request){
                return CompletableFuture.completedFuture(transfer(request));
        }
        @Override
        public CompletableFuture<com.iqexception.fxhelper.api.common.BaseResponse> incrBalanceAsync(com.iqexception.fxhelper.api.account.IncrBalanceRequest request){
                return CompletableFuture.completedFuture(incrBalance(request));
        }

        /**
        * This server stream type unary method is <b>only</b> used for generated stub to support async unary method.
        * It will not be called if you are NOT using Dubbo3 generated triple stub and <b>DO NOT</b> implement this method.
        */
        public void create(com.iqexception.fxhelper.api.account.CreateRequest request, StreamObserver<com.iqexception.fxhelper.api.account.CreateResponse> responseObserver){
            createAsync(request).whenComplete((r, t) -> {
                if (t != null) {
                    responseObserver.onError(t);
                } else {
                    responseObserver.onNext(r);
                    responseObserver.onCompleted();
                }
            });
        }
        public void getAccount(com.iqexception.fxhelper.api.account.GetAccountRequest request, StreamObserver<com.iqexception.fxhelper.api.account.GetAccountResponse> responseObserver){
            getAccountAsync(request).whenComplete((r, t) -> {
                if (t != null) {
                    responseObserver.onError(t);
                } else {
                    responseObserver.onNext(r);
                    responseObserver.onCompleted();
                }
            });
        }
        public void transfer(com.iqexception.fxhelper.api.account.TransferRequest request, StreamObserver<com.iqexception.fxhelper.api.common.BaseResponse> responseObserver){
            transferAsync(request).whenComplete((r, t) -> {
                if (t != null) {
                    responseObserver.onError(t);
                } else {
                    responseObserver.onNext(r);
                    responseObserver.onCompleted();
                }
            });
        }
        public void incrBalance(com.iqexception.fxhelper.api.account.IncrBalanceRequest request, StreamObserver<com.iqexception.fxhelper.api.common.BaseResponse> responseObserver){
            incrBalanceAsync(request).whenComplete((r, t) -> {
                if (t != null) {
                    responseObserver.onError(t);
                } else {
                    responseObserver.onNext(r);
                    responseObserver.onCompleted();
                }
            });
        }

        @Override
        public final Invoker<AccountService> getInvoker(URL url) {
            PathResolver pathResolver = url.getOrDefaultFrameworkModel()
            .getExtensionLoader(PathResolver.class)
            .getDefaultExtension();
            Map<String,StubMethodHandler<?, ?>> handlers = new HashMap<>();

            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/create");
            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/createAsync");
            // for compatibility
            pathResolver.addNativeStub( "/" + JAVA_SERVICE_NAME + "/create");
            pathResolver.addNativeStub( "/" + JAVA_SERVICE_NAME + "/createAsync");

            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/getAccount");
            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/getAccountAsync");
            // for compatibility
            pathResolver.addNativeStub( "/" + JAVA_SERVICE_NAME + "/getAccount");
            pathResolver.addNativeStub( "/" + JAVA_SERVICE_NAME + "/getAccountAsync");

            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/transfer");
            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/transferAsync");
            // for compatibility
            pathResolver.addNativeStub( "/" + JAVA_SERVICE_NAME + "/transfer");
            pathResolver.addNativeStub( "/" + JAVA_SERVICE_NAME + "/transferAsync");

            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/incrBalance");
            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/incrBalanceAsync");
            // for compatibility
            pathResolver.addNativeStub( "/" + JAVA_SERVICE_NAME + "/incrBalance");
            pathResolver.addNativeStub( "/" + JAVA_SERVICE_NAME + "/incrBalanceAsync");


            BiConsumer<com.iqexception.fxhelper.api.account.CreateRequest, StreamObserver<com.iqexception.fxhelper.api.account.CreateResponse>> createFunc = this::create;
            handlers.put(createMethod.getMethodName(), new UnaryStubMethodHandler<>(createFunc));
            BiConsumer<com.iqexception.fxhelper.api.account.CreateRequest, StreamObserver<com.iqexception.fxhelper.api.account.CreateResponse>> createAsyncFunc = syncToAsync(this::create);
            handlers.put(createProxyAsyncMethod.getMethodName(), new UnaryStubMethodHandler<>(createAsyncFunc));
            BiConsumer<com.iqexception.fxhelper.api.account.GetAccountRequest, StreamObserver<com.iqexception.fxhelper.api.account.GetAccountResponse>> getAccountFunc = this::getAccount;
            handlers.put(getAccountMethod.getMethodName(), new UnaryStubMethodHandler<>(getAccountFunc));
            BiConsumer<com.iqexception.fxhelper.api.account.GetAccountRequest, StreamObserver<com.iqexception.fxhelper.api.account.GetAccountResponse>> getAccountAsyncFunc = syncToAsync(this::getAccount);
            handlers.put(getAccountProxyAsyncMethod.getMethodName(), new UnaryStubMethodHandler<>(getAccountAsyncFunc));
            BiConsumer<com.iqexception.fxhelper.api.account.TransferRequest, StreamObserver<com.iqexception.fxhelper.api.common.BaseResponse>> transferFunc = this::transfer;
            handlers.put(transferMethod.getMethodName(), new UnaryStubMethodHandler<>(transferFunc));
            BiConsumer<com.iqexception.fxhelper.api.account.TransferRequest, StreamObserver<com.iqexception.fxhelper.api.common.BaseResponse>> transferAsyncFunc = syncToAsync(this::transfer);
            handlers.put(transferProxyAsyncMethod.getMethodName(), new UnaryStubMethodHandler<>(transferAsyncFunc));
            BiConsumer<com.iqexception.fxhelper.api.account.IncrBalanceRequest, StreamObserver<com.iqexception.fxhelper.api.common.BaseResponse>> incrBalanceFunc = this::incrBalance;
            handlers.put(incrBalanceMethod.getMethodName(), new UnaryStubMethodHandler<>(incrBalanceFunc));
            BiConsumer<com.iqexception.fxhelper.api.account.IncrBalanceRequest, StreamObserver<com.iqexception.fxhelper.api.common.BaseResponse>> incrBalanceAsyncFunc = syncToAsync(this::incrBalance);
            handlers.put(incrBalanceProxyAsyncMethod.getMethodName(), new UnaryStubMethodHandler<>(incrBalanceAsyncFunc));




            return new StubInvoker<>(this, url, AccountService.class, handlers);
        }


        @Override
        public com.iqexception.fxhelper.api.account.CreateResponse create(com.iqexception.fxhelper.api.account.CreateRequest request){
            throw unimplementedMethodException(createMethod);
        }

        @Override
        public com.iqexception.fxhelper.api.account.GetAccountResponse getAccount(com.iqexception.fxhelper.api.account.GetAccountRequest request){
            throw unimplementedMethodException(getAccountMethod);
        }

        @Override
        public com.iqexception.fxhelper.api.common.BaseResponse transfer(com.iqexception.fxhelper.api.account.TransferRequest request){
            throw unimplementedMethodException(transferMethod);
        }

        @Override
        public com.iqexception.fxhelper.api.common.BaseResponse incrBalance(com.iqexception.fxhelper.api.account.IncrBalanceRequest request){
            throw unimplementedMethodException(incrBalanceMethod);
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

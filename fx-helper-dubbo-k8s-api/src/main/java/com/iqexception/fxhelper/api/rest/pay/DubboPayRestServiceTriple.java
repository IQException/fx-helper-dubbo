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

package com.iqexception.fxhelper.api.rest.pay;

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

public final class DubboPayRestServiceTriple {

    public static final String SERVICE_NAME = PayRestService.SERVICE_NAME;

    private static final StubServiceDescriptor serviceDescriptor = new StubServiceDescriptor(SERVICE_NAME,PayRestService.class);

    static {
        org.apache.dubbo.rpc.protocol.tri.service.SchemaDescriptorRegistry.addSchemaDescriptor(SERVICE_NAME,PayRest.getDescriptor());
        StubSuppliers.addSupplier(SERVICE_NAME, DubboPayRestServiceTriple::newStub);
        StubSuppliers.addSupplier(PayRestService.JAVA_SERVICE_NAME,  DubboPayRestServiceTriple::newStub);
        StubSuppliers.addDescriptor(SERVICE_NAME, serviceDescriptor);
        StubSuppliers.addDescriptor(PayRestService.JAVA_SERVICE_NAME, serviceDescriptor);
    }

    @SuppressWarnings("all")
    public static PayRestService newStub(Invoker<?> invoker) {
        return new PayRestServiceStub((Invoker<PayRestService>)invoker);
    }

    private static final StubMethodDescriptor withdrawMethod = new StubMethodDescriptor("withdraw",
    com.iqexception.fxhelper.api.rest.pay.WithdrawRequest.class, com.iqexception.fxhelper.api.common.BaseResponse.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.rest.pay.WithdrawRequest::parseFrom,
    com.iqexception.fxhelper.api.common.BaseResponse::parseFrom);

    private static final StubMethodDescriptor withdrawAsyncMethod = new StubMethodDescriptor("withdraw",
    com.iqexception.fxhelper.api.rest.pay.WithdrawRequest.class, java.util.concurrent.CompletableFuture.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.rest.pay.WithdrawRequest::parseFrom,
    com.iqexception.fxhelper.api.common.BaseResponse::parseFrom);

    private static final StubMethodDescriptor withdrawProxyAsyncMethod = new StubMethodDescriptor("withdrawAsync",
    com.iqexception.fxhelper.api.rest.pay.WithdrawRequest.class, com.iqexception.fxhelper.api.common.BaseResponse.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.rest.pay.WithdrawRequest::parseFrom,
    com.iqexception.fxhelper.api.common.BaseResponse::parseFrom);
    private static final StubMethodDescriptor depositMethod = new StubMethodDescriptor("deposit",
    com.iqexception.fxhelper.api.rest.pay.DepositRequest.class, com.iqexception.fxhelper.api.rest.pay.DepositResponse.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.rest.pay.DepositRequest::parseFrom,
    com.iqexception.fxhelper.api.rest.pay.DepositResponse::parseFrom);

    private static final StubMethodDescriptor depositAsyncMethod = new StubMethodDescriptor("deposit",
    com.iqexception.fxhelper.api.rest.pay.DepositRequest.class, java.util.concurrent.CompletableFuture.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.rest.pay.DepositRequest::parseFrom,
    com.iqexception.fxhelper.api.rest.pay.DepositResponse::parseFrom);

    private static final StubMethodDescriptor depositProxyAsyncMethod = new StubMethodDescriptor("depositAsync",
    com.iqexception.fxhelper.api.rest.pay.DepositRequest.class, com.iqexception.fxhelper.api.rest.pay.DepositResponse.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.rest.pay.DepositRequest::parseFrom,
    com.iqexception.fxhelper.api.rest.pay.DepositResponse::parseFrom);




    static{
        serviceDescriptor.addMethod(withdrawMethod);
        serviceDescriptor.addMethod(withdrawProxyAsyncMethod);
        serviceDescriptor.addMethod(depositMethod);
        serviceDescriptor.addMethod(depositProxyAsyncMethod);
    }

    public static class PayRestServiceStub implements PayRestService{
        private final Invoker<PayRestService> invoker;

        public PayRestServiceStub(Invoker<PayRestService> invoker) {
            this.invoker = invoker;
        }

        @Override
        public com.iqexception.fxhelper.api.common.BaseResponse withdraw(com.iqexception.fxhelper.api.rest.pay.WithdrawRequest request){
            return StubInvocationUtil.unaryCall(invoker, withdrawMethod, request);
        }

        public CompletableFuture<com.iqexception.fxhelper.api.common.BaseResponse> withdrawAsync(com.iqexception.fxhelper.api.rest.pay.WithdrawRequest request){
            return StubInvocationUtil.unaryCall(invoker, withdrawAsyncMethod, request);
        }

        public void withdraw(com.iqexception.fxhelper.api.rest.pay.WithdrawRequest request, StreamObserver<com.iqexception.fxhelper.api.common.BaseResponse> responseObserver){
            StubInvocationUtil.unaryCall(invoker, withdrawMethod , request, responseObserver);
        }
        @Override
        public com.iqexception.fxhelper.api.rest.pay.DepositResponse deposit(com.iqexception.fxhelper.api.rest.pay.DepositRequest request){
            return StubInvocationUtil.unaryCall(invoker, depositMethod, request);
        }

        public CompletableFuture<com.iqexception.fxhelper.api.rest.pay.DepositResponse> depositAsync(com.iqexception.fxhelper.api.rest.pay.DepositRequest request){
            return StubInvocationUtil.unaryCall(invoker, depositAsyncMethod, request);
        }

        public void deposit(com.iqexception.fxhelper.api.rest.pay.DepositRequest request, StreamObserver<com.iqexception.fxhelper.api.rest.pay.DepositResponse> responseObserver){
            StubInvocationUtil.unaryCall(invoker, depositMethod , request, responseObserver);
        }



    }

    public static abstract class PayRestServiceImplBase implements PayRestService, ServerService<PayRestService> {

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
        public CompletableFuture<com.iqexception.fxhelper.api.common.BaseResponse> withdrawAsync(com.iqexception.fxhelper.api.rest.pay.WithdrawRequest request){
                return CompletableFuture.completedFuture(withdraw(request));
        }
        @Override
        public CompletableFuture<com.iqexception.fxhelper.api.rest.pay.DepositResponse> depositAsync(com.iqexception.fxhelper.api.rest.pay.DepositRequest request){
                return CompletableFuture.completedFuture(deposit(request));
        }

        /**
        * This server stream type unary method is <b>only</b> used for generated stub to support async unary method.
        * It will not be called if you are NOT using Dubbo3 generated triple stub and <b>DO NOT</b> implement this method.
        */
        public void withdraw(com.iqexception.fxhelper.api.rest.pay.WithdrawRequest request, StreamObserver<com.iqexception.fxhelper.api.common.BaseResponse> responseObserver){
            withdrawAsync(request).whenComplete((r, t) -> {
                if (t != null) {
                    responseObserver.onError(t);
                } else {
                    responseObserver.onNext(r);
                    responseObserver.onCompleted();
                }
            });
        }
        public void deposit(com.iqexception.fxhelper.api.rest.pay.DepositRequest request, StreamObserver<com.iqexception.fxhelper.api.rest.pay.DepositResponse> responseObserver){
            depositAsync(request).whenComplete((r, t) -> {
                if (t != null) {
                    responseObserver.onError(t);
                } else {
                    responseObserver.onNext(r);
                    responseObserver.onCompleted();
                }
            });
        }

        @Override
        public final Invoker<PayRestService> getInvoker(URL url) {
            PathResolver pathResolver = url.getOrDefaultFrameworkModel()
            .getExtensionLoader(PathResolver.class)
            .getDefaultExtension();
            Map<String,StubMethodHandler<?, ?>> handlers = new HashMap<>();

            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/withdraw");
            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/withdrawAsync");
            // for compatibility
            pathResolver.addNativeStub( "/" + JAVA_SERVICE_NAME + "/withdraw");
            pathResolver.addNativeStub( "/" + JAVA_SERVICE_NAME + "/withdrawAsync");

            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/deposit");
            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/depositAsync");
            // for compatibility
            pathResolver.addNativeStub( "/" + JAVA_SERVICE_NAME + "/deposit");
            pathResolver.addNativeStub( "/" + JAVA_SERVICE_NAME + "/depositAsync");


            BiConsumer<com.iqexception.fxhelper.api.rest.pay.WithdrawRequest, StreamObserver<com.iqexception.fxhelper.api.common.BaseResponse>> withdrawFunc = this::withdraw;
            handlers.put(withdrawMethod.getMethodName(), new UnaryStubMethodHandler<>(withdrawFunc));
            BiConsumer<com.iqexception.fxhelper.api.rest.pay.WithdrawRequest, StreamObserver<com.iqexception.fxhelper.api.common.BaseResponse>> withdrawAsyncFunc = syncToAsync(this::withdraw);
            handlers.put(withdrawProxyAsyncMethod.getMethodName(), new UnaryStubMethodHandler<>(withdrawAsyncFunc));
            BiConsumer<com.iqexception.fxhelper.api.rest.pay.DepositRequest, StreamObserver<com.iqexception.fxhelper.api.rest.pay.DepositResponse>> depositFunc = this::deposit;
            handlers.put(depositMethod.getMethodName(), new UnaryStubMethodHandler<>(depositFunc));
            BiConsumer<com.iqexception.fxhelper.api.rest.pay.DepositRequest, StreamObserver<com.iqexception.fxhelper.api.rest.pay.DepositResponse>> depositAsyncFunc = syncToAsync(this::deposit);
            handlers.put(depositProxyAsyncMethod.getMethodName(), new UnaryStubMethodHandler<>(depositAsyncFunc));




            return new StubInvoker<>(this, url, PayRestService.class, handlers);
        }


        @Override
        public com.iqexception.fxhelper.api.common.BaseResponse withdraw(com.iqexception.fxhelper.api.rest.pay.WithdrawRequest request){
            throw unimplementedMethodException(withdrawMethod);
        }

        @Override
        public com.iqexception.fxhelper.api.rest.pay.DepositResponse deposit(com.iqexception.fxhelper.api.rest.pay.DepositRequest request){
            throw unimplementedMethodException(depositMethod);
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

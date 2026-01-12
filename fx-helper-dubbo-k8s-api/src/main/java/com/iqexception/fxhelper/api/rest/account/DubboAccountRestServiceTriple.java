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

package com.iqexception.fxhelper.api.rest.account;

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

public final class DubboAccountRestServiceTriple {

    public static final String SERVICE_NAME = AccountRestService.SERVICE_NAME;

    private static final StubServiceDescriptor serviceDescriptor = new StubServiceDescriptor(SERVICE_NAME,AccountRestService.class);

    static {
        org.apache.dubbo.rpc.protocol.tri.service.SchemaDescriptorRegistry.addSchemaDescriptor(SERVICE_NAME,AccountRest.getDescriptor());
        StubSuppliers.addSupplier(SERVICE_NAME, DubboAccountRestServiceTriple::newStub);
        StubSuppliers.addSupplier(AccountRestService.JAVA_SERVICE_NAME,  DubboAccountRestServiceTriple::newStub);
        StubSuppliers.addDescriptor(SERVICE_NAME, serviceDescriptor);
        StubSuppliers.addDescriptor(AccountRestService.JAVA_SERVICE_NAME, serviceDescriptor);
    }

    @SuppressWarnings("all")
    public static AccountRestService newStub(Invoker<?> invoker) {
        return new AccountRestServiceStub((Invoker<AccountRestService>)invoker);
    }

    private static final StubMethodDescriptor getAccountMethod = new StubMethodDescriptor("getAccount",
    com.iqexception.fxhelper.api.common.BaseRequest.class, com.iqexception.fxhelper.api.rest.account.GetAccountResponse.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.common.BaseRequest::parseFrom,
    com.iqexception.fxhelper.api.rest.account.GetAccountResponse::parseFrom);

    private static final StubMethodDescriptor getAccountAsyncMethod = new StubMethodDescriptor("getAccount",
    com.iqexception.fxhelper.api.common.BaseRequest.class, java.util.concurrent.CompletableFuture.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.common.BaseRequest::parseFrom,
    com.iqexception.fxhelper.api.rest.account.GetAccountResponse::parseFrom);

    private static final StubMethodDescriptor getAccountProxyAsyncMethod = new StubMethodDescriptor("getAccountAsync",
    com.iqexception.fxhelper.api.common.BaseRequest.class, com.iqexception.fxhelper.api.rest.account.GetAccountResponse.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.common.BaseRequest::parseFrom,
    com.iqexception.fxhelper.api.rest.account.GetAccountResponse::parseFrom);




    static{
        serviceDescriptor.addMethod(getAccountMethod);
        serviceDescriptor.addMethod(getAccountProxyAsyncMethod);
    }

    public static class AccountRestServiceStub implements AccountRestService{
        private final Invoker<AccountRestService> invoker;

        public AccountRestServiceStub(Invoker<AccountRestService> invoker) {
            this.invoker = invoker;
        }

        @Override
        public com.iqexception.fxhelper.api.rest.account.GetAccountResponse getAccount(com.iqexception.fxhelper.api.common.BaseRequest request){
            return StubInvocationUtil.unaryCall(invoker, getAccountMethod, request);
        }

        public CompletableFuture<com.iqexception.fxhelper.api.rest.account.GetAccountResponse> getAccountAsync(com.iqexception.fxhelper.api.common.BaseRequest request){
            return StubInvocationUtil.unaryCall(invoker, getAccountAsyncMethod, request);
        }

        public void getAccount(com.iqexception.fxhelper.api.common.BaseRequest request, StreamObserver<com.iqexception.fxhelper.api.rest.account.GetAccountResponse> responseObserver){
            StubInvocationUtil.unaryCall(invoker, getAccountMethod , request, responseObserver);
        }



    }

    public static abstract class AccountRestServiceImplBase implements AccountRestService, ServerService<AccountRestService> {

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
        public CompletableFuture<com.iqexception.fxhelper.api.rest.account.GetAccountResponse> getAccountAsync(com.iqexception.fxhelper.api.common.BaseRequest request){
                return CompletableFuture.completedFuture(getAccount(request));
        }

        /**
        * This server stream type unary method is <b>only</b> used for generated stub to support async unary method.
        * It will not be called if you are NOT using Dubbo3 generated triple stub and <b>DO NOT</b> implement this method.
        */
        public void getAccount(com.iqexception.fxhelper.api.common.BaseRequest request, StreamObserver<com.iqexception.fxhelper.api.rest.account.GetAccountResponse> responseObserver){
            getAccountAsync(request).whenComplete((r, t) -> {
                if (t != null) {
                    responseObserver.onError(t);
                } else {
                    responseObserver.onNext(r);
                    responseObserver.onCompleted();
                }
            });
        }

        @Override
        public final Invoker<AccountRestService> getInvoker(URL url) {
            PathResolver pathResolver = url.getOrDefaultFrameworkModel()
            .getExtensionLoader(PathResolver.class)
            .getDefaultExtension();
            Map<String,StubMethodHandler<?, ?>> handlers = new HashMap<>();

            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/getAccount");
            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/getAccountAsync");
            // for compatibility
            pathResolver.addNativeStub( "/" + JAVA_SERVICE_NAME + "/getAccount");
            pathResolver.addNativeStub( "/" + JAVA_SERVICE_NAME + "/getAccountAsync");


            BiConsumer<com.iqexception.fxhelper.api.common.BaseRequest, StreamObserver<com.iqexception.fxhelper.api.rest.account.GetAccountResponse>> getAccountFunc = this::getAccount;
            handlers.put(getAccountMethod.getMethodName(), new UnaryStubMethodHandler<>(getAccountFunc));
            BiConsumer<com.iqexception.fxhelper.api.common.BaseRequest, StreamObserver<com.iqexception.fxhelper.api.rest.account.GetAccountResponse>> getAccountAsyncFunc = syncToAsync(this::getAccount);
            handlers.put(getAccountProxyAsyncMethod.getMethodName(), new UnaryStubMethodHandler<>(getAccountAsyncFunc));




            return new StubInvoker<>(this, url, AccountRestService.class, handlers);
        }


        @Override
        public com.iqexception.fxhelper.api.rest.account.GetAccountResponse getAccount(com.iqexception.fxhelper.api.common.BaseRequest request){
            throw unimplementedMethodException(getAccountMethod);
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

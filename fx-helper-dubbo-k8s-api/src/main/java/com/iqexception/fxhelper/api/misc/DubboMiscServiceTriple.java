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

package com.iqexception.fxhelper.api.misc;

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

public final class DubboMiscServiceTriple {

    public static final String SERVICE_NAME = MiscService.SERVICE_NAME;

    private static final StubServiceDescriptor serviceDescriptor = new StubServiceDescriptor(SERVICE_NAME,MiscService.class);

    static {
        org.apache.dubbo.rpc.protocol.tri.service.SchemaDescriptorRegistry.addSchemaDescriptor(SERVICE_NAME,Misc.getDescriptor());
        StubSuppliers.addSupplier(SERVICE_NAME, DubboMiscServiceTriple::newStub);
        StubSuppliers.addSupplier(MiscService.JAVA_SERVICE_NAME,  DubboMiscServiceTriple::newStub);
        StubSuppliers.addDescriptor(SERVICE_NAME, serviceDescriptor);
        StubSuppliers.addDescriptor(MiscService.JAVA_SERVICE_NAME, serviceDescriptor);
    }

    @SuppressWarnings("all")
    public static MiscService newStub(Invoker<?> invoker) {
        return new MiscServiceStub((Invoker<MiscService>)invoker);
    }

    private static final StubMethodDescriptor sendWxMessageMethod = new StubMethodDescriptor("sendWxMessage",
    com.iqexception.fxhelper.api.misc.SendWxMessageRequest.class, com.iqexception.fxhelper.api.common.BaseResponse.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.misc.SendWxMessageRequest::parseFrom,
    com.iqexception.fxhelper.api.common.BaseResponse::parseFrom);

    private static final StubMethodDescriptor sendWxMessageAsyncMethod = new StubMethodDescriptor("sendWxMessage",
    com.iqexception.fxhelper.api.misc.SendWxMessageRequest.class, java.util.concurrent.CompletableFuture.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.misc.SendWxMessageRequest::parseFrom,
    com.iqexception.fxhelper.api.common.BaseResponse::parseFrom);

    private static final StubMethodDescriptor sendWxMessageProxyAsyncMethod = new StubMethodDescriptor("sendWxMessageAsync",
    com.iqexception.fxhelper.api.misc.SendWxMessageRequest.class, com.iqexception.fxhelper.api.common.BaseResponse.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.misc.SendWxMessageRequest::parseFrom,
    com.iqexception.fxhelper.api.common.BaseResponse::parseFrom);
    private static final StubMethodDescriptor getAccessTokenMethod = new StubMethodDescriptor("getAccessToken",
    com.google.protobuf.Empty.class, com.iqexception.fxhelper.api.misc.GetAccessTokenResponse.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.google.protobuf.Empty::parseFrom,
    com.iqexception.fxhelper.api.misc.GetAccessTokenResponse::parseFrom);

    private static final StubMethodDescriptor getAccessTokenAsyncMethod = new StubMethodDescriptor("getAccessToken",
    com.google.protobuf.Empty.class, java.util.concurrent.CompletableFuture.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.google.protobuf.Empty::parseFrom,
    com.iqexception.fxhelper.api.misc.GetAccessTokenResponse::parseFrom);

    private static final StubMethodDescriptor getAccessTokenProxyAsyncMethod = new StubMethodDescriptor("getAccessTokenAsync",
    com.google.protobuf.Empty.class, com.iqexception.fxhelper.api.misc.GetAccessTokenResponse.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.google.protobuf.Empty::parseFrom,
    com.iqexception.fxhelper.api.misc.GetAccessTokenResponse::parseFrom);




    static{
        serviceDescriptor.addMethod(sendWxMessageMethod);
        serviceDescriptor.addMethod(sendWxMessageProxyAsyncMethod);
        serviceDescriptor.addMethod(getAccessTokenMethod);
        serviceDescriptor.addMethod(getAccessTokenProxyAsyncMethod);
    }

    public static class MiscServiceStub implements MiscService{
        private final Invoker<MiscService> invoker;

        public MiscServiceStub(Invoker<MiscService> invoker) {
            this.invoker = invoker;
        }

        @Override
        public com.iqexception.fxhelper.api.common.BaseResponse sendWxMessage(com.iqexception.fxhelper.api.misc.SendWxMessageRequest request){
            return StubInvocationUtil.unaryCall(invoker, sendWxMessageMethod, request);
        }

        public CompletableFuture<com.iqexception.fxhelper.api.common.BaseResponse> sendWxMessageAsync(com.iqexception.fxhelper.api.misc.SendWxMessageRequest request){
            return StubInvocationUtil.unaryCall(invoker, sendWxMessageAsyncMethod, request);
        }

        public void sendWxMessage(com.iqexception.fxhelper.api.misc.SendWxMessageRequest request, StreamObserver<com.iqexception.fxhelper.api.common.BaseResponse> responseObserver){
            StubInvocationUtil.unaryCall(invoker, sendWxMessageMethod , request, responseObserver);
        }
        @Override
        public com.iqexception.fxhelper.api.misc.GetAccessTokenResponse getAccessToken(com.google.protobuf.Empty request){
            return StubInvocationUtil.unaryCall(invoker, getAccessTokenMethod, request);
        }

        public CompletableFuture<com.iqexception.fxhelper.api.misc.GetAccessTokenResponse> getAccessTokenAsync(com.google.protobuf.Empty request){
            return StubInvocationUtil.unaryCall(invoker, getAccessTokenAsyncMethod, request);
        }

        public void getAccessToken(com.google.protobuf.Empty request, StreamObserver<com.iqexception.fxhelper.api.misc.GetAccessTokenResponse> responseObserver){
            StubInvocationUtil.unaryCall(invoker, getAccessTokenMethod , request, responseObserver);
        }



    }

    public static abstract class MiscServiceImplBase implements MiscService, ServerService<MiscService> {

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
        public CompletableFuture<com.iqexception.fxhelper.api.common.BaseResponse> sendWxMessageAsync(com.iqexception.fxhelper.api.misc.SendWxMessageRequest request){
                return CompletableFuture.completedFuture(sendWxMessage(request));
        }
        @Override
        public CompletableFuture<com.iqexception.fxhelper.api.misc.GetAccessTokenResponse> getAccessTokenAsync(com.google.protobuf.Empty request){
                return CompletableFuture.completedFuture(getAccessToken(request));
        }

        /**
        * This server stream type unary method is <b>only</b> used for generated stub to support async unary method.
        * It will not be called if you are NOT using Dubbo3 generated triple stub and <b>DO NOT</b> implement this method.
        */
        public void sendWxMessage(com.iqexception.fxhelper.api.misc.SendWxMessageRequest request, StreamObserver<com.iqexception.fxhelper.api.common.BaseResponse> responseObserver){
            sendWxMessageAsync(request).whenComplete((r, t) -> {
                if (t != null) {
                    responseObserver.onError(t);
                } else {
                    responseObserver.onNext(r);
                    responseObserver.onCompleted();
                }
            });
        }
        public void getAccessToken(com.google.protobuf.Empty request, StreamObserver<com.iqexception.fxhelper.api.misc.GetAccessTokenResponse> responseObserver){
            getAccessTokenAsync(request).whenComplete((r, t) -> {
                if (t != null) {
                    responseObserver.onError(t);
                } else {
                    responseObserver.onNext(r);
                    responseObserver.onCompleted();
                }
            });
        }

        @Override
        public final Invoker<MiscService> getInvoker(URL url) {
            PathResolver pathResolver = url.getOrDefaultFrameworkModel()
            .getExtensionLoader(PathResolver.class)
            .getDefaultExtension();
            Map<String,StubMethodHandler<?, ?>> handlers = new HashMap<>();

            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/sendWxMessage");
            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/sendWxMessageAsync");
            // for compatibility
            pathResolver.addNativeStub( "/" + JAVA_SERVICE_NAME + "/sendWxMessage");
            pathResolver.addNativeStub( "/" + JAVA_SERVICE_NAME + "/sendWxMessageAsync");

            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/getAccessToken");
            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/getAccessTokenAsync");
            // for compatibility
            pathResolver.addNativeStub( "/" + JAVA_SERVICE_NAME + "/getAccessToken");
            pathResolver.addNativeStub( "/" + JAVA_SERVICE_NAME + "/getAccessTokenAsync");


            BiConsumer<com.iqexception.fxhelper.api.misc.SendWxMessageRequest, StreamObserver<com.iqexception.fxhelper.api.common.BaseResponse>> sendWxMessageFunc = this::sendWxMessage;
            handlers.put(sendWxMessageMethod.getMethodName(), new UnaryStubMethodHandler<>(sendWxMessageFunc));
            BiConsumer<com.iqexception.fxhelper.api.misc.SendWxMessageRequest, StreamObserver<com.iqexception.fxhelper.api.common.BaseResponse>> sendWxMessageAsyncFunc = syncToAsync(this::sendWxMessage);
            handlers.put(sendWxMessageProxyAsyncMethod.getMethodName(), new UnaryStubMethodHandler<>(sendWxMessageAsyncFunc));
            BiConsumer<com.google.protobuf.Empty, StreamObserver<com.iqexception.fxhelper.api.misc.GetAccessTokenResponse>> getAccessTokenFunc = this::getAccessToken;
            handlers.put(getAccessTokenMethod.getMethodName(), new UnaryStubMethodHandler<>(getAccessTokenFunc));
            BiConsumer<com.google.protobuf.Empty, StreamObserver<com.iqexception.fxhelper.api.misc.GetAccessTokenResponse>> getAccessTokenAsyncFunc = syncToAsync(this::getAccessToken);
            handlers.put(getAccessTokenProxyAsyncMethod.getMethodName(), new UnaryStubMethodHandler<>(getAccessTokenAsyncFunc));




            return new StubInvoker<>(this, url, MiscService.class, handlers);
        }


        @Override
        public com.iqexception.fxhelper.api.common.BaseResponse sendWxMessage(com.iqexception.fxhelper.api.misc.SendWxMessageRequest request){
            throw unimplementedMethodException(sendWxMessageMethod);
        }

        @Override
        public com.iqexception.fxhelper.api.misc.GetAccessTokenResponse getAccessToken(com.google.protobuf.Empty request){
            throw unimplementedMethodException(getAccessTokenMethod);
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

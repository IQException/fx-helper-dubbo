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

package com.iqexception.fxhelper.api.rest.shop;

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

public final class DubboShopRestServiceTriple {

    public static final String SERVICE_NAME = ShopRestService.SERVICE_NAME;

    private static final StubServiceDescriptor serviceDescriptor = new StubServiceDescriptor(SERVICE_NAME,ShopRestService.class);

    static {
        org.apache.dubbo.rpc.protocol.tri.service.SchemaDescriptorRegistry.addSchemaDescriptor(SERVICE_NAME,ShopRest.getDescriptor());
        StubSuppliers.addSupplier(SERVICE_NAME, DubboShopRestServiceTriple::newStub);
        StubSuppliers.addSupplier(ShopRestService.JAVA_SERVICE_NAME,  DubboShopRestServiceTriple::newStub);
        StubSuppliers.addDescriptor(SERVICE_NAME, serviceDescriptor);
        StubSuppliers.addDescriptor(ShopRestService.JAVA_SERVICE_NAME, serviceDescriptor);
    }

    @SuppressWarnings("all")
    public static ShopRestService newStub(Invoker<?> invoker) {
        return new ShopRestServiceStub((Invoker<ShopRestService>)invoker);
    }

    private static final StubMethodDescriptor createMethod = new StubMethodDescriptor("create",
    com.iqexception.fxhelper.api.rest.shop.CreateRequest.class, com.iqexception.fxhelper.api.rest.shop.CreateResponse.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.rest.shop.CreateRequest::parseFrom,
    com.iqexception.fxhelper.api.rest.shop.CreateResponse::parseFrom);

    private static final StubMethodDescriptor createAsyncMethod = new StubMethodDescriptor("create",
    com.iqexception.fxhelper.api.rest.shop.CreateRequest.class, java.util.concurrent.CompletableFuture.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.rest.shop.CreateRequest::parseFrom,
    com.iqexception.fxhelper.api.rest.shop.CreateResponse::parseFrom);

    private static final StubMethodDescriptor createProxyAsyncMethod = new StubMethodDescriptor("createAsync",
    com.iqexception.fxhelper.api.rest.shop.CreateRequest.class, com.iqexception.fxhelper.api.rest.shop.CreateResponse.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.rest.shop.CreateRequest::parseFrom,
    com.iqexception.fxhelper.api.rest.shop.CreateResponse::parseFrom);
    private static final StubMethodDescriptor updateMethod = new StubMethodDescriptor("update",
    com.iqexception.fxhelper.api.rest.shop.UpdateRequest.class, com.iqexception.fxhelper.api.common.BaseResponse.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.rest.shop.UpdateRequest::parseFrom,
    com.iqexception.fxhelper.api.common.BaseResponse::parseFrom);

    private static final StubMethodDescriptor updateAsyncMethod = new StubMethodDescriptor("update",
    com.iqexception.fxhelper.api.rest.shop.UpdateRequest.class, java.util.concurrent.CompletableFuture.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.rest.shop.UpdateRequest::parseFrom,
    com.iqexception.fxhelper.api.common.BaseResponse::parseFrom);

    private static final StubMethodDescriptor updateProxyAsyncMethod = new StubMethodDescriptor("updateAsync",
    com.iqexception.fxhelper.api.rest.shop.UpdateRequest.class, com.iqexception.fxhelper.api.common.BaseResponse.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.rest.shop.UpdateRequest::parseFrom,
    com.iqexception.fxhelper.api.common.BaseResponse::parseFrom);
    private static final StubMethodDescriptor getShopListMethod = new StubMethodDescriptor("getShopList",
    com.iqexception.fxhelper.api.common.BaseRequest.class, com.iqexception.fxhelper.api.rest.shop.GetShopListResponse.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.common.BaseRequest::parseFrom,
    com.iqexception.fxhelper.api.rest.shop.GetShopListResponse::parseFrom);

    private static final StubMethodDescriptor getShopListAsyncMethod = new StubMethodDescriptor("getShopList",
    com.iqexception.fxhelper.api.common.BaseRequest.class, java.util.concurrent.CompletableFuture.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.common.BaseRequest::parseFrom,
    com.iqexception.fxhelper.api.rest.shop.GetShopListResponse::parseFrom);

    private static final StubMethodDescriptor getShopListProxyAsyncMethod = new StubMethodDescriptor("getShopListAsync",
    com.iqexception.fxhelper.api.common.BaseRequest.class, com.iqexception.fxhelper.api.rest.shop.GetShopListResponse.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.common.BaseRequest::parseFrom,
    com.iqexception.fxhelper.api.rest.shop.GetShopListResponse::parseFrom);
    private static final StubMethodDescriptor getShopPublicInfoMethod = new StubMethodDescriptor("getShopPublicInfo",
    com.iqexception.fxhelper.api.rest.shop.GetShopPublicInfoRequest.class, com.iqexception.fxhelper.api.rest.shop.GetShopPublicInfoResponse.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.rest.shop.GetShopPublicInfoRequest::parseFrom,
    com.iqexception.fxhelper.api.rest.shop.GetShopPublicInfoResponse::parseFrom);

    private static final StubMethodDescriptor getShopPublicInfoAsyncMethod = new StubMethodDescriptor("getShopPublicInfo",
    com.iqexception.fxhelper.api.rest.shop.GetShopPublicInfoRequest.class, java.util.concurrent.CompletableFuture.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.rest.shop.GetShopPublicInfoRequest::parseFrom,
    com.iqexception.fxhelper.api.rest.shop.GetShopPublicInfoResponse::parseFrom);

    private static final StubMethodDescriptor getShopPublicInfoProxyAsyncMethod = new StubMethodDescriptor("getShopPublicInfoAsync",
    com.iqexception.fxhelper.api.rest.shop.GetShopPublicInfoRequest.class, com.iqexception.fxhelper.api.rest.shop.GetShopPublicInfoResponse.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.rest.shop.GetShopPublicInfoRequest::parseFrom,
    com.iqexception.fxhelper.api.rest.shop.GetShopPublicInfoResponse::parseFrom);
    private static final StubMethodDescriptor getShopDetailInfoMethod = new StubMethodDescriptor("getShopDetailInfo",
    com.iqexception.fxhelper.api.rest.shop.GetShopDetailInfoRequest.class, com.iqexception.fxhelper.api.rest.shop.GetShopDetailInfoResponse.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.rest.shop.GetShopDetailInfoRequest::parseFrom,
    com.iqexception.fxhelper.api.rest.shop.GetShopDetailInfoResponse::parseFrom);

    private static final StubMethodDescriptor getShopDetailInfoAsyncMethod = new StubMethodDescriptor("getShopDetailInfo",
    com.iqexception.fxhelper.api.rest.shop.GetShopDetailInfoRequest.class, java.util.concurrent.CompletableFuture.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.rest.shop.GetShopDetailInfoRequest::parseFrom,
    com.iqexception.fxhelper.api.rest.shop.GetShopDetailInfoResponse::parseFrom);

    private static final StubMethodDescriptor getShopDetailInfoProxyAsyncMethod = new StubMethodDescriptor("getShopDetailInfoAsync",
    com.iqexception.fxhelper.api.rest.shop.GetShopDetailInfoRequest.class, com.iqexception.fxhelper.api.rest.shop.GetShopDetailInfoResponse.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.rest.shop.GetShopDetailInfoRequest::parseFrom,
    com.iqexception.fxhelper.api.rest.shop.GetShopDetailInfoResponse::parseFrom);
    private static final StubMethodDescriptor getQrCodesMethod = new StubMethodDescriptor("getQrCodes",
    com.iqexception.fxhelper.api.rest.shop.GetQrCodesRequest.class, com.iqexception.fxhelper.api.rest.shop.GetQrCodesResponse.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.rest.shop.GetQrCodesRequest::parseFrom,
    com.iqexception.fxhelper.api.rest.shop.GetQrCodesResponse::parseFrom);

    private static final StubMethodDescriptor getQrCodesAsyncMethod = new StubMethodDescriptor("getQrCodes",
    com.iqexception.fxhelper.api.rest.shop.GetQrCodesRequest.class, java.util.concurrent.CompletableFuture.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.rest.shop.GetQrCodesRequest::parseFrom,
    com.iqexception.fxhelper.api.rest.shop.GetQrCodesResponse::parseFrom);

    private static final StubMethodDescriptor getQrCodesProxyAsyncMethod = new StubMethodDescriptor("getQrCodesAsync",
    com.iqexception.fxhelper.api.rest.shop.GetQrCodesRequest.class, com.iqexception.fxhelper.api.rest.shop.GetQrCodesResponse.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.rest.shop.GetQrCodesRequest::parseFrom,
    com.iqexception.fxhelper.api.rest.shop.GetQrCodesResponse::parseFrom);
    private static final StubMethodDescriptor switchFxMethod = new StubMethodDescriptor("switchFx",
    com.iqexception.fxhelper.api.rest.shop.SwitchFxRequest.class, com.iqexception.fxhelper.api.common.BaseResponse.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.rest.shop.SwitchFxRequest::parseFrom,
    com.iqexception.fxhelper.api.common.BaseResponse::parseFrom);

    private static final StubMethodDescriptor switchFxAsyncMethod = new StubMethodDescriptor("switchFx",
    com.iqexception.fxhelper.api.rest.shop.SwitchFxRequest.class, java.util.concurrent.CompletableFuture.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.rest.shop.SwitchFxRequest::parseFrom,
    com.iqexception.fxhelper.api.common.BaseResponse::parseFrom);

    private static final StubMethodDescriptor switchFxProxyAsyncMethod = new StubMethodDescriptor("switchFxAsync",
    com.iqexception.fxhelper.api.rest.shop.SwitchFxRequest.class, com.iqexception.fxhelper.api.common.BaseResponse.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.rest.shop.SwitchFxRequest::parseFrom,
    com.iqexception.fxhelper.api.common.BaseResponse::parseFrom);




    static{
        serviceDescriptor.addMethod(createMethod);
        serviceDescriptor.addMethod(createProxyAsyncMethod);
        serviceDescriptor.addMethod(updateMethod);
        serviceDescriptor.addMethod(updateProxyAsyncMethod);
        serviceDescriptor.addMethod(getShopListMethod);
        serviceDescriptor.addMethod(getShopListProxyAsyncMethod);
        serviceDescriptor.addMethod(getShopPublicInfoMethod);
        serviceDescriptor.addMethod(getShopPublicInfoProxyAsyncMethod);
        serviceDescriptor.addMethod(getShopDetailInfoMethod);
        serviceDescriptor.addMethod(getShopDetailInfoProxyAsyncMethod);
        serviceDescriptor.addMethod(getQrCodesMethod);
        serviceDescriptor.addMethod(getQrCodesProxyAsyncMethod);
        serviceDescriptor.addMethod(switchFxMethod);
        serviceDescriptor.addMethod(switchFxProxyAsyncMethod);
    }

    public static class ShopRestServiceStub implements ShopRestService{
        private final Invoker<ShopRestService> invoker;

        public ShopRestServiceStub(Invoker<ShopRestService> invoker) {
            this.invoker = invoker;
        }

        @Override
        public com.iqexception.fxhelper.api.rest.shop.CreateResponse create(com.iqexception.fxhelper.api.rest.shop.CreateRequest request){
            return StubInvocationUtil.unaryCall(invoker, createMethod, request);
        }

        public CompletableFuture<com.iqexception.fxhelper.api.rest.shop.CreateResponse> createAsync(com.iqexception.fxhelper.api.rest.shop.CreateRequest request){
            return StubInvocationUtil.unaryCall(invoker, createAsyncMethod, request);
        }

        public void create(com.iqexception.fxhelper.api.rest.shop.CreateRequest request, StreamObserver<com.iqexception.fxhelper.api.rest.shop.CreateResponse> responseObserver){
            StubInvocationUtil.unaryCall(invoker, createMethod , request, responseObserver);
        }
        @Override
        public com.iqexception.fxhelper.api.common.BaseResponse update(com.iqexception.fxhelper.api.rest.shop.UpdateRequest request){
            return StubInvocationUtil.unaryCall(invoker, updateMethod, request);
        }

        public CompletableFuture<com.iqexception.fxhelper.api.common.BaseResponse> updateAsync(com.iqexception.fxhelper.api.rest.shop.UpdateRequest request){
            return StubInvocationUtil.unaryCall(invoker, updateAsyncMethod, request);
        }

        public void update(com.iqexception.fxhelper.api.rest.shop.UpdateRequest request, StreamObserver<com.iqexception.fxhelper.api.common.BaseResponse> responseObserver){
            StubInvocationUtil.unaryCall(invoker, updateMethod , request, responseObserver);
        }
        @Override
        public com.iqexception.fxhelper.api.rest.shop.GetShopListResponse getShopList(com.iqexception.fxhelper.api.common.BaseRequest request){
            return StubInvocationUtil.unaryCall(invoker, getShopListMethod, request);
        }

        public CompletableFuture<com.iqexception.fxhelper.api.rest.shop.GetShopListResponse> getShopListAsync(com.iqexception.fxhelper.api.common.BaseRequest request){
            return StubInvocationUtil.unaryCall(invoker, getShopListAsyncMethod, request);
        }

        public void getShopList(com.iqexception.fxhelper.api.common.BaseRequest request, StreamObserver<com.iqexception.fxhelper.api.rest.shop.GetShopListResponse> responseObserver){
            StubInvocationUtil.unaryCall(invoker, getShopListMethod , request, responseObserver);
        }
        @Override
        public com.iqexception.fxhelper.api.rest.shop.GetShopPublicInfoResponse getShopPublicInfo(com.iqexception.fxhelper.api.rest.shop.GetShopPublicInfoRequest request){
            return StubInvocationUtil.unaryCall(invoker, getShopPublicInfoMethod, request);
        }

        public CompletableFuture<com.iqexception.fxhelper.api.rest.shop.GetShopPublicInfoResponse> getShopPublicInfoAsync(com.iqexception.fxhelper.api.rest.shop.GetShopPublicInfoRequest request){
            return StubInvocationUtil.unaryCall(invoker, getShopPublicInfoAsyncMethod, request);
        }

        public void getShopPublicInfo(com.iqexception.fxhelper.api.rest.shop.GetShopPublicInfoRequest request, StreamObserver<com.iqexception.fxhelper.api.rest.shop.GetShopPublicInfoResponse> responseObserver){
            StubInvocationUtil.unaryCall(invoker, getShopPublicInfoMethod , request, responseObserver);
        }
        @Override
        public com.iqexception.fxhelper.api.rest.shop.GetShopDetailInfoResponse getShopDetailInfo(com.iqexception.fxhelper.api.rest.shop.GetShopDetailInfoRequest request){
            return StubInvocationUtil.unaryCall(invoker, getShopDetailInfoMethod, request);
        }

        public CompletableFuture<com.iqexception.fxhelper.api.rest.shop.GetShopDetailInfoResponse> getShopDetailInfoAsync(com.iqexception.fxhelper.api.rest.shop.GetShopDetailInfoRequest request){
            return StubInvocationUtil.unaryCall(invoker, getShopDetailInfoAsyncMethod, request);
        }

        public void getShopDetailInfo(com.iqexception.fxhelper.api.rest.shop.GetShopDetailInfoRequest request, StreamObserver<com.iqexception.fxhelper.api.rest.shop.GetShopDetailInfoResponse> responseObserver){
            StubInvocationUtil.unaryCall(invoker, getShopDetailInfoMethod , request, responseObserver);
        }
        @Override
        public com.iqexception.fxhelper.api.rest.shop.GetQrCodesResponse getQrCodes(com.iqexception.fxhelper.api.rest.shop.GetQrCodesRequest request){
            return StubInvocationUtil.unaryCall(invoker, getQrCodesMethod, request);
        }

        public CompletableFuture<com.iqexception.fxhelper.api.rest.shop.GetQrCodesResponse> getQrCodesAsync(com.iqexception.fxhelper.api.rest.shop.GetQrCodesRequest request){
            return StubInvocationUtil.unaryCall(invoker, getQrCodesAsyncMethod, request);
        }

        public void getQrCodes(com.iqexception.fxhelper.api.rest.shop.GetQrCodesRequest request, StreamObserver<com.iqexception.fxhelper.api.rest.shop.GetQrCodesResponse> responseObserver){
            StubInvocationUtil.unaryCall(invoker, getQrCodesMethod , request, responseObserver);
        }
        @Override
        public com.iqexception.fxhelper.api.common.BaseResponse switchFx(com.iqexception.fxhelper.api.rest.shop.SwitchFxRequest request){
            return StubInvocationUtil.unaryCall(invoker, switchFxMethod, request);
        }

        public CompletableFuture<com.iqexception.fxhelper.api.common.BaseResponse> switchFxAsync(com.iqexception.fxhelper.api.rest.shop.SwitchFxRequest request){
            return StubInvocationUtil.unaryCall(invoker, switchFxAsyncMethod, request);
        }

        public void switchFx(com.iqexception.fxhelper.api.rest.shop.SwitchFxRequest request, StreamObserver<com.iqexception.fxhelper.api.common.BaseResponse> responseObserver){
            StubInvocationUtil.unaryCall(invoker, switchFxMethod , request, responseObserver);
        }



    }

    public static abstract class ShopRestServiceImplBase implements ShopRestService, ServerService<ShopRestService> {

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
        public CompletableFuture<com.iqexception.fxhelper.api.rest.shop.CreateResponse> createAsync(com.iqexception.fxhelper.api.rest.shop.CreateRequest request){
                return CompletableFuture.completedFuture(create(request));
        }
        @Override
        public CompletableFuture<com.iqexception.fxhelper.api.common.BaseResponse> updateAsync(com.iqexception.fxhelper.api.rest.shop.UpdateRequest request){
                return CompletableFuture.completedFuture(update(request));
        }
        @Override
        public CompletableFuture<com.iqexception.fxhelper.api.rest.shop.GetShopListResponse> getShopListAsync(com.iqexception.fxhelper.api.common.BaseRequest request){
                return CompletableFuture.completedFuture(getShopList(request));
        }
        @Override
        public CompletableFuture<com.iqexception.fxhelper.api.rest.shop.GetShopPublicInfoResponse> getShopPublicInfoAsync(com.iqexception.fxhelper.api.rest.shop.GetShopPublicInfoRequest request){
                return CompletableFuture.completedFuture(getShopPublicInfo(request));
        }
        @Override
        public CompletableFuture<com.iqexception.fxhelper.api.rest.shop.GetShopDetailInfoResponse> getShopDetailInfoAsync(com.iqexception.fxhelper.api.rest.shop.GetShopDetailInfoRequest request){
                return CompletableFuture.completedFuture(getShopDetailInfo(request));
        }
        @Override
        public CompletableFuture<com.iqexception.fxhelper.api.rest.shop.GetQrCodesResponse> getQrCodesAsync(com.iqexception.fxhelper.api.rest.shop.GetQrCodesRequest request){
                return CompletableFuture.completedFuture(getQrCodes(request));
        }
        @Override
        public CompletableFuture<com.iqexception.fxhelper.api.common.BaseResponse> switchFxAsync(com.iqexception.fxhelper.api.rest.shop.SwitchFxRequest request){
                return CompletableFuture.completedFuture(switchFx(request));
        }

        /**
        * This server stream type unary method is <b>only</b> used for generated stub to support async unary method.
        * It will not be called if you are NOT using Dubbo3 generated triple stub and <b>DO NOT</b> implement this method.
        */
        public void create(com.iqexception.fxhelper.api.rest.shop.CreateRequest request, StreamObserver<com.iqexception.fxhelper.api.rest.shop.CreateResponse> responseObserver){
            createAsync(request).whenComplete((r, t) -> {
                if (t != null) {
                    responseObserver.onError(t);
                } else {
                    responseObserver.onNext(r);
                    responseObserver.onCompleted();
                }
            });
        }
        public void update(com.iqexception.fxhelper.api.rest.shop.UpdateRequest request, StreamObserver<com.iqexception.fxhelper.api.common.BaseResponse> responseObserver){
            updateAsync(request).whenComplete((r, t) -> {
                if (t != null) {
                    responseObserver.onError(t);
                } else {
                    responseObserver.onNext(r);
                    responseObserver.onCompleted();
                }
            });
        }
        public void getShopList(com.iqexception.fxhelper.api.common.BaseRequest request, StreamObserver<com.iqexception.fxhelper.api.rest.shop.GetShopListResponse> responseObserver){
            getShopListAsync(request).whenComplete((r, t) -> {
                if (t != null) {
                    responseObserver.onError(t);
                } else {
                    responseObserver.onNext(r);
                    responseObserver.onCompleted();
                }
            });
        }
        public void getShopPublicInfo(com.iqexception.fxhelper.api.rest.shop.GetShopPublicInfoRequest request, StreamObserver<com.iqexception.fxhelper.api.rest.shop.GetShopPublicInfoResponse> responseObserver){
            getShopPublicInfoAsync(request).whenComplete((r, t) -> {
                if (t != null) {
                    responseObserver.onError(t);
                } else {
                    responseObserver.onNext(r);
                    responseObserver.onCompleted();
                }
            });
        }
        public void getShopDetailInfo(com.iqexception.fxhelper.api.rest.shop.GetShopDetailInfoRequest request, StreamObserver<com.iqexception.fxhelper.api.rest.shop.GetShopDetailInfoResponse> responseObserver){
            getShopDetailInfoAsync(request).whenComplete((r, t) -> {
                if (t != null) {
                    responseObserver.onError(t);
                } else {
                    responseObserver.onNext(r);
                    responseObserver.onCompleted();
                }
            });
        }
        public void getQrCodes(com.iqexception.fxhelper.api.rest.shop.GetQrCodesRequest request, StreamObserver<com.iqexception.fxhelper.api.rest.shop.GetQrCodesResponse> responseObserver){
            getQrCodesAsync(request).whenComplete((r, t) -> {
                if (t != null) {
                    responseObserver.onError(t);
                } else {
                    responseObserver.onNext(r);
                    responseObserver.onCompleted();
                }
            });
        }
        public void switchFx(com.iqexception.fxhelper.api.rest.shop.SwitchFxRequest request, StreamObserver<com.iqexception.fxhelper.api.common.BaseResponse> responseObserver){
            switchFxAsync(request).whenComplete((r, t) -> {
                if (t != null) {
                    responseObserver.onError(t);
                } else {
                    responseObserver.onNext(r);
                    responseObserver.onCompleted();
                }
            });
        }

        @Override
        public final Invoker<ShopRestService> getInvoker(URL url) {
            PathResolver pathResolver = url.getOrDefaultFrameworkModel()
            .getExtensionLoader(PathResolver.class)
            .getDefaultExtension();
            Map<String,StubMethodHandler<?, ?>> handlers = new HashMap<>();

            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/create");
            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/createAsync");
            // for compatibility
            pathResolver.addNativeStub( "/" + JAVA_SERVICE_NAME + "/create");
            pathResolver.addNativeStub( "/" + JAVA_SERVICE_NAME + "/createAsync");

            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/update");
            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/updateAsync");
            // for compatibility
            pathResolver.addNativeStub( "/" + JAVA_SERVICE_NAME + "/update");
            pathResolver.addNativeStub( "/" + JAVA_SERVICE_NAME + "/updateAsync");

            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/getShopList");
            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/getShopListAsync");
            // for compatibility
            pathResolver.addNativeStub( "/" + JAVA_SERVICE_NAME + "/getShopList");
            pathResolver.addNativeStub( "/" + JAVA_SERVICE_NAME + "/getShopListAsync");

            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/getShopPublicInfo");
            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/getShopPublicInfoAsync");
            // for compatibility
            pathResolver.addNativeStub( "/" + JAVA_SERVICE_NAME + "/getShopPublicInfo");
            pathResolver.addNativeStub( "/" + JAVA_SERVICE_NAME + "/getShopPublicInfoAsync");

            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/getShopDetailInfo");
            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/getShopDetailInfoAsync");
            // for compatibility
            pathResolver.addNativeStub( "/" + JAVA_SERVICE_NAME + "/getShopDetailInfo");
            pathResolver.addNativeStub( "/" + JAVA_SERVICE_NAME + "/getShopDetailInfoAsync");

            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/getQrCodes");
            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/getQrCodesAsync");
            // for compatibility
            pathResolver.addNativeStub( "/" + JAVA_SERVICE_NAME + "/getQrCodes");
            pathResolver.addNativeStub( "/" + JAVA_SERVICE_NAME + "/getQrCodesAsync");

            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/switchFx");
            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/switchFxAsync");
            // for compatibility
            pathResolver.addNativeStub( "/" + JAVA_SERVICE_NAME + "/switchFx");
            pathResolver.addNativeStub( "/" + JAVA_SERVICE_NAME + "/switchFxAsync");


            BiConsumer<com.iqexception.fxhelper.api.rest.shop.CreateRequest, StreamObserver<com.iqexception.fxhelper.api.rest.shop.CreateResponse>> createFunc = this::create;
            handlers.put(createMethod.getMethodName(), new UnaryStubMethodHandler<>(createFunc));
            BiConsumer<com.iqexception.fxhelper.api.rest.shop.CreateRequest, StreamObserver<com.iqexception.fxhelper.api.rest.shop.CreateResponse>> createAsyncFunc = syncToAsync(this::create);
            handlers.put(createProxyAsyncMethod.getMethodName(), new UnaryStubMethodHandler<>(createAsyncFunc));
            BiConsumer<com.iqexception.fxhelper.api.rest.shop.UpdateRequest, StreamObserver<com.iqexception.fxhelper.api.common.BaseResponse>> updateFunc = this::update;
            handlers.put(updateMethod.getMethodName(), new UnaryStubMethodHandler<>(updateFunc));
            BiConsumer<com.iqexception.fxhelper.api.rest.shop.UpdateRequest, StreamObserver<com.iqexception.fxhelper.api.common.BaseResponse>> updateAsyncFunc = syncToAsync(this::update);
            handlers.put(updateProxyAsyncMethod.getMethodName(), new UnaryStubMethodHandler<>(updateAsyncFunc));
            BiConsumer<com.iqexception.fxhelper.api.common.BaseRequest, StreamObserver<com.iqexception.fxhelper.api.rest.shop.GetShopListResponse>> getShopListFunc = this::getShopList;
            handlers.put(getShopListMethod.getMethodName(), new UnaryStubMethodHandler<>(getShopListFunc));
            BiConsumer<com.iqexception.fxhelper.api.common.BaseRequest, StreamObserver<com.iqexception.fxhelper.api.rest.shop.GetShopListResponse>> getShopListAsyncFunc = syncToAsync(this::getShopList);
            handlers.put(getShopListProxyAsyncMethod.getMethodName(), new UnaryStubMethodHandler<>(getShopListAsyncFunc));
            BiConsumer<com.iqexception.fxhelper.api.rest.shop.GetShopPublicInfoRequest, StreamObserver<com.iqexception.fxhelper.api.rest.shop.GetShopPublicInfoResponse>> getShopPublicInfoFunc = this::getShopPublicInfo;
            handlers.put(getShopPublicInfoMethod.getMethodName(), new UnaryStubMethodHandler<>(getShopPublicInfoFunc));
            BiConsumer<com.iqexception.fxhelper.api.rest.shop.GetShopPublicInfoRequest, StreamObserver<com.iqexception.fxhelper.api.rest.shop.GetShopPublicInfoResponse>> getShopPublicInfoAsyncFunc = syncToAsync(this::getShopPublicInfo);
            handlers.put(getShopPublicInfoProxyAsyncMethod.getMethodName(), new UnaryStubMethodHandler<>(getShopPublicInfoAsyncFunc));
            BiConsumer<com.iqexception.fxhelper.api.rest.shop.GetShopDetailInfoRequest, StreamObserver<com.iqexception.fxhelper.api.rest.shop.GetShopDetailInfoResponse>> getShopDetailInfoFunc = this::getShopDetailInfo;
            handlers.put(getShopDetailInfoMethod.getMethodName(), new UnaryStubMethodHandler<>(getShopDetailInfoFunc));
            BiConsumer<com.iqexception.fxhelper.api.rest.shop.GetShopDetailInfoRequest, StreamObserver<com.iqexception.fxhelper.api.rest.shop.GetShopDetailInfoResponse>> getShopDetailInfoAsyncFunc = syncToAsync(this::getShopDetailInfo);
            handlers.put(getShopDetailInfoProxyAsyncMethod.getMethodName(), new UnaryStubMethodHandler<>(getShopDetailInfoAsyncFunc));
            BiConsumer<com.iqexception.fxhelper.api.rest.shop.GetQrCodesRequest, StreamObserver<com.iqexception.fxhelper.api.rest.shop.GetQrCodesResponse>> getQrCodesFunc = this::getQrCodes;
            handlers.put(getQrCodesMethod.getMethodName(), new UnaryStubMethodHandler<>(getQrCodesFunc));
            BiConsumer<com.iqexception.fxhelper.api.rest.shop.GetQrCodesRequest, StreamObserver<com.iqexception.fxhelper.api.rest.shop.GetQrCodesResponse>> getQrCodesAsyncFunc = syncToAsync(this::getQrCodes);
            handlers.put(getQrCodesProxyAsyncMethod.getMethodName(), new UnaryStubMethodHandler<>(getQrCodesAsyncFunc));
            BiConsumer<com.iqexception.fxhelper.api.rest.shop.SwitchFxRequest, StreamObserver<com.iqexception.fxhelper.api.common.BaseResponse>> switchFxFunc = this::switchFx;
            handlers.put(switchFxMethod.getMethodName(), new UnaryStubMethodHandler<>(switchFxFunc));
            BiConsumer<com.iqexception.fxhelper.api.rest.shop.SwitchFxRequest, StreamObserver<com.iqexception.fxhelper.api.common.BaseResponse>> switchFxAsyncFunc = syncToAsync(this::switchFx);
            handlers.put(switchFxProxyAsyncMethod.getMethodName(), new UnaryStubMethodHandler<>(switchFxAsyncFunc));




            return new StubInvoker<>(this, url, ShopRestService.class, handlers);
        }


        @Override
        public com.iqexception.fxhelper.api.rest.shop.CreateResponse create(com.iqexception.fxhelper.api.rest.shop.CreateRequest request){
            throw unimplementedMethodException(createMethod);
        }

        @Override
        public com.iqexception.fxhelper.api.common.BaseResponse update(com.iqexception.fxhelper.api.rest.shop.UpdateRequest request){
            throw unimplementedMethodException(updateMethod);
        }

        @Override
        public com.iqexception.fxhelper.api.rest.shop.GetShopListResponse getShopList(com.iqexception.fxhelper.api.common.BaseRequest request){
            throw unimplementedMethodException(getShopListMethod);
        }

        @Override
        public com.iqexception.fxhelper.api.rest.shop.GetShopPublicInfoResponse getShopPublicInfo(com.iqexception.fxhelper.api.rest.shop.GetShopPublicInfoRequest request){
            throw unimplementedMethodException(getShopPublicInfoMethod);
        }

        @Override
        public com.iqexception.fxhelper.api.rest.shop.GetShopDetailInfoResponse getShopDetailInfo(com.iqexception.fxhelper.api.rest.shop.GetShopDetailInfoRequest request){
            throw unimplementedMethodException(getShopDetailInfoMethod);
        }

        @Override
        public com.iqexception.fxhelper.api.rest.shop.GetQrCodesResponse getQrCodes(com.iqexception.fxhelper.api.rest.shop.GetQrCodesRequest request){
            throw unimplementedMethodException(getQrCodesMethod);
        }

        @Override
        public com.iqexception.fxhelper.api.common.BaseResponse switchFx(com.iqexception.fxhelper.api.rest.shop.SwitchFxRequest request){
            throw unimplementedMethodException(switchFxMethod);
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

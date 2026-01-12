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

package com.iqexception.fxhelper.api.rest.agg;

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

public final class DubboAggRestServiceTriple {

    public static final String SERVICE_NAME = AggRestService.SERVICE_NAME;

    private static final StubServiceDescriptor serviceDescriptor = new StubServiceDescriptor(SERVICE_NAME,AggRestService.class);

    static {
        org.apache.dubbo.rpc.protocol.tri.service.SchemaDescriptorRegistry.addSchemaDescriptor(SERVICE_NAME,AggRest.getDescriptor());
        StubSuppliers.addSupplier(SERVICE_NAME, DubboAggRestServiceTriple::newStub);
        StubSuppliers.addSupplier(AggRestService.JAVA_SERVICE_NAME,  DubboAggRestServiceTriple::newStub);
        StubSuppliers.addDescriptor(SERVICE_NAME, serviceDescriptor);
        StubSuppliers.addDescriptor(AggRestService.JAVA_SERVICE_NAME, serviceDescriptor);
    }

    @SuppressWarnings("all")
    public static AggRestService newStub(Invoker<?> invoker) {
        return new AggRestServiceStub((Invoker<AggRestService>)invoker);
    }

    private static final StubMethodDescriptor getShopMethod = new StubMethodDescriptor("getShop",
    com.iqexception.fxhelper.api.rest.agg.GetShopRequest.class, com.iqexception.fxhelper.api.rest.agg.GetShopResponse.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.rest.agg.GetShopRequest::parseFrom,
    com.iqexception.fxhelper.api.rest.agg.GetShopResponse::parseFrom);

    private static final StubMethodDescriptor getShopAsyncMethod = new StubMethodDescriptor("getShop",
    com.iqexception.fxhelper.api.rest.agg.GetShopRequest.class, java.util.concurrent.CompletableFuture.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.rest.agg.GetShopRequest::parseFrom,
    com.iqexception.fxhelper.api.rest.agg.GetShopResponse::parseFrom);

    private static final StubMethodDescriptor getShopProxyAsyncMethod = new StubMethodDescriptor("getShopAsync",
    com.iqexception.fxhelper.api.rest.agg.GetShopRequest.class, com.iqexception.fxhelper.api.rest.agg.GetShopResponse.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.rest.agg.GetShopRequest::parseFrom,
    com.iqexception.fxhelper.api.rest.agg.GetShopResponse::parseFrom);
    private static final StubMethodDescriptor getShopOrderListMethod = new StubMethodDescriptor("getShopOrderList",
    com.iqexception.fxhelper.api.rest.agg.GetShopOrderListRequest.class, com.iqexception.fxhelper.api.rest.agg.GetShopOrderListResponse.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.rest.agg.GetShopOrderListRequest::parseFrom,
    com.iqexception.fxhelper.api.rest.agg.GetShopOrderListResponse::parseFrom);

    private static final StubMethodDescriptor getShopOrderListAsyncMethod = new StubMethodDescriptor("getShopOrderList",
    com.iqexception.fxhelper.api.rest.agg.GetShopOrderListRequest.class, java.util.concurrent.CompletableFuture.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.rest.agg.GetShopOrderListRequest::parseFrom,
    com.iqexception.fxhelper.api.rest.agg.GetShopOrderListResponse::parseFrom);

    private static final StubMethodDescriptor getShopOrderListProxyAsyncMethod = new StubMethodDescriptor("getShopOrderListAsync",
    com.iqexception.fxhelper.api.rest.agg.GetShopOrderListRequest.class, com.iqexception.fxhelper.api.rest.agg.GetShopOrderListResponse.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.rest.agg.GetShopOrderListRequest::parseFrom,
    com.iqexception.fxhelper.api.rest.agg.GetShopOrderListResponse::parseFrom);
    private static final StubMethodDescriptor getUserOrderListMethod = new StubMethodDescriptor("getUserOrderList",
    com.iqexception.fxhelper.api.rest.agg.GetUserOrderListRequest.class, com.iqexception.fxhelper.api.rest.agg.GetUserOrderListResponse.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.rest.agg.GetUserOrderListRequest::parseFrom,
    com.iqexception.fxhelper.api.rest.agg.GetUserOrderListResponse::parseFrom);

    private static final StubMethodDescriptor getUserOrderListAsyncMethod = new StubMethodDescriptor("getUserOrderList",
    com.iqexception.fxhelper.api.rest.agg.GetUserOrderListRequest.class, java.util.concurrent.CompletableFuture.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.rest.agg.GetUserOrderListRequest::parseFrom,
    com.iqexception.fxhelper.api.rest.agg.GetUserOrderListResponse::parseFrom);

    private static final StubMethodDescriptor getUserOrderListProxyAsyncMethod = new StubMethodDescriptor("getUserOrderListAsync",
    com.iqexception.fxhelper.api.rest.agg.GetUserOrderListRequest.class, com.iqexception.fxhelper.api.rest.agg.GetUserOrderListResponse.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.rest.agg.GetUserOrderListRequest::parseFrom,
    com.iqexception.fxhelper.api.rest.agg.GetUserOrderListResponse::parseFrom);




    static{
        serviceDescriptor.addMethod(getShopMethod);
        serviceDescriptor.addMethod(getShopProxyAsyncMethod);
        serviceDescriptor.addMethod(getShopOrderListMethod);
        serviceDescriptor.addMethod(getShopOrderListProxyAsyncMethod);
        serviceDescriptor.addMethod(getUserOrderListMethod);
        serviceDescriptor.addMethod(getUserOrderListProxyAsyncMethod);
    }

    public static class AggRestServiceStub implements AggRestService{
        private final Invoker<AggRestService> invoker;

        public AggRestServiceStub(Invoker<AggRestService> invoker) {
            this.invoker = invoker;
        }

        @Override
        public com.iqexception.fxhelper.api.rest.agg.GetShopResponse getShop(com.iqexception.fxhelper.api.rest.agg.GetShopRequest request){
            return StubInvocationUtil.unaryCall(invoker, getShopMethod, request);
        }

        public CompletableFuture<com.iqexception.fxhelper.api.rest.agg.GetShopResponse> getShopAsync(com.iqexception.fxhelper.api.rest.agg.GetShopRequest request){
            return StubInvocationUtil.unaryCall(invoker, getShopAsyncMethod, request);
        }

        public void getShop(com.iqexception.fxhelper.api.rest.agg.GetShopRequest request, StreamObserver<com.iqexception.fxhelper.api.rest.agg.GetShopResponse> responseObserver){
            StubInvocationUtil.unaryCall(invoker, getShopMethod , request, responseObserver);
        }
        @Override
        public com.iqexception.fxhelper.api.rest.agg.GetShopOrderListResponse getShopOrderList(com.iqexception.fxhelper.api.rest.agg.GetShopOrderListRequest request){
            return StubInvocationUtil.unaryCall(invoker, getShopOrderListMethod, request);
        }

        public CompletableFuture<com.iqexception.fxhelper.api.rest.agg.GetShopOrderListResponse> getShopOrderListAsync(com.iqexception.fxhelper.api.rest.agg.GetShopOrderListRequest request){
            return StubInvocationUtil.unaryCall(invoker, getShopOrderListAsyncMethod, request);
        }

        public void getShopOrderList(com.iqexception.fxhelper.api.rest.agg.GetShopOrderListRequest request, StreamObserver<com.iqexception.fxhelper.api.rest.agg.GetShopOrderListResponse> responseObserver){
            StubInvocationUtil.unaryCall(invoker, getShopOrderListMethod , request, responseObserver);
        }
        @Override
        public com.iqexception.fxhelper.api.rest.agg.GetUserOrderListResponse getUserOrderList(com.iqexception.fxhelper.api.rest.agg.GetUserOrderListRequest request){
            return StubInvocationUtil.unaryCall(invoker, getUserOrderListMethod, request);
        }

        public CompletableFuture<com.iqexception.fxhelper.api.rest.agg.GetUserOrderListResponse> getUserOrderListAsync(com.iqexception.fxhelper.api.rest.agg.GetUserOrderListRequest request){
            return StubInvocationUtil.unaryCall(invoker, getUserOrderListAsyncMethod, request);
        }

        public void getUserOrderList(com.iqexception.fxhelper.api.rest.agg.GetUserOrderListRequest request, StreamObserver<com.iqexception.fxhelper.api.rest.agg.GetUserOrderListResponse> responseObserver){
            StubInvocationUtil.unaryCall(invoker, getUserOrderListMethod , request, responseObserver);
        }



    }

    public static abstract class AggRestServiceImplBase implements AggRestService, ServerService<AggRestService> {

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
        public CompletableFuture<com.iqexception.fxhelper.api.rest.agg.GetShopResponse> getShopAsync(com.iqexception.fxhelper.api.rest.agg.GetShopRequest request){
                return CompletableFuture.completedFuture(getShop(request));
        }
        @Override
        public CompletableFuture<com.iqexception.fxhelper.api.rest.agg.GetShopOrderListResponse> getShopOrderListAsync(com.iqexception.fxhelper.api.rest.agg.GetShopOrderListRequest request){
                return CompletableFuture.completedFuture(getShopOrderList(request));
        }
        @Override
        public CompletableFuture<com.iqexception.fxhelper.api.rest.agg.GetUserOrderListResponse> getUserOrderListAsync(com.iqexception.fxhelper.api.rest.agg.GetUserOrderListRequest request){
                return CompletableFuture.completedFuture(getUserOrderList(request));
        }

        /**
        * This server stream type unary method is <b>only</b> used for generated stub to support async unary method.
        * It will not be called if you are NOT using Dubbo3 generated triple stub and <b>DO NOT</b> implement this method.
        */
        public void getShop(com.iqexception.fxhelper.api.rest.agg.GetShopRequest request, StreamObserver<com.iqexception.fxhelper.api.rest.agg.GetShopResponse> responseObserver){
            getShopAsync(request).whenComplete((r, t) -> {
                if (t != null) {
                    responseObserver.onError(t);
                } else {
                    responseObserver.onNext(r);
                    responseObserver.onCompleted();
                }
            });
        }
        public void getShopOrderList(com.iqexception.fxhelper.api.rest.agg.GetShopOrderListRequest request, StreamObserver<com.iqexception.fxhelper.api.rest.agg.GetShopOrderListResponse> responseObserver){
            getShopOrderListAsync(request).whenComplete((r, t) -> {
                if (t != null) {
                    responseObserver.onError(t);
                } else {
                    responseObserver.onNext(r);
                    responseObserver.onCompleted();
                }
            });
        }
        public void getUserOrderList(com.iqexception.fxhelper.api.rest.agg.GetUserOrderListRequest request, StreamObserver<com.iqexception.fxhelper.api.rest.agg.GetUserOrderListResponse> responseObserver){
            getUserOrderListAsync(request).whenComplete((r, t) -> {
                if (t != null) {
                    responseObserver.onError(t);
                } else {
                    responseObserver.onNext(r);
                    responseObserver.onCompleted();
                }
            });
        }

        @Override
        public final Invoker<AggRestService> getInvoker(URL url) {
            PathResolver pathResolver = url.getOrDefaultFrameworkModel()
            .getExtensionLoader(PathResolver.class)
            .getDefaultExtension();
            Map<String,StubMethodHandler<?, ?>> handlers = new HashMap<>();

            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/getShop");
            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/getShopAsync");
            // for compatibility
            pathResolver.addNativeStub( "/" + JAVA_SERVICE_NAME + "/getShop");
            pathResolver.addNativeStub( "/" + JAVA_SERVICE_NAME + "/getShopAsync");

            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/getShopOrderList");
            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/getShopOrderListAsync");
            // for compatibility
            pathResolver.addNativeStub( "/" + JAVA_SERVICE_NAME + "/getShopOrderList");
            pathResolver.addNativeStub( "/" + JAVA_SERVICE_NAME + "/getShopOrderListAsync");

            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/getUserOrderList");
            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/getUserOrderListAsync");
            // for compatibility
            pathResolver.addNativeStub( "/" + JAVA_SERVICE_NAME + "/getUserOrderList");
            pathResolver.addNativeStub( "/" + JAVA_SERVICE_NAME + "/getUserOrderListAsync");


            BiConsumer<com.iqexception.fxhelper.api.rest.agg.GetShopRequest, StreamObserver<com.iqexception.fxhelper.api.rest.agg.GetShopResponse>> getShopFunc = this::getShop;
            handlers.put(getShopMethod.getMethodName(), new UnaryStubMethodHandler<>(getShopFunc));
            BiConsumer<com.iqexception.fxhelper.api.rest.agg.GetShopRequest, StreamObserver<com.iqexception.fxhelper.api.rest.agg.GetShopResponse>> getShopAsyncFunc = syncToAsync(this::getShop);
            handlers.put(getShopProxyAsyncMethod.getMethodName(), new UnaryStubMethodHandler<>(getShopAsyncFunc));
            BiConsumer<com.iqexception.fxhelper.api.rest.agg.GetShopOrderListRequest, StreamObserver<com.iqexception.fxhelper.api.rest.agg.GetShopOrderListResponse>> getShopOrderListFunc = this::getShopOrderList;
            handlers.put(getShopOrderListMethod.getMethodName(), new UnaryStubMethodHandler<>(getShopOrderListFunc));
            BiConsumer<com.iqexception.fxhelper.api.rest.agg.GetShopOrderListRequest, StreamObserver<com.iqexception.fxhelper.api.rest.agg.GetShopOrderListResponse>> getShopOrderListAsyncFunc = syncToAsync(this::getShopOrderList);
            handlers.put(getShopOrderListProxyAsyncMethod.getMethodName(), new UnaryStubMethodHandler<>(getShopOrderListAsyncFunc));
            BiConsumer<com.iqexception.fxhelper.api.rest.agg.GetUserOrderListRequest, StreamObserver<com.iqexception.fxhelper.api.rest.agg.GetUserOrderListResponse>> getUserOrderListFunc = this::getUserOrderList;
            handlers.put(getUserOrderListMethod.getMethodName(), new UnaryStubMethodHandler<>(getUserOrderListFunc));
            BiConsumer<com.iqexception.fxhelper.api.rest.agg.GetUserOrderListRequest, StreamObserver<com.iqexception.fxhelper.api.rest.agg.GetUserOrderListResponse>> getUserOrderListAsyncFunc = syncToAsync(this::getUserOrderList);
            handlers.put(getUserOrderListProxyAsyncMethod.getMethodName(), new UnaryStubMethodHandler<>(getUserOrderListAsyncFunc));




            return new StubInvoker<>(this, url, AggRestService.class, handlers);
        }


        @Override
        public com.iqexception.fxhelper.api.rest.agg.GetShopResponse getShop(com.iqexception.fxhelper.api.rest.agg.GetShopRequest request){
            throw unimplementedMethodException(getShopMethod);
        }

        @Override
        public com.iqexception.fxhelper.api.rest.agg.GetShopOrderListResponse getShopOrderList(com.iqexception.fxhelper.api.rest.agg.GetShopOrderListRequest request){
            throw unimplementedMethodException(getShopOrderListMethod);
        }

        @Override
        public com.iqexception.fxhelper.api.rest.agg.GetUserOrderListResponse getUserOrderList(com.iqexception.fxhelper.api.rest.agg.GetUserOrderListRequest request){
            throw unimplementedMethodException(getUserOrderListMethod);
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

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

package com.iqexception.fxhelper.api.shop;

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

public final class DubboShopServiceTriple {

    public static final String SERVICE_NAME = ShopService.SERVICE_NAME;

    private static final StubServiceDescriptor serviceDescriptor = new StubServiceDescriptor(SERVICE_NAME,ShopService.class);

    static {
        org.apache.dubbo.rpc.protocol.tri.service.SchemaDescriptorRegistry.addSchemaDescriptor(SERVICE_NAME,Shop.getDescriptor());
        StubSuppliers.addSupplier(SERVICE_NAME, DubboShopServiceTriple::newStub);
        StubSuppliers.addSupplier(ShopService.JAVA_SERVICE_NAME,  DubboShopServiceTriple::newStub);
        StubSuppliers.addDescriptor(SERVICE_NAME, serviceDescriptor);
        StubSuppliers.addDescriptor(ShopService.JAVA_SERVICE_NAME, serviceDescriptor);
    }

    @SuppressWarnings("all")
    public static ShopService newStub(Invoker<?> invoker) {
        return new ShopServiceStub((Invoker<ShopService>)invoker);
    }

    private static final StubMethodDescriptor getUserShopListMethod = new StubMethodDescriptor("getUserShopList",
    com.iqexception.fxhelper.api.shop.GetUserShopListRequest.class, com.iqexception.fxhelper.api.shop.GetUserShopListResponse.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.shop.GetUserShopListRequest::parseFrom,
    com.iqexception.fxhelper.api.shop.GetUserShopListResponse::parseFrom);

    private static final StubMethodDescriptor getUserShopListAsyncMethod = new StubMethodDescriptor("getUserShopList",
    com.iqexception.fxhelper.api.shop.GetUserShopListRequest.class, java.util.concurrent.CompletableFuture.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.shop.GetUserShopListRequest::parseFrom,
    com.iqexception.fxhelper.api.shop.GetUserShopListResponse::parseFrom);

    private static final StubMethodDescriptor getUserShopListProxyAsyncMethod = new StubMethodDescriptor("getUserShopListAsync",
    com.iqexception.fxhelper.api.shop.GetUserShopListRequest.class, com.iqexception.fxhelper.api.shop.GetUserShopListResponse.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.shop.GetUserShopListRequest::parseFrom,
    com.iqexception.fxhelper.api.shop.GetUserShopListResponse::parseFrom);
    private static final StubMethodDescriptor getShopListMethod = new StubMethodDescriptor("getShopList",
    com.iqexception.fxhelper.api.shop.GetShopListRequest.class, com.iqexception.fxhelper.api.shop.GetShopListResponse.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.shop.GetShopListRequest::parseFrom,
    com.iqexception.fxhelper.api.shop.GetShopListResponse::parseFrom);

    private static final StubMethodDescriptor getShopListAsyncMethod = new StubMethodDescriptor("getShopList",
    com.iqexception.fxhelper.api.shop.GetShopListRequest.class, java.util.concurrent.CompletableFuture.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.shop.GetShopListRequest::parseFrom,
    com.iqexception.fxhelper.api.shop.GetShopListResponse::parseFrom);

    private static final StubMethodDescriptor getShopListProxyAsyncMethod = new StubMethodDescriptor("getShopListAsync",
    com.iqexception.fxhelper.api.shop.GetShopListRequest.class, com.iqexception.fxhelper.api.shop.GetShopListResponse.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.shop.GetShopListRequest::parseFrom,
    com.iqexception.fxhelper.api.shop.GetShopListResponse::parseFrom);
    private static final StubMethodDescriptor getSerialNoMethod = new StubMethodDescriptor("getSerialNo",
    com.iqexception.fxhelper.api.shop.GetSerialNoRequest.class, com.iqexception.fxhelper.api.shop.GetSerialNoResponse.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.shop.GetSerialNoRequest::parseFrom,
    com.iqexception.fxhelper.api.shop.GetSerialNoResponse::parseFrom);

    private static final StubMethodDescriptor getSerialNoAsyncMethod = new StubMethodDescriptor("getSerialNo",
    com.iqexception.fxhelper.api.shop.GetSerialNoRequest.class, java.util.concurrent.CompletableFuture.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.shop.GetSerialNoRequest::parseFrom,
    com.iqexception.fxhelper.api.shop.GetSerialNoResponse::parseFrom);

    private static final StubMethodDescriptor getSerialNoProxyAsyncMethod = new StubMethodDescriptor("getSerialNoAsync",
    com.iqexception.fxhelper.api.shop.GetSerialNoRequest.class, com.iqexception.fxhelper.api.shop.GetSerialNoResponse.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.shop.GetSerialNoRequest::parseFrom,
    com.iqexception.fxhelper.api.shop.GetSerialNoResponse::parseFrom);
    private static final StubMethodDescriptor getShopMethod = new StubMethodDescriptor("getShop",
    com.iqexception.fxhelper.api.shop.GetShopRequest.class, com.iqexception.fxhelper.api.shop.GetShopResponse.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.shop.GetShopRequest::parseFrom,
    com.iqexception.fxhelper.api.shop.GetShopResponse::parseFrom);

    private static final StubMethodDescriptor getShopAsyncMethod = new StubMethodDescriptor("getShop",
    com.iqexception.fxhelper.api.shop.GetShopRequest.class, java.util.concurrent.CompletableFuture.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.shop.GetShopRequest::parseFrom,
    com.iqexception.fxhelper.api.shop.GetShopResponse::parseFrom);

    private static final StubMethodDescriptor getShopProxyAsyncMethod = new StubMethodDescriptor("getShopAsync",
    com.iqexception.fxhelper.api.shop.GetShopRequest.class, com.iqexception.fxhelper.api.shop.GetShopResponse.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.shop.GetShopRequest::parseFrom,
    com.iqexception.fxhelper.api.shop.GetShopResponse::parseFrom);




    static{
        serviceDescriptor.addMethod(getUserShopListMethod);
        serviceDescriptor.addMethod(getUserShopListProxyAsyncMethod);
        serviceDescriptor.addMethod(getShopListMethod);
        serviceDescriptor.addMethod(getShopListProxyAsyncMethod);
        serviceDescriptor.addMethod(getSerialNoMethod);
        serviceDescriptor.addMethod(getSerialNoProxyAsyncMethod);
        serviceDescriptor.addMethod(getShopMethod);
        serviceDescriptor.addMethod(getShopProxyAsyncMethod);
    }

    public static class ShopServiceStub implements ShopService{
        private final Invoker<ShopService> invoker;

        public ShopServiceStub(Invoker<ShopService> invoker) {
            this.invoker = invoker;
        }

        @Override
        public com.iqexception.fxhelper.api.shop.GetUserShopListResponse getUserShopList(com.iqexception.fxhelper.api.shop.GetUserShopListRequest request){
            return StubInvocationUtil.unaryCall(invoker, getUserShopListMethod, request);
        }

        public CompletableFuture<com.iqexception.fxhelper.api.shop.GetUserShopListResponse> getUserShopListAsync(com.iqexception.fxhelper.api.shop.GetUserShopListRequest request){
            return StubInvocationUtil.unaryCall(invoker, getUserShopListAsyncMethod, request);
        }

        public void getUserShopList(com.iqexception.fxhelper.api.shop.GetUserShopListRequest request, StreamObserver<com.iqexception.fxhelper.api.shop.GetUserShopListResponse> responseObserver){
            StubInvocationUtil.unaryCall(invoker, getUserShopListMethod , request, responseObserver);
        }
        @Override
        public com.iqexception.fxhelper.api.shop.GetShopListResponse getShopList(com.iqexception.fxhelper.api.shop.GetShopListRequest request){
            return StubInvocationUtil.unaryCall(invoker, getShopListMethod, request);
        }

        public CompletableFuture<com.iqexception.fxhelper.api.shop.GetShopListResponse> getShopListAsync(com.iqexception.fxhelper.api.shop.GetShopListRequest request){
            return StubInvocationUtil.unaryCall(invoker, getShopListAsyncMethod, request);
        }

        public void getShopList(com.iqexception.fxhelper.api.shop.GetShopListRequest request, StreamObserver<com.iqexception.fxhelper.api.shop.GetShopListResponse> responseObserver){
            StubInvocationUtil.unaryCall(invoker, getShopListMethod , request, responseObserver);
        }
        @Override
        public com.iqexception.fxhelper.api.shop.GetSerialNoResponse getSerialNo(com.iqexception.fxhelper.api.shop.GetSerialNoRequest request){
            return StubInvocationUtil.unaryCall(invoker, getSerialNoMethod, request);
        }

        public CompletableFuture<com.iqexception.fxhelper.api.shop.GetSerialNoResponse> getSerialNoAsync(com.iqexception.fxhelper.api.shop.GetSerialNoRequest request){
            return StubInvocationUtil.unaryCall(invoker, getSerialNoAsyncMethod, request);
        }

        public void getSerialNo(com.iqexception.fxhelper.api.shop.GetSerialNoRequest request, StreamObserver<com.iqexception.fxhelper.api.shop.GetSerialNoResponse> responseObserver){
            StubInvocationUtil.unaryCall(invoker, getSerialNoMethod , request, responseObserver);
        }
        @Override
        public com.iqexception.fxhelper.api.shop.GetShopResponse getShop(com.iqexception.fxhelper.api.shop.GetShopRequest request){
            return StubInvocationUtil.unaryCall(invoker, getShopMethod, request);
        }

        public CompletableFuture<com.iqexception.fxhelper.api.shop.GetShopResponse> getShopAsync(com.iqexception.fxhelper.api.shop.GetShopRequest request){
            return StubInvocationUtil.unaryCall(invoker, getShopAsyncMethod, request);
        }

        public void getShop(com.iqexception.fxhelper.api.shop.GetShopRequest request, StreamObserver<com.iqexception.fxhelper.api.shop.GetShopResponse> responseObserver){
            StubInvocationUtil.unaryCall(invoker, getShopMethod , request, responseObserver);
        }



    }

    public static abstract class ShopServiceImplBase implements ShopService, ServerService<ShopService> {

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
        public CompletableFuture<com.iqexception.fxhelper.api.shop.GetUserShopListResponse> getUserShopListAsync(com.iqexception.fxhelper.api.shop.GetUserShopListRequest request){
                return CompletableFuture.completedFuture(getUserShopList(request));
        }
        @Override
        public CompletableFuture<com.iqexception.fxhelper.api.shop.GetShopListResponse> getShopListAsync(com.iqexception.fxhelper.api.shop.GetShopListRequest request){
                return CompletableFuture.completedFuture(getShopList(request));
        }
        @Override
        public CompletableFuture<com.iqexception.fxhelper.api.shop.GetSerialNoResponse> getSerialNoAsync(com.iqexception.fxhelper.api.shop.GetSerialNoRequest request){
                return CompletableFuture.completedFuture(getSerialNo(request));
        }
        @Override
        public CompletableFuture<com.iqexception.fxhelper.api.shop.GetShopResponse> getShopAsync(com.iqexception.fxhelper.api.shop.GetShopRequest request){
                return CompletableFuture.completedFuture(getShop(request));
        }

        /**
        * This server stream type unary method is <b>only</b> used for generated stub to support async unary method.
        * It will not be called if you are NOT using Dubbo3 generated triple stub and <b>DO NOT</b> implement this method.
        */
        public void getUserShopList(com.iqexception.fxhelper.api.shop.GetUserShopListRequest request, StreamObserver<com.iqexception.fxhelper.api.shop.GetUserShopListResponse> responseObserver){
            getUserShopListAsync(request).whenComplete((r, t) -> {
                if (t != null) {
                    responseObserver.onError(t);
                } else {
                    responseObserver.onNext(r);
                    responseObserver.onCompleted();
                }
            });
        }
        public void getShopList(com.iqexception.fxhelper.api.shop.GetShopListRequest request, StreamObserver<com.iqexception.fxhelper.api.shop.GetShopListResponse> responseObserver){
            getShopListAsync(request).whenComplete((r, t) -> {
                if (t != null) {
                    responseObserver.onError(t);
                } else {
                    responseObserver.onNext(r);
                    responseObserver.onCompleted();
                }
            });
        }
        public void getSerialNo(com.iqexception.fxhelper.api.shop.GetSerialNoRequest request, StreamObserver<com.iqexception.fxhelper.api.shop.GetSerialNoResponse> responseObserver){
            getSerialNoAsync(request).whenComplete((r, t) -> {
                if (t != null) {
                    responseObserver.onError(t);
                } else {
                    responseObserver.onNext(r);
                    responseObserver.onCompleted();
                }
            });
        }
        public void getShop(com.iqexception.fxhelper.api.shop.GetShopRequest request, StreamObserver<com.iqexception.fxhelper.api.shop.GetShopResponse> responseObserver){
            getShopAsync(request).whenComplete((r, t) -> {
                if (t != null) {
                    responseObserver.onError(t);
                } else {
                    responseObserver.onNext(r);
                    responseObserver.onCompleted();
                }
            });
        }

        @Override
        public final Invoker<ShopService> getInvoker(URL url) {
            PathResolver pathResolver = url.getOrDefaultFrameworkModel()
            .getExtensionLoader(PathResolver.class)
            .getDefaultExtension();
            Map<String,StubMethodHandler<?, ?>> handlers = new HashMap<>();

            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/getUserShopList");
            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/getUserShopListAsync");
            // for compatibility
            pathResolver.addNativeStub( "/" + JAVA_SERVICE_NAME + "/getUserShopList");
            pathResolver.addNativeStub( "/" + JAVA_SERVICE_NAME + "/getUserShopListAsync");

            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/getShopList");
            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/getShopListAsync");
            // for compatibility
            pathResolver.addNativeStub( "/" + JAVA_SERVICE_NAME + "/getShopList");
            pathResolver.addNativeStub( "/" + JAVA_SERVICE_NAME + "/getShopListAsync");

            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/getSerialNo");
            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/getSerialNoAsync");
            // for compatibility
            pathResolver.addNativeStub( "/" + JAVA_SERVICE_NAME + "/getSerialNo");
            pathResolver.addNativeStub( "/" + JAVA_SERVICE_NAME + "/getSerialNoAsync");

            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/getShop");
            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/getShopAsync");
            // for compatibility
            pathResolver.addNativeStub( "/" + JAVA_SERVICE_NAME + "/getShop");
            pathResolver.addNativeStub( "/" + JAVA_SERVICE_NAME + "/getShopAsync");


            BiConsumer<com.iqexception.fxhelper.api.shop.GetUserShopListRequest, StreamObserver<com.iqexception.fxhelper.api.shop.GetUserShopListResponse>> getUserShopListFunc = this::getUserShopList;
            handlers.put(getUserShopListMethod.getMethodName(), new UnaryStubMethodHandler<>(getUserShopListFunc));
            BiConsumer<com.iqexception.fxhelper.api.shop.GetUserShopListRequest, StreamObserver<com.iqexception.fxhelper.api.shop.GetUserShopListResponse>> getUserShopListAsyncFunc = syncToAsync(this::getUserShopList);
            handlers.put(getUserShopListProxyAsyncMethod.getMethodName(), new UnaryStubMethodHandler<>(getUserShopListAsyncFunc));
            BiConsumer<com.iqexception.fxhelper.api.shop.GetShopListRequest, StreamObserver<com.iqexception.fxhelper.api.shop.GetShopListResponse>> getShopListFunc = this::getShopList;
            handlers.put(getShopListMethod.getMethodName(), new UnaryStubMethodHandler<>(getShopListFunc));
            BiConsumer<com.iqexception.fxhelper.api.shop.GetShopListRequest, StreamObserver<com.iqexception.fxhelper.api.shop.GetShopListResponse>> getShopListAsyncFunc = syncToAsync(this::getShopList);
            handlers.put(getShopListProxyAsyncMethod.getMethodName(), new UnaryStubMethodHandler<>(getShopListAsyncFunc));
            BiConsumer<com.iqexception.fxhelper.api.shop.GetSerialNoRequest, StreamObserver<com.iqexception.fxhelper.api.shop.GetSerialNoResponse>> getSerialNoFunc = this::getSerialNo;
            handlers.put(getSerialNoMethod.getMethodName(), new UnaryStubMethodHandler<>(getSerialNoFunc));
            BiConsumer<com.iqexception.fxhelper.api.shop.GetSerialNoRequest, StreamObserver<com.iqexception.fxhelper.api.shop.GetSerialNoResponse>> getSerialNoAsyncFunc = syncToAsync(this::getSerialNo);
            handlers.put(getSerialNoProxyAsyncMethod.getMethodName(), new UnaryStubMethodHandler<>(getSerialNoAsyncFunc));
            BiConsumer<com.iqexception.fxhelper.api.shop.GetShopRequest, StreamObserver<com.iqexception.fxhelper.api.shop.GetShopResponse>> getShopFunc = this::getShop;
            handlers.put(getShopMethod.getMethodName(), new UnaryStubMethodHandler<>(getShopFunc));
            BiConsumer<com.iqexception.fxhelper.api.shop.GetShopRequest, StreamObserver<com.iqexception.fxhelper.api.shop.GetShopResponse>> getShopAsyncFunc = syncToAsync(this::getShop);
            handlers.put(getShopProxyAsyncMethod.getMethodName(), new UnaryStubMethodHandler<>(getShopAsyncFunc));




            return new StubInvoker<>(this, url, ShopService.class, handlers);
        }


        @Override
        public com.iqexception.fxhelper.api.shop.GetUserShopListResponse getUserShopList(com.iqexception.fxhelper.api.shop.GetUserShopListRequest request){
            throw unimplementedMethodException(getUserShopListMethod);
        }

        @Override
        public com.iqexception.fxhelper.api.shop.GetShopListResponse getShopList(com.iqexception.fxhelper.api.shop.GetShopListRequest request){
            throw unimplementedMethodException(getShopListMethod);
        }

        @Override
        public com.iqexception.fxhelper.api.shop.GetSerialNoResponse getSerialNo(com.iqexception.fxhelper.api.shop.GetSerialNoRequest request){
            throw unimplementedMethodException(getSerialNoMethod);
        }

        @Override
        public com.iqexception.fxhelper.api.shop.GetShopResponse getShop(com.iqexception.fxhelper.api.shop.GetShopRequest request){
            throw unimplementedMethodException(getShopMethod);
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

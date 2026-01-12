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

package com.iqexception.fxhelper.api.order;

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

public final class DubboOrderServiceTriple {

    public static final String SERVICE_NAME = OrderService.SERVICE_NAME;

    private static final StubServiceDescriptor serviceDescriptor = new StubServiceDescriptor(SERVICE_NAME,OrderService.class);

    static {
        org.apache.dubbo.rpc.protocol.tri.service.SchemaDescriptorRegistry.addSchemaDescriptor(SERVICE_NAME,Order.getDescriptor());
        StubSuppliers.addSupplier(SERVICE_NAME, DubboOrderServiceTriple::newStub);
        StubSuppliers.addSupplier(OrderService.JAVA_SERVICE_NAME,  DubboOrderServiceTriple::newStub);
        StubSuppliers.addDescriptor(SERVICE_NAME, serviceDescriptor);
        StubSuppliers.addDescriptor(OrderService.JAVA_SERVICE_NAME, serviceDescriptor);
    }

    @SuppressWarnings("all")
    public static OrderService newStub(Invoker<?> invoker) {
        return new OrderServiceStub((Invoker<OrderService>)invoker);
    }

    private static final StubMethodDescriptor getOrderTotalAmountMethod = new StubMethodDescriptor("getOrderTotalAmount",
    com.iqexception.fxhelper.api.order.GetOrderTotalAmountRequest.class, com.iqexception.fxhelper.api.order.GetOrderTotalAmountResponse.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.order.GetOrderTotalAmountRequest::parseFrom,
    com.iqexception.fxhelper.api.order.GetOrderTotalAmountResponse::parseFrom);

    private static final StubMethodDescriptor getOrderTotalAmountAsyncMethod = new StubMethodDescriptor("getOrderTotalAmount",
    com.iqexception.fxhelper.api.order.GetOrderTotalAmountRequest.class, java.util.concurrent.CompletableFuture.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.order.GetOrderTotalAmountRequest::parseFrom,
    com.iqexception.fxhelper.api.order.GetOrderTotalAmountResponse::parseFrom);

    private static final StubMethodDescriptor getOrderTotalAmountProxyAsyncMethod = new StubMethodDescriptor("getOrderTotalAmountAsync",
    com.iqexception.fxhelper.api.order.GetOrderTotalAmountRequest.class, com.iqexception.fxhelper.api.order.GetOrderTotalAmountResponse.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.order.GetOrderTotalAmountRequest::parseFrom,
    com.iqexception.fxhelper.api.order.GetOrderTotalAmountResponse::parseFrom);
    private static final StubMethodDescriptor getOrderListMethod = new StubMethodDescriptor("getOrderList",
    com.iqexception.fxhelper.api.order.GetOrderListRequest.class, com.iqexception.fxhelper.api.order.GetOrderListResponse.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.order.GetOrderListRequest::parseFrom,
    com.iqexception.fxhelper.api.order.GetOrderListResponse::parseFrom);

    private static final StubMethodDescriptor getOrderListAsyncMethod = new StubMethodDescriptor("getOrderList",
    com.iqexception.fxhelper.api.order.GetOrderListRequest.class, java.util.concurrent.CompletableFuture.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.order.GetOrderListRequest::parseFrom,
    com.iqexception.fxhelper.api.order.GetOrderListResponse::parseFrom);

    private static final StubMethodDescriptor getOrderListProxyAsyncMethod = new StubMethodDescriptor("getOrderListAsync",
    com.iqexception.fxhelper.api.order.GetOrderListRequest.class, com.iqexception.fxhelper.api.order.GetOrderListResponse.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.order.GetOrderListRequest::parseFrom,
    com.iqexception.fxhelper.api.order.GetOrderListResponse::parseFrom);
    private static final StubMethodDescriptor getOrderCountMethod = new StubMethodDescriptor("getOrderCount",
    com.iqexception.fxhelper.api.order.GetOrderCountRequest.class, com.iqexception.fxhelper.api.order.GetOrderCountResponse.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.order.GetOrderCountRequest::parseFrom,
    com.iqexception.fxhelper.api.order.GetOrderCountResponse::parseFrom);

    private static final StubMethodDescriptor getOrderCountAsyncMethod = new StubMethodDescriptor("getOrderCount",
    com.iqexception.fxhelper.api.order.GetOrderCountRequest.class, java.util.concurrent.CompletableFuture.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.order.GetOrderCountRequest::parseFrom,
    com.iqexception.fxhelper.api.order.GetOrderCountResponse::parseFrom);

    private static final StubMethodDescriptor getOrderCountProxyAsyncMethod = new StubMethodDescriptor("getOrderCountAsync",
    com.iqexception.fxhelper.api.order.GetOrderCountRequest.class, com.iqexception.fxhelper.api.order.GetOrderCountResponse.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.iqexception.fxhelper.api.order.GetOrderCountRequest::parseFrom,
    com.iqexception.fxhelper.api.order.GetOrderCountResponse::parseFrom);




    static{
        serviceDescriptor.addMethod(getOrderTotalAmountMethod);
        serviceDescriptor.addMethod(getOrderTotalAmountProxyAsyncMethod);
        serviceDescriptor.addMethod(getOrderListMethod);
        serviceDescriptor.addMethod(getOrderListProxyAsyncMethod);
        serviceDescriptor.addMethod(getOrderCountMethod);
        serviceDescriptor.addMethod(getOrderCountProxyAsyncMethod);
    }

    public static class OrderServiceStub implements OrderService{
        private final Invoker<OrderService> invoker;

        public OrderServiceStub(Invoker<OrderService> invoker) {
            this.invoker = invoker;
        }

        @Override
        public com.iqexception.fxhelper.api.order.GetOrderTotalAmountResponse getOrderTotalAmount(com.iqexception.fxhelper.api.order.GetOrderTotalAmountRequest request){
            return StubInvocationUtil.unaryCall(invoker, getOrderTotalAmountMethod, request);
        }

        public CompletableFuture<com.iqexception.fxhelper.api.order.GetOrderTotalAmountResponse> getOrderTotalAmountAsync(com.iqexception.fxhelper.api.order.GetOrderTotalAmountRequest request){
            return StubInvocationUtil.unaryCall(invoker, getOrderTotalAmountAsyncMethod, request);
        }

        public void getOrderTotalAmount(com.iqexception.fxhelper.api.order.GetOrderTotalAmountRequest request, StreamObserver<com.iqexception.fxhelper.api.order.GetOrderTotalAmountResponse> responseObserver){
            StubInvocationUtil.unaryCall(invoker, getOrderTotalAmountMethod , request, responseObserver);
        }
        @Override
        public com.iqexception.fxhelper.api.order.GetOrderListResponse getOrderList(com.iqexception.fxhelper.api.order.GetOrderListRequest request){
            return StubInvocationUtil.unaryCall(invoker, getOrderListMethod, request);
        }

        public CompletableFuture<com.iqexception.fxhelper.api.order.GetOrderListResponse> getOrderListAsync(com.iqexception.fxhelper.api.order.GetOrderListRequest request){
            return StubInvocationUtil.unaryCall(invoker, getOrderListAsyncMethod, request);
        }

        public void getOrderList(com.iqexception.fxhelper.api.order.GetOrderListRequest request, StreamObserver<com.iqexception.fxhelper.api.order.GetOrderListResponse> responseObserver){
            StubInvocationUtil.unaryCall(invoker, getOrderListMethod , request, responseObserver);
        }
        @Override
        public com.iqexception.fxhelper.api.order.GetOrderCountResponse getOrderCount(com.iqexception.fxhelper.api.order.GetOrderCountRequest request){
            return StubInvocationUtil.unaryCall(invoker, getOrderCountMethod, request);
        }

        public CompletableFuture<com.iqexception.fxhelper.api.order.GetOrderCountResponse> getOrderCountAsync(com.iqexception.fxhelper.api.order.GetOrderCountRequest request){
            return StubInvocationUtil.unaryCall(invoker, getOrderCountAsyncMethod, request);
        }

        public void getOrderCount(com.iqexception.fxhelper.api.order.GetOrderCountRequest request, StreamObserver<com.iqexception.fxhelper.api.order.GetOrderCountResponse> responseObserver){
            StubInvocationUtil.unaryCall(invoker, getOrderCountMethod , request, responseObserver);
        }



    }

    public static abstract class OrderServiceImplBase implements OrderService, ServerService<OrderService> {

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
        public CompletableFuture<com.iqexception.fxhelper.api.order.GetOrderTotalAmountResponse> getOrderTotalAmountAsync(com.iqexception.fxhelper.api.order.GetOrderTotalAmountRequest request){
                return CompletableFuture.completedFuture(getOrderTotalAmount(request));
        }
        @Override
        public CompletableFuture<com.iqexception.fxhelper.api.order.GetOrderListResponse> getOrderListAsync(com.iqexception.fxhelper.api.order.GetOrderListRequest request){
                return CompletableFuture.completedFuture(getOrderList(request));
        }
        @Override
        public CompletableFuture<com.iqexception.fxhelper.api.order.GetOrderCountResponse> getOrderCountAsync(com.iqexception.fxhelper.api.order.GetOrderCountRequest request){
                return CompletableFuture.completedFuture(getOrderCount(request));
        }

        /**
        * This server stream type unary method is <b>only</b> used for generated stub to support async unary method.
        * It will not be called if you are NOT using Dubbo3 generated triple stub and <b>DO NOT</b> implement this method.
        */
        public void getOrderTotalAmount(com.iqexception.fxhelper.api.order.GetOrderTotalAmountRequest request, StreamObserver<com.iqexception.fxhelper.api.order.GetOrderTotalAmountResponse> responseObserver){
            getOrderTotalAmountAsync(request).whenComplete((r, t) -> {
                if (t != null) {
                    responseObserver.onError(t);
                } else {
                    responseObserver.onNext(r);
                    responseObserver.onCompleted();
                }
            });
        }
        public void getOrderList(com.iqexception.fxhelper.api.order.GetOrderListRequest request, StreamObserver<com.iqexception.fxhelper.api.order.GetOrderListResponse> responseObserver){
            getOrderListAsync(request).whenComplete((r, t) -> {
                if (t != null) {
                    responseObserver.onError(t);
                } else {
                    responseObserver.onNext(r);
                    responseObserver.onCompleted();
                }
            });
        }
        public void getOrderCount(com.iqexception.fxhelper.api.order.GetOrderCountRequest request, StreamObserver<com.iqexception.fxhelper.api.order.GetOrderCountResponse> responseObserver){
            getOrderCountAsync(request).whenComplete((r, t) -> {
                if (t != null) {
                    responseObserver.onError(t);
                } else {
                    responseObserver.onNext(r);
                    responseObserver.onCompleted();
                }
            });
        }

        @Override
        public final Invoker<OrderService> getInvoker(URL url) {
            PathResolver pathResolver = url.getOrDefaultFrameworkModel()
            .getExtensionLoader(PathResolver.class)
            .getDefaultExtension();
            Map<String,StubMethodHandler<?, ?>> handlers = new HashMap<>();

            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/getOrderTotalAmount");
            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/getOrderTotalAmountAsync");
            // for compatibility
            pathResolver.addNativeStub( "/" + JAVA_SERVICE_NAME + "/getOrderTotalAmount");
            pathResolver.addNativeStub( "/" + JAVA_SERVICE_NAME + "/getOrderTotalAmountAsync");

            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/getOrderList");
            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/getOrderListAsync");
            // for compatibility
            pathResolver.addNativeStub( "/" + JAVA_SERVICE_NAME + "/getOrderList");
            pathResolver.addNativeStub( "/" + JAVA_SERVICE_NAME + "/getOrderListAsync");

            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/getOrderCount");
            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/getOrderCountAsync");
            // for compatibility
            pathResolver.addNativeStub( "/" + JAVA_SERVICE_NAME + "/getOrderCount");
            pathResolver.addNativeStub( "/" + JAVA_SERVICE_NAME + "/getOrderCountAsync");


            BiConsumer<com.iqexception.fxhelper.api.order.GetOrderTotalAmountRequest, StreamObserver<com.iqexception.fxhelper.api.order.GetOrderTotalAmountResponse>> getOrderTotalAmountFunc = this::getOrderTotalAmount;
            handlers.put(getOrderTotalAmountMethod.getMethodName(), new UnaryStubMethodHandler<>(getOrderTotalAmountFunc));
            BiConsumer<com.iqexception.fxhelper.api.order.GetOrderTotalAmountRequest, StreamObserver<com.iqexception.fxhelper.api.order.GetOrderTotalAmountResponse>> getOrderTotalAmountAsyncFunc = syncToAsync(this::getOrderTotalAmount);
            handlers.put(getOrderTotalAmountProxyAsyncMethod.getMethodName(), new UnaryStubMethodHandler<>(getOrderTotalAmountAsyncFunc));
            BiConsumer<com.iqexception.fxhelper.api.order.GetOrderListRequest, StreamObserver<com.iqexception.fxhelper.api.order.GetOrderListResponse>> getOrderListFunc = this::getOrderList;
            handlers.put(getOrderListMethod.getMethodName(), new UnaryStubMethodHandler<>(getOrderListFunc));
            BiConsumer<com.iqexception.fxhelper.api.order.GetOrderListRequest, StreamObserver<com.iqexception.fxhelper.api.order.GetOrderListResponse>> getOrderListAsyncFunc = syncToAsync(this::getOrderList);
            handlers.put(getOrderListProxyAsyncMethod.getMethodName(), new UnaryStubMethodHandler<>(getOrderListAsyncFunc));
            BiConsumer<com.iqexception.fxhelper.api.order.GetOrderCountRequest, StreamObserver<com.iqexception.fxhelper.api.order.GetOrderCountResponse>> getOrderCountFunc = this::getOrderCount;
            handlers.put(getOrderCountMethod.getMethodName(), new UnaryStubMethodHandler<>(getOrderCountFunc));
            BiConsumer<com.iqexception.fxhelper.api.order.GetOrderCountRequest, StreamObserver<com.iqexception.fxhelper.api.order.GetOrderCountResponse>> getOrderCountAsyncFunc = syncToAsync(this::getOrderCount);
            handlers.put(getOrderCountProxyAsyncMethod.getMethodName(), new UnaryStubMethodHandler<>(getOrderCountAsyncFunc));




            return new StubInvoker<>(this, url, OrderService.class, handlers);
        }


        @Override
        public com.iqexception.fxhelper.api.order.GetOrderTotalAmountResponse getOrderTotalAmount(com.iqexception.fxhelper.api.order.GetOrderTotalAmountRequest request){
            throw unimplementedMethodException(getOrderTotalAmountMethod);
        }

        @Override
        public com.iqexception.fxhelper.api.order.GetOrderListResponse getOrderList(com.iqexception.fxhelper.api.order.GetOrderListRequest request){
            throw unimplementedMethodException(getOrderListMethod);
        }

        @Override
        public com.iqexception.fxhelper.api.order.GetOrderCountResponse getOrderCount(com.iqexception.fxhelper.api.order.GetOrderCountRequest request){
            throw unimplementedMethodException(getOrderCountMethod);
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
